package com.checkemploy.in_out.services;



import com.checkemploy.in_out.dto.EmployeeResponseDTO;
import com.checkemploy.in_out.dto.WorkRecordResponseDTO;
import com.checkemploy.in_out.dto.receive.LoginDTO;
import com.checkemploy.in_out.entities.Employee;
import com.checkemploy.in_out.entities.WorkRecord;
import com.checkemploy.in_out.repositories.EmployeeRepository;
import com.checkemploy.in_out.repositories.WorkRecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class Services {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WorkRecoRepository workRecoRepository;

    // --- 2. CHECK-IN ---
    public WorkRecordResponseDTO checkIn(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        Optional<WorkRecord> openRecord = workRecoRepository.findFirstByEmployeeIdAndCheckoutTimeIsNull(employeeId);
        if (openRecord.isPresent()) {
            throw new RuntimeException("Já existe um check-in aberto para este funcionário!");
        }

        WorkRecord record = new WorkRecord();
        record.setEmployee(employee);
        record.setCheckinTime(LocalDateTime.now());

        WorkRecord saved = workRecoRepository.save(record);

        // Retorna DTO Simples
        return new WorkRecordResponseDTO(
                saved.getId(),
                saved.getEmployee().getId(),
                saved.getCheckinTime(),
                saved.getCheckoutTime(),
                saved.getDuration(),
                ""
        );
    }

    // --- 3. CHECK-OUT ---
    public WorkRecordResponseDTO checkOut(Long employeeId) {

        WorkRecord record = workRecoRepository.findFirstByEmployeeIdAndCheckoutTimeIsNull(employeeId)
                .orElseThrow(() -> new RuntimeException("Não há check-in aberto para finalizar."));

        record.setCheckoutTime(LocalDateTime.now());
        long seconds = Duration.between(record.getCheckinTime(), record.getCheckoutTime()).getSeconds();
        record.setDuration(seconds);

        WorkRecord saved = workRecoRepository.save(record);

        return new WorkRecordResponseDTO(
                saved.getId(),
                saved.getEmployee().getId(),
                saved.getCheckinTime(),
                saved.getCheckoutTime(),
                saved.getDuration(),
                formatDuration(saved.getDuration())
        );
    }

    // --- 4. LISTAGEM ---
    public List<WorkRecordResponseDTO> listAll() {

        return workRecoRepository.findAll().stream()
                .map(record -> new WorkRecordResponseDTO(
                        record.getId(),
                        record.getEmployee().getId(),
                        record.getCheckinTime(),
                        record.getCheckoutTime(),
                        record.getDuration(),
                        formatDuration(record.getDuration())
                ))
                .toList();
    }

    private String formatDuration(Long seconds) {
        if (seconds == null) return null;

        long h = seconds / 3600;
        long m = (seconds % 3600) / 60;
        long s = seconds % 60;

        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
