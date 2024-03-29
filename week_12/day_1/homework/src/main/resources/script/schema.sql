DROP TABLE IF EXISTS Authoroties;
DROP TABLE IF EXISTS Members;
CREATE TABLE IF NOT EXISTS `Members` (
    `member_id`   VARCHAR(50)  NOT NULL,
    `name`        VARCHAR(50)  NOT NULL,
    `pwd`         VARCHAR(100) NOT NULL,

    PRIMARY KEY(`member_id`)
);

CREATE TABLE IF NOT EXISTS `Authoroties` (
    `member_id`   VARCHAR(50)  NOT NULL,
    `authority`   VARCHAR(50)  NOT NULL,

    PRIMARY KEY(`member_id`)
);