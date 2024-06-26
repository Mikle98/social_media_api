create table posts(
    id serial primary key,
    title varchar,
    description varchar,
    created timestamp not null,
    userId int not null references users(id)
)