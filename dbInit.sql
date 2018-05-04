-- MySQL Workbench Synchronization
-- Generated: 2018-05-03 19:17
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: super

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE TABLE IF NOT EXISTS `sem2exam`.`orders` (
  `order_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `zip_code` INT(11) NOT NULL,
  `city` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `note` VARCHAR(500) NULL DEFAULT NULL,
  `width` INT(11) NOT NULL,
  `length` INT(11) NOT NULL,
  `roof_id` INT(11) NOT NULL,
  `angle` INT(11) NULL DEFAULT NULL,
  `shed_width` INT(11) NULL DEFAULT NULL,
  `shed_length` INT(11) NULL DEFAULT NULL,
  `placed` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `status` VARCHAR(100) NOT NULL DEFAULT 'Behandles',
  `price` INT(11) NOT NULL,
  PRIMARY KEY (`order_id`, `roof_id`),
  UNIQUE INDEX `order_id_UNIQUE` (`order_id` ASC),
  INDEX `fk_orders_roofs_idx` (`roof_id` ASC),
  CONSTRAINT `fk_orders_roofs`
    FOREIGN KEY (`roof_id`)
    REFERENCES `sem2exam`.`roofs` (`roof_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `sem2exam`.`roofs` (
  `roof_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` INT(11) NOT NULL,
  `type` INT(11) NOT NULL COMMENT '0 = flatt\n1 = raised',
  PRIMARY KEY (`roof_id`),
  UNIQUE INDEX `roof_id_UNIQUE` (`roof_id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;

CREATE TABLE IF NOT EXISTS `sem2exam`.`employees` (
  `employee_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `password` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `sem2exam`.`sizes` (
  `size_id` INT(11) NOT NULL AUTO_INCREMENT,
  `size` INT(11) NOT NULL,
  PRIMARY KEY (`size_id`),
  UNIQUE INDEX `size_id_UNIQUE` (`size_id` ASC),
  UNIQUE INDEX `size_UNIQUE` (`size` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `sem2exam`.`planks` (
  `plank_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` INT(11) NOT NULL,
  PRIMARY KEY (`plank_id`),
  UNIQUE INDEX `plank_id_UNIQUE` (`plank_id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

/*roofs*/
/*INSERT INTO roofs (name, price, type) VALUES ('Plasttrapezplader', 40, 0);
INSERT INTO roofs (name, price, type) VALUES ('Betontagsten - Rød', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Betontagsten - Teglrød', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Betontagsten - Brun', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Betontagsten - Sort', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Eternittag B6 - Grå', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Eternittag B6 - Sort', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Eternittag B6 - Mokka(brun)', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Eternittag B6 - Rødbrun', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Eternittag B6 - Teglrød', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Eternittag B7 - Grå', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Eternittag B7 - Sort', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Eternittag B7 - Mokka(brun)', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Eternittag B7 - Rødbrun', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Eternittag B7 - Teglrød', 40, 1);
INSERT INTO roofs (name, price, type) VALUES ('Eternittag B7 - Rødflammet', 40, 1);*/

/*planks*/
/*INSERT INTO planks (name, price) VALUES ('25x200 mm. trykimp. Bræt', 55);
INSERT INTO planks (name, price) VALUES ('25x125 mm. trykimp. Bræt', 32);
INSERT INTO planks (name, price) VALUES ('38x73 mm. Lægte ubh.', 23);
INSERT INTO planks (name, price) VALUES ('45x95 mm. Reglar ubh.', 16);
INSERT INTO planks (name, price) VALUES ('45x195 mm. spærtræ ubh.', 39);
INSERT INTO planks (name, price) VALUES ('97x97 mm. trykimp. Stolpe', 35);
INSERT INTO planks (name, price) VALUES ('19x100 mm. trykimp. Bræt', 21);
INSERT INTO planks (name, price) VALUES ('Plastmo Ecolite blåtonet', 40);*/

/*sizes*/
/*INSERT INTO sizes (size) VALUES (180);
INSERT INTO sizes (size) VALUES (210);
INSERT INTO sizes (size) VALUES (240);
INSERT INTO sizes (size) VALUES (270);
INSERT INTO sizes (size) VALUES (300);
INSERT INTO sizes (size) VALUES (330);
INSERT INTO sizes (size) VALUES (360);
INSERT INTO sizes (size) VALUES (390);
INSERT INTO sizes (size) VALUES (420);
INSERT INTO sizes (size) VALUES (450);
INSERT INTO sizes (size) VALUES (480);
INSERT INTO sizes (size) VALUES (510);
INSERT INTO sizes (size) VALUES (540);
INSERT INTO sizes (size) VALUES (570);
INSERT INTO sizes (size) VALUES (600);
INSERT INTO sizes (size) VALUES (630);
INSERT INTO sizes (size) VALUES (660);
INSERT INTO sizes (size) VALUES (690);
INSERT INTO sizes (size) VALUES (720);*/

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
