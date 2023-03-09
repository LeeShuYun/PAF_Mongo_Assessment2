package ibf2022.batch2.paf.server.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import static ibf2022.batch2.paf.server.Constants.*;

@Configuration
public class MongoConfig {
    
    @Value("${mongo.url}")
    private String mongoUrl;
    
    @Bean
    public MongoTemplate createMongoTemplate() {
        MongoClient client = MongoClients.create(mongoUrl);
        return new MongoTemplate(client, DB_RESTDB);
    }
    
}
