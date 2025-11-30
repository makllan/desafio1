package com.checkemploy.in_out;

import com.checkemploy.in_out.dto.WorkRecordResponseDTO;
import com.checkemploy.in_out.entities.Employee;
import com.checkemploy.in_out.entities.WorkRecord;
import com.checkemploy.in_out.repositories.EmployeeRepository;
import com.checkemploy.in_out.repositories.WorkRecoRepository;
import com.checkemploy.in_out.services.Services;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicesTest {

    @InjectMocks
    private Services services;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private WorkRecoRepository workRecoRepository;


    @Test
    void deveCalcularDuracaoCorretamenteNoCheckOut() {

        Long employeeId = 1L;


        Employee employee = new Employee();
        employee.setId(employeeId);

        LocalDateTime checkInTime = LocalDateTime.now().minusHours(2);

        WorkRecord recordExistente = new WorkRecord();
        recordExistente.setId(10L);
        recordExistente.setCheckinTime(checkInTime);


        recordExistente.setEmployee(employee);


        when(workRecoRepository.findFirstByEmployeeIdAndCheckoutTimeIsNull(employeeId))
                .thenReturn(Optional.of(recordExistente));


        when(workRecoRepository.save(any(WorkRecord.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        WorkRecordResponseDTO response = services.checkOut(employeeId);

        assertNotNull(response.checkoutTime(), "O horário de checkout não deve ser nulo");


        long duracaoEsperada = 7200;


        assertTrue(Math.abs(response.duration() - duracaoEsperada) <= 2,
                "A duração deve ser de aproximadamente 7200 segundos (2 horas). Valor atual: " + response.duration());


        assertTrue(response.formattedDuration().startsWith("02:00"),
                "A string formatada deve começar com 02:00. Valor atual: " + response.formattedDuration());
    }


    @Test
    void deveLancarExcecaoAoTentarCheckInDuplicado() {

        Long employeeId = 1L;
        Employee employeeMock = new Employee();
        employeeMock.setId(employeeId);


        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.of(employeeMock));


        when(workRecoRepository.findFirstByEmployeeIdAndCheckoutTimeIsNull(employeeId))
                .thenReturn(Optional.of(new WorkRecord()));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            services.checkIn(employeeId);
        });

        assertEquals("Já existe um check-in aberto para este funcionário!", exception.getMessage());

        // Garante que o método save NUNCA foi chamado (não salvou nada no banco)
        verify(workRecoRepository, never()).save(any(WorkRecord.class));
    }
}