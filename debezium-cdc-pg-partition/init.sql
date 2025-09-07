CREATE TABLE measurement (
    city_id         int not null,
    logdate         date not null,
    peaktemp        int,
    unitsales       int
) PARTITION BY RANGE (logdate);


CREATE TABLE measurement_y2025_s01 PARTITION OF measurement
    FOR VALUES FROM ('2025-01-01') TO ('2025-05-31');

CREATE TABLE measurement_y2025_s02 PARTITION OF measurement
    FOR VALUES FROM ('2025-06-01') TO ('2025-12-31');