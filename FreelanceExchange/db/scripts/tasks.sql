create table tasks (
    id bigint generated by default as identity,
    description varchar(10000),
    isCompleted boolean default false not null,
    isFree boolean default true not null,
    price integer not null,
    employee_id bigint,
    employer_id bigint not null,
    primary key (id),
    
	foreign key (employer_id) references employers (id),
	foreign key(employee_id) references employees (id) on delete cascade
);