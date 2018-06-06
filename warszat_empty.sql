-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 06 Cze 2018, 09:50
-- Wersja serwera: 10.1.31-MariaDB
-- Wersja PHP: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `warszat_empty`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `czesc`
--

CREATE TABLE `czesc` (
  `CzescID` int(11) NOT NULL,
  `Nazwa` varchar(100) NOT NULL,
  `Producent` varchar(100) NOT NULL,
  `Ilosc` int(11) NOT NULL,
  `Cena` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient`
--

CREATE TABLE `klient` (
  `KlientId` int(11) NOT NULL,
  `Imie` varchar(100) NOT NULL,
  `Nazwisko` varchar(100) NOT NULL,
  `Adres` varchar(100) DEFAULT NULL,
  `Miejscowosc` varchar(100) DEFAULT NULL,
  `Telefon` varchar(100) NOT NULL,
  `Email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `naprawa`
--

CREATE TABLE `naprawa` (
  `napraw_id` int(11) NOT NULL,
  `data_rozpoczecia` date NOT NULL,
  `data_zakonczenia` date DEFAULT NULL,
  `koszt` int(11) DEFAULT NULL,
  `status` varchar(100) NOT NULL,
  `opis` varchar(100) NOT NULL,
  `id_klienta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `naprawa_czesci`
--

CREATE TABLE `naprawa_czesci` (
  `id` int(11) NOT NULL,
  `id_czesci` int(11) NOT NULL,
  `id_naprawy` int(11) NOT NULL,
  `ilosc` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `naprawa_pracownika`
--

CREATE TABLE `naprawa_pracownika` (
  `id` int(11) NOT NULL,
  `id_pracownika` int(11) NOT NULL,
  `id_naprawy` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownik`
--

CREATE TABLE `pracownik` (
  `PracownikId` int(11) NOT NULL,
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
  `Wynagrodzenie` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `samochod`
--

CREATE TABLE `samochod` (
  `SamochodId` int(11) NOT NULL,
  `Klient` int(11) NOT NULL,
  `VIN` varchar(17) NOT NULL,
  `Producent` varchar(100) NOT NULL,
  `Model` varchar(100) NOT NULL,
  `Typ` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `czesc`
--
ALTER TABLE `czesc`
  ADD PRIMARY KEY (`CzescID`);

--
-- Indeksy dla tabeli `klient`
--
ALTER TABLE `klient`
  ADD PRIMARY KEY (`KlientId`);

--
-- Indeksy dla tabeli `naprawa`
--
ALTER TABLE `naprawa`
  ADD PRIMARY KEY (`napraw_id`),
  ADD KEY `naprawa_id_klienta_fk` (`id_klienta`);

--
-- Indeksy dla tabeli `naprawa_czesci`
--
ALTER TABLE `naprawa_czesci`
  ADD PRIMARY KEY (`id`),
  ADD KEY `naprawa_czesci_id_czesci_fk` (`id_czesci`),
  ADD KEY `naprawa_czesci_id_naprawy_fk` (`id_naprawy`);

--
-- Indeksy dla tabeli `naprawa_pracownika`
--
ALTER TABLE `naprawa_pracownika`
  ADD PRIMARY KEY (`id`),
  ADD KEY `naprawa_pracownika_id_pracownika_fk` (`id_pracownika`),
  ADD KEY `naprawa_pracownika_id_naprawy_fk` (`id_naprawy`);

--
-- Indeksy dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  ADD PRIMARY KEY (`PracownikId`),
  ADD UNIQUE KEY `Login_UNIQUE` (`Login`);

--
-- Indeksy dla tabeli `samochod`
--
ALTER TABLE `samochod`
  ADD PRIMARY KEY (`SamochodId`),
  ADD KEY `samochod_klient` (`Klient`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `czesc`
--
ALTER TABLE `czesc`
  MODIFY `CzescID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `klient`
--
ALTER TABLE `klient`
  MODIFY `KlientId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `naprawa`
--
ALTER TABLE `naprawa`
  MODIFY `napraw_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT dla tabeli `naprawa_czesci`
--
ALTER TABLE `naprawa_czesci`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT dla tabeli `naprawa_pracownika`
--
ALTER TABLE `naprawa_pracownika`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  MODIFY `PracownikId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT dla tabeli `samochod`
--
ALTER TABLE `samochod`
  MODIFY `SamochodId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `naprawa`
--
ALTER TABLE `naprawa`
  ADD CONSTRAINT `naprawa_id_klienta_fk` FOREIGN KEY (`id_klienta`) REFERENCES `klient` (`KlientId`);

--
-- Ograniczenia dla tabeli `naprawa_czesci`
--
ALTER TABLE `naprawa_czesci`
  ADD CONSTRAINT `naprawa_czesci_id_czesci_fk` FOREIGN KEY (`id_czesci`) REFERENCES `czesc` (`CzescID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `naprawa_czesci_id_naprawy_fk` FOREIGN KEY (`id_naprawy`) REFERENCES `naprawa` (`napraw_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `naprawa_pracownika`
--
ALTER TABLE `naprawa_pracownika`
  ADD CONSTRAINT `naprawa_pracownika_id_naprawy_fk` FOREIGN KEY (`id_naprawy`) REFERENCES `naprawa` (`napraw_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `naprawa_pracownika_id_pracownika_fk` FOREIGN KEY (`id_pracownika`) REFERENCES `pracownik` (`PracownikId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `samochod`
--
ALTER TABLE `samochod`
  ADD CONSTRAINT `samochod_klient` FOREIGN KEY (`Klient`) REFERENCES `klient` (`KlientId`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
