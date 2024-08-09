package com.example.demo;

import com.example.demo.model.Item;
import com.example.demo.service.impl.ItemServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @MockBean
    private ItemServiceImpl itemService;
    @Autowired
    private MockMvc mockMvc;

    List<Item> listOfItems = Arrays.asList();

    @BeforeEach
    void setUp() {
        listOfItems = Arrays.asList(
                new Item(1l, "item1", "001"),
                new Item(2l, "item2", "002"),
                new Item(3l, "item3", "003"),
                new Item(4l, "item4", "004"),
                new Item(5l, "item5", "005")
        );
    }

    @Test
    public void getItemsTest() throws Exception {

        when(itemService.getItems()).thenReturn(listOfItems);
        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("item1"))
                .andExpect(jsonPath("$.size()").value(5));
    }

    @Test
    public void getItemTest() throws Exception {
        when(itemService.getItem(3l)).thenReturn(listOfItems.get(2));
        mockMvc.perform(get("/items/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("item3"));
    }

    @Test
    public void addItemTest() throws Exception {
        Item item = new Item(7l, "item7", "007");
        mockMvc.perform(post("/items")
                        .content(asJsonString(item))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    public void deleteItemTest() throws Exception {
        mockMvc.perform(delete("/items/1"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
