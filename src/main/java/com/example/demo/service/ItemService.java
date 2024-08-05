package com.example.demo.service;

import com.example.demo.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    public abstract void addItem(Item item);
    public abstract void deleteItem(String id);
    public abstract List<Item> getItems();
    public abstract Item getItem(String id);
}
