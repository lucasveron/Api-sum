package com.tenpo.Apisum.services;

import com.tenpo.Apisum.error.ExternServiceException;
import com.tenpo.Apisum.model.PercentageRequest;
import com.tenpo.Apisum.model.ServiceExternResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SumService {

    @Autowired
    private SchedulerExternalService schedulerExternalService;

    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(SumService.class);
    private static final int INC = 10;
    public int calculateSum(PercentageRequest percentageRequest) {
        int sum = percentageRequest.getFirstNumber() + percentageRequest.getSecondNumber();

        int inc = -1;
        try {
            inc = schedulerExternalService.lastPercentageIncrement();
        } catch (RuntimeException ex){
            String msg = String.format("Error calling extern service, msg:", ex.getMessage());
            LOGGER.error(msg);
            throw new ExternServiceException(msg);
        }
        return sum * inc/100;
    }
}
