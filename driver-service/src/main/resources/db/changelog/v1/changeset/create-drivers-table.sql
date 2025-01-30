CREATE TABLE drivers
(
    id          BIGSERIAL PRIMARY KEY,
    first_name   TEXT      NOT NULL,
    last_name    TEXT      NOT NULL,
    phone       TEXT      NOT NULL,
    email       TEXT      NOT NULL,
    rating      TEXT      NOT NULL default 5.0,
    is_active   boolean   NOT NULL default true,
    status      integer           default 1,
    sex         integer           default 0,
    created_at  timestamp NOT NULL,
    modified_at timestamp NOT NULL default CURRENT_TIMESTAMP
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