CREATE DATABASE book_manager;
USE book_manager;
CREATE TABLE user (
  id BIGINT NOT NULL,
  email VARCHAR(256) UNIQUE NOT NULL,
  password VARCHAR(128) NOT NULL,
  name VARCHAR(32) NOT NULL,
  role_type ENUM('ADMIN', 'USER'),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE book (
  id BIGINT NOT NULL,
  title VARCHAR(128) NOT NULL,
  author VARCHAR(32) NOT NULL,
  release_date DATE NOT NULL,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE rental (
  book_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  rental_datetime DATETIME NOT NULL,
  return_deadline DATETIME NOT NULL,
  PRIMARY KEY (book_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO book VALUES(100, 'Intro to Kotlin', 'Jack', '1950-10-01'), (200, 'Java Fundamental', 'Taro', '2005-08-29');
INSERT INTO user VALUES(1, 'admin@test.com', '$2a$10$.kLvZAQfzNvFFlXzaQmwdeUoq2ypwaN.A/GNy32', 'Admin', 'ADMIN'), (2, 'user@test.com', '$2a$10$dtB.bySf.ZcbOPOp3Q7ZgedqofClN56rQ6JboxBuiW02twNMcAoZS', 'User', 'USER');
