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


INSERT INTO resume_portal_db.skill(name) VALUES
('Java'),
('J2EE'),
('Hibernate'),
('Spring'),
('Restful'),
('SOAP'),
('JavaScript'),
('Nodejs'),
('Angularjs'),
('Bootstrap'),
('APIGEE'),
('HTML5'),
('SQL'),
('PL/SQL'),
('MYSQL'),
('AWS'),
('Hadoop'),
('Cassandra'),
('Pig'),
('Hive'),
('Big Data'),
('Scala'),
('Spark'),
('Python'),
('Shellscripting'),
('Android'),
('Object-C'),
('Ab Initio'),
('ER Studio'),
('Tableau'),
('PHP'),
('Kafka'),
('Selenium'),
('SOA'),
('Jmeter'),
('Devtest'),
('ETL'),
('Docker'),
('Gitlab'),
('.NET'),
('BA'),
('Data Warehousing'),
('QA Automation'),
('GCF'),
('Cloud Foundry'),
('PCF'),
('Java Microservices'),
('Machine learning'),
('R'),
('SharePoint'),
('Oracle'),
('Teradata'),
('Linux'),
('Perl'),
('Business Analysis'),
('Data Analysis'),
('Snaplogic'),
('Manual '),
('Testing'),
('Automation'),
('Project Management'),
('Networking'),
('Cisco'),
('System Administration'),
('Hyper V') ;


#INSERT INTO user(user_id,first_name,middle_name,last_name) VALUES ('dummyid','dummyfirst','dummymiddle','dummylast');



CREATE USER 'bwuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON resume_portal_db.* TO 'bitwise'@'localhost';
