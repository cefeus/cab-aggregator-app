CREATE TABLE IF NOT EXISTS rides
(
    id           BIGSERIAL PRIMARY KEY,
    driver_id    UUID             NOT NULL,
    passenger_id UUID             NOT NULL,
    price        DOUBLE PRECISION NOT NULL,
    payment_type INTEGER          NOT NULL,
    from_address TEXT             NOT NULL,
    to_address   TEXT             NOT NULL,
    ride_status  INTEGER          NOT NULL,
    created_at   TIMESTAMP        NOT NULL,
    modified_at  TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP
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
    ON rides
    FOR EACH ROW
    WHEN (OLD.* IS DISTINCT FROM NEW.*)
EXECUTE FUNCTION update_modified_at_column();