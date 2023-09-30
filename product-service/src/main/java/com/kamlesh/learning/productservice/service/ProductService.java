package com.kamlesh.learning.productservice.service;

import com.kamlesh.learning.productservice.dto.ProductDto;
import com.kamlesh.learning.productservice.repository.ProductRepository;
import com.kamlesh.learning.productservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Flux<ProductDto> getAll() {
        return repository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> getProductById(String id) {
        return repository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(repository::insert)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono) {
        return repository.findById(id)
                .flatMap(p -> productDtoMono
                        .map(EntityDtoUtil::toEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(repository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteProduct(String id) {
        return repository.deleteById(id);
    }

    public Flux<ProductDto> getProductByPriceRange(int min, int max) {
        return repository.findByPriceBetween(Range.closed(min, max))
                .map(EntityDtoUtil::toDto);
        /*return repository.findAll()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .map(EntityDtoUtil::toDto);*/
    }
}
