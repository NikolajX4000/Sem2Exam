-- MySQL Workbench Synchronization
-- Generated: 2018-05-08 10:00
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
  `placed` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
  `type` INT(11) NOT NULL COMMENT '0 = flat\n1 = raised',
  PRIMARY KEY (`roof_id`),
  UNIQUE INDEX `roof_id_UNIQUE` (`roof_id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_danish_ci;

CREATE TABLE IF NOT EXISTS `sem2exam`.`employees` (
  `employee_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `sem2exam`.`materials` (
  `plank_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` INT(11) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `size` INT(11) NOT NULL,
  PRIMARY KEY (`plank_id`),
  UNIQUE INDEX `plank_id_UNIQUE` (`plank_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `sem2exam`.`tools` (
  `tool_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `unit_size` INT(11) NOT NULL DEFAULT 1,
  `price` INT(11) NOT NULL,
  PRIMARY KEY (`tool_id`),
  UNIQUE INDEX `tool_id_UNIQUE` (`tool_id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

/*dummy orders*/
/*
INSERT INTO orders (name,address,zip_code,city,phone,email,note,width,length,roof_id,angle,shed_width,shed_length,placed,price) VALUES ('Dummy','Dummy 1',1234,'Dummy',12345678,'dummy@dummy.dummy','Dummy',240,240,2,0,0,0,CURRENT_TIME(),2.40*2.40*300);
INSERT INTO orders (name,address,zip_code,city,phone,email,note,width,length,roof_id,angle,shed_width,shed_length,placed,price) VALUES ('Dummy','Dummy 1',1234,'Dummy',12345678,'dummy@dummy.dummy','Dummy',240,240,1,25,0,0,CURRENT_TIME(),2.40*2.40*666);
*/

/*materials*/
/*
INSERT INTO materials (name,price,description,size) VALUES('overstern',32,'25x125mm. trykimp. Bræt',300);
INSERT INTO materials (name,price,description,size) VALUES('overstern',32,'25x125mm. trykimp. Bræt',360);
INSERT INTO materials (name,price,description,size) VALUES('overstern',32,'25x125mm. trykimp. Bræt',420);
INSERT INTO materials (name,price,description,size) VALUES('overstern',32,'25x125mm. trykimp. Bræt',480);
INSERT INTO materials (name,price,description,size) VALUES('overstern',32,'25x125mm. trykimp. Bræt',540);
INSERT INTO materials (name,price,description,size) VALUES('overstern',32,'25x125mm. trykimp. Bræt',600);
INSERT INTO materials (name,price,description,size) VALUES('understern',55,'25x200mm. trykimp. Bræt',300);
INSERT INTO materials (name,price,description,size) VALUES('understern',55,'25x200mm. trykimp. Bræt',360);
INSERT INTO materials (name,price,description,size) VALUES('understern',55,'25x200mm. trykimp. Bræt',420);
INSERT INTO materials (name,price,description,size) VALUES('understern',55,'25x200mm. trykimp. Bræt',480);
INSERT INTO materials (name,price,description,size) VALUES('understern',55,'25x200mm. trykimp. Bræt',540);
INSERT INTO materials (name,price,description,size) VALUES('understern',55,'25x200mm. trykimp. Bræt',600);
INSERT INTO materials (name,price,description,size) VALUES('rem',39,'45x195 mm. spærtræ ubh.',300);
INSERT INTO materials (name,price,description,size) VALUES('rem',39,'45x195 mm. spærtræ ubh.',360);
INSERT INTO materials (name,price,description,size) VALUES('rem',39,'45x195 mm. spærtræ ubh.',420);
INSERT INTO materials (name,price,description,size) VALUES('rem',39,'45x195 mm. spærtræ ubh.',480);
INSERT INTO materials (name,price,description,size) VALUES('rem',39,'45x195 mm. spærtræ ubh.',540);
INSERT INTO materials (name,price,description,size) VALUES('rem',39,'45x195 mm. spærtræ ubh.',600);
INSERT INTO materials (name,price,description,size) VALUES('rem',39,'45x195 mm. spærtræ ubh.',660);
INSERT INTO materials (name,price,description,size) VALUES('rem',39,'45x195 mm. spærtræ ubh.',720);
INSERT INTO materials (name,price,description,size) VALUES('spær',39,'45x195 mm. spærtræ ubh.',300);
INSERT INTO materials (name,price,description,size) VALUES('spær',39,'45x195 mm. spærtræ ubh.',360);
INSERT INTO materials (name,price,description,size) VALUES('spær',39,'45x195 mm. spærtræ ubh.',420);
INSERT INTO materials (name,price,description,size) VALUES('spær',39,'45x195 mm. spærtræ ubh.',480);
INSERT INTO materials (name,price,description,size) VALUES('spær',39,'45x195 mm. spærtræ ubh.',540);
INSERT INTO materials (name,price,description,size) VALUES('spær',39,'45x195 mm. spærtræ ubh.',600);
INSERT INTO materials (name,price,description,size) VALUES('spær',39,'45x195 mm. spærtræ ubh.',660);
INSERT INTO materials (name,price,description,size) VALUES('spær',39,'45x195 mm. spærtræ ubh.',720);
INSERT INTO materials (name,price,description,size) VALUES('stolpe',35,'97x97 mm. trykimp. Stolpe',180);
INSERT INTO materials (name,price,description,size) VALUES('stolpe',35,'97x97 mm. trykimp. Stolpe',210);
INSERT INTO materials (name,price,description,size) VALUES('stolpe',35,'97x97 mm. trykimp. Stolpe',240);
INSERT INTO materials (name,price,description,size) VALUES('stolpe',35,'97x97 mm. trykimp. Stolpe',270);
INSERT INTO materials (name,price,description,size) VALUES('stolpe',35,'97x97 mm. trykimp. Stolpe',300);
INSERT INTO materials (name,price,description,size) VALUES('stolpe',35,'97x97 mm. trykimp. Stolpe',360);
INSERT INTO materials (name,price,description,size) VALUES('stolpe',35,'97x97 mm. trykimp. Stolpe',420);
INSERT INTO materials (name,price,description,size) VALUES('stolpe',35,'97x97 mm. trykimp. Stolpe',480);
INSERT INTO materials (name,price,description,size) VALUES('vandbræt',21,'19x100 mm. trykimp. Bræt',300);
INSERT INTO materials (name,price,description,size) VALUES('vandbræt',21,'19x100 mm. trykimp. Bræt',360);
INSERT INTO materials (name,price,description,size) VALUES('vandbræt',21,'19x100 mm. trykimp. Bræt',420);
INSERT INTO materials (name,price,description,size) VALUES('vandbræt',21,'19x100 mm. trykimp. Bræt',480);
INSERT INTO materials (name,price,description,size) VALUES('tagplade',40,'Plastmo Ecolite blåtonet',240);
INSERT INTO materials (name,price,description,size) VALUES('tagplade',40,'Plastmo Ecolite blåtonet',300);
INSERT INTO materials (name,price,description,size) VALUES('tagplade',40,'Plastmo Ecolite blåtonet',360);
INSERT INTO materials (name,price,description,size) VALUES('tagplade',40,'Plastmo Ecolite blåtonet',420);
INSERT INTO materials (name,price,description,size) VALUES('tagplade',40,'Plastmo Ecolite blåtonet',480);
INSERT INTO materials (name,price,description,size) VALUES('tagplade',40,'Plastmo Ecolite blåtonet',600);
INSERT INTO materials (name,price,description,size) VALUES('løsholte',16,'45x95 mm. Reglar ub.',240);
INSERT INTO materials (name,price,description,size) VALUES('løsholte',16,'45x95 mm. Reglar ub.',270);
INSERT INTO materials (name,price,description,size) VALUES('løsholte',16,'45x95 mm. Reglar ub.',300);
INSERT INTO materials (name,price,description,size) VALUES('løsholte',16,'45x95 mm. Reglar ub.',330);
INSERT INTO materials (name,price,description,size) VALUES('løsholte',16,'45x95 mm. Reglar ub.',360);
INSERT INTO materials (name,price,description,size) VALUES('løsholte',16,'45x95 mm. Reglar ub.',390);
INSERT INTO materials (name,price,description,size) VALUES('løsholte',16,'45x95 mm. Reglar ub.',420);
INSERT INTO materials (name,price,description,size) VALUES('løsholte',16,'45x95 mm. Reglar ub.',450);
INSERT INTO materials (name,price,description,size) VALUES('løsholte',16,'45x95 mm. Reglar ub.',480);
INSERT INTO materials (name,price,description,size) VALUES('løsholte',16,'45x95 mm. Reglar ub.',510);
INSERT INTO materials (name,price,description,size) VALUES('løsholte',16,'45x95 mm. Reglar ub.',540);
INSERT INTO materials (name,price,description,size) VALUES('beklædning',21,'19x100 mm. trykimp. Bræt',210);
INSERT INTO materials (name,price,description,size) VALUES('beklædning',21,'19x100 mm. trykimp. Bræt',240);
INSERT INTO materials (name,price,description,size) VALUES('beklædning',21,'19x100 mm. trykimp. Bræt',270);
INSERT INTO materials (name,price,description,size) VALUES('beklædning',21,'19x100 mm. trykimp. Bræt',300);
INSERT INTO materials (name,price,description,size) VALUES('beklædning',21,'19x100 mm. trykimp. Bræt',360);
INSERT INTO materials (name,price,description,size) VALUES('beklædning',21,'19x100 mm. trykimp. Bræt',420);
INSERT INTO materials (name,price,description,size) VALUES('beklædning',21,'19x100 mm. trykimp. Bræt',480);
INSERT INTO materials (name,price,description,size) VALUES('z til dør',23,'38x73 mm. Lægte ubh.',420);
INSERT INTO materials (name,price,description,size) VALUES('z til dør',23,'38x73 mm. Lægte ubh.',540);
INSERT INTO materials (name,price,description,size) VALUES('taglægte',23,'38x73 mm. Lægte ubh.',360);
INSERT INTO materials (name,price,description,size) VALUES('taglægte',23,'38x73 mm. Lægte ubh.',420);
INSERT INTO materials (name,price,description,size) VALUES('taglægte',23,'38x73 mm. Lægte ubh.',480);
INSERT INTO materials (name,price,description,size) VALUES('taglægte',23,'38x73 mm. Lægte ubh.',540);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Betontagsten - Rød',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Betontagsten - Teglrød',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Betontagsten - Brun',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Betontagsten - Sort',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Eternittag B6 - Grå',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Eternittag B6 - Sort',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Eternittag B6 - Mokka(brun)',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Eternittag B6 - Rødbrun',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Eternittag B6 - Teglrød',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Eternittag B7 - Grå',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Eternittag B7 - Sort',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Eternittag B7 - Mokka(brun)',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Eternittag B7 - Rødbrun',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Eternittag B7 - Teglrød',0);
INSERT INTO materials (name,price,description,size) VALUES('tagsten',15,'Eternittag B7 - Rødflammet',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Betontagsten - Rød',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Betontagsten - Teglrød',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Betontagsten - Brun',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Betontagsten - Sort',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Eternittag B6 - Grå',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Eternittag B6 - Sort',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Eternittag B6 - Mokka(brun)',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Eternittag B6 - Rødbrun',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Eternittag B6 - Teglrød',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Eternittag B7 - Grå',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Eternittag B7 - Sort',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Eternittag B7 - Mokka(brun)',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Eternittag B7 - Rødbrun',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Eternittag B7 - Teglrød',0);
INSERT INTO materials (name,price,description,size) VALUES('rygsten',15,'Eternittag B7 - Rødflammet',0);
INSERT INTO materials (name,price,description,size) VALUES('oven på tagfodslægte',18,'25x50 mm. trykimp. Bræt',210);
INSERT INTO materials (name,price,description,size) VALUES('oven på tagfodslægte',18,'25x50 mm. trykimp. Bræt',240);
INSERT INTO materials (name,price,description,size) VALUES('oven på tagfodslægte',18,'25x50 mm. trykimp. Bræt',270);
INSERT INTO materials (name,price,description,size) VALUES('oven på tagfodslægte',18,'25x50 mm. trykimp. Bræt',300);
INSERT INTO materials (name,price,description,size) VALUES('oven på tagfodslægte',18,'25x50 mm. trykimp. Bræt',330);
INSERT INTO materials (name,price,description,size) VALUES('oven på tagfodslægte',18,'25x50 mm. trykimp. Bræt',360);
INSERT INTO materials (name,price,description,size) VALUES('oven på tagfodslægte',18,'25x50 mm. trykimp. Bræt',390);
INSERT INTO materials (name,price,description,size) VALUES('oven på tagfodslægte',18,'25x50 mm. trykimp. Bræt',420);
INSERT INTO materials (name,price,description,size) VALUES('oven på tagfodslægte',18,'25x50 mm. trykimp. Bræt',450);
INSERT INTO materials (name,price,description,size) VALUES('oven på tagfodslægte',18,'25x50 mm. trykimp. Bræt',480);
INSERT INTO materials (name,price,description,size) VALUES('oven på tagfodslægte',18,'25x50 mm. trykimp. Bræt',510);
INSERT INTO materials (name,price,description,size) VALUES('oven på tagfodslægte',18,'25x50 mm. trykimp. Bræt',540);
INSERT INTO materials (name,price,description,size) VALUES('stern',32,'25x150mm. trykimp. Bræt',300);
INSERT INTO materials (name,price,description,size) VALUES('stern',32,'25x150mm. trykimp. Bræt',360);
INSERT INTO materials (name,price,description,size) VALUES('stern',32,'25x150mm. trykimp. Bræt',420);
INSERT INTO materials (name,price,description,size) VALUES('stern',32,'25x150mm. trykimp. Bræt',480);
INSERT INTO materials (name,price,description,size) VALUES('stern',32,'25x150mm. trykimp. Bræt',540);
INSERT INTO materials (name,price,description,size) VALUES('stern',32,'25x150mm. trykimp. Bræt',600);
INSERT INTO materials (name,price,description,size) VALUES('vindskede',32,'25x150mm. trykimp. Bræt',300);
INSERT INTO materials (name,price,description,size) VALUES('vindskede',32,'25x150mm. trykimp. Bræt',360);
INSERT INTO materials (name,price,description,size) VALUES('vindskede',32,'25x150mm. trykimp. Bræt',420);
INSERT INTO materials (name,price,description,size) VALUES('vindskede',32,'25x150mm. trykimp. Bræt',480);
INSERT INTO materials (name,price,description,size) VALUES('vindskede',32,'25x150mm. trykimp. Bræt',540);
INSERT INTO materials (name,price,description,size) VALUES('vindskede',32,'25x150mm. trykimp. Bræt',600);
*/

/*roofs*/
/*
INSERT INTO roofs (name, type) VALUES ('Plasttrapezplader', 0);
INSERT INTO roofs (name, type) VALUES ('Betontagsten - Rød', 1);
INSERT INTO roofs (name, type) VALUES ('Betontagsten - Teglrød', 1);
INSERT INTO roofs (name, type) VALUES ('Betontagsten - Brun', 1);
INSERT INTO roofs (name, type) VALUES ('Betontagsten - Sort', 1);
INSERT INTO roofs (name, type) VALUES ('Eternittag B6 - Grå', 1);
INSERT INTO roofs (name, type) VALUES ('Eternittag B6 - Sort', 1);
INSERT INTO roofs (name, type) VALUES ('Eternittag B6 - Mokka(brun)', 1);
INSERT INTO roofs (name, type) VALUES ('Eternittag B6 - Rødbrun', 1);
INSERT INTO roofs (name, type) VALUES ('Eternittag B6 - Teglrød', 1);
INSERT INTO roofs (name, type) VALUES ('Eternittag B7 - Grå', 1);
INSERT INTO roofs (name, type) VALUES ('Eternittag B7 - Sort', 1);
INSERT INTO roofs (name, type) VALUES ('Eternittag B7 - Mokka(brun)', 1);
INSERT INTO roofs (name, type) VALUES ('Eternittag B7 - Rødbrun', 1);
INSERT INTO roofs (name, type) VALUES ('Eternittag B7 - Teglrød', 1);
INSERT INTO roofs (name, type) VALUES ('Eternittag B7 - Rødflammet', 1);
*/

/*tools*/
/*
INSERT INTO tools (name,unit_size,price) VALUES ('Plastmo Bundskruer',200,220);
INSERT INTO tools (name,unit_size,price) VALUES ('Hulbånd 1x20 mm.',10,209);
INSERT INTO tools (name,unit_size,price) VALUES ('Universal 190mm højre',1,18);
INSERT INTO tools (name,unit_size,price) VALUES ('Universal 190mm venstre',1,18);
INSERT INTO tools (name,unit_size,price) VALUES ('4,5x60 mm. skruer',200,159);
INSERT INTO tools (name,unit_size,price) VALUES ('4x50 mm. beslagskruer',250,269);
INSERT INTO tools (name,unit_size,price) VALUES ('Bræddebolte 10x120 mm.',1,19);
INSERT INTO tools (name,unit_size,price) VALUES ('Firkantskiver 40x40x11 mm.',1,10);
INSERT INTO tools (name,unit_size,price) VALUES ('4,5x70 mm. skruer',400,199);
INSERT INTO tools (name,unit_size,price) VALUES ('4,5x50 mm. skruer',300,69);
INSERT INTO tools (name,unit_size,price) VALUES ('Stalddørsgreb 50x75',1,189);
INSERT INTO tools (name,unit_size,price) VALUES ('T hængsel 390 mm.',1,120);
INSERT INTO tools (name,unit_size,price) VALUES ('Vinkelbeslag 35',1,6);
INSERT INTO tools (name,unit_size,price) VALUES ('B & C Toplægteholder',1,50);
INSERT INTO tools (name,unit_size,price) VALUES ('B & C rygstensbeslag',1,50);
INSERT INTO tools (name,unit_size,price) VALUES ('B & C tagstens bindere og nakkekroge',5,50);
INSERT INTO tools (name,unit_size,price) VALUES ('5x40 mm. beslagskruer',250,269);
INSERT INTO tools (name,unit_size,price) VALUES ('5x100 mm. skruer',100,100);
*/

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;