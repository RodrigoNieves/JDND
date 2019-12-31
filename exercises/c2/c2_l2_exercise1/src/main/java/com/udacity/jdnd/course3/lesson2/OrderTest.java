package com.udacity.jdnd.course3.lesson2;

import com.udacity.jdnd.course3.lesson2.entity.Order;
import com.udacity.jdnd.course3.lesson2.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class OrderTest {

    private static final String PERSISTENCE_UNIT_NAME = "Order";

    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        // STEP 1: Create a factory for the persistence unit
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        // STEP 2: Create an EntityManager
        EntityManager em = factory.createEntityManager();

        // STEP 3: Start a transaction
        em.getTransaction().begin();

        // STEP 4: Create an order (entity is in Transient state)
        Order order = new Order();
        order.setCustomerName("Rodrigo");
        order.setCustomerAddress("Calle 1");
        order.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));


        // create order item1
        OrderItem item1 = new OrderItem();
        item1.setItemName("Item 1");
        item1.setItemCount(1);
        item1.setOrder(order);

        // create order item2
        OrderItem item2 = new OrderItem();
        item2.setItemName("Item 2");
        item2.setItemCount(1);
        item2.setOrder(order);

        order.setOrderItems(Arrays.asList(item1, item2));

        // STEP 5: Persist the order entity
        em.persist(order);

        // NOTE: Order Item is NOT persisted here

        // entity is persistent now
        em.getTransaction().commit();
        em.close();

        readOrder(order.getOrderId(), factory);

        factory.close();
    }

    private static void readOrder(Integer orderId, EntityManagerFactory factory) {
        // STEP 1: Create an EntityManager
        EntityManager em = factory.createEntityManager();

        // STEP 2: use the find() method to load an order
        // OrderItem is fetched eagerly by using a JOIN
        Order order = em.find(Order.class, orderId);

        System.err.println("Order: " + order);
        for(OrderItem oi : order.getOrderItems()) {
            System.err.println("Item: " + oi);
        }

        // Using JPQL
        Query query = em.createQuery("SELECT o from Order o");
        List<Order> orders = query.getResultList();
        for(int i =  0 ; i < orders.size(); i++) {
            System.err.println("Order " + orders.get(i).getOrderId());
            System.err.println(orders.get(i));

        }
    }

   private static void deleteOrder(Integer orderId, EntityManagerFactory factory) {
       // STEP 1: Create an EntityManager
       EntityManager em = factory.createEntityManager();

       // STEP 2: use the find() method to load an order
       Order order = new Order();
       order.setOrderId(orderId);
       em.remove(order);

       System.err.println("Order: " + order);

       em.close();
   }
}