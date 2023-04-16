CREATE TABLE tasks (
	id long PRIMARY KEY AUTO_INCREMENT,
	employer_id long REFERENCES employers(id),
	employee_id long FOREIGN KEY NOT NULL REFERENCES employees(id) ON DELETE cascade,
	price int NOT NULL,
	isFree boolean DEFAULT true,
	isCompleted boolean DEFAULT false,
	description varchar(10000),
);
