package com.tenpo.Apisum.services;

import com.tenpo.Apisum.model.PercentageRequest;
import com.tenpo.Apisum.model.ServiceExternResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SumService {


    @Autowired
    private RestTemplate restTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(SumService.class);
    private static final int INC = 10;
    public int calculateSum(PercentageRequest percentageRequest) {
        int sum = percentageRequest.getFirstNumber() + percentageRequest.getSecondNumber();

        int inc = retrieveIncrement();

        return sum * inc/100;
    }
    @Retryable(maxAttempts=3, value = RuntimeException.class,
            backoff = @Backoff(delay = 5000, multiplier = 2))
    public int retrieveIncrement(){
        ResponseEntity<ServiceExternResponse> res = restTemplate.getForEntity(
                "https://tenpo-percentage.free.beeceptor.com/tenpo/percentage",
                ServiceExternResponse.class);
        LOGGER.info("Extern service response: {}", res.getBody());
        return res.getBody().getIncrement_percentage();
    }
}
