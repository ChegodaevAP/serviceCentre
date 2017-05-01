-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`client` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NULL DEFAULT NULL,
  `MIDLE_NAME` VARCHAR(255) NULL DEFAULT NULL,
  `SURNAME` VARCHAR(255) NULL DEFAULT NULL,
  `ADDRESS` VARCHAR(255) NULL DEFAULT NULL,
  `PHONE` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`defect`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`defect` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`request` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `client_ID` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `client_ID`),
  INDEX `fk_order_client1_idx` (`client_ID` ASC),
  CONSTRAINT `fk_order_client1`
  FOREIGN KEY (`client_ID`)
  REFERENCES `mydb`.`client` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 40
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`defect_request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`defect_request` (
  `defect_id` INT(11) NOT NULL,
  `request_id` INT(11) NOT NULL,
  PRIMARY KEY (`defect_id`, `request_id`),
  INDEX `fk_defect_has_request_request1_idx` (`request_id` ASC),
  INDEX `fk_defect_has_request_defect1_idx` (`defect_id` ASC),
  CONSTRAINT `fk_defect_has_request_defect1`
  FOREIGN KEY (`defect_id`)
  REFERENCES `mydb`.`defect` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_defect_has_request_request1`
  FOREIGN KEY (`request_id`)
  REFERENCES `mydb`.`request` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`place`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`place` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS` VARCHAR(255) NOT NULL,
  `lat` FLOAT NULL,
  `lng` FLOAT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `ADDRESS_UNIQUE` (`ADDRESS` ASC))
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`movement_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`movement_history` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `DATE` DATE NOT NULL,
  `place_id` INT(11) NOT NULL,
  `request_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `place_id`, `request_id`),
  INDEX `fk_movement_history_place1_idx` (`place_id` ASC),
  INDEX `fk_movement_history_request1_idx` (`request_id` ASC),
  CONSTRAINT `fk_movement_history_place1`
  FOREIGN KEY (`place_id`)
  REFERENCES `mydb`.`place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movement_history_request1`
  FOREIGN KEY (`request_id`)
  REFERENCES `mydb`.`request` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 38
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`reports`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`reports` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `file` BLOB NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(255) NOT NULL,
  `SECOND_NAME` VARCHAR(45) NULL DEFAULT NULL,
  `FAMILY_NAME` VARCHAR(45) NULL DEFAULT NULL,
  `EMAIL` VARCHAR(45) NULL DEFAULT NULL,
  `place_id` INT(11) NOT NULL,
  PRIMARY KEY (`ID`, `place_id`),
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  INDEX `fk_user_place1_idx` (`place_id` ASC),
  CONSTRAINT `fk_user_place1`
  FOREIGN KEY (`place_id`)
  REFERENCES `mydb`.`place` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`request_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`request_user` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DATE` DATE NOT NULL,
  `user_ID` INT(11) NOT NULL,
  `request_id` INT(11) NOT NULL,
  PRIMARY KEY (`ID`, `user_ID`, `request_id`),
  INDEX `fk_order_user_user1_idx` (`user_ID` ASC),
  INDEX `fk_request_user_request1_idx` (`request_id` ASC),
  CONSTRAINT `fk_order_user_user1`
  FOREIGN KEY (`user_ID`)
  REFERENCES `mydb`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_user_request1`
  FOREIGN KEY (`request_id`)
  REFERENCES `mydb`.`request` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 38
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`status` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `STATUS` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`status_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`status_history` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DATE` DATE NULL DEFAULT NULL,
  `status_ID` INT(11) NOT NULL,
  `request_id` INT(11) NOT NULL,
  PRIMARY KEY (`ID`, `status_ID`, `request_id`),
  INDEX `fk_status_history_status1_idx` (`status_ID` ASC),
  INDEX `fk_status_history_request1_idx` (`request_id` ASC),
  CONSTRAINT `fk_status_history_request1`
  FOREIGN KEY (`request_id`)
  REFERENCES `mydb`.`request` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_status_history_status1`
  FOREIGN KEY (`status_ID`)
  REFERENCES `mydb`.`status` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 37
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user_profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user_profile` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `TYPE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user_has_user_profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user_has_user_profile` (
  `USER_ID` INT(11) NOT NULL,
  `USER_PROFILE_ID` INT(11) NOT NULL,
  PRIMARY KEY (`USER_ID`, `USER_PROFILE_ID`),
  INDEX `fk_USER_has_USER_PROFILE_USER_PROFILE1_idx` (`USER_PROFILE_ID` ASC),
  INDEX `fk_USER_has_USER_PROFILE_USER_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_USER_has_USER_PROFILE_USER`
  FOREIGN KEY (`USER_ID`)
  REFERENCES `mydb`.`user` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_has_USER_PROFILE_USER_PROFILE1`
  FOREIGN KEY (`USER_PROFILE_ID`)
  REFERENCES `mydb`.`user_profile` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



