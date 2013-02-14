
-- ----------------------------
-- Table structure for table aboutme
-- ----------------------------
DROP TABLE IF EXISTS aboutme;
CREATE TABLE aboutme (
  id int(11) NOT NULL,
  nhsno varchar(10) NOT NULL,
  aboutme text,
  talkabout text,
  PRIMARY KEY  (id)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table centre
-- ----------------------------
DROP TABLE IF EXISTS centre;
CREATE TABLE centre (
  centreCode varchar(10) NOT NULL default '',
  centreName varchar(100) default '',
  centreAddress1 varchar(100) default '',
  centreAddress2 varchar(100) default '',
  centreAddress3 varchar(100) default '',
  centreAddress4 varchar(100) default '',
  centrePostCode varchar(100) default '',
  centreTelephone varchar(100) default '',
  centreEmail varchar(100) default '',
  KEY `centreCode` (`centreCode`)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;



-- ----------------------------
-- Table structure for table comment
-- ----------------------------
DROP TABLE IF EXISTS comment;
CREATE TABLE comment (
  id int(11) NOT NULL auto_increment,
  datestamp datetime NOT NULL default '0000-00-00 00:00:00',
  nhsno varchar(10) NOT NULL default '',
  body text NOT NULL,
  PRIMARY KEY  (id)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table diagnosis
-- ----------------------------
DROP TABLE IF EXISTS diagnosis;
CREATE TABLE diagnosis (
  id int(11) NOT NULL default '0',
  nhsno varchar(20) NOT NULL default '',
  unitcode varchar(20) NOT NULL default '',
  diagnosis varchar(200) default '',
  displayorder int(3) default '0',
  KEY diagnosis_nhsno_unitcode (nhsno,unitcode)
  
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table edtacode
-- ----------------------------
DROP TABLE IF EXISTS edtacode;
CREATE TABLE edtacode (
  edtaCode varchar(100) NOT NULL default '',
  linkType varchar(20) default NULL,
  description varchar(100) default '',
  medicalLink01 varchar(100) default '',
  medicalLink02 varchar(100) default '',
  medicalLink03 varchar(100) default '',
  medicalLink04 varchar(100) default '',
  medicalLink05 varchar(100) default '',
  medicalLink06 varchar(100) default '',
  medicalLinkText01 varchar(100) default '',
  medicalLinkText02 varchar(100) default '',
  medicalLinkText03 varchar(100) default '',
  medicalLinkText04 varchar(100) default '',
  medicalLinkText05 varchar(100) default '',
  medicalLinkText06 varchar(100) default '',
  patientLink01 varchar(100) default '',
  patientLink02 varchar(100) default '',
  patientLink03 varchar(100) default '',
  patientLink04 varchar(100) default '',
  patientLink05 varchar(100) default '',
  patientLink06 varchar(100) default '',
  patientLinkText01 varchar(100) default '',
  patientLinkText02 varchar(100) default '',
  patientLinkText03 varchar(100) default '',
  patientLinkText04 varchar(100) default '',
  patientLinkText05 varchar(100) default '',
  patientLinkText06 varchar(100) default '',
  PRIMARY KEY  (edtaCode)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table emailverification
-- ----------------------------
DROP TABLE IF EXISTS emailverification;
CREATE TABLE emailverification (
  id int(11) NOT NULL,
  username varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  verificationcode varchar(50) NOT NULL,
  expirydatestamp datetime NOT NULL,
  PRIMARY KEY  (id)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table feedback
-- ----------------------------
DROP TABLE IF EXISTS feedback;
CREATE TABLE feedback (
  id int(11) NOT NULL auto_increment,
  username varchar(50) NOT NULL,
  name varchar(50) NOT NULL,
  nhsno varchar(10) NOT NULL,
  unitcode varchar(10) NOT NULL,
  datestamp datetime NOT NULL,
  comment text NOT NULL,
  commentedited text,
  anonymous tinyint(1) NOT NULL default '1',
  makepublic tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (id)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_api
-- ----------------------------
DROP TABLE IF EXISTS jforum_api;
CREATE TABLE jforum_api (
  api_id int(11) NOT NULL auto_increment,
  api_key varchar(32) NOT NULL,
  api_validity datetime NOT NULL,
  PRIMARY KEY  (api_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_attach
-- ----------------------------
DROP TABLE IF EXISTS jforum_attach;
CREATE TABLE jforum_attach (
  attach_id int(11) NOT NULL auto_increment,
  post_id int(11) default NULL,
  privmsgs_id int(11) default NULL,
  user_id int(11) NOT NULL,
  PRIMARY KEY  (attach_id),
  KEY jforum_attach_att_post (post_id),
  KEY jforum_attach_att_priv (privmsgs_id),
  KEY jforum_attach_att_user (user_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;



-- ----------------------------
-- Table structure for table jforum_attach_desc
-- ----------------------------
DROP TABLE IF EXISTS jforum_attach_desc;
CREATE TABLE jforum_attach_desc (
  attach_desc_id int(11) NOT NULL auto_increment,
  attach_id int(11) NOT NULL,
  physical_filename varchar(255) NOT NULL,
  real_filename varchar(255) NOT NULL,
  download_count int(11) default NULL,
  description varchar(255) default NULL,
  mimetype varchar(50) default NULL,
  filesize int(11) default NULL,
  upload_time datetime default NULL,
  thumb tinyint(1) default '0',
  extension_id int(11) default NULL,
  PRIMARY KEY  (attach_desc_id),
  KEY idx_att_d_att (attach_id),
  KEY idx_att_d_ext (extension_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_attach_quota
-- ----------------------------
DROP TABLE IF EXISTS jforum_attach_quota;
CREATE TABLE jforum_attach_quota (
  attach_quota_id int(11) NOT NULL auto_increment,
  group_id int(11) NOT NULL,
  quota_limit_id int(11) NOT NULL,
  PRIMARY KEY  (attach_quota_id),
  KEY group_id (group_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;



-- ----------------------------
-- Table structure for table jforum_banlist
-- ----------------------------
DROP TABLE IF EXISTS jforum_banlist;
CREATE TABLE jforum_banlist (
  banlist_id int(11) NOT NULL auto_increment,
  user_id int(11) default NULL,
  banlist_ip varchar(15) default NULL,
  banlist_email varchar(255) default NULL,
  PRIMARY KEY  (banlist_id),
  KEY jforum_banlist_user (user_id),
  KEY jforum_banlist_banlist_ip (banlist_ip),
  KEY jforum_banlist_banlist_email (banlist_email)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_banner
-- ----------------------------
DROP TABLE IF EXISTS jforum_banner;
CREATE TABLE jforum_banner (
  banner_id int(11) NOT NULL auto_increment,
  banner_name varchar(90) default NULL,
  banner_placement int(11) NOT NULL default '0',
  banner_description varchar(250) default NULL,
  banner_clicks int(11) NOT NULL default '0',
  banner_views int(11) NOT NULL default '0',
  banner_url varchar(250) default NULL,
  banner_weight tinyint(1) NOT NULL default '50',
  banner_active tinyint(1) NOT NULL default '0',
  banner_comment varchar(250) default NULL,
  banner_type int(11) NOT NULL default '0',
  banner_width int(11) NOT NULL default '0',
  banner_height int(11) NOT NULL default '0',
  PRIMARY KEY  (banner_id),
  KEY banner_id (banner_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;





-- ----------------------------
-- Table structure for table jforum_bookmarks
-- ----------------------------
DROP TABLE IF EXISTS jforum_bookmarks;
CREATE TABLE jforum_bookmarks (
  bookmark_id int(11) NOT NULL auto_increment,
  user_id int(11) NOT NULL,
  relation_id int(11) NOT NULL,
  relation_type int(11) NOT NULL,
  public_visible int(11) default '1',
  title varchar(255) default NULL,
  description varchar(255) default NULL,
  PRIMARY KEY  (bookmark_id),
  KEY jforum_bookmarks_book_idx_relation (relation_id),
  KEY jforum_bookmarks_book_idx_user_id (user_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_categories
-- ----------------------------
DROP TABLE IF EXISTS jforum_categories;
CREATE TABLE jforum_categories (
  categories_id int(11) NOT NULL auto_increment,
  title varchar(100) NOT NULL default '',
  display_order int(11) NOT NULL default '0',
  moderated tinyint(1) default '0',
  PRIMARY KEY  (categories_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_config
-- ----------------------------
DROP TABLE IF EXISTS jforum_config;
CREATE TABLE jforum_config (
  config_name varchar(255) NOT NULL default '',
  config_value varchar(255) NOT NULL default '',
  config_id int(11) NOT NULL auto_increment,
  PRIMARY KEY  (config_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_extension_groups
-- ----------------------------
DROP TABLE IF EXISTS jforum_extension_groups;
CREATE TABLE jforum_extension_groups (
  extension_group_id int(11) NOT NULL auto_increment,
  name varchar(100) NOT NULL,
  allow tinyint(1) default '1',
  upload_icon varchar(100) default NULL,
  download_mode tinyint(1) default '1',
  PRIMARY KEY  (extension_group_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_extensions
-- ----------------------------
DROP TABLE IF EXISTS jforum_extensions;
CREATE TABLE jforum_extensions (
  extension_id int(11) NOT NULL auto_increment,
  extension_group_id int(11) NOT NULL,
  description varchar(100) default NULL,
  upload_icon varchar(100) default NULL,
  extension varchar(10) default NULL,
  allow tinyint(1) default '1',
  PRIMARY KEY  (extension_id),
  KEY extension_group_id (extension_group_id),
  KEY extension (extension)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_forums
-- ----------------------------
DROP TABLE IF EXISTS jforum_forums;
CREATE TABLE jforum_forums (
  forum_id int(11) NOT NULL auto_increment,
  categories_id int(11) NOT NULL default '1',
  forum_name varchar(150) NOT NULL default '',
  forum_desc varchar(255) default NULL,
  forum_order int(11) default '1',
  forum_topics int(11) NOT NULL default '0',
  forum_last_post_id int(11) NOT NULL default '0',
  moderated tinyint(1) default '0',
  PRIMARY KEY  (forum_id),
  KEY categories_id (categories_id),
  KEY idx_forums_cats (categories_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;



-- ----------------------------
-- Table structure for table jforum_forums_watch
-- ----------------------------
DROP TABLE IF EXISTS jforum_forums_watch;
CREATE TABLE jforum_forums_watch (
  forum_id int(11) NOT NULL,
  user_id int(11) NOT NULL,
  KEY jforum_forums_watch_fw_forum (forum_id),
  KEY jforum_forums_watch_fw_user (user_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_groups
-- ----------------------------
DROP TABLE IF EXISTS jforum_groups;
CREATE TABLE jforum_groups (
  group_id int(11) NOT NULL auto_increment,
  group_name varchar(40) NOT NULL default '',
  group_description varchar(255) default NULL,
  parent_id int(11) default '0',
  PRIMARY KEY  (group_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_karma
-- ----------------------------
DROP TABLE IF EXISTS jforum_karma;
CREATE TABLE jforum_karma (
  karma_id int(11) NOT NULL auto_increment,
  post_id int(11) NOT NULL,
  topic_id int(11) NOT NULL,
  post_user_id int(11) NOT NULL,
  from_user_id int(11) NOT NULL,
  points int(11) NOT NULL,
  rate_date datetime default NULL,
  PRIMARY KEY  (karma_id),
  KEY jforum_karma_post_id (post_id),
  KEY jforum_karma_topic_id (topic_id),
  KEY jforum_karma_post_user_id (post_user_id),
  KEY jforum_karma_from_user_id (from_user_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;



-- ----------------------------
-- Table structure for table jforum_mail_integration
-- ----------------------------
DROP TABLE IF EXISTS jforum_mail_integration;
CREATE TABLE jforum_mail_integration (
  forum_id int(11) NOT NULL,
  forum_email varchar(100) NOT NULL,
  pop_username varchar(100) NOT NULL,
  pop_password varchar(100) NOT NULL,
  pop_host varchar(100) NOT NULL,
  pop_port int(11) default '110',
  pop_ssl tinyint(4) default '0',
  KEY forum_id (forum_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_moderation_log
-- ----------------------------
DROP TABLE IF EXISTS jforum_moderation_log;
CREATE TABLE jforum_moderation_log (
  log_id int(11) NOT NULL auto_increment,
  user_id int(11) NOT NULL,
  log_description text NOT NULL,
  log_original_message text,
  log_date datetime NOT NULL,
  log_type tinyint(4) default '0',
  post_id int(11) default '0',
  topic_id int(11) default '0',
  post_user_id int(11) default '0',
  PRIMARY KEY  (log_id),
  KEY jforum_moderation_user_id (user_id),
  KEY jforum_moderation_post_user_id (post_user_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;



-- ----------------------------
-- Table structure for table jforum_posts
-- ----------------------------
DROP TABLE IF EXISTS jforum_posts;
CREATE TABLE jforum_posts (
  post_id int(11) NOT NULL auto_increment,
  topic_id int(11) NOT NULL default '0',
  forum_id int(11) NOT NULL default '0',
  user_id int(11) NOT NULL default '0',
  post_time datetime default NULL,
  poster_ip varchar(15) default NULL,
  enable_bbcode tinyint(1) NOT NULL default '1',
  enable_html tinyint(1) NOT NULL default '1',
  enable_smilies tinyint(1) NOT NULL default '1',
  enable_sig tinyint(1) NOT NULL default '1',
  post_edit_time datetime default NULL,
  post_edit_count int(11) NOT NULL default '0',
  status tinyint(1) default '1',
  attach tinyint(1) default '0',
  need_moderate tinyint(1) default '0',
  PRIMARY KEY  (post_id),
  KEY jforum_posts_user_id (user_id),
  KEY jforum_posts_topic_id (topic_id),
  KEY jforum_posts_forum_id (forum_id),
  KEY jforum_posts_post_time (post_time),
  KEY jforum_posts_need_moderate (need_moderate)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_posts_text
-- ----------------------------
DROP TABLE IF EXISTS jforum_posts_text;
CREATE TABLE jforum_posts_text (
  post_id int(11) NOT NULL,
  post_text text,
  post_subject varchar(100) default NULL,
  PRIMARY KEY  (post_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_privmsgs
-- ----------------------------
DROP TABLE IF EXISTS jforum_privmsgs;
CREATE TABLE jforum_privmsgs (
  privmsgs_id int(11) NOT NULL auto_increment,
  privmsgs_type tinyint(4) NOT NULL default '0',
  privmsgs_subject varchar(255) NOT NULL default '',
  privmsgs_from_userid int(11) NOT NULL default '0',
  privmsgs_to_userid int(11) NOT NULL default '0',
  privmsgs_date datetime default NULL,
  privmsgs_ip varchar(15) NOT NULL default '',
  privmsgs_enable_bbcode tinyint(1) NOT NULL default '1',
  privmsgs_enable_html tinyint(1) NOT NULL default '0',
  privmsgs_enable_smilies tinyint(1) NOT NULL default '1',
  privmsgs_attach_sig tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (privmsgs_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_privmsgs_text
-- ----------------------------
DROP TABLE IF EXISTS jforum_privmsgs_text;
CREATE TABLE jforum_privmsgs_text (
  privmsgs_id int(11) NOT NULL,
  privmsgs_text text,
  PRIMARY KEY  (privmsgs_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_quota_limit
-- ----------------------------
DROP TABLE IF EXISTS jforum_quota_limit;
CREATE TABLE jforum_quota_limit (
  quota_limit_id int(11) NOT NULL auto_increment,
  quota_desc varchar(50) NOT NULL,
  quota_limit int(11) NOT NULL,
  quota_type tinyint(1) default '1',
  PRIMARY KEY  (quota_limit_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_ranks
-- ----------------------------
DROP TABLE IF EXISTS jforum_ranks;
CREATE TABLE jforum_ranks (
  rank_id int(11) NOT NULL auto_increment,
  rank_title varchar(50) NOT NULL default '',
  rank_min int(11) NOT NULL default '0',
  rank_special tinyint(1) default NULL,
  rank_image varchar(255) default NULL,
  PRIMARY KEY  (rank_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_role_values
-- ----------------------------
DROP TABLE IF EXISTS jforum_role_values;
CREATE TABLE jforum_role_values (
  role_id int(11) NOT NULL,
  role_value varchar(255) default NULL,
  KEY idx_role (role_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_roles
-- ----------------------------
DROP TABLE IF EXISTS jforum_roles;
CREATE TABLE jforum_roles (
  role_id int(11) NOT NULL auto_increment,
  group_id int(11) default '0',
  name varchar(255) NOT NULL,
  PRIMARY KEY  (role_id),
  KEY idx_group (group_id),
  KEY idx_name (name)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_sessions
-- ----------------------------
DROP TABLE IF EXISTS jforum_sessions;
CREATE TABLE jforum_sessions (
  session_id varchar(150) NOT NULL default '',
  session_user_id int(11) NOT NULL default '0',
  session_start datetime default NULL,
  session_time bigint(20) default '0',
  session_ip varchar(15) NOT NULL default '',
  session_page int(11) NOT NULL default '0',
  session_logged_int tinyint(1) default NULL,
  KEY idx_sessions_users (session_user_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_smilies
-- ----------------------------
DROP TABLE IF EXISTS jforum_smilies;
CREATE TABLE jforum_smilies (
  smilie_id int(11) NOT NULL auto_increment,
  code varchar(50) NOT NULL default '',
  url varchar(100) default NULL,
  disk_name varchar(255) default NULL,
  PRIMARY KEY  (smilie_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_themes
-- ----------------------------
DROP TABLE IF EXISTS jforum_themes;
CREATE TABLE jforum_themes (
  themes_id int(11) NOT NULL auto_increment,
  template_name varchar(30) NOT NULL default '',
  style_name varchar(30) NOT NULL default '',
  PRIMARY KEY  (themes_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_topics
-- ----------------------------
DROP TABLE IF EXISTS jforum_topics;
CREATE TABLE jforum_topics (
  topic_id int(11) NOT NULL auto_increment,
  forum_id int(11) NOT NULL default '0',
  topic_title varchar(100) NOT NULL default '',
  user_id int(11) NOT NULL default '0',
  topic_time datetime default NULL,
  topic_views int(11) default '1',
  topic_replies int(11) default '0',
  topic_status tinyint(3) default '0',
  topic_vote_id int(11) NOT NULL default '0',
  topic_type tinyint(3) default '0',
  topic_first_post_id int(11) default '0',
  topic_last_post_id int(11) NOT NULL default '0',
  topic_moved_id int(11) default '0',
  moderated tinyint(1) default '0',
  PRIMARY KEY  (topic_id),
  KEY jforum_topicsforum_id (forum_id),
  KEY jforum_topicsuser_id (user_id),
  KEY jforum_topicstopic_first_post_id (topic_first_post_id),
  KEY jforum_topicstopic_last_post_id (topic_last_post_id),
  KEY jforum_topicstopic_moved_id (topic_moved_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;



-- ----------------------------
-- Table structure for table jforum_topics_watch
-- ----------------------------
DROP TABLE IF EXISTS jforum_topics_watch;
CREATE TABLE jforum_topics_watch (
  topic_id int(11) NOT NULL,
  user_id int(11) NOT NULL,
  is_read tinyint(1) default '1',
  KEY jforum_topics_watch_topic (topic_id),
  KEY jforum_topics_watch_user (user_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_user_groups
-- ----------------------------
DROP TABLE IF EXISTS jforum_user_groups;
CREATE TABLE jforum_user_groups (
  group_id int(11) NOT NULL,
  user_id int(11) NOT NULL,
  KEY jforum_user_groups_group (group_id),
  KEY jforum_user_groups_user (user_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_users
-- ----------------------------
DROP TABLE IF EXISTS jforum_users;
CREATE TABLE jforum_users (
  user_id int(11) NOT NULL auto_increment,
  user_active tinyint(1) default NULL,
  username varchar(50) NOT NULL default '',
  user_password varchar(32) NOT NULL default '',
  user_session_time bigint(20) default '0',
  user_session_page int(11) NOT NULL default '0',
  user_lastvisit datetime default NULL,
  user_regdate datetime default NULL,
  user_level tinyint(4) default NULL,
  user_posts int(11) NOT NULL default '0',
  user_timezone varchar(5) NOT NULL default '',
  user_style tinyint(4) default NULL,
  user_lang varchar(255) NOT NULL default '',
  user_dateformat varchar(20) NOT NULL default '%d/%M/%Y %H:%i',
  user_new_privmsg int(11) NOT NULL default '0',
  user_unread_privmsg int(11) NOT NULL default '0',
  user_last_privmsg datetime default NULL,
  user_emailtime datetime default NULL,
  user_viewemail tinyint(1) default '0',
  user_attachsig tinyint(1) default '1',
  user_allowhtml tinyint(1) default '0',
  user_allowbbcode tinyint(1) default '1',
  user_allowsmilies tinyint(1) default '1',
  user_allowavatar tinyint(1) default '1',
  user_allow_pm tinyint(1) default '1',
  user_allow_viewonline tinyint(1) default '1',
  user_notify tinyint(1) default '1',
  user_notify_always tinyint(1) default '0',
  user_notify_text tinyint(1) default '0',
  user_notify_pm tinyint(1) default '1',
  user_popup_pm tinyint(1) default '1',
  rank_id int(11) default '0',
  user_avatar varchar(100) default NULL,
  user_avatar_type tinyint(4) NOT NULL default '0',
  user_email varchar(255) NOT NULL default '',
  user_icq varchar(15) default NULL,
  user_website varchar(255) default NULL,
  user_from varchar(100) default NULL,
  user_sig text,
  user_sig_bbcode_uid varchar(10) default NULL,
  user_aim varchar(255) default NULL,
  user_yim varchar(255) default NULL,
  user_msnm varchar(255) default NULL,
  user_occ varchar(100) default NULL,
  user_interests varchar(255) default NULL,
  user_biography text,
  user_actkey varchar(32) default NULL,
  gender char(1) default NULL,
  themes_id int(11) default NULL,
  deleted tinyint(1) default NULL,
  user_viewonline tinyint(1) default '1',
  security_hash varchar(32) default NULL,
  user_karma double default NULL,
  user_authhash varchar(32) default NULL,
  PRIMARY KEY  (user_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table jforum_vote_desc
-- ----------------------------
DROP TABLE IF EXISTS jforum_vote_desc;
CREATE TABLE jforum_vote_desc (
  vote_id int(11) NOT NULL auto_increment,
  topic_id int(11) NOT NULL default '0',
  vote_text varchar(255) NOT NULL default '',
  vote_start datetime NOT NULL,
  vote_length int(11) NOT NULL default '0',
  PRIMARY KEY  (vote_id),
  KEY topic_id (topic_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_vote_results
-- ----------------------------
DROP TABLE IF EXISTS jforum_vote_results;
CREATE TABLE jforum_vote_results (
  vote_id int(11) NOT NULL default '0',
  vote_option_id tinyint(4) NOT NULL default '0',
  vote_option_text varchar(255) NOT NULL default '',
  vote_result int(11) NOT NULL default '0',
  KEY vote_id (vote_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_vote_voters
-- ----------------------------
DROP TABLE IF EXISTS jforum_vote_voters;
CREATE TABLE jforum_vote_voters (
  vote_id int(11) NOT NULL default '0',
  vote_user_id int(11) NOT NULL default '0',
  vote_user_ip varchar(15) NOT NULL default '',
  KEY jforum_vote_voters_vote_id (vote_id),
  KEY jforum_vote_voters_vote_user_id (vote_user_id)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table jforum_words
-- ----------------------------
DROP TABLE IF EXISTS jforum_words;
CREATE TABLE jforum_words (
  word_id int(11) NOT NULL auto_increment,
  word varchar(100) NOT NULL default '',
  replacement varchar(100) NOT NULL default '',
  PRIMARY KEY  (word_id)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table letter
-- ----------------------------
DROP TABLE IF EXISTS letter;
CREATE TABLE letter (
  id int(11) NOT NULL,
  nhsno varchar(100) NOT NULL default '',
  unitcode varchar(20) NOT NULL default '',
  date datetime default '0000-00-00 00:00:00',
  type varchar(100) default '',
  content text,
  PRIMARY KEY  (id),
  KEY letter_nhsno_unitcode (nhsno,unitcode)
  
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;



-- ----------------------------
-- Table structure for table log
-- ----------------------------
DROP TABLE IF EXISTS log;
CREATE TABLE log (
  id bigint(20) NOT NULL default '0',
  date datetime NOT NULL default '0000-00-00 00:00:00',
  actor varchar(100) NOT NULL default '',
  action varchar(100) NOT NULL default '',
  nhsno varchar(100) default '',
  user varchar(100) default '',
  unitcode varchar(100) default NULL,
  extrainfo text,
  PRIMARY KEY  (id),
  KEY log_index_nhsno (nhsno),
  KEY log_index_action (action),
  KEY log_index_date (date)
  
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;



-- ----------------------------
-- Table structure for table medicine
-- ----------------------------
DROP TABLE IF EXISTS medicine;
CREATE TABLE medicine (
  id int(11) NOT NULL auto_increment,
  nhsno varchar(20) NOT NULL default '',
  chino varchar(20) default NULL,
  unitcode varchar(20) NOT NULL default '',
  startdate datetime default '0000-00-00 00:00:00',
  name varchar(100) default '',
  dose varchar(100) default '',
  enddate datetime default '0000-00-00 00:00:00',
  PRIMARY KEY  (id),
  KEY  medicine_nhsno_unitcode (nhsno,unitcode)
  
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table news
-- ----------------------------
DROP TABLE IF EXISTS news;
CREATE TABLE news (
  id int(11) NOT NULL default '0',
  datestamp datetime NOT NULL default '0000-00-00 00:00:00',
  unitcode varchar(100) NOT NULL default '',
  admin tinyint(1) NOT NULL default '0',
  patient tinyint(1) NOT NULL default '0',
  everyone tinyint(10) NOT NULL default '0',
  headline varchar(255) NOT NULL default '',
  body text NOT NULL,
  PRIMARY KEY  (id)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table patient
-- ----------------------------
DROP TABLE IF EXISTS patient;
CREATE TABLE patient (
  nhsno varchar(100) NOT NULL default '',
  surname varchar(100) default '',
  forename varchar(100) default '',
  dateofbirth varchar(100) default '',
  sex varchar(100) default '',
  address1 varchar(100) default '',
  address2 varchar(100) default '',
  address3 varchar(100) default '',
  postcode varchar(100) default '',
  telephone1 varchar(100) default '',
  telephone2 varchar(100) default '',
  mobile varchar(100) default '',
  centreCode varchar(100) NOT NULL default '',
  diagnosis varchar(100) default '',
  treatment varchar(100) default '',
  transplantstatus varchar(100) default '',
  hospitalnumber varchar(100) default '',
  gpname varchar(100) default '',
  gpaddress1 varchar(100) default '',
  gpaddress2 varchar(100) default '',
  gpaddress3 varchar(100) default '',
  gppostcode varchar(100) default '',
  gptelephone varchar(100) default '',
  PRIMARY KEY  (nhsno,centreCode)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table rdc_genetic_test
-- ----------------------------
DROP TABLE IF EXISTS rdc_genetic_test;
CREATE TABLE rdc_genetic_test (
  id bigint(20) NOT NULL auto_increment,
  radar_no bigint(20) NOT NULL,
  testsDone int(11) NOT NULL,
  labWhereTestWasDone text,
  testDoneOn text,
  referenceNumber varchar(255) default NULL,
  whatResultsShowed text,
  keyEvidence text,
  PRIMARY KEY  (id)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table rdr_alport_deafness
-- ----------------------------
DROP TABLE IF EXISTS rdr_alport_deafness;
CREATE TABLE rdr_alport_deafness (
  id bigint(20) NOT NULL auto_increment,
  radar_no bigint(20) NOT NULL,
  evidenceOfDeafness int(11) NOT NULL,
  ageProblemFirstNoticed int(11) default NULL,
  ageStartedUsingHearingAid int(11) default NULL,
  PRIMARY KEY  (id)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table rdr_prd_code
-- ----------------------------
DROP TABLE IF EXISTS rdr_prd_code;
CREATE TABLE rdr_prd_code (
  ERA_EDTA_PRD_code varchar(20) NOT NULL,
  ERA_EDTA_primaryRenalDiagnosisTerm varchar(200) default NULL,
  histology tinyint(1) default NULL,
  clinicalHistory tinyint(1) default NULL,
  familyHistory tinyint(1) default NULL,
  clinicalExam tinyint(1) default NULL,
  biochemistry tinyint(1) default NULL,
  immunology tinyint(1) default NULL,
  urineAnalysis tinyint(1) default NULL,
  imaging tinyint(1) default NULL,
  geneTest tinyint(1) default NULL,
  otherCriteriaAndNotes varchar(1000) default NULL,
  SNOMED_CT_conceptIdentifierForFocusConcept varchar(50) default NULL,
  SNOMED_CT_fullySpecifiedName varchar(200) default NULL,
  SNOMED_CT_expressionConstraint varchar(200) default NULL,
  majorHeading varchar(200) default NULL,
  mappingToOldPRDCode int(10) default NULL,
  mappingToOldPRDTerm varchar(200) default NULL,
  ERA_EDTA_defaultSortOrder int(10) default NULL,
  geneticsHomeReferenceLink varchar(200) default NULL,
  nationalCenterForBiotechnologyLink varchar(200) default NULL,
  ICD_10_code varchar(200) default NULL,
  ICD10_rubricTerm varchar(200) default NULL,
  alternativesearchTerms varchar(200) default NULL,
  PRIMARY KEY  (ERA_EDTA_PRD_code)
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table rdr_diagnosis_mapping
-- ----------------------------
DROP TABLE IF EXISTS rdr_diagnosis_mapping;
CREATE TABLE rdr_diagnosis_mapping (
  workingGroup varchar(100) NOT NULL,
  PRDCode varchar(20) NOT NULL,
  ordering int(10) default NULL,
  PRIMARY KEY  (workingGroup,PRDCode),
  CONSTRAINT rdr_diagnosis_mapping_ibfk_1 FOREIGN KEY (PRDCode) REFERENCES rdr_prd_code (ERA_EDTA_PRD_code) ON DELETE CASCADE,
  KEY  rdr_diagnosis_mapping_ibfk_1 (PRDCode)
  
) /*! ENGINE=InnoDB DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table result_heading
-- ----------------------------
DROP TABLE IF EXISTS result_heading;
CREATE TABLE result_heading (
  headingcode varchar(20) NOT NULL default '',
  heading varchar(30) NOT NULL default '',
  rollover varchar(50) NOT NULL default 'Click for info',
  link varchar(100) NOT NULL default '',
  panel int(11) NOT NULL default '0',
  panelorder int(11) NOT NULL default '0',
  PRIMARY KEY  (headingcode)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table splashpage
-- ----------------------------
DROP TABLE IF EXISTS splashpage;
CREATE TABLE splashpage (
  id int(10) NOT NULL,
  name varchar(20) NOT NULL,
  live tinyint(1) NOT NULL,
  headline varchar(100) NOT NULL,
  bodytext text NOT NULL,
  unitcode varchar(20) NOT NULL,
  PRIMARY KEY  (id)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table splashpageuserseen
-- ----------------------------
DROP TABLE IF EXISTS splashpageuserseen;
CREATE TABLE splashpageuserseen (
  id int(10) NOT NULL,
  username varchar(100) NOT NULL,
  splashpageid int(10) NOT NULL,
  PRIMARY KEY  (id),
  KEY splashpageuserseen_username_splashpageid (username,splashpageid)
  
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table sysdiagrams
-- ----------------------------
DROP TABLE IF EXISTS sysdiagrams;
CREATE TABLE sysdiagrams (
  name varchar(128) NOT NULL,
  principal_id int(11) NOT NULL,
  diagram_id int(11) NOT NULL,
  version int(11) default NULL,
  definition blob
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_6month
-- ----------------------------
DROP TABLE IF EXISTS tbl_6month;
CREATE TABLE tbl_6month (
  fuID int(11) NOT NULL,
  RADAR_NO int(11) default NULL,
  DATE_FUP datetime default NULL,
  RELAP_SINCE_LAST bit(1) default NULL,
  RELAP_LEN int(11) default NULL,
  VIRAL_TRIG int(11) default NULL,
  IMMUN_TRIG int(11) default NULL,
  OTHER_TRIG int(11) default NULL,
  IMMUNOSUP_INC bit(1) default NULL,
  IMMUNOSUP_DOSE int(11) default NULL,
  IMMUNOSUP_DUR int(11) default NULL,
  PLASMA_EXCH int(11) default NULL,
  PLASMA_EXCH_NO int(11) default NULL,
  RESPONSE_TO int(11) default NULL,
  MAX_PR_CREAT_RATIO int(11) default NULL,
  MIN_SER_ALB int(11) default NULL,
  COMP1 int(11) default NULL,
  COMP2 int(11) default NULL,
  COMP3 int(11) default NULL,
  COMP4 int(11) default NULL,
  OTHER_COMP varchar(50) default NULL,
  DATE_START_DIAL datetime default NULL,
  DIAL_TYPE int(11) default NULL,
  DATE_TRANSPLANT datetime default NULL,
  TRANS_TYPE int(11) default NULL,
  TRANS_RECURR bit(1) default NULL,
  DATE_TX_REJECT datetime default NULL,
  DATE_BX datetime default NULL,
  DATE_NEPHRECT datetime default NULL,
  DRUG1 varchar(50) default NULL,
  DRUG2 varchar(50) default NULL,
  DRUG3 varchar(50) default NULL,
  DRUG4 varchar(50) default NULL,
  DRUG5 varchar(50) default NULL,
  DRUG6 varchar(50) default NULL,
  SIG_CHANGE_STATUS char(10) default NULL
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_adminusers
-- ----------------------------
DROP TABLE IF EXISTS tbl_adminusers;
CREATE TABLE tbl_adminusers (
  uID int(11) NOT NULL auto_increment,
  uName varchar(30) default NULL,
  uEmail varchar(50) default NULL,
  uPass varbinary(50) default NULL,
  uUserName varbinary(50) default NULL,
  PRIMARY KEY  (uID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_centres
-- ----------------------------
DROP TABLE IF EXISTS tbl_centres;
CREATE TABLE tbl_centres (
  cID int(11) NOT NULL auto_increment,
  cName varchar(80) default NULL,
  cAbbrev varchar(15) default NULL,
  cCountry int(11) default NULL,
  PRIMARY KEY  (cID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_clin_pres
-- ----------------------------
DROP TABLE IF EXISTS tbl_clin_pres;
CREATE TABLE tbl_clin_pres (
  cID int(11) NOT NULL auto_increment,
  CLIN_PRES varchar(20) default NULL,
  PRIMARY KEY  (cID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_clinicaldata
-- ----------------------------
DROP TABLE IF EXISTS tbl_clinicaldata;
CREATE TABLE tbl_clinicaldata (
  cID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) NOT NULL,
  DATE_CLIN_PIC datetime default NULL,
  HEIGHT decimal(8,1) default NULL,
  WEIGHT decimal(8,2) default NULL,
  COURSE_DIS int(11) default NULL,
  SYS_BP int(11) default NULL,
  DIA_BP int(11) default NULL,
  MAP_BP int(11) default NULL,
  DIALYSIS_REQ int(11) default NULL,
  DATE_BX datetime default NULL,
  OEDEMA bit(1) default NULL,
  ANAEMIA bit(1) default NULL,
  HYPOVAL bit(1) default NULL,
  FEVER bit(1) default NULL,
  INFECTION bit(1) default NULL,
  INFECTION_DETAIL varchar(50) default NULL,
  INFECTION_TYPE varchar(50) default NULL,
  THROMBOSIS bit(1) default NULL,
  THROMBOSIS_DETAIL varchar(250) default NULL,
  COMP_THROMBOSIS bit(1) default NULL,
  COMP_THROMBOSIS_DETAIL text,
  PERITONITIS bit(1) default NULL,
  PUL_OED bit(1) default NULL,
  HTH_REQ_TMT bit(1) default NULL,
  PREC_INF bit(1) default NULL,
  PREC_INF_DETAIL varchar(150) default NULL,
  CLIN_EV_CHR_INF bit(1) default NULL,
  CLIN_EV_CHR_INF_DETAIL varchar(150) default NULL,
  DIABETES smallint(6) default NULL,
  URTICARIA smallint(6) default NULL,
  RASH bit(1) default NULL,
  RASH_DETAIL varchar(50) default NULL,
  PART_LIPODYS bit(1) default NULL,
  OPTHALM bit(1) default NULL,
  OPTHALM_DETAIL varchar(50) default NULL,
  IMMUNIS_TRIGGER bit(1) default NULL,
  COMMENTS text,
  PHENOTYPE1 int(11) default NULL,
  PHENOTYPE2 int(11) default NULL,
  PHENOTYPE3 int(11) default NULL,
  PHENOTYPE4 int(11) default NULL,
  SIG_DIAG1 varchar(30) default NULL,
  SIG_DIAG2 varchar(30) default NULL,
  TX_LISTED bit(1) default NULL,
  CKD_STAGE int(11) default NULL,
  SEQ_NO int(11) default NULL,
  PRIMARY KEY  (cID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_complication
-- ----------------------------
DROP TABLE IF EXISTS tbl_complication;
CREATE TABLE tbl_complication (
  cmpID int(11) NOT NULL auto_increment,
  cmpDesc varchar(50) default NULL,
  PRIMARY KEY  (cmpID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_consultants
-- ----------------------------
DROP TABLE IF EXISTS tbl_consultants;
CREATE TABLE tbl_consultants (
  cID int(11) NOT NULL auto_increment,
  cSNAME varchar(50) default NULL,
  cFNAME varchar(50) default NULL,
  cCentre int(11) default NULL,
  PRIMARY KEY  (cID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_country
-- ----------------------------
DROP TABLE IF EXISTS tbl_country;
CREATE TABLE tbl_country (
  cID int(11) NOT NULL auto_increment,
  cName varchar(50) default NULL,
  PRIMARY KEY  (cID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_demographics
-- ----------------------------
DROP TABLE IF EXISTS tbl_demographics;
CREATE TABLE tbl_demographics (
  RADAR_NO int(11) NOT NULL auto_increment,
  RR_NO varchar(10) default NULL,
  DATE_REG datetime default NULL,
  NHS_NO varbinary(50) default NULL,
  HOSP_NO varbinary(50) default NULL,
  UKT_NO bigint(20) default NULL,
  CHI_NO bigint(20) default NULL,
  SNAME varbinary(50) default NULL,
  SNAME_ALIAS varbinary(50) default NULL,
  FNAME varbinary(50) default NULL,
  DOB varbinary(50) default NULL,
  AGE int(11) default NULL,
  SEX int(11) default NULL,
  ETHNIC_GP varchar(6) default NULL,
  ADD1 varbinary(50) default NULL,
  ADD2 varbinary(50) default NULL,
  ADD3 varbinary(50) default NULL,
  ADD4 varbinary(50) default NULL,
  POSTCODE varbinary(50) default NULL,
  POSTCODE_OLD varbinary(50) default NULL,
  CONSENT bit(1) default NULL,
  DATE_BAPN_REG datetime default NULL,
  CONS_NEPH varchar(6) default NULL,
  RENAL_UNIT int(11) default NULL,
  RENAL_UNIT_2 int(11) default NULL,
  STATUS int(11) default NULL,
  RDG varchar(100) default NULL,
  emailAddress varchar(50) default NULL,
  phone1 varchar(20) default NULL,
  phone2 varchar(20) default NULL,
  mobile varchar(20) default NULL,
  RRT_modality int(10) default NULL,
  genericDiagnosis varchar(20) default NULL,
  dateOfGenericDiagnosis datetime default NULL,
  otherClinicianAndContactInfo varchar(500) default NULL,
  comments varchar(500) default NULL,
  republicOfIrelandId varchar(20) default NULL,
  isleOfManId varchar(20) default NULL,
  channelIslandsId varchar(20) default NULL,
  indiaId varchar(20) default NULL,
  generic tinyint(1) default NULL,
  PRIMARY KEY  (RADAR_NO),
  KEY fk_RDG (RDG),
  KEY fk_genericDiagnosis (genericDiagnosis)
  
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table tbl_diagcode
-- ----------------------------
DROP TABLE IF EXISTS tbl_diagcode;
CREATE TABLE tbl_diagcode (
  dcID int(11) NOT NULL auto_increment,
  dcDesc varchar(70) default NULL,
  dcAbbr varchar(15) default NULL,
  PRIMARY KEY  (dcID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_diagnosis
-- ----------------------------
DROP TABLE IF EXISTS tbl_diagnosis;
CREATE TABLE tbl_diagnosis (
  dID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  DATE_DIAG datetime default NULL,
  DIAG int(11) default NULL,
  DIAG_TXT varchar(100) default NULL,
  AGE_AT_DIAG decimal(4,1) default NULL,
  HEIGHT_FIRST_VISIT decimal(4,1) default NULL,
  BX_PROVEN_DIAG varchar(1) default NULL,
  PREPUB_DIAG bit(1) default NULL,
  CLIN_PRES int(11) default NULL,
  CLIN_PRES_B int(11) default NULL,
  GENE_MUT varchar(50) default NULL,
  GENE_MUT_TEXT varchar(100) default NULL,
  KARYOTYPE varchar(50) default NULL,
  KARYOTYPE_OTHER varchar(100) default NULL,
  DATE_ONSET_RENALDIS datetime default NULL,
  CONSANGUINITY int(11) default NULL,
  FAM_HIST int(11) default NULL,
  REL1 varchar(20) default NULL,
  REL1_RADAR int(11) default NULL,
  REL2 varchar(20) default NULL,
  REL2_RADAR int(11) default NULL,
  REL3 varchar(20) default NULL,
  REL3_RADAR int(11) default NULL,
  REL4 varchar(20) default NULL,
  REL4_RADAR int(11) default NULL,
  REL5 varchar(20) default NULL,
  REL5_RADAR int(11) default NULL,
  REL6 varchar(20) default NULL,
  REL6_RADAR int(11) default NULL,
  SIG_DIAG1 varchar(50) default NULL,
  SIG_DIAG2 varchar(50) default NULL,
  STEROID_RESIST int(11) default NULL,
  DATE_ESRF datetime default NULL,
  MUTATION_1 bit(1) default NULL,
  MUTATION_1S bit(1) default NULL,
  MUTATION_2 bit(1) default NULL,
  MUTATION_2S bit(1) default NULL,
  MUTATION_3 bit(1) default NULL,
  MUTATION_3S bit(1) default NULL,
  MUTATION_4 bit(1) default NULL,
  MUTATION_4S bit(1) default NULL,
  MUTATION_5 bit(1) default NULL,
  MUTATION_5S bit(1) default NULL,
  MUTATION_6 bit(1) default NULL,
  MUTATION_6S bit(1) default NULL,
  MUTATION_7 bit(1) default NULL,
  MUTATION_7S bit(1) default NULL,
  MUTATION_8 bit(1) default NULL,
  MUTATION_8S bit(1) default NULL,
  MUTATION_9 bit(1) default NULL,
  MUTATION_9S bit(1) default NULL,
  PRIMARY KEY  (dID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_diseasedata
-- ----------------------------
DROP TABLE IF EXISTS tbl_diseasedata;
CREATE TABLE tbl_diseasedata (
  dID int(11) NOT NULL auto_increment,
  dText text,
  PRIMARY KEY  (dID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_ethnicity
-- ----------------------------
DROP TABLE IF EXISTS tbl_ethnicity;
CREATE TABLE tbl_ethnicity (
  eID int(11) NOT NULL auto_increment,
  eName varchar(50) default NULL,
  eCode varchar(50) default NULL,
  PRIMARY KEY  (eID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_genemutation
-- ----------------------------
DROP TABLE IF EXISTS tbl_genemutation;
CREATE TABLE tbl_genemutation (
  gmID int(11) NOT NULL auto_increment,
  GENE_MUTATION varchar(30) default NULL,
  PRIMARY KEY  (gmID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_hd_modality
-- ----------------------------
DROP TABLE IF EXISTS tbl_hd_modality;
CREATE TABLE tbl_hd_modality (
  hdID int(11) NOT NULL auto_increment,
  hdType varchar(75) default NULL,
  PRIMARY KEY  (hdID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_hdial
-- ----------------------------
DROP TABLE IF EXISTS tbl_hdial;
CREATE TABLE tbl_hdial (
  hdID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  DATE_START_HDIAL datetime default NULL,
  PRIMARY KEY  (hdID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_hospitalisation
-- ----------------------------
DROP TABLE IF EXISTS tbl_hospitalisation;
CREATE TABLE tbl_hospitalisation (
  hID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  DATE_ADMIT datetime default NULL,
  DATE_DISCHARGE datetime default NULL,
  REASON_ADMIT varchar(250) default NULL,
  COMMENT text,
  PRIMARY KEY  (hID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_immunosupp
-- ----------------------------
DROP TABLE IF EXISTS tbl_immunosupp;
CREATE TABLE tbl_immunosupp (
  imID int(11) NOT NULL auto_increment,
  imDesc varchar(50) default NULL,
  `Group` int(11) default NULL,
  PRIMARY KEY  (imID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_immunsup_treatment
-- ----------------------------
DROP TABLE IF EXISTS tbl_immunsup_treatment;
CREATE TABLE tbl_immunsup_treatment (
  tID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  IMMUNSUP_DRUG_STARTDATE datetime default NULL,
  IMMUNSUP_DRUG_ENDDATE datetime default NULL,
  IMMUNSUP_DRUG int(11) default NULL,
  CYCLOPHOS_TOT_DOSE decimal(5,3) default NULL,
  FIRST_FLAG bit(1) default NULL,
  PRIMARY KEY  (tID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_issuetracker
-- ----------------------------
DROP TABLE IF EXISTS tbl_issuetracker;
CREATE TABLE tbl_issuetracker (
  bID int(11) NOT NULL auto_increment,
  bType varchar(50) default NULL,
  bPage varchar(50) default NULL,
  bDateLogged datetime default NULL,
  bDateResolved datetime default NULL,
  bDesc text,
  bComment text,
  bPriority varchar(20) default NULL,
  bStatus varchar(50) default NULL,
  bUpdated datetime default NULL,
  PRIMARY KEY  (bID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_karyotype
-- ----------------------------
DROP TABLE IF EXISTS tbl_karyotype;
CREATE TABLE tbl_karyotype (
  kID int(11) NOT NULL auto_increment,
  KARYOTYPE varchar(50) default NULL,
  PRIMARY KEY  (kID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_labdata
-- ----------------------------
DROP TABLE IF EXISTS tbl_labdata;
CREATE TABLE tbl_labdata (
  labID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  DATE_LAB_RES datetime default NULL,
  CREAT_SER int(11) default NULL,
  PROTEIN int(11) default NULL,
  ALBUMIN int(11) default NULL,
  UREA_SER decimal(3,1) default NULL,
  SODIUM int(11) default NULL,
  POTASSIUM decimal(2,1) default NULL,
  PHOS decimal(3,2) default NULL,
  PROT_CREAT_RAT decimal(6,1) default NULL,
  ALB_CREAT_RAT decimal(5,1) default NULL,
  WBC decimal(3,1) default NULL,
  HB decimal(3,1) default NULL,
  NEUTRO decimal(3,1) default NULL,
  PLATELETS int(11) default NULL,
  FERRITIN int(11) default NULL,
  CHOL_TOTAL decimal(3,1) default NULL,
  CHOL_HDL decimal(3,1) default NULL,
  CHOL_LDL decimal(3,1) default NULL,
  TRIG decimal(3,1) default NULL,
  CREAT_CLEAR_24_URINE int(11) default NULL,
  CREAT_CLEAR_RADIO int(11) default NULL,
  CREAT_CLEAR_SCHZ int(11) default NULL,
  THYROX decimal(4,1) default NULL,
  TSH decimal(4,2) default NULL,
  ANCA int(11) default NULL,
  ELISA_ASS int(11) default NULL,
  ENA int(11) default NULL,
  ANA int(11) default NULL,
  DNA_ANTIB varchar(50) default NULL,
  DNA_ANTI_DS int(11) default NULL,
  CRYOGLOB varchar(50) default NULL,
  ANTI_GBM varchar(50) default NULL,
  IGG decimal(4,1) default NULL,
  IGA decimal(4,1) default NULL,
  IGM decimal(4,1) default NULL,
  COMP_C3 decimal(4,2) default NULL,
  COMP_C4 decimal(4,2) default NULL,
  COMP_OTHER text,
  C3_NEPH_FAC int(11) default NULL,
  ANTI_SLT int(11) default NULL,
  INR decimal(2,1) default NULL,
  CRP int(11) default NULL,
  ANTI_STREP_O int(11) default NULL,
  HEP_B int(11) default NULL,
  HEP_C int(11) default NULL,
  HIV int(11) default NULL,
  DNA_FACTOR_H bit(1) default NULL,
  EBV int(11) default NULL,
  CMV int(11) default NULL,
  CMV_SYM bit(1) default NULL,
  BKV bit(1) default NULL,
  BKV_SYM bit(1) default NULL,
  HANTAVIRUS bit(1) default NULL,
  PARVO_ANTIB int(11) default NULL,
  OTHER_INFECT bit(1) default NULL,
  OTHER_INFECT_SP varchar(50) default NULL,
  UR_VOL_24H int(11) default NULL,
  UR_VOL_24H_COND int(11) default NULL,
  HAEMATURIA int(11) default NULL,
  ALBUMINURIA int(11) default NULL,
  DYS_ERYTH_URINE int(11) default NULL,
  RED_CCASTS_URINE int(11) default NULL,
  WBC_CASTS_URINE int(11) default NULL,
  LEUC_URINE bit(1) default NULL,
  NITRITE bit(1) default NULL,
  BACT_URINE bit(1) default NULL,
  GLUC_URINE bit(1) default NULL,
  OSMOLARITY varchar(50) default NULL,
  PROTEINURIA_DIP int(11) default NULL,
  SEQ_NO int(11) default NULL,
  ANTI_CLQ decimal(4,1) default NULL,
  PRIMARY KEY  (labID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_monoclonal
-- ----------------------------
DROP TABLE IF EXISTS tbl_monoclonal;
CREATE TABLE tbl_monoclonal (
  mID int(11) NOT NULL auto_increment,
  mDesc varchar(20) default NULL,
  PRIMARY KEY  (mID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_pathology
-- ----------------------------
DROP TABLE IF EXISTS tbl_pathology;
CREATE TABLE tbl_pathology (
  pID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  BX_DATE datetime default NULL,
  NAT_TRANSP_KID int(11) default NULL,
  LATERALITY_BX int(11) default NULL,
  SAMPLE_LAB_NO varchar(20) default NULL,
  PATHDIAG int(11) default NULL,
  GLOM_TOTAL_NO int(11) default NULL,
  GLOM_GLOB_SCL int(11) default NULL,
  GLOM_SEQ_SCL int(11) default NULL,
  GLOM_CELL_CRES int(11) default NULL,
  GLOM_FIB_CRES int(11) default NULL,
  GLOM_END_HYPER int(11) default NULL,
  GLOM_FIN_NEC int(11) default NULL,
  GLOM_ANY_OTH_FEAT varchar(50) default NULL,
  TUB_ATROP_IF_EST int(11) default NULL,
  TUB_ATROP_IF_MEAS decimal(3,1) default NULL,
  TUB_OTHER_FEAT varchar(150) default NULL,
  INTER_INFLAM_INFIL varchar(150) default NULL,
  ART_ABNORMAL varchar(150) default NULL,
  IMM_HIST_FIND varchar(150) default NULL,
  ELECT_MSCOPE_FIND varchar(150) default NULL,
  IMAGE_URL1 varchar(150) default NULL,
  IMAGE_URL2 varchar(150) default NULL,
  IMAGE_URL3 varchar(150) default NULL,
  IMAGE_URL4 varchar(150) default NULL,
  IMAGE_URL5 varchar(150) default NULL,
  PATH_TXT text,
  SEQ_NO int(11) default NULL,
  PRIMARY KEY  (pID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_patient_users
-- ----------------------------
DROP TABLE IF EXISTS tbl_patient_users;
CREATE TABLE tbl_patient_users (
  pID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  pUserName varchar(50) default NULL,
  pPassWord varbinary(50) default NULL,
  pDOB datetime default NULL,
  pDateReg datetime default NULL,
  PRIMARY KEY  (pID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_pd_modality
-- ----------------------------
DROP TABLE IF EXISTS tbl_pd_modality;
CREATE TABLE tbl_pd_modality (
  pdID int(11) NOT NULL auto_increment,
  pdType varchar(75) default NULL,
  PRIMARY KEY  (pdID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_pdial
-- ----------------------------
DROP TABLE IF EXISTS tbl_pdial;
CREATE TABLE tbl_pdial (
  pdID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  DATE_START_PDIAL datetime default NULL,
  PRIMARY KEY  (pdID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_phenotypes
-- ----------------------------
DROP TABLE IF EXISTS tbl_phenotypes;
CREATE TABLE tbl_phenotypes (
  pID int(11) NOT NULL auto_increment,
  pDesc varchar(75) default NULL,
  PRIMARY KEY  (pID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_plasmaph
-- ----------------------------
DROP TABLE IF EXISTS tbl_plasmaph;
CREATE TABLE tbl_plasmaph (
  plID int(11) NOT NULL auto_increment,
  RENAL_NO int(11) default NULL,
  DATE_STARTED_PLASMAPH datetime default NULL,
  DUR_PLASMAPH int(11) default NULL,
  NO_EXCH_PLASMAPH int(11) default NULL,
  PRIMARY KEY  (plID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_relapse
-- ----------------------------
DROP TABLE IF EXISTS tbl_relapse;
CREATE TABLE tbl_relapse (
  relID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  DATE_ONSET_RELAP datetime default NULL,
  RELAP_TX_NAT bit(1) default NULL,
  TRIG_VIRAL varchar(50) default NULL,
  TRIG_IMMUN varchar(50) default NULL,
  TRIG_OTHER varchar(50) default NULL,
  RELAP_DRUG_1 varchar(50) default NULL,
  RELAP_DRUG_2 varchar(50) default NULL,
  RELAP_DRUG_3 varchar(50) default NULL,
  REMISS_ACHIEVE int(11) default NULL,
  DATE_REMISSION datetime default NULL,
  SEQ_NO int(11) default NULL,
  PRIMARY KEY  (relID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_relative
-- ----------------------------
DROP TABLE IF EXISTS tbl_relative;
CREATE TABLE tbl_relative (
  rID int(11) NOT NULL auto_increment,
  RELATIVE varchar(20) default NULL,
  PRIMARY KEY  (rID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_rrt_hd
-- ----------------------------
DROP TABLE IF EXISTS tbl_rrt_hd;
CREATE TABLE tbl_rrt_hd (
  hID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  HD_TMT_MODALITY int(11) default NULL,
  DATE_START_HDIAL datetime default NULL,
  DATE_STOP_HDIAL datetime default NULL,
  PRIMARY KEY  (hID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_rrt_modality
-- ----------------------------
DROP TABLE IF EXISTS tbl_rrt_modality;
CREATE TABLE tbl_rrt_modality (
  mID int(11) NOT NULL auto_increment,
  mType varchar(50) default NULL,
  `Group` int(11) default NULL,
  PRIMARY KEY  (mID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_rrt_pd
-- ----------------------------
DROP TABLE IF EXISTS tbl_rrt_pd;
CREATE TABLE tbl_rrt_pd (
  pID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  PD_TMT_MODALITY int(11) default NULL,
  DATE_START_PD datetime default NULL,
  DATE_STOP_PD datetime default NULL,
  PRIMARY KEY  (pID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_rrt_plasma
-- ----------------------------
DROP TABLE IF EXISTS tbl_rrt_plasma;
CREATE TABLE tbl_rrt_plasma (
  plID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  PLASMAPH varchar(20) default NULL,
  DATE_START_PLASMAPH datetime default NULL,
  DATE_STOP_PLASMAPH datetime default NULL,
  NO_EXCH_PLASMAPH varchar(10) default NULL,
  DUR_PLASMAPH int(11) default NULL,
  RESPONSE_TO_PLASMA int(11) default NULL,
  PRIMARY KEY  (plID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_rrt_plasma_lu
-- ----------------------------
DROP TABLE IF EXISTS tbl_rrt_plasma_lu;
CREATE TABLE tbl_rrt_plasma_lu (
  exID int(11) NOT NULL auto_increment,
  exDesc varchar(50) default NULL,
  PRIMARY KEY  (exID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_rrt_treatment
-- ----------------------------
DROP TABLE IF EXISTS tbl_rrt_treatment;
CREATE TABLE tbl_rrt_treatment (
  tID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  MODALITY int(11) default NULL,
  DATE_START datetime default NULL,
  DATE_STOP datetime default NULL,
  UNIT_CODE int(11) default NULL,
  FIRST_FLAG bit(1) default NULL,
  PRIMARY KEY  (tID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_rt_modality
-- ----------------------------
DROP TABLE IF EXISTS tbl_rt_modality;
CREATE TABLE tbl_rt_modality (
  mID int(11) NOT NULL,
  mDesc varchar(50) default NULL,
  PRIMARY KEY  (mID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_sex
-- ----------------------------
DROP TABLE IF EXISTS tbl_sex;
CREATE TABLE tbl_sex (
  sID int(11) NOT NULL auto_increment,
  sType varchar(14) default NULL,
  PRIMARY KEY  (sID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_status
-- ----------------------------
DROP TABLE IF EXISTS tbl_status;
CREATE TABLE tbl_status (
  sID int(11) NOT NULL auto_increment,
  sDesc varchar(50) default NULL,
  sAbbrev varchar(20) default NULL,
  PRIMARY KEY  (sID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_test
-- ----------------------------
DROP TABLE IF EXISTS tbl_test;
CREATE TABLE tbl_test (
  tID smallint(6) NOT NULL auto_increment,
  tOne bit(1) default NULL,
  tTwo bit(1) default NULL,
  tThree char(10) default NULL,
  PRIMARY KEY  (tID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_therapy
-- ----------------------------
DROP TABLE IF EXISTS tbl_therapy;
CREATE TABLE tbl_therapy (
  tID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  SIG_CHANGE_STATUS varchar(20) default NULL,
  P_NSAID bit(1) default NULL,
  NSAID bit(1) default NULL,
  P_DIURETIC bit(1) default NULL,
  DIURETIC bit(1) default NULL,
  P_ANTI_HTN bit(1) default NULL,
  ANTI_HTN bit(1) default NULL,
  P_ACE_INHIB bit(1) default NULL,
  ACE_INHIB bit(1) default NULL,
  P_ARB_ANTAG bit(1) default NULL,
  ARB_ANTAG bit(1) default NULL,
  P_CA_CH_BLOCK bit(1) default NULL,
  CA_CH_BLOCK bit(1) default NULL,
  P_B_BLOCK bit(1) default NULL,
  B_BLOCK bit(1) default NULL,
  P_OTHER_ANTI_HTN bit(1) default NULL,
  OTHER_ANTI_HTN bit(1) default NULL,
  P_INSULIN bit(1) default NULL,
  INSULIN bit(1) default NULL,
  P_LIP_LOWER_AG bit(1) default NULL,
  LIP_LOWER_AG bit(1) default NULL,
  P_EPO bit(1) default NULL,
  EPO bit(1) default NULL,
  P_OTHER_DRUG1 varchar(50) default NULL,
  OTHER_DRUG1 varchar(50) default NULL,
  P_OTHER_DRUG2 varchar(50) default NULL,
  OTHER_DRUG2 varchar(50) default NULL,
  P_OTHER_DRUG3 varchar(50) default NULL,
  OTHER_DRUG3 varchar(50) default NULL,
  P_OTHER_DRUG4 varchar(50) default NULL,
  OTHER_DRUG4 varchar(50) default NULL,
  P_IMMUN_SUP bit(1) default NULL,
  IMMUN_SUP bit(1) default NULL,
  P_IMMUN_SUP_DRUG varchar(50) default NULL,
  IMMUN_SUP_DRUG varchar(50) default NULL,
  MONOCLONAL_YN bit(1) default NULL,
  MONOCLONAL_NAME varchar(50) default NULL,
  DATE_TREAT datetime default NULL,
  TMT_MODALITY varchar(50) default NULL,
  SEQ_NO int(11) default NULL,
  PRIMARY KEY  (tID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_transplant
-- ----------------------------
DROP TABLE IF EXISTS tbl_transplant;
CREATE TABLE tbl_transplant (
  trID int(11) NOT NULL auto_increment,
  RADAR_NO int(11) default NULL,
  DATE_TRANSPLANT datetime default NULL,
  TRANS_TYPE varchar(50) default NULL,
  TRANSPLANT_COUNTER int(11) default NULL,
  DATE_NEPHRECT datetime default NULL,
  TRANS_RECURR bit(1) default NULL,
  DATE_RECURR_TXK datetime default NULL,
  DATE_TX_REJECT datetime default NULL,
  DATE_BX_TXK datetime default NULL,
  PRIMARY KEY  (trID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_transplant_modality
-- ----------------------------
DROP TABLE IF EXISTS tbl_transplant_modality;
CREATE TABLE tbl_transplant_modality (
  trID int(11) NOT NULL auto_increment,
  trDesc varchar(75) default NULL,
  PRIMARY KEY  (trID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_transplant_reject
-- ----------------------------
DROP TABLE IF EXISTS tbl_transplant_reject;
CREATE TABLE tbl_transplant_reject (
  recID int(11) NOT NULL auto_increment,
  trID int(11) NOT NULL,
  trRejectDate datetime default NULL,
  trBiopsyDate datetime default NULL,
  trFailureDate datetime default NULL,
  PRIMARY KEY  (recID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table tbl_users
-- ----------------------------
DROP TABLE IF EXISTS tbl_users;
CREATE TABLE tbl_users (
  uID int(11) NOT NULL auto_increment,
  uSurname varchar(50) default NULL,
  uForename varchar(50) default NULL,
  uTitle varchar(50) default NULL,
  uGMC varchar(50) default NULL,
  uRole varchar(50) default NULL,
  uEmail varchar(50) default NULL,
  uPhone varchar(50) default NULL,
  uCentre int(11) default NULL,
  uDateJoin timestamp NOT NULL default CURRENT_TIMESTAMP,
  uPass varbinary(50) default NULL,
  uUserName varbinary(50) default NULL,
  PRIMARY KEY  (uID)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table testresult
-- ----------------------------
DROP TABLE IF EXISTS testresult;
CREATE TABLE testresult (
  id int(11) unsigned NOT NULL auto_increment,
  nhsno varchar(100) NOT NULL default '',
  unitcode varchar(20) NOT NULL default '',
  testcode varchar(100) NOT NULL default '',
  datestamp datetime NOT NULL default '0000-00-00 00:00:00',
  prepost varchar(100) default '',
  value varchar(100) NOT NULL default '',
  RADAR_NO int(11) unsigned default NULL,
  PRIMARY KEY  (id),
  KEY nhsno_testcode (nhsno,testcode,unitcode),
  KEY radarno_unitcode (RADAR_NO,unitcode)
  
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;



-- ----------------------------
-- Table structure for table treatment
-- ----------------------------
DROP TABLE IF EXISTS treatment;
CREATE TABLE treatment (
  nhsNo varchar(100) NOT NULL default '',
  treatmentCode varchar(100) NOT NULL default '',
  PRIMARY KEY  (nhsNo,treatmentCode)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table uktcode
-- ----------------------------
DROP TABLE IF EXISTS uktcode;
CREATE TABLE uktcode (
  id int(11) NOT NULL default '0',
  uktcode varchar(10) NOT NULL default '',
  description varchar(100) NOT NULL default '',
  PRIMARY KEY  (id),
  KEY uktcode_unique (uktcode)
  
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;




-- ----------------------------
-- Table structure for table uktstatus
-- ----------------------------
DROP TABLE IF EXISTS uktstatus;
CREATE TABLE uktstatus (
  nhsno varchar(20) NOT NULL default '',
  kidney varchar(10) default '',
  pancreas varchar(10) default '',
  PRIMARY KEY  (`nhsno`)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table unit
-- ----------------------------
DROP TABLE IF EXISTS unit;
CREATE TABLE unit (
  unitcode varchar(100) NOT NULL default '',
  name varchar(100) NOT NULL default '',
  shortname varchar(15) NOT NULL default '',
  unituser varchar(20) default NULL,
  address1 varchar(100) default NULL,
  address2 varchar(100) default NULL,
  address3 varchar(100) default NULL,
  postcode varchar(100) default NULL,
  uniturl varchar(100) default NULL,
  trusturl varchar(100) default NULL,
  rpvadminname varchar(100) default NULL,
  rpvadminphone varchar(100) default NULL,
  rpvadminemail varchar(100) default NULL,
  unitenquiriesphone varchar(100) default NULL,
  unitenquiriesemail varchar(100) default NULL,
  appointmentphone varchar(100) default NULL,
  appointmentemail varchar(100) default NULL,
  outofhours varchar(100) default NULL,
  peritonealdialysisphone varchar(100) default NULL,
  peritonealdialysisemail varchar(100) default NULL,
  haemodialysisunitname1 varchar(100) default NULL,
  haemodialysisunitphone1 varchar(100) default NULL,
  haemodialysisunitlocation1 varchar(100) default NULL,
  haemodialysisuniturl1 varchar(100) default NULL,
  haemodialysisunitname2 varchar(100) default NULL,
  haemodialysisunitphone2 varchar(100) default NULL,
  haemodialysisunitlocation2 varchar(100) default NULL,
  haemodialysisuniturl2 varchar(100) default NULL,
  haemodialysisunitname3 varchar(100) default NULL,
  haemodialysisunitphone3 varchar(100) default NULL,
  haemodialysisunitlocation3 varchar(100) default NULL,
  haemodialysisuniturl3 varchar(100) default NULL,
  haemodialysisunitname4 varchar(100) default NULL,
  haemodialysisunitphone4 varchar(100) default NULL,
  haemodialysisunitlocation4 varchar(100) default NULL,
  haemodialysisuniturl4 varchar(100) default NULL,
  haemodialysisunitname5 varchar(100) default NULL,
  haemodialysisunitphone5 varchar(100) default NULL,
  haemodialysisunitlocation5 varchar(100) default NULL,
  haemodialysisuniturl5 varchar(100) default NULL,
  haemodialysisunitname6 varchar(100) default NULL,
  haemodialysisunitphone6 varchar(100) default NULL,
  haemodialysisunitlocation6 varchar(100) default NULL,
  haemodialysisuniturl6 varchar(100) default NULL,
  haemodialysisunitname7 varchar(100) default NULL,
  haemodialysisunitphone7 varchar(100) default NULL,
  haemodialysisunitlocation7 varchar(100) default NULL,
  haemodialysisuniturl7 varchar(100) default NULL,
  haemodialysisunitname8 varchar(100) default NULL,
  haemodialysisunitphone8 varchar(100) default NULL,
  haemodialysisunitlocation8 varchar(100) default NULL,
  haemodialysisuniturl8 varchar(100) default NULL,
  haemodialysisunitname9 varchar(100) default NULL,
  haemodialysisunitphone9 varchar(100) default NULL,
  haemodialysisunitlocation9 varchar(100) default NULL,
  haemodialysisuniturl9 varchar(100) default NULL,
  haemodialysisunitname10 varchar(100) default NULL,
  haemodialysisunitphone10 varchar(100) default NULL,
  haemodialysisunitlocation10 varchar(100) default NULL,
  haemodialysisuniturl10 varchar(100) default NULL,
  haemodialysisunitname11 varchar(100) default NULL,
  haemodialysisunitphone11 varchar(100) default NULL,
  haemodialysisunitlocation11 varchar(100) default NULL,
  haemodialysisuniturl11 varchar(100) default NULL,
  haemodialysisunitname12 varchar(100) default NULL,
  haemodialysisunitphone12 varchar(100) default NULL,
  haemodialysisunitlocation12 varchar(100) default NULL,
  haemodialysisuniturl12 varchar(100) default NULL,
  sourceType varchar(50) default NULL,
  PRIMARY KEY  (unitcode)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table unitstat
-- ----------------------------
DROP TABLE IF EXISTS unitstat;
CREATE TABLE unitstat (
  id int(11) NOT NULL auto_increment,
  unitcode varchar(20) NOT NULL,
  yearmonth varchar(7) NOT NULL,
  action varchar(30) NOT NULL,
  count int(10) NOT NULL default '0',
  PRIMARY KEY  (id)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table user
-- ----------------------------
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  username varchar(100) NOT NULL default '',
  password varchar(100) NOT NULL default '',
  role varchar(100) NOT NULL default '',
  name varchar(100) default NULL,
  email varchar(100) default NULL,
  emailverified tinyint(1) default '0',
  firstlogon tinyint(1) default '0',
  dummypatient tinyint(1) NOT NULL default '0',
  lastlogon datetime default NULL,
  failedlogons int(3) default '0',
  accountlocked tinyint(1) default '0',
  screenname varchar(100) NOT NULL,
  PRIMARY KEY  (username)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table userlog
-- ----------------------------
DROP TABLE IF EXISTS userlog;
CREATE TABLE userlog (
  id int(11) NOT NULL auto_increment,
  datestamp datetime NOT NULL,
  unitcode varchar(100) default NULL,
  role varchar(100) NOT NULL default '',
  count int(10) NOT NULL default '0',
  PRIMARY KEY  (id)
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;


-- ----------------------------
-- Table structure for table usermapping
-- ----------------------------
DROP TABLE IF EXISTS usermapping;
CREATE TABLE usermapping (
  id int(11) NOT NULL auto_increment,
  username varchar(100) NOT NULL,
  unitcode varchar(10) NOT NULL,
  nhsno varchar(10) default NULL,
  PRIMARY KEY  (id),
  KEY usermapping_index_nhsno (nhsno),
  KEY usermapping_index_username (username)
  
) /*! ENGINE=MyISAM DEFAULT CHARSET=latin1 */;
