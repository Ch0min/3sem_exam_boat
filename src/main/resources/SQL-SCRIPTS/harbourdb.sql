-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema startcode
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `startcode`;

-- -----------------------------------------------------
-- Schema startcode
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `startcode` DEFAULT CHARACTER SET utf8mb3;
USE `startcode`;

-- -----------------------------------------------------
-- Table `startcode`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `startcode`.`user`;

CREATE TABLE IF NOT EXISTS `startcode`.`user`
(
    `user_name`  VARCHAR(25)  NOT NULL,
    `user_email` VARCHAR(255) NULL,
    `user_pass`  VARCHAR(255) NULL,
    PRIMARY KEY (`user_name`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startcode`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `startcode`.`roles`;

CREATE TABLE IF NOT EXISTS `startcode`.`roles`
(
    `role_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`role_name`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startcode`.`user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `startcode`.`user_roles`;

CREATE TABLE IF NOT EXISTS `startcode`.`user_roles`
(
    `user_name` VARCHAR(25) NOT NULL,
    `role_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`user_name`, `role_name`),
    INDEX `fk_user_has_roles_roles1_idx` (`role_name` ASC) VISIBLE,
    INDEX `fk_user_has_roles_user_idx` (`user_name` ASC) VISIBLE,
    CONSTRAINT `fk_user_has_roles_user`
        FOREIGN KEY (`user_name`)
            REFERENCES `startcode`.`user` (`user_name`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_has_roles_roles1`
        FOREIGN KEY (`role_name`)
            REFERENCES `startcode`.`roles` (`role_name`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startcode`.`owner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `startcode`.`owner`;

CREATE TABLE IF NOT EXISTS `startcode`.`owner`
(
    `owner_id`      INT         NOT NULL AUTO_INCREMENT,
    `owner_name`    VARCHAR(45) NULL,
    `owner_address` VARCHAR(45) NULL,
    `owner_phone`   INT         NULL,
    `user_name`     VARCHAR(25) NOT NULL,
    PRIMARY KEY (`owner_id`, `user_name`),
    INDEX `fk_owner_user1_idx` (`user_name` ASC) VISIBLE,
    CONSTRAINT `fk_owner_user1`
        FOREIGN KEY (`user_name`)
            REFERENCES `startcode`.`user` (`user_name`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startcode`.`harbour`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `startcode`.`harbour`;

CREATE TABLE IF NOT EXISTS `startcode`.`harbour`
(
    `harbour_id`       INT         NOT NULL AUTO_INCREMENT,
    `harbour_name`     VARCHAR(45) NULL,
    `harbour_address`  VARCHAR(45) NULL,
    `harbour_capacity` INT         NULL,
    PRIMARY KEY (`harbour_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startcode`.`boat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `startcode`.`boat`;

CREATE TABLE IF NOT EXISTS `startcode`.`boat`
(
    `boat_id`    INT         NOT NULL AUTO_INCREMENT,
    `boat_brand` VARCHAR(45) NULL,
    `boat_make`  VARCHAR(45) NULL,
    `boat_name`  VARCHAR(45) NULL,
    `boat_image` BLOB        NULL,
    `harbour_id` INT         NOT NULL,
    PRIMARY KEY (`boat_id`, `harbour_id`),
    INDEX `fk_boat_harbour1_idx` (`harbour_id` ASC) VISIBLE,
    CONSTRAINT `fk_boat_harbour1`
        FOREIGN KEY (`harbour_id`)
            REFERENCES `startcode`.`harbour` (`harbour_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `startcode`.`owner_boat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `startcode`.`owner_boat`;

CREATE TABLE IF NOT EXISTS `startcode`.`owner_boat`
(
    `owner_id` INT NOT NULL,
    `boat_id`  INT NOT NULL,
    PRIMARY KEY (`owner_id`, `boat_id`),
    INDEX `fk_owner_has_boat_boat1_idx` (`boat_id` ASC) VISIBLE,
    INDEX `fk_owner_has_boat_owner1_idx` (`owner_id` ASC) VISIBLE,
    CONSTRAINT `fk_owner_has_boat_owner1`
        FOREIGN KEY (`owner_id`)
            REFERENCES `startcode`.`owner` (`owner_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_owner_has_boat_boat1`
        FOREIGN KEY (`boat_id`)
            REFERENCES `startcode`.`boat` (`boat_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
