-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 05 Cze 2018, 21:34
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
  `napraw_id` int(11) NOT NULL,
  `data_rozpoczecia` date NOT NULL,
  `data_zakonczenia` date DEFAULT NULL,
  `koszt` int(11) DEFAULT NULL,
  `status` varchar(100) NOT NULL,
  `opis` varchar(100) NOT NULL,
  `id_klienta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `naprawa`
--

INSERT INTO `naprawa` (`napraw_id`, `data_rozpoczecia`, `data_zakonczenia`, `koszt`, `status`, `opis`, `id_klienta`) VALUES
(1, '2018-06-04', '2018-06-04', 100, '123', '4564564', 4),
(2, '2018-06-05', '2018-06-05', 1000, 'Przyjete', 'eqdasda', 1),
(3, '2018-06-05', '2018-06-05', 1000, 'W trakcie', 'Wymiana b', 1),
(4, '2018-06-05', '2018-06-14', 100, 'Przyjete', 'dasdad', 6),
(5, '2018-06-05', '2018-06-14', 100, 'Przyjete', 'dasdad', 6),
(6, '2018-06-05', '2018-06-28', 123, 'W trakcie', 'eadadzcz', 3),
(7, '2018-06-05', '2018-06-29', 2312, 'W trakcie', 'dasdasd', 6),
(8, '2018-06-05', '2018-06-22', 231, 'W trakcie', 'dadad', 7),
(9, '2018-06-05', '2018-06-21', 231, 'Przyjete', 'dasdadz', 6),
(10, '2018-06-05', '2018-06-21', 213, 'Przyjete', 'dasdasda', 7),
(11, '2018-06-05', '2018-06-22', 2313, 'W trakcie', 'dadadadaczczx', 9),
(12, '2018-06-05', '2018-06-22', 231, 'Przyjete', 'asdadqowjrpogksd', 8),
(13, '2018-06-05', '2018-06-14', 321, 'Przyjete', 'dsadafgxxvxv', 8),
(14, '2018-06-05', '2018-06-22', 231, 'Przyjete', 'davcxnfhf', 7),
(15, '2018-06-05', '2018-06-13', 3213, 'Przyjete', 'davxvxvx', 5),
(16, '2018-06-05', '2018-06-16', 231, 'Przyjete', 'sfsoiwriosopfkl', 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `naprawa_czesci`
--

CREATE TABLE `naprawa_czesci` (
  `id` int(11) NOT NULL,
  `id_czesci` int(11) NOT NULL,
  `id_naprawy` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `naprawa_czesci`
--

INSERT INTO `naprawa_czesci` (`id`, `id_czesci`, `id_naprawy`) VALUES
(1, 1, 1),
(2, 1, 7),
(3, 1, 7),
(4, 1, 7),
(5, 1, 8),
(6, 1, 8),
(7, 1, 8),
(8, 1, 9),
(9, 1, 9),
(10, 1, 10),
(11, 1, 10),
(12, 1, 10),
(13, 1, 11),
(14, 1, 11),
(15, 1, 11),
(16, 1, 12),
(17, 1, 12),
(18, 1, 13),
(19, 1, 13),
(20, 1, 14),
(21, 1, 14),
(22, 1, 15),
(23, 1, 16),
(24, 1, 16);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `naprawa_pracownika`
--

CREATE TABLE `naprawa_pracownika` (
  `id` int(11) NOT NULL,
  `id_pracownika` int(11) NOT NULL,
  `id_naprawy` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `naprawa_pracownika`
--

INSERT INTO `naprawa_pracownika` (`id`, `id_pracownika`, `id_naprawy`) VALUES
(1, 3, 10),
(2, 8, 10),
(3, 10, 11),
(4, 10, 11),
(5, 9, 12),
(6, 9, 13),
(7, 10, 14),
(8, 9, 15),
(9, 10, 16),
(10, 2, 16);

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
  MODIFY `napraw_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT dla tabeli `naprawa_czesci`
--
ALTER TABLE `naprawa_czesci`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT dla tabeli `naprawa_pracownika`
--
ALTER TABLE `naprawa_pracownika`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

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
  ADD CONSTRAINT `naprawa_pracownika_id_naprawy_fk` FOREIGN KEY (`id_naprawy`) REFERENCES `naprawa` (`napraw_id`),
  ADD CONSTRAINT `naprawa_pracownika_id_pracownika_fk` FOREIGN KEY (`id_pracownika`) REFERENCES `pracownik` (`PracownikId`);

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
