package com.dudko.example.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Item {

    @NotBlank
    private UUID id;

    @Valid
    private Content content;

    @Valid
    private SecretDetails secretDetails;

}
