package com.tenpo.Apisum.services;


import com.tenpo.Apisum.model.ServiceExternResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SchedulerExternalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SumService.class);
    @Value("${restTemplate.extern-service.url}")
    private String serviceExternUrl;
    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;
    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedDelay = 100000)
    public void schedulePercentageIncrement() {
        LOGGER.info("Init schedulePercentageIncrement ...");
        int inc = -1;
        try {
            inc = retrieveIncrement();
        } catch (RuntimeException ex){
            LOGGER.error("Error calling extern service, msg:", ex.getMessage());
        }
        redisTemplate.opsForValue().set("tenpo::per-inc", inc);
        LOGGER.info("Save percentage-increment {}", inc);
    }

    public int lastPercentageIncrement(){
        return redisTemplate.opsForValue().get("tenpo::per-inc");
    }

    @Retryable(maxAttempts=3, value = RuntimeException.class,
            backoff = @Backoff(delay = 5000, multiplier = 2))
    private int retrieveIncrement(){
        ResponseEntity<ServiceExternResponse> res = restTemplate.getForEntity(
                serviceExternUrl,
                ServiceExternResponse.class);
        LOGGER.info("Extern service response: {}", res.getBody());
        return res.getBody().getIncrement_percentage();
    }
}
