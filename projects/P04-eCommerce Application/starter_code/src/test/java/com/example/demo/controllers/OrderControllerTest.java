package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.apache.tomcat.util.security.Escape;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorCompletionService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerTest {
    private OrderController orderController;

    private UserRepository userRepo = mock(UserRepository.class);
    private OrderRepository orderRepo = mock(OrderRepository.class);

    @Before
    public void setUp() {
        orderController = new OrderController();
        TestUtils.injectObject(orderController, "userRepository",  userRepo);
        TestUtils.injectObject(orderController, "orderRepository",  orderRepo);
    }

    @Test
    public void submit_happy_path() throws Exception {
        User testUser = new User();
        testUser.setId(1);
        testUser.setUsername("testUsername");
        testUser.setPassword("testPassword");
        testUser.setCart(new Cart());
        Item testItem = new Item();
        testItem.setId(10L);
        testItem.setPrice(BigDecimal.valueOf(10));
        testItem.setName("test item");
        testItem.setDescription("item only for unit test purpose.");
        testUser.getCart().addItem(testItem);
        testUser.getCart().setUser(testUser);
        when(userRepo.findByUsername(testUser.getUsername())).thenReturn(testUser);

        ResponseEntity<UserOrder> response = orderController.submit(testUser.getUsername());

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        UserOrder order = response.getBody();
        assertNotNull(order);

        assertSame(testUser, order.getUser());
        assertEquals(testItem.getPrice(), order.getTotal());
        assertEquals(1, order.getItems().size());
        assertSame(testItem, order.getItems().get(0));
    }

    @Test
    public void history_happy_path() throws Exception {
        User testUser = new User();
        testUser.setId(1);
        testUser.setUsername("testUsername");
        testUser.setPassword("testPassword");
        testUser.setCart(new Cart());
        Item testItem = new Item();
        testItem.setId(10L);
        testItem.setPrice(BigDecimal.valueOf(10));
        testItem.setName("test item");
        testItem.setDescription("item only for unit test purpose.");
        testUser.getCart().addItem(testItem);
        testUser.getCart().setUser(testUser);
        when(userRepo.findByUsername(testUser.getUsername())).thenReturn(testUser);

        ResponseEntity<UserOrder> responseSubmit = orderController.submit(testUser.getUsername());

        assertNotNull(responseSubmit);
        assertEquals(200, responseSubmit.getStatusCodeValue());
        UserOrder order = responseSubmit.getBody();
        assertNotNull(order);
        when(orderRepo.findByUser(testUser)).thenReturn(Arrays.asList(order));

        ResponseEntity<List<UserOrder>> response = orderController.getOrdersForUser(testUser.getUsername());
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Arrays.asList(order), response.getBody());
    }

}
