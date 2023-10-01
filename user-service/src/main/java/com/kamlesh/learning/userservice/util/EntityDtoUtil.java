package com.kamlesh.learning.userservice.util;

import com.kamlesh.learning.userservice.dto.*;
import com.kamlesh.learning.userservice.entity.User;
import com.kamlesh.learning.userservice.entity.UserTransaction;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

public class EntityDtoUtil {

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    public static User toEntity(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }

    public static UserTransaction toEntity(TransactionRequestDto requestDto) {
        UserTransaction ut = new UserTransaction();
        ut.setUserId(requestDto.getUserId());
        ut.setAmount(requestDto.getAmount());
        ut.setTransactionDate(LocalDateTime.now());
        return ut;
    }

    public static TransactionResponseDto toDto(TransactionRequestDto requestDto, TransactionStatus status) {
        TransactionResponseDto responseDto = new TransactionResponseDto();
        responseDto.setAmount(requestDto.getAmount());
        responseDto.setUserId(requestDto.getUserId());
        responseDto.setStatus(status);
        return responseDto;
    }

    public static UserTransactionDto toDto(UserTransaction userTransaction) {
        UserTransactionDto dto = new UserTransactionDto();
        BeanUtils.copyProperties(userTransaction, dto);
        return dto;
    }
}
