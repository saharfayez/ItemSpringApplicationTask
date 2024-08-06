package com.example.demo.controller;


import com.example.demo.model.Item;
import com.example.demo.service.ItemService;
import com.example.demo.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> deleteItem(@PathVariable("id") String id) {
        Item item = itemService.getItem(id);
        if (item != null) {
            itemService.deleteItem(id);
            return new ResponseEntity<>("Item deleted", HttpStatus.OK);
        }
      return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItem(@PathVariable("id") String id) {
      Item item = itemService.getItem(id);
        System.out.println(item);
        if (item == null) {
            return new ResponseEntity<Item>(item , HttpStatus.NOT_FOUND);
        }

        System.out.println(item);
        return ResponseEntity.ok().body(item);
    }
}
