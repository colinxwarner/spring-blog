CREATE DATABASE IF NOT EXISTS adlister_db;

CREATE USER adlister_user@localhost IDENTIFIED BY '!Password123';
GRANT ALL ON adlister_db.* TO adlister_user@localhost;