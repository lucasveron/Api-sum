package com.tenpo.Apisum.services;

import com.tenpo.Apisum.model.User;
import com.tenpo.Apisum.model.UserBuilder;
import com.tenpo.Apisum.model.UserRequest;
import com.tenpo.Apisum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void signup(UserRequest userRequest) {
        User user = new UserBuilder()
                        .withEmail(userRequest.email)
                        .withPassword(userRequest.password)
                        .build();
        userRepository.signupUser(user);
    }

    public void loginUser(UserRequest loginRequest) {
        //Not yet implemented
    }
}
