package com.example.demo.service;
import com.example.demo.model.Item;
import java.util.List;

public interface ItemService {

    public abstract Item addItem(Item item);
    public abstract void deleteItem(Long id);
    public abstract List<Item> getItems();
    public abstract Item getItem(Long id);

    public abstract Item getItemByName(String name);
}
