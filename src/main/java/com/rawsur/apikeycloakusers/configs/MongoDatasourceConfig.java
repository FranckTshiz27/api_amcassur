// package com.rawsur.apikeycloakusers.configs;

// import com.mongodb.MongoClientSettings;
// import com.mongodb.MongoCredential;
// import com.mongodb.ServerAddress;
// import com.mongodb.client.MongoClient;
// import com.mongodb.client.MongoClients;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.autoconfigure.mongo.MongoProperties;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.context.properties.EnableConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
// import org.springframework.data.mongodb.MongoDatabaseFactory;
// import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
// import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

// import static java.util.Collections.singletonList;

// @Configuration
// @EnableMongoRepositories(basePackageClasses  = com.rawsur.apikeycloakusers.repository.mongo.COtpRepository.class, mongoTemplateRef = "primaryMongoTemplate")
// @EnableConfigurationProperties
// public class MongoDatasourceConfig {

//     @Bean(name = "primaryProperties")
//     @ConfigurationProperties(prefix = "mongodb.primary")
//     @Primary
//     public MongoProperties primaryProperties() {
//         return new MongoProperties();
//     }

//     /*

//     Pour accorder l'accès à plusieurs utilisateurs, nous utilisons un mécanisme d'authentification MongoDB  avec MongoCredential .
//     Nous construisons notre objet d'identification en ajoutant une base de données d'authentification, admin

//      */
//     @Bean(name = "primaryMongoClient")
//     public MongoClient mongoClient(@Qualifier("primaryProperties") MongoProperties mongoProperties) {

//         MongoCredential credential = MongoCredential
//                 .createCredential(mongoProperties.getUsername(), mongoProperties.getAuthenticationDatabase(), mongoProperties.getPassword());

//         return MongoClients.create(MongoClientSettings.builder()
//                 .applyToClusterSettings(builder -> builder
//                         .hosts(singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
//                 .credential(credential)
//                 .build());
//     }


//     @Primary
//     @Bean(name = "primaryMongoDBFactory")
//     public MongoDatabaseFactory mongoDatabaseFactory(
//             @Qualifier("primaryMongoClient") MongoClient mongoClient,
//             @Qualifier("primaryProperties") MongoProperties mongoProperties) {
//         return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
//     }


//     @Bean(name = "primaryMongoTemplate")
//     public MongoTemplate mongoTemplate(@Qualifier("primaryMongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory) {
//         return new MongoTemplate(mongoDatabaseFactory);
//     }


// }