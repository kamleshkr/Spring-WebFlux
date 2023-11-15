package com.kamlesh.learning.orderservice.service;

import com.kamlesh.learning.orderservice.dto.PurchaseOrderResponseDto;
import com.kamlesh.learning.orderservice.entity.PurchaseOrder;
import com.kamlesh.learning.orderservice.repository.PurchaseOrderRepository;
import com.kamlesh.learning.orderservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class OrderQueryService {

    @Autowired
    private PurchaseOrderRepository orderRepository;

    public Flux<PurchaseOrderResponseDto> getProductsByUserId(int userId) {
        return Flux.fromStream(() -> orderRepository.findByUserId(userId).stream())
                .map(EntityDtoUtil::getPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
