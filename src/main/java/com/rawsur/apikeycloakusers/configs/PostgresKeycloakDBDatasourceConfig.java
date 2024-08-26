// package com.rawsur.apikeycloakusers.configs;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
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
// @EnableJpaRepositories(entityManagerFactoryRef = "keycloakEntityManagerFactory", transactionManagerRef = "keycloakTransactionManager", basePackages = {
//         "com.rawsur.apikeycloakusers.repository.keycloak" })
// public class PostgresKeycloakDBDatasourceConfig {

//     @Bean(name = "dataSource")
//     @ConfigurationProperties(prefix = "spring.datasource.postgresql-keycloak")
//     public DataSource dataSource() {
//         return DataSourceBuilder.create().build();
//     }


//     @Bean(name = "keycloakEntityManagerFactory")
//     public LocalContainerEntityManagerFactoryBean
//     entityManagerFactory(
//             EntityManagerFactoryBuilder builder,
//             @Qualifier("dataSource") DataSource dataSource
//     ) {
//         return builder
//                 .dataSource(dataSource)
//                 .packages("com.rawsur.apikeycloakusers.model.keyclaok")
//                 .persistenceUnit("keycloak")
//                 .build();
//     }


//     @Bean(name = "keycloakTransactionManager")
//     public PlatformTransactionManager transactionManager(
//             @Qualifier("keycloakEntityManagerFactory") EntityManagerFactory
//                     entityManagerFactory
//     ) {
//         return new JpaTransactionManager(entityManagerFactory);
//     }


// }
