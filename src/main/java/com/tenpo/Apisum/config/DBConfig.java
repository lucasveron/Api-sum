package com.tenpo.Apisum.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DBConfig {
    @Primary
    @Bean(name="dataSourceConfigurations")
    @ConfigurationProperties(prefix = "datasource.sql-server.configurations")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
