drop database if exists packOverflow_test;
create database packOverflow_test;
use packOverflow_test;

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
  foreign key (`user_id`) references `user`(user_id)
);


-- create table `answers`(
-- answer_id int primary key auto_increment,
-- `user_id` int NOT NULL,
-- question_id int NOT NULL,
--     `body` text NOT NULL,
-- 	`created_at` DATE NOT NULL,
-- 	`updated` DATE NOT NULL,
--   foreign key (user_id) references `user`(user_id),
--   foreign key (question_id) references `questions`(question_id)
-- );

delimiter //
create procedure set_known_good_state()
begin
	delete from `questions`;
    alter table `questions` auto_increment = 1;
    delete from `user`;
    alter table `user` auto_increment = 1;	

	insert into `user` (username, `password`, `email`, first_name, last_name)
	values
    ('test username 1', 'password1','email', 'Brandi', 'Murray'),
	('test username 2', 'password2', 'email2', 'Kyle', 'Murray');
    
	insert into `questions` (`user_id`, `title`, `body`, `created`, `updated`)
	values
	(1, 'moving to nova scotia', 'do i need to pack my own fishing gear or can i buy it there?', '2025-03-24', '2025-03-25');

end//
delimiter ;


