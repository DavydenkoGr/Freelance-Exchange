INSERT INTO roles (name) VALUES ('employee');
INSERT INTO roles (name) VALUES ('employer');

-- create people
INSERT INTO users (login, password, role_id, name, surname, age, resume, organization, information)
VALUES ('Lin', '123', 1, 'Linus', 'Torvalds', 30, 'Creator of Linux kernel', '', '');

INSERT INTO users (login, password, role_id, name, surname, age, resume, organization, information)
VALUES ('Ada', '123', 1, 'Ada', 'Lovelace', 25, 'First computer programmer', '', '');

INSERT INTO users (login, password, role_id, name, surname, age, resume, organization, information)
VALUES ('Bill', '123', 2, 'Bill', 'Gates', 50, '', 'Microsoft', 'Co-founder of Microsoft');

INSERT INTO users (login, password, role_id, name, surname, age, resume, organization, information)
VALUES ('Mark', '123', 1, 'Mark', 'Zuckerberg', 30, 'Co-founder of Facebook', '', '');

INSERT INTO users (login, password, role_id, name, surname, age, resume, organization, information)
VALUES ('Steve', '123', 1, 'Steve', 'Jobs', 50, 'Co-founder of Apple', '', '');

INSERT INTO users (login, password, role_id, name, surname, age, resume, organization, information)
VALUES ('Jeff', '123', 2, 'Jeff', 'Bezos', 40, '', 'Amazon', 'Founder and CEO of Amazon');

INSERT INTO users (login, password, role_id, name, surname, age, resume, organization, information)
VALUES ('Elon', '123', 1, 'Elon', 'Musk', 40, 'Founder of SpaceX and Tesla', '', '');

INSERT INTO users (login, password, role_id, name, surname, age, resume, organization, information)
VALUES ('Larry', '123', 2, 'Larry', 'Page', 45, '', 'Google', 'Co-founder of Google');

INSERT INTO users (login, password, role_id, name, surname, age, resume, organization, information)
VALUES ('Sundar', '123', 1, 'Sundar', 'Pichai', 45, 'CEO of Google', '', '');

INSERT INTO users (login, password, role_id, name, surname, age, resume, organization, information)
VALUES ('Jack', '123', 2, 'Jack', 'Ma', 55, '', 'Alibaba', 'Founder of Alibaba Group');

INSERT INTO users (login, password, role_id, name, surname, age, resume, organization, information)
VALUES ('Satya', '123', 1, 'Satya', 'Nadella', 55, 'CEO of Microsoft', '', '');

INSERT INTO users (login, password, role_id, name, surname, age, resume, organization, information)
VALUES ('Marissa', '123', 1, 'Marissa', 'Mayer', 45, 'Former CEO of Yahoo!', '', '');

-- create tasks
INSERT INTO tasks (description, price, isFree, isCompleted, employer_id, employee_id)
VALUES ('Web Designer: Create visually appealing and user-friendly websites, redesign existing ones,
optimize for mobile devices, and ensure high website speed.', 140, true, false, 3, '');

INSERT INTO tasks (description, price, isFree, isCompleted, employer_id, employee_id)
VALUES ('Content Writer: Write SEO-friendly articles, blog posts, product descriptions,
social media posts, and website copy for various niches.', 50, false, true, 6, 5);

INSERT INTO tasks (description, price, isFree, isCompleted, employer_id, employee_id)
VALUES ('Social Media Manager: Develop social media strategies, create and schedule
engaging posts, respond to comments, and track analytics.', 169, true, false, 8, '');
