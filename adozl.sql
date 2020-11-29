-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 10 Cze 2020, 16:10
-- Wersja serwera: 10.1.19-MariaDB
-- Wersja PHP: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `adozl`
--
CREATE DATABASE IF NOT EXISTS `adozl` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `adozl`;

DELIMITER $$
--
-- Procedury
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `accept_hours_to_schedule` (`h_id_hours` INT(11), `p_id_places` INT(11))  BEGIN
	SET @user = (SELECT id_user FROM hours WHERE id_hours=h_id_hours);
	SET @role = (SELECT id_role FROM user_role WHERE id_user=@user);
	SET @s_hour_from = (SELECT hour_from FROM hours WHERE id_hours=h_id_hours);
	SET @s_hour_to = (SELECT hour_to FROM hours WHERE id_hours=h_id_hours);
	INSERT INTO schedule(id_user,id_role,id_places,hour_from,hour_to)
	VALUES (@user,@role,p_id_places,@s_hour_from,@s_hour_to);
 END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `hours_by_day` (`day` DATE)  BEGIN
	SELECT * FROM hours WHERE hours.hour_from >= CONCAT(day, ' 00:00:00') 
	AND hours.hour_to <= CONCAT(day, ' 23:59:59');
 END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `save_user_hours` (`u_id_user` INT(11), `h_hour_from` DATETIME, `h_hour_to` DATETIME)  BEGIN
	INSERT INTO hours(id_user,hour_from, hour_to)
	VALUES (u_id_user,h_hour_from, h_hour_to);
 END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_all_user_hours` (`u_id_user` INT(11))  BEGIN
	SELECT * FROM hours WHERE hours.id_user=u_id_user;
 END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `show_user_day` (`u_id_user` INT(11), `h_date` DATE)  BEGIN
	SELECT hours.* FROM hours WHERE hours.id_user=u_id_user 
	AND hours.hour_from>=CONCAT(day, ' 00:00:00') 
	AND hours.hour_to<=CONCAT(day, ' 23:59:59');
 END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `hours`
--

CREATE TABLE `hours` (
  `id_hours` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `hour_from` datetime NOT NULL,
  `hour_to` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `hours`
--

INSERT INTO `hours` (`id_hours`, `id_user`, `hour_from`, `hour_to`) VALUES
(1, 1, '2020-02-04 00:07:00', '2020-02-04 15:10:00'),
(2, 3, '2020-01-10 07:30:00', '2020-01-10 10:00:00'),
(3, 1, '2020-01-02 00:00:01', '2020-01-04 23:00:02'),
(4, 14, '2020-01-18 00:00:00', '2020-01-19 00:00:00'),
(5, 14, '2020-01-18 00:00:00', '2020-01-19 20:00:00'),
(6, 1, '2020-01-18 00:00:00', '2020-01-18 00:00:09'),
(7, 1, '2020-01-18 00:00:00', '2010-01-18 20:00:00'),
(8, 16, '2020-01-20 15:30:00', '2020-01-20 18:30:00'),
(22, 16, '2020-01-21 15:30:00', '2020-01-21 19:00:00'),
(23, 16, '2020-01-20 07:00:00', '2020-01-25 03:00:00'),
(24, 31, '2010-01-18 07:00:00', '2020-01-18 11:30:00'),
(25, 1, '2020-01-21 08:30:00', '2020-01-21 10:00:00'),
(26, 1, '2020-01-21 10:16:00', '2020-01-21 11:16:00'),
(27, 3, '2020-01-20 12:12:00', '2020-01-20 21:05:00'),
(28, 3, '2020-01-21 10:00:00', '2020-01-21 12:30:00'),
(32, 9, '2020-01-23 15:30:00', '2020-01-23 17:00:00'),
(37, 3, '2020-01-24 12:00:00', '2020-01-24 16:30:00'),
(38, 3, '2020-01-28 07:20:00', '2020-01-28 10:00:00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `places`
--

CREATE TABLE `places` (
  `id_places` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_bin NOT NULL,
  `address_google` varchar(512) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `places`
--

INSERT INTO `places` (`id_places`, `description`, `address_google`) VALUES
(1, 'Skrzyżowanie Szlak z Warszawską', 'https://goo.gl/maps/r9Jwb3D3b4Qe2CRG9'),
(2, 'AGH Czarnowiejska przystanek', 'https://goo.gl/maps/Znmxtbi3cAwXkmHf6'),
(3, 'AGH UR', 'https://goo.gl/maps/iGVszYJ3oP8gZzSS6');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `plan`
--

CREATE TABLE `plan` (
  `id_plan` int(11) NOT NULL,
  `id_places` int(11) NOT NULL,
  `hour_from` datetime NOT NULL,
  `hour_to` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `report`
--

CREATE TABLE `report` (
  `id_schedule` int(11) NOT NULL,
  `id_types_of_reports` int(11) NOT NULL,
  `information` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
-- Struktura tabeli dla tabeli `schedule`
--

CREATE TABLE `schedule` (
  `id_schedule` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_role` int(11) NOT NULL,
  `id_places` int(11) NOT NULL,
  `hour_from` datetime NOT NULL,
  `hour_to` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `schedule`
--

INSERT INTO `schedule` (`id_schedule`, `id_user`, `id_role`, `id_places`, `hour_from`, `hour_to`) VALUES
(1, 1, 2, 1, '2020-01-20 00:00:00', '2020-01-20 10:00:00'),
(2, 3, 2, 1, '2020-01-20 07:30:00', '2020-01-20 09:00:00'),
(4, 3, 2, 1, '2020-01-21 10:00:00', '2020-01-21 12:30:00'),
(5, 3, 2, 2, '2020-01-22 08:15:00', '2020-01-22 10:45:00'),
(6, 3, 2, 3, '2020-01-24 12:00:00', '2020-01-24 16:30:00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `settings`
--

CREATE TABLE `settings` (
  `id_settings` int(11) NOT NULL,
  `time` varchar(8) COLLATE utf8_bin NOT NULL,
  `scale_of_evaluation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `types_of_reports`
--

CREATE TABLE `types_of_reports` (
  `id_types_of_reports` int(11) NOT NULL,
  `types_of_reports` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
(1, 1, 'rafalmiskiewicz@gmail.com', 'Miskiewicz', 'Rafał', '$2a$10$zwbHDpzQCtCHIrclfhaAc.6BghXmW5y.N3ue.2Br8qcevBZYYODA6', 0, 0, 0, 0),
(3, 1, '123@gmail.com', 'Miskiewicz', 'Ewa', '$2a$10$SaJ2PaPCe3/HfXJ/yvJKme9UuE4VtbFWMXEhnRB2grr7PYurvrgSm', 794286054, 0, 0, 1),
(4, 1, 'AlaNowak@gmail.com', 'Nowak', 'Ala', '$2a$10$P5VlG1UsmdP66xR9LDHANOeKxlZGC6zUHk3PA4GrJp6jQ21kB1Zvq', 123456789, 50, 1, 1),
(5, 1, 'ada.baranowska@gmail.com', 'Baranowska', 'Ada', '$2a$10$w42We91Ae8lmrIXytj88kOuH6gTpzounbtXZMjsfd5shMPiFmzWTm', 439229048, 50, 0, 1),
(6, 1, 'zbigniew.millarski@gmail.com', 'Millarski', 'Zbigniew', '$2a$10$FPf8KnJ9muQXLtKf6IcymeSac9c7S8JCYGZF5fM5P4a8KT8DGlUXi', 858599332, 50, 0, 1),
(7, 1, 'damian.lichota@gmail.com', 'Lichota', 'Damian', '$2a$10$WKZBa7Wbjot6p7jUo/ln/uSLXk6P7B9MMtHIez1yNKWvR5Ci9E6g.', 399020094, 50, 1, 1),
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
-- Wyzwalacze `user`
--
DELIMITER $$
CREATE TRIGGER `create_user` AFTER INSERT ON `user` FOR EACH ROW BEGIN
 
	set @role=(SELECT id_role FROM role WHERE role="user");
    set @u_user=new.id_user;
	INSERT INTO user_role(id_user,id_role)
	VALUES (@u_user,@role);
   END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `delete_fired_user_hours` BEFORE UPDATE ON `user` FOR EACH ROW DELETE FROM hours WHERE hours.id_user=old.id_user 
  AND old.is_fired='1' AND hours.hour_from>=CURRENT_DATE()
$$
DELIMITER ;

-- --------------------------------------------------------

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
(3, 2),
(4, 3),
(5, 2),
(6, 1),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
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
-- Indexes for table `hours`
--
ALTER TABLE `hours`
  ADD PRIMARY KEY (`id_hours`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `places`
--
ALTER TABLE `places`
  ADD PRIMARY KEY (`id_places`);

--
-- Indexes for table `plan`
--
ALTER TABLE `plan`
  ADD PRIMARY KEY (`id_plan`),
  ADD KEY `plan_ibfk_1` (`id_places`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id_schedule`,`id_types_of_reports`),
  ADD KEY `report_ibfk_2` (`id_types_of_reports`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id_schedule`),
  ADD KEY `schedule_ibfk_1` (`id_places`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_role` (`id_role`);

--
-- Indexes for table `settings`
--
ALTER TABLE `settings`
  ADD PRIMARY KEY (`id_settings`);

--
-- Indexes for table `types_of_reports`
--
ALTER TABLE `types_of_reports`
  ADD PRIMARY KEY (`id_types_of_reports`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id_user`,`id_role`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`id_role`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `hours`
--
ALTER TABLE `hours`
  MODIFY `id_hours` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT dla tabeli `places`
--
ALTER TABLE `places`
  MODIFY `id_places` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `plan`
--
ALTER TABLE `plan`
  MODIFY `id_plan` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `role`
--
ALTER TABLE `role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id_schedule` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT dla tabeli `settings`
--
ALTER TABLE `settings`
  MODIFY `id_settings` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `types_of_reports`
--
ALTER TABLE `types_of_reports`
  MODIFY `id_types_of_reports` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `hours`
--
ALTER TABLE `hours`
  ADD CONSTRAINT `hours_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `plan`
--
ALTER TABLE `plan`
  ADD CONSTRAINT `plan_ibfk_1` FOREIGN KEY (`id_places`) REFERENCES `places` (`id_places`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `report_ibfk_1` FOREIGN KEY (`id_schedule`) REFERENCES `schedule` (`id_schedule`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `report_ibfk_2` FOREIGN KEY (`id_types_of_reports`) REFERENCES `types_of_reports` (`id_types_of_reports`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`id_places`) REFERENCES `places` (`id_places`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedule_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedule_ibfk_3` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
