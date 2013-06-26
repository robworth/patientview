--  Changes for 72 - PV/Radar - Unit Admin controls how staff are shown in lists

ALTER TABLE USER ADD COLUMN isrecipient TINYINT(1) NOT NULL DEFAULT 0;
ALTER TABLE USER ADD COLUMN isclinician TINYINT(1) NOT NULL DEFAULT 0;

