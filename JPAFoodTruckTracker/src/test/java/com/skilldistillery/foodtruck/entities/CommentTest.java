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

class CommentTest {

	public static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private Comment comment;
	
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
		comment = em.find(Comment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comment = null;
	}

	@Test
	@DisplayName("testing basic Comment mapping")
	void test1() {
		
//		mysql> select * from comment;
//		+----+------------------------+--------+---------+---------------+---------------------+
//		| id | comment                | rating | user_id | food_truck_id | comment_date        |
//		+----+------------------------+--------+---------+---------------+---------------------+
//		|  1 | Great food!            |      5 |       1 |             1 | 2022-05-01 13:35:00 |
//		|  2 | Sauce is great.        |      4 |       2 |             2 | 2022-04-01 14:35:00 |
//		|  3 | Bring your own napkins |      3 |       3 |             3 | 2018-03-01 13:39:00 |
//		|  4 | High prices            |      2 |       4 |             4 | 2021-04-01 16:22:00 |
//		|  5 | Best burgers ever!     |      5 |       5 |             5 | 2022-05-01 14:47:00 |
//		+----+------------------------+--------+---------+---------------+---------------------+

		assertNotNull(comment);
		assertEquals("Great food!", comment.getComment());
	}
	
	@Test
	@DisplayName("testing Comment to User mapping")
	void test2() {
		
//		mysql> SELECT * FROM comment WHERE comment.id = 1;
//		+----+-------------+--------+---------+---------------+---------------------+
//		| id | comment     | rating | user_id | food_truck_id | comment_date        |
//		+----+-------------+--------+---------+---------------+---------------------+
//		|  1 | Great food! |      5 |       1 |             1 | 2022-05-01 13:35:00 |
//		+----+-------------+--------+---------+---------------+---------------------+
		
		assertNotNull(comment);
		assertNotNull(comment.getUser());
		assertEquals(1, comment.getUser().getId());
		
	}

	@Test
	@DisplayName("testing Comment to FoodTruck mapping")
	void test3() {
		
//		mysql> SELECT * FROM comment WHERE comment.id = 1;
//		+----+-------------+--------+---------+---------------+---------------------+
//		| id | comment     | rating | user_id | food_truck_id | comment_date        |
//		+----+-------------+--------+---------+---------------+---------------------+
//		|  1 | Great food! |      5 |       1 |             1 | 2022-05-01 13:35:00 |
//		+----+-------------+--------+---------+---------------+---------------------+
		
		assertNotNull(comment);
		assertNotNull(comment.getFoodTruck());
		assertEquals(1, comment.getFoodTruck().getId());
		
	}
}
