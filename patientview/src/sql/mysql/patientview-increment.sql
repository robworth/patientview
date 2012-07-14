DELETE FROM splashpage;

ALTER TABLE splashpage ADD unitcode VARCHAR(20) NOT NULL AFTER bodytext;

CREATE TABLE splashpageuserseen (
  id int(10) NOT NULL,
  username varchar(100) NOT NULL,
  splashpageid int(10) NOT NULL,
  PRIMARY KEY (id),
  KEY usersplashpage (username, splashpageid) USING BTREE
);

ALTER TABLE user DROP COLUMN splashpage;