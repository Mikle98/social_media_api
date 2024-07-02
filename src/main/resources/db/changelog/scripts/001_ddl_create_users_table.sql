create table users(
    id serial primary key,
    login varchar unique not null,
    email varchar unique not null,
    password varchar not null
);