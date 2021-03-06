-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-09-2019 a las 13:50:42
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hcpeventosbd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `afp`
--

CREATE TABLE `afp` (
  `cod_afp` int(11) NOT NULL,
  `descripcion` varchar(60) DEFAULT NULL,
  `minimo` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `afp`
--

INSERT INTO `afp` (`cod_afp`, `descripcion`, `minimo`) VALUES
(2, 'confia1', '12'),
(3, 'test', '12.5');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autorizacion`
--

CREATE TABLE `autorizacion` (
  `idautorizacion` int(11) NOT NULL,
  `descripcion` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `autorizacion`
--

INSERT INTO `autorizacion` (`idautorizacion`, `descripcion`) VALUES
(1, 'primera'),
(4, 'cuarta'),
(5, 'nueva autorizacion1'),
(6, 'nuevo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bancos`
--

CREATE TABLE `bancos` (
  `cod_banco` int(11) NOT NULL,
  `descripcion` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `bancos`
--

INSERT INTO `bancos` (`cod_banco`, `descripcion`) VALUES
(3, 'Promerica'),
(4, 'Azteca'),
(7, 'Sistema Fedecredito1'),
(8, 'Agricola'),
(9, 'agrico'),
(10, 'test'),
(11, 'agricola1'),
(12, 'tst'),
(13, 'test2'),
(14, 'test5'),
(15, 'jaryva'),
(16, 'Mexican'),
(17, 'nuevo registro3'),
(18, 'Nuevo Banco');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargos`
--

CREATE TABLE `cargos` (
  `cod_cargo` int(11) NOT NULL,
  `descripcion` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cargos`
--

INSERT INTO `cargos` (`cod_cargo`, `descripcion`) VALUES
(2, 'Administrador'),
(3, 'Ing Civil Industrial'),
(4, 'Ing Industrial'),
(5, 'Profesor'),
(7, 'Administrador'),
(8, 'nuevo registro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cat_personal`
--

CREATE TABLE `cat_personal` (
  `cod_personal` int(11) NOT NULL,
  `cod_programacion` int(11) DEFAULT NULL,
  `cod_cargo` int(11) DEFAULT NULL,
  `cod_jaryva` varchar(10) DEFAULT NULL,
  `cod_afp` int(11) DEFAULT NULL,
  `cod_banco` int(11) DEFAULT NULL,
  `nombres` varchar(70) DEFAULT NULL,
  `apellidos` varchar(70) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `genero` varchar(45) DEFAULT NULL,
  `estado_civil` varchar(45) DEFAULT NULL,
  `telefono1` varchar(8) DEFAULT NULL,
  `telefono2` varchar(8) DEFAULT NULL,
  `cod_departamento` int(11) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `dui` varchar(10) DEFAULT NULL,
  `nit` varchar(17) DEFAULT NULL,
  `isss` varchar(9) DEFAULT NULL,
  `nup` varchar(12) DEFAULT NULL,
  `cta_bancaria` varchar(15) DEFAULT NULL,
  `cat_pago` varchar(5) DEFAULT NULL,
  `obserbaciones` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cat_personal`
--

INSERT INTO `cat_personal` (`cod_personal`, `cod_programacion`, `cod_cargo`, `cod_jaryva`, `cod_afp`, `cod_banco`, `nombres`, `apellidos`, `direccion`, `genero`, `estado_civil`, `telefono1`, `telefono2`, `cod_departamento`, `fecha_ingreso`, `fecha_nacimiento`, `dui`, `nit`, `isss`, `nup`, `cta_bancaria`, `cat_pago`, `obserbaciones`) VALUES
(11, 2, 5, '2345', 2, 10, 'diego arevalo', 'arevalo', 'la reina1', 'Masculino', 'Soltero(a)', '7548393', '', 1, '2019-08-14', '1996-10-04', '594393939', '549339339', '5749849', '5849303', '8449393', 'A', 'hola'),
(14, 2, 5, '5678', 2, 9, 'Diego Enrique', 'Arevalo', 'la reina', 'Masculino', 'Soltero(a)', '7654004', '', 1, '2019-08-18', '2019-08-23', '7493930', '8439303', '483930330', '48390303', '4830303', 'A', 'esta es la primera obserbacion encontrada debido a tal cosa'),
(15, 4, 3, '2345', 3, 8, 'Cristian', 'menjivar', 'nueva concepcion', 'Masculino', 'Soltero(a)', '578439', '7438292', 2, '2019-09-10', '2019-09-17', '5739292', '47392920', '4739303', '4739302', '43839292', 'A', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contrato_trabajo`
--

CREATE TABLE `contrato_trabajo` (
  `idcontrato` int(11) NOT NULL,
  `cod_personal` int(11) NOT NULL,
  `fechacontrato` date NOT NULL,
  `finalizacioncontrato` date NOT NULL,
  `enfecha` date NOT NULL,
  `extendido` varchar(80) NOT NULL,
  `nacionalidad` varchar(30) NOT NULL,
  `profesion` varchar(30) NOT NULL,
  `lugartrabajo` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `contrato_trabajo`
--

INSERT INTO `contrato_trabajo` (`idcontrato`, `cod_personal`, `fechacontrato`, `finalizacioncontrato`, `enfecha`, `extendido`, `nacionalidad`, `profesion`, `lugartrabajo`) VALUES
(9, 11, '2019-08-21', '2019-08-21', '2019-08-21', 'djdj', 'Salvadoreña', 'Profesor', 'jjdd'),
(10, 14, '2019-08-24', '2019-08-24', '2019-08-24', 'merliot', 'Salvadoreña', 'Profesor', 'crowne plaza'),
(11, 15, '2019-09-09', '2019-09-11', '2019-09-13', 'san salvador', 'Salvadoreña', 'Ing Civil Industrial', 'crowne plaza');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamentos`
--

CREATE TABLE `departamentos` (
  `cod_departamento` int(11) NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `departamentos`
--

INSERT INTO `departamentos` (`cod_departamento`, `descripcion`) VALUES
(1, 'ventas1'),
(2, 'nuevo registro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_progeventos`
--

CREATE TABLE `detalle_progeventos` (
  `iddetalleprogeventos` int(11) NOT NULL,
  `idprogeventos` int(11) NOT NULL,
  `codtipoplanilla` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `devengos_contrato`
--

CREATE TABLE `devengos_contrato` (
  `iddevengo` int(11) NOT NULL,
  `idcontrato` int(11) NOT NULL,
  `devengospactados` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `devengos_contrato`
--

INSERT INTO `devengos_contrato` (`iddevengo`, `idcontrato`, `devengospactados`) VALUES
(1, 9, 'este es un devengo1'),
(3, 10, 'holaaaaaaaaaaaaaa1'),
(4, 11, 'devengos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eventos`
--

CREATE TABLE `eventos` (
  `idevento` int(11) NOT NULL,
  `descripcion` varchar(60) DEFAULT NULL,
  `precio_a` double DEFAULT NULL,
  `precio_b` double DEFAULT NULL,
  `precio_c` double DEFAULT NULL,
  `cod_programacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `eventos`
--

INSERT INTO `eventos` (`idevento`, `descripcion`, `precio_a`, `precio_b`, `precio_c`, `cod_programacion`) VALUES
(6, 'otro proceso1', 20, 27, 30, 1),
(7, 'agregar1', 45, 23, 34, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupofamiliar`
--

CREATE TABLE `grupofamiliar` (
  `idgrupo` int(11) NOT NULL,
  `cod_contrato` int(11) NOT NULL,
  `grupofamiliar` varchar(70) NOT NULL,
  `parentezco` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `grupofamiliar`
--

INSERT INTO `grupofamiliar` (`idgrupo`, `cod_contrato`, `grupofamiliar`, `parentezco`) VALUES
(1, 11, '', 'padres e hijos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `obligaciones_contrato`
--

CREATE TABLE `obligaciones_contrato` (
  `idobligaciones` int(11) NOT NULL,
  `idcontrato` int(11) NOT NULL,
  `obligaciones` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `obligaciones_contrato`
--

INSERT INTO `obligaciones_contrato` (`idobligaciones`, `idcontrato`, `obligaciones`) VALUES
(2, 9, 'mi obligacion'),
(3, 9, 'nueva obligacion1'),
(5, 10, ' VI, que dependen'),
(6, 10, 'otra obligacion11'),
(7, 10, 'esta es una prueba1'),
(8, 10, 'otra obligacion'),
(9, 11, 'primer obligacion formal'),
(10, 11, 'es un crio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planillas`
--

CREATE TABLE `planillas` (
  `codigoplanilla` varchar(10) NOT NULL,
  `desde` date NOT NULL,
  `hasta` date NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `planillas`
--

INSERT INTO `planillas` (`codigoplanilla`, `desde`, `hasta`, `status`) VALUES
('12', '2019-08-15', '2019-08-22', 'b'),
('34', '2019-08-15', '2019-08-14', 'b'),
('45', '2019-07-10', '2019-07-18', 'b'),
('4757', '2019-01-01', '2019-01-02', 'prueba');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `progeventos`
--

CREATE TABLE `progeventos` (
  `id` int(11) NOT NULL,
  `codtipoprogramacion` int(11) NOT NULL,
  `codplanilla` varchar(10) NOT NULL,
  `codprogramacion` int(11) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `codsalon` int(11) NOT NULL,
  `pax` int(11) NOT NULL,
  `entrada` time NOT NULL,
  `capitan` varchar(70) NOT NULL,
  `progeventos` varchar(30) NOT NULL,
  `idpago` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `programacion`
--

CREATE TABLE `programacion` (
  `cod_programacion` int(11) NOT NULL,
  `descripcion` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `programacion`
--

INSERT INTO `programacion` (`cod_programacion`, `descripcion`) VALUES
(1, 'Restaurante1'),
(2, 'proceso'),
(4, 'proceso nuevo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salones`
--

CREATE TABLE `salones` (
  `idsalon` int(11) NOT NULL,
  `descripcion` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `salones`
--

INSERT INTO `salones` (`idsalon`, `descripcion`) VALUES
(1, 'Salon1'),
(4, 'nuevo salon');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_planilla`
--

CREATE TABLE `tipo_planilla` (
  `cod_tipoplanilla` int(11) NOT NULL,
  `descripcion` varchar(20) NOT NULL,
  `precio` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_planilla`
--

INSERT INTO `tipo_planilla` (`cod_tipoplanilla`, `descripcion`, `precio`) VALUES
(1, 'test', '25.00'),
(2, 'test1', '45.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `nivel_acceso` varchar(45) DEFAULT NULL,
  `clave` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`codigo`, `nombre`, `usuario`, `nivel_acceso`, `clave`) VALUES
(2, 'diego enrique arevalo', 'Diego1', 'Consulta', '1234'),
(3, 'Melvin Marin', 'Melvin', 'Total', '1234');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `afp`
--
ALTER TABLE `afp`
  ADD PRIMARY KEY (`cod_afp`);

--
-- Indices de la tabla `autorizacion`
--
ALTER TABLE `autorizacion`
  ADD PRIMARY KEY (`idautorizacion`);

--
-- Indices de la tabla `bancos`
--
ALTER TABLE `bancos`
  ADD PRIMARY KEY (`cod_banco`);

--
-- Indices de la tabla `cargos`
--
ALTER TABLE `cargos`
  ADD PRIMARY KEY (`cod_cargo`);

--
-- Indices de la tabla `cat_personal`
--
ALTER TABLE `cat_personal`
  ADD PRIMARY KEY (`cod_personal`),
  ADD KEY `fk_programacion_personal_idx` (`cod_programacion`),
  ADD KEY `fk_cargo_personal_idx` (`cod_cargo`),
  ADD KEY `fk_afp_personal_idx` (`cod_afp`),
  ADD KEY `fk_banco_personal_idx` (`cod_banco`),
  ADD KEY `fk_departamento_personal_idx` (`cod_departamento`);

--
-- Indices de la tabla `contrato_trabajo`
--
ALTER TABLE `contrato_trabajo`
  ADD PRIMARY KEY (`idcontrato`),
  ADD KEY `fk_contrato_catpersonal` (`cod_personal`);

--
-- Indices de la tabla `departamentos`
--
ALTER TABLE `departamentos`
  ADD PRIMARY KEY (`cod_departamento`);

--
-- Indices de la tabla `detalle_progeventos`
--
ALTER TABLE `detalle_progeventos`
  ADD PRIMARY KEY (`iddetalleprogeventos`),
  ADD KEY `fk_progeventos_detalleprog` (`idprogeventos`),
  ADD KEY `fk_prog_tipoplanilla` (`codtipoplanilla`);

--
-- Indices de la tabla `devengos_contrato`
--
ALTER TABLE `devengos_contrato`
  ADD PRIMARY KEY (`iddevengo`),
  ADD KEY `fk_pedo1` (`idcontrato`);

--
-- Indices de la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD PRIMARY KEY (`idevento`),
  ADD KEY `evento_programacion` (`cod_programacion`);

--
-- Indices de la tabla `grupofamiliar`
--
ALTER TABLE `grupofamiliar`
  ADD PRIMARY KEY (`idgrupo`),
  ADD KEY `fk_contrato_grupofamiliar` (`cod_contrato`);

--
-- Indices de la tabla `obligaciones_contrato`
--
ALTER TABLE `obligaciones_contrato`
  ADD PRIMARY KEY (`idobligaciones`),
  ADD KEY `fk_pedo` (`idcontrato`);

--
-- Indices de la tabla `planillas`
--
ALTER TABLE `planillas`
  ADD PRIMARY KEY (`codigoplanilla`);

--
-- Indices de la tabla `progeventos`
--
ALTER TABLE `progeventos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_progeventos_salon` (`codsalon`),
  ADD KEY `fk_progeventos_planillas` (`codplanilla`),
  ADD KEY `fk_progeventos_programacion` (`codprogramacion`),
  ADD KEY `fk_progev_eventos` (`codtipoprogramacion`);

--
-- Indices de la tabla `programacion`
--
ALTER TABLE `programacion`
  ADD PRIMARY KEY (`cod_programacion`);

--
-- Indices de la tabla `salones`
--
ALTER TABLE `salones`
  ADD PRIMARY KEY (`idsalon`);

--
-- Indices de la tabla `tipo_planilla`
--
ALTER TABLE `tipo_planilla`
  ADD PRIMARY KEY (`cod_tipoplanilla`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `afp`
--
ALTER TABLE `afp`
  MODIFY `cod_afp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `autorizacion`
--
ALTER TABLE `autorizacion`
  MODIFY `idautorizacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `bancos`
--
ALTER TABLE `bancos`
  MODIFY `cod_banco` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `cargos`
--
ALTER TABLE `cargos`
  MODIFY `cod_cargo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `cat_personal`
--
ALTER TABLE `cat_personal`
  MODIFY `cod_personal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `contrato_trabajo`
--
ALTER TABLE `contrato_trabajo`
  MODIFY `idcontrato` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `departamentos`
--
ALTER TABLE `departamentos`
  MODIFY `cod_departamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `detalle_progeventos`
--
ALTER TABLE `detalle_progeventos`
  MODIFY `iddetalleprogeventos` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `devengos_contrato`
--
ALTER TABLE `devengos_contrato`
  MODIFY `iddevengo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `eventos`
--
ALTER TABLE `eventos`
  MODIFY `idevento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `grupofamiliar`
--
ALTER TABLE `grupofamiliar`
  MODIFY `idgrupo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `obligaciones_contrato`
--
ALTER TABLE `obligaciones_contrato`
  MODIFY `idobligaciones` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `progeventos`
--
ALTER TABLE `progeventos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `programacion`
--
ALTER TABLE `programacion`
  MODIFY `cod_programacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `salones`
--
ALTER TABLE `salones`
  MODIFY `idsalon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tipo_planilla`
--
ALTER TABLE `tipo_planilla`
  MODIFY `cod_tipoplanilla` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cat_personal`
--
ALTER TABLE `cat_personal`
  ADD CONSTRAINT `fk_afp_personal` FOREIGN KEY (`cod_afp`) REFERENCES `afp` (`cod_afp`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_banco_personal` FOREIGN KEY (`cod_banco`) REFERENCES `bancos` (`cod_banco`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_cargo_personal` FOREIGN KEY (`cod_cargo`) REFERENCES `cargos` (`cod_cargo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_departamento_personal` FOREIGN KEY (`cod_departamento`) REFERENCES `departamentos` (`cod_departamento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_programacion_personal` FOREIGN KEY (`cod_programacion`) REFERENCES `programacion` (`cod_programacion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `contrato_trabajo`
--
ALTER TABLE `contrato_trabajo`
  ADD CONSTRAINT `fk_contrato_catpersonal` FOREIGN KEY (`cod_personal`) REFERENCES `cat_personal` (`cod_personal`);

--
-- Filtros para la tabla `detalle_progeventos`
--
ALTER TABLE `detalle_progeventos`
  ADD CONSTRAINT `fk_prog_eventoss` FOREIGN KEY (`codtipoplanilla`) REFERENCES `programacion` (`cod_programacion`),
  ADD CONSTRAINT `fk_prog_tipoplanilla` FOREIGN KEY (`codtipoplanilla`) REFERENCES `tipo_planilla` (`cod_tipoplanilla`),
  ADD CONSTRAINT `fk_progdetalle_eventos` FOREIGN KEY (`codtipoplanilla`) REFERENCES `eventos` (`cod_programacion`),
  ADD CONSTRAINT `fk_progeventos_detalleprog` FOREIGN KEY (`idprogeventos`) REFERENCES `progeventos` (`id`),
  ADD CONSTRAINT `fk_tipoactividad_detalleprog` FOREIGN KEY (`codtipoplanilla`) REFERENCES `tipo_planilla` (`cod_tipoplanilla`);

--
-- Filtros para la tabla `devengos_contrato`
--
ALTER TABLE `devengos_contrato`
  ADD CONSTRAINT `fk_pedo1` FOREIGN KEY (`idcontrato`) REFERENCES `contrato_trabajo` (`idcontrato`);

--
-- Filtros para la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD CONSTRAINT `evento_programacion` FOREIGN KEY (`cod_programacion`) REFERENCES `programacion` (`cod_programacion`);

--
-- Filtros para la tabla `grupofamiliar`
--
ALTER TABLE `grupofamiliar`
  ADD CONSTRAINT `fk_contrato_grupofamiliar` FOREIGN KEY (`cod_contrato`) REFERENCES `contrato_trabajo` (`idcontrato`);

--
-- Filtros para la tabla `obligaciones_contrato`
--
ALTER TABLE `obligaciones_contrato`
  ADD CONSTRAINT `fk_pedo` FOREIGN KEY (`idcontrato`) REFERENCES `contrato_trabajo` (`idcontrato`);

--
-- Filtros para la tabla `progeventos`
--
ALTER TABLE `progeventos`
  ADD CONSTRAINT `fk_progev_eventos` FOREIGN KEY (`codtipoprogramacion`) REFERENCES `eventos` (`idevento`),
  ADD CONSTRAINT `fk_progeventos_planillas` FOREIGN KEY (`codplanilla`) REFERENCES `planillas` (`codigoplanilla`),
  ADD CONSTRAINT `fk_progeventos_programacion` FOREIGN KEY (`codprogramacion`) REFERENCES `programacion` (`cod_programacion`),
  ADD CONSTRAINT `fk_progeventos_salon` FOREIGN KEY (`codsalon`) REFERENCES `salones` (`idsalon`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
