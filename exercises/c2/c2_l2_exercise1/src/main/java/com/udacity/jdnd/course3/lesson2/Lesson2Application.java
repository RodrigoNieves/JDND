package com.udacity.jdnd.course3.lesson2;

import com.udacity.jdnd.course3.lesson2.entity.Order;
import com.udacity.jdnd.course3.lesson2.entity.OrderItem;
import com.udacity.jdnd.course3.lesson2.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
public class Lesson2Application {

	private static final Logger log = LoggerFactory.getLogger(Lesson2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Lesson2Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(OrderRepository repository) {
		return (args) -> {
			// save a couple of customers
			Order order = new Order();
			order.setCustomerName("Demo1");
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

			order = repository.save(order);
			System.err.println("Order id " + order.getOrderId());

			Optional<Order> orderRead = repository.findByCustomerName("Demo1");
			orderRead.ifPresent(value -> System.err.println("Order" + value));

		};
	}
}
