package com.dt.reactor.FluxMono;

import reactor.core.publisher.Mono;

/**
 * @author dt 2019/3/22 17:39
 * Mono
 */
public class LearnMono {

    public static void main(String[] args) {

        Mono<Integer> mono = Mono.just(1);

        mono.subscribe(System.out::println);

    }

}
