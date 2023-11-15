package com.kamlesh.learning.productservice.service;

import com.kamlesh.learning.productservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataSetupService implements CommandLineRunner {

    @Autowired
    private ProductService service;

    @Override
    public void run(String... args) {
        ProductDto product1 = new ProductDto("4k-tv", 1000);
        ProductDto product2 = new ProductDto("slr-camera", 750);
        ProductDto product3 = new ProductDto("iphone", 800);
        ProductDto product4 = new ProductDto("headphone", 100);

        Flux.just(product1, product2, product3, product4)
                .concatWith(newProducts())
                .flatMap(p -> service.insertProduct(Mono.just(p)))
                .subscribe(System.out::println);
    }

    private Flux<ProductDto> newProducts() {
        return Flux.range(1, 1000)
                .delayElements(Duration.ofSeconds(2))
                .map(i -> new ProductDto("product-" + i, ThreadLocalRandom.current().nextInt(10, 100)));

    }
}
