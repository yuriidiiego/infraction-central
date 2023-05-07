CREATE TABLE records(
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP,
    speed INTEGER NOT NULL,
    license_plate VARCHAR(7) NOT NULL,
    vehicle_type VARCHAR(20) NOT NULL
);

