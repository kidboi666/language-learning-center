create table if not exists users
(
    id         serial primary key,
    email      varchar(100) unique not null,
    name       varchar(100),
    password   varchar(100),
    provider   provider_type_enum default 'local',
    sns_id     bigint,
    avatar_url varchar(200),
    created_at timestamp          default now()
);

create table if not exists posts
(
    id         serial primary key,
    title      text not null,
    content    text not null,
    image      text,
    created_at timestamp default now()
);

create table if not exists hashtags
(
    id         serial primary key,
    title      text not null,
    created_at timestamp default now()
);

create table if not exists post_hashtags
(
    post_id    int not null,
    hashtag_id int not null,
    primary key (post_id, hashtag_id),
    foreign key (post_id) references posts (id) on delete cascade,
    foreign key (hashtag_id) references hashtags (id) on delete cascade
);

create table if not exists domain
(
    id            serial primary key,
    host          varchar(80) not null,
    type          domain_type_enum default ('free'),
    client_secret uuid             default gen_random_uuid(),
    created_at    timestamp        default now(),
    user_id       int unique  not null,
    constraint fk_user foreign key (user_id) references users (id) on delete cascade
);

