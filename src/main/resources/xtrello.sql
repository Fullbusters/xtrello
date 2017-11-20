create table boards
(
	id int auto_increment
		primary key,
	name varchar(70) not null,
	User_id int not null,
	constraint Boards_users_id_fk
		foreign key (User_id) references users (id)
)
;

create index Boards_users_id_fk
	on boards (User_id)
;


