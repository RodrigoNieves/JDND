CREATE TABLE member (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(1000),
    last_name VARCHAR(1000),
    age TINIYINT UNSIGNED,
    genger TINYTEXT,
    balance FLOAT(7, 2) DEFAULT 0,
    CONSTRAINT member_pk PRIMARY_KEY(id)
);
