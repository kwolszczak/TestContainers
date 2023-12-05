drop schema public;
create schema public;
drop table if exists actor;
create table public.actor (
id numeric primary key null,
firstname varchar not null,
lastname varchar not null,
rating numeric not null
);
insert into actor (id,firstname,lastname,rating) values (1, 'Brad', 'Pit', 7);
insert into actor (id,firstname,lastname,rating) values (2, 'Angelina', 'Jolie', 8);
insert into actor (id,firstname,lastname,rating) values (3, 'Salma', 'Hayek', 5);