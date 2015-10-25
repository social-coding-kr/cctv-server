CREATE TABLE `cctv`.`private_cctvs` (
  `cctvId` BIGINT NOT NULL,
  `cctvImage` VARCHAR(512) NOT NULL,
  `noticeImage` VARCHAR(512) NULL,
  PRIMARY KEY (`cctvId`),
  CONSTRAINT `fk_cctvId_private`
    FOREIGN KEY (`cctvId`)
    REFERENCES `cctv`.`cctvs` (`cctvId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `cctv`.`public_cctvs` (
  `cctvId` BIGINT NOT NULL,
  `cctvName` VARCHAR(32) NOT NULL,
  `address` VARCHAR(128) NULL DEFAULT NULL,
  `borough` VARCHAR(64) NULL DEFAULT NULL,
  `dong` VARCHAR(64) NULL DEFAULT NULL,
  `range` VARCHAR(256) NULL DEFAULT NULL,
  `department` VARCHAR(128) NULL DEFAULT NULL,
  `pixel` VARCHAR(128) NULL DEFAULT NULL,
  `form` VARCHAR(128) NULL DEFAULT NULL,
  `installedAt` VARCHAR(256) NULL DEFAULT NULL,
  PRIMARY KEY (`cctvId`),
  CONSTRAINT `fk_cctvId_public`
    FOREIGN KEY (`cctvId`)
    REFERENCES `cctv`.`cctvs` (`cctvId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


ALTER TABLE `cctv`.`cctvs`
DROP COLUMN `installedAt`,
DROP COLUMN `form`,
DROP COLUMN `pixel`,
DROP COLUMN `department`,
DROP COLUMN `range`,
DROP COLUMN `noticeImage`,
DROP COLUMN `cctvImage`,
DROP COLUMN `dong`,
DROP COLUMN `borough`,
DROP COLUMN `address`,
DROP COLUMN `cctvName`,
CHANGE COLUMN `purpose` `purpose` VARCHAR(128) NULL DEFAULT NULL ,
CHANGE COLUMN `source` `source` VARCHAR(64) NOT NULL ;
