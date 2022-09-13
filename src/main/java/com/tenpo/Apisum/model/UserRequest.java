package com.tenpo.Apisum.model;

import com.google.gson.Gson;

public class UserRequest {
    public String email;
    public String password;

    @Override
    public String toString(){
        return new Gson().toJson(this, UserRequest.class);
    }
}
