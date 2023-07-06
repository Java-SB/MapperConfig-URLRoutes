package com.isa.platform.u202023992.si729ebu202023992.inventory.repository;

import com.isa.platform.u202023992.si729ebu202023992.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsBySerialNumber(String serialNumber);
}
