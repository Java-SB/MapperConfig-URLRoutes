package com.isa.platform.u202023992.si729ebu202023992.inventory.service.implement;

import com.isa.platform.u202023992.si729ebu202023992.common.exception.ResourceNotFoundException;
import com.isa.platform.u202023992.si729ebu202023992.common.exception.ValidationException;
import com.isa.platform.u202023992.si729ebu202023992.inventory.dto.ProductRequestDto;
import com.isa.platform.u202023992.si729ebu202023992.inventory.enums.MonitoringLevel;
import com.isa.platform.u202023992.si729ebu202023992.inventory.model.Product;
import com.isa.platform.u202023992.si729ebu202023992.inventory.repository.ProductRepository;
import com.isa.platform.u202023992.si729ebu202023992.inventory.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }
    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        validateProduct(productRequestDto);
        Product product = modelMapper.map(productRequestDto, Product.class);
        return productRepository.save(product);
    }
    private void validateProduct(ProductRequestDto productRequestDto) {
        if (productRepository.existsBySerialNumber(productRequestDto.getSerialNumber())) {
            throw new ValidationException("Product with serial number " + productRequestDto.getSerialNumber() + " already exists");
        }
        if (productRequestDto.getBrand() == null || productRequestDto.getBrand().trim().isEmpty()) {
            throw new ValidationException("Product brand cannot be empty");
        }
        if (productRequestDto.getModel() == null || productRequestDto.getModel().trim().isEmpty()) {
            throw new ValidationException("Product model cannot be empty");
        }
        if (productRequestDto.getSerialNumber() == null || productRequestDto.getSerialNumber().trim().isEmpty()) {
            throw new ValidationException("Product serial number cannot be empty");
        }
        if (productRequestDto.getMonitoringLevel() == null || productRequestDto.getMonitoringLevel().trim().isEmpty()) {
            throw new ValidationException("Product monitoring level cannot be empty");
        }
        if (!productRequestDto.getMonitoringLevel().equals(MonitoringLevel.ESSENTIAL_MONITORING.name()) &&
                !productRequestDto.getMonitoringLevel().equals(MonitoringLevel.ADVANCE_MONITORING.name())) {
            throw new ValidationException("Product monitoring level must be either ESSENTIAL_MONITORING or ADVANCE_MONITORING");
        }
    }
}
