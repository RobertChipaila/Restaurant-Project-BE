ALTER TABLE `book_it`.`reservation_time`
    ADD COLUMN `reserved_seats` INT    NULL AFTER `end_time`,
    ADD COLUMN `table_id`       BIGINT NULL AFTER `reserved_seats`,
    ADD FOREIGN KEY (`table_id`) REFERENCES tables(`id`);
