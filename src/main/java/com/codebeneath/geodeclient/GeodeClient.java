package com.codebeneath.geodeclient;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeodeClient implements CommandLineRunner {

    @Value("${GEODE_HOST:localhost}")
    private String geodeHost;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(GeodeClient.class, args);
    }

    public void run(String... args) throws Exception {      
        System.out.print("Connecting to Geode at host: [" + geodeHost + "]");
        
        try (ClientCache cache = new ClientCacheFactory()
                .addPoolLocator(geodeHost, 10334)
                .create()) {
            Region<String, String> region = cache
                    .<String, String>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
                    .create("hello-world-region");
            
            region.put("1", "Hello");
            region.put("2", "World");
            
            region.entrySet().forEach((entry) -> {
                System.out.format("key = %s, value = %s\n", entry.getKey(), entry.getValue());
            });
        }
    }

}
