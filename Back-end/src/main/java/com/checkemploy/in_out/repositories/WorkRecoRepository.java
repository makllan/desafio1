package com.checkemploy.in_out.repositories;

import com.checkemploy.in_out.entities.WorkRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkRecoRepository extends JpaRepository<WorkRecord, Integer> {
    Optional<WorkRecord> findFirstByEmployeeIdAndCheckoutTimeIsNull(Long employeeId);
    List<WorkRecord> findByEmployeeId(Long employeeId);
}
