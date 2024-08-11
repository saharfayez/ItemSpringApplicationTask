package com.example.demo;
import com.example.demo.model.Item;
import com.example.demo.repositories.ItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

//    @Autowired
//    private ItemRepository itemRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getItemsTest() throws Exception {

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("item1"))
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void getItemTest() throws Exception {

        mockMvc.perform(get("/items/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("item2"));
    }

    @Test
    public void addItemTest() throws Exception {
        Item item = new Item(3l, "item3", "003");
        mockMvc.perform(post("/items")
                        .content(asJsonString(item))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    public void deleteItemTest() throws Exception {
        mockMvc.perform(delete("/items/3"))
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