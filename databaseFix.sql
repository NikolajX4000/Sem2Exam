-- MySQL Workbench Synchronization
-- Generated: 2018-06-05 13:02
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: super

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER SCHEMA `sem2examFix`  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci ;

CREATE TABLE IF NOT EXISTS `sem2examFix`.`orders` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `zip_code` INT(11) NOT NULL,
  `city` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `note` VARCHAR(500) NULL DEFAULT NULL,
  `width` INT(11) NOT NULL,
  `length` INT(11) NOT NULL,
  `angle` INT(11) NULL DEFAULT NULL,
  `shed_width` INT(11) NULL DEFAULT NULL,
  `shed_length` INT(11) NULL DEFAULT NULL,
  `placed` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` VARCHAR(100) NOT NULL DEFAULT 'Behandles',
  `material_price` INT(11) NOT NULL,
  `price` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `order_id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `sem2examFix`.`employees` (
  `employee_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `sem2examFix`.`materials` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `types_id` INT(11) NOT NULL,
  `price` INT(11) NOT NULL,
  `size` INT(11) NOT NULL DEFAULT 0,
  `description` VARCHAR(100) NOT NULL,
  `amount` INT(11) NOT NULL DEFAULT 1,
  `active` INT(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`, `types_id`),
  UNIQUE INDEX `plank_id_UNIQUE` (`id` ASC),
  INDEX `fk_materials_types_idx` (`types_id` ASC),
  CONSTRAINT `fk_materials_types`
    FOREIGN KEY (`types_id`)
    REFERENCES `sem2examFix`.`types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `sem2examFix`.`types` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `sem2examFix`.`partlists` (
  `orders_id` INT(11) NOT NULL,
  `materials_id` INT(11) NOT NULL,
  `amount` INT(11) NOT NULL,
  PRIMARY KEY (`orders_id`, `materials_id`),
  INDEX `fk_orders_has_materials_materials1_idx` (`materials_id` ASC),
  INDEX `fk_orders_has_materials_orders1_idx` (`orders_id` ASC),
  CONSTRAINT `fk_orders_has_materials_orders1`
    FOREIGN KEY (`orders_id`)
    REFERENCES `sem2examFix`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_has_materials_materials1`
    FOREIGN KEY (`materials_id`)
    REFERENCES `sem2examFix`.`materials` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
