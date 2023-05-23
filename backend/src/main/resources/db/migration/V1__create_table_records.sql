CREATE TABLE records(
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP,
    speed INTEGER,
    license_plate VARCHAR(7),
    vehicle_type VARCHAR(20)
);

