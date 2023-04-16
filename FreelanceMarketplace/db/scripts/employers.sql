CREATE TABLE employers (
	id long PRIMARY KEY AUTO_INCREMENT,
	login varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	surname varchar(255) NOT NULL,
	age int,
	resume varchar(10000),
);
