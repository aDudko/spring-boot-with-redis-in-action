package com.dudko.example.service.impl;

import com.dudko.example.domain.entity.ItemEntity;
import com.dudko.example.domain.mapper.ItemMapper;
import com.dudko.example.domain.repository.ItemRepository;
import com.dudko.example.model.Item;
import com.dudko.example.model.exception.ResourceNotFoundException;
import com.dudko.example.model.exception.ServiceAPIException;
import com.dudko.example.service.ItemService;
import lombok.NonNull;
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
    private final ItemMapper mapper;


    @Override
    @CacheEvict(value = "items", key = "#item.id")
    public Item save(@NonNull Item item) {
        if (repository.existsById(item.getId())) {
            log.info("Item with id: {} already exists", item.getId());
            return mapper.mapToModel(repository.findById(item.getId())
                    .orElseThrow(() -> new ServiceAPIException(
                            String.format("Unexpected error for id: %s", item.getId())
                    )));
        }
        ItemEntity savedEntity = repository.save(mapper.mapToEntity(item));
        log.info("Item was saved successfully with id: {}", savedEntity.getId());
        return mapper.mapToModel(savedEntity);
    }

    @Override
    @CacheEvict(value = "items", key = "#id")
    public Item getById(@NonNull String id) {
        log.debug("Fetching Data from DB...");
        return repository.findById(UUID.fromString(id))
                .map(mapper::mapToModel)
                .orElseThrow(() -> {
                    log.warn("Item not found for id {}", id);
                    return new ResourceNotFoundException(String.format("Item not found for id: %s", id));
                });
    }

}
