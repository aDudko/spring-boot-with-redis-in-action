package com.dudko.example.controller;

import com.dudko.example.domain.entity.Item;
import com.dudko.example.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    @GetMapping("/{id}")
    public Item getById(@PathVariable String id) {
        return itemService.getById(id);
    }

    @PostMapping
    public Item save(@RequestBody Item item) {
        item.setId(UUID.randomUUID());
        return itemService.save(item);
    }

}
