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

INSERT INTO posts(title, body) VALUES
('Test', 'Test post'),
('My Post', 'My post'),
('Beagles', 'Summer, Ducle and Obi')
