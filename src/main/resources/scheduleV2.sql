CREATE DATABASE IF NOT EXISTS scheduleV2;
USE scheduleV2;

CREATE TABLE IF NOT EXISTS user
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '고유 식별자',
    email   VARCHAR(25) NOT NULL UNIQUE COMMENT '이메일',
    password     VARCHAR(25) NOT NULL COMMENT '비밀번호',
    name        VARCHAR(25) NOT NULL COMMENT '작성자명'
    );

CREATE TABLE IF NOT EXISTS event
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '고유 식별자',
    user_id BIGINT NOT NULL COMMENT '유저 고유 식별자',
    FOREIGN KEY(user_id) REFERENCES `user`('id'),
    task         VARCHAR(50) COMMENT '할일',
    created_time TIMESTAMP   NOT NULL COMMENT '생성 날짜',
    edited_time  TIMESTAMP   NOT NULL COMMENT '수정된 날짜'
    );