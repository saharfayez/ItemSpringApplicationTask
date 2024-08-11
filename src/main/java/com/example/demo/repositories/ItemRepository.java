package com.example.demo.repositories;

import com.example.demo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
@Query("SELECT t from Item t where t.name=:name")
public Item findItem(String name);

}
