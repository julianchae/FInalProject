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

class TaggedTruckTest {

	public static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private TaggedTruck taggedTruck;
	
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
		taggedTruck = em.find(TaggedTruck.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		taggedTruck = null;
	}

	@Test
	void test() {
		
//		mysql> select * from tagged_truck;
//		+----+-----------------+---------------+---------+-------------+---------------------------------+---------------------+
//		| id | photo_url       | food_truck_id | user_id | location_id | comment                         | date_tagged         |
//		+----+-----------------+---------------+---------+-------------+---------------------------------+---------------------+
//		|  1 | google.com      |             1 |       1 |           1 | Smells amazing.                 | 2000-05-01 13:35:00 |
//		|  2 | googletwo.com   |             2 |       2 |           2 | Almost out of steak.            | 2001-05-01 17:22:00 |
//		|  3 | googlethree.com |             3 |       3 |           3 | Seems like they have new items. | 2002-05-01 15:56:00 |
//		|  4 | googlefour.com  |             4 |       4 |           4 | Only here until 7pm             | 2003-05-01 11:32:00 |
//		|  5 | googlefive.com  |             5 |       5 |           5 | Looks busy.                     | 2019-05-01 08:35:00 |
//		+----+-----------------+---------------+---------+-------------+---------------------------------+---------------------+
		
		assertNotNull(taggedTruck);
		assertEquals("Smells amazing.", taggedTruck.getComment());
	}
}
