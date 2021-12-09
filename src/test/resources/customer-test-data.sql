 CREATE TABLE IF NOT EXISTS `TIM`.`TIM_CUSTOMERS` (
  `UUID` UUID NOT NULL,
  `DNI` VARCHAR(45) NOT NULL,
  `NAME` VARCHAR(45) NULL,
  `SURNAMES` VARCHAR(45) NULL,
  `ADDRESS` VARCHAR(200) NULL,
  `PHONE` VARCHAR(10) NULL,
  `EMAIL` VARCHAR(100) NULL,
  `EXPIRATION_DATE` DATE NULL,
  UNIQUE INDEX `DNI_UNIQUE` (`DNI` ASC),
  PRIMARY KEY (`UUID`));


INSERT INTO TIM.TIM_CUSTOMERS(UUID, DNI, NAME, SURNAMES, ADDRESS, PHONE, EMAIL)  VALUES ('123e4567e89b12d3a456556642440000','30', 'usuario 1', 'USUARIO 1', 'Parque de las avenidas', '679948260', 'jmmluna@gmail.com');
INSERT INTO TIM.TIM_CUSTOMERS(UUID, DNI, NAME, SURNAMES, ADDRESS, PHONE, EMAIL)  VALUES ('123e4567e89b12d3a456556642440001','40', 'usuario 2', 'USUARIO 2', 'Parque de las avenidas', '679948260', 'jmmluna@gmail.com');
INSERT INTO TIM.TIM_CUSTOMERS(UUID, DNI, NAME, SURNAMES, ADDRESS, PHONE, EMAIL)  VALUES ('123e4567e89b12d3a456556642440002','50', 'usuario 3', 'USUARIO 3', 'Parque de las avenidas', '679948260', 'jmmluna@gmail.com');
