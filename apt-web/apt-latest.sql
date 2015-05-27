-- MySQL dump 10.13  Distrib 5.5.41, for Linux (x86_64)
--
-- Host: localhost    Database: apt
-- ------------------------------------------------------
-- Server version	5.5.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL,
  `description` varchar(50) NOT NULL,
  `no_of_question` int(11) NOT NULL,
  `duration_minute` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` VALUES (1,'APT-001','Aptitude Test Question set 1',10,30);
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(2000) NOT NULL,
  `choice_a` varchar(400) DEFAULT NULL,
  `choice_b` varchar(400) DEFAULT NULL,
  `choice_c` varchar(400) DEFAULT NULL,
  `choice_d` varchar(400) DEFAULT NULL,
  `choice_e` varchar(400) DEFAULT NULL,
  `answer` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'what is for id #1?','4','8','6','12','0','CHOICE_C'),(2,'what is for id #2?','4','8','6','12','0','CHOICE_C'),(3,'what is for id #3?','4','8','6','12','0','CHOICE_C'),(4,'what is for id #4?','4','8','6','12','0','CHOICE_C'),(5,'what is for id #5?','4','8','6','12','0','CHOICE_C'),(6,'what is for id #6?','4','8','6','12','0','CHOICE_C'),(7,'what is for id #7?','4','8','6','12','0','CHOICE_C'),(8,'what is for id #8?','4','8','6','12','0','CHOICE_C'),(9,'what is for id #9?','4','8','6','12','0','CHOICE_C'),(10,'what is for id #10?','4','8','6','12','0','CHOICE_C'),(11,'what is for id #11?','4','8','6','12','0','CHOICE_C'),(12,'what is for id #12?','4','8','6','12','0','CHOICE_C'),(13,'what is for id #13?','4','8','6','12','0','CHOICE_C'),(14,'what is for id #14?','4','8','6','12','0','CHOICE_C'),(15,'what is for id #15?','4','8','6','12','0','CHOICE_C'),(16,'what is for id #16?','4','8','6','12','0','CHOICE_C'),(17,'what is for id #17?','4','8','6','12','0','CHOICE_C'),(18,'what is for id #18?','4','8','6','12','0','CHOICE_C'),(19,'what is for id #19?','4','8','6','12','0','CHOICE_C'),(20,'what is for id #20?','4','8','6','12','0','CHOICE_C'),(21,'what is for id #21?','4','8','6','12','0','CHOICE_C'),(22,'what is for id #22?','4','8','6','12','0','CHOICE_C'),(23,'what is for id #23?','4','8','6','12','0','CHOICE_C'),(24,'what is for id #24?','4','8','6','12','0','CHOICE_C'),(25,'what is for id #25?','4','8','6','12','0','CHOICE_C'),(26,'what is for id #26?','4','8','6','12','0','CHOICE_C'),(27,'what is for id #27?','4','8','6','12','0','CHOICE_C'),(28,'what is for id #28?','4','8','6','12','0','CHOICE_C'),(29,'what is for id #29?','4','8','6','12','0','CHOICE_C');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sample`
--

DROP TABLE IF EXISTS `sample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sample` (
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample`
--

LOCK TABLES `sample` WRITE;
/*!40000 ALTER TABLE `sample` DISABLE KEYS */;
/*!40000 ALTER TABLE `sample` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_answer`
--

DROP TABLE IF EXISTS `test_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `answer` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `question_id_tad_fk` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `user_id_tad_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_answer`
--

LOCK TABLES `test_answer` WRITE;
/*!40000 ALTER TABLE `test_answer` DISABLE KEYS */;
INSERT INTO `test_answer` VALUES (1,15,18,'CHOICE_A'),(2,15,5,'CHOICE_E'),(3,15,7,'CHOICE_D'),(4,15,26,'CHOICE_C'),(5,15,27,'CHOICE_B'),(6,15,13,'CHOICE_C'),(7,15,4,'CHOICE_D'),(8,15,8,'CHOICE_E'),(9,15,21,'CHOICE_A'),(10,15,28,'CHOICE_E'),(11,15,11,'CHOICE_E'),(12,15,18,'CHOICE_E'),(13,15,5,'CHOICE_D'),(14,15,7,'CHOICE_D'),(15,15,26,'CHOICE_D'),(16,15,27,'CHOICE_D'),(17,15,13,NULL),(18,15,4,NULL),(19,15,8,'CHOICE_C'),(20,15,21,'CHOICE_C'),(21,15,28,NULL),(22,15,11,NULL),(23,15,18,'CHOICE_E'),(24,15,5,NULL),(25,15,7,NULL),(26,15,26,NULL),(27,15,27,NULL),(28,15,13,NULL),(29,15,4,'CHOICE_E'),(30,15,8,'CHOICE_E'),(31,15,21,'CHOICE_E'),(32,15,28,'CHOICE_D'),(33,15,11,NULL);
/*!40000 ALTER TABLE `test_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) NOT NULL,
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(4) DEFAULT '1',
  `name` varchar(25) DEFAULT NULL,
  `roll_number` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mobile_number` varchar(10) DEFAULT NULL,
  `login_active` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_2` (`email`),
  UNIQUE KEY `phone_number` (`mobile_number`),
  KEY `username` (`username`),
  KEY `roll_number` (`roll_number`),
  KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (15,'100','$2a$10$85HJOwobRkCMpAwJnNXu7uUgs9zdbI4BI2h/WJtirpfvPLb2vIkW6',1,'user1','100','user1@mail.com','123',1),(16,'101','$2a$10$WgIO.LypgUYH73l3GuFQ/OI.9hUolq1GXpmLAAne5lLP15X1ZVFX2',1,'sri','101','sri@mail.com','9899123241',0),(17,'admin','$2a$10$37egHkHzqKzMUr0DJaIHkuzVO0wNY0AiXBebEJxtwvmTm6mSiG1UO',1,'sriram','admin','admin@mail.com','9812345678',1),(18,'102','$2a$10$6ViWfc4dtGz8SKWyBpfdP.j.3.YcYht01I2SpHh9a1Rj8GpO/St5W',1,'test','102','test@mail.com','9845215222',0),(19,'1000','$2a$10$m/jFDyHNbmlhjkbN6Mu20ejVYDGp0KUUZSXAvJ8d88OmmysXpYAUq',1,'Prabhu','1000','prabhu@mail.com','9894215452',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `ROLE` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`ROLE`,`username`),
  KEY `fk_username_idx` (`username`),
  CONSTRAINT `username_ur_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (12,'admin','ROLE_ADMIN'),(9,'100','ROLE_USER'),(14,'1000','ROLE_USER'),(10,'101','ROLE_USER'),(13,'102','ROLE_USER'),(11,'admin','ROLE_USER');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-27 11:59:09
