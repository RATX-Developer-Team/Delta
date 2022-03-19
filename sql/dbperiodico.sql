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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- Dumping data for table dbperiodico.articulo: ~10 rows (approximately)
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` (`cod_art`, `cod_usuario`, `cod_categoria`, `cod_subcategoria`, `titular`, `descripcion`, `cuerpoNoticia`, `imagen`, `fecha_publi`, `n_visitas`, `prioridadBase`) VALUES
	(7, 8, 1, 1, 'Radoncic: Estuve deprimido, quería dejar el baloncesto', 'Radoncic, el que fue promesa del Real Madrid, se sincera en una charla para GRR Estaba deprimido, quería dejar el baloncesto.', '23 años, 5 equipos ACB, debut a los 16 en el Real Madrid y la presión de haber crecido bajo la sombra de Luka Doncic. La madurez con la que Dino Radoncic afronta el baloncesto no es fruto de la casualidad si no de una lucha encarnecida contra el que a veces es nuestro peor enemigo, nuestra propia mente. Su trayectoria es prueba del esfuerzo constante que lo ha acompañado desde dejar Serbia con tan solo', '2.jpg', '2022-02-15', 2, 0),
	(8, 5, 1, 1, 'Los Lakers navegan el caos total', 'Un fallo y posterior acierto de Westbrook en los últimos segundos mandaron el partido a la prórroga, donde los Lakers remataron.', 'Las burlas a Russell Westbrook tocaron techo en Minnesota y la respuesta que tenía preparada el base era esperable, pero el resultado ni deja indiferente a mezquinos y comprensibles ni podía estar escrita en el guión mental del jugador. Los Lakers se llevaron un triunfo importante de Toronto en esta gira por varios pabellones y lo hicieron de la forma más Westbrook que Westbrook está conociendo este año, una suerte de Doctor Jekyll y Míster Hyde que asusta hasta al más echado para delante y en la que la cara que sale con más asiduidad es la mala. El base fue el encargado de ejecutar la jugada con la que forzar la prórroga en el ScotiaBank Arena, salió muy mal y no fue ésa sino una posterior e inesperada la que sí les dio esa opción a él y su equipo, que remataron en un buen tiempo extra para ganar por 123-128. Todo lo que resonaba en su cabeza antes de este encuentro era una mezcolanza que así salió en la pista, en un triple-doble (22 puntos, 10 rebotes y 10 asistencias) que a punto estuvo de no servir para nada, pero en esta ocasión la suerte se alió con su trabajo y los Lakers obtienen un preciado botín que les hace afianzar su novena posición de la Conferencia Oeste sin que Blazers, Kings o, sobre todo, Spurs amenacen la misma.¿Consecuencias de esta locura? Que se te quede una cara como la del rapero Drake, activista de los Raptors además de aficionado, en la imagen que ilustra esta crónica. Lo que haga Russell Westbrook, llegados a este punto, casi siempre va a pasar por el filtro de la sorpresa. El mejor de la noche para los angelinos fue LeBron James, que terminó con 35 puntos y un imponente 15/26 en tiros. El titular Wenyen Gabriel sólo erró uno de los suyos para acabar con 17 y Avery Bradley, que metió 14, fue el autor del lanzamiento más importante: el triple, a pase de un Westbrook que leyó perfectamente las ventajas de ese ataque, que sentenció la victoria a falta de medio minuto para aacabar la prórroga. Para los Raptors la pena fue que su hombre más acertado, el novato Scottie Barnes (31 puntos, 17 rebotes y 6 asistencias), fue también al que Westbrook se apareció para hacer fallar los balones decisivos y anular el efecto que estaba produciendo en el conjunto de Los Ángeles. Los de Vogel suman la trigésima de setenta posibles, un rédito escaso dado el palmarés y posibilidades que poseen pero perfectamente asumible en la descontrolada situación que aún navegan.', '1.png', '2022-03-05', 23, 2),
	(9, 5, 1, 1, 'Un nuevo escándalo en los Mavs', 'Nelson denuncia que su despido fue una represalia por denunciar el acoso sexual que su sobrino sufrió por parte de un alto cargo de los Mavs.', 'Vuelven los líos a Dallas Mavericks, en un momento en el que la franquicia pasa por una fase deportiva óptima, la mejor de los últimos años: 43-26, luchando por el cuarto puesto del Oeste y la ventaja de campo en primera ronda de playoffs. Con Luka Doncic en un trance maravilloso, en nivel de pelear por el MVP (con el hándicap de su discreto, para sus estándares, inicio de temporada) y un feliz resultado en apuestas que parecían arriesgadas como la llegada de Jason Kidd al banquillo o de Spencer Dinwiddie al equipo en la operación que sacó del roster a Kristaps Porzingis. Por ahora todo va bien, pero los problemas en los despachos resurgen, un asunto que parece constante en la última etapa del régimen Mark Cuban, el propietario del equipo desde 2000.La situación se había calmado tras el terremoto que siguió al cierre de la pasada temporada. La convulsa salida de Rick Carlisle, un entrenador que se había convertido en una institución en Dallas, y la de otro clásico esencial en la historia de la franquicia: el ejecutivo Donnie Nelson. La llegada de Nico Harrison para tomar el mando en los despachos, los rumores sobre la influencia de Doncic en todos estos movimientos, la extraña labor y salida de Haralabos Voulgaris, colaborador de Nelson que se convirtió en enemigo de Doncic, los roces entre el esloveno y Porzingis… y, por encima de todo, las denuncias y la investigación que llevó a cabo la Liga por una “cultura de acoso sexual y machismo” sostenido durante años a la sombra de Mark Cuban y su mando.De este último asunto colea un nuevo escándalo, destapado por Don Van Natta Jr en ESPN: Donnie Nelson, que salió de los Mavs después de 24 años en los que construyó, sobre todo, el equipo campeón en 2011, ha demandado a la franquicia porque asegura que su despido fue una represalia por informar de que Jason Lutin, jefe de staff y mano derecha de Cuban, acosó y asaltó sexualmente a su sobrino en un hotel de Chicago, durante el All-Star 2020 de la NBA y tras citarlo en su habitación para, en teoría, tratar asuntos laborales. Nelson asegura que Cuban le ofreció 52 millones de dólares para llegar a un acuerdo y firmar una cláusula de confidencialidad sobre ese caso de Lutin en Chicago.Cuban ya ha negado todas las acusaciones de Nelson a través de un email que ha enviado a ESPN: Todo son mentiras. Lo investigamos todo y la única persona que no está a la altura de nuestros estándares fue el señor Nelson. Así que fue despedido. Conocía la investigación y se negó a participar plenamente en ella. Así que insisto: todo lo que dice es mentira. En otro correo electrónico, Lutin niega los hechos de los que Nelson lo acusa y asegura que lo que le está haciendo el exejecutivo de la franquicia es algo para lo que no hay palabras. La NBA, a través de su portavoz, Mike Bass, asegura que estaba al tanto de las acusaciones contra Lutin y de la investigación interna que se estaba realizando. Y, posteriormente, de que se iba a proceder al despido de Nelson.', '3.jpg', '2022-03-02', 4, 3),
	(10, 5, 1, 4, 'Moussa Gholam el King Khalifa prepara su asalto al trono', 'King Khalifa', 'Moussa Gholam (18-0, 11 KO) inicia este domingo, en las Cotxeres de Sants de Barcelona, un vibrante camino hacia la cima. El marroquí, de 26 años y residente en España desde su infancia, ha firmado junto a su promotora, Gallego Prada, un contrato con la prestigiosa MTK. El acuerdo le dará una mayor visibilidad internacional y le permitirá alcanzar pleitos importantes. En el plano nacional, Gallego Prada seguirá dándole actividad y por ello no han querido esperar más.Gholam, uno de los mayores prospectos de la actualidad, se enfrenta al excampeón del mundo Tomás Rojas. El mexicano (52-19-1, 34 KO) se presenta como una nueva prueba en el crecimiento de Moussa, quien intentará dar una buena imagen antes de que esos combates lleguen. Estoy listo para lo que venga, apuntó en el podcast \'KO a la Carrera\'.El evento se completa con otros tres combates profesionales. Destaca la presencia del madrileño Amin Hachimi, quien es aspirante oficial al Nacional del welter. Además, harán su tercer pleito profesional Cristian Eusse y Carlos Alberto Lamela.', '4.jpg', '2022-02-17', 2, 34),
	(11, 5, 1, 4, 'Cristóbal Lorente estrena el año el sábado en Barcelona', 'El pabellón CEM Mundet volverá a vibrar con el boxeo este sábado. Hasta cuatro combates profesionales', 'El pabellón CEM Mundet volverá a vibrar con el boxeo este sábado. Hasta cuatro combates profesionales se darán cita en una nueva velada organizada por BCN Boxing Nights. El combate estelar será protagonizado por el actual campeón de España del pluma, Cristobal Lorente, a la distancia de ocho rounds, frente al púgil afincado en Madrid, Ricardo Martinez. Será el primer combate de 2022 para Lorente, quien marcha invicto como profesional y que ha solventado todos los combates que realizó en 2021 de manera contundente venciendo antes del límite a Dionis Martinez y Juan Jesús Antúnez (por el título nacional) además de su primera aparición en el show de Matchroom Boxing dejando a todos sorprendidos por su calidad frente al complicadisimo Anuar Salas a quien derribó hasta en dos ocasiones. Sin duda, este es el paso previo a combates todavía más importantes.', '5.jpg', '2022-03-02', 2, 2),
	(12, 6, 1, 2, 'El Atlético, atento a un crack', 'Sigue en directo las últimas noticias sobre el mercado de fichajes hoy 19 de marzo de 2022, tras su cierre: altas y bajas sorprendentes, reacciones...', 'La tensión generada entre el Bayern y Lewandowski sobre su renovación ha creado bandos en el entorno del Bayern. Algunos defienden al jugador polaco, piedra angular todavía del proyecto, que le mandó un mensaje de descontento al club en público sobre la falta de progresos en el contrato que termina en 2023. Mientras, se ha posicionado otro bando, de exjugadores del Bayern, quienes piensan que la presión de Lewandowski no está justificada por todo lo que el club le ha dado y dónde está ubicado salarialmente. Basler era muy crítico con el polaco hace unos días e igualmente ha sido Markus Babbel. Se dice que el aprecio no es suficiente para él, ya no puedo escuchar eso, dijo el exjugador del Bayern en Sky. Ningún jugador ha recibido tanto aprecio como Lewandowski en el Bayern en los últimos 20 años. Thomas Müller, por ejemplo, no lo convierte en un circo. Esta queja constante es molesta. Lewandowski gana 25 millones de euros y quiere más. Ningún otro jugador del Bayern gana tanto. Hubo momentos en los que quería irse, la identificación con el club no fue particularmente buena por un tiempo. Sin embargo, el Bayern lo apoyó porque le aprecia mucho, dice Babbel. ', '6.jpg', '2022-03-02', 2, 2),
	(13, 6, 2, 7, 'Verstappen bate por la mínima a los Ferrari en los Libres 2', 'Alonso, quinto', 'Max Verstappen ha destapado una parte del potencial de su Red Bull RB18 y ha liderado la segunda sesión de entrenamientos libres del GP de Baréin F1 2022. Los Ferrari, sin embargo, están ahí, con Charles Leclerc en la segunda plaza y Carlos Sainz en la tercera. Fernando Alonso, por su parte, ha mantenido viva la llama de Alpine con una buena quinta plaza.', 'f1.jpg', '2022-03-02', 1, 2),
	(14, 6, 2, 7, 'Mercedes, sin soluciones en Sakhir', '¿Qué le pasa a Hamilton?', 'l viernes debía de tener algo de celebración para Lewis Hamilton, dado que hoy se cumplen 15 años de su debut en la F1. Desde aquel GP de Australia 2007, nada menos que 289 carreras para el piloto más laureado de la historia, envuelto en este arranque de Mundial en una espiral preocupante. A más de un segundo de Max Verstappen y Charles Leclerc, en la línea de lo mostrado durante los test, el heptacampeón se enfrenta a dificultades a las que Mercedes debe encontrar solución de inmediato.No se trata sólo de este noveno puesto en una sesión libre, en la que terminó por detrás de un Haas y un Alfa Romeo; ni de verse superado, en la tarde y en la noche, por George Russell, su compañero de garaje. Las cuestiones verdaderamente acuciantes para Hamilton van más allá y Toto Wolff, como team principal, precisa medidas urgentes en el W13.', 'f12.jpg', '2022-03-02', 3, 2),
	(15, 6, 2, 7, 'Vettel da positivo en Covid', 'se perderá la primera carrera del Mundial de F1 en Bahréin', 'Sebastian Vettel ha dado positivo por covid-19 y no participará en el GP de Baréin. Será sustituido por Nico Hulkenberg, piloto reserva del equipo Aston Martin Aramco Cognizant Formula One Team, que estará en el coche desde los primeros entrenamientos libres, detallan desde Aston Martin.Hulkenberg formará con el canadiense Lance Stroll la pareja de pilotos de Aston Martin en Bahrein y vuelve a la Fórmula 1 gracias al Covid de nuevo. Su última aparición en el Gran Circo fue en la temporada 2020, cuando el equipo británico todavía era Racing Point.', 'f13.jpg', '2022-03-02', 4, 2),
	(16, 6, 2, 7, 'Llega el Ferrari F1-75', 'el arma de Carlos Sainz y Charles Leclerc para 2022', 'La decoración, aparentemente sobre un vehículo totalmente funcional y no sobre una de las maquetas proporcionadas por Liberty Media, corresponde con la filtrada el pasado miércoles. Como no podía ser de otra manera y sin que sea una sorpresa para nadie, Ferrari repite el color rojo, pero en una des sus vertientes más oscuras. También regresa el color negro, como se podía intuir por la ropa que los miembros del equipo han portado en pasados días, pero quizás menos de lo que inicialmente se pensaba, recordando al F14T, pues apenas tiene ciertos apéndices así como la parte inferior de los pontones de este color. Sin duda Ferrari ha mostrado el diseño más agresivo de los ocho que se han visto hasta la fecha, con especial atención al morro, más en pico que el resto visto hasta ahora, así como una abertura de los pontones que poco tiene que ver con la del resto de monoplazas vistos.', 'f14.jpg', '2022-03-02', 34, 25);
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;

-- Dumping structure for table dbperiodico.categorias
CREATE TABLE IF NOT EXISTS `categorias` (
  `cod_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) NOT NULL,
  PRIMARY KEY (`cod_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table dbperiodico.categorias: ~6 rows (approximately)
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` (`cod_categoria`, `categoria`) VALUES
	(1, 'Deportes'),
	(2, 'Motor'),
	(3, 'Política'),
	(4, 'Economía'),
	(5, 'Cultura'),
	(6, 'Tecnología');
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Dumping data for table dbperiodico.subcategorias: ~8 rows (approximately)
/*!40000 ALTER TABLE `subcategorias` DISABLE KEYS */;
INSERT INTO `subcategorias` (`cod_subcategoria`, `cod_categoria`, `nombre`) VALUES
	(1, 1, 'Baloncesto'),
	(2, 1, 'Futbol'),
	(3, 1, 'Tenis'),
	(4, 1, 'Boxeo'),
	(7, 2, 'Formula 1'),
	(8, 2, 'Formula 2'),
	(9, 2, 'Formula 3'),
	(10, 2, 'Formula E');
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

-- Dumping data for table dbperiodico.usuario: ~3 rows (approximately)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`email`, `cod_usuario`, `fecha_nacimiento`, `pais`, `nombre`, `apellidos`, `password`, `permiso`) VALUES
	('admin@delta.es', 6, '2022-03-09', 'USA', 'Tania', 'Hinton', 'YAgIFasDMz4=', 2),
	('editor@delta.es', 8, '2022-03-10', 'spain', 'Liam', 'Hollis', 'YAgIFasDMz4=', 1),
	('mendozazambranaa@iesmartinrivero.org', 9, '2000-11-07', 'spain', 'Alejandro', 'Mendoza', 'YAgIFasDMz4=', NULL),
	('prueba@gmail.com', 5, '2022-03-09', 'USA', 'Evan', 'Hoover', 'YAgIFasDMz4=', NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
