CREATE DATABASE example;
USE example;
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id INT(10) NOT NULL,
  name VARCHAR(16) NOT NULL,
  age INT(10) NOT NULL,
  profile VARCHAR(64) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO user VALUES (100, "Ichiro", 30, "Hello"), (101, "Jiro", 25, "Konichiwa"), (102, "Saburo", 20, "G'Day Mate");