package com.isa.platform.u202023992.si729ebu202023992.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private String brand;
    private String model;
    private String serialNumber;
    private String monitoringLevel;
}
