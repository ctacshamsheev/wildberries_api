create TABLE IF NOT EXISTS product_table
(
    id       varchar(255),
    sup_id   varchar(255),
    wb_id    bigint,
    category varchar(255),
    subject  varchar(255),
    brand    varchar(255),
    size     varchar(255),
    PRIMARY KEY (id)
);