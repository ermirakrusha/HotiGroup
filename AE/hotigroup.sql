-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 18, 2017 at 10:19 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotigroup`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id_comment` int(11) NOT NULL,
  `textcomment` text,
  `id_products` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id_comment`, `textcomment`, `id_products`, `id_user`) VALUES
(0, 'ahdjaj', 100, 9);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id_products` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `img` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id_products`, `title`, `description`, `img`) VALUES
(100, 'Window', 'Readymade Wooden Windows From DoorsIndia, One Of The Finest Wooden Products Manufacturer In North-East. Wooden Windows Are Made From Solid Hardwood And Teakwood Completely Kiln Seasoned To Provide Protection Against Termites, Borer & Bending. ', 'http://www.doorsindia.com/photo/photo54.jpg'),
(101, 'Door', 'Readymade Moulded Doors From DoorsIndia One Of The Finest Door Manufacturers In North-East. Moulded Doors Made From Solid Hardwood Completely Treated To Provide Protection Against Termites, Borer & Bending With HDF Sheets 3.5mm Pasted On Both Faces. The HDF Sheets Are Exclusively Imported From Germany & Europe. ', 'http://www.doorsindia.com/photo/photo1.jpg'),
(102, 'Window', 'Readymade Wooden Windows From DoorsIndia, One Of The Finest Wooden Products Manufacturer In North-East. Wooden Windows Are Made From Solid Hardwood And Teakwood Completely Kiln Seasoned To Provide Protection Against Termites, Borer & Bending. ', 'http://www.doorsindia.com/photo/photo61.jpg'),
(103, 'Door', 'Readymade Wooden Doors From DoorsIndia, One Of The Finest Door Manufacturers In North-East. Wooden Doors Made From Solid Hardwood And Teakwood Completely Kiln Seasoned To Provide Protection Against Termites, Borer & Bending. \r\n', 'http://www.doorsindia.com/photo/photo34.jpg'),
(104, 'Window', 'Readymade Wooden Windows From DoorsIndia, One Of The Finest Wooden Products Manufacturer In North-East. Wooden Windows Are Made From Solid Hardwood And Teakwood Completely Kiln Seasoned To Provide Protection Against Termites, Borer & Bending. ', 'http://www.doorsindia.com/photo/photo56.jpg'),
(105, 'Window', 'Readymade Wooden Windows From DoorsIndia, One Of The Finest Wooden Products Manufacturer In North-East. Wooden Windows Are Made From Solid Hardwood And Teakwood Completely Kiln Seasoned To Provide Protection Against Termites, Borer & Bending. ', 'http://www.doorsindia.com/photo/photo51.jpg'),
(106, 'Door', 'Readymade Moulded Doors From DoorsIndia One Of The Finest Door Manufacturers In North-East. Moulded Doors Made From Solid Hardwood Completely Treated To Provide Protection Against Termites, Borer & Bending With HDF Sheets 3.5mm Pasted On Both Faces. The HDF Sheets Are Exclusively Imported From Germany & Europe. ', 'http://www.doorsindia.com/photo/photo3.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE `user_info` (
  `id_user` int(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `lastname` varchar(200) NOT NULL,
  `email` varchar(300) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`id_user`, `name`, `lastname`, `email`, `username`, `password`) VALUES
(8, 'Ermira', 'Krusha', 'ermira-pz@hotmail.com', 'ekrusha', '123456'),
(9, '1', '2', '3', '4', '5');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id_comment`),
  ADD KEY `id_products` (`id_products`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id_products`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user_info`
--
ALTER TABLE `user_info`
  MODIFY `id_user` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`id_products`) REFERENCES `products` (`id_products`),
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user_info` (`id_user`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
