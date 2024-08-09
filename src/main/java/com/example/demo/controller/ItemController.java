package com.example.demo.controller;
import com.example.demo.model.Item;
import com.example.demo.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemServiceImpl itemService;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> itemList = itemService.getItems();
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    @PostMapping("/items")
    public ResponseEntity <Item> addItem(@RequestBody Item item) {
     Item itemCreated = itemService.addItem(item);
        return new ResponseEntity<Item>(itemCreated , HttpStatus.CREATED);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity <Item>deleteItem(@PathVariable("id") Long id) {
     itemService.deleteItem(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItem(@PathVariable("id") Long id) {
      Item item = itemService.getItem(id);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }
}
