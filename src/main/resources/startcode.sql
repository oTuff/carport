-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `carport`;

-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `carport` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `carport`;

-- -----------------------------------------------------
-- Schema carport
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `carport` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
-- -----------------------------------------------------
-- Schema carport_test
-- -----------------------------------------------------
USE `carport`;

-- -----------------------------------------------------
-- Table `carport`.`shed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`shed`;

CREATE TABLE IF NOT EXISTS `carport`.`shed`
(
    `shed_id`     INT NOT NULL AUTO_INCREMENT,
    `shed_width`  INT NOT NULL,
    `shed_length` INT NOT NULL,
    PRIMARY KEY (`shed_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`unit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`unit`;

CREATE TABLE IF NOT EXISTS `carport`.`unit`
(
    `unit_id`   INT         NOT NULL AUTO_INCREMENT,
    `unit_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`unit_id`)
)
    ENGINE = InnoDB;

USE `carport`;

-- -----------------------------------------------------
-- Table `carport`.`zip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`zip`;

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
DROP TABLE IF EXISTS `carport`.`user`;

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
DROP TABLE IF EXISTS `carport`.`partslist_order`;

CREATE TABLE IF NOT EXISTS `carport`.`partslist_order`
(
    `partslist_order_id` INT         NOT NULL AUTO_INCREMENT,
    `email`              VARCHAR(45) NOT NULL,
    `total_width`        INT         NOT NULL,
    `total_length`       INT         NOT NULL,
    `order_price`        INT         NOT NULL,
    `shed_id`            INT         NULL,
    `accepted`           TINYINT     NULL,
    PRIMARY KEY (`partslist_order_id`),
    INDEX `fk_partslist_order_user1_idx` (`email` ASC) VISIBLE,
    INDEX `fk_partslist_order_shed1_idx` (`shed_id` ASC) VISIBLE,
    CONSTRAINT `fk_partslist_order_user1`
        FOREIGN KEY (`email`)
            REFERENCES `carport`.`user` (`email`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_partslist_order_shed1`
        FOREIGN KEY (`shed_id`)
            REFERENCES `carport`.`shed` (`shed_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`product`;

CREATE TABLE IF NOT EXISTS `carport`.`product`
(
    `product_id`    INT         NOT NULL AUTO_INCREMENT,
    `product_name`  VARCHAR(45) NOT NULL,
    `product_price` INT         NOT NULL,
    `unit_id`       INT         NOT NULL,
    PRIMARY KEY (`product_id`),
    INDEX `fk_product_unit1_idx` (`unit_id` ASC) VISIBLE,
    CONSTRAINT `fk_product_unit1`
        FOREIGN KEY (`unit_id`)
            REFERENCES `carport`.`unit` (`unit_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `carport`.`partslist_line`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carport`.`partslist_line`;

CREATE TABLE IF NOT EXISTS `carport`.`partslist_line`
(
    `partslist_line_id`  INT          NOT NULL AUTO_INCREMENT,
    `product_id`         INT          NOT NULL,
    `partslist_order_id` INT          NOT NULL,
    `product_length`     INT          NOT NULL,
    `quantity`           INT          NOT NULL,
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


INSERT INTO `carport`.`user` (`email`, `full_name`, `password`, `balance`, `address`, `zip_nr`, `role`)
VALUES ('a@a.dk', 'Adminbruger', '1234', '100000', 'Nørgaardsvej 30', '2800', 'admin');
INSERT INTO `carport`.`user` (`email`, `full_name`, `password`, `balance`, `address`, `zip_nr`, `role`)
VALUES ('b@b.dk', 'Testbruger', '1234', '100000', 'Nørgaardsvej 30', '2800', 'user');


INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3450,'Allerød');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3770,'Allinge');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2880,'Bagsværd');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2750,'Ballerup');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3460,'Birkerød');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2605,'Brøndby');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2660,'Brøndby Strand');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2700,'Brønshøj');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2920,'Charlottenlund');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2791,'Dragør');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3120,'Dronningmølle');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2870,'Dyssegård');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3060,'Espergærde');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3520,'Farum');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3480,'Fredensborg');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2000,'Frederiksberg');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3600,'Frederikssund');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3300,'Frederiksværk');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2820,'Gentofte');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3250,'Gilleleje');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2600,'Glostrup');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3230,'Græsted');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3760,'Gudhjem');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3330,'Gørløse');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3790,'Hasle');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2640,'Hedehusene');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3150,'Hellebæk');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2900,'Hellerup');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3200,'Helsinge');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3000,'Helsingør');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2730,'Herlev');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3400,'Hillerød');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2840,'Holte');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3100,'Hornbæk');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3050,'Humlebæk');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3390,'Hundested');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2650,'Hvidovre');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2970,'Hørsholm');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2635,'Ishøj');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3630,'Jægerspris');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2770,'Kastrup');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2930,'Klampenborg');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3782,'Klemensker');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2980,'Kokkedal');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2800,'Kongens Lyngby');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3490,'Kvistgård');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2200,'København N');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2400,'København NV');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2300,'København S');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2450,'København SV');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2100,'København ø');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3360,'Liseleje');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3540,'Lynge');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3370,'Melby');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2760,'Måløv');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3730,'Nexø');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2990,'Nivå');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2850,'Nærum');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2960,'Rungsted Kyst');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2610,'Rødovre');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3700,'Rønne');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (4050,'Skibby');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2942,'Skodsborg');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2740,'Skovlunde');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3320,'Skævinge');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3550,'Slangerup');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2765,'Smørum');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3070,'Snekkersten');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3660,'Stenløse');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3740,'Svaneke');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2860,'Søborg');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2630,'Taastrup');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3080,'Tikøb');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3220,'Tisvildeleje');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2500,'Valby');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2625,'Vallensbæk');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2665,'Vallensbæk Strand');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2720,'Vanløse');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2950,'Vedbæk');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3210,'Vejby');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3670,'Veksø Sjælland');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (2830,'Virum');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3500,'Værløse');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3310,'ølsted');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3650,'ølstykke');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3751,'østermarie');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3720,'Aakirkeby');
INSERT INTO `carport`.`zip`(zip_nr,city_name) VALUES (3140,'ålsgårde');




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


INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (100,'25x200 mm trykimp Brædt',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (75,'25x125mm trykimp Brædt',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (20,'38x73 mm Lægte ubh',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (80,'45x95 mm Reglar ub',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (85,'45x195 mm spærtræ ubh',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (59,'97x97 mm trykimp Stolpe',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (50,'19x100 mm trykimp Brædt',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (48,'Plastmo Ecolite blåtonet',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (229,'plastmo bundskruer 200 stk',2);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (109,'hulbånd 1x20 mm 10 mtr',3);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (25,'universal 190 mm højre',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (25,'universal 190 mm venstre',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (200,'4,5 x 60 mm skruer 200 stk',2);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (175,'4,0 x 50 mm beslagskruer 250 stk',2);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (170,'bræddebolt 10 x 120 mm',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (126,'firkantskiver 40x40x11mm',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (229,'4,5 x 70 mm Skruer 400 stk',2);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (150,'4,5 x 50 mm Skruer 300 stk',2);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (169,'stalddørsgreb 50x75',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (79,'t hængsel 390 mm',1);
INSERT INTO `carport`.`product`(product_price,product_name,unit_id) VALUES (70,'vinkelbeslag 35',1);


INSERT INTO `carport`.`unit`(unit_name) VALUES ('Stk');
INSERT INTO `carport`.`unit`(unit_name) VALUES ('Pakke');
INSERT INTO `carport`.`unit`(unit_name) VALUES ('Rulle');

