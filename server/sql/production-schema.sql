drop database if exists packOverflow;
create database packOverflow;
use packOverflow;

create table `user` (
	user_id int primary key auto_increment,
    username varchar(255) unique,
    `password` text,
    `email` varchar(255) unique,
    first_name varchar(105),
    last_name varchar(105)
);

create table `questions` (
question_id int primary key auto_increment,
	`user_id` int NOT NULL,
	`title` varchar(255) NOT NULL,
	`body` text NOT NULL,
	`created` DATE NOT NULL,
	`updated` DATE NOT NULL,
  foreign key (user_id) references `user`(user_id),

);


create table `answers`(
answer_id int primary key auto_increment,
`user_id` int NOT NULL,
question_id int NOT NULL,
    `body` text NOT NULL,
	`created_at` DATE NOT NULL,
	`updated` DATE NOT NULL,
  foreign key (user_id) references `user`(user_id),
  foreign key (question_id) references `questions`(question_id),

);

insert into `user` (username, `password`, `email`, first_name, last_name)
	values
    ('test username 1', 'password1','email', 'Brandi', 'Murray'),
	('test username 2', 'password2', 'email2', 'Kyle', 'Murray');