// package com.rawsur.apikeycloakusers.configs;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
// import org.springframework.context.annotation.PropertySource;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.transaction.PlatformTransactionManager;
// import org.springframework.transaction.annotation.EnableTransactionManagement;

// import javax.persistence.EntityManagerFactory;
// import javax.sql.DataSource;

// @Configuration
// @EnableTransactionManagement
// @PropertySource({"classpath:persistence-multiple-db-boot.properties"})
// @EnableJpaRepositories(entityManagerFactoryRef = "mobileEntityManagerFactory", transactionManagerRef = "mobileTransactionManager", basePackages = {
//         "com.rawsur.apikeycloakusers.repository.myrawsur" })
// public class PostgresMobileDBUserDatasourceConfig {

//     @Primary
//     @Bean(name = "mobileDataSource")
//     @ConfigurationProperties(prefix = "spring.datasource.postgresql-mobile")
//     public DataSource dataSource() {
//         return DataSourceBuilder.create().build();
//     }
//     @Primary
//     @Bean(name = "mobileEntityManagerFactory")
//     public LocalContainerEntityManagerFactoryBean
//     mobileEntityManagerFactory(
//             EntityManagerFactoryBuilder builder,
//             @Qualifier("mobileDataSource") DataSource dataSource
//     ) {
//         return
//                 builder
//                         .dataSource(dataSource)
//                         .packages("com.rawsur.apikeycloakusers.model.myrawsur")
//                         .persistenceUnit("webapi")
//                         .build();
//     }
//     @Primary
//     @Bean(name = "mobileTransactionManager")
//     public PlatformTransactionManager mobileTransactionManager(
//             @Qualifier("mobileEntityManagerFactory") EntityManagerFactory
//                     mobileEntityManagerFactory
//     ) {
//         return new JpaTransactionManager(mobileEntityManagerFactory);
//     }

// }
