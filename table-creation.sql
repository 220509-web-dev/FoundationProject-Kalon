/*
multipy line
*/

--establish a 'namespace'  for  related  database entities to exist  within
create schema foundation_project;

--convenience command that will help us to reference the schema created above
set search_path to foundation_project;

create table user_table (
    id  int generated always as identity primary key,
    name varchar unique not null
);