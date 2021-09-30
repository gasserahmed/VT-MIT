
--
-- Drop tables in reverse order of their creation
--

DROP TABLE IF EXISTS `customer_order_line_item`;
DROP TABLE IF EXISTS `book`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `customer_order`;
DROP TABLE IF EXISTS `customer`;

--
-- Table structure for table `customer`
--

CREATE  TABLE `customer` (
  `customer_id` INT UNSIGNED AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `cc_number` VARCHAR(19) NOT NULL,
  `cc_exp_date` DATE NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE = InnoDB;

--
-- Table structure for table `customer_order`
--

CREATE  TABLE `customer_order` (
  `customer_order_id` INT UNSIGNED AUTO_INCREMENT,
  `amount` INT UNSIGNED NOT NULL,
  `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `confirmation_number` INT UNSIGNED NOT NULL,
  `customer_id` INT UNSIGNED,
  PRIMARY KEY (`customer_order_id`),  
  FOREIGN KEY (`customer_id`) REFERENCES `customer`(`customer_id`)
) ENGINE = InnoDB;

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` INT UNSIGNED AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE = InnoDB;

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `book_id` INT UNSIGNED AUTO_INCREMENT,
  `title` VARCHAR(60) NOT NULL,
  `author` VARCHAR(60) NOT NULL,
  `description` VARCHAR(1000) NOT NULL,
  `price` INT UNSIGNED NOT NULL,
  `rating` INT UNSIGNED NOT NULL,
  `is_public` BOOLEAN NOT NULL,
  `is_featured` BOOLEAN NOT NULL,
  `category_id` INT UNSIGNED,
  PRIMARY KEY (`book_id`),
  FOREIGN KEY (`category_id`) REFERENCES `category`(`category_id`)
) ENGINE = InnoDB;

--
-- Table structure for the table `customer_order_line_item`
--

CREATE TABLE `customer_order_line_item` (
  `customer_order_id` INT UNSIGNED,
  `book_id` INT UNSIGNED,
  `quantity` SMALLINT UNSIGNED DEFAULT 1,
  PRIMARY KEY (`customer_order_id`, `book_id`),
  FOREIGN KEY (`customer_order_id`) REFERENCES `customer_order`(`customer_order_id`),
  FOREIGN KEY (`book_id`) REFERENCES `book`(`book_id`)
) ENGINE = InnoDB;
