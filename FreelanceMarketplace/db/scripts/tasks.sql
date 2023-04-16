CREATE TABLE tasks (
	id long PRIMARY KEY AUTO_INCREMENT,
	employer_id long,
	employee_id long NOT NULL,
	price int NOT NULL,
	isFree boolean DEFAULT true,
	isCompleted boolean DEFAULT false,
	description varchar(10000),

	FOREIGN KEY (employer_id) REFERENCES employers (id),
	FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE cascade,
);
