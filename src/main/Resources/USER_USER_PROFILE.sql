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
-- Table `mydb`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USER` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `SECOND_NAME` VARCHAR(45) NULL,
  `FAMILY_NAME` VARCHAR(45) NULL,
  `EMAIL` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`USER_PROFILE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USER_PROFILE` (
  `ID` INT NOT NULL,
  `TYPE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`USER_has_USER_PROFILE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USER_has_USER_PROFILE` (
  `USER_ID` INT NOT NULL,
  `USER_PROFILE_ID` INT NOT NULL,
  PRIMARY KEY (`USER_ID`, `USER_PROFILE_ID`),
  INDEX `fk_USER_has_USER_PROFILE_USER_PROFILE1_idx` (`USER_PROFILE_ID` ASC),
  INDEX `fk_USER_has_USER_PROFILE_USER_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_USER_has_USER_PROFILE_USER`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `mydb`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_has_USER_PROFILE_USER_PROFILE1`
    FOREIGN KEY (`USER_PROFILE_ID`)
    REFERENCES `mydb`.`USER_PROFILE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;