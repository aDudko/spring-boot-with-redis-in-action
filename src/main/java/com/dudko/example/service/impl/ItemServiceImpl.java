package com.dudko.example.service.impl;

import com.dudko.example.domain.entity.Item;
import com.dudko.example.domain.repository.ItemRepository;
import com.dudko.example.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;


    @Override
    @CacheEvict(value = "items", key = "#item.id")
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    @CacheEvict(value = "items", key = "#id")
    public Item getById(String id) {
        log.debug("Fetching Data from DB...");
        return repository.getReferenceById(UUID.fromString(id));
    }

}
