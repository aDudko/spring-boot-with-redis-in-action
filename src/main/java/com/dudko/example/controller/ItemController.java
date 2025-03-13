package com.dudko.example.controller;

import com.dudko.example.controller.converter.ItemConverter;
import com.dudko.example.controller.dto.ItemRequestDto;
import com.dudko.example.controller.dto.ItemResponseDto;
import com.dudko.example.model.Item;
import com.dudko.example.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/items", produces = "application/vnd.api.v1+json")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemConverter converter;


    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(converter.convertToDto(itemService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ItemResponseDto> create(@RequestBody ItemRequestDto itemRequest) {
        Item persisted = itemService.save(converter.convertToModel(itemRequest));
        ItemResponseDto converted = converter.convertToDto(persisted);
        return new ResponseEntity<>(converted, HttpStatus.CREATED);
    }

}
