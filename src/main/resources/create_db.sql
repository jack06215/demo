-- create database --
CREATE DATABASE exposed_example;
-- switch database --
USE exposed_example;

-- create table schema --
CREATE TABLE member (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;