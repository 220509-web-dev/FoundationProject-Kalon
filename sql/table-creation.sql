/*
multipy line
*/

--establish a 'namespace'  for  related  database entities to exist  within
create schema shoe_collection_app;

--convenience command that will help us to reference the schema created above
set search_path to shoe_collection_app;

create table user_status (
    id  int generated always as identity primary key,
    status varchar unique not null
);

create table app_users (
    id int generated always as identity,
    first_name varchar not null,
    last_name varchar not null,
    email varchar unique not null,
    username varchar unique not null check ( length(username) >= 4 ),
    status_id int,

    constraint app_users_pk
    primary key (id)
);

alter table app_users
add column pword varchar not null check ( length(pword) >= 8);

alter table app_users
add foreign key (status_id)
references user_status(id);

create table categories (
    id int generated always as identity primary key,
    name varchar not null unique
);

create table shoes (
    id int generated always as identity primary key,
    shoe_brand varchar not null,
    model_number varchar not null,
    shoe_size int not null,
    owner_id int,
    category_id int default 1 check (category_id > 0 and category_id <= 4),

    constraint shoe_owner_fk
    foreign key (owner_id)
    references app_users(id),

    constraint shoe_category_fk
    foreign key (category_id)
    references categories(id)
);

create table shoe_list (
id int generated always as identity primary key,
name varchar not null,
owner_id int,

constraint list_owner_fk
foreign key (owner_id)
references app_users(id)
);

create table shoe_list_shoes (
    list_id int,
    shoe_id int,

    constraint shoe_list_shoes_pk
    primary key (list_id, shoe_id),

    constraint shoe_list_fk
    foreign key (list_id)
    references shoe_list(id),

    constraint shoe_fk
    foreign key (shoe_id)
    references shoes(id)
);