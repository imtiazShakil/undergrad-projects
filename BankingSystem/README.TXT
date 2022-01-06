Before running the project following things must be installed;

1. oracle 10g express edition
2. following tables;


create table users (
name varchar(15) primary key ,
password varchar(15) not null,
typ number not null );


insert into table values('admin','admin',1);
