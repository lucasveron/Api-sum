package com.tenpo.Apisum.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class UserBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserBuilder.class);
    private User user;
    public UserBuilder(){
        user = new User();
    }
    public UserBuilder withEmail(String email){
        user.setEmail(email);
        return this;
    }

    /**
     * Obviamente MD5 no es una funcion de Hash Criptografica segura. Se usa este método
     * para simplificar la implementación.
     * **/
    public UserBuilder withPassword(String password){
        String myHash;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            myHash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
        } catch (Exception ex){
            LOGGER.info("Error Hashing transform password");
            throw new RuntimeException("Error in signup");
        }

        user.setPassword(myHash);
        return this;
    }

    public User build() {
        return user;
    }
}
