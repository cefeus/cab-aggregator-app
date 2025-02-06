CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

ALTER TABLE passengers
    ADD uuid_id uuid NOT NULL DEFAULT uuid_generate_v4();
