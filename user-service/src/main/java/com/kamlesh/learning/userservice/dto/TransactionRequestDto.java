package com.kamlesh.learning.userservice.dto;

import lombok.Data;

@Data
public class TransactionRequestDto {

    private Integer userId;
    private Integer amount;
}
