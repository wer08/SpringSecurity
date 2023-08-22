--liquibase formatted sql
--changeset wzolkowski:1
CREATE TABLE _USER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    password VARCHAR(200) NOT NULL,
    email VARCHAR(40) NOT NULL,
    role VARCHAR(40) NOT NULL
);