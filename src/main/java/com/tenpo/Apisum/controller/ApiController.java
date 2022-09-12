package com.tenpo.Apisum.controller;

import com.tenpo.Apisum.model.*;
import com.tenpo.Apisum.services.SumService;
import com.tenpo.Apisum.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
    @Autowired
    private SumService sumService;

    @Autowired
    private UserService userService;

    @GetMapping(value="/ping", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String health(){
        return "";
    }
    @PostMapping(value="/signup/users", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignupResponse> sigupUsers(@RequestBody UserRequest userRequest) {
        userService.signup(userRequest);
        LOGGER.info("OK users signup!");
        return new ResponseEntity<SignupResponse>(HttpStatus.OK);
    }

    @PostMapping(value="/login", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> loginUser(@RequestBody UserRequest loginRequest){
        LOGGER.info("Login user {} ...", loginRequest.email);
        userService.loginUser(loginRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value="/percentage", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PercentageResponse> calculateSum(@RequestBody PercentageRequest percentageRequest){
        LOGGER.info("calculate: ({} + {}) * inc ",
                percentageRequest.getFirstNumber(), percentageRequest.getSecondNumber());
        int inc = sumService.calculateSum(percentageRequest);
        LOGGER.info("inc calculated: {}", inc);
        return ResponseEntity.ok(new PercentageResponse(inc));
    }

}
