create table if not exists users
(
    id         serial primary key,
    email      varchar(100) unique not null,
    name       varchar(100),
    password   varchar(100)        not null,
    provider   provider_type_enum default 'local',
    avatar_url varchar(200),
    created_at timestamp          default now()
)
