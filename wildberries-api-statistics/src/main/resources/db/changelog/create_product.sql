create TABLE IF NOT EXISTS product_table
(
    id       bigint not null,
    brand    varchar(255),
    category varchar(255),
    size     varchar(255),
    subject  varchar(255),
    sup_id   varchar(255),
    wb_id    bigint,
    PRIMARY KEY (id)
);