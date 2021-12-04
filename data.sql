-- Load initial sample data


-- Load platforms data
INSERT INTO platforms (platform_id, platform_name) VALUES (1, 'Windows');
INSERT INTO platforms (platform_id, platform_name) VALUES (2, 'Xbox One');
INSERT INTO platforms (platform_id, platform_name) VALUES (3, 'Xbox Series X/S');
INSERT INTO platforms (platform_id, platform_name) VALUES (4, 'Playstation 4');
INSERT INTO platforms (platform_id, platform_name) VALUES (5, 'Playstation 5');
INSERT INTO platforms (platform_id, platform_name) VALUES (6, 'Nintendo Switch');
INSERT INTO platforms (platform_id, platform_name) VALUES (7, 'SNES');
INSERT INTO platforms (platform_id, platform_name) VALUES (8, 'NES');
INSERT INTO platforms (platform_id, platform_name) VALUES (9, 'Arcade');
INSERT INTO platforms (platform_id, platform_name) VALUES (10, 'Mobile');


-- Load publishers data
INSERT INTO publishers (publisher_id, publisher_name) VALUES (1, 'Gearbox Publishing');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (2, 'Supergiant Games');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (3, 'Matt Makes Games');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (4, 'FromSoftware');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (5, 'Nintendo');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (6, 'ConcernedApe');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (7, '7th Beat Games');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (8, 'CAPCOM');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (9, 'Peropero Games');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (10, 'OMOCAT');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (11, 'Hempuli Oy');


-- Load genres data
INSERT INTO genres (genre_id, genre_name) VALUES (1, 'Rogue');
INSERT INTO genres (genre_id, genre_name) VALUES (2, 'Platformer');
INSERT INTO genres (genre_id, genre_name) VALUES (3, 'RPG');
INSERT INTO genres (genre_id, genre_name) VALUES (4, 'Simulation');
INSERT INTO genres (genre_id, genre_name) VALUES (5, 'Music');
INSERT INTO genres (genre_id, genre_name) VALUES (6, 'Metroidvania');
INSERT INTO genres (genre_id, genre_name) VALUES (7, 'Fighting');
INSERT INTO genres (genre_id, genre_name) VALUES (8, 'Puzzle');


-- Load games data
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (1, 1, 1, 2020, 'Risk of Rain 2', 'Committing platentary genocide for fun');
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (2, 1, 2, 2020, 'Hades', 'A godlike roguelike');
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (3, 2, 3, 2018, 'Celeste', 'Overcoming mountains');
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (4, 3, 4, 2011, 'Dark Souls', 'Cursed pilgrimage simulator');
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (5, 4, 6, 2016, 'Stardew Valley', 'Farming simulator');
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (6, 3, 5, 2019, 'Fire Emblem: Three Houses', 'Play one of three tactical storylines');
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (7, 5, 7, 2021, 'Rhythm Doctor', 'Press SPACE on the 7th beat!');
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (8, 6, 5, 1986, 'Metroid', 'The original 2D platformer exploration game');
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (9, 5, 7, 2018, 'A Dance of Fire and Ice', 'A one touch game of intertwined energies');
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (10, 2, 5, 1985, 'Super Mario Bros.', "It's a me! Mario");
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (11, 7, 8, 1994, 'Street Fighter', "Hadouken!");
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (12, 5, 9, 2019, 'Muse Dash', "Fulllll Combo!");
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (13, 3, 10, 2020, 'OMORI', "Don't worry...everything is going to be okay...");
INSERT INTO games (game_id, genre_id, publisher_id, release_year, game_name, game_description) VALUES (14, 8, 11, 2019, 'Baba is You', "BABA is YOU");


-- Load user data
INSERT INTO users (name, allowed_rentals) VALUES ("Bob", 10);
INSERT INTO users (name, allowed_rentals) VALUES ("Hailey", 4);
INSERT INTO users (name, allowed_rentals) VALUES ("Logan", 3);
INSERT INTO users (name, allowed_rentals) VALUES ("Paige", 5);
INSERT INTO users (name, allowed_rentals) VALUES ("Ian", 15);
INSERT INTO users (name, allowed_rentals) VALUES ("Omori", 2);
INSERT INTO users (name, allowed_rentals) VALUES ("Aubrey", 3);
INSERT INTO users (name, allowed_rentals) VALUES ("Kel", 2);
INSERT INTO users (name, allowed_rentals) VALUES ("Hero", 4);
INSERT INTO users (name, allowed_rentals) VALUES ("Basil", 9);


-- Load inventory data
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (1, 1, 100);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (1, 2, 10);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (1, 3, 20);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (2, 1, 100);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (2, 6, 50);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (2, 3, 50);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (3, 2, 50);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (3, 3, 50);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (3, 6, 50);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (4, 4, 3);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (4, 5, 3);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (5, 1, 1);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (5, 2, 1);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (5, 3, 1);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (5, 4, 1);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (5, 5, 1);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (5, 6, 1);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (6, 6, 100);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (6, 7, 10);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (6, 8, 1);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (7, 8, 2);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (8, 8, 20);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (9, 1, 20);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (9, 10, 2);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (10, 1, 2000);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (10, 2, 2000);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (10, 3, 2000);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (10, 4, 2000);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (10, 5, 2000);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (10, 6, 2000);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (10, 7, 2000);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (10, 8, 2000);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (10, 9, 2000);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (10, 10, 2000);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (11, 8, 888);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (12, 1, 4);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (12, 10, 4);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (13, 1, 10);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (13, 6, 10);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (14, 1, 1);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (14, 2, 2);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (14, 3, 3);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (14, 4, 4);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (14, 5, 5);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (14, 6, 6);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (14, 7, 7);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (14, 8, 8);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (14, 9, 9);
INSERT INTO inventory (game_id, platform_id, available_copies) VALUES (14, 10, 10);


-- Load rental data
INSERT INTO rentals (user_id, inventory_id, rented_at, rental_length) VALUES (1, 1, '2021-12-03', 10);
INSERT INTO rentals (user_id, inventory_id, rented_at, rental_length) VALUES (2, 3, '1999-07-10', 4);
INSERT INTO rentals (user_id, inventory_id, rented_at, rental_length) VALUES (3, 4, '1983-12-05', 1);
INSERT INTO rentals (user_id, inventory_id, rented_at, rental_length) VALUES (4, 5, '2012-12-31', 30);
INSERT INTO rentals (user_id, inventory_id, rented_at, rental_length) VALUES (5, 7, '2000-11-09', 365);
INSERT INTO rentals (user_id, inventory_id, rented_at, rental_length) VALUES (6, 8, '2021-10-03', 30);
INSERT INTO rentals (user_id, inventory_id, rented_at, rental_length) VALUES (7, 9, '9999-02-28', 2);
INSERT INTO rentals (user_id, inventory_id, rented_at, rental_length) VALUES (8, 10, '2001-12-03', 100);
INSERT INTO rentals (user_id, inventory_id, rented_at, rental_length) VALUES (9, 11, '1990-12-03', 1);
INSERT INTO rentals (user_id, inventory_id, rented_at, rental_length) VALUES (10, 12, '1999-12-03', 999);