--liquibase formatted sql
--changeset wzolkowski:2
CREATE TABLE POST (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(400) NOT NULL,
    _user_id BIGINT NOT NULL,
    content VARCHAR(2000) NULL,
    created TIMESTAMP
);

ALTER TABLE POST
    ADD CONSTRAINT post_user_id
    FOREIGN KEY (_user_id) REFERENCES _USER(id);
