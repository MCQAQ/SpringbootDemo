package com.sbstest.Config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by 21510 on 2021/7/12.
 */

@Configuration
@MapperScan(basePackages = "com.sbstest.Dao.Mybatis", sqlSessionFactoryRef = "mybatisSqlSessionFactory", sqlSessionTemplateRef = "mybatisSqlSessionTemplate")
public class MybatisConfig {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final DataSource mybatisDataSource;

    @Autowired
    public MybatisConfig(@Qualifier("mybatisDataSource") DataSource mybatisDataSource) {
        this.mybatisDataSource = mybatisDataSource;
    }

    @Bean(name = "mybatisSqlSessionFactory")
    @Qualifier(value = "mybatisSqlSessionFactory")
    @Primary
    public SqlSessionFactory mybatisSQLSessionFactory() {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(mybatisDataSource);

        sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:/mybatis-mysql-config.xml"));

        try {
            sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/**/*.xml"));
            return sessionFactoryBean.getObject();
        } catch (Exception e){
            log.error(e.getMessage());
            return null;
        }

    }

    @Bean(name = "mybatisTranscationManager")
    public PlatformTransactionManager mybayisTransactionManager() {
        return new DataSourceTransactionManager(mybatisDataSource);
    }

    @Bean("mybatisSqlSessionTemplate")
    public SqlSessionTemplate mybatisSqlSessionTemplate(@Qualifier("mybatisSqlSessionFactory") SqlSessionFactory mybatisSqlSessionFactory){
        return new SqlSessionTemplate(mybatisSqlSessionFactory);
    }

}
