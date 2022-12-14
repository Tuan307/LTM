-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: messenger
-- ------------------------------------------------------
-- Server version	5.5.61

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
-- Table structure for table `friendlist`
--

DROP TABLE IF EXISTS `friendlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friendlist` (
  `UserId` int(11) NOT NULL,
  `UserFriendId` int(11) NOT NULL,
  PRIMARY KEY (`UserId`,`UserFriendId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendlist`
--

LOCK TABLES `friendlist` WRITE;
/*!40000 ALTER TABLE `friendlist` DISABLE KEYS */;
INSERT INTO `friendlist` VALUES (1,2),(1,5),(1,6),(1,7),(1,8),(1,9),(1,64),(1,85),(2,3),(2,6),(3,6),(5,8),(7,3),(8,6),(9,2),(9,6),(9,10),(30,69),(30,80),(51,30),(51,80),(80,1),(85,30),(85,80);
/*!40000 ALTER TABLE `friendlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupchat`
--

DROP TABLE IF EXISTS `groupchat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groupchat` (
  `GroupId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL COMMENT 'User created',
  `GroupName` varchar(45) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`GroupId`,`UserId`),
  KEY `UserId_idx` (`UserId`),
  CONSTRAINT `UserId` FOREIGN KEY (`UserId`) REFERENCES `users` (`UsersId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupchat`
--

LOCK TABLES `groupchat` WRITE;
/*!40000 ALTER TABLE `groupchat` DISABLE KEYS */;
INSERT INTO `groupchat` VALUES (1,1,'test g1','2018-11-04 00:00:00'),(2,1,'g2','2018-11-04 00:00:00'),(4,2,'fsd','2018-11-04 00:00:00'),(5,1,'dfbf','2018-11-06 14:03:40'),(6,1,'sdvsd','2018-11-06 14:16:19'),(7,1,'dfd','2018-11-06 14:25:55'),(8,1,'sfs','2018-11-06 14:31:28'),(9,1,'sfs','2018-11-06 14:33:09'),(10,1,'a','2018-11-06 14:33:48'),(11,1,'a','2018-11-06 14:34:21'),(12,1,'a','2018-11-06 14:34:34'),(13,1,'r','2018-11-06 14:36:00'),(14,1,'test gf','2018-11-06 14:38:41'),(15,1,'test 1','2018-11-06 14:46:34'),(16,1,'g3rger','2018-11-06 14:48:29'),(17,1,'fsdf','2018-11-06 14:51:36'),(18,1,'dger','2018-11-06 14:52:18'),(19,1,'sdf','2018-11-06 14:56:15'),(20,1,'sdf','2018-11-06 15:00:02'),(21,1,'rthrth','2018-11-06 15:01:32'),(22,1,'sdfs','2018-11-06 15:02:21'),(23,80,'group chat A','2018-11-07 07:35:29'),(24,85,'test','2018-11-07 08:01:51'),(25,30,'group t4','2018-11-07 08:05:21');
/*!40000 ALTER TABLE `groupchat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupdetail`
--

DROP TABLE IF EXISTS `groupdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groupdetail` (
  `GroupId` int(11) NOT NULL,
  `ToUserId` int(11) NOT NULL,
  PRIMARY KEY (`GroupId`,`ToUserId`),
  KEY `TouserId_idx` (`ToUserId`),
  CONSTRAINT `GroupId` FOREIGN KEY (`GroupId`) REFERENCES `groupchat` (`GroupId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TouserId` FOREIGN KEY (`ToUserId`) REFERENCES `users` (`UsersId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupdetail`
--

LOCK TABLES `groupdetail` WRITE;
/*!40000 ALTER TABLE `groupdetail` DISABLE KEYS */;
INSERT INTO `groupdetail` VALUES (1,1),(2,1),(6,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(1,2),(5,2),(6,2),(13,2),(14,2),(19,2),(13,3),(14,3),(15,3),(16,3),(17,3),(18,3),(20,3),(21,3),(5,4),(6,4),(10,4),(11,4),(12,4),(13,4),(14,4),(22,4),(13,5),(14,5),(5,6),(13,6),(14,6),(13,7),(14,7),(13,8),(14,8),(13,9),(14,9),(24,30),(25,30),(23,51),(23,80),(24,80),(25,80),(24,85),(25,85);
/*!40000 ALTER TABLE `groupdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `messagesId` int(11) NOT NULL AUTO_INCREMENT,
  `fromUserId` int(11) NOT NULL,
  `toUserId` int(11) NOT NULL,
  `content` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `createdDate` datetime NOT NULL,
  `groupId` int(11) DEFAULT '-1',
  PRIMARY KEY (`messagesId`,`fromUserId`,`toUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,1,2,'dfgf','2018-12-09 00:00:00',-1),(2,2,1,'bunij123','2018-12-09 00:01:00',-1),(3,1,2,'fsddddddddddddddddddddddddddddffffffffffffffffffffffffffffffffffffffffgggggggggggggggggggfsddddddddddddddddddddddddddddffffffffffffffffffffffffffffffffffffffffgggggggggggggggggggfsddddddddddddddddddddddddddddffffffffffffffffffffffffffffffffffffffffgggggggggggggggggggfsddddddddddddddddddddddddddddffffffffffffffffffffffffffffffffffffffffgggggggggggggggggggfsddddddddddddddddddddddddddddffffffffffffffffffffffffffffffffffffffffggggggggggggggggggg','2018-12-09 00:02:00',-1),(4,3,1,'dgsgsdgdfgs','2018-06-09 00:02:00',-1),(5,1,4,'sdfgrtghyhty','2018-03-09 00:02:01',-1),(6,1,4,'asdfs','2018-10-13 00:02:01',-1),(7,1,4,'manh test gui mess 1','2018-10-13 18:20:55',-1),(8,1,6,'xin chao','2018-10-13 18:30:09',-1),(9,1,8,'manh test mess\n','2018-10-13 18:46:35',-1),(10,8,1,'1','2018-10-13 18:59:31',-1),(11,1,8,'what your name?\n','2018-10-13 19:43:07',-1),(12,1,8,'dfv','2018-10-13 19:43:39',-1),(13,1,8,'fvdfvff','2018-10-13 19:43:43',-1),(14,1,8,'3','2018-10-13 19:43:47',-1),(15,1,8,'4','2018-10-13 19:43:53',-1),(16,1,8,'df','2018-10-13 19:43:58',-1),(17,1,8,'dfvdfv','2018-10-13 19:44:02',-1),(18,1,8,'dfbrtrt','2018-10-13 19:44:05',-1),(19,1,8,'rtrtt','2018-10-13 19:44:08',-1),(20,1,8,'rtttt','2018-10-13 19:44:12',-1),(21,1,8,'jujuyjy','2018-10-13 19:44:15',-1),(22,1,8,'new1','2018-10-13 19:44:24',-1),(23,1,3,'afsdf','2018-10-13 21:26:30',-1),(24,1,2,'dsgdsds','2018-10-17 07:42:00',-1),(25,1,2,'1','2018-10-17 07:42:12',-1),(26,2,1,'chim','2018-10-17 07:42:25',-1),(27,1,2,'sdfsdfsd','2018-11-03 11:15:59',-1),(28,2,1,'bay h la may h?','2018-11-03 11:16:10',-1),(29,1,1,'test mess group1','2018-11-03 11:16:10',1),(30,1,0,'test mess group2','2018-11-04 12:10:51',1),(31,1,1,'mess g3','2018-11-04 12:13:00',1),(32,1,1,'mess g4\n','2018-11-04 12:15:06',1),(33,2,1,'mess1','2018-11-05 00:19:09',-1),(34,1,2,'do minh cai thoi gian lech nen moi hien lon xon vay','2018-11-05 00:19:37',-1),(35,1,1,'phan group chat tuong tu','2018-11-05 00:20:20',1),(36,1,1,'minh de code & db ben duoi nhe, ai can giai thich thi inbox minh','2018-11-05 00:20:53',1),(37,1,1,'thank!','2018-11-05 00:21:03',1),(38,1,2,'dfbf','2018-11-06 11:41:32',-1),(39,1,2,'sdbef','2018-11-06 11:41:40',2),(40,1,2,'nguy???n v??n a','2018-11-06 11:46:26',-1),(41,1,2,'ph???m ?????c m???nh','2018-11-06 11:49:16',-1),(42,1,2,'m?nh ??c','2018-11-06 11:58:58',-1),(43,1,3,'ti?ng vi?t 1','2018-11-06 12:02:00',-1),(44,1,4,'tieesng vi?t','2018-11-06 12:14:29',-1),(45,1,64,'test 1','2018-11-06 22:28:57',-1),(46,1,64,'test2','2018-11-06 22:29:13',-1),(47,80,1,'hello\n','2018-11-07 07:31:43',-1),(48,80,23,'hello world','2018-11-07 07:35:51',23),(49,1,23,'lo cc','2018-11-07 07:36:08',23),(50,30,25,'sfsdfsf','2018-11-07 08:05:36',25),(51,80,25,'to la quyet','2018-11-07 08:06:09',25),(52,85,25,'day la may client\n','2018-11-07 08:06:16',25);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offlinemessage`
--

DROP TABLE IF EXISTS `offlinemessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offlinemessage` (
  `FromUserId` int(11) NOT NULL,
  `ToUserId` varchar(45) NOT NULL,
  `Message` text,
  `CreatedDate` datetime NOT NULL,
  PRIMARY KEY (`FromUserId`,`ToUserId`,`CreatedDate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offlinemessage`
--

LOCK TABLES `offlinemessage` WRITE;
/*!40000 ALTER TABLE `offlinemessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `offlinemessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UsersId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) NOT NULL,
  `PassWord` varchar(45) NOT NULL,
  `State` int(1) DEFAULT '0' COMMENT '0- offline\n1-online',
  `FirstName` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `LastName` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Address` varchar(65) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`UsersId`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin',0,'M???nh','Ph???m ?????c','a','a'),(2,'user1','pass',0,'user1','last1','a','a'),(3,'user2','pass',0,'user2','last1','a','a'),(4,'user3','pass',0,'user3','last1','a','a'),(5,'user4','pass',0,'user4','last1','a','a'),(6,'user5','pass',0,'user5','last1','a','a'),(7,'user6','pass',0,'user6','last1','a','a'),(8,'user7','pass',0,'user7','last1','a','a'),(9,'user8','pass',0,'user8','last1','a','a'),(10,'user9','pass',0,'user9','last1','a','a'),(20,'B15DCCN010','pass',0,'L?? Vi???t','Anh','email','adress'),(21,'B15DCAT019','pass',0,'Ng?? Ng???c','B??ch','email','adress'),(22,'B15DCCN063','pass',0,'V?? Minh','Ch??u','email','adress'),(23,'B15DCAT031','pass',0,'Ng?? V??n','C?????ng','email','adress'),(24,'B15DCCN118','pass',0,'Nguy???n V??n','Di???n','email','adress'),(25,'B15DCAT052','pass',0,'Nguy???n Ch??','D??ng','email','adress'),(26,'B15DCAT043','pass',0,'Nguy???n V??n','??i???n','email','adress'),(27,'B14DCCN028','pass',0,'L?? Xu??n','Hai','email','adress'),(28,'B15DCCN225','pass',0,'V?? Th???','Hoa','email','adress'),(29,'B15DCAT078','pass',0,'Nguy???n Do??n','Ho??i','email','adress'),(30,'B15DCCN347','pass',0,'Ph???m ?????c','M???nh','email','adress'),(31,'B15DCCN358','pass',0,'B??i Ch??','Minh','email','adress'),(32,'B15DCCN352','pass',0,'L????ng H???i','Minh','email','adress'),(33,'B15DCCN373','pass',0,'????? Ho??ng','Nam','email','adress'),(34,'B15DCCN384','pass',0,'Ho??ng Ng???c','Nga','email','adress'),(35,'B15DCAT123','pass',0,'Nguy???n Minh','Ngh??a','email','adress'),(36,'B15DCCN392','pass',0,'Nguy???n ?????c Minh','Ng???c','email','adress'),(37,'B15DCCN414','pass',0,'Tr???n V??n','Ph??ng','email','adress'),(38,'B15DCAT132','pass',0,'B??i Th???','Ph????ng','email','adress'),(39,'B15DCCN417','pass',0,'L?? Th???','Ph????ng','email','adress'),(40,'B15DCCN416','pass',0,'Nguy???n Th???','Ph????ng','email','adress'),(41,'B15DCAT139','pass',0,'Cao ?????c','Qu???nh','email','adress'),(42,'B15DCAT140','pass',0,'V?? Th??? Ng???c','Qu???nh','email','adress'),(43,'B15DCCN469','pass',0,'Ho??ng Th???','S??n','email','adress'),(44,'B15DCCN519','pass',0,'Nguy???n Tu???n','Th??nh','email','adress'),(45,'B15DCAT156','pass',0,'T??? Th???','Th???o','email','adress'),(46,'B15DCCN498','pass',0,'Nguy???n C??ng','Th???ng','email','adress'),(47,'B15DCAT172','pass',0,'????? H???u','Trung','email','adress'),(48,'B15DCCN580','pass',0,'????? V??n','Trung','email','adress'),(49,'B14DCCN543','pass',0,'L?? Th??nh','Trung','email','adress'),(50,'B15DCCN599','pass',0,'B??i V??n','T???','email','adress'),(51,'B15DCCN600','pass',0,'Tr???nh V??n','Tu??n','email','adress'),(52,'B15DCCN624','pass',0,'Nguy???n Thanh','T??ng','email','adress'),(53,'B16LDCN008','pass',0,'Ki???u Ti???n','V??','email','adress'),(54,'B15DCCN046','pass',0,'Nguy???n Tu???n','Anh','email','adress'),(55,'B15DCAT005','pass',0,'Nguy???n Vi???t','Anh','email','adress'),(56,'B15DCCN065','pass',0,'?????ng B???o','Chi???n','email','adress'),(57,'B15DCAT053','pass',0,'L?? Ch??','D??ng','email','adress'),(58,'B15DCCN167','pass',0,'Ph???m Quang','Duy','email','adress'),(59,'B15DCCN156','pass',0,'H??n Ng???c','D????ng','email','adress'),(60,'B15DCCN163','pass',0,'Nguy???n V??n','D????ng','email','adress'),(61,'B15DCAT036','pass',0,'Nguy???n Vi???t','?????i','email','adress'),(62,'B15DCAT041','pass',0,'Nguy???n Th??nh','?????t','email','adress'),(63,'B15DCCN132','pass',0,'Nguy???n Anh','?????c','email','adress'),(64,'B15DCCN173','pass',0,'Phan Th??? Di???u','H??','email','adress'),(65,'B15DCCN194','pass',0,'Nguy???n Th???','H???ng','email','adress'),(66,'B14DCCN089','pass',0,'D????ng V??n','Ho??n','email','adress'),(67,'B15DCAT084','pass',0,'Nguy???n ?????c','Hu???','email','adress'),(68,'B15DCCN277','pass',0,'Chu Th???','Huy','email','adress'),(69,'B15DCAT093','pass',0,'L?? M???nh','Huy','email','adress'),(70,'B15DCCN276','pass',0,'Tr???n ????nh','Huy','email','adress'),(71,'B15DCCN256','pass',0,'L?? Ph??c Di??n','H??ng','email','adress'),(72,'B15DCCN266','pass',0,'Nguy???n Thu','H????ng','email','adress'),(73,'B15DCCN700','pass',0,'Kittiphatphong','Khanthavong','email','adress'),(74,'B16LDCN004','pass',0,'B??i Th??i','Linh','email','adress'),(75,'B15DCCN308','pass',0,'T??? T??i','Linh','email','adress'),(76,'B15DCCN336','pass',0,'Ph???m Th???','Mai','email','adress'),(77,'B15DCCN354','pass',0,'Nguy???n Duy','Minh','email','adress'),(78,'B15DCAT129','pass',0,'????? V??n','Nh???t','email','adress'),(79,'B15DCCN438','pass',0,'H?? Minh','Quang','email','adress'),(80,'B15DCCN446','pass',0,'Ho??ng Xu??n','Quy???t','email','adress'),(81,'B15DCAT159','pass',0,'Tr???n Xu??n','Thi???n','email','adress'),(82,'B15DCAT171','pass',0,'????? V??n','Tr???nh','email','adress'),(83,'B15DCCN591','pass',0,'Ph???m Minh','T??','email','adress'),(84,'B15DCCN640','pass',0,'Nguy???n Th???','V??n','email','adress'),(85,'B15DCCN644','pass',0,'Nguy???n Minh','Vi???t','email','adress'),(86,'B15DCAT198','pass',0,'Nguy???n ????nh','V?????ng','email','adress');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-08 11:41:45
