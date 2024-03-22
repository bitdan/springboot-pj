CREATE TABLE IF NOT EXISTS `t_db_version`
(
    `name`  varchar(255) NOT NULL,
    `value` int(11) DEFAULT NULL,
    PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_db_version
-- ----------------------------
BEGIN;
INSERT ignore INTO `t_db_version` (`name`, `value`)
VALUES ('version', 0);
COMMIT;
