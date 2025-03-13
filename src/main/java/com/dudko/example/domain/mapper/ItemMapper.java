package com.dudko.example.domain.mapper;

import com.dudko.example.domain.entity.ItemEntity;
import com.dudko.example.model.Content;
import com.dudko.example.model.Item;
import com.dudko.example.model.SecretDetails;
import com.dudko.example.model.exception.ServiceAPIException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class ItemMapper {

    private final ObjectMapper objectMapper;


    public ItemEntity mapToEntity(@NonNull Item model) {
        final Content content = model.getContent();
        return ItemEntity.builder()
                .id(model.getId())
                .content(ofNullable(content).map(Content::getContent).orElse(null))
                .secretDetails(getEncryptedSecretDetails(model.getSecretDetails()))
                .build();
    }

    public Item mapToModel(@NonNull ItemEntity entity) {
        return Item.builder()
                .id(entity.getId())
                .content(Content.builder()
                        .content(entity.getContent())
                        .build())
                .secretDetails(getDecryptedSecretDetails(entity.getSecretDetails()))
                .build();
    }


    private String getEncryptedSecretDetails(@NonNull SecretDetails secretDetails) {
        try {
            return objectMapper.writeValueAsString(secretDetails);
        } catch (JsonProcessingException e) {
            throw new ServiceAPIException("Secret Details object cannot be encrypted to string", e);
        }
    }

    private SecretDetails getDecryptedSecretDetails(@NonNull String secretDetails) {
        try {
            return objectMapper.readValue(secretDetails, SecretDetails.class);
        } catch (IOException e) {
            throw new ServiceAPIException("Secret Details string cannot be decrypted to JSON", e);
        }
    }

}
