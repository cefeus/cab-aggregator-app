CREATE TABLE passengers
(
    id           BIGSERIAL PRIMARY KEY,
    first_name   TEXT      NOT NULL,
    last_name    TEXT      NOT NULL,
    phone_number TEXT      NOT NULL,
    email        TEXT,
    rating       TEXT      NOT NULL DEFAULT 5.0,
    is_active    BOOLEAN   NOT NULL DEFAULT true,
    payment_type TEXT               DEFAULT 'CASH',
    created_at   TIMESTAMP NOT NULL,
    modified_at  TIMESTAMP NOT NULL
);

CREATE OR REPLACE FUNCTION update_modified_at_column()
    RETURNS TRIGGER
AS
$$
BEGIN
    NEW.modified_at = NOW();
    RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_modified_at_trigger
    BEFORE UPDATE
    ON passengers
    FOR EACH ROW
    WHEN (OLD.* IS DISTINCT FROM NEW.*)
EXECUTE FUNCTION update_modified_at_column();