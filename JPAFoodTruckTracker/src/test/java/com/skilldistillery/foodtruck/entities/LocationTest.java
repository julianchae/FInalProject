package com.skilldistillery.foodtruck.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LocationTest {

	public static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Location location;
	
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
		location = em.find(Location.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		location = null;
	}

	@Test
	@DisplayName("testing basic Location mapping")
	void test1() {
		
//		mysql> select * from location;
//		+----+-------------------------+-----------------+-------+-------+
//		| id | street                  | city            | state | zip   |
//		+----+-------------------------+-----------------+-------+-------+
//		|  1 |  10175 Easter Street    | Grand Junction  | CO    | 81505 |
//		|  2 | 19968 Cucumber Ave      | Aurora          | CO    | 80019 |
//		|  3 | 1550 Larson Road        | Lone Tree       | CO    | 80124 |
//		|  4 | 1611 Main Circle        | Highlands Ranch | CO    | 80129 |
//		|  5 | 8200 Park Meadows Drive | Lone Tree       | CO    | 80124 |
//		+----+-------------------------+-----------------+-------+-------+

		assertNotNull(location);
		assertEquals(" 10175 Easter Street", location.getStreet());
	}
	
	@Test
	@DisplayName("testing Location to Festival mapping")
	void test3() {
		
		assertNotNull(location);
		assertNotNull(location.getFestivals());
		assertTrue(location.getFestivals().size()>0);
	}
	
	@Test
	@DisplayName("testing Location to Request mapping")
	void test4() {
	
		assertNotNull(location);
		assertNotNull(location.getRequests());
		assertTrue(location.getRequests().size()>0);
	}
	
	@Test
	@DisplayName("testing Location to Schedule mapping")
	void test5() {
	
		assertNotNull(location);
		assertNotNull(location.getSchedules());
		assertTrue(location.getSchedules().size()>0);	
	}
	
	@Test
	@DisplayName("testing Location to TaggedTruck mapping")
	void test6() {
	
		assertNotNull(location);
		assertNotNull(location.getTaggedTrucks());
		assertTrue(location.getTaggedTrucks().size()>0);
	}

}
