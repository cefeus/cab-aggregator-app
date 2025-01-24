CREATE TABLE passengers(
  id BIGSERIAL PRIMARY KEY,
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  phone_number TEXT NOT NULL,
  email TEXT,
  rating double precision NOT NULL default 5.0,
  is_active boolean NOT NULL default true,
  payment_type TEXT,
  created_at timestamp NOT NULL,
  modified_at timestamp NOT NULL
);