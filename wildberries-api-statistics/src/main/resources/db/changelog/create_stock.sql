create TABLE IF NOT EXISTS stock_table
(
    id                 bigint not null,
    last_change_date   timestamp,
    warehouse_name     varchar(255),
    product_id         varchar(255),
    quantity           integer,
    in_way_to_client   integer,
    in_way_from_client integer,
    quantity_full      integer,
    price              numeric(19, 2),
    discount           numeric(19, 2),
    is_supply          boolean,
    is_realization     boolean,
    sc_code            varchar(255),
    primary key (id),
    CONSTRAINT FK_stock_product_id FOREIGN KEY (product_id) REFERENCES product_table (id)
);