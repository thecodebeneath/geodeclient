package com.codebeneath.geodeclient;

import com.codebeneath.geodeclient.model.ApplicationMessage;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;
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
                .setPdxSerializer(new ReflectionBasedAutoSerializer("com.codebeneath.geodeclient.model.*"))
                .create()) {

            Region<String, ApplicationMessage> region = cache
                    .<String, ApplicationMessage>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
                    .create("hello-world-region");

            region.put("1", new ApplicationMessage("Hello"));
            region.put("2", new ApplicationMessage("World"));

            region.entrySet().forEach((entry) -> {
                System.out.format("key = %s, value = %s\n", entry.getKey(), entry.getValue());
            });
        }
    }

}
