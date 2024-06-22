package com.example.backend_edispatch.controller;

import com.example.backend_edispatch.model.entity.Master;
import com.example.backend_edispatch.model.entity.User;
import com.example.backend_edispatch.service.MasterService;
import com.example.backend_edispatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/masters")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<Master>> getAllMasters() {
        return ResponseEntity.ok(masterService.getAllMasters());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Master> createMaster(@RequestBody Master master) {
        return ResponseEntity.ok(masterService.createMaster(master));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Master> updateMaster(@PathVariable Long id, @RequestBody Master master) {
        return ResponseEntity.ok(masterService.updateMaster(id, master));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMaster(@PathVariable Long id) {
        masterService.deleteMaster(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<Master> getMasterById(@PathVariable Long id) {
        return ResponseEntity.ok(masterService.getMasterById(id));
    }
}