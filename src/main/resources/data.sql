/* cannot drop foreign key because it does not exist (yet)
https://stackoverflow.com/questions/1571581/how-to-add-on-delete-cascade-in-alter-table-statement
https://stackoverflow.com/questions/12994919/can-not-create-table-in-workbench-errno-121
https://www.w3schools.com/sql/sql_foreignkey.asp
 */

/*ALTER TABLE `user_role`
    DROP FOREIGN KEY `user_id`,
    DROP FOREIGN KEY `role_id`;


ALTER TABLE `role`
  DROP FOREIGN KEY `role`;


ALTER TABLE `role`
  ADD CONSTRAINT `fk_role`
  FOREIGN KEY (`role_id`)
  REFERENCES `user`(`user_id`)
  ON DELETE CASCADE;*/

ALTER TABLE `user_role`
DROP FOREIGN KEY `FK859n2jvi8ivhui0rl0esws6o`;
ALTER TABLE `user_role`
ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o`
FOREIGN KEY (`user_id`)
REFERENCES `user`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `user_role`
DROP FOREIGN KEY `FKa68196081fvovjhkek5m97n3y`;


INSERT INTO `role` (`role`) VALUES
  ('ROLE_ADMIN'),
  ('ROLE_DRIVER'),
  ('ROLE_LOGISTICIAN');

INSERT INTO `client` (`id`, `city`, `phone`, `email`, `land`, `name`, `street`) VALUES
  (1, '2770-123 Paço de Arcos', '(977) 828-9760', 'cyclops.motors@example.com', 'Portugal', 'Cyclops Motors', 'Praceta Rosa 1'),
  (2, 'Dunrobin, ON K0A 1T0', '(627) 137-1790', 'sail.navigations@example.com', 'Canada', 'Sail Navigations', '98 Porcupine Trail'),
  (3, '45004 Toledo', '(567) 906-4052', 'rose.limited@example.com', 'Spain', 'Rose Limited', 'Ronda de el Olivar de los Pozos, 4'),
  (4, '29196 Málaga', '(426) 707-3647', 'robinetworks@example.com', 'Spain', 'Robinetworks', 'Calle Generación, 21-9'),
  (5, 'Leixlip\nCo. Kildare', '(737) 688-0889', 'peachco@example.com', 'Ireland', 'Peachco', '363-371 Castletown'),
  (6, 'Stoke-on-Trent\nST1 5HR\n', '(176) 380-3148', 'whiteoutwares@example.com', 'UK', 'Whiteoutwares', '151-153 Marsh St N'),
  (7, '1090 Jette', '(356) 649-7639', 'griffindustries@example.com', 'Belgium', 'Griffindustries', 'Rue Alexandre Wouters 7-12'),
  (8, '01-476 Warszawa', '(134) 469-9583', 'herbbooks@example.com', 'Poland', 'Herbbooks', 'Kaliskiego 25'),
  (9, '38110 Braunschweig', '(676) 246-7637', 'shadowtronics@example.com', 'Germany', 'Shadowtronics', 'Im Steinkampe 14-16'),
  (10, 'Moskva\n107140', '(555) 232-8189', 'nightwell@example.com', 'Russia', 'Nightwell', 'Komsomolskaya pl., 1'),
  (11, 'București', '(716) 683-2661', 'melon.enterprises@example.com', 'Romania', 'Melon Enterprises', 'Piața Dorobanților 2'),
  (12, 'Athina 104 35', '(822) 998-3532', 'granite.corporation@example.com', 'Greece', 'Granite Corporation', 'Iera Odos 36-44'),
  (13, 'Vogošća', '(587) 173-5823', 'eclipse.productions@example.com', 'Bosnia and Herzegovina', 'Eclipse Productions', 'Partizanskog odreda Zvijezda 2-22'),
  (14, 'Il-Mosta', '(586) 499-4837', 'purplelimited@example.com', 'Malta', 'Purplelimited', '14 Triq Ir-Rifuġjati Tal-Gwerra'),
  (15, '20016 Pero MI', '(114) 462-9810', 'omegacoustics@example.com', 'Italy', 'Omegacoustics', 'Via Bergamina, 14-18'),
  (16, 'Budapest\n1196', '(979) 370-0150', 'jetechnologies@example.com', 'Hungary', 'Jetechnologies', 'Árpád u. 45-71'),
  (17, '50825 Köln', '(222) 393-9330', 'felinetworks@example.com', 'Germany', 'Felinetworks', 'Grüner Weg 3-1'),
  (18, '6500 Landeck', '(727) 294-5007', 'nemoair@example.com', 'Austria', 'Nemoair', 'Perjenerweg 2'),
  (19, '3600 Thun', '(177) 593-0085', 'peachstar@example.com', 'Switzerland', 'Peachstar', 'Burgerstrasse 13-15'),
  (20, '1627 Vaulruz', '(530) 277-8373', 'hatchman@example.com', 'Switzerland', 'Hatchman', 'Vue-des-Alpes 19-1'),
  (21, '6032 Emmen', '(912) 643-4135', 'sphinx.enterprises@example.com', 'Switzerland', 'Sphinx Enterprises', 'Rütistrasse 28'),
  (22, '6330 Cham', '(494) 888-4751', 'core.security@example.com', 'Switzerland', 'Core Security', 'Rührliberg 32'),
  (23, '6932 Lugano', '(408) 472-1821', 'hurricane.systems@example.com', 'Switzerland', 'Hurricane Systems', 'Via Vergiò 8-18'),
  (24, '3400 Burgdorf', '(743) 775-0073', 'signalimited@example.com', 'Switzerland', 'Signalimited', 'Meiefeldstrasse 34'),
  (25, '1436 Chamblon', '(842) 959-3414', 'stardustechnologies@example.com', 'Switzerland', 'Stardustechnologies', 'Les Chandelènes 2-4'),
  (26, '1920 Martigny', '(540) 252-2091', 'cyclolutions@example.com', 'Switzerland', 'Cyclolutions', 'Chemin du Château 2-8'),
  (27, '5032 Aarau', '(895) 953-6977', 'galerprises@example.com', 'Switzerland', 'Galerprises', 'Hauptstrasse 37'),
  (28, '6340 Baar', '(708) 622-2003', 'pixelmaster@example.com', 'Switzerland', 'Pixelmaster', 'Neuhofstrasse 2'),
  (29, '2802 Develier', '(681) 776-9988', 'shadetales@example.com', 'Switzerland', 'Shadetales', 'Chemin de la Combatte 3'),
  (30, '7000 Chur', '(804) 820-2271', 'vortexbar@example.com', 'Switzerland', 'Vortexbar', 'Giacomettistrasse 114-116');


INSERT INTO `product` (`id`, `palettes`, `product_name`) VALUES
  (1, 0, 'EPX-WQ-965'),
  (2, 0, 'XKG-TX-156'),
  (3, 2, 'XKG-S-596'),
  (4, 4, 'XKG-X-529'),
  (5, 6, 'XKG-S-68'),
  (6, 5, 'XKG-X-667'),
  (7, 0, 'XKG-S-519'),
  (8, 4, 'XKG-TX-738'),
  (9, 7, 'XKG-WQ-405'),
  (10, 1, 'XKG-X-780'),
  (11, 4, 'XKG-ZZ-759'),
  (12, 2, 'XKG-S-537'),
  (13, 0, 'XKG-S-116'),
  (14, 5, 'XKG-WQ-604'),
  (15, 0, 'XKG-ZZ-712'),
  (16, 0, 'XKG-X-990'),
  (17, 1, 'XKG-X-273'),
  (18, 5, 'XKG-WQ-3'),
  (19, 7, 'XKG-TX-287'),
  (20, 1, 'XKG-S-305'),
  (21, 0, 'XKG-TX-893'),
  (22, 2, 'XKG-ZZ-922'),
  (23, 2, 'XKG-ZZ-645'),
  (24, 2, 'XKG-TX-196'),
  (25, 2, 'XKG-TX-356'),
  (26, 4, 'XKG-S-852'),
  (27, 4, 'XKG-X-180'),
  (28, 1, 'XKG-TX-842'),
  (29, 5, 'XKG-TX-936'),
  (30, 4, 'XKG-ZZ-908'),
  (31, 5, 'XKG-X-58'),
  (32, 1, 'XKG-TX-49'),
  (33, 6, 'XKG-X-277'),
  (34, 5, 'XKG-S-538'),
  (35, 0, 'XKG-ZZ-211'),
  (36, 7, 'XKG-S-555'),
  (37, 7, 'XKG-X-890'),
  (38, 0, 'XKG-WQ-901'),
  (39, 2, 'XKG-S-590'),
  (40, 0, 'XKG-S-895'),
  (41, 7, 'XKG-WQ-27'),
  (42, 4, 'XKG-WQ-905'),
  (43, 0, 'XKG-X-903'),
  (44, 0, 'XKG-S-772'),
  (45, 0, 'CPT-ZZ-352'),
  (46, 7, 'CPT-S-302'),
  (47, 1, 'CPT-TX-129'),
  (48, 3, 'CPT-ZZ-494'),
  (49, 2, 'CPT-S-757'),
  (50, 0, 'CPT-ZZ-240'),
  (51, 3, 'CPT-ZZ-956'),
  (52, 7, 'CPT-ZZ-95'),
  (53, 1, 'WVB-X-579'),
  (54, 5, 'WVB-WQ-24'),
  (55, 0, 'WVB-S-36'),
  (56, 3, 'WVB-ZZ-455'),
  (57, 4, 'WVB-WQ-788'),
  (58, 5, 'WVB-X-152'),
  (59, 7, 'WVB-ZZ-780'),
  (60, 7, 'JQS-TX-13'),
  (61, 4, 'JQS-ZZ-240'),
  (62, 0, 'JQS-ZZ-425'),
  (63, 2, 'JQS-S-227'),
  (64, 6, 'JQS-X-628'),
  (65, 6, 'JQS-ZZ-998'),
  (66, 7, 'JQS-TX-353'),
  (67, 2, 'JQS-X-259'),
  (68, 1, 'JQS-WQ-125'),
  (69, 7, 'JQS-X-255'),
  (70, 4, 'JQS-TX-179'),
  (71, 1, 'JQS-S-997'),
  (72, 0, 'JQS-ZZ-372'),
  (73, 3, 'JQS-WQ-442'),
  (74, 5, 'JQS-WQ-290'),
  (75, 4, 'JQS-WQ-488'),
  (76, 6, 'JQS-WQ-973'),
  (77, 0, 'JQS-ZZ-770'),
  (78, 6, 'JQS-X-152'),
  (79, 7, 'JQS-TX-86'),
  (80, 7, 'JQS-S-69'),
  (81, 1, 'JQS-X-962'),
  (82, 4, 'JQS-X-191'),
  (83, 4, 'JQS-WQ-445'),
  (84, 5, 'JQS-WQ-528'),
  (85, 7, 'JQS-TX-705'),
  (86, 5, 'JQS-ZZ-310'),
  (87, 7, 'JQS-S-597'),
  (88, 0, 'JQS-S-321'),
  (89, 4, 'JQS-ZZ-601'),
  (90, 1, 'JQS-TX-69'),
  (91, 7, 'JQS-S-334'),
  (92, 5, 'JQS-ZZ-630'),
  (93, 5, 'JQS-ZZ-842'),
  (94, 0, 'JQS-X-513'),
  (95, 6, 'JQS-ZZ-534'),
  (96, 5, 'JQS-ZZ-585'),
  (97, 7, 'JQS-WQ-716'),
  (98, 0, 'JQS-TX-418'),
  (99, 1, 'JQS-ZZ-979'),
  (100, 7, 'JQS-X-199'),
  (101, 7, 'JQS-WQ-63'),
  (102, 2, 'JQS-TX-886'),
  (103, 7, 'JQS-S-375'),
  (104, 5, 'JQS-X-557'),
  (105, 3, 'OEB-TX-495'),
  (106, 7, 'OEB-WQ-304'),
  (107, 3, 'OEB-X-165'),
  (108, 4, 'OEB-WQ-733'),
  (109, 7, 'OEB-WQ-171'),
  (110, 5, 'OEB-S-754'),
  (111, 6, 'OEB-S-475'),
  (112, 1, 'OEB-TX-864'),
  (113, 1, 'OEB-WQ-894'),
  (114, 2, 'OEB-S-211'),
  (115, 6, 'OEB-S-916'),
  (116, 5, 'JWQ-WQ-712'),
  (117, 5, 'JWQ-TX-713'),
  (118, 5, 'JWQ-ZZ-159'),
  (119, 4, 'JWQ-WQ-323'),
  (120, 4, 'JWQ-TX-528'),
  (121, 5, 'JWQ-S-789'),
  (122, 3, 'JWQ-S-840'),
  (123, 3, 'JWQ-X-428'),
  (124, 0, 'JWQ-ZZ-314'),
  (125, 2, 'JWQ-WQ-652'),
  (126, 6, 'JWQ-WQ-53'),
  (127, 2, 'JWQ-ZZ-861'),
  (128, 6, 'JWQ-ZZ-142'),
  (129, 5, 'JWQ-WQ-144'),
  (130, 0, 'JWQ-X-200'),
  (131, 0, 'JWQ-WQ-480'),
  (132, 5, 'JWQ-WQ-923'),
  (133, 1, 'JWQ-ZZ-871'),
  (134, 0, 'JWQ-X-133'),
  (135, 1, 'JWQ-TX-613'),
  (136, 5, 'JWQ-X-368'),
  (137, 2, 'JWQ-TX-542'),
  (138, 1, 'JWQ-WQ-961'),
  (139, 4, 'JWQ-S-862'),
  (140, 5, 'JWQ-ZZ-897'),
  (141, 2, 'JWQ-ZZ-253'),
  (142, 7, 'JWQ-TX-980'),
  (143, 3, 'JWQ-ZZ-568'),
  (144, 5, 'JWQ-ZZ-151'),
  (145, 0, 'JWQ-TX-876'),
  (146, 4, 'JWQ-ZZ-431'),
  (147, 7, 'JWQ-TX-879'),
  (148, 6, 'JWQ-S-894'),
  (149, 1, 'ABE-WQ-657'),
  (150, 5, 'ABE-ZZ-94'),
  (151, 5, 'ABE-X-13'),
  (152, 4, 'ABE-ZZ-926'),
  (153, 6, 'ABE-S-955'),
  (154, 7, 'ABE-TX-671'),
  (155, 1, 'ABE-ZZ-948'),
  (156, 7, 'ABE-WQ-700'),
  (157, 5, 'ABE-X-272'),
  (158, 3, 'ABE-ZZ-441');

INSERT INTO `driver` (`id`,`user_id`,`email`, `first_name`, `last_name`, `phone_number`) VALUES
  (1, 1, 'stokes@example.com', 'Celia', 'Stokes', '031 843 72 33'),
  (2, 2, 'ball@example.com', 'Emma', 'Ball', '031 843 72 34'),
  (3, 3, 'keller@example.com', 'Eloise', 'Keller', '031 843 72 35'),
  (4, 4, 'stone@example.com', 'Guadalupe', 'Stone', '031 843 72 36'),
  (5, 5, 'clarke@example.com', 'Isaac', 'Clarke', '031 843 72 37'),
  (6, 6, 'miller@example.com', 'Candice', 'Miller', '031 843 72 38'),
  (7, 7, 'harmon@example.com', 'Ben', 'Harmon', '031 843 72 39'),
  (8, 8, 'curry@example.com', 'Jason', 'Curry', '031 843 72 40'),
  (9, 9, 'mitchell@example.com', 'Eunice', 'Mitchell', '031 843 72 41');

INSERT INTO `logistician` (`id`, `email`, `first_name`, `last_name`, `phone_number`) VALUES
  (1, 'warner@example.com', 'Devin', 'Warner', '031 843 71 13'),
  (2, 'brock@example.com', 'Debra', 'Brock', '031 843 71 14'),
  (3, 'mckenzie@example.com', 'Daryl', 'Mckenzie', '031 843 71 15'),
  (4, 'greer@example.com', 'Dixie', 'Greer', '031 843 71 16'),
  (5, 'oliver@example.com', 'Clarence', 'Oliver', '031 843 71 17'),
  (6, 'riley@example.com', 'Ignacio', 'Riley', '031 843 71 18'),
  (7, 'gutierrez@example.com', 'Wendy', 'Gutierrez', '031 843 71 19'),
  (8, 'mcdaniel@example.com', 'Dallas', 'Mcdaniel', '031 843 71 20'),
  (9, 'patterson@example.com', 'Harry', 'Patterson', '031 843 71 21');

INSERT INTO `product_order` (`id`, `amount`, `product_id`, `client_id`, `acc_or_rej`) VALUES
(1, '1', 100, 28, 'keine Angabe'),
(2, '4', 9, 21, 'keine Angabe');

-- All drivers (user_id 1 to 9) have password "driver"
-- All logisticians (user_id 10 to 18) have password "logistician"
-- ADMIN has password "admin"
INSERT INTO `user` (`user_id`, `active`, `email`, `last_name`, `name`, `phone`, `password`) VALUES
(1, 1, 'stokes@example.com', 'Stokes', 'Celia','031 843 72 33', '$2a$10$K/2nhMGmH29GdiExDUtwPeWQNzHux.xFhMe4RzLM.mUG7WgrUhL.y'),
(2, 1, 'ball@example.com', 'Ball', 'Emma', '031 843 72 34', '$2a$10$Ap/4eeOUC5AsLqKrTIL3j.tjB6dG0g5yxj24/rURhxojBcZgFG02i'),
(3, 1, 'keller@example.com', 'Keller', 'Eloise', '031 843 72 35', '$2a$10$Kxl61etWij1KgzJaclL2/OL1xEYmx8fKv56PTWl9jRueDs1clMahG'),
(4, 1, 'stone@example.com', 'Stone', 'Guadalupe', '031 843 72 36', '$2a$10$LJ3GLJJpbHCvi4nlD8aagugX7oNk5wbLQ9mPhesyZ3cqKndOkrhxG'),
(5, 1, 'clarke@example.com', 'Clarke', 'Isaac', '031 843 72 37', '$2a$10$t0lHEn/NWL88QLnqw5bhQuLFx/6zOy6kE36H/PPH31joKXdTdb2Qe'),
(6, 1, 'miller@example.com', 'Miller', 'Candice', '031 843 72 38', '$2a$10$xxBtpkjjF1nF2pFzo1mToODlX6gGa9wou24mIxmCwq2vQUlLoIiV.'),
(7, 1, 'harmon@example.com', 'Harmon', 'Ben', '031 843 72 39', '$2a$10$rJZziDrgkLcXFPEMIx3zZeJrdSnqqwE7kWHGwnFg0BAxjFQLZoED.'),
(8, 1, 'curry@example.com', 'Curry', 'Jason', '031 843 72 40', '$2a$10$1P4dksyxv6d8eDylBdg8VOgja7tkNI6XU9G8dHUmctb66/IkagSt2'),
(9, 1, 'mitchell@example.com', 'Mitchell', 'Eunice', '031 843 72 41', '$2a$10$fdM4JlQKYFT4Y3RMf4r.ZOQGztL5j/l1zLygF/8H9omWTD5q1eCS2'),
(10, 1, 'warner@example.com', 'Warner', 'Devin', '031 843 71 13', '$2a$10$KRGj0sgrhNkcPd72HWpY3.mXFRfrbuJcgVsuqTe3q49KyMvqRXMUW'),
(11, 1, 'brock@example.com', 'Brock', 'Debra', '031 843 71 14', '$2a$10$ebHrVgcY3C4Vty022WqV5OD8HqFppEqLIy50b5MwF.k4BWKSwLWDe'),
(12, 1, 'mckenzie@example.com', 'Mckenzie', 'Daryl', '031 843 71 15', '$2a$10$DUi7mVvbr1q/vLWX7OYFY.yy5lyLyU6l35FemShdGqenVtvVaggtC'),
(13, 1, 'greer@example.com', 'Greer', 'Dixie', '031 843 71 16', '$2a$10$eovIcMPedl7E958144d/7utnpAWp1F7AavOOGmWFnf5Tsf0unZozG'),
(14, 1, 'oliver@example.com', 'Oliver', 'Clarence', '031 843 71 17', '$2a$10$M9w5iMhm6uM6VoGwJiUhguSa1Rxo1IFVZ1R5fSeqqHg6LQCKwDhIK'),
(15, 1, 'riley@example.com', 'Riley', 'Ignacio', '031 843 71 18', '$2a$10$S6N7jzHX.xZ1l6tNIPjQ5ecb8X8tZmoiwV1cJrRc1vgGTwZAKW8zC'),
(16, 1, 'gutierrez@example.com', 'Gutierrez', 'Wendy', '031 843 71 19', '$2a$10$e86Iglr6miyfgzzhwYlyvOzKHtoxV2NfXXnuesVnphsilp/M/VdSy'),
(17, 1, 'mcdaniel@example.com', 'Mcdaniel', 'Dallas', '031 843 71 20', '$2a$10$w4GOkciv0yy.HazmQqVpBuxB7o11urimSOmgO/d6Ntn8WlKi/7jFu'),
(18, 1, 'patterson@example.com', 'Patterson', 'Harry', '031 843 71 21', '$2a$10$za6sQ8GEzZxpyP1.x.BTiOZssTLxjaVqVq9tz/Dw/YwVFgJeoJ/xO');

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 3),
(11, 3),
(12, 3),
(13, 3),
(14, 3),
(15, 3),
(16, 3),
(17, 3),
(18, 3);

INSERT INTO `van` (`id`, `amount`, `freight`, `model`, `palettes_amount`) VALUES
(1, '7', '1', 'VW Transporter T1', '1');

INSERT INTO `boxes` (`id`, `amount`, `freight`, `model`, `palettes_amount`) VALUES
(1, '4', '12', 'Scania S 450 B6x4NB', '18');

INSERT INTO `tractor_machine` (`id`, `amount`, `model`) VALUES
(1, 12, 'Scania S 450 B6x4NA'),
(2, 5, 'DAF XF EURO 6'),
(3, 2, 'Mercedes-Benz Actros');

INSERT INTO `trailer` (`id`, `amount`, `freight`, `model`, `palettes_amount`, `free`) VALUES
(1, 13, 25, 'Schmitz-Cargobull S.PR+', 34, 13),
(2, 8, 25, 'Koegel XT.3', 32, 8),
(3, 2, 25, 'Schwarzmüller 34P', 34, 2);

INSERT INTO `vehicle` (`id`, `amount`, `freight`, `free`, `model`, `palettes_amount`, `type`) VALUES
(1, 12, 0, 12, 'Scania S 450 B6x4NA', 0, 'Tractor Machine'),
(2, 5, 0, 5, 'DAF XF EURO 6', 0, 'Tractor Machine'),
(3, 2, 0, 2, 'Mercedes-Benz Actros', 0, 'Tractor Machine'),
(4, '7', '1', 7, 'VW Transporter T1', '1', 'Van');