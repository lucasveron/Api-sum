package com.tenpo.Apisum.repository;

import com.tenpo.Apisum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class UserRepository {

    @Autowired()
    NamedParameterJdbcTemplate jdbcTemplate;

   public void signupUser(User user) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("creation_date", new Timestamp(System.currentTimeMillis()))
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword());
        jdbcTemplate.update("INSERT INTO public.users (user_id,creation_date,email,password) VALUES" +
                        " (nextval('users_id_seq'),:creation_date,:email,:password)",
                parameterSource);
    }

    public void saveInvocation(String request, String response) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("creation_date", new Timestamp(System.currentTimeMillis()))
                .addValue("request", request.substring(0, Math.min(request.length(),255)))
                .addValue("response", response.substring(0,Math.min(response.length(),255)));
        jdbcTemplate.update(
                "INSERT INTO public.invocations (invocation_id,creation_date,request,response) VALUES" +
                        "(nextval('invocations_id_seq'), :creation_date, :request, :response)", parameterSource);
    }
}
