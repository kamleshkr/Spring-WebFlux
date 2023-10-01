package com.kamlesh.learning.userservice.controller;

import com.kamlesh.learning.userservice.dto.TransactionRequestDto;
import com.kamlesh.learning.userservice.dto.TransactionResponseDto;
import com.kamlesh.learning.userservice.dto.UserTransactionDto;
import com.kamlesh.learning.userservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
public class UserTransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Flux<UserTransactionDto> getTransactionsByUserId(@RequestParam Integer userId) {
        return transactionService.getTransactionsByUserId(userId);
    }

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> requestDtoMono) {
        return requestDtoMono.flatMap(transactionService::createTransaction);
    }



}
