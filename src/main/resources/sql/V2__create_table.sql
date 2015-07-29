CREATE TABLE `cctv`.`cctvs` (
  `cctvId` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `latitude` DOUBLE NOT NULL COMMENT '',
  `longitude` DOUBLE NOT NULL COMMENT '',
  `tileName` VARCHAR(16) NOT NULL COMMENT '',
  `purpose` VARCHAR(16) NOT NULL COMMENT '',
  `address` VARCHAR(128) NULL COMMENT '',
  `cctvImage` VARCHAR(256) NOT NULL COMMENT '',
  `noticeImage` VARCHAR(256) NULL COMMENT '',
  `source` VARCHAR(16) NOT NULL COMMENT '',
  PRIMARY KEY (`cctvId`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `cctv`.`reliabilities` (
  `reliabilityId` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `cctvId` BIGINT NOT NULL COMMENT '',
  `userId` VARCHAR(45) NOT NULL COMMENT '',
  `reliable` TINYINT(1) NOT NULL COMMENT '',
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`reliabilityId`)  COMMENT '',
  INDEX `cctvId_idx` (`cctvId` ASC)  COMMENT '',
  CONSTRAINT `fk_reliabilities_cctvs`
    FOREIGN KEY (`cctvId`)
    REFERENCES `cctv`.`cctvs` (`cctvId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `cctv`.`comments` (
  `commentId` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `cctvId` BIGINT NOT NULL COMMENT '',
  `userId` VARCHAR(45) NOT NULL COMMENT '',
  `contents` VARCHAR(512) NOT NULL COMMENT '',
  `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`commentId`)  COMMENT '',
  INDEX `fk_cctvId_idx` (`cctvId` ASC)  COMMENT '',
  CONSTRAINT `fk_comments_cctvs`
    FOREIGN KEY (`cctvId`)
    REFERENCES `cctv`.`cctvs` (`cctvId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
