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

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

   public void signupUser(User user) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("creation_date", new Timestamp(System.currentTimeMillis()))
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword());
        jdbcTemplate.update("INSERT INTO users (user_id,creation_date,email,password) VALUES" +
                        " (nextval('user_id_seq'),:creation_date,:email,:password)",
                parameterSource);
    }
}
