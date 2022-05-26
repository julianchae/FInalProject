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
import org.junit.jupiter.api.Test;


class UserTest {

	public static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private User user;
	
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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test() {
		assertNotNull(user);
		assertEquals("Jurisa", user.getFirstName());
	}

	@Test
	void test_User_to_Comment_list_mapping() {
		assertNotNull(user);
		assertNotNull(user.getComments());
		assertTrue(user.getComments().size() > 0);
	}
	
	@Test
	void test_User_to_Festival_list_mapping() {
		assertNotNull(user);
		assertNotNull(user.getFestivals());
		assertTrue(user.getFestivals().size() > 0);
	}
	
	@Test 
	void test_User_to_FoodTruck_list_mapping() {
		assertNotNull(user);
		assertNotNull(user.getFavFoodTrucks());
		assertTrue(user.getFoodTrucks().size() > 0);
	}
	
	@Test
	void test_User_Location_mapping() {
		assertNotNull(user);
		assertEquals("Grand Junction", user.getLocation().getCity());
	}
	
	@Test
	void test_User_to_Order_list_mapping() {
		assertNotNull(user);
		assertNotNull(user.getOrders());
		assertTrue(user.getOrders().size() > 0);
	}
	
	@Test
	void test_User_to_Requests_list_mapping() {
		assertNotNull(user);
		assertNotNull(user.getRequests());
		assertTrue(user.getRequests().size() > 0);
	}
	
	@Test 
	void test_User_to_TaggedTruck_list() {
		assertNotNull(user);
		assertNotNull(user.getTaggedTrucks());
		assertTrue(user.getTaggedTrucks().size() > 0);
	}
	
	@Test
	void test_User_to_Favorites_list_mapping() {
		assertNotNull(user);
		assertNotNull(user.getFavFoodTrucks());
		assertTrue(user.getFavFoodTrucks().size() > 0);
	}
}
