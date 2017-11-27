create table boards
(
	id int auto_increment
		primary key,
	name varchar(150) not null,
	ListBoard_id int null,
	creator_id int not null
)
;

create index boards_listboards_idListBoards_fk
	on boards (ListBoard_id)
;

create table cards
(
	idCard int auto_increment
		primary key,
	nameCard varchar(150) not null,
	Comentar varchar(500) not null,
	ListCard_id int not null
)
;

create index card_listcard_idListCard_fk
	on cards (ListCard_id)
;

create table listboards
(
	idListBoards int auto_increment
		primary key,
	name varchar(75) not null,
	User_id int not null,
	text varchar(1000) null
)
;

create index listboards_users_id_fk
	on listboards (User_id)
;

alter table boards
	add constraint boards_listboards_idListBoards_fk
foreign key (ListBoard_id) references listboards (idListBoards)
	on update cascade on delete cascade
;

create table listcards
(
	idListCard int auto_increment
		primary key,
	name varchar(150) not null,
	Board_id int not null
)
;

create index listcard_boards_id_fk
	on listcards (Board_id)
;

alter table cards
	add constraint card_listcard_idListCard_fk
foreign key (ListCard_id) references listcards (idListCard)
	on update cascade on delete cascade
;

create table sharedboard
(
	id int auto_increment
		primary key,
	User_id int not null,
	Board_id int not null,
	constraint sharedboard_boards_id_fk
	foreign key (Board_id) references boards (id)
		on update cascade on delete cascade
)
;

create index sharedBoard_users_id_fk
	on sharedboard (User_id)
;

create index sharedboard_boards_id_fk
	on sharedboard (Board_id)
;

create table sharedlistboards
(
	id int auto_increment
		primary key,
	User_id int not null,
	listBoard_id int not null,
	constraint SharedListBoards_listboards_idListBoards_fk
	foreign key (listBoard_id) references listboards (idListBoards)
		on update cascade on delete cascade
)
;

create index SharedListBoards_listboards_idListBoards_fk
	on sharedlistboards (listBoard_id)
;

create index SharedListBoards_users_id_fk
	on sharedlistboards (User_id)
;

create table users
(
	id int auto_increment
		primary key,
	email varchar(150) not null,
	password varchar(150) not null,
	name varchar(50) not null,
	date date not null,
	role int(1) not null comment '1 is Admin, 2 is User , 0 is Banned',
	constraint users_email_uindex
	unique (email)
)
	comment 'all users'
;

alter table sharedboard
	add constraint sharedBoard_users_id_fk
foreign key (User_id) references users (id)
	on update cascade on delete cascade
;

alter table sharedlistboards
	add constraint SharedListBoards_users_id_fk
foreign key (User_id) references users (id)
	on update cascade on delete cascade
;

