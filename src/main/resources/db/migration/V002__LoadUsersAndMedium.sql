INSERT INTO public.users (id, display_name, email, user_id)
VALUES ('a8117e44-c5b1-40d2-9572-81182a718d5f', 'Bradley Starkenberg', 'brad@starkenberg.net', 'bstarke')
     , ('b6027d1b-209b-44ec-ad41-f2d26f30e4d2', 'Admin Admin', 'admin@starkenberg.net', 'admin');

-- Type is Enum from Java code (ordinal position is used DVD = 0, BluRay = 1, Digital = 2)
INSERT INTO public.medium (id, location, type)
VALUES (1, 'Plex', 2)
     , (2, 'iTunes', 2);
