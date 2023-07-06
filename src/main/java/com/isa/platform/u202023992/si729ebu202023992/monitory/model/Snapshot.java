package com.isa.platform.u202023992.si729ebu202023992.monitory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "snapshots")
public class Snapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "snapshot_Id", nullable = false)
    private String snapshotId;

    @Column(name = "product_Serial_Number", nullable = false)
    private String productSerialNumber;

    @Column(name = "temperature", nullable = false)
    private Double temperature;

    @Column(name = "energy")
    private Double energy;

    @Column(name = "leakage")
    private Integer leakage;
}
