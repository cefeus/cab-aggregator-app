CREATE TABLE cars
(
    id          BIGSERIAL PRIMARY KEY,
    color       TEXT      NOT NULL,
    brand       TEXT      NOT NULL,
    car_number  TEXT      NOT NULL,
    is_active   boolean   NOT NULL DEFAULT true,
    created_at  timestamp NOT NULL,
    modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
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
    ON cars
    FOR EACH ROW
    WHEN (OLD.* IS DISTINCT FROM NEW.*)
EXECUTE FUNCTION update_modified_at_column();