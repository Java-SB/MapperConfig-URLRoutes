package com.isa.platform.u202023992.si729ebu202023992.monitory.controller;

import com.isa.platform.u202023992.si729ebu202023992.monitory.dto.SnapshotRequestDto;
import com.isa.platform.u202023992.si729ebu202023992.monitory.model.Snapshot;
import com.isa.platform.u202023992.si729ebu202023992.monitory.service.SnapshotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SnapshotController {
    private final SnapshotService snapshotService;

    public SnapshotController(SnapshotService snapshotService) {
        this.snapshotService = snapshotService;
    }

    @Transactional(readOnly = true)
    @GetMapping("/products/{productId}/snapshots")
    public ResponseEntity<List<Snapshot>> getSnapshotById(@PathVariable(value = "productId") Long productId) {
        return ResponseEntity.ok(snapshotService.getSnapshotById(productId));
    }

    @Transactional
    @PostMapping("/products/{productId}/snapshots")
    public ResponseEntity<Snapshot> createSnapshot(@PathVariable Long productId, @RequestBody SnapshotRequestDto snapshotRequestDto) {
        return new ResponseEntity<>(snapshotService.createSnapshot(productId, snapshotRequestDto), HttpStatus.CREATED);
    }
}
