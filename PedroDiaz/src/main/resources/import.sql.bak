-- CLEANED import.sql (only Real Betis)
-- Delete existing rows first to avoid primary key violations on a persistent H2 database.
DELETE FROM estadisticas;
DELETE FROM futbolista;
DELETE FROM equipo;

-- Equipo: Real Betis Balompié
INSERT INTO equipo (id, nombre, categoria, img_equipo) VALUES (1, 'Real Betis Balompié', 'Primera División', '/img/equipos/betis.png');

-- Futbolistas (Real Betis)
INSERT INTO futbolista (id, nombre, apellidos, img_futbolista, fecha_nacimiento, fecha_inicio_contrato, nacionalidad, num_camiseta, salario_mensual_base, equipo_id, tipo) VALUES (101, 'Rui', 'Silva', '/img/futbolistas/rui_silva.png', '1994-02-07', '2021-07-01', 'Portugal', 13, 180000, 1, 'PORTERO');
INSERT INTO futbolista (id, nombre, apellidos, img_futbolista, fecha_nacimiento, fecha_inicio_contrato, nacionalidad, num_camiseta, salario_mensual_base, equipo_id, tipo) VALUES (102, 'Isco', 'Alarcón', '/img/futbolistas/isco.png', '1992-04-21', '2023-08-01', 'España', 22, 350000, 1, 'JUGADOR');
INSERT INTO futbolista (id, nombre, apellidos, img_futbolista, fecha_nacimiento, fecha_inicio_contrato, nacionalidad, num_camiseta, salario_mensual_base, equipo_id, tipo) VALUES (103, 'Ayoze', 'Pérez', '/img/futbolistas/ayoze_perez.jpg', '1993-07-29', '2023-07-01', 'España', 10, 220000, 1, 'JUGADOR');
INSERT INTO futbolista (id, nombre, apellidos, img_futbolista, fecha_nacimiento, fecha_inicio_contrato, nacionalidad, num_camiseta, salario_mensual_base, equipo_id, tipo) VALUES (104, 'Guido', 'Rodríguez', '/img/futbolistas/guido_rodriguez.jpg', '1994-04-12', '2019-01-01', 'Argentina', 5, 250000, 1, 'JUGADOR');

-- Estadísticas (Real Betis)
INSERT INTO estadisticas (id, futbolista_id, min_jugados, tar_amarilla, tar_roja, calificacion, goles, asistencias, paradas, porteriasacero, dtype) VALUES (1000, 101, 2700, 3, 0, 7.2, NULL, NULL, 88, 10, 'EstadisticasPortero');
INSERT INTO estadisticas (id, futbolista_id, min_jugados, tar_amarilla, tar_roja, calificacion, goles, asistencias, paradas, porteriasacero, dtype) VALUES (1001, 102, 1800, 2, 0, 7.8, 8, 10, NULL, NULL, 'EstadisticasJugador');
-- UPDATED from web: Ayoze Pérez 2024-25
INSERT INTO estadisticas (id, futbolista_id, min_jugados, tar_amarilla, tar_roja, calificacion, goles, asistencias, paradas, porteriasacero, dtype) VALUES (1002, 103, 2100, 4, 0, 7.5, 22, 4, NULL, NULL, 'EstadisticasJugador');
-- UPDATED from web: Guido Rodríguez 2024-25
INSERT INTO estadisticas (id, futbolista_id, min_jugados, tar_amarilla, tar_roja, calificacion, goles, asistencias, paradas, porteriasacero, dtype) VALUES (1003, 104, 2400, 7, 1, 7.3, 0, 2, NULL, NULL, 'EstadisticasJugador');