DROP SCHEMA IF EXISTS User_DB;

CREATE SCHEMA User_DB;
USE User_DB;

CREATE TABLE User (
                      id INT AUTO_INCREMENT,
                      name VARCHAR(50) NOT NULL,
                      country VARCHAR(50) NOT NULL,
                      age INT NOT NULL,
                      gender VARCHAR(50) NOT NULL,
                      user_type VARCHAR(50) NOT NULL,
                      CONSTRAINT userId_pk PRIMARY KEY (id)
);
INSERT INTO User(id, name, country, age, gender, user_type) VALUES (100,'Surendra Onteru', 'France','26', 'Male', 'Advanced');

SELECT * FROM User;