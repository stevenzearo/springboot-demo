CREATE TABLE IF NOT EXISTS `persons`
(
    `id`           VARCHAR(50)  NOT NULL,
    `name`         VARCHAR(50)  NOT NULL,
    `birthday`     TIMESTAMP(6) NULL,
    `created_by`   VARCHAR(50)  NOT NULL,
    `created_time` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`),
    INDEX ix_created_time (`created_time`)
)
    ENGINE = InnoDB;

insert ignore into persons value ('id-0001', 'howard', null, 'admin', CURRENT_TIMESTAMP(6));