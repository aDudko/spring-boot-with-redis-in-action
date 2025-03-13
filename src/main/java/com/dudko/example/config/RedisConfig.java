package com.dudko.example.config;

import java.util.Collections;

import com.dudko.example.domain.entity.ItemEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.convert.KeyspaceConfiguration;
import org.springframework.data.redis.core.convert.MappingConfiguration;
import org.springframework.data.redis.core.index.IndexConfiguration;
import org.springframework.data.redis.core.mapping.RedisMappingContext;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableCaching
@Configuration
@RequiredArgsConstructor
@EnableRedisRepositories(enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
public class RedisConfig {

    private final RedisKeysProperties properties;


    @Bean
    public RedisMappingContext keyValueMappingContext() {
        return new RedisMappingContext(
                new MappingConfiguration(new IndexConfiguration(), new CustomKeyspaceConfiguration()));
    }

    public class CustomKeyspaceConfiguration extends KeyspaceConfiguration {

        @Override
        protected Iterable<KeyspaceSettings> initialConfiguration() {
            return Collections.singleton(customKeyspaceSettings(ItemEntity.class, CacheName.ITEM));
        }

        private <T> KeyspaceSettings customKeyspaceSettings(Class<T> type, String keyspace) {
            final KeyspaceSettings keyspaceSettings = new KeyspaceSettings(type, keyspace);
            keyspaceSettings.setTimeToLive(properties.getItem().getTimeToLive().toSeconds());
            return keyspaceSettings;
        }

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CacheName {
        public static final String ITEM = "item";
    }

}
