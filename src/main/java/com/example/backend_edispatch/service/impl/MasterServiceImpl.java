package com.example.backend_edispatch.service.impl;

import com.example.backend_edispatch.model.entity.Master;
import com.example.backend_edispatch.model.entity.User;
import com.example.backend_edispatch.repository.MasterRepository;
import com.example.backend_edispatch.repository.UserRepository;
import com.example.backend_edispatch.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private MasterRepository masterRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Master> getAllMasters() {
        return masterRepository.findAll();
    }

    @Override
    public Master createMaster(Master master) {
        User user = userRepository.findById(master.getUser().getId()).orElseThrow(() -> new RuntimeException("User not found"));
        master.setUser(user);
        return masterRepository.save(master);
    }

    @Override
    public Master updateMaster(Long id, Master master) {
        Master existingMaster = masterRepository.findById(id).orElseThrow(() -> new RuntimeException("Master not found"));
        User user = userRepository.findById(master.getUser().getId()).orElseThrow(() -> new RuntimeException("User not found"));
        existingMaster.setBusinessName(master.getBusinessName());
        existingMaster.setUser(user);
        return masterRepository.save(existingMaster);
    }

    @Override
    public void deleteMaster(Long id) {
        masterRepository.deleteById(id);
    }

    @Override
    public Master getMasterById(Long id) {
        return masterRepository.findById(id).orElseThrow(() -> new RuntimeException("Master not found"));
    }
}