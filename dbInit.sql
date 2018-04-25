-- MySQL Workbench Synchronization
-- Generated: 2018-04-25 14:20
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
  `placed` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `status` VARCHAR(100) NULL DEFAULT 'Behandles',
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
  `type` INT(11) NULL DEFAULT NULL,
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
