create TABLE IF NOT EXISTS note_table
(
    id      bigint NOT NULL,
    name    varchar(255),
    address varchar(255),
    phone   varchar(255),
    PRIMARY KEY (id)
);