--Roles--
INSERT INTO rol (descripcion_rol, nombre)
VALUES ('Encargado de verificar periódicamente el estado de la estación, sus equipos e infraestructura, detectando fallas o necesidades de mantenimiento.', 'Responsable de Inspecciones')

INSERT INTO rol (descripcion_rol, nombre)
VALUES ('Encargado de ejecutar las reparaciones técnicas necesarias en los equipos e instalaciones de la estación, garantizando su correcto funcionamiento.', 'Responsable de Reparacion')

INSERT INTO rol (descripcion_rol, nombre)
VALUES ('Encargado de gestionar los recursos económicos de la estación sismológica, incluyendo presupuestos, control de gastos y provisión de fondos para inspecciones y reparaciones.', 'Finanzas')


--Empleados--
INSERT INTO empleado (nombre, apellido, mail, telefono, rol_id)
VALUES ('Enzo', 'Ferreyra', 'enzoferreyra@redsismica.com', 1)

INSERT INTO empleado (nombre, apellido, mail, telefono, rol_id)
VALUES ('Mateo', 'Tazzioli', 'mateotazzioli@redsismica.com', 2)

INSERT INTO empleado (nombre, apellido, mail, telefono, rol_id)
VALUES ('Pedro', 'Rodriguez', 'pedrorodriguez@redsismica.com', 2)

INSERT INTO empleado (nombre, apellido, mail, telefono, rol_id)
VALUES ('Juan', 'Perez', 'juanperez@redsismica.com', 3)

--Usuarios--
INSERT INTO usuario (nombre_usuario, contraseña, empleado_id)
VALUES ('enzo', '1234', 1)

INSERT INTO usuario (nombre_usuario, contraseña, empleado_id)
VALUES ('mateo', '1234', 2)

INSERT INTO usuario (nombre_usuario, contraseña, empleado_id)
VALUES ('juan', '1234', 3)

--Sesiones--
INSERT INTO sesion (fecha_hora_inicio, fecha_hora_fin, usuario_id)
VALUES ('2025-08-16 08:30:00', NULL, 1);

INSERT INTO sesion (fecha_hora_inicio, fecha_hora_fin, usuario_id)
VALUES ('2025-08-15 09:00:00', '2025-08-15 12:00:00', 2);

INSERT INTO sesion (fecha_hora_inicio, fecha_hora_fin, usuario_id)
VALUES ('2025-08-14 14:00:00', '2025-08-14 18:00:00', 1);

--Ordenes--
INSERT INTO orden_inspeccion (fecha_hora_cierre, fecha_hora_finalizacion, fecha_hora_inicio, 
                    numero_orden, observacion_cierre, empleado_id, estado_id, estacion_id)
VALUES ()

INSERT INTO orden_inspeccion (fecha_hora_cierre, fecha_hora_finalizacion, fecha_hora_inicio, 
                    numero_orden, observacion_cierre, empleado_id, estado_id, estacion_id)
VALUES ()

INSERT INTO orden_inspeccion (fecha_hora_cierre, fecha_hora_finalizacion, fecha_hora_inicio, 
                    numero_orden, observacion_cierre, empleado_id, estado_id, estacion_id)
VALUES ()

INSERT INTO orden_inspeccion (fecha_hora_cierre, fecha_hora_finalizacion, fecha_hora_inicio, 
                    numero_orden, observacion_cierre, empleado_id, estado_id, estacion_id)
VALUES ()