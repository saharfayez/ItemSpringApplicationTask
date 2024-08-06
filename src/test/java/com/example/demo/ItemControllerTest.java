package com.example.demo;
import com.example.demo.model.Item;
import com.example.demo.service.ItemServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @Test
    public void getItemsTest() throws Exception {
        List<Item> listOfItems = Arrays.asList(
                new Item("1", "item1", "001"),
                new Item("2", "item2", "002"),
                new Item("3", "item3", "003"),
                new Item("4", "item4", "004")
        );
        when(itemService.getItems()).thenReturn(listOfItems);

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("item1"))
                .andExpect(jsonPath("$.size()").value(4));
    }
}
