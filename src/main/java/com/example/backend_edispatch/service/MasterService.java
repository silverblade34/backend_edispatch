package com.example.backend_edispatch.service;

import com.example.backend_edispatch.model.entity.Master;

import java.util.List;

public interface MasterService {
    List<Master> getAllMasters();
    Master createMaster(Master master);
    Master updateMaster(Long id, Master master);
    void deleteMaster(Long id);
    Master getMasterById(Long id);
}