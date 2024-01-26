create TABLE IF NOT EXISTS stock_table
(
    id                 bigint not null,
    discount           numeric(19, 2),
    in_way_from_client integer,
    in_way_to_client   integer,
    is_realization     boolean,
    is_supply          boolean,
    last_change_date   timestamp,
    price              numeric(19, 2),
    quantity           integer,
    quantity_full      integer,
    sc_code            varchar(255),
    warehouse_name     varchar(255),
    product_id         varchar(255),
    primary key (id),
    CONSTRAINT FK_stock_product_id FOREIGN KEY (product_id) REFERENCES product_table (id)
);