-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: alumnos1
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumnos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) NOT NULL,
  `carrera` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `promedio` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKrrq96s55g45kywh0ypeed2u1v` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
INSERT INTO `alumnos` VALUES (2,'Gómez','Redes y Telecom','ana.gomez@correo.com','Ana','passAna',8.9),(3,'Romero','Sistemas','irwin@email.com','Irwin','12345',9.5),(15,'Crescensio','Ingeniería en Software','julio.crescencio@correo.com','julio','12345',9),(16,'Cirilo','Ingeniería en Software','javier.cirilo@correo.com','javier','12345',9.2),(22,'Gómez','Redes y Telecom','ana2@correo.com','Ana','12345',8.9),(25,'david','Ingeniería en Sistemas','ikerdavid@correo.com','iker','12345',8.9),(26,'tellex','TI','juan@test.com','Juan','12345',9.5),(30,'bustos','medicina','jazbk@test.com','Jazmin','12345',9.5);
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entregas`
--

DROP TABLE IF EXISTS `entregas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entregas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha_entrega` date NOT NULL,
  `id_alumno_recibe` bigint NOT NULL,
  `id_alumno_reporta` bigint NOT NULL,
  `id_objeto` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbsor5tm6uyw2bu4m1yv9vum8t` (`id_alumno_recibe`),
  KEY `FKd7d5xjwp8slfap6atiamu55jl` (`id_alumno_reporta`),
  KEY `FKjqxm1ayvgvwymm5r7mv94rb59` (`id_objeto`),
  CONSTRAINT `FKbsor5tm6uyw2bu4m1yv9vum8t` FOREIGN KEY (`id_alumno_recibe`) REFERENCES `alumnos` (`id`),
  CONSTRAINT `FKd7d5xjwp8slfap6atiamu55jl` FOREIGN KEY (`id_alumno_reporta`) REFERENCES `alumnos` (`id`),
  CONSTRAINT `FKjqxm1ayvgvwymm5r7mv94rb59` FOREIGN KEY (`id_objeto`) REFERENCES `objetos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entregas`
--

LOCK TABLES `entregas` WRITE;
/*!40000 ALTER TABLE `entregas` DISABLE KEYS */;
INSERT INTO `entregas` VALUES (1,'2026-03-03',30,2,1),(2,'2026-03-12',15,3,1),(3,'2026-03-12',15,3,1),(4,'2026-03-12',2,30,1),(5,'2026-03-12',16,22,1);
/*!40000 ALTER TABLE `entregas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libros` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `autor` varchar(255) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES (1,'Craig Walls','Spring Boot'),(2,'Autor desconocido','Introducción a la programación'),(3,'Stewart','Calculo I'),(4,'Barry Burd','Java for dummies'),(5,'karen j','farma');
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objetos`
--

DROP TABLE IF EXISTS `objetos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `objetos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `fecha` date NOT NULL,
  `id_alumno` bigint NOT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiofrkieva9bskrxmu7uk4itle` (`id_alumno`),
  CONSTRAINT `FKiofrkieva9bskrxmu7uk4itle` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objetos`
--

LOCK TABLES `objetos` WRITE;
/*!40000 ALTER TABLE `objetos` DISABLE KEYS */;
INSERT INTO `objetos` VALUES (1,'Mochila azul','PERDIDO','2026-02-26',30,'mochila_azul.jpg'),(3,'casco','ENCONTRADO','2026-03-02',22,NULL),(4,'laptop','PERDIDO','2026-03-12',30,NULL),(5,'pluma roja','PERDIDO','2026-03-19',22,NULL),(6,'telefono manuel','PERDIDO','2026-03-19',30,NULL),(7,'lentes rojos','PERDIDO','2026-03-19',22,NULL),(8,'maus nazul','ENCONTRADO','2026-03-19',16,NULL),(9,'gorra negra r','ENCONTRADO','2026-03-19',22,NULL),(10,'Mochila verde','PERDIDO','2026-03-30',30,NULL),(11,'pluma roja','PERDIDO','2026-03-30',30,NULL),(12,'sueter negro','PERDIDO','2026-03-30',22,NULL);
/*!40000 ALTER TABLE `objetos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha_devolucion` date NOT NULL,
  `fecha_prestamo` date NOT NULL,
  `id_alumno` bigint NOT NULL,
  `id_libro` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjj0evwss3afettd2bwv94exfw` (`id_alumno`),
  KEY `FKsifvu6veoseewjhlmnmva95oh` (`id_libro`),
  CONSTRAINT `FKjj0evwss3afettd2bwv94exfw` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`id`),
  CONSTRAINT `FKsifvu6veoseewjhlmnmva95oh` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (1,'2026-03-04','2026-02-25',3,1),(2,'2026-03-04','2026-02-25',25,3),(3,'2026-03-04','2026-02-25',26,4),(4,'2026-03-05','2026-02-26',30,5),(5,'2026-03-05','2026-02-26',30,5);
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-30 23:52:00
