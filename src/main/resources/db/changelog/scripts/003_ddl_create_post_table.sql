create table posts(
    id serial primary key,
    title varchar,
    description varchar,
    created timestamp not null,
    user_id int not null references users(id)
)