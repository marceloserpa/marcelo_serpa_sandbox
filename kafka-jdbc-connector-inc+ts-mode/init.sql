CREATE TABLE facts (
    id serial PRIMARY KEY,
    event_content VARCHAR NOT NULL,
    entity VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
