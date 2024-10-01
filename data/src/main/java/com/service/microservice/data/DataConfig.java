package com.service.microservice.data;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
@Configuration
public class DataConfig {
    private static final Integer DEFAULT_QUERY_TIMEOUT_FOR_JDBC_TEMPLATE = 30 * 60;

    @Bean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate(
            @Qualifier(JpaDataSourceConfig.INTEGRATION_DATA_SOURCE_NAME)
            DataSource integrationDataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(integrationDataSource);
        jdbcTemplate.setQueryTimeout(DEFAULT_QUERY_TIMEOUT_FOR_JDBC_TEMPLATE);
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }
}
