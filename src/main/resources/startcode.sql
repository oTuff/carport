-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `carport` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `carport` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
-- -----------------------------------------------------
-- Schema carport_test
-- -----------------------------------------------------
USE `carport`;
USE `carport`;

-- -----------------------------------------------------
-- Table `carport`.`zip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`zip`
(
    `zip_nr`    INT         NOT NULL,
    `city_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`zip_nr`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`user`
(
    `email`     VARCHAR(45) NOT NULL,
    `full_name` VARCHAR(45) NOT NULL,
    `password`  VARCHAR(45) NOT NULL,
    `balance`   INT         NOT NULL,
    `address`   VARCHAR(45) NOT NULL,
    `zip_nr`    INT         NOT NULL,
    `role`      VARCHAR(45) NOT NULL,
    PRIMARY KEY (`email`),
    INDEX `fk_user_zip1_idx` (`zip_nr` ASC) VISIBLE,
    CONSTRAINT `fk_user_zip1`
        FOREIGN KEY (`zip_nr`)
            REFERENCES `carport`.`zip` (`zip_nr`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `carport`.`partslist_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`partslist_order`
(
    `partslist_order_id` INT         NOT NULL AUTO_INCREMENT,
    `email`              VARCHAR(45) NOT NULL,
    `total_width`        INT         NOT NULL,
    `total_length`       INT         NOT NULL,
    `shed_width`         INT         NULL,
    `shed_length`        INT         NULL,
    `order_price`        INT         NOT NULL,
    PRIMARY KEY (`partslist_order_id`),
    INDEX `fk_partslist_order_user1_idx` (`email` ASC) VISIBLE,
    CONSTRAINT `fk_partslist_order_user1`
        FOREIGN KEY (`email`)
            REFERENCES `carport`.`user` (`email`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `carport`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`product`
(
    `product_id`    INT         NOT NULL AUTO_INCREMENT,
    `product_name`  VARCHAR(45) NOT NULL,
    `product_price` INT         NOT NULL,
    PRIMARY KEY (`product_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`partslist_line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `carport`.`partslist_line`
(
    `partslist_line_id`  INT          NOT NULL AUTO_INCREMENT,
    `product_id`         INT          NOT NULL,
    `partslist_order_id` INT          NOT NULL,
    `product_length`     INT          NOT NULL,
    `quantity`           INT          NOT NULL,
    `unit`               VARCHAR(45)  NOT NULL,
    `parts_price`        INT          NOT NULL,
    `description`        VARCHAR(150) NOT NULL,
    INDEX `fk_partslist_line_product1_idx` (`product_id` ASC) VISIBLE,
    PRIMARY KEY (`partslist_line_id`),
    INDEX `fk_partslist_line_partslist_order1_idx` (`partslist_order_id` ASC) VISIBLE,
    CONSTRAINT `fk_partslist_line_product1`
        FOREIGN KEY (`product_id`)
            REFERENCES `carport`.`product` (`product_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_partslist_line_partslist_order1`
        FOREIGN KEY (`partslist_order_id`)
            REFERENCES `carport`.`partslist_order` (`partslist_order_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION

)
    ENGINE = InnoDB;

INSERT INTO `carport`.`user` (`email`, `full_name`, `password`, `balance`, `address`, `zip_nr`, `role`) VALUES ('a@a.dk', 'Adminbruger', '1234', '100000', 'Nørgaardsvej 30', '2800', 'admin');
INSERT INTO `carport`.`user` (`email`, `full_name`, `password`, `balance`, `address`, `zip_nr`, `role`) VALUES ('b@b.dk', 'Testbruger', '1234', '100000', 'Nørgaardsvej 30', '2800', 'user');



UNLOCK TABLES;
CREATE DATABASE IF NOT EXISTS `carport_test`;
USE `carport_test`;
CREATE TABLE carport_test.user LIKE carport.user;
CREATE TABLE carport_test.zip LIKE carport.zip;
CREATE TABLE carport_test.partslist_line LIKE carport.partslist_line;
CREATE TABLE carport_test.partslist_order LIKE carport.partslist_order;
CREATE TABLE carport_test.product LIKE carport.product;



SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;