package com.tenpo.Apisum.services;

import com.tenpo.Apisum.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvocationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvocationService.class);
    @Autowired
    private UserRepository userRepository;

    public void saveInvocation(String request, String response) {
        LOGGER.info("Save invocation request: {}, response: {}", request, response);
        userRepository.saveInvocation(request, response);
    }
}
