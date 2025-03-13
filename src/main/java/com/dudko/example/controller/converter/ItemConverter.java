package com.dudko.example.controller.converter;

import com.dudko.example.controller.dto.ContentDto;
import com.dudko.example.controller.dto.ItemRequestDto;
import com.dudko.example.controller.dto.ItemResponseDto;
import com.dudko.example.controller.dto.SecretDetailsDto;
import com.dudko.example.model.Content;
import com.dudko.example.model.Item;
import com.dudko.example.model.SecretDetails;
import com.dudko.example.model.exception.ServiceAPIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class ItemConverter {

    private final ObjectMapper mapper;


    public Item convertToModel(@NonNull ItemRequestDto dto) {
        final ContentDto contentDto = dto.getContentDto();
        return Item.builder()
                .id(UUID.fromString(dto.getId()))
                .content(Content.builder()
                        .content(ofNullable(contentDto).map(ContentDto::getContent).orElse(null))
                        .build())
                .secretDetails(getDecryptedSecretDetails(dto.getSecretDetails()))
                .build();
    }

    public ItemResponseDto convertToDto(@NonNull Item model) {
        final Content content = model.getContent();
        return ItemResponseDto.builder()
                .id(model.getId().toString())
                .contentDto(ContentDto.builder()
                        .content(ofNullable(content).map(Content::getContent).orElse(null))
                        .build())
                .secretDetailsDto(SecretDetailsDto.builder()
                        .first(model.getSecretDetails().getFirst())
                        .second(model.getSecretDetails().getSecond())
                        .build())
                .build();
    }


    private SecretDetails getDecryptedSecretDetails(@NonNull String secretDetails) {
        try {
            String[] values = secretDetails.split(":");
            if (values.length != 2) {
                throw new ServiceAPIException("Invalid secretDetails format. Expected 'first:second' or JSON");
            }
            String json = String.format("{\"first\":\"%s\",\"second\":\"%s\"}", values[0], values[1]);
            return mapper.readValue(json, SecretDetails.class);
        } catch (IOException e) {
            throw new ServiceAPIException("Secret Details string cannot be decrypted to JSON", e);
        }
    }

}
