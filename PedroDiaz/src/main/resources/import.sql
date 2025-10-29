-- 
-- Equipos
-- 
INSERT INTO equipo (id, nombre, categoria, img_equipo) VALUES
(1, 'Real Betis Balompié', 'Primera División', '/img/equipos/betis.png'),
(2, 'Betis Deportivo', 'Cantera', '/img/equipos/betis_deportivo.png'),
(3, 'Real Betis Féminas', 'Liga F', '/img/equipos/betis_feminas.png');



-- Futbolistas
INSERT INTO futbolista (id, nombre, apellidos, img_futbolista, fecha_nacimiento, fecha_inicio_contrato, nacionalidad, num_camiseta, salario_mensual_base, equipo_id, tipo) VALUES
(101, 'Rui', 'Silva', '/img/futbolistas/rui_silva.png', '1994-02-07', '2021-07-01', 'Portugal', 13, 180000, 1, 'PORTERO'),
(102, 'Isco', 'Alarcón', '/img/futbolistas/isco.png', '1992-04-21', '2023-08-01', 'España', 22, 350000, 1, 'JUGADOR'),
(103, 'Ayoze', 'Pérez', '/img/futbolistas/ayoze.png', '1993-07-29', '2023-07-01', 'España', 10, 220000, 1, 'JUGADOR'),
(104, 'Guido', 'Rodríguez', '/img/futbolistas/guido.png', '1994-04-12', '2019-01-01', 'Argentina', 5, 250000, 1, 'JUGADOR'),
(201, 'Assane', 'Diao', '/img/futbolistas/assane_diao.png', '2005-09-07', '2022-07-01', 'España', 37, 15000, 2, 'JUGADOR'),
(202, 'Dani', 'Pérez', '/img/futbolistas/dani_perez.png', '2006-06-26', '2022-07-01', 'España', 8, 12000, 2, 'JUGADOR'),
(203, 'Fran', 'Vieites', '/img/futbolistas/fran_vieites.png', '1999-03-07', '2023-07-01', 'España', 25, 18000, 2, 'PORTERO'),
(301, 'Rosa', 'Márquez', '/img/futbolistas/rosa_marquez.png', '2000-12-23', '2019-07-01', 'España', 6, 30000, 3, 'JUGADOR'),
(302, 'Ángela', 'Sosa', '/img/futbolistas/angela_sosa.png', '1993-01-16', '2020-09-15', 'España', 10, 45000, 3, 'JUGADOR'),
(303, 'Gaëlle', 'Thalmann', '/img/futbolistas/gaelle_thalmann.png', '1986-01-18', '2020-08-01', 'Suiza', 1, 40000, 3, 'PORTERO');



-- Estadísticas
INSERT INTO estadisticas (id, futbolista_id, min_jugados, tar_amarilla, tar_roja, calificacion, goles, asistencias, paradas, porterias_acero, tipo) VALUES
(1000, 101, 2700, 3, 0, 7.2, NULL, NULL, 88, 10, 'EstadisticasPortero'),
(1001, 102, 1800, 2, 0, 7.8, 8, 10, NULL, NULL, 'EstadisticasJugador'),
(1002, 103, 2100, 4, 0, 7.5, 12, 7, NULL, NULL, 'EstadisticasJugador'),
(1003, 104, 2400, 7, 1, 7.3, 3, 4, NULL, NULL, 'EstadisticasJugador'),
(1004, 201, 1200, 1, 0, 7.1, 6, 3, NULL, NULL, 'EstadisticasJugador'),
(1005, 202, 900, 2, 0, 6.9, 2, 5, NULL, NULL, 'EstadisticasJugador'),
(1006, 203, 1500, 2, 0, 7.0, NULL, NULL, 60, 7, 'EstadisticasPortero'),
(1007, 301, 2000, 3, 0, 7.4, 4, 8, NULL, NULL, 'EstadisticasJugador'),
(1008, 302, 1900, 2, 0, 7.6, 7, 6, NULL, NULL, 'EstadisticasJugador'),
(1009, 303, 2200, 1, 0, 7.3, NULL, NULL, 95, 9, 'EstadisticasPortero');



-- Relacionar estadisticas con futbolistas
UPDATE futbolista SET estadisticas_id = 1000 WHERE id = 101;
UPDATE futbolista SET estadisticas_id = 1001 WHERE id = 102;
UPDATE futbolista SET estadisticas_id = 1002 WHERE id = 103;
UPDATE futbolista SET estadisticas_id = 1003 WHERE id = 104;
UPDATE futbolista SET estadisticas_id = 1004 WHERE id = 201;
UPDATE futbolista SET estadisticas_id = 1005 WHERE id = 202;
UPDATE futbolista SET estadisticas_id = 1006 WHERE id = 203;
UPDATE futbolista SET estadisticas_id = 1007 WHERE id = 301;
UPDATE futbolista SET estadisticas_id = 1008 WHERE id = 302;
UPDATE futbolista SET estadisticas_id = 1009 WHERE id = 303;