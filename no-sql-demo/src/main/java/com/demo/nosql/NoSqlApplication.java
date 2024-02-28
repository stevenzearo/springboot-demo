package com.demo.nosql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableElasticsearchRepositories("com.demo.nosql.elasticsearch.repository")
@EnableKafka
@SpringBootApplication
public class NoSqlApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(NoSqlApplication.class, args);
    }

    @GetMapping("/")
    public String runningCheck() {
        return "NoSqlApplication starting";
    }

}
