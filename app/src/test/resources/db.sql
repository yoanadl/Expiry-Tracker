CREATE DATABASE ExpiryTracker;

USE ExpiryTracker;

CREATE TABLE `user` (
	user_id int NOT NULL AUTO_INCREMENT,
	username varchar(128) NOT NULL,
	fullname varchar(512) NOT NULL,
	email varchar (1024) NOT NULL,
	phone int NOT NULL,
	pwd varchar(1024) NOT NULL,
	PRIMARY KEY (user_id)
);

CREATE TABLE `list` (
	list_id int NOT NULL AUTO_INCREMENT,
	list_name varchar(128) NOT NULL,
	list_desc varchar(1024) NOT NULL,
	PRIMARY KEY (list_id)
);

CREATE TABLE `item` (
    item_name varchar(128) NOT NULL,
    item_desc varchar(1024) NOT NULL,
    default_exp_in_days int NOT NULL,
    list_id int NOT NULL,
    user_id int NOT NULL,
    FOREIGN KEY (list_id) REFERENCES `list`(list_id),
    FOREIGN KEY (user_id) REFERENCES `user`(user_id),
    PRIMARY KEY (list_id, user_id)
);

INSERT INTO `user` (username, fullname, pwd, email, phone)
VALUES
('user1', 'Yoan ADL', 'password1', 'user1@example.com', '1234567890'),
('user2', 'Hetty Verina', 'password2', 'user2@example.com', '2345678901'),
('user3', 'Alethea Azka', 'password3', 'user3@example.com', '3456789012');

INSERT INTO `list` (list_name, list_desc)
VALUES
('Grocery', 'Grocery shopping list'),
('Skincare', 'Skincare expiry date'),
('Fruit', 'My fruits');

INSERT INTO `item` (item_name, item_desc, default_exp_in_days, list_id, user_id)
VALUES
('Cracker', 'Snacks', 365, 1, 1),
('Moisturizer', 'Any', 720, 2, 2),
('Apple', 'For salad', 7, 3, 3);