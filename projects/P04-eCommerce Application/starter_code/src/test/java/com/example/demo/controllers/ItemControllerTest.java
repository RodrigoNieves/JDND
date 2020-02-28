package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

public class ItemControllerTest {
    private ItemController itemController;
    private  ItemRepository itemRepo = mock(ItemRepository.class);
    private Item item1;
    private Item item2;

    @Before
    public void setUp() {
        itemController = new ItemController();
        TestUtils.injectObject(itemController, "itemRepository",  itemRepo);
        item1 = new Item();
        item1.setId(1L);
        item1.setName("Item 1");
        item1.setDescription("Item for testing with number 1.");
        item1.setPrice(BigDecimal.valueOf(10.0));
        item2 = new Item();
        item2.setId(2L);
        item2.setName("Item 2");
        item2.setDescription("Item for testing with number 2.");
        item2.setPrice(BigDecimal.valueOf(20.0));
    }

    @Test
    public void get_items_happy_paht() throws Exception {
        when(itemRepo.findAll()).thenReturn(Arrays.asList(item1,item2));

        ResponseEntity<List<Item>> response = itemController.getItems();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Arrays.asList(item1,item2), response.getBody());
    }

    @Test
    public void get_item_by_id() throws Exception {
        when(itemRepo.findById(item1.getId())).thenReturn(Optional.of(item1));

        ResponseEntity<Item> response = itemController.getItemById(item1.getId());

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(item1, response.getBody());
    }

    @Test
    public void get_items_by_name() throws Exception {
        when(itemRepo.findByName(item1.getName())).thenReturn(Arrays.asList(item1));

        ResponseEntity<List<Item>> response = itemController.getItemsByName(item1.getName());

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        List<Item> items = response.getBody();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals(item1, items.get(0));
    }
}
