DROP DATABASE blog_db;
CREATE DATABASE IF NOT EXISTS blog_db;

CREATE USER blog_user@localhost IDENTIFIED BY '!Password123';
GRANT ALL ON blog_db.* TO blog_user@localhost;

USE blog_db;

INSERT INTO posts(title, body) VALUES
('Test', 'Test post'),
('My Post', 'My post'),
('Beagles', 'Summer, Ducle and Obi')
