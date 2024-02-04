create TABLE IF NOT EXISTS users
(
    id           bigint generated by default as identity,
    username     varchar(255) not null,
    password     varchar(255),
    groups       varchar(255),
    enabled      boolean      not null,
    locked       boolean      not null,
    expired      boolean      not null,
    cred_expired boolean      not null,
    primary key (id)
);