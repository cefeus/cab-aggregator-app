ALTER TABLE passengers ALTER COLUMN payment_type DROP DEFAULT;

ALTER TABLE passengers
    ALTER COLUMN payment_type TYPE SMALLINT USING (NULLIF(payment_type, '')::smallint);

ALTER TABLE passengers
    ALTER COLUMN payment_type SET DEFAULT 2;