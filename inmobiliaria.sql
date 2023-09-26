-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: inmobiliaria
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `imagenes`
--

DROP TABLE IF EXISTS `imagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imagenes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `inmueble` bigint DEFAULT NULL,
  `activo` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FKnd92et7oxqhcsxq2hw1kkxia0` (`inmueble`),
  CONSTRAINT `FKnd92et7oxqhcsxq2hw1kkxia0` FOREIGN KEY (`inmueble`) REFERENCES `inmuebles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenes`
--

LOCK TABLES `imagenes` WRITE;
/*!40000 ALTER TABLE `imagenes` DISABLE KEYS */;
INSERT INTO `imagenes` VALUES (5,'1689749681754.jpg',3,1),(6,'1689756466753.jpg',3,1),(7,'1689756991697.jpg',3,1),(8,'1689757016227.jpg',1,1),(9,'1689757025189.jpg',1,1),(10,'1689757033959.jpg',1,1),(11,'1689757172706.jpg',2,1),(12,'1689757181172.jpg',2,1),(13,'1689757186319.jpg',2,1),(14,'1689940623346.jpg',2,NULL),(37,'1693996877416.jpg',7,NULL),(38,'1694513782872.jpg',7,1),(39,'1694514229823.jpg',7,1),(40,'1694514229830.jpg',7,1);
/*!40000 ALTER TABLE `imagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inmuebles`
--

DROP TABLE IF EXISTS `inmuebles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inmuebles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` int DEFAULT '1',
  `amueblado` int DEFAULT NULL,
  `apertura` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `ascensor` int DEFAULT NULL,
  `cp` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `descripcion` varchar(3500) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `jardin` int DEFAULT NULL,
  `numero_balcones` int DEFAULT NULL,
  `numero_banhos` int DEFAULT NULL,
  `numero_habitaciones` int DEFAULT NULL,
  `nombre_via` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `numero` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `orientacion` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `piscina` int DEFAULT NULL,
  `planta` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `plazas_garaje` int DEFAULT NULL,
  `portada` int DEFAULT NULL,
  `precio` int DEFAULT NULL,
  `puerta` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `superficie_construida` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `superficie_util` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `tendedero` int DEFAULT NULL,
  `tipo_calefaccion` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `titular` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `trastero` int DEFAULT NULL,
  `via` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `poblacion` bigint DEFAULT NULL,
  `tipo` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbsltt65sdkybknkr8nb7qn43s` (`poblacion`),
  KEY `FKcs1i8omrwkr2pq3155pxn6qdp` (`tipo`),
  CONSTRAINT `FKbsltt65sdkybknkr8nb7qn43s` FOREIGN KEY (`poblacion`) REFERENCES `poblaciones` (`id`),
  CONSTRAINT `FKcs1i8omrwkr2pq3155pxn6qdp` FOREIGN KEY (`tipo`) REFERENCES `tipos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inmuebles`
--

LOCK TABLES `inmuebles` WRITE;
/*!40000 ALTER TABLE `inmuebles` DISABLE KEYS */;
INSERT INTO `inmuebles` VALUES (1,1,1,'INTERIOR',1,'48001','Piso muy bonito',0,1,2,3,'Salsidu','34','NORTE',0,'2',0,0,200000,'A','72','65',1,'GAS NATURAL','Maravilloso piso en Algorta',0,'Avenida',1,6),(2,1,0,'INTERIOR',0,'48991','Piso en Algorta',0,0,1,4,'Komporte','16','SUR',0,'3',0,1,34567,'B','65','61',1,'ELÉCTRICA','Chollo en Algorta!!!',0,'Calle',1,1),(3,1,1,'EXTERIOR',0,'48991','Chalet estupendo',1,2,3,4,'Mayor','5','ESTE',1,'3',3,1,345345,'C','234','213',1,'GAS NATURAL','Oportunidad!!!',1,'Calle',1,3),(4,1,1,'INTERIOR',1,'48991','Lonja Fantastica',0,0,1,0,'Iturgitxi','45','SUR',0,'0',2,1,23123,'SN','50','46',0,'GAS NATURAL','Gran oportunidad Inversores!!!',0,'Calle',1,2),(7,1,1,'EXTERIOR',1,'48090','VIVIENDA EN EL CENTRO DE ALGORTA CON VISTAS ESPECTACULARES. SE DISTRIBUYE EN HALL, SALÓN POR EL QUE SE ACCEDE A LA TERRAZA CON LAS IMPRESIONANTES VISTAS A TODO EL ABRA, 4 DORMITORIOS, DOS DE ELLOS CON TERRAZA, 2 CUARTOS DE BAÑO COMPLETOS Y COCINA.',0,2,2,4,'PRINCIPAL ALGORTA','4','norte',0,'8',2,1,500000,'A','130','120',1,'central gas','ESPECTACULAR PISO EN ALGORTA',1,'calle',1,1);
/*!40000 ALTER TABLE `inmuebles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poblaciones`
--

DROP TABLE IF EXISTS `poblaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `poblaciones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `provincia` bigint DEFAULT NULL,
  `activo` int DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FKed2mfrtnivr1wcnmq335c4ldf` (`provincia`),
  CONSTRAINT `FKed2mfrtnivr1wcnmq335c4ldf` FOREIGN KEY (`provincia`) REFERENCES `provincias` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poblaciones`
--

LOCK TABLES `poblaciones` WRITE;
/*!40000 ALTER TABLE `poblaciones` DISABLE KEYS */;
INSERT INTO `poblaciones` VALUES (1,'ALGORTA',1,0),(2,'MUNGIA',1,1),(3,'SOPELANA',1,1),(4,'BARAKALDO',1,1),(5,'BERANGO',1,1),(6,'ARTZENIEGA',2,1);
/*!40000 ALTER TABLE `poblaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `activo` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincias`
--

LOCK TABLES `provincias` WRITE;
/*!40000 ALTER TABLE `provincias` DISABLE KEYS */;
INSERT INTO `provincias` VALUES (1,'BIZKAIA',1),(2,'ARABA',1),(3,'CÁCERES',1),(4,'BARCELONA',1);
/*!40000 ALTER TABLE `provincias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos`
--

DROP TABLE IF EXISTS `tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `activo` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos`
--

LOCK TABLES `tipos` WRITE;
/*!40000 ALTER TABLE `tipos` DISABLE KEYS */;
INSERT INTO `tipos` VALUES (1,'PISO',1),(2,'LONJA',0),(3,'CHALET INDIVIDUAL',1),(4,'CHALET ADOSADO',1),(5,'ATICO ',1),(6,'APARTAMENTO',1),(7,'CASERÍO',1),(8,'SÓTANO',0),(9,'CABAÑA RURAL',0),(10,'PLAZA DE GARAJE',1);
/*!40000 ALTER TABLE `tipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` int DEFAULT '1',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `rol` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,1,'joselu@gmail.com','1234','joselu','ROLE_USER'),(2,1,'admin@gmail.com','$2a$12$385kJgx2.omQFSQxg4oOuucICN4ThZWEGPT2A61yHJbvCqNJdieeO','admin','ROLE_ADMIN'),(3,1,'pepito@gmail.com','$2a$10$69hZFx.EJ6nnT5XaVfzty.cE.zNOub1T4ax1QaTtXpq1WH7y10N3W','pepito','ROLE_USER');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-14 13:56:06
