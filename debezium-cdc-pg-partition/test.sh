#!/bin/bash

#!/bin/bash

docker exec -i debezium-cdc-rabbit-db bash -c "psql -U postgres -d debezium-cdc-rabbit-db -c \"
INSERT INTO measurement (city_id, logdate, peaktemp, unitsales) VALUES (1, '2025-01-15', 25, 120);
INSERT INTO measurement (city_id, logdate, peaktemp, unitsales) VALUES (2, '2025-02-10', 28, 150);
INSERT INTO measurement (city_id, logdate, peaktemp, unitsales) VALUES (3, '2025-03-22', 30, 200);
INSERT INTO measurement (city_id, logdate, peaktemp, unitsales) VALUES (1, '2025-04-18', 27, 180);

INSERT INTO measurement (city_id, logdate, peaktemp, unitsales) VALUES (4, '2025-06-05', 33, 220);
INSERT INTO measurement (city_id, logdate, peaktemp, unitsales) VALUES (2, '2025-07-12', 35, 250);
INSERT INTO measurement (city_id, logdate, peaktemp, unitsales) VALUES (3, '2025-08-25', 36, 300);
INSERT INTO measurement (city_id, logdate, peaktemp, unitsales) VALUES (4, '2025-09-14', 32, 210);
INSERT INTO measurement (city_id, logdate, peaktemp, unitsales) VALUES (5, '2025-11-03', 29, 190);
INSERT INTO measurement (city_id, logdate, peaktemp, unitsales) VALUES (1, '2025-12-20', 26, 160);
\""
