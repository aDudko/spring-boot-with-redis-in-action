package com.dudko.example.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@Data
@Builder
@RedisHash
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

    @Id
    private UUID id;

    private String content;

    private String secretDetails;

}
