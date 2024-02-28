SELECT title, year
  FROM movie
 WHERE title IN ('The Black Hole', 'The Black Cauldron', 'The Blind Side')
 ORDER BY title, year;

SELECT title, year
  FROM movie
 WHERE title LIKE ('The Bl%')
 ORDER BY title, year;


SELECT year, COUNT(*) AS number_of_movies
  FROM movie
 GROUP BY year
 ORDER BY number_of_movies DESC
 LIMIT 10;

SELECT m.title, m.year, med.location
  FROM movie_inventory mi
       INNER JOIN movie m ON mi.movie_id = m.id
       INNER JOIN movie_inventory_media mim ON mi.id = mim.movie_item_id
       INNER JOIN medium med ON mim.media_id = med.id
 WHERE m.title = 'White Zombie'
 ORDER BY m.title, m.year, med.type;



SELECT m.title, m.year
  FROM movie m
       INNER JOIN movie_inventory mi ON m.id = mi.movie_id
 WHERE mi.owner_id = 'a8117e44-c5b1-40d2-9572-81182a718d5f'
   AND m.id NOT IN
       (SELECT m2.id
          FROM movie m2
               INNER JOIN movie_inventory mi ON m2.id = mi.movie_id
               INNER JOIN movie_inventory_media mim ON mi.id = mim.movie_item_id
         WHERE mi.owner_id = 'a8117e44-c5b1-40d2-9572-81182a718d5f'
           AND mim.media_id = 1)
 ORDER BY m.title;

SELECT m.title, m.year
  FROM movie m
       INNER JOIN movie_inventory mi
                  ON m.id = mi.movie_id
       LEFT JOIN movie_inventory_media mim
                 ON mi.id = mim.movie_item_id
                     AND mim.media_id > 1
       LEFT JOIN movie_inventory_media mim2
                 ON mi.id = mim2.movie_item_id
                     AND mim2.media_id = 1
 WHERE mi.owner_id = 'a8117e44-c5b1-40d2-9572-81182a718d5f'
   AND mim2.media_id IS NULL
 ORDER BY m.title;



DROP SEQUENCE testing_1_seq;
CREATE SEQUENCE IF NOT EXISTS testing_1_seq
    START WITH 99 INCREMENT BY 1;
SELECT NEXTVAL('testing_1_seq');
SELECT SETVAL('testing_1_seq', (SELECT MAX(id) FROM movie_inventory));
SELECT CURRVAL('testing_1_seq');

SELECT all_ids AS missing_ids
  FROM GENERATE_SERIES((SELECT MIN(id) FROM movie), (SELECT MAX(id) FROM movie)) all_ids
EXCEPT
SELECT id
  FROM movie
 ORDER BY missing_ids;

SELECT *
  FROM flyway_schema_history;


SELECT m.id, m.title, m.year
  FROM movie m
       LEFT JOIN movie_inventory mi ON m.id = mi.movie_id
 WHERE mi.id IS NULL;