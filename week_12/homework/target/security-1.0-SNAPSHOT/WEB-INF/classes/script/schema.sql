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
MERGE INTO Members AS target
USING (VALUES ('admin', 'admin', 'admin')) AS source (member_id, name, pwd)
ON (target.member_id = source.member_id)
WHEN NOT MATCHED THEN
INSERT (member_id, name, pwd) VALUES (source.member_id, source.name, source.pwd);

MERGE INTO `Authoroties` AS target
USING (VALUES ('admin', 'ROLE_ADMIN')) AS source (member_id, authority)
ON (target.member_id = source.member_id)
WHEN NOT MATCHED THEN
INSERT (member_id, authority) VALUES (source.member_id, source.authority);