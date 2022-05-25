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
  `location_id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `img_url` VARCHAR(2000) NULL,
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
  `img_url` VARCHAR(45) NULL,
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
-- Table `order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order` ;

CREATE TABLE IF NOT EXISTS `order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ordered_date` DATETIME NULL,
  `special_requests` TEXT NULL,
  `user_id` INT NOT NULL,
  `menu_item_id` INT NOT NULL,
  `completed` TINYINT NOT NULL DEFAULT 0,
  `pickup_date` DATETIME NULL,
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

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `location_id`, `first_name`, `last_name`, `img_url`) VALUES (1, 'admin', '$2a$10$bab1OlosOTq0/3fj9wutSex96pbHs6en/bPpaoMbDjdMEKV8cP0Qy', 1, 'ROLE_ADMIN', 1, 'Dave', 'Ramsey', 'google.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `food_truck`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `food_truck` (`id`, `name`, `description`, `img_url`, `user_id`, `active`, `date_created`, `website_url`) VALUES (1, 'Tacos R Us', 'Fresh traditional tacos', 'google.com', 1, 1, '2020-05-01 13:35:00', 'google.com');
INSERT INTO `food_truck` (`id`, `name`, `description`, `img_url`, `user_id`, `active`, `date_created`, `website_url`) VALUES (2, 'MIchael Scott Tapas Company', 'Tapas. What else do you need to know?', 'google.com', 1, 1, '2020-05-01 13:35:00', 'google.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `food_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `food_category` (`id`, `name`, `description`, `img_url`) VALUES (1, 'Taco Burger', 'Tacos on a bun. ', 'google.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `menu_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `menu_item` (`id`, `name`, `description`, `price`, `img_url`, `active`, `food_truck_id`) VALUES (1, 'Taco Burger', 'Tacos on a burger. ', 5.99, 'google.com', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `comment` (`id`, `comment`, `rating`, `user_id`, `food_truck_id`, `comment_date`) VALUES (1, 'Great food!', 5, 1, 1, '2022-05-01 13:35:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tagged_truck`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `tagged_truck` (`id`, `photo_url`, `food_truck_id`, `user_id`, `location_id`, `comment`, `date_tagged`) VALUES (1, 'google.com', 1, 1, 1, 'good food', '2000-05-01 13:35:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `schedule`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `schedule` (`id`, `arrival`, `departure`, `description`, `food_truck_id`, `location_id`) VALUES (1, '2022-05-01 13:35:00', '2022-05-01 20:35:00', 'Good deal', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `favorites`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `favorites` (`food_truck_id`, `user_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `request`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `request` (`id`, `remarks`, `user_id`, `food_truck_id`, `location_id`, `request_placed`, `requested_date`, `accepted`) VALUES (1, 'Please don\'t park by the fire hydrant', 1, 1, 1, '2022-05-01 13:35:00', '2022-06-01 13:35:00', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `food_truck_has_food_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `food_truck_has_food_category` (`food_truck_id`, `food_category_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `festival`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `festival` (`id`, `user_id`, `name`, `location_id`, `festival_date`, `start_time`, `description`, `img_url`, `created_date`) VALUES (1, 1, 'Beers, Brats, Battlestar Galactica Fest', 1, '2022-07-01', '13:35:00', 'Taco Truck Mania', 'google.com', '2022-04-01 13:35:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `festival_has_food_truck`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `festival_has_food_truck` (`festival_id`, `food_truck_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_food_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `user_has_food_category` (`user_id`, `food_category_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `order`
-- -----------------------------------------------------
START TRANSACTION;
USE `foodtruckdb`;
INSERT INTO `order` (`id`, `ordered_date`, `special_requests`, `user_id`, `menu_item_id`, `completed`, `pickup_date`) VALUES (1, '2022-05-01 13:35:00', 'No pickles', 1, 1, 1, '2022-05-01 15:35:00');

COMMIT;

