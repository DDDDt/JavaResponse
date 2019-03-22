package com.dt.reactor.StepVerifier;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author dt 2019/3/22 18:03
 * 测试响应式流
 */
public class TestStepVerifier {

    private Flux<Integer> getFluxFromTo6(){
        return Flux.just(1,2,3,4,5,6);
    }

    private Mono<Integer> getMonoWithErroe(){
        return Mono.error(new Exception("error"));
    }


    @Test
    public void testViaStepVerifier(){

        StepVerifier.create(this.getFluxFromTo6())
                .expectNext(1,2,3,4,5,6)
                .expectComplete()
                .verify();

        StepVerifier.create(this.getMonoWithErroe())
                .expectErrorMessage("error")
                .verify();

    }

}
