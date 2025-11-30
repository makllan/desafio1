package com.checkemploy.in_out.dto;

import java.time.LocalDateTime;

public record WorkRecordResponseDTO(
        Long id,
        Long employeeId,
        LocalDateTime checkinTime,
        LocalDateTime checkoutTime,
        Long duration,
        String formattedDuration
) {

}
