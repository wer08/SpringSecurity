--liquibase formatted sql
--changeset wzolkowski:3
CREATE TABLE COMMENT(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    _user_id BIGINT NOT NULL,
    content VARCHAR(2000) NULL,
    created TIMESTAMP
);

ALTER TABLE COMMENT
    ADD CONSTRAINT comment_post_id
    FOREIGN KEY (post_id) REFERENCES POST(id);

ALTER TABLE COMMENT
    ADD CONSTRAINT comment_user_id
    FOREIGN KEY (_user_id) REFERENCES _USER(id);