package com.example.demo.service;

import com.example.demo.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    List<Item> listOfItems = new ArrayList<>(
            Arrays.asList(
                    new Item("1", "item1", "001"),
                    new Item("2", "item2", "002"),
                    new Item("3", "item3", "003"),
                    new Item("4", "item4", "004")
            )
    );

    @Override
    public void addItem(Item item) {
        listOfItems.add(item);
    }

    @Override
    public void deleteItem(String id) {
        listOfItems.remove(id);
    }

    @Override
    public List<Item> getItems() {
        return listOfItems;
    }

    @Override
    public Optional<Item> getItem(String id) {
        return listOfItems.stream().filter(item -> item.getId().equals(id)).findFirst();
    }
}
