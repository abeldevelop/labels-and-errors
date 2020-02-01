CREATE TABLE `labels_and_errors_db`.`error_messages` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`service_name` VARCHAR(50) NOT NULL,
	`language_code` VARCHAR(5) NOT NULL,
	`code` VARCHAR(100) NOT NULL,
	`message` VARCHAR(255) NOT NULL,
	`version` INT NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`),
	UNIQUE KEY (`service_name`, `language_code`, `code`)
);