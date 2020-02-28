package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartControllerTest {

    private CartController cartController;

    private CartRepository cartRepo = mock(CartRepository.class);
    private UserRepository userRepo = mock(UserRepository.class);
    private ItemRepository itemRepo = mock(ItemRepository.class);

    @Before
    public void setUp() {
        cartController = new CartController();
        TestUtils.injectObject(cartController, "cartRepository",  cartRepo);
        TestUtils.injectObject(cartController, "userRepository",  userRepo);
        TestUtils.injectObject(cartController, "itemRepository",  itemRepo);
    }

    @Test
    public void add_to_cart_happy_path() throws Exception {
        User testUser = new User();
        testUser.setId(1);
        testUser.setUsername("username");
        testUser.setPassword("password");
        testUser.setCart(new Cart());
        Item testItem = new Item();
        testItem.setId(1L);
        testItem.setName("Test item");
        testItem.setDescription("Item just for unit test.");
        testItem.setPrice(BigDecimal.valueOf(10.0));

        when(userRepo.findByUsername(testUser.getUsername())).thenReturn(testUser);
        when(itemRepo.findById(testItem.getId())).thenReturn(Optional.of(testItem));

        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername(testUser.getUsername());
        request.setItemId(testItem.getId());
        request.setQuantity(2);
        final ResponseEntity<Cart> response = cartController.addTocart(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        Cart cart = response.getBody();
        assertNotNull(cart);

        assertEquals(2,cart.getItems().size());
        assertSame(1L, cart.getItems().get(0).getId());
        assertSame(1L, cart.getItems().get(1).getId());
        assertEquals(BigDecimal.valueOf(20.0),cart.getTotal());
    }

    @Test
    public void remove_from_cart_happy_path() throws Exception {
        User testUser = new User();
        testUser.setId(1);
        testUser.setUsername("username");
        testUser.setPassword("password");
        testUser.setCart(new Cart());
        Item testItem = new Item();
        testItem.setId(1L);
        testItem.setName("Test item");
        testItem.setDescription("Item just for unit test.");
        testItem.setPrice(BigDecimal.valueOf(10.0));

        when(userRepo.findByUsername(testUser.getUsername())).thenReturn(testUser);
        when(itemRepo.findById(testItem.getId())).thenReturn(Optional.of(testItem));

        ModifyCartRequest request = new ModifyCartRequest();
        request.setUsername(testUser.getUsername());
        request.setItemId(testItem.getId());
        request.setQuantity(2);
        cartController.addTocart(request);

        request.setQuantity(1);
        final ResponseEntity<Cart> response = cartController.removeFromcart(request);
        Cart cart = response.getBody();
        assertNotNull(cart);

        assertEquals(1,cart.getItems().size());
        assertSame(1L, cart.getItems().get(0).getId());
        assertEquals(BigDecimal.valueOf(10.0),cart.getTotal());
    }
}
