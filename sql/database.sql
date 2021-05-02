DROP DATABASE adlister_db;
CREATE DATABASE IF NOT EXISTS adlister_db;

CREATE USER adlister_user@localhost IDENTIFIED BY '!Password123';
GRANT ALL ON adlister_db.* TO adlister_user@localhost;

USE adlister_db;
create table ads
(
    id bigint NOT NULL AUTO_INCREMENT,
    description varchar(255) null,
    title varchar(100) null,
    PRIMARY KEY (id)
);

INSERT INTO ads (title, description) Values ('Test title','Test Description');
INSERT INTO ads (title, description) Values ('Test1 title','Test1 Description');

CREATE table posts(
                      id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(100),
                      body VARCHAR(255)
);

DROP TABLE posts;

INSERT INTO posts(title, body, user_id) VALUES
('Test', 'Test post', 1),
('My Post', 'My post', 1),
('Beagles', 'Summer, Ducle and Obi', 1);

INSERT INTO user(email, password, username) VALUES
    ('kathleen@codeup','password','kathleen');
