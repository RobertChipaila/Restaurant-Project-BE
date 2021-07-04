CREATE TABLE if not exists `book_it`.`seats`
(
    `id`          BIGINT  NOT NULL AUTO_INCREMENT,
    `is_selected` BOOLEAN NOT NULL,
    PRIMARY KEY (`id`)
);
