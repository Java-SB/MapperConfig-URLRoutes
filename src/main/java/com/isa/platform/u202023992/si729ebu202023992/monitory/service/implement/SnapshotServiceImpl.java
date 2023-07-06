package com.isa.platform.u202023992.si729ebu202023992.monitory.service.implement;

import com.isa.platform.u202023992.si729ebu202023992.common.exception.ResourceNotFoundException;
import com.isa.platform.u202023992.si729ebu202023992.common.exception.ValidationException;
import com.isa.platform.u202023992.si729ebu202023992.inventory.enums.MonitoringLevel;
import com.isa.platform.u202023992.si729ebu202023992.inventory.model.Product;
import com.isa.platform.u202023992.si729ebu202023992.inventory.repository.ProductRepository;
import com.isa.platform.u202023992.si729ebu202023992.monitory.dto.SnapshotRequestDto;
import com.isa.platform.u202023992.si729ebu202023992.monitory.model.Snapshot;
import com.isa.platform.u202023992.si729ebu202023992.monitory.repository.SnapshotRepository;
import com.isa.platform.u202023992.si729ebu202023992.monitory.service.SnapshotService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SnapshotServiceImpl implements SnapshotService {
    private final SnapshotRepository snapshotRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public SnapshotServiceImpl(SnapshotRepository snapshotRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.snapshotRepository = snapshotRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Snapshot createSnapshot(Long productId, SnapshotRequestDto snapshotRequestDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product id not found"));
        validateSnapshot(snapshotRequestDto, product);
        validateRequestEnergyAndLeakage(productId, snapshotRequestDto);
        Snapshot snapshot = modelMapper.map(snapshotRequestDto, Snapshot.class);
        return snapshotRepository.save(snapshot);
    }
    @Override
    public List<Snapshot> getSnapshotById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product id not found"));
        return snapshotRepository.findByProductSerialNumber(product.getSerialNumber());
    }
    private void validateSnapshot(SnapshotRequestDto snapshotRequestDto, Product product) {
        if (snapshotRepository.existsBySnapshotId(snapshotRequestDto.getSnapshotId())) {
            throw new ValidationException("Snapshot with snapshot id " + snapshotRequestDto.getSnapshotId() + " already exists");
        }
        if (snapshotRequestDto.getSnapshotId() == null || snapshotRequestDto.getSnapshotId().trim().isEmpty()) {
            throw new ValidationException("Snapshot id cannot be empty");
        }
        if (snapshotRequestDto.getProductSerialNumber() == null || snapshotRequestDto.getProductSerialNumber().trim().isEmpty()) {
            throw new ValidationException("Product serial number cannot be empty");
        }
        if (snapshotRequestDto.getTemperature() == null || snapshotRequestDto.getTemperature() <= 0) {
            throw new ValidationException("Snapshot temperature cannot be empty and must be greater than 0");
        }
        if (!snapshotRequestDto.getProductSerialNumber().equals(product.getSerialNumber())) {
            throw new ValidationException("Incorrect. The product id corresponds to the serial number → " + product.getSerialNumber());
        }
    }
    private void validateRequestEnergyAndLeakage(Long productId, SnapshotRequestDto snapshotRequestDto) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            if (productOptional.get().getMonitoringLevel() == MonitoringLevel.ADVANCE_MONITORING) {
                if (snapshotRequestDto.getEnergy() == null || snapshotRequestDto.getEnergy() <= 0) {
                    throw new ValidationException("Snapshot energy cannot be empty and must be greater than 0");
                }
                if (snapshotRequestDto.getLeakage() == null || snapshotRequestDto.getLeakage() < 0 || snapshotRequestDto.getLeakage() >= 2) {
                    throw new ValidationException("Snapshot leakage cannot be empty and must be between 0 = No Leakage, 1 = Leakage");
                }
            } else if (productOptional.get().getMonitoringLevel() == MonitoringLevel.ESSENTIAL_MONITORING) {
                if (snapshotRequestDto.getEnergy() != null || snapshotRequestDto.getLeakage() != null) {
                    throw new ValidationException("Snapshot Data not compatible with product current Monitoring Level");
                }
            }
        }
    }
}
