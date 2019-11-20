CREATE TABLE `sequence` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`table_name` VARCHAR(64) NOT NULL,
	`max_id` BIGINT(20) NOT NULL,
	`step` SMALLINT(6) NOT NULL,
	`modify_time` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `ind_table_name` (`table_name`)
)COMMENT='序列表' COLLATE='utf8_general_ci' ENGINE=InnoDB;