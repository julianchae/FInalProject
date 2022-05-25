package com.skilldistillery.foodtruck.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest {

	public static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Order order;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAFoodTruckTracker");	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		order = em.find(Order.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		order = null;
	}

	@Test
	void test() {
		
//		mysql> select * from pre_order;
//		+----+---------------------+------------------------+-----------+---------------------+---------+--------------+
//		| id | ordered_date        | special_requests       | completed | pickup_date         | user_id | menu_item_id |
//		+----+---------------------+------------------------+-----------+---------------------+---------+--------------+
//		|  1 | 2022-05-01 13:35:00 | No pickles             |         1 | 2022-05-01 15:35:00 |       1 |            1 |
//		|  2 | 2022-04-29 13:00:00 | Double steak.          |         1 | 2022-05-01 14:30:00 |       2 |            2 |
//		|  3 | 2022-05-18 17:54:00 | Extra sauce please.    |         1 | 2022-05-18 19:25:00 |       3 |            3 |
//		|  4 | 2022-05-29 12:14:00 | Two forks!             |         0 | 2022-05-30 16:20:00 |       4 |            4 |
//		|  5 | 2022-05-01 14:42:00 | No bun, lettuce wrap.  |         1 | 2022-05-01 17:35:00 |       5 |            5 |
//		+----+---------------------+------------------------+-----------+---------------------+---------+--------------+

		assertNotNull(order);
		assertEquals(2022, order.getOrderedDate().getYear());
		assertEquals(5, order.getOrderedDate().getMonthValue());
	}

}
