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
	@DisplayName("testing basic Festival mapping")
	void test1() {
		
//		mysql> select * from festival;
//		+----+---------+-----------------------------------------+-------------+---------------+------------+------------------------------------+------------------------------------------------------------------------------------------------------+---------------------+
//		| id | user_id | name                                    | location_id | festival_date | start_time | description                        | img_url                                                                                              | created_date        |
//		+----+---------+-----------------------------------------+-------------+---------------+------------+------------------------------------+------------------------------------------------------------------------------------------------------+---------------------+
//		|  1 |       1 | Beers, Brats, Battlestar Galactica Fest |           1 | 2022-07-01    | 13:35:00   | Beers, Beets, Food                 | https://64.media.tumblr.com/3088bc582dae38b21f195e468fb8d224/tumblr_inline_ousnf8oJkW1t0aw4o_640.jpg | 2022-01-01 13:35:00 |
//		|  2 |       2 | Food Truck Mania                        |           2 | 2022-02-01    | 13:35:00   | So many trucks!                    | https://images.unsplash.com/photo-1612208176815-e132bcf971b0?                                        | 2022-02-01 13:35:00 |
//		|  3 |       3 | Truck a palooza                         |           3 | 2023-03-01    | 13:35:00   | Festival of food                   | https://images.unsplash.com/photo-1505496704829-37e28089504e?                                        | 2022-03-01 13:35:00 |
//		|  4 |       4 | Pizza and Burgers Fest                  |           4 | 2024-04-01    | 13:35:00   | Pizza and Burger Trucks            | https://images.unsplash.com/photo-1487004121828-9fa15a215a7a?                                        | 2022-04-01 13:35:00 |
//		|  5 |       5 | Sandal Extravaganza                     |           5 | 2025-05-01    | 13:35:00   | Walk around in sandals eating food | https://images.unsplash.com/photo-1625563206627-7e713d1ac0a8?                                        | 2022-05-01 13:35:00 |
//		+----+---------+-----------------------------------------+-------------+---------------+------------+------------------------------------+------------------------------------------------------------------------------------------------------+----------------------

		assertNotNull(festival);
		assertEquals("Beers, Brats, Battlestar Galactica Fest", festival.getName());
	}
	
	@Test
	@DisplayName("testing Festival to User mapping")
	void test2() {
		
//		mysql> SELECT * FROM festival WHERE festival.id = 1;
//		+----+---------+-----------------------------------------+-------------+---------------+------------+--------------------+------------------------------------------------------------------------------------------------------+---------------------+
//		| id | user_id | name                                    | location_id | festival_date | start_time | description        | img_url                                                                                              | created_date        |
//		+----+---------+-----------------------------------------+-------------+---------------+------------+--------------------+------------------------------------------------------------------------------------------------------+---------------------+
//		|  1 |       1 | Beers, Brats, Battlestar Galactica Fest |           1 | 2022-07-01    | 13:35:00   | Beers, Beets, Food | https://64.media.tumblr.com/3088bc582dae38b21f195e468fb8d224/tumblr_inline_ousnf8oJkW1t0aw4o_640.jpg | 2022-01-01 13:35:00 |
//		+----+---------+-----------------------------------------+-------------+---------------+------------+--------------------+------------------------------------------------------------------------------------------------------+---------------------+
	
		assertNotNull(festival);
		assertNotNull(festival.getUser());
		assertEquals(1, festival.getUser().getId());
		
	}

	
	@Test
	@DisplayName("testing Festival to Location mapping")
	void test3() {
		
//		mysql> SELECT * FROM festival WHERE festival.id = 1;
//		+----+---------+-----------------------------------------+-------------+---------------+------------+--------------------+------------------------------------------------------------------------------------------------------+---------------------+
//		| id | user_id | name                                    | location_id | festival_date | start_time | description        | img_url                                                                                              | created_date        |
//		+----+---------+-----------------------------------------+-------------+---------------+------------+--------------------+------------------------------------------------------------------------------------------------------+---------------------+
//		|  1 |       1 | Beers, Brats, Battlestar Galactica Fest |           1 | 2022-07-01    | 13:35:00   | Beers, Beets, Food | https://64.media.tumblr.com/3088bc582dae38b21f195e468fb8d224/tumblr_inline_ousnf8oJkW1t0aw4o_640.jpg | 2022-01-01 13:35:00 |
//		+----+---------+-----------------------------------------+-------------+---------------+------------+--------------------+------------------------------------------------------------------------------------------------------+---------------------+
		
		assertNotNull(festival);
		assertNotNull(festival.getLocation());
		assertEquals(1, festival.getLocation().getId());
		
	}

}
