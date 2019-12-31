package com.udacity.jdnd.course3.lesson2;

import com.udacity.jdnd.course3.lesson2.entity.Order;
import com.udacity.jdnd.course3.lesson2.entity.OrderItem;
import com.udacity.jdnd.course3.lesson2.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class Lesson2ApplicationTests {

	@Autowired private DataSource dataSource;
	@Autowired private JdbcTemplate jdbcTamplate;
	@Autowired private EntityManager entityManager;
	@Autowired private TestEntityManager testEntityManager;
	@Autowired private OrderRepository orderRepository;

	@Test
	public void contextLoads() {
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTamplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(testEntityManager).isNotNull();
		assertThat(orderRepository).isNotNull();
	}

	@Test
	public void testFindByCustomerNam() {
		Order order = new Order();
		order.setCustomerName("Testing1");
		order.setCustomerAddress("My Address 1");
		order.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
		// create order item1
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setItemName("Item1");
		orderItem1.setItemCount(2);
		orderItem1.setOrder(order);

		// create order item2
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setItemName("Item2");
		orderItem2.setItemCount(1);
		orderItem2.setOrder(order);

		entityManager.persist(order);

		Optional<Order> actual = orderRepository.findByCustomerName("Testing1");
		assertThat(actual).isNotEmpty();
		assertEquals(order.getOrderId(), actual.get().getOrderId());
	}
}
