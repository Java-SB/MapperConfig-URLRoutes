package com.isa.platform.u202023992.si729ebu202023992.inventory.service;

import com.isa.platform.u202023992.si729ebu202023992.inventory.dto.ProductRequestDto;
import com.isa.platform.u202023992.si729ebu202023992.inventory.model.Product;

public interface ProductService {
    Product getProductById(Long id);
    Product createProduct(ProductRequestDto productRequestDto);
}
