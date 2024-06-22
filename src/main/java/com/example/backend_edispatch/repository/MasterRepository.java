package com.example.backend_edispatch.repository;

import com.example.backend_edispatch.model.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master, Long> {
}