-- Load initial sample data

-- Load platforms data
INSERT INTO platforms (platform_id, platform_name) VALUES (1, 'Windows');
INSERT INTO platforms (platform_id, platform_name) VALUES (2, 'Xbox One');
INSERT INTO platforms (platform_id, platform_name) VALUES (3, 'Xbox Series X/S');
INSERT INTO platforms (platform_id, platform_name) VALUES (4, 'Playstation 4');
INSERT INTO platforms (platform_id, platform_name) VALUES (5, 'Playstation 5');
INSERT INTO platforms (platform_id, platform_name) VALUES (6, 'Nintendo Switch');

-- Load publishers data
INSERT INTO publishers (publisher_id, publisher_name) VALUES (1, 'Gearbox Publishing');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (2, 'Supergiant Games');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (3, 'Matt Makes Games');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (4, 'FromSoftware');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (5, 'Nintendo');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (6, 'ConcernedApe');

-- Load genres data
INSERT INTO genres (genre_id, genre_name) VALUES (1, 'Rogue');
INSERT INTO genres (genre_id, genre_name) VALUES (2, 'Platformer');
INSERT INTO genres (genre_id, genre_name) VALUES (3, 'RPG');
INSERT INTO genres (genre_id, genre_name) VALUES (4, 'Simulation');


-- Load games data
INSERT INTO games (game_id, genre_id, publisher_id, platform_id, release_year, game_name, game_description) VALUES (1, 1, 1, 1, 2020, 'Risk of Rain 2', 'Committing platentary genocide for fun');
INSERT INTO games (game_id, genre_id, publisher_id, platform_id, release_year, game_name, game_description) VALUES (2, 1, 2, 1, 2020, 'Hades', 'A godlike roguelike');
INSERT INTO games (game_id, genre_id, publisher_id, platform_id, release_year, game_name, game_description) VALUES (3, 2, 3, 1, 2018, 'Celeste', 'Overcoming mountains');
INSERT INTO games (game_id, genre_id, publisher_id, platform_id, release_year, game_name, game_description) VALUES (4, 3, 4, 1, 2011, 'Dark Souls', 'Cursed pilgrimage simulator');
INSERT INTO games (game_id, genre_id, publisher_id, platform_id, release_year, game_name, game_description) VALUES (5, 4, 6, 1, 2016, 'Stardew Valley', 'Farming simulator');
INSERT INTO games (game_id, genre_id, publisher_id, platform_id, release_year, game_name, game_description) VALUES (6, 3, 5, 1, 2019, 'Fire Emblem: Three Houses', 'Play one of three tactical storylines');
