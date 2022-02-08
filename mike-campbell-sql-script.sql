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
	course_seats_max int,
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


insert into users(user_fname, user_lname, user_email, user_username, user_password, user_permission) 
values ('mike', 'camp', 'memail@email.com', 'memike', 'amazingpass', 0);