package com.dudko.example.service;

import com.dudko.example.domain.entity.Item;

public interface ItemService {

    Item save(Item item);

    Item getById(String id);

}
