-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema foodtruckdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `foodtruckdb` ;

-- -----------------------------------------------------
-- Schema foodtruckdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `foodtruckdb` DEFAULT CHARACTER SET utf8 ;
USE `foodtruckdb` ;

-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(55) NULL,
  `city` VARCHAR(65) NULL,
  `state` VARCHAR(2) NULL,
  `zip` VARCHAR(10) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `role` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `profile_img_url` VARCHAR(2000) NULL,
  `location_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_user_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food_truck`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `food_truck` ;

CREATE TABLE IF NOT EXISTS `food_truck` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `img_url` VARCHAR(2000) NULL,
  `user_id` INT NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  `date_created` DATETIME NULL,
  `website_url` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_food_truck_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_food_truck_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `food_category` ;

CREATE TABLE IF NOT EXISTS `food_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(55) NULL,
  `description` TEXT NULL,
  `img_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `menu_item` ;

CREATE TABLE IF NOT EXISTS `menu_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` TEXT NULL,
  `price` DECIMAL(5,2) NULL,
  `img_url` VARCHAR(2000) NULL,
  `active` TINYINT NULL DEFAULT 1,
  `food_truck_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_menu_item_food_truck1_idx` (`food_truck_id` ASC),
  CONSTRAINT `fk_menu_item_food_truck1`
    FOREIGN KEY (`food_truck_id`)
    REFERENCES `food_truck` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(300) NOT NULL,
  `rating` INT NULL,
  `user_id` INT NOT NULL,
  `food_truck_id` INT NOT NULL,
  `comment_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_comment_food_truck1_idx` (`food_truck_id` ASC),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_food_truck1`
    FOREIGN KEY (`food_truck_id`)
    REFERENCES `food_truck` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tagged_truck`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tagged_truck` ;

CREATE TABLE IF NOT EXISTS `tagged_truck` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `photo_url` VARCHAR(2000) NULL,
  `food_truck_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  `comment` TEXT NULL,
  `date_tagged` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tagged_truck_food_truck1_idx` (`food_truck_id` ASC),
  INDEX `fk_tagged_truck_user1_idx` (`user_id` ASC),
  INDEX `fk_tagged_truck_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_tagged_truck_food_truck1`
    FOREIGN KEY (`food_truck_id`)
    REFERENCES `food_truck` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tagged_truck_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tagged_truck_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `schedule` ;

CREATE TABLE IF NOT EXISTS `schedule` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `arrival` DATETIME NULL,
  `departure` DATETIME NULL,
  `description` TEXT NULL,
  `food_truck_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_schedule_food_truck1_idx` (`food_truck_id` ASC),
  INDEX `fk_schedule_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_schedule_food_truck1`
    FOREIGN KEY (`food_truck_id`)
    REFERENCES `food_truck` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_schedule_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `favorites`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `favorites` ;

CREATE TABLE IF NOT EXISTS `favorites` (
  `food_truck_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`food_truck_id`, `user_id`),
  INDEX `fk_food_truck_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_food_truck_has_user_food_truck1_idx` (`food_truck_id` ASC),
  CONSTRAINT `fk_food_truck_has_user_food_truck1`
    FOREIGN KEY (`food_truck_id`)
    REFERENCES `food_truck` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_food_truck_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `request` ;

CREATE TABLE IF NOT EXISTS `request` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `remarks` TEXT NULL,
  `user_id` INT NOT NULL,
  `food_truck_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  `request_placed` DATETIME NULL,
  `requested_date` DATETIME NULL,
  `accepted` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_request_user1_idx` (`user_id` ASC),
  INDEX `fk_request_food_truck1_idx` (`food_truck_id` ASC),
  INDEX `fk_request_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_request_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_food_truck1`
    FOREIGN KEY (`food_truck_id`)
    REFERENCES `food_truck` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food_truck_has_food_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `food_truck_has_food_category` ;

CREATE TABLE IF NOT EXISTS `food_truck_has_food_category` (
  `food_truck_id` INT NOT NULL,
  `food_category_id` INT NOT NULL,
  PRIMARY KEY (`food_truck_id`, `food_category_id`),
  INDEX `fk_food_truck_has_food_category_food_category1_idx` (`food_category_id` ASC),
  INDEX `fk_food_truck_has_food_category_food_truck1_idx` (`food_truck_id` ASC),
  CONSTRAINT `fk_food_truck_has_food_category_food_truck1`
    FOREIGN KEY (`food_truck_id`)
    REFERENCES `food_truck` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_food_truck_has_food_category_food_category1`
    FOREIGN KEY (`food_category_id`)
    REFERENCES `food_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `festival`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `festival` ;

CREATE TABLE IF NOT EXISTS `festival` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `name` VARCHAR(100) NULL,
  `location_id` INT NOT NULL,
  `festival_date` DATE NULL,
  `start_time` TIME NULL,
  `description` TEXT NULL,
  `img_url` VARCHAR(2000) NULL,
  `created_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_festival_user1_idx` (`user_id` ASC),
  INDEX `fk_festival_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_festival_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_festival_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `festival_has_food_truck`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `festival_has_food_truck` ;

CREATE TABLE IF NOT EXISTS `festival_has_food_truck` (
  `festival_id` INT NOT NULL,
  `food_truck_id` INT NOT NULL,
  PRIMARY KEY (`festival_id`, `food_truck_id`),
  INDEX `fk_festival_has_food_truck_food_truck1_idx` (`food_truck_id` ASC),
  INDEX `fk_festival_has_food_truck_festival1_idx` (`festival_id` ASC),
  CONSTRAINT `fk_festival_has_food_truck_festival1`
    FOREIGN KEY (`festival_id`)
    REFERENCES `festival` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_festival_has_food_truck_food_truck1`
    FOREIGN KEY (`food_truck_id`)
    REFERENCES `food_truck` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_food_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_food_category` ;

CREATE TABLE IF NOT EXISTS `user_has_food_category` (
  `user_id` INT NOT NULL,
  `food_category_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `food_category_id`),
  INDEX `fk_user_has_food_category_food_category1_idx` (`food_category_id` ASC),
  INDEX `fk_user_has_food_category_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_food_category_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_food_category_food_category1`
    FOREIGN KEY (`food_category_id`)
    REFERENCES `food_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pre_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pre_order` ;

CREATE TABLE IF NOT EXISTS `pre_order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ordered_date` DATETIME NULL,
  `special_requests` TEXT NULL,
  `completed` TINYINT NOT NULL DEFAULT 0,
  `pickup_date` DATETIME NULL,
  `user_id` INT NOT NULL,
  `menu_item_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_user1_idx` (`user_id` ASC),
  INDEX `fk_order_menu_item1_idx` (`menu_item_id` ASC),
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_menu_item1`
    FOREIGN KEY (`menu_item_id`)
    REFERENCES `menu_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS foodtruckuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'foodtruckuser'@'localhost' IDENTIFIED BY 'foodtruck';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'foodtruckuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `location`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `location` (`id`, `street`, `city`, `state`, `zip`) VALUES (1, ' 10175 Easter Street', 'Grand Junction', 'CO', '81505');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `zip`) VALUES (2, '19968 Cucumber Ave', 'Aurora', 'CO', '80019');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `zip`) VALUES (3, '1550 Larson Road', 'Lone Tree', 'CO', '80124');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `zip`) VALUES (4, '1611 Main Circle', 'Highlands Ranch', 'CO', '80129');
INSERT INTO `location` (`id`, `street`, `city`, `state`, `zip`) VALUES (5, '8200 Park Meadows Drive', 'Lone Tree', 'CO', '80124');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `profile_img_url`, `location_id`) VALUES (1, 'admin', '$2a$10$bab1OlosOTq0/3fj9wutSex96pbHs6en/bPpaoMbDjdMEKV8cP0Qy', 1, 'ROLE_ADMIN', 'Jurisa', 'Yockenfelder', 'https://cdn.icon-icons.com/icons2/2506/PNG/512/user_icon_150670.png', 1);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `profile_img_url`, `location_id`) VALUES (2, 'foodtruckuser', '$2a$10$A24dTQay/kJiInPFNgt7gObuR815NdmOsknX6q8qp9vq77h.Dyi12', 1, 'ROLE_ADMIN', 'Food', 'Truck', 'https://d2t1xqejof9utc.cloudfront.net/screenshots/pics/26e603a3960425a6aa28ef1bfb22ae64/large.jpg', 2);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `profile_img_url`, `location_id`) VALUES (3, 'RandomFoodTrucker', 'password', 1, 'GENERAL', 'Bob', 'Jeselnik', 'https://media-exp1.licdn.com/dms/image/C4D03AQFsU2PWoZ2uuQ/profile-displayphoto-shrink_800_800/0/1649875181704?e=1658966400&v=beta&t=paSWVje0pYBaPnipLLHtwpiuvsPrnPAyaacyAU7_IWk', 3);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `profile_img_url`, `location_id`) VALUES (4, 'SDsBestInstructor', 'password', 1, 'GENERAL', 'Jer', 'Bear', 'https://i.guim.co.uk/img/media/86c3481516dce247943ac2978b4f48d16a3ac265/0_170_5120_3074/master/5120.jpg?width=620&quality=85&auto=format&fit=max&s=d73e0c12a90e9da24736865e9274ef17', 4);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `profile_img_url`, `location_id`) VALUES (5, 'ArchersFriend', 'password', 1, 'GENERAL', 'Slater', 'Burgers', 'https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.gannett-cdn.com%2Fpresto%2F2020%2F06%2F12%2FPPHX%2Fe7207da9-6fbe-467b-b63e-ac44e95db582-BobsBurgers_2019_KeyPoses_Bob_1.jpg%3Fcrop%3D3356%2C1888%2Cx0%2Cy480%26width%3D3200%26height%3D1801%26format%3Dpjpg%26auto%3Dwebp&imgrefurl=https%3A%2F%2Fwww.azcentral.com%2Fstory%2Fentertainment%2Fmovies%2Fbillgoodykoontz%2F2020%2F06%2F19%2Ffathers-day-bob-belcher-bobs-burgers-best-dad-tv%2F5347097002%2F&tbnid=XyIDyGLtYhddEM&vet=12ahUKEwjrgvGFuPv3AhXJCs0KHR0eDS4QMygHegUIARDtAQ..i&docid=qC9uM9xa2MXDgM&w=3200&h=1801&q=bob%27s%20burger&ved=2ahUKEwjrgvGFuPv3AhXJCs0KHR0eDS4QMygHegUIARDtAQ', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `food_truck`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `food_truck` (`id`, `name`, `description`, `img_url`, `user_id`, `active`, `date_created`, `website_url`) VALUES (1, 'Tacos R Us', 'Fresh traditional authentic mexican food.', 'https://images.unsplash.com/photo-1599974579688-8dbdd335c77f?', 1, 1, '2021-05-01 13:35:00', 'tacosrus.com');
INSERT INTO `food_truck` (`id`, `name`, `description`, `img_url`, `user_id`, `active`, `date_created`, `website_url`) VALUES (2, 'MIchael Scott Tapas Company', 'Tapas. What else do you need to know?', 'https://images.unsplash.com/photo-1620041328526-d0cf044a0739?', 2, 1, '2020-02-01 13:35:00', 'michaelscotttapascompany.com');
INSERT INTO `food_truck` (`id`, `name`, `description`, `img_url`, `user_id`, `active`, `date_created`, `website_url`) VALUES (3, 'Pizza Pizza ', 'We serve wood fired oven pizza. All pizzas are 12-13\".', 'https://images.unsplash.com/photo-1513104890138-7c749659a591?', 3, 1, '2020-03-01 13:35:00', 'pizzapizza.com');
INSERT INTO `food_truck` (`id`, `name`, `description`, `img_url`, `user_id`, `active`, `date_created`, `website_url`) VALUES (4, 'Licky Chicky', 'We specialize in chicken strips with a variety of sauces.', 'https://images.unsplash.com/photo-1587606381492-7586d66a04a5?', 4, 1, '2020-04-01 13:35:00', 'LickyChicky.com');
INSERT INTO `food_truck` (`id`, `name`, `description`, `img_url`, `user_id`, `active`, `date_created`, `website_url`) VALUES (5, 'Bobs Burgers', 'Homestyle burgers hand crafted fresh every day.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7sfEVEo_RVnqQu1K9KWquwcsDGfvdHzJhSA&usqp=CAU', 5, 1, '2020-05-01 13:35:00', 'BobbyBurgers.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `food_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `food_category` (`id`, `name`, `description`, `img_url`) VALUES (1, 'Mexican', 'Tacos, burritos, tostadas.', 'https://images.unsplash.com/photo-1565299585323-38d6b0865b47?');
INSERT INTO `food_category` (`id`, `name`, `description`, `img_url`) VALUES (2, 'Spanish', 'Tapas, paella.', 'https://images.unsplash.com/photo-1555939594-58d7cb561ad1?');
INSERT INTO `food_category` (`id`, `name`, `description`, `img_url`) VALUES (3, 'Italian', 'Pizza and pasta. ', 'https://images.unsplash.com/photo-1552580715-4d9bc27f1e2f?');
INSERT INTO `food_category` (`id`, `name`, `description`, `img_url`) VALUES (4, 'Fried chicken', 'Chicken strips, chicken sandwiches.', 'https://images.unsplash.com/photo-1626645738196-c2a7c87a8f58?');
INSERT INTO `food_category` (`id`, `name`, `description`, `img_url`) VALUES (5, 'American ', 'Burgers, hotdogs, fries.', 'https://images.unsplash.com/photo-1601313816462-fe3cbebf6753?');

COMMIT;


-- -----------------------------------------------------
-- Data for table `menu_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (1, 'Taco Burger', 'Tacos on a burger with a special taco sauce and fried onions.', 5.75, 'https://i0.wp.com/www.alidaskitchen.com/wp-content/uploads/2015/06/IMG_2974-pm600tc.jpg?ssl=1', 1, 1);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (2, 'Steak Tapas', 'Steak tapas with spicy aoli.', 8.50, 'https://steamykitchen.com/wp-content/uploads/2011/04/flank-steak-goat-cheese-tapas-recipe-7908.jpg', 1, 2);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (3, 'Pepperoni Pizza', 'Wood fired pepperoni pizza with red sauce.', 11.50, 'https://www.thespruceeats.com/thmb/EFfZEGfHWzb-qJTCsUlNCitowYs=/2492x1869/smart/filters:no_upscale()/Pepperonipizzahoriz-3d53b00a7cce429eae3bfb629df356ec.jpg', 1, 3);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (4, 'Chicken Strips', 'Peanut oil fried chicken strips with your choice of two sauces. ', 8.50, 'https://www.thespruceeats.com/thmb/vDlaVREm3DdAUHEJeUqv53zbWao=/4256x2832/filters:fill(auto,1)/spicy-fried-chicken-strips-3056880-hero-01-e5bad43e0d3441749f17c1b98b5486c2.jpg', 1, 4);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (5, 'Cheeseburger', 'Cheeseburger on a potato roll served with fries.', 6.75, 'https://static.showit.co/800/wqVSgNPySpucCIF2ZW6yCA/shared/image_4.png', 1, 5);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (6, 'Carne Asado Tacos', 'Three carne asada tacos with onion and cilantro.', 5.75, 'https://www.eatingonadime.com/wp-content/uploads/2020/10/carne-asada-1-square.jpg', 1, 1);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (7, 'Chorizo al Vino', 'Sliced of chorizo in red wine.', 8.00, 'https://www.carolinescooking.com/wp-content/uploads/2021/03/chorizo-in-red-wine-photo.jpg', 1, 2);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (8, 'Gambas al Ajillo', 'Shrimp in garlic butter sauce.', 9.00, 'https://static.onecms.io/wp-content/uploads/sites/9/2017/08/gambas-al-ajillo-XL-RECIPE0917.jpg', 1, 2);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (9, 'Beef Croquette', 'Five dumplings filled with ground beef and mashed potatoes with brown sauce deep, breaded and deep fried.', 11.00, 'https://img.taste.com.au/WGOhhNTe/taste/2016/11/potato-and-beef-croquettes-73096-1.jpeg', 1, 2);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (10, 'Sausage and Fennel Pizza', 'Wood fired pizza topped with local sausage and fenel.', 12.50, 'https://youplateit.com.au/wp-content/uploads/2019/05/Pork-Fennel-Sausage-Pizza-Plated.jpg', 1, 3);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (11, 'Fresh Baked Focaccia Bread', 'Focaccia bread topped with olive oil, parmesan, rosemary and sea salt.', 6.25, 'https://www.giallozafferano.com/images/228-22899/soft-focaccia_1200x800.jpg', 1, 3);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (12, 'Three Cheese Pizza', 'Wood fired pizza topped with mozzarella, parmesan, smoked provolone and oregano.', 10.50, 'https://www.averiecooks.com/wp-content/uploads/2018/04/pizza-9.jpg', 1, 3);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (13, 'Buffalo Chicken Wrap', 'Three chicken strips wrapped in a tortilla with ranch on the side.', 8.75, 'https://www.kindpng.com/picc/m/37-372910_buffalo-wild-wings-chicken-wrap-hd-png-download.png', 1, 4);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (14, 'Onion Ring and Chicken Strip Family Platter', 'A basket of onion rings topped with diced chicken strips and shredded cheese on top. ', 15.35, 'https://www.crispyfishandchicken.com/images/chicken-tenders.jpg', 1, 4);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (15, 'Chicken Strip Sammy', 'Four crispy chicken strips on Texas Toast with melted cheese on strips. ', 10.00, 'https://copykat.com/wp-content/uploads/2018/09/whataburger-honey-bbq-chicken-strip-sandwich-copycat-recipe-500x500.jpg', 1, 4);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (16, 'Double Bacon Cheeseburger', 'Two hand made patties topped with bacon and cheese on a brioche bun.', 12.15, 'https://cdn.shopify.com/s/files/1/2264/4927/products/baconcheeseburger_600x.png?v=1630093666', 1, 5);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (17, 'Burger of the Day', 'Chef crafted burger of the day. Changes every day, see today\'s menu for more information.', 9.00, 'https://thumbs.dreamstime.com/b/lunch-burger-icon-outline-lunch-burger-vector-icon-web-design-isolated-white-background-lunch-burger-icon-outline-style-173127884.jpg', 1, 5);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (18, 'Burger Dog', 'Hamburger topped with two halved hot dogs.', 8.50, 'https://cdn.vox-cdn.com/thumbor/uWheIrlUpUX1oZYPdNt0sq7XRTg=/0x0:900x675/1200x800/filters:focal(0x0:900x675)/cdn.vox-cdn.com/uploads/chorus_image/image/46334120/the-most-american-thickburger.0.0.jpg', 1, 5);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (19, 'Tostada', 'Crispy fried corn tortilla topped with refried beans, grated cheese, chopped avocado and tomato, sliced lettuce, and salsa and your choice of meat.', 7.00, 'https://www.simplyrecipes.com/thmb/QtelSVG3QT94q_fJSozw-IVk0GU=/648x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/Simply-Recipes-Mexican-Tostada-LEAD-4-acbcd5e6b81d478fa1e2f0718058de80.jpg', 1, 1);
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (20, 'Burrito', 'Traditional burrito with your choice of meat with beans, sour cream, cilantro and onions.', 8.75, 'https://robertostacoshop.com/wp-content/uploads/2020/08/Robertos-Taco-Shop-Carne-Asada-Burrito-1170x658.jpg', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `comment` (`id`, `comment`, `rating`, `user_id`, `food_truck_id`, `comment_date`) VALUES (1, 'Great food!', 5, 1, 1, '2022-05-01 13:35:00');
INSERT INTO `comment` (`id`, `comment`, `rating`, `user_id`, `food_truck_id`, `comment_date`) VALUES (2, 'Sauce is great. ', 4, 2, 2, '2022-04-01 14:35:00');
INSERT INTO `comment` (`id`, `comment`, `rating`, `user_id`, `food_truck_id`, `comment_date`) VALUES (3, 'Bring your own napkins', 3, 3, 3, '2018-03-01 13:39:00');
INSERT INTO `comment` (`id`, `comment`, `rating`, `user_id`, `food_truck_id`, `comment_date`) VALUES (4, 'High prices', 2, 4, 4, '2021-04-01 16:22:00');
INSERT INTO `comment` (`id`, `comment`, `rating`, `user_id`, `food_truck_id`, `comment_date`) VALUES (5, 'Best burgers ever!', 5, 5, 5, '2022-05-01 14:47:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tagged_truck`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `tagged_truck` (`id`, `photo_url`, `food_truck_id`, `user_id`, `location_id`, `comment`, `date_tagged`) VALUES (1, 'google.com', 1, 1, 1, 'Smells amazing. ', '2000-05-01 13:35:00');
INSERT INTO `tagged_truck` (`id`, `photo_url`, `food_truck_id`, `user_id`, `location_id`, `comment`, `date_tagged`) VALUES (2, 'googletwo.com', 2, 2, 2, 'Almost out of steak.', '2001-05-01 17:22:00');
INSERT INTO `tagged_truck` (`id`, `photo_url`, `food_truck_id`, `user_id`, `location_id`, `comment`, `date_tagged`) VALUES (3, 'googlethree.com', 3, 3, 3, 'Seems like they have new items.', '2002-05-01 15:56:00');
INSERT INTO `tagged_truck` (`id`, `photo_url`, `food_truck_id`, `user_id`, `location_id`, `comment`, `date_tagged`) VALUES (4, 'googlefour.com', 4, 4, 4, 'Only here until 7pm', '2003-05-01 11:32:00');
INSERT INTO `tagged_truck` (`id`, `photo_url`, `food_truck_id`, `user_id`, `location_id`, `comment`, `date_tagged`) VALUES (5, 'googlefive.com', 5, 5, 5, 'Looks busy. ', '2019-05-01 08:35:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `schedule`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `schedule` (`id`, `arrival`, `departure`, `description`, `food_truck_id`, `location_id`) VALUES (1, '2022-05-01 13:35:00', '2022-05-01 20:35:00', 'Good deal', 1, 1);
INSERT INTO `schedule` (`id`, `arrival`, `departure`, `description`, `food_truck_id`, `location_id`) VALUES (2, '2022-05-02 13:35:00', '2022-05-02 20:35:00', 'Great prices', 2, 2);
INSERT INTO `schedule` (`id`, `arrival`, `departure`, `description`, `food_truck_id`, `location_id`) VALUES (3, '2022-05-03 13:35:00', '2022-05-03 20:35:00', 'Well be under the overhang', 3, 3);
INSERT INTO `schedule` (`id`, `arrival`, `departure`, `description`, `food_truck_id`, `location_id`) VALUES (4, '2022-05-01 13:35:00', '2022-05-04 20:35:00', 'Come see us!', 4, 4);
INSERT INTO `schedule` (`id`, `arrival`, `departure`, `description`, `food_truck_id`, `location_id`) VALUES (5, '2022-05-01 13:35:00', '2022-05-05 20:35:00', 'Five', 5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `favorites`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `favorites` (`food_truck_id`, `user_id`) VALUES (1, 1);
INSERT INTO `favorites` (`food_truck_id`, `user_id`) VALUES (2, 2);
INSERT INTO `favorites` (`food_truck_id`, `user_id`) VALUES (3, 3);
INSERT INTO `favorites` (`food_truck_id`, `user_id`) VALUES (4, 4);
INSERT INTO `favorites` (`food_truck_id`, `user_id`) VALUES (5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `request`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `request` (`id`, `remarks`, `user_id`, `food_truck_id`, `location_id`, `request_placed`, `requested_date`, `accepted`) VALUES (1, 'Please don\'t park by the fire hydrant', 1, 1, 1, '2022-05-01 13:35:00', '2022-06-01 13:35:00', 1);
INSERT INTO `request` (`id`, `remarks`, `user_id`, `food_truck_id`, `location_id`, `request_placed`, `requested_date`, `accepted`) VALUES (2, 'Back in until you touch the curb. ', 2, 2, 2, '2022-02-01 13:35:00', '2019-06-01 13:35:00', 1);
INSERT INTO `request` (`id`, `remarks`, `user_id`, `food_truck_id`, `location_id`, `request_placed`, `requested_date`, `accepted`) VALUES (3, 'No electicity for truck.', 3, 3, 3, '2022-03-01 13:35:00', '2020-06-01 13:35:00', 1);
INSERT INTO `request` (`id`, `remarks`, `user_id`, `food_truck_id`, `location_id`, `request_placed`, `requested_date`, `accepted`) VALUES (4, 'Please use porta potty.', 4, 4, 4, '2022-04-01 13:35:00', '2021-06-01 13:35:00', 1);
INSERT INTO `request` (`id`, `remarks`, `user_id`, `food_truck_id`, `location_id`, `request_placed`, `requested_date`, `accepted`) VALUES (5, 'Not allowed inside!', 5, 5, 5, '2022-05-01 13:35:00', '2018-02-07 13:35:00', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `food_truck_has_food_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `food_truck_has_food_category` (`food_truck_id`, `food_category_id`) VALUES (1, 1);
INSERT INTO `food_truck_has_food_category` (`food_truck_id`, `food_category_id`) VALUES (2, 2);
INSERT INTO `food_truck_has_food_category` (`food_truck_id`, `food_category_id`) VALUES (3, 3);
INSERT INTO `food_truck_has_food_category` (`food_truck_id`, `food_category_id`) VALUES (4, 4);
INSERT INTO `food_truck_has_food_category` (`food_truck_id`, `food_category_id`) VALUES (5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `festival`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `festival` (`id`, `user_id`, `name`, `location_id`, `festival_date`, `start_time`, `description`, `img_url`, `created_date`) VALUES (1, 1, 'Beers, Brats, Battlestar Galactica Fest', 1, '2022-07-01', '13:35:00', 'Beers, Beets, Food', 'https://64.media.tumblr.com/3088bc582dae38b21f195e468fb8d224/tumblr_inline_ousnf8oJkW1t0aw4o_640.jpg', '2022-01-01 13:35:00');
INSERT INTO `festival` (`id`, `user_id`, `name`, `location_id`, `festival_date`, `start_time`, `description`, `img_url`, `created_date`) VALUES (2, 2, 'Food Truck Mania', 2, '2022-02-01', '13:35:00', 'So many trucks!', 'https://images.unsplash.com/photo-1612208176815-e132bcf971b0?', '2022-02-01 13:35:00');
INSERT INTO `festival` (`id`, `user_id`, `name`, `location_id`, `festival_date`, `start_time`, `description`, `img_url`, `created_date`) VALUES (3, 3, 'Truck a palooza', 3, '2023-03-01', '13:35:00', 'Festival of food', 'https://images.unsplash.com/photo-1505496704829-37e28089504e?', '2022-03-01 13:35:00');
INSERT INTO `festival` (`id`, `user_id`, `name`, `location_id`, `festival_date`, `start_time`, `description`, `img_url`, `created_date`) VALUES (4, 4, 'Pizza and Burgers Fest', 4, '2024-04-01', '13:35:00', 'Pizza and Burger Trucks', 'https://images.unsplash.com/photo-1487004121828-9fa15a215a7a?', '2022-04-01 13:35:00');
INSERT INTO `festival` (`id`, `user_id`, `name`, `location_id`, `festival_date`, `start_time`, `description`, `img_url`, `created_date`) VALUES (5, 5, 'Sandal Extravaganza', 5, '2025-05-01', '13:35:00', 'Walk around in sandals eating food', 'https://images.unsplash.com/photo-1625563206627-7e713d1ac0a8?', '2022-05-01 13:35:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `festival_has_food_truck`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `festival_has_food_truck` (`festival_id`, `food_truck_id`) VALUES (1, 1);
INSERT INTO `festival_has_food_truck` (`festival_id`, `food_truck_id`) VALUES (2, 2);
INSERT INTO `festival_has_food_truck` (`festival_id`, `food_truck_id`) VALUES (3, 3);
INSERT INTO `festival_has_food_truck` (`festival_id`, `food_truck_id`) VALUES (4, 4);
INSERT INTO `festival_has_food_truck` (`festival_id`, `food_truck_id`) VALUES (5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_food_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `user_has_food_category` (`user_id`, `food_category_id`) VALUES (1, 1);
INSERT INTO `user_has_food_category` (`user_id`, `food_category_id`) VALUES (2, 2);
INSERT INTO `user_has_food_category` (`user_id`, `food_category_id`) VALUES (3, 3);
INSERT INTO `user_has_food_category` (`user_id`, `food_category_id`) VALUES (4, 4);
INSERT INTO `user_has_food_category` (`user_id`, `food_category_id`) VALUES (5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pre_order`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `pre_order` (`id`, `ordered_date`, `special_requests`, `completed`, `pickup_date`, `user_id`, `menu_item_id`) VALUES (1, '2022-05-01 13:35:00', 'No pickles', 1, '2022-05-01 15:35:00', 1, 1);
INSERT INTO `pre_order` (`id`, `ordered_date`, `special_requests`, `completed`, `pickup_date`, `user_id`, `menu_item_id`) VALUES (2, '2022-04-29 13:00:00', 'Double steak.', 1, '2022-05-01 14:30:00', 2, 2);
INSERT INTO `pre_order` (`id`, `ordered_date`, `special_requests`, `completed`, `pickup_date`, `user_id`, `menu_item_id`) VALUES (3, '2022-05-18 17:54:00', 'Extra sauce please. ', 1, '2022-05-18 19:25:00', 3, 3);
INSERT INTO `pre_order` (`id`, `ordered_date`, `special_requests`, `completed`, `pickup_date`, `user_id`, `menu_item_id`) VALUES (4, '2022-05-29 12:14:00', 'Two forks!', 0, '2022-05-30 16:20:00', 4, 4);
INSERT INTO `pre_order` (`id`, `ordered_date`, `special_requests`, `completed`, `pickup_date`, `user_id`, `menu_item_id`) VALUES (5, '2022-05-01 14:42:00', 'No bun, lettuce wrap. ', 1, '2022-05-01 17:35:00', 5, 5);

COMMIT;

