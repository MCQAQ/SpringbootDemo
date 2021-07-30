package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by 21510 on 2021/7/9.
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "jpaDataSource")
    @Qualifier("jpaDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.jpa")
    public javax.sql.DataSource jpaDataSource() { return DataSourceBuilder.create().build(); }

    @Bean(name = "mybatisDataSource")
    @Qualifier("mybatisDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mybatis")
    public javax.sql.DataSource mybatisDataSource() { return DataSourceBuilder.create().build(); }

}
