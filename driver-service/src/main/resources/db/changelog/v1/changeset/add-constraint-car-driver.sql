ALTER TABLE cars_drivers
    ADD FOREIGN KEY (car_id) REFERENCES cars (id);
ALTER TABLE cars_drivers
    ADD FOREIGN KEY (driver_id) REFERENCES drivers (id);