ALTER TABLE `cctv`.`cctvs`
CHANGE COLUMN `cctvImage` `cctvImage` VARCHAR(256) NULL COMMENT '' ;

ALTER TABLE `cctv`.`cctvs` 
ADD COLUMN `borough` VARCHAR(64) NULL COMMENT '' AFTER `address`,
ADD COLUMN `dong` VARCHAR(64) NULL COMMENT '' AFTER `borough`;

ALTER TABLE `cctv`.`cctvs`
CHANGE COLUMN `latitude` `latitude` DECIMAL(10,7) NOT NULL COMMENT '' ,
CHANGE COLUMN `longitude` `longitude` DECIMAL(10,7) NOT NULL COMMENT '' ;
