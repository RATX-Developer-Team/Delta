-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.17-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for dbperiodico
CREATE DATABASE IF NOT EXISTS `dbperiodico` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dbperiodico`;

-- Dumping structure for table dbperiodico.articulo
CREATE TABLE IF NOT EXISTS `articulo` (
  `cod_art` int(11) NOT NULL AUTO_INCREMENT,
  `cod_usuario` int(11) NOT NULL,
  `cod_categoria` int(11) NOT NULL,
  `cod_subcategoria` int(11) NOT NULL,
  `titular` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `cuerpoNoticia` longtext NOT NULL DEFAULT '',
  `imagen` varchar(255) NOT NULL,
  `fecha_publi` date NOT NULL,
  `n_visitas` int(11) DEFAULT NULL,
  `prioridadBase` int(11) NOT NULL,
  PRIMARY KEY (`cod_art`),
  KEY `cod_usuario` (`cod_usuario`),
  KEY `cod_categoria` (`cod_categoria`),
  KEY `cod_subcategoria` (`cod_subcategoria`),
  CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`cod_categoria`) REFERENCES `categorias` (`cod_categoria`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `articulo_ibfk_2` FOREIGN KEY (`cod_usuario`) REFERENCES `usuario` (`cod_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `articulo_ibfk_3` FOREIGN KEY (`cod_subcategoria`) REFERENCES `subcategorias` (`cod_subcategoria`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- Dumping data for table dbperiodico.articulo: ~10 rows (approximately)
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` (`cod_art`, `cod_usuario`, `cod_categoria`, `cod_subcategoria`, `titular`, `descripcion`, `cuerpoNoticia`, `imagen`, `fecha_publi`, `n_visitas`, `prioridadBase`) VALUES
	(7, 8, 1, 1, '2', 'Descripcion corta', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus mi euismod, porta urna ut, tempus lectus. Donec nunc diam, viverra in varius a, ornare aliquam neque. Quisque malesuada porttitor congue. Integer maximus scelerisque pulvinar. Vestibulum at euismod diam. Maecenas malesuada pharetra ipsum et aliquet. Morbi vel lectus erat. Phasellus tempor consectetur neque, eget tempor nunc vestibulum lacinia. Phasellus imperdiet nibh ut ipsum pellentesque, quis porta est interdum. Nam consectetur lacinia dolor at faucibus.', 'foto.png', '2022-02-15', 0, 0),
	(8, 5, 1, 1, '1', 'Descripcion corta', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus mi euismod, porta urna ut, tempus lectus. Donec nunc diam, viverra in varius a, ornare aliquam neque. Quisque malesuada porttitor congue. Integer maximus scelerisque pulvinar. Vestibulum at euismod diam. Maecenas malesuada pharetra ipsum et aliquet. Morbi vel lectus erat. Phasellus tempor consectetur neque, eget tempor nunc vestibulum lacinia. Phasellus imperdiet nibh ut ipsum pellentesque, quis porta est interdum. Nam consectetur lacinia dolor at faucibus.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus mi euismod, porta urna ut, tempus lectus. Donec nunc diam, viverra in varius a, ornare aliquam neque. Quisque malesuada porttitor congue. Integer maximus scelerisque pulvinar. Vestibulum at euismod diam. Maecenas malesuada pharetra ipsum et aliquet. Morbi vel lectus erat. Phasellus tempor consectetur neque, eget tempor nunc vestibulum lacinia. Phasellus imperdiet nibh ut ipsum pellentesque, quis porta est interdum. Nam consectetur lacinia dolor at faucibus.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus mi euismod, porta urna ut, tempus lectus. Donec nunc diam, viverra in varius a, ornare aliquam neque. Quisque malesuada porttitor congue. Integer maximus scelerisque pulvinar. Vestibulum at euismod diam. Maecenas malesuada pharetra ipsum et aliquet. Morbi vel lectus erat. Phasellus tempor consectetur neque, eget tempor nunc vestibulum lacinia. Phasellus imperdiet nibh ut ipsum pellentesque, quis porta est interdum. Nam consectetur lacinia dolor at faucibus.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus mi euismod, porta urna ut, tempus lectus. Donec nunc diam, viverra in varius a, ornare aliquam neque. Quisque malesuada porttitor congue. Integer maximus scelerisque pulvinar. Vestibulum at euismod diam. Maecenas malesuada pharetra ipsum et aliquet. Morbi vel lectus erat. Phasellus tempor consectetur neque, eget tempor nunc vestibulum lacinia. Phasellus imperdiet nibh ut ipsum pellentesque, quis porta est interdum. Nam consectetur lacinia dolor at faucibus.', 'foto.png', '2022-03-02', 5, 2),
	(9, 5, 1, 1, '3', 'Descripcion corta', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus mi euismod, porta urna ut, tempus lectus. Donec nunc diam, viverra in varius a, ornare aliquam neque. Quisque malesuada porttitor congue. Integer maximus scelerisque pulvinar. Vestibulum at euismod diam. Maecenas malesuada pharetra ipsum et aliquet. Morbi vel lectus erat. Phasellus tempor consectetur neque, eget tempor nunc vestibulum lacinia. Phasellus imperdiet nibh ut ipsum pellentesque, quis porta est interdum. Nam consectetur lacinia dolor at faucibus.', 'foto.png', '2022-03-02', 4, 3),
	(10, 5, 1, 1, '4', 'Descripcion corta', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus mi euismod, porta urna ut, tempus lectus. Donec nunc diam, viverra in varius a, ornare aliquam neque. Quisque malesuada porttitor congue. Integer maximus scelerisque pulvinar. Vestibulum at euismod diam. Maecenas malesuada pharetra ipsum et aliquet. Morbi vel lectus erat. Phasellus tempor consectetur neque, eget tempor nunc vestibulum lacinia. Phasellus imperdiet nibh ut ipsum pellentesque, quis porta est interdum. Nam consectetur lacinia dolor at faucibus.', 'foto.png', '2022-02-17', 2, 34),
	(11, 5, 1, 1, '5', 'Descripcion corta', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus mi euismod, porta urna ut, tempus lectus. Donec nunc diam, viverra in varius a, ornare aliquam neque. Quisque malesuada porttitor congue. Integer maximus scelerisque pulvinar. Vestibulum at euismod diam. Maecenas malesuada pharetra ipsum et aliquet. Morbi vel lectus erat. Phasellus tempor consectetur neque, eget tempor nunc vestibulum lacinia. Phasellus imperdiet nibh ut ipsum pellentesque, quis porta est interdum. Nam consectetur lacinia dolor at faucibus.', 'foto.png', '2022-03-02', 1, 2),
	(12, 6, 1, 1, '6', 'Descripcion corta', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus mi euismod, porta urna ut, tempus lectus. Donec nunc diam, viverra in varius a, ornare aliquam neque. Quisque malesuada porttitor congue. Integer maximus scelerisque pulvinar. Vestibulum at euismod diam. Maecenas malesuada pharetra ipsum et aliquet. Morbi vel lectus erat. Phasellus tempor consectetur neque, eget tempor nunc vestibulum lacinia. Phasellus imperdiet nibh ut ipsum pellentesque, quis porta est interdum. Nam consectetur lacinia dolor at faucibus.', 'foto.png', '2022-03-02', 1, 2),
	(13, 6, 1, 1, '7', 'Descripcion corta', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus mi euismod, porta urna ut, tempus lectus. Donec nunc diam, viverra in varius a, ornare aliquam neque. Quisque malesuada porttitor congue. Integer maximus scelerisque pulvinar. Vestibulum at euismod diam. Maecenas malesuada pharetra ipsum et aliquet. Morbi vel lectus erat. Phasellus tempor consectetur neque, eget tempor nunc vestibulum lacinia. Phasellus imperdiet nibh ut ipsum pellentesque, quis porta est interdum. Nam consectetur lacinia dolor at faucibus.', 'foto.png', '2022-03-02', 1, 2),
	(14, 6, 1, 1, '8', 'Descripcion corta', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus mi euismod, porta urna ut, tempus lectus. Donec nunc diam, viverra in varius a, ornare aliquam neque. Quisque malesuada porttitor congue. Integer maximus scelerisque pulvinar. Vestibulum at euismod diam. Maecenas malesuada pharetra ipsum et aliquet. Morbi vel lectus erat. Phasellus tempor consectetur neque, eget tempor nunc vestibulum lacinia. Phasellus imperdiet nibh ut ipsum pellentesque, quis porta est interdum. Nam consectetur lacinia dolor at faucibus.', 'foto.png', '2022-03-02', 1, 2),
	(15, 6, 1, 1, '9', 'Descripcion corta', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus mi euismod, porta urna ut, tempus lectus. Donec nunc diam, viverra in varius a, ornare aliquam neque. Quisque malesuada porttitor congue. Integer maximus scelerisque pulvinar. Vestibulum at euismod diam. Maecenas malesuada pharetra ipsum et aliquet. Morbi vel lectus erat. Phasellus tempor consectetur neque, eget tempor nunc vestibulum lacinia. Phasellus imperdiet nibh ut ipsum pellentesque, quis porta est interdum. Nam consectetur lacinia dolor at faucibus.', 'foto.png', '2022-03-02', 1, 2),
	(16, 6, 1, 1, '10', 'Descripcion corta', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce cursus mi euismod, porta urna ut, tempus lectus. Donec nunc diam, viverra in varius a, ornare aliquam neque. Quisque malesuada porttitor congue. Integer maximus scelerisque pulvinar. Vestibulum at euismod diam. Maecenas malesuada pharetra ipsum et aliquet. Morbi vel lectus erat. Phasellus tempor consectetur neque, eget tempor nunc vestibulum lacinia. Phasellus imperdiet nibh ut ipsum pellentesque, quis porta est interdum. Nam consectetur lacinia dolor at faucibus.', 'foto.png', '2022-03-02', 1, 2);
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;

-- Dumping structure for table dbperiodico.categorias
CREATE TABLE IF NOT EXISTS `categorias` (
  `cod_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) NOT NULL,
  PRIMARY KEY (`cod_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table dbperiodico.categorias: ~6 rows (approximately)
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` (`cod_categoria`, `categoria`) VALUES
	(1, 'Deportes'),
	(2, 'Politica'),
	(3, 'Motor'),
	(4, 'Economia'),
	(5, 'Cultura'),
	(6, 'Tecnologia');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;

-- Dumping structure for table dbperiodico.newletter
CREATE TABLE IF NOT EXISTS `newletter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table dbperiodico.newletter: ~0 rows (approximately)
/*!40000 ALTER TABLE `newletter` DISABLE KEYS */;
INSERT INTO `newletter` (`id`, `email`) VALUES
	(1, 'ren0xi@aol.com');
/*!40000 ALTER TABLE `newletter` ENABLE KEYS */;

-- Dumping structure for table dbperiodico.opinion
CREATE TABLE IF NOT EXISTS `opinion` (
  `cod_opinion` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `cod_art` int(11) NOT NULL,
  `hora` time NOT NULL,
  `fecha_publi` date NOT NULL,
  `contenido` varchar(255) NOT NULL,
  PRIMARY KEY (`cod_opinion`),
  KEY `email` (`email`),
  KEY `cod_art` (`cod_art`),
  CONSTRAINT `opinion_ibfk_1` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `opinion_ibfk_2` FOREIGN KEY (`cod_art`) REFERENCES `articulo` (`cod_art`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table dbperiodico.opinion: ~0 rows (approximately)
/*!40000 ALTER TABLE `opinion` DISABLE KEYS */;
/*!40000 ALTER TABLE `opinion` ENABLE KEYS */;

-- Dumping structure for table dbperiodico.respuestas
CREATE TABLE IF NOT EXISTS `respuestas` (
  `cod_respuesta` int(11) NOT NULL AUTO_INCREMENT,
  `cod_opinion` int(11) NOT NULL DEFAULT 0,
  `cod_engancha` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`cod_respuesta`),
  KEY `cod_engancha` (`cod_engancha`),
  KEY `cod_opinion` (`cod_opinion`),
  CONSTRAINT `respuestas_ibfk_1` FOREIGN KEY (`cod_opinion`) REFERENCES `opinion` (`cod_opinion`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `respuestas_ibfk_2` FOREIGN KEY (`cod_engancha`) REFERENCES `opinion` (`cod_opinion`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table dbperiodico.respuestas: ~0 rows (approximately)
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
/*!40000 ALTER TABLE `respuestas` ENABLE KEYS */;

-- Dumping structure for table dbperiodico.subcategorias
CREATE TABLE IF NOT EXISTS `subcategorias` (
  `cod_subcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `cod_categoria` int(11) NOT NULL DEFAULT 0,
  `nombre` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`cod_subcategoria`),
  KEY `cod_categoria` (`cod_categoria`),
  CONSTRAINT `subcategorias_ibfk_1` FOREIGN KEY (`cod_categoria`) REFERENCES `categorias` (`cod_categoria`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table dbperiodico.subcategorias: ~4 rows (approximately)
/*!40000 ALTER TABLE `subcategorias` DISABLE KEYS */;
INSERT INTO `subcategorias` (`cod_subcategoria`, `cod_categoria`, `nombre`) VALUES
	(1, 1, 'Baloncesto'),
	(2, 1, 'Futbol'),
	(3, 1, 'Futbol2'),
	(4, 1, 'Futbol3');
/*!40000 ALTER TABLE `subcategorias` ENABLE KEYS */;

-- Dumping structure for table dbperiodico.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `email` varchar(255) NOT NULL DEFAULT 'admin@admin.es',
  `cod_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_nacimiento` date NOT NULL,
  `pais` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `permiso` int(11) DEFAULT 0,
  PRIMARY KEY (`email`),
  UNIQUE KEY `cod_usuario` (`cod_usuario`),
  KEY `cod_usuario_2` (`cod_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Dumping data for table dbperiodico.usuario: ~7 rows (approximately)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`email`, `cod_usuario`, `fecha_nacimiento`, `pais`, `nombre`, `apellidos`, `password`, `permiso`) VALUES
	('admin@admin.es', 6, '2022-03-09', 'USA', 'Prueba', 'Admin', 'YAgIFasDMz4=', NULL),
	('alexyanamusicpro@gmail.com', 8, '2022-03-10', 'España', 'Lucia', 'prueba@gmail.com', 'YAgIFasDMz4=', NULL),
	('contactoladronazi@gmail.com', 11, '2002-06-27', 'España', 'Mario', 'Ionut', '06eYqCyK/nI=', NULL),
	('prueba@gmail.com', 5, '2022-03-09', 'USA', 'Prueba', 'Admin', 'YAgIFasDMz4=', NULL),
	('ren0xi@aol.com', 2, '2022-03-09', 'España', 'Alfonso', 'Adfz', 'DGcCKPq+8Aw=', NULL),
	('rordonezlaino@gmail.com', 10, '2002-06-27', 'España', 'Mario', 'Ionut', 'DGcCKPq+8Aw=', NULL),
	('skydrex14@gmail.com', 9, '2002-06-27', 'España', 'Mario', 'Ionut', 'DGcCKPq+8Aw=', NULL),
	('xocon25787@toudrum.com', 7, '2022-03-16', 'España', 'Lucia', 'prueba@gmail.com', 'YAgIFasDMz4=', NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
