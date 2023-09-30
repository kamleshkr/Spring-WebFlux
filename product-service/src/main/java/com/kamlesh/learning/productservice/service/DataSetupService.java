package com.kamlesh.learning.productservice.service;

import com.kamlesh.learning.productservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner {

    @Autowired
    private ProductService service;

    @Override
    public void run(String... args) throws Exception {
        ProductDto product1 = new ProductDto("4k-tv", 1000);
        ProductDto product2 = new ProductDto("slr-camera", 750);
        ProductDto product3 = new ProductDto("iphone", 800);
        ProductDto product4 = new ProductDto("headphone", 100);

        Flux.just(product1, product2, product3, product4)
                .flatMap(p -> service.insertProduct(Mono.just(p)))
                .subscribe(System.out::println);
    }
}
