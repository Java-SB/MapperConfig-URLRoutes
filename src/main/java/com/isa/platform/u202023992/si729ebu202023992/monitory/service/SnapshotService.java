package com.isa.platform.u202023992.si729ebu202023992.monitory.service;

import com.isa.platform.u202023992.si729ebu202023992.monitory.dto.SnapshotRequestDto;
import com.isa.platform.u202023992.si729ebu202023992.monitory.model.Snapshot;

import java.util.List;

public interface SnapshotService {
    Snapshot createSnapshot(Long id, SnapshotRequestDto snapshotRequestDto);
    List<Snapshot> getSnapshotById(Long id);

}
