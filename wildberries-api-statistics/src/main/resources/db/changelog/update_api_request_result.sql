ALTER TABLE IF EXISTS api_request_result_table
    ADD COLUMN IF NOT EXISTS from_date_time timestamp DEFAULT NOW();
