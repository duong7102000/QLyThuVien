-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: localhost    Database: QLThuVien
-- ------------------------------------------------------
-- Server version	8.0.27-0ubuntu0.20.04.1

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
-- Table structure for table `tbl_chitietphieumuon`
--

DROP TABLE IF EXISTS `tbl_chitietphieumuon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_chitietphieumuon` (
  `id_phieumuon` int NOT NULL,
  `id_sach` int NOT NULL,
  `date_borrow` date NOT NULL,
  `date_return` date NOT NULL,
  `forfeit` float NOT NULL,
  PRIMARY KEY (`id_phieumuon`,`id_sach`),
  KEY `FK_id_sach` (`id_sach`),
  CONSTRAINT `FK_id_phieumuon` FOREIGN KEY (`id_phieumuon`) REFERENCES `tbl_phieumuon` (`id_phieumuon`),
  CONSTRAINT `FK_id_sach` FOREIGN KEY (`id_sach`) REFERENCES `tbl_sach` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_chitietphieumuon`
--

LOCK TABLES `tbl_chitietphieumuon` WRITE;
/*!40000 ALTER TABLE `tbl_chitietphieumuon` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_chitietphieumuon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_phieumuon`
--

DROP TABLE IF EXISTS `tbl_phieumuon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_phieumuon` (
  `id_phieumuon` int NOT NULL,
  `username_sinhvien` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `username_thuthu` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_start` date NOT NULL,
  `date_end` date NOT NULL,
  `deposit` float NOT NULL,
  PRIMARY KEY (`id_phieumuon`,`username_sinhvien`),
  KEY `fk_tbl_phieumuon_1_idx` (`username_sinhvien`),
  KEY `FK_username_thuthu_phieumuon` (`username_thuthu`),
  CONSTRAINT `FK_username_sinhvien_phieumuon` FOREIGN KEY (`username_sinhvien`) REFERENCES `tbl_taikhoan` (`username`),
  CONSTRAINT `FK_username_thuthu_phieumuon` FOREIGN KEY (`username_thuthu`) REFERENCES `tbl_taikhoan` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_phieumuon`
--

LOCK TABLES `tbl_phieumuon` WRITE;
/*!40000 ALTER TABLE `tbl_phieumuon` DISABLE KEYS */;
INSERT INTO `tbl_phieumuon` VALUES (1,'duongdt','namnv','2021-12-20','2021-12-27',20000);
/*!40000 ALTER TABLE `tbl_phieumuon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_sach`
--

DROP TABLE IF EXISTS `tbl_sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_sach` (
  `id` int NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `artist` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `major` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `total` int NOT NULL,
  `remain` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_sach`
--

LOCK TABLES `tbl_sach` WRITE;
/*!40000 ALTER TABLE `tbl_sach` DISABLE KEYS */;
INSERT INTO `tbl_sach` VALUES (1,'Giải tích 2','Giáo sư A','Sách giải tích cơ bản','Toán học',52,52);
/*!40000 ALTER TABLE `tbl_sach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_sinhvien`
--

DROP TABLE IF EXISTS `tbl_sinhvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_sinhvien` (
  `fullname` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `birth` date NOT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `major` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `class` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `FK_username_sinhvien` FOREIGN KEY (`username`) REFERENCES `tbl_taikhoan` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_sinhvien`
--

LOCK TABLES `tbl_sinhvien` WRITE;
/*!40000 ALTER TABLE `tbl_sinhvien` DISABLE KEYS */;
INSERT INTO `tbl_sinhvien` VALUES ('Đào Tùng Dương 1','2000-10-07','Hải Dương','Khoa học máy tính','IT1-04-K63','duongdt');
/*!40000 ALTER TABLE `tbl_sinhvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_taikhoan`
--

DROP TABLE IF EXISTS `tbl_taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_taikhoan` (
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `position` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_taikhoan`
--

LOCK TABLES `tbl_taikhoan` WRITE;
/*!40000 ALTER TABLE `tbl_taikhoan` DISABLE KEYS */;
INSERT INTO `tbl_taikhoan` VALUES ('Admin','admin','admin'),('duongdt','12345678','student'),('namnv','12345678','librarian');
/*!40000 ALTER TABLE `tbl_taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_thuthu`
--

DROP TABLE IF EXISTS `tbl_thuthu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_thuthu` (
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `birth` date NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `FK_username_thuthu` FOREIGN KEY (`username`) REFERENCES `tbl_taikhoan` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_thuthu`
--

LOCK TABLES `tbl_thuthu` WRITE;
/*!40000 ALTER TABLE `tbl_thuthu` DISABLE KEYS */;
INSERT INTO `tbl_thuthu` VALUES ('Nguyễn Văn Nam','2000-07-10','namnv');
/*!40000 ALTER TABLE `tbl_thuthu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-21 15:51:00
