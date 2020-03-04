DROP TABLE `biblioteca`.`prestito`;
DROP TABLE `biblioteca`.`utente`;
DROP TABLE `biblioteca`.`libro`;

-- -----------------------------------------------------
-- Creazione tabelle
-- -----------------------------------------------------

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `biblioteca` DEFAULT CHARACTER SET utf8 ;
USE `biblioteca` ;

-- -----------------------------------------------------
-- Table `biblioteca`.`libro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca`.`libro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titolo` VARCHAR(45) NOT NULL,
  `autore` VARCHAR(45) NOT NULL,
  `editore` VARCHAR(45) NOT NULL,
  `isbn` VARCHAR(45) NOT NULL,
  `quantita` VARCHAR(45) NOT NULL,
  `corsia` VARCHAR(45) NULL DEFAULT NULL,
  `libreria` VARCHAR(45) NULL DEFAULT NULL,
  `scaffale` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `biblioteca`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca`.`utente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `cognome` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `telefono` VARCHAR(45) NULL DEFAULT NULL,
  `via` VARCHAR(45) NULL DEFAULT NULL,
  `civico` VARCHAR(45) NULL DEFAULT NULL,
  `citta` VARCHAR(45) NULL DEFAULT NULL,
  `provincia` VARCHAR(45) NULL DEFAULT NULL,
  `cap` VARCHAR(45) NULL DEFAULT NULL,
  `ruolo` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `biblioteca`.`prestito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca`.`prestito` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fkIdUtente` INT NULL DEFAULT NULL,
  `fkIdLibro` INT NULL DEFAULT NULL,
  `dataInizio` DATE NOT NULL,
  `dataConsegna` DATE NULL DEFAULT NULL,
  `dataUltimoSollecito` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fkIdUtente_idx` (`fkIdUtente` ASC) VISIBLE,
  INDEX `fkIdLibro_idx` (`fkIdLibro` ASC) VISIBLE,
  CONSTRAINT `fkIdLibro`
    FOREIGN KEY (`fkIdLibro`)
    REFERENCES `biblioteca`.`libro` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fkIdUtente`
    FOREIGN KEY (`fkIdUtente`)
    REFERENCES `biblioteca`.`utente` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Creazione valori popolazione tabelle
-- -----------------------------------------------------

INSERT INTO `biblioteca`.`utente` (`nome`, `cognome`, `email`, `telefono`, `via`, `civico`, `citta`, `provincia`, `cap`, `ruolo`, `username`, `password`) 
VALUES ('n1', 'c1', 'e1', 't1', 'v1', 'civ1', 'citta1', 'p1', '11', 'iscritto', 'user1', 'pass1');
INSERT INTO `biblioteca`.`utente` (`nome`, `cognome`, `email`, `telefono`, `via`, `civico`, `citta`, `provincia`, `cap`, `ruolo`, `username`, `password`) 
VALUES ('n2', 'c2', 'e2', 't2', 'v2', 'civ2', 'citta2', 'p2', '12', 'iscritto', 'user2', 'pass2');
INSERT INTO `biblioteca`.`utente` (`nome`, `cognome`, `email`, `telefono`, `via`, `civico`, `citta`, `provincia`, `cap`, `ruolo`, `username`, `password`) 
VALUES ('n3', 'c3', 'e3', 't3', 'v3', 'civ3', 'citta3', 'p3', '13', 'gestore', 'user3', 'pass3');
INSERT INTO `biblioteca`.`utente` (`nome`, `cognome`, `email`, `telefono`, `via`, `civico`, `citta`, `provincia`, `cap`, `ruolo`, `username`, `password`) 
VALUES ('n4', 'c4', 'e4', 't4', 'v4', 'civ4', 'citta4', 'p4', '14', 'gestore', 'user4', 'pass4');
INSERT INTO `biblioteca`.`utente` (`nome`, `cognome`, `email`, `telefono`, `via`, `civico`, `citta`, `provincia`, `cap`, `ruolo`, `username`, `password`) 
VALUES ('n5', 'c5', 'e5', 't5', 'v5', 'civ5', 'citta5', 'p5', '15', 'amministatore', 'user5', 'pass5');
INSERT INTO `biblioteca`.`utente` (`nome`, `cognome`, `email`, `telefono`, `via`, `civico`, `citta`, `provincia`, `cap`, `ruolo`, `username`, `password`) 
VALUES ('n6', 'c6', 'e6', 't6', 'v6', 'civ6', 'citta6', 'p6', '16', 'amministatore', 'user6', 'pass6');


INSERT INTO `biblioteca`.`libro` (`titolo`, `autore`, `editore`, `isbn`, `quantita`, `corsia`, `libreria`, `scaffale`) 
VALUES ('t1', 'a1', 'e1', 'i1', '1', 'a', 'a', '1');
INSERT INTO `biblioteca`.`libro` (`titolo`, `autore`, `editore`, `isbn`, `quantita`, `corsia`, `libreria`, `scaffale`) 
VALUES ('t2', 'a2', 'e2', 'i2', '2', 'b', 'b', '2');
INSERT INTO `biblioteca`.`libro` (`titolo`, `autore`, `editore`, `isbn`, `quantita`, `corsia`, `libreria`, `scaffale`) 
VALUES ('t3', 'a3', 'e3', 'i3', '3', 'c', 'c', '3');
INSERT INTO `biblioteca`.`libro` (`titolo`, `autore`, `editore`, `isbn`, `quantita`, `corsia`, `libreria`, `scaffale`) 
VALUES ('t4', 'a4', 'e4', 'i4', '4', 'd', 'd', '4');
INSERT INTO `biblioteca`.`libro` (`titolo`, `autore`, `editore`, `isbn`, `quantita`, `corsia`, `libreria`, `scaffale`) 
VALUES ('t5', 'a5', 'e5', 'i5', '5', 'e', 'e', '5');
INSERT INTO `biblioteca`.`libro` (`titolo`, `autore`, `editore`, `isbn`, `quantita`, `corsia`, `libreria`, `scaffale`) 
VALUES ('t6', 'a6', 'e6', 'i6', '6', 'f', 'f', '6');
INSERT INTO `biblioteca`.`libro` (`titolo`, `autore`, `editore`, `isbn`, `quantita`, `corsia`, `libreria`, `scaffale`) 
VALUES ('t7', 'a7', 'e7', 'i7', '7', 'g', 'g', '7');
INSERT INTO `biblioteca`.`libro` (`titolo`, `autore`, `editore`, `isbn`, `quantita`, `corsia`, `libreria`, `scaffale`) 
VALUES ('t8', 'a8', 'e8', 'i8', '8', 'h', 'h', '8');
INSERT INTO `biblioteca`.`libro` (`titolo`, `autore`, `editore`, `isbn`, `quantita`, `corsia`, `libreria`, `scaffale`) 
VALUES ('t9', 'a9', 'e9', 'i9', '9', 'i', 'i', '9');
INSERT INTO `biblioteca`.`libro` (`titolo`, `autore`, `editore`, `isbn`, `quantita`, `corsia`, `libreria`, `scaffale`) 
VALUES ('t10', 'a10', 'e10', 'i10', '10', 'j', 'j', '10');


INSERT INTO `biblioteca`.`prestito` (`fkIdUtente`, `fkIdLibro`, `dataInizio`, `dataConsegna`) 
VALUES ('1', '2', '2020/01/16', '2020/01/30');
INSERT INTO `biblioteca`.`prestito` (`fkIdUtente`, `fkIdLibro`, `dataInizio`, `dataConsegna`, `dataUltimoSollecito`) 
VALUES ('2', '5', '2019/01/16', '2020/01/30', '2020/01/19');
INSERT INTO `biblioteca`.`prestito` (`fkIdUtente`, `fkIdLibro`, `dataInizio`, `dataConsegna`, `dataUltimoSollecito`) 
VALUES ('2', '9', '2019/07/16', '2019/08/30', '2019/08/19');
INSERT INTO `biblioteca`.`prestito` (`fkIdUtente`, `fkIdLibro`, `dataInizio`) 
VALUES ('1', '2', '2020/01/16');
INSERT INTO `biblioteca`.`prestito` (`fkIdUtente`, `fkIdLibro`, `dataInizio`, `dataConsegna`) 
VALUES ('2', '7', '2020/01/16', '2020/01/17');
INSERT INTO `biblioteca`.`prestito` (`fkIdUtente`, `fkIdLibro`, `dataInizio`, `dataConsegna`, `dataUltimoSollecito`) 
VALUES ('1', '10', '2018/01/16', '2019/01/30', '2018/01/19');
INSERT INTO `biblioteca`.`prestito` (`fkIdUtente`, `fkIdLibro`, `dataInizio`) 
VALUES ('2', '7', '2020/01/16');
INSERT INTO `biblioteca`.`prestito` (`fkIdUtente`, `fkIdLibro`, `dataInizio`, `dataConsegna`) 
VALUES ('1', '8', '2019/12/16', '2020/01/05');
INSERT INTO `biblioteca`.`prestito` (`fkIdUtente`, `fkIdLibro`, `dataInizio`, `dataConsegna`, `dataUltimoSollecito`) 
VALUES ('1', '6', '2018/01/16', '2018/03/30', '2018/02/28');

