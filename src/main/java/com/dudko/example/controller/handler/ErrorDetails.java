package com.dudko.example.controller.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorDetails {

    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime timestamp;

    private String message;

    private String details;

    private String errorCode;

}
