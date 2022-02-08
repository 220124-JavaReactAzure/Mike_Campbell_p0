/*
drop table if exists enrollment;
drop table if exists course;
drop table if exists users;
*/


create table if not exists users(
	user_id SERIAL primary key,
	user_fname varchar(25),
	user_lname varchar(25),
	user_email varchar(25) UNIQUE,
	user_username varchar(25) UNIQUE,
	user_password varchar(25),
	user_permission int
);

create table if not exists course(
	course_id SERIAL primary key,
	course_name varchar(40),
	course_description varchar (80),
	instructor_id int,
	course_seats_max int,
	course_seats_taken int,
	course_is_full boolean,
	foreign key(instructor_id) references users (user_id)
);

create table if not exists enrollment(
	enrollment_id SERIAL primary key,
	student_id int,
	course_id int,
	foreign key (student_id) references users(user_id),
	foreign key (course_id) references course(course_id)
);

--insert test users
insert into users(user_fname, user_lname, user_email, user_username, user_password, user_permission) 
values 
('mike', 'camp', 'memail@email.com', 'memike', 'amazingpass', 0),
('chris', 'smith', 'csmith@email.com', 'csmith', 'bestpass', 0),
('brandon', 'genie', 'brenie@email.com', 'brenie', 'wowpassgood', 0),
('mykael', 'smith', 'myth@email.com', 'myth', 'secretcode', 0),
('jim', 'bob', 'jimbob@email.com', 'jimbob', 'jibompass', 1);

--insert test courses
insert into course(course_name, course_description, instructor_id, course_seats_max, course_seats_taken, course_is_full) 
values 
('fish101', 'fish basics', 1, 15, 5, false),
('fish102', 'fish basics part 2', 1, 15, 1, false),
('fish201', 'surprising facts about fish', 1, 15, 0, false),
('fish300', 'fish remember faces, please love fish', 1, 12, 2, false),
('why we should all care about fish', 'why is no one listening to me about fish', 1, 20, 3, false);

--insert test enrollments
insert into enrollment (student_id, course_id) 
values (1, 1),(2,1),(3,4),(4,5);

