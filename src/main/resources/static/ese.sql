-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 01. Nov 2017 um 18:13
-- Server-Version: 10.1.26-MariaDB
-- PHP-Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `ese`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `client`
--

--
-- DROP TABLE first so new table can be created (table client already exists)
--
DROP TABLE `client`;

CREATE TABLE `client` (
  `id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `land` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `client`
--

INSERT INTO `client` (`id`, `city`, `phone`, `email`, `land`, `name`, `street`) VALUES
(1, '2770-123 Pa?o de Arcos', '(977) 828-9760', 'cyclops.motors@example.com', 'Portugal', 'Cyclops Motors', 'Praceta Rosa 1'),
(2, 'Dunrobin, ON K0A 1T0', '(627) 137-1790', 'sail.navigations@example.com', 'Canada', 'Sail Navigations', '98 Porcupine Trail'),
(3, '45004 Toledo', '(567) 906-4052', 'rose.limited@example.com', 'Spain', 'Rose Limited', 'Ronda de el Olivar de los Pozos, 4'),
(4, '29196 M?laga', '(426) 707-3647', 'robinetworks@example.com', 'Spain', 'Robinetworks', 'Calle Generaci?n, 21-9'),
(5, 'Leixlip\nCo. Kildare', '(737) 688-0889', 'peachco@example.com', 'Ireland', 'Peachco', '363-371 Castletown'),
(6, 'Stoke-on-Trent\nST1 5HR\n', '(176) 380-3148', 'whiteoutwares@example.com', 'UK', 'Whiteoutwares', '151-153 Marsh St N'),
(7, '1090 Jette', '(356) 649-7639', 'griffindustries@example.com', 'Belgium', 'Griffindustries', 'Rue Alexandre Wouters 7-12'),
(8, '01-476 Warszawa', '(134) 469-9583', 'herbbooks@example.com', 'Poland', 'Herbbooks', 'Kaliskiego 25'),
(9, '38110 Braunschweig', '(676) 246-7637', 'shadowtronics@example.com', 'Germany', 'Shadowtronics', 'Im Steinkampe 14-16'),
(10, 'Moskva\n107140', '(555) 232-8189', 'nightwell@example.com', 'Russia', 'Nightwell', 'Komsomolskaya pl., 1'),
(11, 'Bucure?ti', '(716) 683-2661', 'melon.enterprises@example.com', 'Romania', 'Melon Enterprises', 'Pia?a Doroban?ilor 2'),
(12, 'Athina 104 35', '(822) 998-3532', 'granite.corporation@example.com', 'Greece', 'Granite Corporation', 'Iera Odos 36-44'),
(13, 'Vogo??a', '(587) 173-5823', 'eclipse.productions@example.com', 'Bosnia and Herzegovina', 'Eclipse Productions', 'Partizanskog odreda Zvijezda 2-22'),
(14, 'Il-Mosta', '(586) 499-4837', 'purplelimited@example.com', 'Malta', 'Purplelimited', '14 Triq Ir-Rifu?jati Tal-Gwerra'),
(15, '20016 Pero MI', '(114) 462-9810', 'omegacoustics@example.com', 'Italy', 'Omegacoustics', 'Via Bergamina, 14-18'),
(16, 'Budapest\n1196', '(979) 370-0150', 'jetechnologies@example.com', 'Hungary', 'Jetechnologies', '?rp?d u. 45-71'),
(17, '50825 K?ln', '(222) 393-9330', 'felinetworks@example.com', 'Germany', 'Felinetworks', 'Gr?ner Weg 3-1'),
(18, '6500 Landeck', '(727) 294-5007', 'nemoair@example.com', 'Austria', 'Nemoair', 'Perjenerweg 2'),
(19, '3600 Thun', '(177) 593-0085', 'peachstar@example.com', 'Switzerland', 'Peachstar', 'Burgerstrasse 13-15'),
(20, '1627 Vaulruz', '(530) 277-8373', 'hatchman@example.com', 'Switzerland', 'Hatchman', 'Vue-des-Alpes 19-1'),
(21, '6032 Emmen', '(912) 643-4135', 'sphinx.enterprises@example.com', 'Switzerland', 'Sphinx Enterprises', 'R?tistrasse 28'),
(22, '6330 Cham', '(494) 888-4751', 'core.security@example.com', 'Switzerland', 'Core Security', 'R?hrliberg 32'),
(23, '6932 Lugano', '(408) 472-1821', 'hurricane.systems@example.com', 'Switzerland', 'Hurricane Systems', 'Via Vergi? 8-18'),
(24, '3400 Burgdorf', '(743) 775-0073', 'signalimited@example.com', 'Switzerland', 'Signalimited', 'Meiefeldstrasse 34'),
(25, '1436 Chamblon', '(842) 959-3414', 'stardustechnologies@example.com', 'Switzerland', 'Stardustechnologies', 'Les Chandel?nes 2-4'),
(26, '1920 Martigny', '(540) 252-2091', 'cyclolutions@example.com', 'Switzerland', 'Cyclolutions', 'Chemin du Ch?teau 2-8'),
(27, '5032 Aarau', '(895) 953-6977', 'galerprises@example.com', 'Switzerland', 'Galerprises', 'Hauptstrasse 37'),
(28, '6340 Baar', '(708) 622-2003', 'pixelmaster@example.com', 'Switzerland', 'Pixelmaster', 'Neuhofstrasse 2'),
(29, '2802 Develier', '(681) 776-9988', 'shadetales@example.com', 'Switzerland', 'Shadetales', 'Chemin de la Combatte 3'),
(30, '7000 Chur', '(804) 820-2271', 'vortexbar@example.com', 'Switzerland', 'Vortexbar', 'Giacomettistrasse 114-116');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `job`
--
DROP TABLE `job`;

CREATE TABLE `job` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `client` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `product` varchar(255) DEFAULT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `product_order`
--

--
-- DROP TABLE first so new table can be created (table product_order already exists)
--
DROP TABLE `product_order`;

CREATE TABLE `product_order` (
  `id` int(11) NOT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `driver_id` int(11) DEFAULT NULL,
  `product` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--
DROP TABLE `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `email`, `name`) VALUES
(1, 'devin.warner@example.com', 'Devin Warner'),
(2, 'debra.brock@example.com', 'Debra Brock'),
(3, 'daryl.mckenzie@example.com', 'Daryl Mckenzie'),
(4, 'dixie.greer@example.com', 'Dixie Greer'),
(5, 'clarence.oliver@example.com', 'Clarence Oliver'),
(6, 'ignacio.riley@example.com', 'Ignacio Riley'),
(7, 'wendy.gutierrez@example.com', 'Wendy Gutierrez'),
(8, 'dallas.mcdaniel@example.com', 'Dallas Mcdaniel'),
(9, 'harry.patterson@example.com', 'Harry Patterson'),
(10, 'ivan.mann@example.com', 'Ivan Mann'),
(11, 'lee.fisher@example.com', 'Lee Fisher'),
(12, 'shannon.guzman@example.com', 'Shannon Guzman'),
(13, 'cathy.maldonado@example.com', 'Cathy Maldonado'),
(14, 'sylvia.carter@example.com', 'Sylvia Carter'),
(15, 'raquel.fernandez@example.com', 'Raquel Fernandez'),
(16, 'sheryl.fox@example.com', 'Sheryl Fox'),
(17, 'linda.ray@example.com', 'Linda Ray'),
(18, 'eleanor.castillo@example.com', 'Eleanor Castillo'),
(19, 'celia.stokes@example.com', 'Celia Stokes'),
(20, 'emma.ball@example.com', 'Emma Ball'),
(21, 'eloise.keller@example.com', 'Eloise Keller'),
(22, 'guadalupe.stone@example.com', 'Guadalupe Stone'),
(23, 'isaac.clarke@example.com', 'Isaac Clarke'),
(24, 'candice.miller@example.com', 'Candice Miller'),
(25, 'ben.harmon@example.com', 'Ben Harmon'),
(26, 'jason.curry@example.com', 'Jason Curry'),
(27, 'eunice.mitchell@example.com', 'Eunice Mitchell');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `job`
--
ALTER TABLE `job`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `product_order`
--
ALTER TABLE `product_order`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `client`
--
ALTER TABLE `client`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT für Tabelle `job`
--
ALTER TABLE `job`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `product_order`
--
ALTER TABLE `product_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
