-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-09-2016 a las 20:54:56
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `coleccionistas`
--
CREATE DATABASE IF NOT EXISTS `coleccionistas` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `coleccionistas`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `producto_id` int(10) NOT NULL,
  `producto_nombre` varchar(100) COLLATE utf8_bin NOT NULL,
  `producto_descripcion` varchar(600) COLLATE utf8_bin NOT NULL,
  `producto_precio` decimal(9,2) NOT NULL,
  `producto_imagen` varchar(100) COLLATE utf8_bin NOT NULL,
  `producto_categoria` varchar(100) COLLATE utf8_bin NOT NULL,
  `usuario_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`producto_id`, `producto_nombre`, `producto_descripcion`, `producto_precio`, `producto_imagen`, `producto_categoria`, `usuario_id`) VALUES
(2, 'Botella Coca-Cola 60''s', 'Donec id elit non mi porta gravida at eget metus.Fusce dapibus, tellus ac cursus commodo,ortor mauris condimentum nibh,ut fermentum massa justo sit amet risus', '60.00', '/img/botella_coca-cola.jpg', 'otros', 1),
(3, 'Moneda celta 500 años', 'Donec id elit non mi porta gravida at eget metus.Fusce dapibus, tellus ac cursus commodo,ortor mauris condimentum nibh,ut fermentum massa justo sit amet risus', '1000.00', '/img/moneda_500.jpg', 'monedas', 1),
(4, 'Detecctive Comic #27', 'Donec id elit non mi porta gravida at eget metus.Fusce dapibus, tellus ac cursus commodo,ortor mauris condimentum nibh,ut fermentum massa justo sit amet risus', '5000.00', '/img/comic_27.jpg', 'comics', 1),
(5, 'Consola antigua año 90', 'Donec id elit non mi porta gravida at eget metus.Fusce dapibus, tellus ac cursus commodo,ortor mauris condimentum nibh,ut fermentum massa justo sit amet risus', '500.00', '/img/consola_antigua.jpg', 'videojuegos', 2),
(6, 'Figura de accion de superman 70''s', 'Donec id elit non mi porta gravida at eget metus.Fusce dapibus, tellus ac cursus commodo,ortor mauris condimentum nibh,ut fermentum massa justo sit amet risus', '800.00', '/img/figura_superman.jpg', 'figuras de accion', 2),
(7, 'Llave antigua 200 años', 'Donec id elit non mi porta gravida at eget metus.Fusce dapibus, tellus ac cursus commodo,ortor mauris condimentum nibh,ut fermentum massa justo sit amet risus', '100.00', '/img/llave_vieja.jpg', 'otros', 2),
(8, 'carro', 'adggdrtyhgds', '10000.00', '/img/autos.png', 'autos', 2),
(9, 'juego', 'afjurdwy dutrgvvcdb rej. fftj dew', '100.00', '/img/game.png', 'videojuegos', 2),
(10, 'mazda', 'gdsstyjfd', '15000.00', '/img/autos.png', 'autos', 2),
(11, 'comic', 'fshj eufde swwthfd hdfmjgc trkudei\nddjgthhddgcdyhhdd', '2000.00', '/img/comics.png', 'comics', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `usuario_id` int(10) NOT NULL,
  `usuario_nombre` varchar(50) COLLATE utf8_bin NOT NULL,
  `usuario_apellido` varchar(50) COLLATE utf8_bin NOT NULL,
  `usuario_usuario` varchar(50) COLLATE utf8_bin NOT NULL,
  `usuario_contrasena` varchar(50) COLLATE utf8_bin NOT NULL,
  `usuario_correo` varchar(50) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`usuario_id`, `usuario_nombre`, `usuario_apellido`, `usuario_usuario`, `usuario_contrasena`, `usuario_correo`) VALUES
(1, 'fernando', 'ordonez', 'fernando', '1234', 'fernando@correo.com'),
(2, 'cristhian', 'ochoa', 'cristhian', '1234', 'cristhho@gmail.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`producto_id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usuario_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `producto_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `usuario_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
