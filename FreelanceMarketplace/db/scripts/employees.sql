CREATE TABLE employees (
	id long PRIMARY KEY AUTO_INCREMENT,
	login varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	surname varchar(255) NOT NULL,
	organization varchar(255),
	information varchar(10000),
);
