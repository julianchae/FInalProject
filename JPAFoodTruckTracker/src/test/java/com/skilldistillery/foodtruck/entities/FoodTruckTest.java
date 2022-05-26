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

class FoodTruckTest {

	public static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private FoodTruck foodTruck;
	
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
		foodTruck = em.find(FoodTruck.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		foodTruck = null;
	}

	@Test
	void test() {
		
//		mysql> select * from food_truck;
//		+----+-----------------------------+-----------------------------------------------------------+------------+---------+--------+---------------------+-----------------+
//		| id | name                        | description                                               | img_url    | user_id | active | date_created        | website_url     |
//		+----+-----------------------------+-----------------------------------------------------------+------------+---------+--------+---------------------+-----------------+
//		|  1 | Tacos R Us                  | Fresh traditional tacos                                   | google.com |       1 |      1 | 2021-05-01 13:35:00 | google.com      |
//		|  2 | MIchael Scott Tapas Company | Tapas. What else do you need to know?                     | two.com    |       2 |      1 | 2020-02-01 13:35:00 | googletwo.com   |
//		|  3 | Pizza Pizza                 | We serve wood fired oven pizza                            | three.com  |       3 |      1 | 2020-03-01 13:35:00 | googlethree.com |
//		|  4 | Licky Chicky                | We specialize in chicken strips with a variety of sauces. | four.com   |       4 |      1 | 2020-04-01 13:35:00 | googlefour.com  |
//		|  5 | Bobs Burgers                | Homestyle burgers.                                        | five.com   |       5 |      1 | 2020-05-01 13:35:00 | googlefive.com  |
//		+----+-----------------------------+-----------------------------------------------------------+------------+---------+--------+---------------------+-----------------+

		assertNotNull(foodTruck);
		assertEquals("Tacos R Us", foodTruck.getName());
	}
	@Test
	void test_FoodTruck_to_Commments_OneToMany_mapping() {
		
		assertNotNull(foodTruck);
		assertNotNull(foodTruck.getComments());
		assertTrue( foodTruck.getComments().size() > 0);
	}
	@Test
	void test_FoodTruck_to_Festival_ManyToMany_mapping() {
		
		
		assertNotNull(foodTruck);
		assertNotNull(foodTruck.getFestivals());
		assertTrue( foodTruck.getFestivals().size() > 0);
	}
	@Test
	void test_FoodTruck_to_FoodCat_ManyToMany_mapping() {
		
		
		assertNotNull(foodTruck);
		assertNotNull(foodTruck.getFoodCategories());
		assertTrue( foodTruck.getFoodCategories().size() > 0);
	}
	@Test
	void test_FoodTruck_to_Schedule_OneToMany_mapping() {
		
		
		assertNotNull(foodTruck);
		assertNotNull(foodTruck.getSchedules());
		assertTrue( foodTruck.getSchedules().size() > 0);
	}
	@Test
	void test_FoodTruck_to_MenuItems_OneToMany_mapping() {
		
		
		assertNotNull(foodTruck);
		assertNotNull(foodTruck.getMenuItems());
		assertTrue( foodTruck.getMenuItems().size() > 0);
	}
	@Test
	void test_FoodTruck_to_Request_OneToMany_mapping() {
		
		
		assertNotNull(foodTruck);
		assertNotNull(foodTruck.getRequests());
		assertTrue( foodTruck.getRequests().size() > 0);
	}
	@Test
	void test_FoodTruck_to_TaggedTruck_OneToMany_mapping() {
		
		
		assertNotNull(foodTruck);
		assertNotNull(foodTruck.getTaggedTrucks());
		assertTrue( foodTruck.getTaggedTrucks().size() > 0);
	}
	
	@Test
	@DisplayName("Foodtruck to favorites")
	void test2() {
		
		assertNotNull(foodTruck);
		assertNotNull(foodTruck.getUsersWhoFavorited());
		assertTrue( foodTruck.getUsersWhoFavorited().size() > 0);
	}
	
	@Test
	void test_FoodTruck_to_User_ManyToMany_mapping() {
		
		
		assertNotNull(foodTruck);
		assertNotNull(foodTruck.getUser());
		assertEquals("Yockenfelder", foodTruck.getUser().getLastName());
	}

}
