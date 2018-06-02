-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 01 Cze 2018, 20:17
-- Wersja serwera: 10.1.32-MariaDB
-- Wersja PHP: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `warsztat`
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

--
-- Zrzut danych tabeli `czesc`
--

INSERT INTO `czesc` (`CzescID`, `Nazwa`, `Producent`, `Ilosc`, `Cena`) VALUES
(1, 'sruba', 'xd', 10, 5);

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

--
-- Zrzut danych tabeli `klient`
--

INSERT INTO `klient` (`KlientId`, `Imie`, `Nazwisko`, `Adres`, `Miejscowosc`, `Telefon`, `Email`) VALUES
(1, 'a', 'a', 'a', 'a', 'a', 'a'),
(2, 'w', 'w', 'a', 'a', '123', '2'),
(3, 'w', 'w', 'r', '4', 'w', 'r'),
(4, 'q', 'q', 'q', 'q', 'q', 'q'),
(5, 'c', 'c', 'c', 'c', 'c', 'c'),
(6, 't', 't', 't', 'tt', 't', 't'),
(7, 'm', 'm', 'm', 'm', 'm', 'm'),
(8, 'k', 'k', 'k', 'k', 'k', 'k'),
(9, 'w', 'w', 'w', 'ww', 'ww', 'w');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `naprawa`
--

CREATE TABLE `naprawa` (
  `NaprawaId` int(11) NOT NULL,
  `Klient` int(11) NOT NULL,
  `Samochod` int(11) NOT NULL,
  `Data_rozpoczecia` datetime NOT NULL,
  `Data_zakonczenia` datetime DEFAULT NULL,
  `Koszt` float DEFAULT NULL,
  `Opis` text NOT NULL,
  `Status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `naprawa`
--

INSERT INTO `naprawa` (`NaprawaId`, `Klient`, `Samochod`, `Data_rozpoczecia`, `Data_zakonczenia`, `Koszt`, `Opis`, `Status`) VALUES
(1, 8, 8, '2018-05-29 21:11:46', '2018-05-29 21:11:46', 10, '', ''),
(2, 9, 9, '2018-06-01 19:23:51', '2018-06-01 19:23:51', 0, '', '');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `naprawa_czesc`
--

CREATE TABLE `naprawa_czesc` (
  `ID` int(11) NOT NULL,
  `Naprawa` int(11) NOT NULL,
  `Czesc` int(11) NOT NULL,
  `Ilosc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `naprawa_pracownik`
--

CREATE TABLE `naprawa_pracownik` (
  `ID` int(11) NOT NULL,
  `Naprawa` int(11) NOT NULL,
  `Pracownik` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `naprawa_pracownik`
--

INSERT INTO `naprawa_pracownik` (`ID`, `Naprawa`, `Pracownik`) VALUES
(1, 1, 1),
(2, 1, 5),
(3, 1, 2),
(4, 2, 2);

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

--
-- Zrzut danych tabeli `pracownik`
--

INSERT INTO `pracownik` (`PracownikId`, `Login`, `Haslo`, `Imie`, `Nazwisko`, `Miejscowosc`, `Adres`, `Telefon`, `Email`, `Specjalizacja`, `Status`, `Wynagrodzenie`) VALUES
(1, 'asd', 'asd', 'asda', 'sdasd', 'asdasd', 'asd', 'asd', 'asda', 'asd', 'Status', NULL),
(2, 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'aa', 'Status', NULL),
(3, 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'Kierownik', 'Status', NULL),
(4, 'z', 'z', 'z', 'z', 'z', 'z', 'z', 'z', 'Administrator', 'Status', NULL),
(5, 'admin', 'admin', '', '', '', '', '', '', '', 'Status', NULL),
(7, 'c', 'c', '', '', '', '', '', '', 'administrator', 'Status', NULL),
(8, 'r', 'r', 'r', 'r', 'r', 'rr', '23', 'r', 'Diagnosta', 'Urlop', 12),
(9, 'w', 'w', 'w', 'ww', 'w', 'w', '123', 'w', 'Mechanik', 'Zatrudniony', 14),
(10, 'y', 'y', 'y', 'y', 'y', 'y', '123', 'y', 'Mechanik', 'Urlop', 15);

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
-- Zrzut danych tabeli `samochod`
--

INSERT INTO `samochod` (`SamochodId`, `Klient`, `VIN`, `Producent`, `Model`, `Typ`) VALUES
(1, 1, 'a', 'a', 'a', 'a'),
(2, 2, '12312', 'mercedes', 'klasa a', 'hatchback'),
(3, 3, '3', 'mazda', '3', 'spor'),
(4, 4, 'q', 'q', 'q', 'q'),
(5, 5, 'cc', 'c', 'c', 'c'),
(6, 6, 't', 't', 't', 't'),
(7, 7, 'm', 'm', 'm', 'm'),
(8, 8, 'k', 'k', 'k', 'k'),
(9, 9, 'w', 'ww', 'w', 'w');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienie`
--

CREATE TABLE `zamowienie` (
  `ID` int(11) NOT NULL,
  `Czesc` int(11) NOT NULL,
  `Zamawiajacy` int(11) NOT NULL,
  `Ilosc` int(11) NOT NULL,
  `Koszt` float NOT NULL,
  `Data_zamowienia` datetime NOT NULL,
  `Status` varchar(100) NOT NULL
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
  ADD PRIMARY KEY (`NaprawaId`),
  ADD KEY `naprawa_klient` (`Klient`),
  ADD KEY `naprawa_samochod` (`Samochod`);

--
-- Indeksy dla tabeli `naprawa_czesc`
--
ALTER TABLE `naprawa_czesc`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `naprawa_czesc_czesc` (`Czesc`),
  ADD KEY `naprawa_czesci_naprawa` (`Naprawa`);

--
-- Indeksy dla tabeli `naprawa_pracownik`
--
ALTER TABLE `naprawa_pracownik`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `naprawa_pracownik_naprawa` (`Naprawa`),
  ADD KEY `naprawa_pracownik_pracownik` (`Pracownik`);

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
-- Indeksy dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `zamawiajacy` (`Zamawiajacy`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `czesc`
--
ALTER TABLE `czesc`
  MODIFY `CzescID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `klient`
--
ALTER TABLE `klient`
  MODIFY `KlientId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `naprawa`
--
ALTER TABLE `naprawa`
  MODIFY `NaprawaId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `naprawa_czesc`
--
ALTER TABLE `naprawa_czesc`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `naprawa_pracownik`
--
ALTER TABLE `naprawa_pracownik`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
-- AUTO_INCREMENT dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `naprawa`
--
ALTER TABLE `naprawa`
  ADD CONSTRAINT `naprawa_klient` FOREIGN KEY (`Klient`) REFERENCES `klient` (`KlientId`) ON UPDATE CASCADE,
  ADD CONSTRAINT `naprawa_samochod` FOREIGN KEY (`Samochod`) REFERENCES `samochod` (`SamochodId`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `naprawa_czesc`
--
ALTER TABLE `naprawa_czesc`
  ADD CONSTRAINT `naprawa_czesc_czesc` FOREIGN KEY (`Czesc`) REFERENCES `czesc` (`CzescID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `naprawa_czesci_naprawa` FOREIGN KEY (`Naprawa`) REFERENCES `naprawa` (`NaprawaId`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `naprawa_pracownik`
--
ALTER TABLE `naprawa_pracownik`
  ADD CONSTRAINT `naprawa_pracownik_naprawa` FOREIGN KEY (`Naprawa`) REFERENCES `naprawa` (`NaprawaId`) ON UPDATE CASCADE,
  ADD CONSTRAINT `naprawa_pracownik_pracownik` FOREIGN KEY (`Pracownik`) REFERENCES `pracownik` (`PracownikId`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `samochod`
--
ALTER TABLE `samochod`
  ADD CONSTRAINT `samochod_klient` FOREIGN KEY (`Klient`) REFERENCES `klient` (`KlientId`) ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `zamowienie`
--
ALTER TABLE `zamowienie`
  ADD CONSTRAINT `zamawiajacy` FOREIGN KEY (`Zamawiajacy`) REFERENCES `pracownik` (`PracownikId`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
