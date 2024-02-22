CREATE SEQUENCE IF NOT EXISTS medium_seq
    START WITH 1 INCREMENT BY 1;
SELECT SETVAL('medium_seq',  (SELECT max(id) FROM medium));

CREATE SEQUENCE IF NOT EXISTS movie_inventory_seq
    START WITH 1 INCREMENT BY 5;
SELECT SETVAL('movie_inventory_seq',  (SELECT max(id) FROM movie_inventory));

CREATE SEQUENCE IF NOT EXISTS movie_seq
    START WITH 1 INCREMENT BY 5;
SELECT SETVAL('movie_seq',  (SELECT max(id) FROM movie));

CREATE INDEX IF NOT EXISTS movie_inventory_media_pk_rev
    ON movie_inventory_media (media_id, movie_item_id);

CREATE INDEX IF NOT EXISTS movie_title_idx
    ON movie (title, year);

ALTER TABLE medium
    ADD CONSTRAINT uc_medium UNIQUE (type, location);

ALTER TABLE movie_inventory
    ADD CONSTRAINT uc_movie_inventory UNIQUE (owner_id, movie_id);

ALTER TABLE movie
    ADD CONSTRAINT uc_movie_imdbid UNIQUE (imdbid);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_userid UNIQUE (user_id);

ALTER TABLE movie_inventory
    ADD CONSTRAINT fk_movie_inventory_on_movie FOREIGN KEY (movie_id) REFERENCES movie (id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE movie_inventory
    ADD CONSTRAINT fk_movie_inventory_on_owner FOREIGN KEY (owner_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE movie_inventory_media
    ADD CONSTRAINT fk_movinvmed_on_medium FOREIGN KEY (media_id) REFERENCES medium (id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE movie_inventory_media
    ADD CONSTRAINT fk_movinvmed_on_movie_item FOREIGN KEY (movie_item_id) REFERENCES movie_inventory (id) ON UPDATE CASCADE ON DELETE CASCADE;