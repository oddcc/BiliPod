package ren.chenhq.bilipod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import ren.chenhq.bilipod.config.ConfigManager;

import java.io.IOException;

@SpringBootApplication
public class BiliPodApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiliPodApplication.class, args);
    }

    @Bean
    public ConfigManager configManager(Environment environment) throws IOException {
        return ConfigManager.init(environment);
    }

}
