package com.isa.platform.u202023992.si729ebu202023992.monitory.repository;

import com.isa.platform.u202023992.si729ebu202023992.monitory.model.Snapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SnapshotRepository extends JpaRepository<Snapshot, Long> {
    Boolean existsBySnapshotId(String snapshotId);
    List<Snapshot> findByProductSerialNumber(String productSerialNumber);
}
