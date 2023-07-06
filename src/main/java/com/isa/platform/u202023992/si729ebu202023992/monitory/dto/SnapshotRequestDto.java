package com.isa.platform.u202023992.si729ebu202023992.monitory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnapshotRequestDto {

    private String snapshotId;
    private String productSerialNumber;
    private Double temperature;
    private Double energy;
    private Integer leakage;
}
