package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * Created by 21510 on 2021/7/9.
 */

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.example.demo.**.Repository", transactionManagerRef = "jpaTransactionManager", entityManagerFactoryRef = "jpaEntityManagerFactory")
@EnableTransactionManagement
public class JpaConfig {

    private final DataSource jpaDataSource;
    private final JpaProperties jpaProperties;

    @Autowired
    public JpaConfig(@Qualifier("jpaDataSource") DataSource jpaDataSource, JpaProperties jpaProperties) {
        this.jpaDataSource = jpaDataSource;
        this.jpaProperties = jpaProperties;
    }

    @Bean(name = "jpaEntityManager")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Bean(name = "jpaEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(jpaDataSource)
                .properties(jpaProperties.getHibernateProperties(new HibernateSettings()))
                .packages("com.sbstest.Dao.Entity")
                .persistenceUnit("jpaPersistenceUnit")
                .build();
    }

    @Bean(name = "jpaTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}
