# TripleJ
Example Java project that includes the basics for REST endpoints.

See Git commits for step-by-step notes.

## Database Example
create table accounts (
  id int not null auto_increment primary key,
  name varchar(256)
);

insert into accounts (name) values ('pepsi');
insert into accounts (name) values ('coke');