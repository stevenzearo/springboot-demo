CREATE TABLE IF NOT EXISTS `identities`
(
    `id`           VARCHAR(50)  NOT NULL,
    `name`         VARCHAR(50)  NOT NULL,
    `age`          INT          NOT NULL,
    `created_by`   VARCHAR(50)  NOT NULL,
    `created_time` TIMESTAMP(6) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX ix_created_time (`created_time`)
    )
    ENGINE = InnoDB;

insert ignore into identities value ('id-0001', 'howard', '27', 'admin', CURRENT_TIMESTAMP());