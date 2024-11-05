DROP TABLE IF EXISTS unit;
DROP TABLE IF EXISTS users;

create table unit(id serial primary key,
				 name varchar(255) not null,
				 mhp int not null,
				 hp int not null,
				 mmp int not null,
				 mp int not null,
				 str int not null,
				 inte int not null,
				 skl int not null,
				 agi int not null,
				 def int not null,
				 mdf int not null,
				 luck int not null );

create table users(
				 id serial primary key,
				 username varchar(255) not null,
				 password varchar(255) not null,
				 authority varchar(25) default 'USER' );