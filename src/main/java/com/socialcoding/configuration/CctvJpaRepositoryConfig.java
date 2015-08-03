package com.socialcoding.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
public class CctvJpaRepositoryConfig {
    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(); //TODO set datasource
    }
}
