CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER TABLE drivers
    ADD uuid_id uuid NOT NULL DEFAULT uuid_generate_v4();

ALTER TABLE cars
    ADD uuid_id uuid NOT NULL default uuid_generate_v4();