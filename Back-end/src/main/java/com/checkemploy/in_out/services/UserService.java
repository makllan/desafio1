package com.checkemploy.in_out.services;

import com.checkemploy.in_out.dto.EmployeeResponseDTO;
import com.checkemploy.in_out.dto.receive.LoginDTO;
import com.checkemploy.in_out.entities.Employee;
import com.checkemploy.in_out.repositories.EmployeeRepository;
import com.checkemploy.in_out.repositories.WorkRecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private EmployeeRepository employeeRepository;

    // --- 1. LOGIN ---
    public EmployeeResponseDTO login(LoginDTO loginDTO) {
        seedDataIfNeeded();
        Employee employee = employeeRepository.findAll().stream()
                .filter(e -> e.getEmail().equals(loginDTO.email()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));

        return new EmployeeResponseDTO(employee.getId(), employee.getName(), employee.getEmail());
    }

    private void seedDataIfNeeded() {
        if (employeeRepository.count() == 0) {
            System.out.println("🚨 Nenhum dado encontrado. Inicializando dados...");

            Employee employee = new Employee("Admin","admin@gmail.com");
            employeeRepository.save(employee);
        }
    }
}
