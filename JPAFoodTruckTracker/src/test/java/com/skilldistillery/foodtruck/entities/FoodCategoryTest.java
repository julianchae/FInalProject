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

class FoodCategoryTest {

	public static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private FoodCategory foodCategory;
	
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
		foodCategory = em.find(FoodCategory.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		foodCategory = null;
	}

	@Test
	void test() {
		
//		mysql> select * from food_category;
//		+----+---------------------+--------------------+------------+
//		| id | name                | description        | img_url    |
//		+----+---------------------+--------------------+------------+
//		|  1 | Mexican             | authenticate tacos | google.com |
//		|  2 | Spanish style tapas | steak tapas        | 2.com      |
//		|  3 | Pizza               | pizza              | 3.com      |
//		|  4 | Fried chicken       | chicken strips     | 4.com      |
//		|  5 | American            | burgers, fries     | 5.com      |
//		+----+---------------------+--------------------+------------+

		assertNotNull(foodCategory);
		assertEquals("Mexican", foodCategory.getName());
	}
	@Test 
	@DisplayName("Food category to food truck connection")
	void test2() {
		assertNotNull(foodCategory);
		assertNotNull(foodCategory.getFoodTrucks());
		assertTrue( foodCategory.getFoodTrucks().size() > 0);
		
	}

}
