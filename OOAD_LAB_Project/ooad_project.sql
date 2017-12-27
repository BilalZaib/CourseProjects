-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2016 at 07:17 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ooad_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `e_id` int(5) NOT NULL,
  `e_name` varchar(30) NOT NULL,
  `e_dob` varchar(12) NOT NULL,
  `e_contact` varchar(15) NOT NULL,
  `e_address` varchar(40) NOT NULL,
  `e_job` varchar(30) NOT NULL,
  `e_pay` int(10) NOT NULL,
  `h_id` int(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`e_id`, `e_name`, `e_dob`, `e_contact`, `e_address`, `e_job`, `e_pay`, `h_id`) VALUES
(1, 'Momin Khan', '12/12/1990', '03023455598', 'Peshawar', 'Cook', 12000, 1),
(2, 'Sarfaraz Ahmad', '4/5/1990', '03012133467', 'Charsadda KPK', 'Cook', 12000, 1),
(3, 'Ismail Meo', '14/3/1994', '03332144497', 'Lahore ', 'Cook', 15000, 2),
(4, 'Shafqat Hussain', '2/4/1990', '03023465987', 'Lahore', 'Cook', 25000, 1),
(5, 'Fahad Khan', '15/12/1993', '03213456777', 'Peshawar', 'Sweeper', 12000, 1),
(6, 'Faizan Ahmed', '8/9/1994', '03332233445', 'Rawalpendi', 'Warden', 30000, 1),
(7, 'Kashif Khan', '22/12/1990', '03331210112', 'Faislabad', 'Warden', 40000, 2);

-- --------------------------------------------------------

--
-- Table structure for table `hostel`
--

CREATE TABLE `hostel` (
  `h_id` int(5) NOT NULL,
  `h_name` varchar(30) NOT NULL,
  `T_student` int(5) NOT NULL,
  `T_employee` int(5) NOT NULL,
  `T_rooms` int(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hostel`
--

INSERT INTO `hostel` (`h_id`, `h_name`, `T_student`, `T_employee`, `T_rooms`) VALUES
(1, 'Zia Hostel', 6, 5, 4),
(2, 'National Hostel', 2, 2, 4),
(3, 'Bilal Hostel', 2, 0, 3),
(4, 'Zikriya Hostel', 0, 0, 3),
(5, 'Khattak Hostel', 0, 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `log_in`
--

CREATE TABLE `log_in` (
  `user` varchar(30) NOT NULL,
  `pswd` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log_in`
--

INSERT INTO `log_in` (`user`, `pswd`) VALUES
('Bilal', '123');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `r_id` int(5) NOT NULL,
  `r_capacity` int(2) NOT NULL,
  `h_id` int(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`r_id`, `r_capacity`, `h_id`) VALUES
(1, 2, 1),
(2, 3, 1),
(3, 2, 1),
(4, 2, 1),
(5, 3, 2),
(6, 3, 2),
(7, 2, 2),
(8, 3, 2),
(9, 3, 3),
(10, 2, 3),
(11, 2, 3),
(12, 3, 4),
(13, 2, 4),
(14, 3, 4),
(15, 4, 5),
(16, 4, 5);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `s_id` int(5) NOT NULL,
  `s_name` varchar(30) NOT NULL,
  `s_address` varchar(40) NOT NULL,
  `s_contact` varchar(15) NOT NULL,
  `s_dob` varchar(30) NOT NULL,
  `s_pay` varchar(5) NOT NULL,
  `h_id` int(5) NOT NULL,
  `r_id` int(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`s_id`, `s_name`, `s_address`, `s_contact`, `s_dob`, `s_pay`, `h_id`, `r_id`) VALUES
(1, 'Bilal Zaib', 'Peshawar', '03022144497', '2016-12-05', 'YES', 1, 1),
(2, 'Fareed Afzal', 'Vihari ', '03022144490', '12/03/2012', 'NO', 3, 9),
(3, 'Saddam Hussain', 'Noshehra', '03022244487', '12/3/1996', 'YES', 1, 1),
(4, 'Fakhar Abbas', 'Muzafrabad', '47682366587', '12/3/1209', 'YES', 1, 2),
(5, 'Anas Awan', 'Faislabad', '0302224569', '23/09/1990', 'YES', 1, 2),
(6, 'Raheel Ahmad', 'Qasoori Lahore', '03322144497', '3/3/1997', 'NO', 1, 2),
(7, 'Sarmad Khan', 'Islamabad', '03321234567', '12/12/1994', 'YES', 2, 6),
(9, 'Rayed Aslam', 'Saudi Arab', '03123435360', '23/10/1996', 'YES', 2, 6),
(8, 'Jibran Baig', 'kj', '03022144465', '12/3/1996', 'YES', 1, 3),
(10, 'Waqas Aslam', 'Shujabad', '03022144455', '12/12/1998', 'NO', 3, 9);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`e_id`);

--
-- Indexes for table `hostel`
--
ALTER TABLE `hostel`
  ADD PRIMARY KEY (`h_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`r_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`s_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
