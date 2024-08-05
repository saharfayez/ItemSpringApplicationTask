package com.example.demo.controller;


import com.example.demo.model.Item;
import com.example.demo.service.ItemService;
import com.example.demo.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemServiceImpl itemService;

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemService.getItems();
    }

    @PostMapping("/items")
    public void addItem(@RequestBody Item item) {
        itemService.addItem(item);
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable("id") String id) {
        itemService.deleteItem(id);
    }

    @GetMapping("/items/{id}")
    public Item getItem(@PathVariable("id") String id) {

        return itemService.getItem(id).get();
    }
}
