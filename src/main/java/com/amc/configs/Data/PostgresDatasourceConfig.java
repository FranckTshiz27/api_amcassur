package com.amc.configs.Data;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "postgresqlEntityManagerFactory", transactionManagerRef = "postgresqlTransactionManager", basePackages = {
        "com.amc.repository.myamc" })
public class PostgresDatasourceConfig {

    @Bean(name = "postgresqlDatasource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    public DataSource postgresqlDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresqlEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerfactoryBean(EntityManagerFactoryBuilder builder,
            @Qualifier("postgresqlDatasource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.amc.model.myamc")
                .build();
    }

    @Bean(name = "postgresqlTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
