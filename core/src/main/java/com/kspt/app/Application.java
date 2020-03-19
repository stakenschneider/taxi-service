package com.kspt.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * Created by Masha on 28.02.2020
 */
@EnableResourceServer
@EnableJpaAuditing
@EntityScan(basePackageClasses = {
        Application.class,
        Jsr310JpaConverters.class
})
@SpringBootApplication
public class Application {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
