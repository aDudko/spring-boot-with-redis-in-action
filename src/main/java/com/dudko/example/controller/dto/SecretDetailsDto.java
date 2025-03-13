package com.dudko.example.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"first"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecretDetailsDto {

    @NotBlank
    private String first;

    @NotBlank
    private String second;

}
