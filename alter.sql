-- Create publishers table
CREATE TABLE `publishers` (
  `publisher_id` int NOT NULL AUTO_INCREMENT,
  `publisher_name` varchar(100) NOT NULL,
  PRIMARY KEY (`publisher_id`)
);

-- Create platforms table
CREATE TABLE `platforms` (
  `platform_id` int NOT NULL AUTO_INCREMENT,
  `platform_name` varchar(100) NOT NULL,
  PRIMARY KEY (`platform_id`)
);

-- Create genres table
CREATE TABLE `genres` (
  `genre_id` int NOT NULL AUTO_INCREMENT,
  `genre_name` varchar(100) NOT NULL,
  PRIMARY KEY (`genre_id`)
);

-- Create games table
CREATE TABLE `games` (
  `game_id` int NOT NULL AUTO_INCREMENT,
  `genre_id` int NOT NULL,
  `publisher_id` int NOT NULL,
  `release_year` int NOT NULL,
  `game_name` varchar(100) NOT NULL,
  `game_description` text NOT NULL,
  PRIMARY KEY (`game_id`),
  KEY `genre_id` (`genre_id`),
  KEY `publisher_id` (`publisher_id`),
  CONSTRAINT `games_ibfk_1` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`genre_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `games_ibfk_2` FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`publisher_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create inventory table
CREATE TABLE `inventory` (
  `inventory_id` int NOT NULL AUTO_INCREMENT,
  `game_id` int NOT NULL,
  `platform_id` int NOT NULL,
  `available_copies` int NOT NULL,
  PRIMARY KEY (`inventory_id`),
  UNIQUE KEY `game_and_platform` (`game_id`,`platform_id`),
  KEY `platform_id` (`platform_id`),
  CONSTRAINT `inventory_ibfk_3` FOREIGN KEY (`game_id`) REFERENCES `games` (`game_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inventory_ibfk_4` FOREIGN KEY (`platform_id`) REFERENCES `platforms` (`platform_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create users table
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `allowed_rentals` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`)
);

-- Create rentals table
CREATE TABLE `rentals` (
  `rental_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `inventory_id` int NOT NULL,
  `rented_at` date NOT NULL,
  `rental_length` int NOT NULL,
  `is_returned` tinyint(1) NOT NULL DEFAULT '0',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`rental_id`),
  KEY `user_id` (`user_id`),
  KEY `inventory_id` (`inventory_id`),
  CONSTRAINT `rentals_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rentals_ibfk_2` FOREIGN KEY (`inventory_id`) REFERENCES `inventory` (`inventory_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
