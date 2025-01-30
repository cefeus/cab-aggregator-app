CREATE TABLE cars_drivers
(
    id BIGINT NOT NULL,
    car_id BIGINT NOT NULL,
    driver_id BIGINT NOT NULL,
    PRIMARY KEY (car_id, driver_id)
);