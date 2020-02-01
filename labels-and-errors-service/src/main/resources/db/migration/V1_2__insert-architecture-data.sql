-- Pagination
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'paginationResponseResourceNotNull', 'El recurso de entrada no puede ser vacio');
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'paginationMinPageError', 'El numero de pagina no puede ser inferior a {}');
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'paginationMinSizeError', 'El tamaño de pagina no puede ser inferior a {}');
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'paginationPageNotNull', 'El numero de pagina no puede estar vacio');
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'paginationSizeNotNull', 'El tamaño de pagina no puede estar vacio');
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'paginationNumberOfElementsNotNull', 'El numero de elementos no puede estar vacio');
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'paginationTotalPagesNotNull', 'El total de paginas no puede estar vacio');
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'paginationTotalElementsNotNull', 'El total de elementos no puede estar vacio');
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'paginationFirstNotNull', 'El campo first no puede estar vacio');
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'paginationLastNotNull', 'El last no puede estar vacio');


-- Audit
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'auditResponseResourceNotNull', 'El recurso de entrada no puede ser vacio');
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'auditCreatedDateNotNull', 'El campo fecha de creacion de la auditoria es obligatorio');
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'auditCreatedUserNotNull', 'El campo usuario de creacion de la auditoria es obligatorio');


-- Service
INSERT INTO `labels_and_errors_db`.`error_messages` (`service_name`, `language_code`, `code`, `message`) VALUES ('architecture', 'es-ES', 'versionConflict', 'Hay conflicto al guardar, refresque la pantalla y vuelva a intentarlo');