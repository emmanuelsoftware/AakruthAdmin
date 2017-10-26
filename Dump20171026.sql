CREATE DATABASE  IF NOT EXISTS `erp` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `erp`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: erp
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `BRANCH_ID` int(11) NOT NULL,
  `BRANCH_NAME` varchar(45) NOT NULL,
  `ADDRRESS` varchar(45) NOT NULL,
  `CONTACT_NUMBER` int(10) NOT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `GSTIN` varchar(45) DEFAULT NULL,
  `PAN` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`BRANCH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'EMMANUAL','GUDALUR',123456789,'EMANUAL@GMAIL','0000','12234');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit`
--

DROP TABLE IF EXISTS `credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit` (
  `cred_Id` int(11) NOT NULL AUTO_INCREMENT,
  `voucher_no` varchar(45) NOT NULL,
  `type_of_payment` int(11) NOT NULL,
  `SGST` decimal(10,2) DEFAULT '0.00',
  `IGST` decimal(10,2) DEFAULT '0.00',
  `CGST` decimal(10,2) DEFAULT '0.00',
  `OTHER_TAX` decimal(10,2) DEFAULT '0.00',
  `strdte` date NOT NULL,
  `enddte` date NOT NULL,
  `type` int(11) NOT NULL,
  `particulars` varchar(100) NOT NULL,
  `remarks` varchar(100) NOT NULL,
  `sta` char(1) NOT NULL,
  `dealer_id` int(11) DEFAULT NULL,
  `pur_id` int(11) DEFAULT '0',
  `aud_usr_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`cred_Id`),
  KEY `FK_DEALER_CREDIT_idx` (`dealer_id`),
  KEY `FK_TRAN_CREDIT_idx` (`type`),
  CONSTRAINT `FK_DEALER_CREDIT` FOREIGN KEY (`dealer_id`) REFERENCES `dealer` (`DEALER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TRAN_CREDIT` FOREIGN KEY (`type`) REFERENCES `transaction` (`tran_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit`
--

LOCK TABLES `credit` WRITE;
/*!40000 ALTER TABLE `credit` DISABLE KEYS */;
/*!40000 ALTER TABLE `credit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dealer`
--

DROP TABLE IF EXISTS `dealer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dealer` (
  `DEALER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `poc` varchar(45) NOT NULL,
  `phnnbr` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `adr` varchar(100) NOT NULL,
  `strdte` date NOT NULL,
  `enddte` date NOT NULL,
  `sta` char(1) NOT NULL,
  `type` char(1) NOT NULL,
  `aud_usr_id` int(11) NOT NULL DEFAULT '0',
  `gstin` varchar(45) NOT NULL,
  PRIMARY KEY (`DEALER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dealer`
--

LOCK TABLES `dealer` WRITE;
/*!40000 ALTER TABLE `dealer` DISABLE KEYS */;
INSERT INTO `dealer` VALUES (1,'Apple','apple@customercar.com','6455','Good@bad.com','aha','2017-01-01','9999-01-01','L','B',1,'11112222'),(2,'Farmer','farmor','46432321','farmer.momail.com','dfasdf','2017-02-02','9999-01-02','L','C',2,'64446'),(3,'Jobin','Jobin farm','232313','sdfasdfas','adfasd','2017-07-25','9999-01-01','L','C',1,'685464646sdsfd'),(6,'hgh','gfhg','6587568','khgkhg','jkhghg','2017-07-17','9999-01-01','L','B',1,'23'),(7,'hello','hg','3333444','gjcg','gjfh','2017-07-17','9999-01-01','L','B',1,'5'),(11,'jhhhgh','bkjhbhjb','6896','hhubub','hhubh','2017-07-17','9999-01-01','L','C',1,'68');
/*!40000 ALTER TABLE `dealer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `debit`
--

DROP TABLE IF EXISTS `debit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `debit` (
  `deb_Id` int(11) NOT NULL AUTO_INCREMENT,
  `voucher_no` varchar(45) NOT NULL,
  `type_of_payment` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `AMT` decimal(10,2) DEFAULT '0.00',
  `SGST` decimal(10,2) DEFAULT '0.00',
  `IGST` decimal(10,2) DEFAULT '0.00',
  `CGST` decimal(10,2) DEFAULT '0.00',
  `OTHER_TAX` decimal(10,2) DEFAULT '0.00',
  `particular` varchar(100) NOT NULL,
  `remarks` varchar(100) NOT NULL,
  `strdte` date NOT NULL,
  `enddte` date NOT NULL,
  `sta` char(1) NOT NULL,
  `dealer_id` int(11) DEFAULT NULL,
  `bill_id` int(11) DEFAULT '0',
  `aud_usr_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`deb_Id`),
  KEY `FK_DEALER_DEBIT_idx` (`dealer_id`),
  KEY `FK_TRAN_DEBIT_idx` (`type`),
  CONSTRAINT `FK_DEALER_DEBIT` FOREIGN KEY (`dealer_id`) REFERENCES `dealer` (`DEALER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TRAN_DEBIT` FOREIGN KEY (`type`) REFERENCES `transaction` (`tran_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `debit`
--

LOCK TABLES `debit` WRITE;
/*!40000 ALTER TABLE `debit` DISABLE KEYS */;
/*!40000 ALTER TABLE `debit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logistic`
--

DROP TABLE IF EXISTS `logistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logistic` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGISTIC_NAME` varchar(45) DEFAULT NULL,
  `TRANSMIT_FROM` varchar(45) DEFAULT NULL,
  `TRANSMIT_TWO` varchar(45) DEFAULT NULL,
  `TRACKING_NUMBER` varchar(45) DEFAULT NULL,
  `CONTACT_PERSON` varchar(45) DEFAULT NULL,
  `CONTACT_NUMBER` varchar(45) DEFAULT NULL,
  `USR_ID` int(11) DEFAULT NULL,
  `CATEGORY` varchar(45) DEFAULT NULL,
  `REF_ID` int(11) DEFAULT NULL,
  `STA` char(1) DEFAULT NULL,
  `START_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logistic`
--

LOCK TABLES `logistic` WRITE;
/*!40000 ALTER TABLE `logistic` DISABLE KEYS */;
/*!40000 ALTER TABLE `logistic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `NOTIFY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOTIFY_DES` varchar(45) DEFAULT NULL,
  `NOTIFY_ROLE` varchar(10) DEFAULT NULL,
  `AUD_USR_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`NOTIFY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'Changed sdsdf','ADMIN',1),(2,'Changed sdsdf','ADMIN',1),(3,'Changed sdfds','ADMIN',1),(4,'Changed sdfds','ADMIN',1),(5,'Changed hgh','ADMIN',1),(6,'Changed hello','ADMIN',1),(7,'Changed ttre','ADMIN',1),(8,'Changed fdf','ADMIN',1),(9,'Changed eeeer','ADMIN',1),(10,'Changed helo','ADMIN',1),(11,'Changed helo','ADMIN',1),(12,'Changed Clientjhhhgh','ADMIN',1),(13,'Changed Clienteeeer','ADMIN',1),(14,'Deleted Dealer eeeer','ADMIN',1),(15,'Deleted Dealer ttre','ADMIN',1),(16,'Deleted Dealer sdfds','ADMIN',1),(17,'Changed afafdsas','ADMIN',1),(18,'Deleted Product afafdsas','ADMIN',1),(19,'Changed ClientJobin','ADMIN',1);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `PRD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRDNME` varchar(45) NOT NULL,
  `BLD_ID` int(11) NOT NULL,
  `STRDTE` date NOT NULL,
  `ENDDTE` date NOT NULL,
  `STA` char(1) NOT NULL,
  `WARRANTY` decimal(3,0) NOT NULL,
  `PUR_AMT` decimal(10,2) NOT NULL,
  `SALE_AMT` decimal(10,2) NOT NULL,
  `hsn_code` varchar(45) NOT NULL,
  `SGST` decimal(10,2) DEFAULT '0.00',
  `IGST` decimal(10,2) DEFAULT '0.00',
  `CGST` decimal(10,2) DEFAULT '0.00',
  `OTHER_TAX` decimal(10,2) DEFAULT '0.00',
  `aud_usr_id` int(11) NOT NULL DEFAULT '0',
  `discount` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`PRD_ID`),
  KEY `FK_DEALER_PRD_TBL_idx` (`BLD_ID`),
  CONSTRAINT `FK_DEALER_PRD_TBL` FOREIGN KEY (`BLD_ID`) REFERENCES `dealer` (`DEALER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Iphone',1,'2017-07-16','2017-07-16','L',13,4566546.00,465462.00,'dfas',15.00,12.00,14.00,1.00,1,12.00),(2,'Water melon',2,'2017-02-04','9999-01-02','L',15,454645.00,5465.00,'dfadfasd',2.00,5.00,6.00,1.00,2,2.00),(3,'Yoga',1,'2017-07-16','2017-07-16','L',2,1.00,2.00,'goods',3.00,2.00,2.00,3.00,1,2.00),(4,'afafdsas',1,'2017-07-17','2017-07-17','T',232,3323.00,23.00,'addq',232.00,22.00,2323.00,23.00,1,2320.00);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `PUR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRD_ID` int(11) NOT NULL,
  `CNT` int(11) NOT NULL,
  `STRDTE` date NOT NULL,
  `BILL_ID` int(11) NOT NULL,
  `ENDDTE` date NOT NULL,
  `STA` char(1) NOT NULL,
  `ORDER_NUM` int(11) DEFAULT NULL,
  `PUR_AMT` decimal(10,2) NOT NULL,
  `SGST` decimal(10,2) DEFAULT '0.00',
  `IGST` decimal(10,2) DEFAULT '0.00',
  `CGST` decimal(10,2) DEFAULT '0.00',
  `OTHER_TAX` decimal(10,2) DEFAULT '0.00',
  `aud_usr_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`PUR_ID`),
  KEY `FK_PRODUCT_idx` (`PRD_ID`),
  KEY `FK_BILL_idx` (`BILL_ID`),
  CONSTRAINT `FK_BILL` FOREIGN KEY (`BILL_ID`) REFERENCES `purchase_bill` (`BILL_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PRODUCT` FOREIGN KEY (`PRD_ID`) REFERENCES `product` (`PRD_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_bill`
--

DROP TABLE IF EXISTS `purchase_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_bill` (
  `BILL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BLD_ID` int(11) NOT NULL,
  `USR_ID` int(11) NOT NULL,
  `STRDTE` date NOT NULL,
  `STA` char(1) NOT NULL,
  `ENDDTE` date NOT NULL,
  `po_num` varchar(45) DEFAULT NULL,
  `entry_dte` date DEFAULT NULL,
  `LOG_ID` int(11) DEFAULT NULL,
  `tax_type` char(1) NOT NULL,
  PRIMARY KEY (`BILL_ID`),
  KEY `FK_USER_BILL_idx` (`USR_ID`),
  KEY `FK_DEALER_BILL_idx` (`BLD_ID`),
  KEY `FK_LOGISTIC_idx` (`LOG_ID`),
  KEY `FK_LOG_idx` (`LOG_ID`),
  CONSTRAINT `FK_DEALER_BILL` FOREIGN KEY (`BLD_ID`) REFERENCES `dealer` (`DEALER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_LOG` FOREIGN KEY (`LOG_ID`) REFERENCES `logistic` (`LOG_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_BILL` FOREIGN KEY (`USR_ID`) REFERENCES `user` (`USR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_bill`
--

LOCK TABLES `purchase_bill` WRITE;
/*!40000 ALTER TABLE `purchase_bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE` varchar(255) NOT NULL,
  `USR_ID` int(11) NOT NULL,
  PRIMARY KEY (`ROLE_ID`),
  KEY `FK_USR_TBL_ROL_TBL_idx` (`USR_ID`),
  CONSTRAINT `FK_USR_TBL_ROL_TBL` FOREIGN KEY (`USR_ID`) REFERENCES `user` (`USR_ID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN',1),(2,'USER',2);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale` (
  `SAL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CNT` int(11) NOT NULL,
  `PRD_ID` int(11) NOT NULL,
  `BILL_ID` int(11) NOT NULL,
  `STA` char(1) NOT NULL,
  `SALE_AMT` decimal(10,2) NOT NULL,
  `SGST` decimal(10,2) DEFAULT '0.00',
  `IGST` decimal(10,2) DEFAULT '0.00',
  `CGST` decimal(10,2) DEFAULT '0.00',
  `OTHER_TAX` decimal(10,2) DEFAULT '0.00',
  `discount` decimal(10,2) DEFAULT '0.00',
  `aud_usr_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`SAL_ID`),
  KEY `FK_PRD_TBL_SAL_TBL_idx` (`PRD_ID`),
  KEY `FK_BILL_TBL_SAL_TBL_idx` (`BILL_ID`),
  CONSTRAINT `FK_BILL_TBL_SAL_TBL` FOREIGN KEY (`BILL_ID`) REFERENCES `sale_bill` (`BILL_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PRD_TBL_SAL_TBL` FOREIGN KEY (`PRD_ID`) REFERENCES `product` (`PRD_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES (1,2,1,1,'L',465462.00,15.00,12.00,14.00,0.00,0.00,1),(2,3,2,1,'L',5465.00,2.00,5.00,6.00,1.00,0.00,2),(3,3,3,1,'\0',2.00,3.00,2.00,2.00,3.00,2.00,1);
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_bill`
--

DROP TABLE IF EXISTS `sale_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale_bill` (
  `BILL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CLI_ID` int(11) NOT NULL,
  `USR_ID` int(11) NOT NULL,
  `STRDTE` date NOT NULL,
  `STA` char(1) NOT NULL,
  `ENDDTE` date NOT NULL,
  `po_num` varchar(45) DEFAULT NULL,
  `entry_dte` date DEFAULT NULL,
  `LOG_ID` int(11) DEFAULT NULL,
  `tax_type` char(1) DEFAULT NULL,
  PRIMARY KEY (`BILL_ID`),
  KEY `FK_USR_TBL_BILL_TBL_idx` (`USR_ID`),
  KEY `FK_DEALER_BILL_TBL_idx` (`CLI_ID`),
  KEY `FK_LOGISTIC_idx` (`LOG_ID`),
  CONSTRAINT `FK_DEALER_BILL_TBL` FOREIGN KEY (`CLI_ID`) REFERENCES `dealer` (`DEALER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_LOGISTIC` FOREIGN KEY (`LOG_ID`) REFERENCES `logistic` (`LOG_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USR_TBL_BILL_TBL` FOREIGN KEY (`USR_ID`) REFERENCES `user` (`USR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_bill`
--

LOCK TABLES `sale_bill` WRITE;
/*!40000 ALTER TABLE `sale_bill` DISABLE KEYS */;
INSERT INTO `sale_bill` VALUES (1,2,1,'2017-02-05','L','9999-01-05','fhfhgfhgf','2017-05-06',NULL,'C'),(2,3,2,'2017-05-08','L','9999-04-06','dgdfgdg','2017-06-08',NULL,'S'),(5,11,1,'2017-07-30','L','9999-01-01','fdsssaaas','2017-07-17',NULL,'S');
/*!40000 ALTER TABLE `sale_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `TASK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TASK_DES` varchar(45) DEFAULT NULL,
  `STA` char(1) DEFAULT NULL,
  `AUD_USR_ID` int(11) DEFAULT NULL,
  `ASSIGN_USR_ID` int(11) DEFAULT NULL,
  `STARTDATE` date DEFAULT NULL,
  `ENDDATE` date DEFAULT NULL,
  `ASSIGN_ROLE` varchar(10) DEFAULT NULL,
  `ASSIGN_DATE` date DEFAULT NULL,
  `REF_ID` int(11) DEFAULT NULL,
  `REF_TABLE` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`TASK_ID`),
  KEY `FK_USE_TASK_idx` (`ASSIGN_USR_ID`),
  CONSTRAINT `FK_USE_TASK` FOREIGN KEY (`ASSIGN_USR_ID`) REFERENCES `user` (`USR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `tran_Id` int(11) NOT NULL AUTO_INCREMENT,
  `detail` varchar(45) NOT NULL,
  `type` char(1) NOT NULL,
  PRIMARY KEY (`tran_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USRNME` varchar(45) NOT NULL,
  `PHNNBR` varchar(10) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `ADR` varchar(45) NOT NULL,
  `STRDTE` date NOT NULL,
  `ENDDTE` date NOT NULL,
  `STA` char(1) NOT NULL,
  `PSWD` varchar(45) NOT NULL,
  `AUD_USR_ID` int(11) DEFAULT NULL,
  `BRANCH_ID` int(11) NOT NULL,
  PRIMARY KEY (`USR_ID`),
  KEY `FK_BRANCH_USER` (`BRANCH_ID`),
  CONSTRAINT `FK_BRANCH_USER` FOREIGN KEY (`BRANCH_ID`) REFERENCES `branch` (`BRANCH_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Issac','9739633716','issac.prasad@erp.com','Charuvila','2017-01-01','9999-02-05','L','issac@123',1,1),(2,'Jobin','7895564','jobin.ebanezer@erp.com','Gudalur','2017-01-01','9999-01-02','L','jobin@123',2,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'erp'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-26 23:16:09
