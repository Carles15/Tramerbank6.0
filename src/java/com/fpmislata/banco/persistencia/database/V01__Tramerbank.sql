CREATE TABLE `entidadbancaria` (
  `id` int(11) NOT NULL,
  `codigoEntidad` int(11) DEFAULT NULL,
  `nombreEntidad` varchar(50) DEFAULT NULL,
  `cif` varchar(50) DEFAULT NULL,
  `tipoEntidad` enum('VIRTUAL','FISICA') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `entidadbancaria` VALUES (1,7897,'Santander','1','VIRTUAL'),(2,7798,'PayPal','2','VIRTUAL'),(3,3,'Prueba','2','FISICA'),(4,7897,'Santander','1','VIRTUAL'),(5,7898,'Santander','1','VIRTUAL');

CREATE TABLE `sucursalbancaria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entidadPertenece` int(11) DEFAULT NULL,
  `codigoSucursal` varchar(50) DEFAULT NULL,
  `nombreSucursal` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_entidadPertenece_entidad_id` (`entidadPertenece`),
  CONSTRAINT `FK_entidadPertenece_entidad_id` FOREIGN KEY (`entidadPertenece`) REFERENCES `entidadbancaria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

INSERT INTO `sucursalbancaria` VALUES (1,1,'1','Estrella de la muerte'),(2,1,'2','Hogwarts'),(3,1,'3','Orgrimmar'),(4,1,'4','Ventormenta'),(5,1,'5','Banco de hierro'),(6,1,'6','Posada Villadorada'),(7,1,'7','Dalaran'),(8,1,'8','KevinStormWind');

CREATE TABLE `cuentabancaria` (
  `id` int(11) NOT NULL,
  `sucursalPertenece` int(11) DEFAULT NULL,
  `numCuenta` int(11) DEFAULT NULL,
  `digitosControl` int(11) DEFAULT NULL,
  `saldoCuenta` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`sucursalPertenece`) REFERENCES `sucursalbancaria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `cuentabancaria` VALUES (1,4,797774,25,589.3),(2,2,955678,89,78888),(3,5,777789,77,1000000),(4,6,565687,97,15.0917),(5,7,999998,68,8.37),(6,4,888888,66,9);

CREATE TABLE `movimientobancario` (
  `id` int(11) NOT NULL,
  `cuentaPertenece` int(11) DEFAULT NULL,
  `tipoMovimiento` varchar(50) DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `saldoTotal` double DEFAULT NULL,
  `concepto` text,
  PRIMARY KEY (`id`),
  KEY `FK_cuentaPertenece_cuenta_id` (`cuentaPertenece`),
  CONSTRAINT `FK_cuentaPertenece_cuenta_id` FOREIGN KEY (`cuentaPertenece`) REFERENCES `cuentabancaria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `movimientobancario` VALUES (1,1,'DEBE',158.3,'2014-05-15',589.3,'Feliz Navidad'),(2,3,'HABER',15000,'2014-10-15',1000000,'Un Lannister siempre paga sus deudas');


CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL DEFAULT '0',
  `apellidos` varchar(50) NOT NULL DEFAULT '0',
  `email` varchar(100) NOT NULL DEFAULT '0',
  `direccion` varchar(50) DEFAULT '0',
  `telefono` varchar(15) DEFAULT '0',
  `contrasenya` varchar(100) NOT NULL DEFAULT '0',
  `rol` enum('CLIENTE','ADMINISTRADOR','JEDI') NOT NULL DEFAULT 'CLIENTE',
  `idCuentaBancaria` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_usuarios_cuentabancaria` (`idCuentaBancaria`),
  CONSTRAINT `fk_usuarios_cuentabancaria` FOREIGN KEY (`idCuentaBancaria`) REFERENCES `cuentabancaria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `usuarios` VALUES (1,'Tywin','Lannister','Elseñordelaroca@poniente.com','Roca Casterly','989658798','dabestmano','CLIENTE',3),(2,'Kevin','Lannister','Elseñordelaroca@poniente.com','Roca Casterly','989658798','dabestmano','CLIENTE',3),(3,'a','a','a','a','1232','a','CLIENTE',3);

ALTER TABLE `movimientobancario` 
CHANGE COLUMN `tipoMovimiento` `tipoMovimiento` ENUM('DEBE','HABER') NULL DEFAULT NULL ;

ALTER TABLE `movimientobancario` 
CHANGE COLUMN `id` `id` INT(11) NULL AUTO_INCREMENT ;

