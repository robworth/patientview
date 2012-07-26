
SET NAMES latin1;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `aboutme`
-- ----------------------------
DROP TABLE IF EXISTS `aboutme`;
CREATE TABLE `aboutme` (
  `id` int(11) NOT NULL,
  `nhsno` varchar(10) DEFAULT NULL,
  `aboutme` text,
  `talkabout` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `centre`
-- ----------------------------
DROP TABLE IF EXISTS `centre`;
CREATE TABLE `centre` (
  `centreCode` varchar(10) NOT NULL DEFAULT '',
  `centreName` varchar(100) DEFAULT '',
  `centreAddress1` varchar(100) DEFAULT '',
  `centreAddress2` varchar(100) DEFAULT '',
  `centreAddress3` varchar(100) DEFAULT '',
  `centreAddress4` varchar(100) DEFAULT '',
  `centrePostCode` varchar(100) DEFAULT '',
  `centreTelephone` varchar(100) DEFAULT '',
  `centreEmail` varchar(100) DEFAULT '',
  KEY `centreCode` (`centreCode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `nhsno` varchar(10) NOT NULL DEFAULT '',
  `body` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `diagnosis`
-- ----------------------------
DROP TABLE IF EXISTS `diagnosis`;
CREATE TABLE `diagnosis` (
  `id` int(11) NOT NULL DEFAULT '0',
  `nhsno` varchar(20) NOT NULL DEFAULT '',
  `unitcode` varchar(20) NOT NULL DEFAULT '',
  `diagnosis` varchar(200) DEFAULT '',
  `displayorder` int(3) DEFAULT '0',
  KEY `nhsno_unitcode` (`nhsno`,`unitcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `edtaCode`
-- ----------------------------
DROP TABLE IF EXISTS `edtaCode`;
CREATE TABLE `edtaCode` (
  `edtaCode` varchar(100) NOT NULL DEFAULT '',
  `linkType` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT '',
  `medicalLink01` varchar(100) DEFAULT '',
  `medicalLink02` varchar(100) DEFAULT '',
  `medicalLink03` varchar(100) DEFAULT '',
  `medicalLink04` varchar(100) DEFAULT '',
  `medicalLink05` varchar(100) DEFAULT '',
  `medicalLink06` varchar(100) DEFAULT '',
  `medicalLinkText01` varchar(100) DEFAULT '',
  `medicalLinkText02` varchar(100) DEFAULT '',
  `medicalLinkText03` varchar(100) DEFAULT '',
  `medicalLinkText04` varchar(100) DEFAULT '',
  `medicalLinkText05` varchar(100) DEFAULT '',
  `medicalLinkText06` varchar(100) DEFAULT '',
  `patientLink01` varchar(100) DEFAULT '',
  `patientLink02` varchar(100) DEFAULT '',
  `patientLink03` varchar(100) DEFAULT '',
  `patientLink04` varchar(100) DEFAULT '',
  `patientLink05` varchar(100) DEFAULT '',
  `patientLink06` varchar(100) DEFAULT '',
  `patientLinkText01` varchar(100) DEFAULT '',
  `patientLinkText02` varchar(100) DEFAULT '',
  `patientLinkText03` varchar(100) DEFAULT '',
  `patientLinkText04` varchar(100) DEFAULT '',
  `patientLinkText05` varchar(100) DEFAULT '',
  `patientLinkText06` varchar(100) DEFAULT '',
  PRIMARY KEY (`edtaCode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `emailverification`
-- ----------------------------
DROP TABLE IF EXISTS `emailverification`;
CREATE TABLE `emailverification` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `verificationcode` varchar(50) NOT NULL,
  `expirydatestamp` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `feedback`
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `nhsno` varchar(10) NOT NULL,
  `unitcode` varchar(10) NOT NULL,
  `datestamp` datetime NOT NULL,
  `comment` text NOT NULL,
  `commentedited` text,
  `anonymous` tinyint(1) NOT NULL,
  `makepublic` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_api`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_api`;
CREATE TABLE `jforum_api` (
  `api_id` int(11) NOT NULL AUTO_INCREMENT,
  `api_key` varchar(32) NOT NULL,
  `api_validity` datetime NOT NULL,
  PRIMARY KEY (`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_attach`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_attach`;
CREATE TABLE `jforum_attach` (
  `attach_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `privmsgs_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`attach_id`),
  KEY `idx_att_post` (`post_id`),
  KEY `idx_att_priv` (`privmsgs_id`),
  KEY `idx_att_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_attach_desc`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_attach_desc`;
CREATE TABLE `jforum_attach_desc` (
  `attach_desc_id` int(11) NOT NULL AUTO_INCREMENT,
  `attach_id` int(11) NOT NULL,
  `physical_filename` varchar(255) NOT NULL,
  `real_filename` varchar(255) NOT NULL,
  `download_count` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `mimetype` varchar(50) DEFAULT NULL,
  `filesize` int(11) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `thumb` tinyint(1) DEFAULT '0',
  `extension_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`attach_desc_id`),
  KEY `idx_att_d_att` (`attach_id`),
  KEY `idx_att_d_ext` (`extension_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_attach_quota`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_attach_quota`;
CREATE TABLE `jforum_attach_quota` (
  `attach_quota_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `quota_limit_id` int(11) NOT NULL,
  PRIMARY KEY (`attach_quota_id`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_banlist`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_banlist`;
CREATE TABLE `jforum_banlist` (
  `banlist_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `banlist_ip` varchar(15) DEFAULT NULL,
  `banlist_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`banlist_id`),
  KEY `idx_user` (`user_id`),
  KEY `banlist_ip` (`banlist_ip`),
  KEY `banlist_email` (`banlist_email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_banner`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_banner`;
CREATE TABLE `jforum_banner` (
  `banner_id` int(11) NOT NULL AUTO_INCREMENT,
  `banner_name` varchar(90) DEFAULT NULL,
  `banner_placement` int(11) NOT NULL DEFAULT '0',
  `banner_description` varchar(250) DEFAULT NULL,
  `banner_clicks` int(11) NOT NULL DEFAULT '0',
  `banner_views` int(11) NOT NULL DEFAULT '0',
  `banner_url` varchar(250) DEFAULT NULL,
  `banner_weight` tinyint(1) NOT NULL DEFAULT '50',
  `banner_active` tinyint(1) NOT NULL DEFAULT '0',
  `banner_comment` varchar(250) DEFAULT NULL,
  `banner_type` int(11) NOT NULL DEFAULT '0',
  `banner_width` int(11) NOT NULL DEFAULT '0',
  `banner_height` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`banner_id`),
  KEY `banner_id` (`banner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_bookmarks`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_bookmarks`;
CREATE TABLE `jforum_bookmarks` (
  `bookmark_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `relation_id` int(11) NOT NULL,
  `relation_type` int(11) NOT NULL,
  `public_visible` int(11) DEFAULT '1',
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookmark_id`),
  KEY `book_idx_relation` (`relation_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_categories`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_categories`;
CREATE TABLE `jforum_categories` (
  `categories_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '',
  `display_order` int(11) NOT NULL DEFAULT '0',
  `moderated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`categories_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_config`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_config`;
CREATE TABLE `jforum_config` (
  `config_name` varchar(255) NOT NULL DEFAULT '',
  `config_value` varchar(255) NOT NULL DEFAULT '',
  `config_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_extension_groups`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_extension_groups`;
CREATE TABLE `jforum_extension_groups` (
  `extension_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `allow` tinyint(1) DEFAULT '1',
  `upload_icon` varchar(100) DEFAULT NULL,
  `download_mode` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`extension_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_extensions`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_extensions`;
CREATE TABLE `jforum_extensions` (
  `extension_id` int(11) NOT NULL AUTO_INCREMENT,
  `extension_group_id` int(11) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `upload_icon` varchar(100) DEFAULT NULL,
  `extension` varchar(10) DEFAULT NULL,
  `allow` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`extension_id`),
  KEY `extension_group_id` (`extension_group_id`),
  KEY `extension` (`extension`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_forums`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_forums`;
CREATE TABLE `jforum_forums` (
  `forum_id` int(11) NOT NULL AUTO_INCREMENT,
  `categories_id` int(11) NOT NULL DEFAULT '1',
  `forum_name` varchar(150) NOT NULL DEFAULT '',
  `forum_desc` varchar(255) DEFAULT NULL,
  `forum_order` int(11) DEFAULT '1',
  `forum_topics` int(11) NOT NULL DEFAULT '0',
  `forum_last_post_id` int(11) NOT NULL DEFAULT '0',
  `moderated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`forum_id`),
  KEY `categories_id` (`categories_id`),
  KEY `idx_forums_cats` (`categories_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_forums_watch`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_forums_watch`;
CREATE TABLE `jforum_forums_watch` (
  `forum_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `idx_fw_forum` (`forum_id`),
  KEY `idx_fw_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_groups`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_groups`;
CREATE TABLE `jforum_groups` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(40) NOT NULL DEFAULT '',
  `group_description` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT '0',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_karma`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_karma`;
CREATE TABLE `jforum_karma` (
  `karma_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  `post_user_id` int(11) NOT NULL,
  `from_user_id` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `rate_date` datetime DEFAULT NULL,
  PRIMARY KEY (`karma_id`),
  KEY `post_id` (`post_id`),
  KEY `topic_id` (`topic_id`),
  KEY `post_user_id` (`post_user_id`),
  KEY `from_user_id` (`from_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_mail_integration`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_mail_integration`;
CREATE TABLE `jforum_mail_integration` (
  `forum_id` int(11) NOT NULL,
  `forum_email` varchar(100) NOT NULL,
  `pop_username` varchar(100) NOT NULL,
  `pop_password` varchar(100) NOT NULL,
  `pop_host` varchar(100) NOT NULL,
  `pop_port` int(11) DEFAULT '110',
  `pop_ssl` tinyint(4) DEFAULT '0',
  KEY `forum_id` (`forum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_moderation_log`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_moderation_log`;
CREATE TABLE `jforum_moderation_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `log_description` text NOT NULL,
  `log_original_message` text,
  `log_date` datetime NOT NULL,
  `log_type` tinyint(4) DEFAULT '0',
  `post_id` int(11) DEFAULT '0',
  `topic_id` int(11) DEFAULT '0',
  `post_user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`log_id`),
  KEY `user_id` (`user_id`),
  KEY `post_user_id` (`post_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_posts`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_posts`;
CREATE TABLE `jforum_posts` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL DEFAULT '0',
  `forum_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `post_time` datetime DEFAULT NULL,
  `poster_ip` varchar(15) DEFAULT NULL,
  `enable_bbcode` tinyint(1) NOT NULL DEFAULT '1',
  `enable_html` tinyint(1) NOT NULL DEFAULT '1',
  `enable_smilies` tinyint(1) NOT NULL DEFAULT '1',
  `enable_sig` tinyint(1) NOT NULL DEFAULT '1',
  `post_edit_time` datetime DEFAULT NULL,
  `post_edit_count` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(1) DEFAULT '1',
  `attach` tinyint(1) DEFAULT '0',
  `need_moderate` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`post_id`),
  KEY `user_id` (`user_id`),
  KEY `topic_id` (`topic_id`),
  KEY `forum_id` (`forum_id`),
  KEY `post_time` (`post_time`),
  KEY `need_moderate` (`need_moderate`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_posts_text`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_posts_text`;
CREATE TABLE `jforum_posts_text` (
  `post_id` int(11) NOT NULL,
  `post_text` text,
  `post_subject` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_privmsgs`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_privmsgs`;
CREATE TABLE `jforum_privmsgs` (
  `privmsgs_id` int(11) NOT NULL AUTO_INCREMENT,
  `privmsgs_type` tinyint(4) NOT NULL DEFAULT '0',
  `privmsgs_subject` varchar(255) NOT NULL DEFAULT '',
  `privmsgs_from_userid` int(11) NOT NULL DEFAULT '0',
  `privmsgs_to_userid` int(11) NOT NULL DEFAULT '0',
  `privmsgs_date` datetime DEFAULT NULL,
  `privmsgs_ip` varchar(15) NOT NULL DEFAULT '',
  `privmsgs_enable_bbcode` tinyint(1) NOT NULL DEFAULT '1',
  `privmsgs_enable_html` tinyint(1) NOT NULL DEFAULT '0',
  `privmsgs_enable_smilies` tinyint(1) NOT NULL DEFAULT '1',
  `privmsgs_attach_sig` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`privmsgs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_privmsgs_text`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_privmsgs_text`;
CREATE TABLE `jforum_privmsgs_text` (
  `privmsgs_id` int(11) NOT NULL,
  `privmsgs_text` text,
  PRIMARY KEY (`privmsgs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_quota_limit`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_quota_limit`;
CREATE TABLE `jforum_quota_limit` (
  `quota_limit_id` int(11) NOT NULL AUTO_INCREMENT,
  `quota_desc` varchar(50) NOT NULL,
  `quota_limit` int(11) NOT NULL,
  `quota_type` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`quota_limit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_ranks`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_ranks`;
CREATE TABLE `jforum_ranks` (
  `rank_id` int(11) NOT NULL AUTO_INCREMENT,
  `rank_title` varchar(50) NOT NULL DEFAULT '',
  `rank_min` int(11) NOT NULL DEFAULT '0',
  `rank_special` tinyint(1) DEFAULT NULL,
  `rank_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_role_values`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_role_values`;
CREATE TABLE `jforum_role_values` (
  `role_id` int(11) NOT NULL,
  `role_value` varchar(255) DEFAULT NULL,
  KEY `idx_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_roles`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_roles`;
CREATE TABLE `jforum_roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT '0',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  KEY `idx_group` (`group_id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_sessions`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_sessions`;
CREATE TABLE `jforum_sessions` (
  `session_id` varchar(150) NOT NULL DEFAULT '',
  `session_user_id` int(11) NOT NULL DEFAULT '0',
  `session_start` datetime DEFAULT NULL,
  `session_time` bigint(20) DEFAULT '0',
  `session_ip` varchar(15) NOT NULL DEFAULT '',
  `session_page` int(11) NOT NULL DEFAULT '0',
  `session_logged_int` tinyint(1) DEFAULT NULL,
  KEY `idx_sessions_users` (`session_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_smilies`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_smilies`;
CREATE TABLE `jforum_smilies` (
  `smilie_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL DEFAULT '',
  `url` varchar(100) DEFAULT NULL,
  `disk_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`smilie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_themes`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_themes`;
CREATE TABLE `jforum_themes` (
  `themes_id` int(11) NOT NULL AUTO_INCREMENT,
  `template_name` varchar(30) NOT NULL DEFAULT '',
  `style_name` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`themes_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_topics`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_topics`;
CREATE TABLE `jforum_topics` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `forum_id` int(11) NOT NULL DEFAULT '0',
  `topic_title` varchar(100) NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `topic_time` datetime DEFAULT NULL,
  `topic_views` int(11) DEFAULT '1',
  `topic_replies` int(11) DEFAULT '0',
  `topic_status` tinyint(3) DEFAULT '0',
  `topic_vote_id` int(11) NOT NULL DEFAULT '0',
  `topic_type` tinyint(3) DEFAULT '0',
  `topic_first_post_id` int(11) DEFAULT '0',
  `topic_last_post_id` int(11) NOT NULL DEFAULT '0',
  `topic_moved_id` int(11) DEFAULT '0',
  `moderated` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`topic_id`),
  KEY `forum_id` (`forum_id`),
  KEY `user_id` (`user_id`),
  KEY `topic_first_post_id` (`topic_first_post_id`),
  KEY `topic_last_post_id` (`topic_last_post_id`),
  KEY `topic_moved_id` (`topic_moved_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_topics_watch`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_topics_watch`;
CREATE TABLE `jforum_topics_watch` (
  `topic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `is_read` tinyint(1) DEFAULT '1',
  KEY `idx_topic` (`topic_id`),
  KEY `idx_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_user_groups`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_user_groups`;
CREATE TABLE `jforum_user_groups` (
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `idx_group` (`group_id`),
  KEY `idx_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_users`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_users`;
CREATE TABLE `jforum_users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_active` tinyint(1) DEFAULT NULL,
  `username` varchar(50) NOT NULL DEFAULT '',
  `user_password` varchar(32) NOT NULL DEFAULT '',
  `user_session_time` bigint(20) DEFAULT '0',
  `user_session_page` int(11) NOT NULL DEFAULT '0',
  `user_lastvisit` datetime DEFAULT NULL,
  `user_regdate` datetime DEFAULT NULL,
  `user_level` tinyint(4) DEFAULT NULL,
  `user_posts` int(11) NOT NULL DEFAULT '0',
  `user_timezone` varchar(5) NOT NULL DEFAULT '',
  `user_style` tinyint(4) DEFAULT NULL,
  `user_lang` varchar(255) NOT NULL DEFAULT '',
  `user_dateformat` varchar(20) NOT NULL DEFAULT '%d/%M/%Y %H:%i',
  `user_new_privmsg` int(11) NOT NULL DEFAULT '0',
  `user_unread_privmsg` int(11) NOT NULL DEFAULT '0',
  `user_last_privmsg` datetime DEFAULT NULL,
  `user_emailtime` datetime DEFAULT NULL,
  `user_viewemail` tinyint(1) DEFAULT '0',
  `user_attachsig` tinyint(1) DEFAULT '1',
  `user_allowhtml` tinyint(1) DEFAULT '0',
  `user_allowbbcode` tinyint(1) DEFAULT '1',
  `user_allowsmilies` tinyint(1) DEFAULT '1',
  `user_allowavatar` tinyint(1) DEFAULT '1',
  `user_allow_pm` tinyint(1) DEFAULT '1',
  `user_allow_viewonline` tinyint(1) DEFAULT '1',
  `user_notify` tinyint(1) DEFAULT '1',
  `user_notify_always` tinyint(1) DEFAULT '0',
  `user_notify_text` tinyint(1) DEFAULT '0',
  `user_notify_pm` tinyint(1) DEFAULT '1',
  `user_popup_pm` tinyint(1) DEFAULT '1',
  `rank_id` int(11) DEFAULT '0',
  `user_avatar` varchar(100) DEFAULT NULL,
  `user_avatar_type` tinyint(4) NOT NULL DEFAULT '0',
  `user_email` varchar(255) NOT NULL DEFAULT '',
  `user_icq` varchar(15) DEFAULT NULL,
  `user_website` varchar(255) DEFAULT NULL,
  `user_from` varchar(100) DEFAULT NULL,
  `user_sig` text,
  `user_sig_bbcode_uid` varchar(10) DEFAULT NULL,
  `user_aim` varchar(255) DEFAULT NULL,
  `user_yim` varchar(255) DEFAULT NULL,
  `user_msnm` varchar(255) DEFAULT NULL,
  `user_occ` varchar(100) DEFAULT NULL,
  `user_interests` varchar(255) DEFAULT NULL,
  `user_biography` text,
  `user_actkey` varchar(32) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `themes_id` int(11) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `user_viewonline` tinyint(1) DEFAULT '1',
  `security_hash` varchar(32) DEFAULT NULL,
  `user_karma` double DEFAULT NULL,
  `user_authhash` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_vote_desc`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_vote_desc`;
CREATE TABLE `jforum_vote_desc` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) NOT NULL DEFAULT '0',
  `vote_text` varchar(255) NOT NULL DEFAULT '',
  `vote_start` datetime NOT NULL,
  `vote_length` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`vote_id`),
  KEY `topic_id` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_vote_results`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_vote_results`;
CREATE TABLE `jforum_vote_results` (
  `vote_id` int(11) NOT NULL DEFAULT '0',
  `vote_option_id` tinyint(4) NOT NULL DEFAULT '0',
  `vote_option_text` varchar(255) NOT NULL DEFAULT '',
  `vote_result` int(11) NOT NULL DEFAULT '0',
  KEY `vote_id` (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_vote_voters`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_vote_voters`;
CREATE TABLE `jforum_vote_voters` (
  `vote_id` int(11) NOT NULL DEFAULT '0',
  `vote_user_id` int(11) NOT NULL DEFAULT '0',
  `vote_user_ip` varchar(15) NOT NULL DEFAULT '',
  KEY `vote_id` (`vote_id`),
  KEY `vote_user_id` (`vote_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `jforum_words`
-- ----------------------------
DROP TABLE IF EXISTS `jforum_words`;
CREATE TABLE `jforum_words` (
  `word_id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(100) NOT NULL DEFAULT '',
  `replacement` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`word_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `letter`
-- ----------------------------
DROP TABLE IF EXISTS `letter`;
CREATE TABLE `letter` (
  `id` int(11) NOT NULL DEFAULT '0',
  `nhsno` varchar(100) NOT NULL DEFAULT '',
  `unitcode` varchar(20) NOT NULL DEFAULT '',
  `date` datetime DEFAULT '0000-00-00 00:00:00',
  `type` varchar(100) DEFAULT '',
  `content` text,
  KEY `nhsno_unitcode` (`nhsno`,`unitcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `actor` varchar(100) NOT NULL DEFAULT '',
  `action` varchar(100) NOT NULL DEFAULT '',
  `nhsno` varchar(100) DEFAULT '',
  `user` varchar(100) DEFAULT '',
  `unitcode` varchar(100) DEFAULT '',
  `extrainfo` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `medicine`
-- ----------------------------
DROP TABLE IF EXISTS `medicine`;
CREATE TABLE `medicine` (
  `id` int(11) NOT NULL DEFAULT '0',
  `nhsno` varchar(20) NOT NULL DEFAULT '',
  `unitcode` varchar(20) NOT NULL DEFAULT '',
  `startdate` datetime DEFAULT '0000-00-00 00:00:00',
  `name` varchar(100) DEFAULT '',
  `dose` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `nhsno_unitcode` (`nhsno`,`unitcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL DEFAULT '0',
  `datestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `unitcode` varchar(100) NOT NULL DEFAULT '',
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `patient` tinyint(1) NOT NULL DEFAULT '0',
  `everyone` tinyint(10) NOT NULL DEFAULT '0',
  `headline` varchar(255) NOT NULL DEFAULT '',
  `body` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `patient`
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `nhsno` varchar(100) NOT NULL DEFAULT '',
  `surname` varchar(100) DEFAULT '',
  `forename` varchar(100) DEFAULT '',
  `dateofbirth` varchar(100) DEFAULT '',
  `sex` varchar(100) DEFAULT '',
  `address1` varchar(100) DEFAULT '',
  `address2` varchar(100) DEFAULT '',
  `address3` varchar(100) DEFAULT '',
  `postcode` varchar(100) DEFAULT '',
  `telephone1` varchar(100) DEFAULT '',
  `telephone2` varchar(100) DEFAULT '',
  `mobile` varchar(100) DEFAULT '',
  `centreCode` varchar(100) NOT NULL DEFAULT '',
  `diagnosis` varchar(100) DEFAULT '',
  `treatment` varchar(100) DEFAULT '',
  `transplantstatus` varchar(100) DEFAULT '',
  `hospitalnumber` varchar(100) DEFAULT '',
  `gpname` varchar(100) DEFAULT '',
  `gpaddress1` varchar(100) DEFAULT '',
  `gpaddress2` varchar(100) DEFAULT '',
  `gpaddress3` varchar(100) DEFAULT '',
  `gppostcode` varchar(100) DEFAULT '',
  `gptelephone` varchar(100) DEFAULT '',
  PRIMARY KEY (`nhsno`,`centreCode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `result_heading`
-- ----------------------------
DROP TABLE IF EXISTS `result_heading`;
CREATE TABLE `result_heading` (
  `headingcode` varchar(20) NOT NULL DEFAULT '',
  `heading` varchar(30) NOT NULL DEFAULT '',
  `rollover` varchar(50) NOT NULL DEFAULT 'Click for info',
  `link` varchar(100) NOT NULL DEFAULT '',
  `panel` int(11) NOT NULL DEFAULT '0',
  `panelorder` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`headingcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `splashpage`
-- ----------------------------
DROP TABLE IF EXISTS `splashpage`;
CREATE TABLE `splashpage` (
  `id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `live` tinyint(1) NOT NULL,
  `headline` varchar(100) NOT NULL,
  `bodytext` text NOT NULL,
  `unitcode` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `splashpageuserseen`
-- ----------------------------
DROP TABLE IF EXISTS `splashpageuserseen`;
CREATE TABLE `splashpageuserseen` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `splashpageid` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usersplashpage` (`username`,`splashpageid`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `testresult`
-- ----------------------------
DROP TABLE IF EXISTS `testresult`;
CREATE TABLE `testresult` (
  `nhsno` varchar(100) NOT NULL DEFAULT '',
  `unitcode` varchar(20) NOT NULL DEFAULT '',
  `testcode` varchar(100) NOT NULL DEFAULT '',
  `datestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `prepost` varchar(100) DEFAULT '',
  `value` varchar(100) NOT NULL DEFAULT '',
  KEY `nhsno_testcode` (`nhsno`,`testcode`,`unitcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `treatment`
-- ----------------------------
DROP TABLE IF EXISTS `treatment`;
CREATE TABLE `treatment` (
  `nhsNo` varchar(100) NOT NULL DEFAULT '',
  `treatmentCode` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`nhsNo`,`treatmentCode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `uktcode`
-- ----------------------------
DROP TABLE IF EXISTS `uktcode`;
CREATE TABLE `uktcode` (
  `id` int(11) NOT NULL DEFAULT '0',
  `uktcode` varchar(10) NOT NULL DEFAULT '',
  `description` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uktcode_unique` (`uktcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `uktstatus`
-- ----------------------------
DROP TABLE IF EXISTS `uktstatus`;
CREATE TABLE `uktstatus` (
  `nhsno` varchar(20) NOT NULL DEFAULT '',
  `kidney` varchar(10) DEFAULT '',
  `pancreas` varchar(10) DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `unit`
-- ----------------------------
DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit` (
  `unitcode` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(100) NOT NULL DEFAULT '',
  `shortname` varchar(15) NOT NULL DEFAULT '',
  `unituser` varchar(20) DEFAULT NULL,
  `address1` varchar(100) DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `address3` varchar(100) DEFAULT NULL,
  `postcode` varchar(100) DEFAULT NULL,
  `uniturl` varchar(100) DEFAULT NULL,
  `trusturl` varchar(100) DEFAULT NULL,
  `rpvadminname` varchar(100) DEFAULT NULL,
  `rpvadminphone` varchar(100) DEFAULT NULL,
  `rpvadminemail` varchar(100) DEFAULT NULL,
  `unitenquiriesphone` varchar(100) DEFAULT NULL,
  `unitenquiriesemail` varchar(100) DEFAULT NULL,
  `appointmentphone` varchar(100) DEFAULT NULL,
  `appointmentemail` varchar(100) DEFAULT NULL,
  `outofhours` varchar(100) DEFAULT NULL,
  `peritonealdialysisphone` varchar(100) DEFAULT NULL,
  `peritonealdialysisemail` varchar(100) DEFAULT NULL,
  `haemodialysisunitname1` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone1` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation1` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl1` varchar(100) DEFAULT NULL,
  `haemodialysisunitname2` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone2` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation2` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl2` varchar(100) DEFAULT NULL,
  `haemodialysisunitname3` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone3` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation3` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl3` varchar(100) DEFAULT NULL,
  `haemodialysisunitname4` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone4` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation4` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl4` varchar(100) DEFAULT NULL,
  `haemodialysisunitname5` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone5` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation5` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl5` varchar(100) DEFAULT NULL,
  `haemodialysisunitname6` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone6` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation6` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl6` varchar(100) DEFAULT NULL,
  `haemodialysisunitname7` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone7` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation7` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl7` varchar(100) DEFAULT NULL,
  `haemodialysisunitname8` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone8` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation8` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl8` varchar(100) DEFAULT NULL,
  `haemodialysisunitname9` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone9` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation9` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl9` varchar(100) DEFAULT NULL,
  `haemodialysisunitname10` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone10` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation10` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl10` varchar(100) DEFAULT NULL,
  `haemodialysisunitname11` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone11` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation11` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl11` varchar(100) DEFAULT NULL,
  `haemodialysisunitname12` varchar(100) DEFAULT NULL,
  `haemodialysisunitphone12` varchar(100) DEFAULT NULL,
  `haemodialysisunitlocation12` varchar(100) DEFAULT NULL,
  `haemodialysisuniturl12` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`unitcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `unitstat`
-- ----------------------------
DROP TABLE IF EXISTS `unitstat`;
CREATE TABLE `unitstat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unitcode` varchar(20) NOT NULL,
  `yearmonth` varchar(7) NOT NULL,
  `action` varchar(30) NOT NULL,
  `count` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(100) NOT NULL DEFAULT '',
  `password` varchar(100) NOT NULL DEFAULT '',
  `role` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `emailverified` tinyint(1) DEFAULT '0',
  `firstlogon` tinyint(1) DEFAULT '0',
  `dummypatient` tinyint(1) NOT NULL DEFAULT '0',
  `lastlogon` datetime DEFAULT NULL,
  `failedlogons` int(10) DEFAULT '0',
  `accountlocked` tinyint(1) DEFAULT '0',
  `screenname` varchar(100) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `userlog`
-- ----------------------------
DROP TABLE IF EXISTS `userlog`;
CREATE TABLE `userlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datestamp` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `unitcode` varchar(100) DEFAULT NULL,
  `role` varchar(100) NOT NULL DEFAULT '',
  `count` int(21) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `usermapping`
-- ----------------------------
DROP TABLE IF EXISTS `usermapping`;
CREATE TABLE `usermapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `unitcode` varchar(10) NOT NULL,
  `nhsno` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=147 DEFAULT CHARSET=latin1;