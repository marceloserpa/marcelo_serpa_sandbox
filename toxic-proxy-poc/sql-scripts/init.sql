CREATE TABLE character (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(200) NOT NULL,
   height VARCHAR(20),
   mass VARCHAR(20),
   hair_color VARCHAR(100),
   eye_color VARCHAR(100),
   birth_year VARCHAR(20),
   created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);