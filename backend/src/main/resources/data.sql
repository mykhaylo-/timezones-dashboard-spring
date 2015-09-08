--create table users (    username varchar(50) not null primary key,    password varchar(255) not null,
-- enabled boolean not null);
-- create table authorities (    username varchar(50) not null,
--   authority varchar(50) not null,
--   foreign key (username) references users (username),
--   unique index authorities_idx_1 (username, authority));

insert into user (id, username, name, password) values (1, 'admin', 'Superuser', 'admin')
insert into role (id, role) values (1, 'ADMIN')
insert into user_role(role_id, user_id) values (1, 1)