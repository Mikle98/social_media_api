create table friends (
    id serial primary key,
    user_main int not null references users(id),
    user_friend  int not null references users(id),
    status varchar
);