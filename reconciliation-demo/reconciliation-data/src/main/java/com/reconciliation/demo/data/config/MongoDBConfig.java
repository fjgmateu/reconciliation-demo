package com.reconciliation.demo.data.config;

import java.io.IOException;
//import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import com.mongodb.Mongo;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.*;
import com.mongodb.MongoClient;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by FJGMATEU
 */

@Configuration
/*
@EnableMongoRepositories("com.reconciliation.demo.data")
public class MongoDBConfig extends AbstractMongoConfiguration {*/
@EnableMongoRepositories("com.reconciliation.demo.data")
public class MongoDBConfig{

    @Value("${mongodb.data.host}")
    private String mongoHost;

    @Value("${mongodb.data.port}")
    private String mongoPort;

    @Value("${mongodb.data.database}")
    private String mongoDB;


    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(mongoHost);
        MongoClient mongoClient = mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, mongoDB);
       return mongoTemplate;
    }

    /*
    @Override
    public MongoMappingContext mongoMappingContext()
          throws ClassNotFoundException {
        return super.mongoMappingContext();
    }
    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(mongoHost + ":" + mongoPort);
    }
    @Override
    protected String getDatabaseName() {
        return mongoDB;
    }
    */
}