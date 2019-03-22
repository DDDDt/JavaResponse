package com.dt.reactor.FluxMono;

import reactor.core.publisher.Flux;

/**
 * @author dt 2019/3/22 17:38
 * Flux
 */
public class LearnFlux {

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6);

        // 订阅前什么都不会发生
        flux.subscribe(System.out::println);

        // 订阅捕获完成或者失败
        Flux.error(new Exception("error")).subscribe(System.out::println,System.err::println,() -> System.out.println("完成"));

    }

}
