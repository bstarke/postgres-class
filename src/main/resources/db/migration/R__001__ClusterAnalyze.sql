-- ${flyway:timestamp}
CREATE INDEX IF NOT EXISTS movie_title_idx
    ON movie (title, year);

CLUSTER movie USING movie_title_idx;

ANALYZE;