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

class RequestTest {

	public static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Request request;
	
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
		request = em.find(Request.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		request = null;
	}

	@Test
	void test() {
		
//		mysql> select * from request;
//		+----+---------------------------------------+---------+---------------+-------------+---------------------+---------------------+----------+
//		| id | remarks                               | user_id | food_truck_id | location_id | request_placed      | requested_date      | accepted |
//		+----+---------------------------------------+---------+---------------+-------------+---------------------+---------------------+----------+
//		|  1 | Please don't park by the fire hydrant |       1 |             1 |           1 | 2022-05-01 13:35:00 | 2022-06-01 13:35:00 |        1 |
//		|  2 | Back in until you touch the curb.     |       2 |             2 |           2 | 2022-02-01 13:35:00 | 2019-06-01 13:35:00 |        1 |
//		|  3 | No electicity for truck.              |       3 |             3 |           3 | 2022-03-01 13:35:00 | 2020-06-01 13:35:00 |        1 |
//		|  4 | Please use porta potty.               |       4 |             4 |           4 | 2022-04-01 13:35:00 | 2021-06-01 13:35:00 |        1 |
//		|  5 | Not allowed inside!                   |       5 |             5 |           5 | 2022-05-01 13:35:00 | 2018-02-07 13:35:00 |        1 |
//		+----+---------------------------------------+---------+---------------+-------------+---------------------+---------------------+----------+
		
		assertNotNull(request);
		assertEquals("Please don't park by the fire hydrant", request.getRemarks());
	}

}
