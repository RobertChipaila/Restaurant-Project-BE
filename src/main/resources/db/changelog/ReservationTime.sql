CREATE TABLE if not exists `book_it`.`reservation_time`
(
    `id`         BIGINT      NOT NULL AUTO_INCREMENT,
    `start_time` DATETIME NOT NULL,
    `end_time`   DATETIME NOT NULL,
    PRIMARY KEY (`id`)
);
