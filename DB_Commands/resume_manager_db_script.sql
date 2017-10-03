DROP DATABASE IF EXISTS resume_portal_db;

CREATE DATABASE resume_portal_db;

USE resume_portal_db;

DROP TABLE IF EXISTS skill;

CREATE TABLE skill (
  skill_id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  PRIMARY KEY (skill_id)
) ENGINE=INNODB;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  user_key BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id VARCHAR(50) NOT NULL,
  password VARCHAR(10) NOT NULL,
  first_name VARCHAR(200) NOT NULL,
  middle_name VARCHAR(200),
  last_name VARCHAR(200) NOT NULL,
  PRIMARY KEY (user_key)
)ENGINE=INNODB;

DROP TABLE IF EXISTS resume;

CREATE TABLE resume (
  resume_id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  file_path VARCHAR(200) NOT NULL,
  upload_timestamp TIMESTAMP NOT NULL,
  user_key BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (resume_id),
  FOREIGN KEY (user_key) REFERENCES user(user_key)
  ON UPDATE CASCADE ON DELETE RESTRICT
)ENGINE=INNODB;

DROP TABLE IF EXISTS resume_skill;

CREATE TABLE resume_skill (
  skill_id BIGINT(20) UNSIGNED NOT NULL,
  resume_id BIGINT(20) UNSIGNED NOT NULL,
  FOREIGN KEY (skill_id) REFERENCES skill(skill_id)
  ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (resume_id) REFERENCES resume(resume_id)
  ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=INNODB;


INSERT INTO skill(name) VALUES ('Tableau');
INSERT INTO skill(name) VALUES ('PHP');
INSERT INTO skill(name) VALUES ('Kafka');
INSERT INTO skill(name) VALUES ('Selenium');
INSERT INTO skill(name) VALUES ('SOA');
INSERT INTO skill(name) VALUES ('Jmeter');
INSERT INTO skill(name) VALUES ('Devtest');
INSERT INTO skill(name) VALUES ('ETL');
INSERT INTO skill(name) VALUES ('Docker');
INSERT INTO skill(name) VALUES ('Gitlab');
INSERT INTO skill(name) VALUES ('.NET');
INSERT INTO skill(name) VALUES ('BA');
INSERT INTO skill(name) VALUES ('Data Warehousing');
INSERT INTO skill(name) VALUES ('QA Automation');
INSERT INTO skill(name) VALUES ('GCF');
INSERT INTO skill(name) VALUES ('Cloud Foundry');
INSERT INTO skill(name) VALUES ('PCF');
INSERT INTO skill(name) VALUES ('Java Microservices');
INSERT INTO skill(name) VALUES ('Machine learning');
INSERT INTO skill(name) VALUES ('R');
INSERT INTO skill(name) VALUES ('SharePoint');
INSERT INTO skill(name) VALUES ('J2EE');

#INSERT INTO user(user_id,first_name,middle_name,last_name) VALUES ('dummyid','dummyfirst','dummymiddle','dummylast');



CREATE USER 'bwuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON resume_portal_db.* TO 'bitwise'@'localhost';
