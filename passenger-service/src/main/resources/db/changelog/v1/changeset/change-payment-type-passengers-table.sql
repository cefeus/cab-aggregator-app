ALTER TABLE passengers ALTER COLUMN payment_type DROP DEFAULT;

ALTER TABLE passengers
    ALTER COLUMN payment_type TYPE smallint USING (NULLIF(payment_type, '')::smallint);

ALTER TABLE passengers
    ALTER COLUMN payment_type SET default 2;