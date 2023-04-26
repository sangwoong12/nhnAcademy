CREATE TABLE `user` (
  `user_id` varchar(255) PRIMARY KEY,
  `user_password` varchar(255)
);

CREATE TABLE `profile` (
  `user_id` varchar(255),
  `user_name` varchar(255),
  `image_url` varchar(255),
  `introduction` varchar(255)
);

CREATE TABLE `post` (
  `post_id` integer PRIMARY KEY,
  `category_id` integer,
  `board_id` integer,
  `title` varchar(255),
  `content` text,
  `view_count` integer,
  `write_time` date
);

CREATE TABLE `category` (
  `category_id` integer PRIMARY KEY,
  `category` varchar(255)
);

CREATE TABLE `comment` (
  `comment_id` integer PRIMARY KEY,
  `parent_id` integer,
  `post_id` integer,
  `content` varchar(255),
  `user_id` varchar(255)
);

CREATE TABLE `post_image` (
  `post_id` integer,
  `image_id` integer
);

CREATE TABLE `image` (
  `image_id` integer PRIMARY KEY,
  `image_url` varchar(255),
  `image_name` varchar(255)
);

CREATE TABLE `blog` (
  `blog_id` integer PRIMARY KEY,
  `owner_id` varchar(255)
);

ALTER TABLE `post` ADD FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`comment_id`) REFERENCES `user` (`user_id`);

ALTER TABLE `post_image` ADD FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);

ALTER TABLE `post_image` ADD FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`);

ALTER TABLE `profile` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

ALTER TABLE `post` ADD FOREIGN KEY (`board_id`) REFERENCES `blog` (`blog_id`);

ALTER TABLE `blog` ADD FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`parent_id`) REFERENCES `comment` (`comment_id`);
