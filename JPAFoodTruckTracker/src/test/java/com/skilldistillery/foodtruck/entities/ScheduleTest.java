package com.skilldistillery.foodtruck.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScheduleTest {

	public static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Schedule schedule;
	
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
		schedule = em.find(Schedule.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		schedule = null;
	}

	@Test
	void test() {
		
//		mysql> select * from schedule;
//		+----+---------------------+---------------------+----------------------------+---------------+-------------+
//		| id | arrival             | departure           | description                | food_truck_id | location_id |
//		+----+---------------------+---------------------+----------------------------+---------------+-------------+
//		|  1 | 2022-05-01 13:35:00 | 2022-05-01 20:35:00 | Good deal                  |             1 |           1 |
//		|  2 | 2022-05-02 13:35:00 | 2022-05-02 20:35:00 | Great prices               |             2 |           2 |
//		|  3 | 2022-05-03 13:35:00 | 2022-05-03 20:35:00 | Well be under the overhang |             3 |           3 |
//		|  4 | 2022-05-01 13:35:00 | 2022-05-04 20:35:00 | Come see us!               |             4 |           4 |
//		|  5 | 2022-05-01 13:35:00 | 2022-05-05 20:35:00 | Five                       |             5 |           5 |
//		+----+---------------------+---------------------+----------------------------+---------------+-------------+
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		assertNotNull(schedule);
		assertEquals(2022, schedule.getArrival().getYear());
		assertEquals(5, schedule.getArrival().getMonthValue());
		//assertEquals(LocalDateTime.parse("2022-05-01T13:35:00"), schedule.getArrival());
	}

	@Test
	@DisplayName("Schedule to FoodTruck mapping")
	void test2() {
		assertNotNull(schedule);
		assertEquals("Tacos R Us", schedule.getFoodTruck().getName());
		
	}
	@Test
	@DisplayName("Schedule to Location Mapping")
	void test3() {
		assertNotNull(schedule);
		assertEquals("Grand Junction", schedule.getLocation().getCity());
		
	}

}
