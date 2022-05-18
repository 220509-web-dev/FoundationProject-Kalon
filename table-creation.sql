/*
multipy line
*/

--establish a 'namespace'  for  related  database entities to exist  within
create schema quizzard_app;

--convenience command that will help us to reference the schema created above
set search_path to quizzard_app;

create table user_roles (
    id  int generated always as identity primary key,
    name varchar unique not null
);

create table app_users (
    id  int generated  always as identity,
    first_name varchar not null,
    last_name varchar not null,
    email   varchar unique not null,
    username varchar unique not null check (length(username) >= 4),
    password varchar not null  check (length(password) >= 8),
    role_id int,

    constraint app_users_pk
    primary key (id)
);

create table   categories (
    id int generated always  as  identity  primary key,
    name    varchar unique not null
);

create table flashcards  (
    id  generated always as  identity primary key,
    question_text   varchar not null,
    answer_text     varchar not null,
    difficulty 1    int check (difficulty > 0 and difficulty <=  10)
    creator_id      int,
    category_id     int
    
    constraint  flashcard_creator_fk
    foreign  key (creator_id)
    references app_users(id),
      
    constraint   flashcard_category_fk
    foreign  key (category_id)
    references categories(id), 
);

create table study_decks (
    id  int generated always as identity primary key,
    name    varchar not null,
    owner_id    int,
    category_id int,

    constraint study_deck_owner_fk
    foreign key (owner_id)
    references app_users(id),

    constraint  study_deck_category_fk
    foreign key (category_id)
    references  categories(id)
);

create table study_deck_cards (
    deck_id int,
    card_id int,

    constraint study_deck_cards_pk
    primary key  (deck_id)
    references study_decks(id),

    constraint study_deck_category_fk

);

