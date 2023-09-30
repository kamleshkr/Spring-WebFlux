package com.kamlesh.learning.userservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransaction extends ReactiveCrudRepository<UserTransaction, Integer> {

    
}