CREATE TABLE IF NOT EXISTS medium (
    id       BIGINT NOT NULL,
    location VARCHAR(255),
    type     SMALLINT,  --Ordinal Value from Enum MediaType
    CONSTRAINT pk_medium PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS movie (
    id          BIGINT NOT NULL,
    actors      VARCHAR(151),
    awards      VARCHAR(152),
    box_office  VARCHAR(15),
    country     VARCHAR(153),
    director    VARCHAR(250),
    dvd         VARCHAR(11),
    genre       VARCHAR(100),
    imdbid      VARCHAR(10),
    imdb_rating VARCHAR(5),
    imdb_votes  VARCHAR(15),
    language    VARCHAR(155),
    metascore   VARCHAR(3),
    plot        VARCHAR(251),
    poster      VARCHAR(252),
    production  VARCHAR(156),
    rated       VARCHAR(10),
    released    VARCHAR(15),
    runtime     VARCHAR(10),
    title       VARCHAR(157),
    type        VARCHAR(25),
    writer      VARCHAR(260),
    year        VARCHAR(4),
    CONSTRAINT pk_movie PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS movie_inventory (
    id       BIGINT NOT NULL,
    movie_id BIGINT, --REFERENCES movie (id)
    owner_id VARCHAR(50), --REFERENCES users (id)
    CONSTRAINT pk_movie_inventory PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS movie_inventory_media (
    movie_item_id BIGINT NOT NULL, --REFERENCES movie_inventory (id)
    media_id      BIGINT NOT NULL, --REFERENCES medium (id)
    CONSTRAINT pk_movie_inventory_media PRIMARY KEY (movie_item_id, media_id)
);

CREATE TABLE IF NOT EXISTS users (
    id           VARCHAR(50) NOT NULL,
    display_name VARCHAR(100),
    email        VARCHAR(100),
    user_id      VARCHAR(100),
    CONSTRAINT pk_users PRIMARY KEY (id)
);