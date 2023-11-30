-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Hotel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Hotel` (
  `idHotel` INT NOT NULL AUTO_INCREMENT,
  `nomeHotel` VARCHAR(45) NOT NULL,
  `qntQuartos` INT NULL,
  PRIMARY KEY (`idHotel`),
  UNIQUE INDEX `idHotel_UNIQUE` (`idHotel` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Quarto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Quarto` (
  `idQuarto` INT NOT NULL AUTO_INCREMENT,
  `tipoQuarto` VARCHAR(45) NULL,
  `Hotel_idHotel` INT NOT NULL,
  PRIMARY KEY (`idQuarto`),
  INDEX `fk_Quarto_Hotel1_idx` (`Hotel_idHotel` ASC) VISIBLE,
  CONSTRAINT `fk_Quarto_Hotel1`
    FOREIGN KEY (`Hotel_idHotel`)
    REFERENCES `mydb`.`Hotel` (`idHotel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Pessoa` (
  `idPessoa` INT NOT NULL AUTO_INCREMENT,
  `nomePessoa` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(45) NOT NULL,
  `idade` VARCHAR(45) NOT NULL,
  `ocupacao` VARCHAR(45) NULL,
  PRIMARY KEY (`idPessoa`),
  UNIQUE INDEX `idPessoas_UNIQUE` (`idPessoa` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pessoa_has_Hotel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Pessoa_has_Hotel` (
  `Pessoa_idPessoa` INT NOT NULL,
  `Hotel_idHotel` INT NOT NULL,
  PRIMARY KEY (`Pessoa_idPessoa`, `Hotel_idHotel`),
  INDEX `fk_Pessoa_has_Hotel_Hotel1_idx` (`Hotel_idHotel` ASC) VISIBLE,
  INDEX `fk_Pessoa_has_Hotel_Pessoa1_idx` (`Pessoa_idPessoa` ASC) VISIBLE,
  CONSTRAINT `fk_Pessoa_has_Hotel_Pessoa1`
    FOREIGN KEY (`Pessoa_idPessoa`)
    REFERENCES `mydb`.`Pessoa` (`idPessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pessoa_has_Hotel_Hotel1`
    FOREIGN KEY (`Hotel_idHotel`)
    REFERENCES `mydb`.`Hotel` (`idHotel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

SELECT * FROM PESSOA;
SELECT * FROM HOTEL;
SELECT * FROM QUARTO;