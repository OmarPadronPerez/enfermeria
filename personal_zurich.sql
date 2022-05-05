-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-05-2022 a las 20:05:30
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `personal_zurich`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consultas_enfermeria`
--

CREATE TABLE `consultas_enfermeria` (
  `ID` int(11) NOT NULL,
  `id_empleado` int(11) NOT NULL,
  `fecha_consulta` date NOT NULL DEFAULT current_timestamp(),
  `sintomas` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `signos_vitales` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `medicamento_suministrado` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `diagnistico` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `hospital` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `consultas_enfermeria`
--

INSERT INTO `consultas_enfermeria` (`ID`, `id_empleado`, `fecha_consulta`, `sintomas`, `signos_vitales`, `medicamento_suministrado`, `diagnistico`, `hospital`) VALUES
(1, 1311, '2022-05-04', 'sintomas', 'signos vitales', 'medicamento', 'diagnostico', 0),
(2, 1311, '2022-05-04', 'sintomas', 'signos vitales', 'medicamento', 'diagnostico', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datos_medicos`
--

CREATE TABLE `datos_medicos` (
  `ID` int(11) NOT NULL,
  `ruido` tinyint(1) DEFAULT 0,
  `polvo` tinyint(1) DEFAULT 0,
  `gases` tinyint(1) DEFAULT NULL,
  `biologico` tinyint(1) DEFAULT NULL,
  `humo` tinyint(1) DEFAULT NULL,
  `vapores` tinyint(1) DEFAULT NULL,
  `solventes` tinyint(1) DEFAULT NULL,
  `metales_pesado` tinyint(1) DEFAULT NULL,
  `temperaturas_altas` tinyint(1) DEFAULT NULL,
  `movimientos_repetitivos` tinyint(1) DEFAULT NULL,
  `posturas` tinyint(1) DEFAULT NULL,
  `cargas_pesadas` tinyint(1) DEFAULT NULL,
  `enfermedades_respiratorias` int(2) DEFAULT 0,
  `enfermedades_respiratorias_observaciones` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `enfermedades_hematologicas` int(2) DEFAULT 0,
  `enfermedades_hematologicas_observaciones` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `enfermedades_sexuales` int(2) DEFAULT 0,
  `enfermedades_sexuales_observaciones` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `enfermedades_neurologicas` int(2) DEFAULT 0,
  `enfermedades_neurologicas_observaciones` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `enfermedades_inter_quirurgicas` int(2) DEFAULT NULL,
  `enfermedades_inter_quirurgicas_observaciones` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `enfermedades_no_transmitibles` int(2) DEFAULT 0,
  `enfermedades_no_transmitibles_observaciones` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `enfermedades_psiquiatricas` int(2) DEFAULT 0,
  `enfermedades_psiquiatricas_observaciones` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `enfermedades_digestivas` int(2) DEFAULT 0,
  `enfermedades_digestivas_observaciones` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `enfermedades_autoinmunes` int(2) DEFAULT 0,
  `enfermedades_autoinmunes_observaciones` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `enfermedades_renales` int(2) DEFAULT 0,
  `enfermedades_renales_observaciones` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `enfermedades_oncologicas` int(2) DEFAULT 0,
  `enfermedades_oncologicas_observaciones` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `actividad_fisica` tinyint(1) DEFAULT 0,
  `alcohol` tinyint(1) DEFAULT 0,
  `fumar` tinyint(1) DEFAULT 0,
  `drogas` tinyint(1) DEFAULT 0,
  `drogas_tipo` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `examen_clinico` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `altura` float DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `fr` float DEFAULT NULL,
  `fc` float DEFAULT NULL,
  `perimetro_abdominal` float DEFAULT NULL,
  `test_snellen` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ojo_izquierdo` float DEFAULT NULL,
  `ojo_derecho` float DEFAULT NULL,
  `piel_cabello` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ojos_anexos` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `aparato_respiratorio` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `aparato_cardiovascular` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `aparato_digestivo` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `aparato_urinario` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `sistema_muscular` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `sistema_nervioso` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `valoracion_medica` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `historial_vacunacion` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `medicacion` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `contactos_emergencia` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `datos_medicos`
--

INSERT INTO `datos_medicos` (`ID`, `ruido`, `polvo`, `gases`, `biologico`, `humo`, `vapores`, `solventes`, `metales_pesado`, `temperaturas_altas`, `movimientos_repetitivos`, `posturas`, `cargas_pesadas`, `enfermedades_respiratorias`, `enfermedades_respiratorias_observaciones`, `enfermedades_hematologicas`, `enfermedades_hematologicas_observaciones`, `enfermedades_sexuales`, `enfermedades_sexuales_observaciones`, `enfermedades_neurologicas`, `enfermedades_neurologicas_observaciones`, `enfermedades_inter_quirurgicas`, `enfermedades_inter_quirurgicas_observaciones`, `enfermedades_no_transmitibles`, `enfermedades_no_transmitibles_observaciones`, `enfermedades_psiquiatricas`, `enfermedades_psiquiatricas_observaciones`, `enfermedades_digestivas`, `enfermedades_digestivas_observaciones`, `enfermedades_autoinmunes`, `enfermedades_autoinmunes_observaciones`, `enfermedades_renales`, `enfermedades_renales_observaciones`, `enfermedades_oncologicas`, `enfermedades_oncologicas_observaciones`, `actividad_fisica`, `alcohol`, `fumar`, `drogas`, `drogas_tipo`, `examen_clinico`, `altura`, `peso`, `fr`, `fc`, `perimetro_abdominal`, `test_snellen`, `ojo_izquierdo`, `ojo_derecho`, `piel_cabello`, `ojos_anexos`, `aparato_respiratorio`, `aparato_cardiovascular`, `aparato_digestivo`, `aparato_urinario`, `sistema_muscular`, `sistema_nervioso`, `valoracion_medica`, `historial_vacunacion`, `medicacion`, `contactos_emergencia`) VALUES
(1311, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, NULL, 0, NULL, 0, NULL, 0, NULL, 0, NULL, 0, NULL, 0, NULL, 0, NULL, 0, NULL, 0, NULL, 0, NULL, 0, 0, 0, 0, NULL, '', 1.85, 80, 0, 0, 0, '', 0, 0, '', '', '', '', '', '', '', '', '', 'COVID19- 14/04/2022', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `ID` int(5) NOT NULL,
  `nombre` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `apellido1` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `apellido2` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `area` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `telefono` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fecha_nacimiento` date NOT NULL,
  `lugar_nacimiento` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `sexo` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `estudios` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado_civil` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'0',
  `fecha_baja` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`ID`, `nombre`, `apellido1`, `apellido2`, `fecha_ingreso`, `area`, `telefono`, `fecha_nacimiento`, `lugar_nacimiento`, `sexo`, `estudios`, `estado_civil`, `activo`, `fecha_baja`) VALUES
(1311, 'JAVIER OMAR', 'PEREZ', 'PEREZ', '2019-02-28', 'SOPORTE', '4447141382', '1992-04-06', 'SAN LUIS POTOSI', 'HOMBRE', 'Tecnico', 'Soltero', b'1', NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `consultas_enfermeria`
--
ALTER TABLE `consultas_enfermeria`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `datos_medicos`
--
ALTER TABLE `datos_medicos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `consultas_enfermeria`
--
ALTER TABLE `consultas_enfermeria`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `datos_medicos`
--
ALTER TABLE `datos_medicos`
  ADD CONSTRAINT `FK_DATOS_MEDICOS` FOREIGN KEY (`ID`) REFERENCES `empleado` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
