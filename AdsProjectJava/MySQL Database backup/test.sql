-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 08, 2013 at 08:49 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `test_multi_sets`()
    DETERMINISTIC
begin
        select user() as first_col;
        select user() as first_col, now() as second_col;
        select user() as first_col, now() as second_col, now() as third_col;
        end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `logintable`
--

CREATE TABLE IF NOT EXISTS `logintable` (
  `user` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `logintable`
--

INSERT INTO `logintable` (`user`, `password`, `type`) VALUES
('ajaira', 'ajaira', 'emnei'),
('asad', 'asad', 'client'),
('Enam', 'enam', 'student'),
('fahim', 'fahim', 'user'),
('manush', 'manu', 'hum'),
('Mehedi', 'Mehedi', 'General'),
('nafis', 'nafisa', 'employee'),
('rajib', 'rajib', 'Admin'),
('rumman', 'rumman', 'student'),
('safat', 'safat', 'quantum_pro_master'),
('shahriar', 'shahriar', 'makhal'),
('shakil', 'shakil', 'admin'),
('xx', 'xx', 'hudai');

-- --------------------------------------------------------

--
-- Table structure for table `studentinfo`
--

CREATE TABLE IF NOT EXISTS `studentinfo` (
  `RegNum` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Marks` double NOT NULL,
  `Dept` varchar(20) NOT NULL,
  PRIMARY KEY (`RegNum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentinfo`
--

INSERT INTO `studentinfo` (`RegNum`, `Name`, `Marks`, `Dept`) VALUES
(2009331001, 'Syed Shahriar Manjur', 78, 'CSE'),
(2009331002, 'Sakib Khan', 56, 'CSE'),
(2009331003, 'Hbe keu', 63, 'CSE'),
(2009331004, 'Safat Siddiqui', 69, 'CSE'),
(2009331005, 'Asad', 67, 'CSE'),
(2009331006, 'Shakil', 67, 'CSEE'),
(2009331007, 'Experiment', 24, 'CSE'),
(2009331008, 'Rajib The King', 93, 'CSE'),
(2009331012, 'Rashed Safa', 93, 'CSE'),
(2009331020, 'hudai', 10, 'BMB'),
(2009331042, 'Nafis Ahmed', 73, 'CSE'),
(2009331050, 'Sumon', 50, 'CSE'),
(2009331099, 'hudai', 12, 'BANGLA'),
(2010335001, 'Naeem Ahmed', 78, 'ARC'),
(2010335002, 'Ahmed Hasib Bulbul', 86, 'ARC'),
(2011323001, 'Farhan Ahmed', 92, 'GEOGRAPHY');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
