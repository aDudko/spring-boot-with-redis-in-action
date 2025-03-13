package com.dudko.example.model;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"first", "second"})
public class SecretDetails {

    @NotBlank
    private String first;

    private String second;

}
