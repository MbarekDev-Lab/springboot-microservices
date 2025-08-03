package com.mbarekDev.currency_exchange_service.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class CircuitBreakerController {
    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse") // is a method hardcodedResponse(Exception ex);
    //@CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodedResponse")
   // @RateLimiter(name="default")
    @Bulkhead(name="sample-api")
    //10s => 10,000 calls to the sample-api
    public String sampleApi() {
        logger.info("Sample api call received");
 	//ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
      //  return forEntity.getBody();
        return "sample-api";
    }
    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }

    /*
            while ($true) {
            curl http://localhost:8000/sample-api
            Start-Sleep -Seconds 2
            Clear-Host
        }


        while ($true) {
            curl http://localhost:8000/sample-api
            Start-Sleep -Milliseconds 100
            Clear-Host
        }

        while ($true) {
            Clear-Host
            curl http://localhost:8000/sample-api
            Start-Sleep -Milliseconds 100
        }

        watch -n 0.1 curl http://localhost:8000/sample-api

        resilience4j.retry.instances.sample-api.maxRetryAttempts=5
        resilience4j.retry.instances.sample-api.waitDuration=1s
        resilience4j.retry.instances.sample-api.enableExponentialBackoff=true

        #resilience4j.circuitbreaker.instances.default.failureRateThreshold=90
        resilience4j.ratelimiter.instances.default.limitForPeriod=2
        resilience4j.ratelimiter.instances.default.limitRefreshPeriod=10s

        resilience4j.bulkhead.instances.default.maxConcurrentCalls=10
        resilience4j.bulkhead.instances.sample-api.maxConcurrentCalls=10
     */


}
