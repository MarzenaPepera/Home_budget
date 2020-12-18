-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 09 Gru 2020, 22:49
-- Wersja serwera: 10.4.11-MariaDB
-- Wersja PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `home_budget`
--
CREATE DATABASE IF NOT EXISTS `home_budget` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `home_budget`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `plan`
--

CREATE TABLE `plan` (
  `id_plan` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `amount` double NOT NULL,
  `date` date NOT NULL,
  `description` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `plan`
--

INSERT INTO `plan` (`id_plan`, `id_user`, `amount`, `date`, `description`) VALUES
(1, 1, 200, '2020-12-31', 'Zakupy'),
(2, 1, 60, '2020-12-31', 'Przejazdy');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `role`
--

CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `role`
--

INSERT INTO `role` (`id_role`, `role`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_CONTROLLER');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `transaction`
--

CREATE TABLE `transaction` (
  `id_transaction` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `amount` double NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `transaction`
--

INSERT INTO `transaction` (`id_transaction`, `id_user`, `amount`, `description`, `date`) VALUES
(1, 12, -12, 'Kebap', '2020-11-30 00:00:00'),
(2, 34, 100, 'Od babci', '2020-11-30 00:00:00'),
(3, 1, 333, 'Wyp?ata+', '2020-12-03 06:06:00'),
(4, 1, 34.3456, 'sadgsdgf', '2020-11-30 06:37:00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) NOT NULL DEFAULT 'unique',
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telephone` int(9) NOT NULL,
  `mark` int(11) NOT NULL,
  `is_fired` tinyint(1) NOT NULL,
  `is_new` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id_user`, `active`, `email`, `last_name`, `name`, `password`, `telephone`, `mark`, `is_fired`, `is_new`) VALUES
(1, 1, 'rafalmiskiewicz@gmail.com', 'Miskiewicz', 'Rafał', '$2a$10$mCFc/hUUhJGNAmHC83JceeSQJgms8qVnYeA0QfMLxi5CpDsw0cMom', 0, 0, 0, 0),
(3, 1, '123@gmail.com', 'Miskiewicz', 'Ewa', '$2a$10$mCFc/hUUhJGNAmHC83JceeSQJgms8qVnYeA0QfMLxi5CpDsw0cMom', 794286054, 0, 0, 1),
(4, 1, 'AlaNowak@gmail.com', 'Nowak', 'Ala', '$2a$10$P5VlG1UsmdP66xR9LDHANOeKxlZGC6zUHk3PA4GrJp6jQ21kB1Zvq', 123456789, 50, 1, 1),
(5, 1, 'ada.baranowska@gmail.com', 'Baranowska', 'Ada', '$2a$10$w42We91Ae8lmrIXytj88kOuH6gTpzounbtXZMjsfd5shMPiFmzWTm', 439229048, 50, 0, 1),
(6, 1, 'zbigniew.millarski@gmail.com', 'Millarski', 'Zbigniew', '$2a$10$FPf8KnJ9muQXLtKf6IcymeSac9c7S8JCYGZF5fM5P4a8KT8DGlUXi', 858599332, 50, 0, 1),
(7, 1, 'damian.lichota@gmail.com', 'Lichota', 'Damian', '$2a$10$mCFc/hUUhJGNAmHC83JceeSQJgms8qVnYeA0QfMLxi5CpDsw0cMom', 399020094, 50, 1, 1),
(8, 1, 'pawel.piskorz@gmail.com', 'Piskorz', 'Pawel', '$2a$10$zwbHDpzQCtCHIrclfhaAc.6BghXmW5y.N3ue.2Br8qcevBZYYODA6', 729473098, 50, 0, 1),
(9, 0, 'mania.guz@gmail.com', 'Guz', 'Mania', '$2a$10$fa6pMvAPIazO39m254PJeebR5uPHwlJ6lT53pkI40CIAaItyetXgC', 938402933, 50, 0, 1),
(10, 1, 'pawel.midura@gmail.com', 'Midura', 'Pawel', '$2a$10$uBBQVuTSLhDm/TjkkYqFoOhFFrN0rqGa5b483vPEguZmhoGrrP97q', 847393849, 50, 0, 1),
(11, 1, 'pawel.grabczynski@gmail.com', 'Grabczynski', 'Pawel', '$2a$10$3Zq4sFS51hh4ZTkLCM8ZOu8aL9/qMO7jQntwXlwvKVQTH1f3htkbG', 947495293, 50, 0, 1),
(12, 1, 'wojtek.smieszny@gmail.com', 'Maludzinski', 'Wojtek', '$2a$10$Sv.ie.pUcwPDQg0Wly3LB.otbJNYgnAPSuNQ6dB791OMcCj0A4yUO', 84930299, 50, 1, 1),
(14, 1, 'hellmood0749@gmail.com', 'Miskiewicz', 'Rafal', '$2a$10$a/fJ.FPLsdX9aNXN0Bgocexb.i5pSqBiSPaQ3pInrEwDtXBchslRq', 794286054, 50, 0, 1),
(16, 1, 'marzena.pepera@gmail.com', 'Pepera', 'Marzena', '$2a$10$ZLh02Of0nd4d8FEDee3Vy.51RIUJHTlzL7EctiOP.YycAzSiiDPbG', 513423068, 50, 0, 1),
(17, 1, 'marzena.pepera1@gmail.com', 'Pepera', 'Marzena', '$2a$10$YEZC3pt07EPSQD/xR0RsWORWILpKwG.6SohmaONm6kxZyE.KeSUk6', 513423068, 50, 0, 1),
(31, 0, 'kg@gmail.com', 'Gonciarz', 'Krzysztof', '$2a$10$KbElHJD2KOMMhETyec52ROGPmKlnYHcA7MHIeSfRLE9rLRxLgniwW', 123654780, 50, 0, 1),
(32, 1, 'szpilkaka@gmail.com', 'Szpila', 'Katarzyna', '$2a$10$uwNMzDMLjlZfDBQ8VupSDOBJXaW1MyiipYsTwODNBMK75iR.ry6uO', 662138123, 50, 0, 1),
(33, 1, 'marzena1.pepera@gmail.com', 'Pepera', 'Marzena', '$2a$10$.KYO4p7zg0URMfJbE8ioLeWij1MEi2ebSmlV1vlNlHyVNErVKLIvC', 51342306, 50, 0, 1),
(34, 1, 'karol.patko@gmail.com', 'Patko', 'Karol', '$2a$10$TZ7FzC1hFULvvm7aNWjO0eT.RF9nNWrS8LmlOpH/APWF9RHyLnuJW', 432568345, 50, 1, 1),
(35, 1, 'ola.kuc@gmail.com', 'Kuc', 'Aleksandra', '$2a$10$uVC4fLosmsRT51.dhw4ot.zkpcXU6FIb/RlJnO29QWzeO4/KO0tPy', 12343212, 50, 0, 1),
(36, 1, 'ola.bojes@gmail.com', 'Bojes', 'Aleksandra', '$2a$10$eKMIjWgfxCs3Xa6DcJwRVucNpH71NXL.Pf8BIBvSwKwcA2UIPrZu.', 847392847, 50, 0, 1),
(38, 1, 'szymon.oleksy@gmail.com', 'Oleksy', 'Szymon', '$2a$10$0OWfI5yX0cj12nIa1oJXnOa2gMMfJP6QdRKqHmIs9Etyw7ySIHJyS', 48529234, 50, 0, 1),
(39, 1, 'szymon.kurowski@gmail.com', 'Kurowski', 'Szymon', '$2a$10$0W7YrSitDfi5nu1wGS1LUOSguzW5K3C9TdaRy41UB0N8VfUdO9mGS', 543849274, 50, 0, 1);

--
-- Struktura tabeli dla tabeli `user_role`
--

CREATE TABLE `user_role` (
  `id_user` int(11) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `user_role`
--

INSERT INTO `user_role` (`id_user`, `id_role`) VALUES
(1, 1),
(1, 2),
(1, 3),
(3, 2),
(4, 3),
(5, 2),
(6, 1),
(7, 2),
(8, 2),
(9, 2),
(10, 3),
(11, 2),
(12, 3),
(14, 2),
(16, 1),
(17, 2),
(31, 1),
(32, 2),
(33, 2),
(34, 3),
(35, 2),
(36, 2),
(38, 2),
(39, 2);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`id_plan`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeksy dla tabeli `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Indeksy dla tabeli `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id_transaction`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indeksy dla tabeli `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id_user`,`id_role`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`id_role`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `plan`
--
ALTER TABLE `plan`
  MODIFY `id_plan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `role`
--
ALTER TABLE `role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id_transaction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `plan`
--
ALTER TABLE `plan`
  ADD CONSTRAINT `plan_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Ograniczenia dla tabeli `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
