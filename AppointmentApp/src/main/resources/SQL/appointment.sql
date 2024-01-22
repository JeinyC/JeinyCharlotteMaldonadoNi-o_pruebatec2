-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-01-2024 a las 20:16:31
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `appointment`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `appointment`
--

CREATE TABLE `appointment` (
  `ID` int(11) NOT NULL,
  `APPOINTDATE` date DEFAULT NULL,
  `STATUS` tinyint(1) DEFAULT 0,
  `PAPERWORK_ID` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `appointment`
--
/*
INSERT INTO `appointment` (`ID`, `APPOINTDATE`, `STATUS`, `PAPERWORK_ID`, `user_id`) VALUES
(1, '2024-01-22', 1, 1, 2),
(2, '2024-01-22', 0, 3, 3),
(3, '2024-01-22', 0, 3, 4),
(4, '2024-01-22', 1, 2, 2),
(5, '2024-01-22', 0, 3, 5);
*/
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paperwork`
--

CREATE TABLE `paperwork` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `paperwork`
--
/*
INSERT INTO `paperwork` (`ID`, `NAME`) VALUES
(1, 'Project Hidden Cipher'),
(2, 'Oracle Program'),
(3, 'Operation Silence');
*/
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `secretuser`
--

CREATE TABLE `secretuser` (
  `ID` int(11) NOT NULL,
  `AGE` int(11) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `ROL` varchar(255) DEFAULT NULL,
  `SECRETNUMBER` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `secretuser`
--
/*
INSERT INTO `secretuser` (`ID`, `AGE`, `NAME`, `ROL`, `SECRETNUMBER`) VALUES
(1, 0, NULL, 'ADMIN', 321),
(2, 23, 'Jeiny Charlotte', 'USER', 2),
(3, 27, 'Jule', 'USER', 3),
(4, 8, 'Jimmy', 'USER', 4),
(5, 666, 'devil', 'USER', 666);
*/
--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_APPOINTMENT_user_id` (`user_id`),
  ADD KEY `FK_APPOINTMENT_PAPERWORK_ID` (`PAPERWORK_ID`);

--
-- Indices de la tabla `paperwork`
--
ALTER TABLE `paperwork`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `secretuser`
--
ALTER TABLE `secretuser`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `appointment`
--
ALTER TABLE `appointment`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `paperwork`
--
ALTER TABLE `paperwork`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `secretuser`
--
ALTER TABLE `secretuser`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `FK_APPOINTMENT_PAPERWORK_ID` FOREIGN KEY (`PAPERWORK_ID`) REFERENCES `paperwork` (`ID`),
  ADD CONSTRAINT `FK_APPOINTMENT_user_id` FOREIGN KEY (`user_id`) REFERENCES `secretuser` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
