package com.marceloserpa.reactorkafkapoc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class SaleController {

    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("hello")
    public Mono<String> sayHello(){
        return Mono.just("world");
    }

    @PostMapping
    public Mono<String> sale(@RequestBody Sale sale){
        return saleService.sale(sale)
                .map(result-> result ? "ok" : "failed");
    }

}
