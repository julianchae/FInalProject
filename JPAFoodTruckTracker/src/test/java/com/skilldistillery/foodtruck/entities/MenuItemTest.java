package com.skilldistillery.foodtruck.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuItemTest {

	public static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private MenuItem menuItem;
	
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
		menuItem = em.find(MenuItem.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		menuItem = null;
	}

	@Test
	void test() {
		
//		mysql> select * from menu_item;
//		+----+-----------------+---------------------+-------+------------+--------+---------------+
//		| id | name            | description         | price | img_url    | active | food_truck_id |
//		+----+-----------------+---------------------+-------+------------+--------+---------------+
//		|  1 | Taco Burger     | Tacos on a burger.  |  5.99 | google.com |      1 |             1 |
//		|  2 | Steak Tapas     | tapas tapas         |  7.99 | two.com    |      2 |             2 |
//		|  3 | Pepperoni Pizza | pizza pizza         | 11.99 | three.com  |      3 |             3 |
//		|  4 | Chicken Strips  | strips strips       |  8.50 | four.com   |      4 |             4 |
//		|  5 | Cheeseburger    | Cheese on a burger  |  5.50 | five.com   |      5 |             5 |
//		+----+-----------------+---------------------+-------+------------+--------+---------------+

		assertNotNull(menuItem);
		assertEquals("Taco Burger", menuItem.getName());
	}

	@Test
	@DisplayName("Menu to Foodtruck name")
	void test2() {
		assertNotNull(menuItem);
		assertNotNull(menuItem.getFoodTruck());
		assertEquals("Tacos R Us" ,menuItem.getFoodTruck().getName());
		
	}
	@Test
	@DisplayName("Menu Item to Orders")
	void test3() {
		assertNotNull(menuItem);
		assertNotNull(menuItem.getOrders());
		assertTrue(menuItem.getOrders().size() > 0);
		
	}


}
