create TABLE IF NOT EXISTS api_request_result_table
(
    id              bigint not null,
    start_date_time timestamp,
    end_date_time   timestamp,
    api_type        varchar(255),
    api_status      varchar(255),
    error_message   varchar(255),
    count           integer,
    primary key (id)
);