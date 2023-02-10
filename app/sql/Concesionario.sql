-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema concesionario
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema concesionario
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `concesionario` ;
USE `concesionario` ;

-- -----------------------------------------------------
-- Table `concesionario`.`tipos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concesionario`.`tipos` (
  `idTipo` INT NOT NULL,
  `Tipo` VARCHAR(5) COLLATE 'utf8mb3_spanish2_ci' NOT NULL,
  PRIMARY KEY (`idTipo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concesionario`.`vehiculos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concesionario`.`vehiculos` (
  `Matricula` VARCHAR(7) COLLATE 'utf8mb3_spanish2_ci' NOT NULL,
  `Modelo` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NOT NULL,
  `Marca` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NOT NULL,
  `Caballos` INT NOT NULL,
  `Precio` DECIMAL(8,2) NOT NULL,
  `Tipos_id` INT NOT NULL,
  `foto` MEDIUMBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`Matricula`),
  INDEX `fk_Vehiculos_Tipos_idx` (`Tipos_id` ASC) VISIBLE,
  CONSTRAINT `fk_Vehiculos_Tipos`
    FOREIGN KEY (`Tipos_id`)
    REFERENCES `concesionario`.`tipos` (`idTipo`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
