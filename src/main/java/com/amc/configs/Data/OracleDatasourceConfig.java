package com.amc.configs.Data;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "oracleEntityManagerFactory", transactionManagerRef = "oracleTransactionManager", basePackages = {
        "com.amc.repository.orass" })

public class OracleDatasourceConfig {

    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
    }

    // @Bean(name = "oracleEntityManagerFactory")
    // public LocalContainerEntityManagerFactoryBean entityManagerfactoryBean(EntityManagerFactoryBuilder builder,
    //         @Qualifier("oracleDataSource") DataSource dataSource) {
    //     return builder.dataSource(dataSource)
    //             .packages("com.amc.model.orass")
    //             .build();
    // }

    @Bean(name = "oracleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("oracleDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.amc.model.orass")
                .persistenceUnit("oracle")
                .build();
    }
    @Bean(name = "oracleTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("oracleEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }





// package com.amc.configs.Data;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.PropertySource;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.orm.jpa.JpaVendorAdapter;
// import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
// import org.springframework.transaction.PlatformTransactionManager;
// import org.springframework.transaction.annotation.EnableTransactionManagement;

// import javax.persistence.EntityManagerFactory;
// import javax.sql.DataSource;

// @Configuration
// @EnableTransactionManagement
// @PropertySource({"classpath:persistence-multiple-db-boot.properties"})
// @EnableJpaRepositories(
//         entityManagerFactoryRef = "oracleEntityManagerFactory",
//         transactionManagerRef = "oracleTransactionManager",
//         basePackages = "com.amc.repository.orass"
// )
// public class OracleDatasourceConfig {

//     @Bean(name = "oracleDataSource")
//     @ConfigurationProperties(prefix = "spring.datasource.oracle")
//     public DataSource oracleDataSource() {
//         return DataSourceBuilder.create().build();
//     }

//     @Bean(name = "oracleEntityManagerFactory")
//     public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(
//             @Qualifier("oracleDataSource") DataSource dataSource) {
//         LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//         em.setDataSource(dataSource);
//         em.setPackagesToScan("com.amc.model.orass");
//         JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//         em.setJpaVendorAdapter(vendorAdapter);
//         return em;
//     }

//     @Bean(name = "oracleTransactionManager")
//     public PlatformTransactionManager oracleTransactionManager(
//             @Qualifier("oracleEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//         return new JpaTransactionManager(entityManagerFactory);
//     }
// }
}
