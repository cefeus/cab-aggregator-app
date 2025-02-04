CREATE TABLE drivers
(
    id          BIGSERIAL PRIMARY KEY,
    first_name  TEXT      NOT NULL,
    last_name   TEXT      NOT NULL,
    phone       TEXT      NOT NULL,
    email       TEXT      NOT NULL,
    rating      TEXT      NOT NULL DEFAULT 5.0,
    is_active   BOOLEAN   NOT NULL DEFAULT true,
    status      INTEGER            DEFAULT 1,
    sex         INTEGER            DEFAULT 0,
    created_at  TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
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
    ON drivers
    FOR EACH ROW
    WHEN (OLD.* IS DISTINCT FROM NEW.*)
EXECUTE FUNCTION update_modified_at_column();