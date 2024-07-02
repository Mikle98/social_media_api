alter table posts
add column fileId int references files(id);