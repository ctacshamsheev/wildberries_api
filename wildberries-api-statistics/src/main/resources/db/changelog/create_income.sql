create TABLE IF NOT EXISTS income_table
(
    id               bigint not null,
    date_in          date,
    date_close       date,
    income_id        integer,
    last_change_date timestamp,
    number           varchar(255),
    quantity         integer,
    status           varchar(255),
    total_price      numeric(19, 2),
    warehouse_name   varchar(255),
    product_id       varchar(255),
    primary key (id),
    CONSTRAINT FK_income_product_id FOREIGN KEY (product_id) REFERENCES product_table (id)
);