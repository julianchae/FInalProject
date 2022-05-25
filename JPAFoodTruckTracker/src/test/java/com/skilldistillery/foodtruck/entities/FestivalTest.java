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

class FestivalTest {

	public static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Festival festival;
	
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
		festival = em.find(Festival.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		festival = null;
	}

	@Test
	void test() {
		
//		mysql> select * from festival;
//		+----+---------+-----------------------------------------+-------------+---------------+------------+------------------------------------+------------+---------------------+
//		| id | user_id | name                                    | location_id | festival_date | start_time | description                        | img_url    | created_date        |
//		+----+---------+-----------------------------------------+-------------+---------------+------------+------------------------------------+------------+---------------------+
//		|  1 |       1 | Beers, Brats, Battlestar Galactica Fest |           1 | 2022-07-01    | 13:35:00   | Beers, Beets, Food                 | google.com | 2022-01-01 13:35:00 |
//		|  2 |       2 | Food Truck Mania                        |           2 | 2022-02-01    | 13:35:00   | So many trucks!                    | Two.com    | 2022-02-01 13:35:00 |
//		|  3 |       3 | Truck a palooza                         |           3 | 2023-03-01    | 13:35:00   | Festival of food                   | Three.com  | 2022-03-01 13:35:00 |
//		|  4 |       4 | Pizza and Burgers Fest                  |           4 | 2024-04-01    | 13:35:00   | Pizza and Burger Trucks            | Four.com   | 2022-04-01 13:35:00 |
//		|  5 |       5 | Sandal Extravaganza                     |           5 | 2025-05-01    | 13:35:00   | Walk around in sandals eating food | Five.com   | 2022-05-01 13:35:00 |
//		+----+---------+-----------------------------------------+-------------+---------------+------------+------------------------------------+------------+---------------------+
		
		assertNotNull(festival);
		assertEquals("Beers, Brats, Battlestar Galactica Fest", festival.getName());
	}

}
