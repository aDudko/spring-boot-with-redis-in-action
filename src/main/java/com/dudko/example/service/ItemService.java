package com.dudko.example.service;

import com.dudko.example.model.Item;

public interface ItemService {

    Item save(Item item);

    Item getById(String id);

}
