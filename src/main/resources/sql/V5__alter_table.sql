ALTER TABLE `cctv`.`cctvs`
CHANGE COLUMN `purpose` `purpose` VARCHAR(16) NULL COMMENT '' ,
ADD COLUMN `range` VARCHAR(256) NULL COMMENT '' AFTER `source`,
ADD COLUMN `department` VARCHAR(128) NULL COMMENT '' AFTER `range`,
ADD COLUMN `pixel` VARCHAR(128) NULL COMMENT '' AFTER `department`,
ADD COLUMN `form` VARCHAR(128) NULL COMMENT '' AFTER `pixel`,
ADD COLUMN `installedAt` VARCHAR(256) NULL COMMENT '' AFTER `form`;
