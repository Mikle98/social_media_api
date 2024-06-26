create table friends (
    id serial primary key,
    toUser int not null references users(id),
    fromUser  int not null references users(id),
    status varchar
);