--liquibase formatted sql
--changeset wzolkowski:5
CREATE TABLE BLACKLIST_TOKEN (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(400) NOT NULL,
    created TIMESTAMP
);