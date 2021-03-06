-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 18 Mar 2018, 19:15
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
-- Baza danych: `warsztat`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `czesci`
--

CREATE TABLE `czesci` (
  `id_czesci` int(11) NOT NULL,
  `nazwa_cz` varchar(50) NOT NULL,
  `producent_cz` varchar(20) NOT NULL,
  `cena_cz` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klient`
--

CREATE TABLE `klient` (
  `id_klienta` int(11) NOT NULL,
  `kod_pocztowy` varchar(10) DEFAULT NULL,
  `imie_k` varchar(20) NOT NULL,
  `nazwisko_k` varchar(20) NOT NULL,
  `miejscowosc_k` varchar(20) NOT NULL,
  `adres_k` varchar(20) NOT NULL,
  `telefon` varchar(20) DEFAULT NULL,
  `e_mail` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `naprawa`
--

CREATE TABLE `naprawa` (
  `id_naprawy` int(11) NOT NULL,
  `data_przyjecia` varchar(20) DEFAULT NULL,
  `data_oddania` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `gwarancja` varchar(20) DEFAULT NULL,
  `cena` varchar(20) DEFAULT NULL,
  `opis` varchar(50) DEFAULT NULL,
  `id_pojazdu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `naprawaczesci`
--

CREATE TABLE `naprawaczesci` (
  `id_naprawy` int(11) NOT NULL,
  `id_czesci` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pojazd`
--

CREATE TABLE `pojazd` (
  `id_pojazdu` int(11) NOT NULL,
  `nr_vin` varchar(20) DEFAULT NULL,
  `producent` varchar(20) DEFAULT NULL,
  `model` varchar(20) DEFAULT NULL,
  `typ` varchar(20) DEFAULT NULL,
  `id_klienta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownik`
--

CREATE TABLE `pracownik` (
  `id_pracownika` int(11) NOT NULL,
  `imie_p` varchar(20) NOT NULL,
  `nazwisko_p` varchar(20) NOT NULL,
  `specjalizacja` varchar(20) NOT NULL,
  `login` varchar(20) NOT NULL,
  `haslo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracowniknaprawa`
--

CREATE TABLE `pracowniknaprawa` (
  `id_pracownika` int(11) NOT NULL,
  `id_naprawy` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `czesci`
--
ALTER TABLE `czesci`
  ADD PRIMARY KEY (`id_czesci`);

--
-- Indeksy dla tabeli `klient`
--
ALTER TABLE `klient`
  ADD PRIMARY KEY (`id_klienta`);

--
-- Indeksy dla tabeli `naprawa`
--
ALTER TABLE `naprawa`
  ADD PRIMARY KEY (`id_naprawy`),
  ADD KEY `id_pojazdu` (`id_pojazdu`);

--
-- Indeksy dla tabeli `naprawaczesci`
--
ALTER TABLE `naprawaczesci`
  ADD KEY `id_czesci` (`id_czesci`),
  ADD KEY `id_naprawy` (`id_naprawy`);

--
-- Indeksy dla tabeli `pojazd`
--
ALTER TABLE `pojazd`
  ADD PRIMARY KEY (`id_pojazdu`),
  ADD KEY `id_klienta` (`id_klienta`);

--
-- Indeksy dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  ADD PRIMARY KEY (`id_pracownika`);

--
-- Indeksy dla tabeli `pracowniknaprawa`
--
ALTER TABLE `pracowniknaprawa`
  ADD KEY `id_naprawy` (`id_naprawy`),
  ADD KEY `id_pracownika` (`id_pracownika`);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `naprawa`
--
ALTER TABLE `naprawa`
  ADD CONSTRAINT `naprawa_ibfk_1` FOREIGN KEY (`id_pojazdu`) REFERENCES `pojazd` (`id_pojazdu`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `naprawaczesci`
--
ALTER TABLE `naprawaczesci`
  ADD CONSTRAINT `naprawaczesci_ibfk_1` FOREIGN KEY (`id_czesci`) REFERENCES `czesci` (`id_czesci`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `naprawaczesci_ibfk_2` FOREIGN KEY (`id_naprawy`) REFERENCES `naprawa` (`id_naprawy`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `pojazd`
--
ALTER TABLE `pojazd`
  ADD CONSTRAINT `pojazd_ibfk_1` FOREIGN KEY (`id_klienta`) REFERENCES `klient` (`id_klienta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `pracowniknaprawa`
--
ALTER TABLE `pracowniknaprawa`
  ADD CONSTRAINT `pracowniknaprawa_ibfk_1` FOREIGN KEY (`id_naprawy`) REFERENCES `naprawa` (`id_naprawy`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pracowniknaprawa_ibfk_2` FOREIGN KEY (`id_pracownika`) REFERENCES `pracownik` (`id_pracownika`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
