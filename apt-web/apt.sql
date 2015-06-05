-- MySQL dump 10.13  Distrib 5.5.24, for Win32 (x86)
--
-- Host: localhost    Database: apt
-- ------------------------------------------------------
-- Server version	5.5.24

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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (31,'A man bought a horse and a cart. If he sold the horse at 10 % loss and the cart at 20 % gain, he would not lose anything; but if he sold the horse at 5% loss and  the cart at 5% gain, he would lose Rs. 10 in the bargain. The amount paid by him was Rs. _______ for the horse and Rs.________ for the cart.','1200, 800','1100, 1000','100, 150','1800, 2000','None of the above','CHOICE_E'),(32,'It was calculated that 75 men could complete a piece of work in 20 days. When work was scheduled to commence, it was found necessary to send 25 men to another  project. How much longer will it take to complete the work?','21 days','30 days','28 days','32 days','None of the above','CHOICE_B'),(33,'A student divided a number by 2/3 when he required to multiply by 3/2. Calculate the percentage of error in his result.','12','10','30','18','None of the above','CHOICE_E'),(34,'A dishonest shopkeeper professes to sell pulses at the cost price, but he uses a false weight of 950gm. for a kg. His gain is ...%.','25.00%','33.33%','27.00%','48.50%','12.25%','CHOICE_D'),(35,'A software engineer has the capability of thinking 100 lines of code in five minutes and can type 100 lines of code in 10 minutes. He takes a break for five minutes after every ten minutes. How many lines of codes will he  complete typing after an hour?','280 lines','100 lines','250 lines','200 lines','210 lines','CHOICE_C'),(36,'A man was engaged on a job for 30 days on the condition that he would get a wage of Rs. 10 for the day he works, but he have to pay a fine of Rs. 2 for each day of his absence. If he gets Rs. 216 at the end, he was absent for work for ... days.','5 days','2 days','10 days','4 days','3 days','CHOICE_A'),(37,'A contractor agreeing to finish a work in 150 days, employed 75 men each working 8 hours daily. After 90 days, only 2/7 of the work was completed. Increasing the number of men by ________ each working now for 10 hours daily, the work can be completed in time.','10','30','20','25','50','CHOICE_D'),(38,'Eight friends Harsha, Fakis, Balaji, Eswar, Dhinesh, Chandra, Geetha, and Ahmed are sitting in a circle facing the center. Balaji is sitting between Geetha and Dhinesh. Harsha is third to the left of Balaji and second to the right of Ahmed. Chandra is sitting between Ahmed and Geetha and Balaji and Eshwar are not sitting opposite to each other. Who is third to the left of Dhinesh?','Harsha','Chandra','Ahmed','Geetha','Balaji','CHOICE_C'),(39,'Eight friends Harsha, Fakis, Balaji, Eswar, Dhinesh, Chandra, Geetha, and Ahmed are sitting in a circle facing the center. Balaji is sitting between Geetha and Dhinesh. Harsha is third to the left of Balaji and second to the right of Ahmed. Chandra is sitting between Ahmed and Geetha and Balaji and Eshwar are not sitting opposite to each other. Who is third to the left of Dhinesh?','Harsha','Chandra','Ahmed','Geetha','Balaji','CHOICE_C'),(40,'If every alternative letter starting from B of the English alphabet is written in small letter, rest all are written in capital letters, how the month September be written.','SeptEMbEr','SEpTeMBEr','SeptembeR','SepteMber','None of the above.','CHOICE_A'),(41,'All men are vertebrates. Some mammals are vertebrates. Which of the following conclusions drawn from the above statement is correct.','All men are mammals','All mammals are men','Some vertebrates are mammals.','None',NULL,'CHOICE_C'),(42,'If point P is on line segment AB, then which of the following is always true?','AP = PB','AP > PB','PB > AP','AB > AP','AB > AP + PB','CHOICE_E'),(43,'A tennis marker is trying to put together a team of four players for a tennis tournament out of seven available. males - a, b and c; females â€“ m, n, o and p. All players are of equal ability and there must be at least two males in the team. For a team of four, all players must be able to play with each other under the following restrictions:','b and p cannot be selected together','c and o cannot be selected together','c and n cannot be selected together',NULL,NULL,'CHOICE_A'),(44,'Which of the following statements, if true, would make the information in the numbered statements more specific?\r\n(a) Coshocton is north of Dover.\r\n(b) East Liverpool is north of Dover\r\n(c) Ashland is east of Bowling green.\r\n(d) Coshocton is east of Fredericktown\r\n(e) Bowling green is north of Fredericktown','a','b','c','d','e','CHOICE_D'),(45,'Which of the following towns must be situated both south and west of at least one other town?\r\nA. Ashland only\r\nB. Ashland and Fredericktown\r\nC. Dover and Fredericktown\r\nD. Dover, Coshocton and Fredericktown\r\nE. Coshocton, Dover and East Liverpool.','A','B','C','D','E','CHOICE_E'),(46,'Which of the following statements drawn from the given statements are correct?\r\nGiven:\r\nAll watches sold in that shop are of high standard. Some of the HMT watches\r\nare sold in that shop.\r\na) All watches of high standard were manufactured by HMT.\r\nb) Some of the HMT watches are of high standard.\r\nc) None of the HMT watches is of high standard.\r\nd) Some of the HMT watches of high standard are sold in that shop.','A','B','C','D','E','CHOICE_B'),(47,'A man bought a horse and a cart. If he sold the horse at 10 % loss and the cart at 20 % gain, he would not lose anything; but if he sold the horse at 5% loss and  the cart at 5% gain, he would lose Rs. 10 in the bargain. The amount paid by him was Rs. _______ for the horse and Rs.________ for the cart.','1200, 800','1100, 1000','100, 150','1800, 2000','None of the above','CHOICE_E'),(48,'Which of the following towns must be situated both south and west of at least one other town?\r\nA. Ashland only\r\nB. Ashland and Fredericktown\r\nC. Dover and Fredericktown\r\nD. Dover, Coshocton and Fredericktown\r\nE. Coshocton, Dover and East Liverpool.','A','B','C','D','E','CHOICE_E');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
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
  `weightage` float DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `question_id_tad_fk` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `user_id_tad_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_answer`
--

LOCK TABLES `test_answer` WRITE;
/*!40000 ALTER TABLE `test_answer` DISABLE KEYS */;
INSERT INTO `test_answer` VALUES (44,22,39,'CHOICE_E',0),(45,22,46,'CHOICE_B',1),(46,22,44,NULL,0),(47,22,47,NULL,0),(48,22,41,NULL,0),(49,22,34,'CHOICE_D',1),(50,22,37,'CHOICE_A',0),(51,22,33,'CHOICE_C',0),(52,22,48,'CHOICE_A',0),(53,22,42,NULL,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (17,'admin','$2a$10$37egHkHzqKzMUr0DJaIHkuzVO0wNY0AiXBebEJxtwvmTm6mSiG1UO',1,'sriram','admin','admin@mail.com','9812345678',0),(22,'061234','$2a$10$1rtNaqbgbZhwr5lDsPMc4.YwFYrjY4JsZZEbsKmWGPUN21isMaSIe',1,'NewUser','061234','newuser@gmail.com','9898212345',0),(23,'075012','$2a$10$s.chbaEVZ/bwwrzYMo9g6.etB8fJ1.k1E.pd.1KJUP7hIf9CeUPJ2',1,'Gokul','075012','gokul@gmail.com','9892224554',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (12,'admin','ROLE_ADMIN'),(18,'061234','ROLE_USER'),(19,'075012','ROLE_USER'),(17,'admin','ROLE_USER');
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

-- Dump completed on 2015-06-05 21:46:14
