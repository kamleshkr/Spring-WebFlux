package com.kamlesh.learning.orderservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserTransactionDto {

    private Integer id;
    private Integer userId;
    private Integer amount;
    private LocalDateTime transactionDate;
}
