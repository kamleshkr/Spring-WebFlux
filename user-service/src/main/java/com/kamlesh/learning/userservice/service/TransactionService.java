package com.kamlesh.learning.userservice.service;

import com.kamlesh.learning.userservice.dto.TransactionRequestDto;
import com.kamlesh.learning.userservice.dto.TransactionResponseDto;
import com.kamlesh.learning.userservice.dto.UserTransactionDto;
import com.kamlesh.learning.userservice.repository.UserRepository;
import com.kamlesh.learning.userservice.repository.UserTransactionRepository;
import com.kamlesh.learning.userservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.kamlesh.learning.userservice.dto.TransactionStatus.APPROVED;
import static com.kamlesh.learning.userservice.dto.TransactionStatus.DECLINED;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTransactionRepository transactionRepository;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto) {
        return userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> EntityDtoUtil.toEntity(requestDto))
                .flatMap(transactionRepository::save)
                .map(ut -> EntityDtoUtil.toDto(requestDto, APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toDto(requestDto, DECLINED));
    }

    public Flux<UserTransactionDto> getTransactionsByUserId(Integer userId) {
        return transactionRepository.findByUserId(userId)
                .map(EntityDtoUtil::toDto);
    }
}
