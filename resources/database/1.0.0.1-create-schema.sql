CREATE SCHEMA labels_and_errors_db;

CREATE TABLE `error_messages` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`service_name` varchar(50) NOT NULL,
	`language_code` varchar(5) NOT NULL,
	`code` varchar(50) NOT NULL,
	`message` varchar(255) NOT NULL,
	`version` int(11) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`),
	UNIQUE KEY (`service_name`, `language_code`, `code`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;