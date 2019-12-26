CREATE TABLE `member` (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(1000),
    last_name VARCHAR(1000),
    age INTEGER UNSIGNED,
    gender TINYTEXT,
    balance FLOAT(7, 2) DEFAULT 0.00,
    CONSTRAINT member_pk PRIMARY KEY(id)
);
