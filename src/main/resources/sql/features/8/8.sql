-- feature 2 way messaging

CREATE TABLE `conversation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` tinyint(1) NOT NULL,
  `started` datetime NOT NULL,
  `participant1_id` bigint(20) NOT NULL,
  `participant2_id` bigint(20) NOT NULL,
  `subject` text NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `date` datetime NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  `hasRead` tinyint(1) NOT NULL,
  `conversation_id` bigint(20) NOT NULL,
  `recipient_id` bigint(20) NOT NULL,
  `sender_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9C2397E72776A072` (`conversation_id`),
  CONSTRAINT `FK9C2397E72776A072` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`id`)
);