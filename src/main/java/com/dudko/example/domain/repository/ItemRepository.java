package com.dudko.example.domain.repository;

import com.dudko.example.domain.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, UUID> {
}
