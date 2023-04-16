CREATE TABLE tasks (
	id long PRIMARY KEY AUTO_INCREMENT,
	employer_id long,
	employee_id long NOT null,
	price int NOT NULL,
	isFree boolean DEFAULT true,
	isCompleted boolean DEFAULT false,
	description varchar(10000),
);
