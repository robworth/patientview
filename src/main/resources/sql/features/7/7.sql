-- feature 2 way messaging

CREATE TABLE `conversation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` tinyint(1) NOT NULL,
  `started` datetime NOT NULL,
  `from_user_id` bigint(20) NOT NULL,
  `to_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK35E930A32BF31DAE` (`to_user_id`),
  KEY `FK35E930A39C9A87DD` (`from_user_id`),
  CONSTRAINT `FK35E930A39C9A87DD` FOREIGN KEY (`from_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK35E930A32BF31DAE` FOREIGN KEY (`to_user_id`) REFERENCES `user` (`id`)
)

CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  `description` text NOT NULL,
  `conversation_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9C2397E72776A072` (`conversation_id`),
  CONSTRAINT `FK9C2397E72776A072` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`)
)