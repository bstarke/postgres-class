INSERT INTO movie ( id, actors, awards, box_office, country, director, dvd, genre, imdbid, imdb_rating, imdb_votes
                  , language, metascore, plot, poster, production, rated, released, runtime, title, type, writer, year)
VALUES ( (SELECT NEXTVAL('movie_seq')), 'Ben Affleck, Anna Kendrick, J.K. Simmons', '3 wins & 5 nominations'
       , '$86,260,045', 'United States', 'Gavin O''Connor', '10 Jan 2017', 'Action, Crime, Drama', 'tt2140479'
       , '7.3', '274,144', 'English, Indonesian, French', '51'
       , 'As a math savant uncooks the books for a new client, the Treasury Department closes in on his activities, and the body count starts to rise.'
       , 'https://m.media-amazon.com/images/M/MV5BNDc5Mzg2NTYxNV5BMl5BanBnXkFtZTgwMjQ2ODAwOTE@._V1_SX300.jpg'
       , 'Warner Bros. Pictures, Electric City Entertainment', 'R', '14 Oct 2016', '128 min', 'The Accountant'
       , 'movie', 'Bill Dubuque', '2016');

INSERT INTO movie_inventory (id, movie_id, owner_id)
VALUES ((SELECT NEXTVAL('movie_inventory_seq')), (SELECT CURRVAL('movie_seq')), 'a8117e44-c5b1-40d2-9572-81182a718d5f');

-- Type is Enum from Java code (ordinal position is used)
INSERT INTO public.medium (id, location, type)
VALUES ((SELECT NEXTVAL('medium_seq')), 'Book 1 Page 2', 1);

INSERT INTO movie_inventory_media (movie_item_id, media_id)
VALUES ((SELECT CURRVAL('movie_inventory_seq')), (SELECT id FROM medium WHERE location = 'Plex'))
     , ((SELECT CURRVAL('movie_inventory_seq')), (SELECT id FROM medium WHERE location = 'iTunes'))
     , ((SELECT CURRVAL('movie_inventory_seq')), (SELECT CURRVAL('medium_seq')));
