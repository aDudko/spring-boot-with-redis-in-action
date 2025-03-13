package com.dudko.example.config;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Data
@Component
@Validated
@ConfigurationProperties("redis.keys")
public class RedisKeysProperties {

    @NotNull
    private KeyParameters item;


    @Data
    @Validated
    public static class KeyParameters {
        @NotNull
        private Duration timeToLive;
    }

}
