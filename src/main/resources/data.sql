--Roles--
INSERT INTO rol (descripcion_rol, nombre)
VALUES ('Encargado de verificar periódicamente el estado de la estación, sus equipos e infraestructura, detectando fallas o necesidades de mantenimiento.', 'Responsable de Inspecciones');

INSERT INTO rol (descripcion_rol, nombre)
VALUES ('Encargado de ejecutar las reparaciones técnicas necesarias en los equipos e instalaciones de la estación, garantizando su correcto funcionamiento.', 'Responsable de Reparacion');

INSERT INTO rol (descripcion_rol, nombre)
VALUES ('Encargado de gestionar los recursos económicos de la estación sismológica, incluyendo presupuestos, control de gastos y provisión de fondos para inspecciones y reparaciones.', 'Finanzas');


--Empleados--
INSERT INTO empleado (nombre, apellido, mail, telefono, rol_id)
VALUES ('Enzo', 'Ferreyra', 'enzoferreyra@redsismica.com','12345678', 1);

INSERT INTO empleado (nombre, apellido, mail, telefono, rol_id)
VALUES ('Mateo', 'Tazzioli', 'mateotazzioli@redsismica.com', '12345678', 2);

INSERT INTO empleado (nombre, apellido, mail, telefono, rol_id)
VALUES ('Pedro', 'Rodriguez', 'pedrorodriguez@redsismica.com', '12345678', 2);

INSERT INTO empleado (nombre, apellido, mail, telefono, rol_id)
VALUES ('Juan', 'Perez', 'juanperez@redsismica.com', '12345678', 3);

--Usuarios--
INSERT INTO usuario (nombre_usuario, contrasenia, empleado_id)
VALUES ('enzo', '1234', 1);

INSERT INTO usuario (nombre_usuario, contrasenia, empleado_id)
VALUES ('mateo', '1234', 2);

INSERT INTO usuario (nombre_usuario, contrasenia, empleado_id)
VALUES ('juan', '1234', 3);

--Sesiones--
INSERT INTO sesion (fecha_hora_inicio, fecha_hora_fin, usuario_id)
VALUES ('2025-08-16 08:30:00', NULL, 1);

INSERT INTO sesion (fecha_hora_inicio, fecha_hora_fin, usuario_id)
VALUES ('2025-08-15 09:00:00', '2025-08-15 12:00:00', 2);

INSERT INTO sesion (fecha_hora_inicio, fecha_hora_fin, usuario_id)
VALUES ('2025-08-14 14:00:00', '2025-08-14 18:00:00', 1);

--Estados de Ordenes--
INSERT INTO estado (ambito, nombre_estado)
VALUES ('OrdenDeInspeccion', 'CompletamenteRealizada');

INSERT INTO estado (ambito, nombre_estado)
VALUES ('OrdenDeInspeccion', 'ParcialmenteRealizada');

INSERT INTO estado (ambito, nombre_estado)
VALUES ('OrdenDeInspeccion', 'Cerrada');

--Estados de Sismografos--
INSERT INTO estado (ambito, nombre_estado)
VALUES ('Sismografo', 'Disponible');

INSERT INTO estado (ambito, nombre_estado)
VALUES ('Sismografo', 'EnEsperaDeCertificacion');

INSERT INTO estado (ambito, nombre_estado)
VALUES ('Sismografo', 'HabilitadoParaConstruccion');

INSERT INTO estado (ambito, nombre_estado)
VALUES ('Sismografo', 'IncluidoEnPlanDeConstruccion');

INSERT INTO estado (ambito, nombre_estado)
VALUES ('Sismografo', 'EnInstalacion');

INSERT INTO estado (ambito, nombre_estado)
VALUES ('Sismografo', 'EnLinea');

INSERT INTO estado (ambito, nombre_estado)
VALUES ('Sismografo', 'Reclamado');

INSERT INTO estado (ambito, nombre_estado)
VALUES ('Sismografo', 'InhabilitadoPorInspeccion');

INSERT INTO estado (ambito, nombre_estado)
VALUES ('Sismografo', 'FueraDeServicio');

INSERT INTO estado (ambito, nombre_estado)
VALUES ('Sismografo', 'DeBaja');

--Motivos tipo--
INSERT INTO motivo_tipo (descripcion)
VALUES ('Falla eléctrica');

INSERT INTO motivo_tipo (descripcion)
VALUES ('Averías generales');

INSERT INTO motivo_tipo (descripcion)
VALUES ('Defectos de fábrica');

--Sismografos--
INSERT INTO sismografo (fecha_adquisicion, identificador_sismografo, nro_serie, estado_actual_id)
VALUES ('2005-04-01', 'S A1', '1', 9);

INSERT INTO sismografo (fecha_adquisicion, identificador_sismografo, nro_serie, estado_actual_id)
VALUES ('2006-08-01', 'S A2', '2', 9);

INSERT INTO sismografo (fecha_adquisicion, identificador_sismografo, nro_serie, estado_actual_id)
VALUES ('2010-01-01', 'S A3', '3', 9);

INSERT INTO sismografo (fecha_adquisicion, identificador_sismografo, nro_serie, estado_actual_id)
VALUES ('2011-06-25', 'S A4', '4', 12);

--Cambios de estado--
INSERT INTO cambio_estado (fecha_hora_fin, fecha_hora_inicio, estado_id, empleado_id, sismografo_id)
VALUES (NULL, '2023-01-01', 9, 1, 1);

INSERT INTO cambio_estado (fecha_hora_fin, fecha_hora_inicio, estado_id, empleado_id, sismografo_id)
VALUES ('2025-01-01', '2023-05-01', 12, 1, 2);

--Motivos Fuera de Servicio--
--Nota: la columna cambio_estado_id apunta al id de cambio_estado al que pertenece--
INSERT INTO motivo_fuera_de_servicio (comentario, motivo_tipo_id, cambio_estado_id)
VALUES ('Generador defectuoso que produce un cortocircuito', 1, 2);

--Estaciones--
INSERT INTO estacion_sismologica (documento_certificacion_adq, fecha_solicitud_certificacion, latitud, longitud, nombre, nro_certificacion_adquisicion, sismografo_id)
VALUES ('doc1', '2005-05-25', '-34.6037', '-58.3816', 'Estacion CABA', '123', 1);

INSERT INTO estacion_sismologica (documento_certificacion_adq, fecha_solicitud_certificacion, latitud, longitud, nombre, nro_certificacion_adquisicion, sismografo_id)
VALUES ('doc2', '2006-08-30', '40.7128', '-74.0060', 'Estacion NY', '456', 2);

INSERT INTO estacion_sismologica (documento_certificacion_adq, fecha_solicitud_certificacion, latitud, longitud, nombre, nro_certificacion_adquisicion, sismografo_id)
VALUES ('doc3', '2010-12-01', '35.6895', '139.6917', 'Estacion TOKIO', '789', 3);

--Ordenes--
INSERT INTO orden_inspeccion (fecha_hora_cierre, fecha_hora_finalizacion, fecha_hora_inicio, numero_orden, observacion_cierre, empleado_id, estado_id, estacion_id)
VALUES ('2024-07-16 12:00:00', '2024-07-10 10:00:00', '2024-07-10 08:00:00', '1', 'Se espera reparación de sismógrafo.', 1, 3, 1);

INSERT INTO orden_inspeccion (fecha_hora_cierre, fecha_hora_finalizacion, fecha_hora_inicio, numero_orden, observacion_cierre, empleado_id, estado_id, estacion_id)
VALUES ('2024-05-20 12:00:00', '2024-05-10 12:00:00', '2024-05-10 08:00:00', '2', 'Se necesita mantenimiento de instalaciones eléctricas.', 1, 3, 2);

INSERT INTO orden_inspeccion (fecha_hora_cierre, fecha_hora_finalizacion, fecha_hora_inicio, numero_orden, observacion_cierre, empleado_id, estado_id, estacion_id)
VALUES (NULL, '2024-03-20 12:00:00', '2024-03-20 08:00:00', '3', NULL, 1, 1, 3);

INSERT INTO orden_inspeccion (fecha_hora_cierre, fecha_hora_finalizacion, fecha_hora_inicio, numero_orden, observacion_cierre, empleado_id, estado_id, estacion_id)
VALUES (NULL, '2025-03-20 12:00:00', '2024-03-20 08:00:00', '4', NULL, 1, 1, 3);

INSERT INTO orden_inspeccion (fecha_hora_cierre, fecha_hora_finalizacion, fecha_hora_inicio, numero_orden, observacion_cierre, empleado_id, estado_id, estacion_id)
VALUES (NULL, '2022-03-20 12:00:00', '2024-03-20 08:00:00', '5', NULL, 1, 1, 3);
