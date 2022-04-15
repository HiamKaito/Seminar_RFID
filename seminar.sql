-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2022 at 04:35 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `seminar`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `book_id` varchar(50) NOT NULL,
  `book_title` varchar(50) NOT NULL,
  `book_status` int(11) NOT NULL,
  `book_author` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`book_id`, `book_title`, `book_status`, `book_author`) VALUES
('111', 'Nghìn lẻ một đêm', 1, 'Ko biết'),
('222', 'Harry Potter', 2, 'J.K Rowling'),
('333', 'The Da Vinci Code', 2, 'Dan Brown');

-- --------------------------------------------------------

--
-- Table structure for table `borrow`
--

CREATE TABLE `borrow` (
  `borrow_id` varchar(50) NOT NULL,
  `borrow_begindate` timestamp NULL DEFAULT NULL,
  `borrow_enddate` timestamp NULL DEFAULT NULL,
  `borrow_returndate` timestamp NULL DEFAULT NULL,
  `user_id` varchar(50) NOT NULL,
  `borrow_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `borrow`
--

INSERT INTO `borrow` (`borrow_id`, `borrow_begindate`, `borrow_enddate`, `borrow_returndate`, `user_id`, `borrow_status`) VALUES
('B1', '2022-04-09 14:11:36', '2022-04-12 14:11:36', '2022-04-12 14:11:36', '1', 2),
('B2', '2022-04-10 14:11:36', '2022-04-15 14:11:36', '2022-04-15 14:11:36', '1', 2),
('B3', '2022-04-11 14:11:36', '2022-04-15 14:11:36', '2022-04-15 14:11:36', '1', 2);

-- --------------------------------------------------------

--
-- Table structure for table `borrow_detail`
--

CREATE TABLE `borrow_detail` (
  `book_id` varchar(50) NOT NULL,
  `borrow_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `borrow_detail`
--

INSERT INTO `borrow_detail` (`book_id`, `borrow_id`) VALUES
('111', 'B1'),
('222', 'B2'),
('333', 'B3');

-- --------------------------------------------------------

--
-- Table structure for table `tag_read`
--

CREATE TABLE `tag_read` (
  `tag_rfid` varchar(50) NOT NULL,
  `book_id` varchar(50) DEFAULT NULL,
  `tag_time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tag_read`
--

INSERT INTO `tag_read` (`tag_rfid`, `book_id`, `tag_time`) VALUES
('E200001730010136290000DD', '111', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` varchar(50) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_phone` varchar(50) DEFAULT NULL,
  `user_loginname` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `user_phone`, `user_loginname`, `user_password`) VALUES
('1', 'administrator', '01694598489', 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `borrow`
--
ALTER TABLE `borrow`
  ADD PRIMARY KEY (`borrow_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `borrow_detail`
--
ALTER TABLE `borrow_detail`
  ADD PRIMARY KEY (`book_id`,`borrow_id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `borrow_id` (`borrow_id`);

--
-- Indexes for table `tag_read`
--
ALTER TABLE `tag_read`
  ADD PRIMARY KEY (`tag_rfid`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrow`
--
ALTER TABLE `borrow`
  ADD CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `borrow_detail`
--
ALTER TABLE `borrow_detail`
  ADD CONSTRAINT `borrow_detail_ibfk_1` FOREIGN KEY (`borrow_id`) REFERENCES `borrow` (`borrow_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `borrow_detail_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `tag_read`
--
ALTER TABLE `tag_read`
  ADD CONSTRAINT `tag_read_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
