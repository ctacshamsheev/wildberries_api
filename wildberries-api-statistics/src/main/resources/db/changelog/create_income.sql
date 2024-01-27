create TABLE IF NOT EXISTS income_table
(
    id               bigint not null,
    income_id        integer,
    number           varchar(255),
    date_in          date,
    last_change_date timestamp,
    product_id       varchar(255),
    quantity         integer,
    total_price      numeric(19, 2),
    date_close       date,
    warehouse_name   varchar(255),
    status           varchar(255),
    primary key (id),
    CONSTRAINT FK_income_product_id FOREIGN KEY (product_id) REFERENCES product_table (id)
);