CREATE DATABASE  IF NOT EXISTS `warsztat` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `warsztat`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: warsztat
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `czesc`
--

DROP TABLE IF EXISTS `czesc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `czesc` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Nazwa` varchar(100) NOT NULL,
  `Producent` varchar(100) NOT NULL,
  `Ilosc` int(11) NOT NULL,
  `Cena` float NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `czesc`
--

LOCK TABLES `czesc` WRITE;
/*!40000 ALTER TABLE `czesc` DISABLE KEYS */;
/*!40000 ALTER TABLE `czesc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klient`
--

DROP TABLE IF EXISTS `klient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `klient` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Imie` varchar(100)  NOT NULL,
  `Nazwisko` varchar(100)  NOT NULL,
  `Adres` varchar(100)  DEFAULT NULL,
  `Miejscowosc` varchar(100)  DEFAULT NULL,
  `Telefon` varchar(100)  NOT NULL,
  `Email` varchar(100)  DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klient`
--

LOCK TABLES `klient` WRITE;
/*!40000 ALTER TABLE `klient` DISABLE KEYS */;
/*!40000 ALTER TABLE `klient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `naprawa`
--

DROP TABLE IF EXISTS `naprawa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `naprawa` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Klient` int(11) NOT NULL,
  `Samochod` int(11) NOT NULL,
  `Data_rozpoczecia` datetime NOT NULL,
  `Data_zakonczenia` datetime DEFAULT NULL,
  `Koszt` float DEFAULT NULL,
  `Opis` text NOT NULL,
  `Status` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `naprawa_klient` FOREIGN KEY (`Klient`) REFERENCES `klient` (`ID`) ON UPDATE CASCADE,
  CONSTRAINT `naprawa_samochod` FOREIGN KEY (`Samochod`) REFERENCES `samochod` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `naprawa`
--

LOCK TABLES `naprawa` WRITE;
/*!40000 ALTER TABLE `naprawa` DISABLE KEYS */;
/*!40000 ALTER TABLE `naprawa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `naprawa_czesc`
--

DROP TABLE IF EXISTS `naprawa_czesc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `naprawa_czesc` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Naprawa` int(11) NOT NULL,
  `Czesc` int(11) NOT NULL,
  `Ilosc` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `naprawa_czesc_czesc` FOREIGN KEY (`Czesc`) REFERENCES `czesc` (`ID`) ON UPDATE CASCADE,
  CONSTRAINT `naprawa_czesci_naprawa` FOREIGN KEY (`Naprawa`) REFERENCES `naprawa` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `naprawa_czesc`
--

LOCK TABLES `naprawa_czesc` WRITE;
/*!40000 ALTER TABLE `naprawa_czesc` DISABLE KEYS */;
/*!40000 ALTER TABLE `naprawa_czesc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `naprawa_pracownik`
--

DROP TABLE IF EXISTS `naprawa_pracownik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `naprawa_pracownik` (
  `ID` int(11) NOT NULL,
  `Naprawa` int(11) NOT NULL,
  `Pracownik` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `naprawa_pracownik_naprawa` FOREIGN KEY (`Naprawa`) REFERENCES `naprawa` (`ID`) ON UPDATE CASCADE,
  CONSTRAINT `naprawa_pracownik_pracownik` FOREIGN KEY (`Pracownik`) REFERENCES `pracownik` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `naprawa_pracownik`
--

LOCK TABLES `naprawa_pracownik` WRITE;
/*!40000 ALTER TABLE `naprawa_pracownik` DISABLE KEYS */;
/*!40000 ALTER TABLE `naprawa_pracownik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pracownik`
--

DROP TABLE IF EXISTS `pracownik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pracownik` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(100) NOT NULL,
  `Haslo` varchar(100) NOT NULL,
  `Imie` varchar(100) NOT NULL,
  `Nazwisko` varchar(100) NOT NULL,
  `Miejscowosc` varchar(100) NOT NULL,
  `Adres` varchar(100) NOT NULL,
  `Telefon` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Specjalizacja` varchar(100) NOT NULL,
  `Status` varchar(100) NOT NULL,
  `Wynagrodzenie` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pracownik`
--

LOCK TABLES `pracownik` WRITE;
/*!40000 ALTER TABLE `pracownik` DISABLE KEYS */;
INSERT INTO `pracownik` (`ID`, `Login`, `Haslo`, `Imie`, `Nazwisko`, `Miejscowosc`, `Adres`, `Telefon`, `Email`, `Specjalizacja`, `Status`, `Wynagrodzenie`) VALUES (1,'Janusz','123456','Janusz','Nosacz','Rzeszów','ul. Podwi?ocze 1','123456789','janusz@gmail.com','Kierownik','Zatrudniony',5000),(2,'Gra?yna','brajanek2010','Gra?yna','Nosacz','Rzeszów','ul. Podwis?ocze 1','987456321','grazyna@gmail.com','Recepcja','Zatrudnony',2000),(3,'Heniek','kochamgrazynke','Henryk','Kowalski','Kraków','ul. Partyzantów 4','111222333','heniek@gmail.com','Mechanik','Zatrudnoiny',3000),(4,'Tadeusz','qwerty','Tadeusz','Nowak','Mielec','ul. Grunwaldzka 10','741852963','tadek@gmail.com','Administrator','Zatrudniony',4000);
/*!40000 ALTER TABLE `pracownik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `samochod`
--

DROP TABLE IF EXISTS `samochod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `samochod` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Klient` int(11) NOT NULL,
  `VIN` varchar(17) NOT NULL,
  `Producent` varchar(100) NOT NULL,
  `Model` varchar(100) NOT NULL,
  `Typ` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `samochod_klient` FOREIGN KEY (`Klient`) REFERENCES `klient` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `samochod`
--

LOCK TABLES `samochod` WRITE;
/*!40000 ALTER TABLE `samochod` DISABLE KEYS */;
/*!40000 ALTER TABLE `samochod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zamowienie`
--

DROP TABLE IF EXISTS `zamowienie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zamowienie` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Czesc` int(11) NOT NULL,
  `Zamawiajacy` int(11) NOT NULL,
  `Ilosc` int(11) NOT NULL,
  `Koszt` int(11) NOT NULL,
  `Data_zamowienia` datetime NOT NULL,
  `Status` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `zamawiajacy` FOREIGN KEY (`Zamawiajacy`) REFERENCES `pracownik` (`ID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zamowienie`
--

LOCK TABLES `zamowienie` WRITE;
/*!40000 ALTER TABLE `zamowienie` DISABLE KEYS */;
/*!40000 ALTER TABLE `zamowienie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'warsztat'
--

--
-- Dumping routines for database 'warsztat'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-16 19:37:19
