package com.dudko.example.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {

    @NotBlank
    private String id;

    @Valid
    private ContentDto contentDto;

    @Valid
    private SecretDetailsDto secretDetailsDto;

}
