-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb`;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`autor` (
  `idautor` INT NOT NULL AUTO_INCREMENT,
  `nomeAutor` VARCHAR(45) NOT NULL,
  `nacionalidadeAutor` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idautor`),
  UNIQUE INDEX `idautor_UNIQUE` (`idautor` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `mydb`.`generoLiterario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`generoLiterario` (
  `idgeneroLiterario` INT NOT NULL AUTO_INCREMENT,
  `genero` VARCHAR(45) NULL,
  PRIMARY KEY (`idgeneroLiterario`),
  UNIQUE INDEX `idgeneroLiterario_UNIQUE` (`idgeneroLiterario` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `mydb`.`Livro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Livro` (
  `idLivro` INT NOT NULL AUTO_INCREMENT,
  `tituloObra` VARCHAR(45) NOT NULL,
  `autor_idautor` INT NOT NULL,
  `generoLiterario_idgeneroLiterario` INT NOT NULL,
  `listaInteresse_idlistaInteresse` INT NOT NULL,
  PRIMARY KEY (`idLivro`),
  UNIQUE INDEX `idLivro_UNIQUE` (`idLivro` ASC) VISIBLE,
  INDEX `fk_Livro_autor_idx` (`autor_idautor` ASC) VISIBLE,
  INDEX `fk_Livro_generoLiterario1_idx` (`generoLiterario_idgeneroLiterario` ASC) VISIBLE,
  CONSTRAINT `fk_Livro_autor`
    FOREIGN KEY (`autor_idautor`)
    REFERENCES `mydb`.`autor` (`idautor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Livro_generoLiterario1`
    FOREIGN KEY (`generoLiterario_idgeneroLiterario`)
    REFERENCES `mydb`.`generoLiterario` (`idgeneroLiterario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`editora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`editora` (
  `ideditora` INT NOT NULL AUTO_INCREMENT,
  `nomeEditora` VARCHAR(45) NOT NULL,
  `telefoneEditora` VARCHAR(45) NULL,
  `emailEditora` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ideditora`),
  UNIQUE INDEX `ideditora_UNIQUE` (`ideditora` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `mydb`.`copiaLivro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`copiaLivro` (
  `idcopiaLivro` INT NOT NULL AUTO_INCREMENT,
  `Livro_idLivro` INT NOT NULL,
  `editoraCopia` VARCHAR(45) NULL,
  `edicaoCopia` INT NULL,
  `editora_ideditora` INT NOT NULL,
  `CopiaDisponivel` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcopiaLivro`),
  UNIQUE INDEX `idcopiaLivro_UNIQUE` (`idcopiaLivro` ASC) VISIBLE,
  INDEX `fk_copiaLivro_Livro1_idx` (`Livro_idLivro` ASC) VISIBLE,
  INDEX `fk_copiaLivro_editora1_idx` (`editora_ideditora` ASC) VISIBLE,
  CONSTRAINT `fk_copiaLivro_Livro1`
    FOREIGN KEY (`Livro_idLivro`)
    REFERENCES `mydb`.`Livro` (`idLivro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_copiaLivro_editora1`
    FOREIGN KEY (`editora_ideditora`)
    REFERENCES `mydb`.`editora` (`ideditora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`membro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`membro` (
  `idmembro` INT NOT NULL AUTO_INCREMENT,
  `nomeMembro` VARCHAR(45) NOT NULL,
  `enderecoMembro` VARCHAR(45) NOT NULL,
  `telefoneMembro` VARCHAR(45) NOT NULL,
  `emailMembro` VARCHAR(45) NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idmembro`),
  UNIQUE INDEX `idmembro_UNIQUE` (`idmembro` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `mydb`.`emprestimos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`emprestimos` (
  `idemprestimos` INT NOT NULL AUTO_INCREMENT,
  `dataRetirada` VARCHAR(45) NULL,
  `dataDevolucao` VARCHAR(45) NULL,
  `membro_idmembro` INT NOT NULL,
  `copiaLivro_idcopiaLivro` INT NOT NULL,
  PRIMARY KEY (`idemprestimos`, `membro_idmembro`),
  UNIQUE INDEX `idemprestimos_UNIQUE` (`idemprestimos` ASC) VISIBLE,
  INDEX `fk_emprestimos_membro1_idx` (`membro_idmembro` ASC) VISIBLE,
  INDEX `fk_emprestimos_copiaLivro1_idx` (`copiaLivro_idcopiaLivro` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimos_membro1`
    FOREIGN KEY (`membro_idmembro`)
    REFERENCES `mydb`.`membro` (`idmembro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_emprestimos_copiaLivro1`
    FOREIGN KEY (`copiaLivro_idcopiaLivro`)
    REFERENCES `mydb`.`copiaLivro` (`idcopiaLivro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`Livro_has_editora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Livro_has_editora` (
  `Livro_idLivro` INT NOT NULL,
  `editora_ideditora` INT NOT NULL,
  PRIMARY KEY (`Livro_idLivro`, `editora_ideditora`),
  INDEX `fk_Livro_has_editora_editora1_idx` (`editora_ideditora` ASC) VISIBLE,
  INDEX `fk_Livro_has_editora_Livro1_idx` (`Livro_idLivro` ASC) VISIBLE,
  CONSTRAINT `fk_Livro_has_editora_Livro1`
    FOREIGN KEY (`Livro_idLivro`)
    REFERENCES `mydb`.`Livro` (`idLivro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Livro_has_editora_editora1`
    FOREIGN KEY (`editora_ideditora`)
    REFERENCES `mydb`.`editora` (`ideditora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`resenha`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`resenha` (
  `nota` INT NULL,
  `comentario` VARCHAR(45) NULL,
  `membro_idmembro` INT NOT NULL,
  `Livro_idLivro` INT NOT NULL,
  INDEX `fk_resenha_membro1_idx` (`membro_idmembro` ASC) VISIBLE,
  INDEX `fk_resenha_Livro1_idx` (`Livro_idLivro` ASC) VISIBLE,
  CONSTRAINT `fk_resenha_membro1`
    FOREIGN KEY (`membro_idmembro`)
    REFERENCES `mydb`.`membro` (`idmembro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resenha_Livro1`
    FOREIGN KEY (`Livro_idLivro`)
    REFERENCES `mydb`.`Livro` (`idLivro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

insert into autor() values (default,"Fernando Sabino", "Brasileiro");
insert into autor() values (default,"J.R.R.Tolkien", "Britânico");
insert into autor() values (default,"Carlos Drummond de Andrade", "Mineiro");
insert into autor() values (default, "Bernard Cornwell", "Australiano");
insert into autor() values (default,"J.K.Rolling", "Britanico");
insert into autor() values (default, "Salinger", "Estadunidense");
select * from autor;
update autor set nacionalidadeAutor = "Brasileiro" where idautor = 3;
update autor set nacionalidadeAutor = "Britânico" where idautor = 4;
delete from autor where idautor = 5;
delete from autor where idautor = 6;
select * from autor;

insert into generoLiterario() values (default, "Poesia");
insert into generoLiterario() values (default, "Fantasia");
insert into generoLiterario() values (default, "Crônica");
insert into generoLiterario() values (default, "Romance Historico");
insert into generoLiterario() values (default, "Ficcao");
insert into generoLiterario() values (default, "prosas");
select * from generoLiterario;
update generoLiterario set genero = "Ficcao Cientifica" where idgeneroLiterario = 5;
update generoLiterario set genero = "Prosa" where idgeneroLiterario = 6;
delete from generoLiterario where idgeneroLiterario = 5;
delete from generoLiterario where idgeneroLiterario = 6;
select * from generoLiterario;

insert into editora() values (default, "Rocco", "1935334567", "publicacao@rocco.com");
insert into editora() values (default, "Reccord", "1935678923", "autores@reccord.com.br");
insert into editora() values (default, "Companhia das Letras", "1234556789", "atendimento@letras.com.br");
insert into editora() values (default, "Harper Collins", "1988723341", "tradutores@hpbrasil.com.br");
insert into editora() values (default, "Darkside", "1234556789", "atendimento@darkside.com.br");
insert into editora() values (default, "cosac naify", "1988723341", "tradutores@cosac.com.br");
select * from editora;
update editora set emailEditora = "publicacao@rocco.com.br" where ideditora = 1;
update editora set emailEditora = "atendimento@cletras.com.br" where ideditora = 3;
delete from editora where ideditora = 5;
delete from editora where ideditora = 6;
select * from editora;

insert into membro() values (default, "Natalia", "Pouso Alegre", "35999999999", "natalia.batista@gec.inatel.br","emprestado");
insert into membro() values (default, "Joao", "SRS", "35988881234", "joao@gmail.com","atrasado");
insert into membro() values (default, "Luiz", "Pouso Alegre","35912345566", "luiz@outlook.com","devolvido");
insert into membro() values (default, "Maria", "Sao Lourenco", "35944556789", "maria@inatel.br","sem emprestimos");
insert into membro() values (default, "Pedro", "SRS", "35988881234", "pedro@gmail.com","atrasado");
insert into membro() values (default, "Ana", "Pouso Alegre","35912345566", "ana@outlook.com","devolvido");
select * from membro;
update membro set enderecoMembro = "Santa Rita do Sapucai" where idmembro = 2;
update membro set status = "devolvido" where idmembro = 4;
delete from membro where idmembro = 5;
delete from membro where idmembro = 6;
select * from membro;

insert into livro() values (default, "O Senhor dos Aneis", 2, 2, 4);
insert into livro() values (default, "O Menino No Espelho", 1, 3, 1);
insert into livro() values (default, "Antologia Poetica", 3, 1, 2);
insert into livro() values (default, "O Rei do Inverno", 4, 4, 2);
insert into livro() values (default, "O Inimigo de Deus", 3, 2, 1);
insert into livro() values (default, "Excalibur", 3, 2, 1);
insert into livro() values (default, "O Inimigo de Deus", 3, 2, 1);
insert into livro() values (default, "Excalibur", 3, 2, 1);
select * from livro;
update livro set autor_idautor = 4 where idLivro = 5;
update livro set generoLiterario_idgeneroLiterario = 4 where idLivro = 5;
update livro set autor_idautor = 4 where idLivro = 6;
update livro set generoLiterario_idgeneroLiterario = 4 where idLivro = 6;
delete from livro where idLivro = 7;
delete from livro where idLivro = 8;
select * from livro;

insert into resenha() values (10, "Surreal", 1, 1);
insert into resenha() values ( 5, NULL, 2, 4);
insert into resenha() values ( 3, NULL, 3, 3);
insert into resenha() values (10, NULL, 1, 2);
insert into resenha() values ( 9, NULL, 4, 1);
insert into resenha() values ( 10, NULL, 2, 5);
select * from resenha;
update resenha set comentario = "Estilo marcante do autor" where Livro_idLivro = 2;
update resenha set comentario = "Nao gosto muito de poesia" where Livro_idLivro = 3;

insert into copiaLivro() values(default, 1, NULL, 9, 4, "Sim");
insert into copiaLivro() values(default, 2, NULL, 5, 2, "Sim");
insert into copiaLivro() values(default, 3, NULL, 8, 3, "Sim");
insert into copiaLivro() values(default, 4, NULL, 3, 1, "Sim");
insert into copiaLivro() values(default, 5, NULL, 2, 1, "Sim");
insert into copiaLivro() values(default, 5, NULL, 1, 1, "Sim");
select * from copialivro;
update copiaLivro set Livro_idLivro = 6 where idcopialivro = 6;
update copiaLivro set edicaoCopia = 4 where idcopialivro = 4;
insert into copiaLivro() values(default, 5, NULL, 2, 1, "Sim");
insert into copiaLivro() values(default, 5, NULL, 1, 1, "Sim");
delete from copiaLivro where idcopiaLivro = 7;
delete from copiaLivro where idcopiaLivro = 8;

-- usei essa procedure para atualizar um valor na tabela
DELIMITER $$
DROP PROCEDURE IF EXISTS atualizaEmprestimo $$
CREATE PROCEDURE atualizaEmprestimo(idCopia INT)
BEGIN
	update copiaLivro set CopiaDisponivel = "Nao" where idcopialivro = idCopia;
END$$
DELIMITER ;

-- usei esse trigger para chamar a procedure antes de cada inserção
DELIMITER $$
DROP TRIGGER IF EXISTS emprestimoCheck $$
CREATE TRIGGER emprestimoCheck BEFORE INSERT
ON emprestimos
FOR EACH ROW 
BEGIN
	CALL atualizaEmprestimo(NEW.copiaLivro_idcopiaLivro);
END$$
DELIMITER ;

-- nao achei necessario implementar views, visto que os selects deviam usar join
-- nao usei functions porque no contexto não vi vantagens no uso de variaveis

insert into emprestimos() values(default, "03/04/2025", "18/04/2025", 1, 1);
insert into emprestimos() values(default, "12/04/2025", "27/04/2025", 2, 3);
insert into emprestimos() values(default, "02/04/2025","17/04/2025", 3, 2);
insert into emprestimos() values(default, "20/04/2025", "05/05/2025", 4, 4);

delete from emprestimos where idemprestimos = 1;
delete from emprestimos where idemprestimos = 2;
delete from emprestimos where idemprestimos = 3;
delete from emprestimos where idemprestimos = 4;

insert into emprestimos() values(default, "03/04/2025", "18/04/2025", 1, 1);
delete from emprestimos where idemprestimos = 5;
insert into emprestimos() values(default, "03/04/2025", "18/04/2025", 1, 1);
delete from emprestimos where idemprestimos = 6;
insert into emprestimos() values(default, "03/04/2025", "18/04/2025", 1, 1);
insert into emprestimos() values(default, "12/04/2025", "27/04/2025", 2, 3);
insert into emprestimos() values(default, "02/04/2025","17/04/2025", 3, 2);
insert into emprestimos() values(default, "20/04/2025", "05/05/2025", 4, 4);
select * from copiaLivro;

insert into Livro_has_editora() values(1,1);
insert into Livro_has_editora() values(1,4);
insert into Livro_has_editora() values(2,2);
insert into Livro_has_editora() values(3,3);
insert into Livro_has_editora() values(3,2);
insert into Livro_has_editora() values(4,1);
insert into Livro_has_editora() values(5,1);
insert into Livro_has_editora() values(6,1);

select m.nomeMembro, r.nota, l.tituloObra from membro as m
join resenha as r on m.idmembro = r.membro_idmembro
join livro as l on r.Livro_idLivro = l.idLivro
order by l.tituloObra;

select m.nomeMembro, e.dataDevolucao, l.tituloObra from membro as m
join emprestimos as e on m.idmembro = e.membro_idmembro
join copiaLivro as c on e.copiaLivro_idcopiaLivro = c.idcopiaLivro 
join livro as l on c.idcopiaLivro = l.idLivro
where c.CopiaDisponivel = "Nao"
order by m.nomeMembro;

select a.nomeAutor, l.tituloObra, e.nomeEditora from autor as a
join livro as l on l.autor_idautor = a.idautor
join Livro_has_Editora as lhe on lhe.Livro_idLivro = l.idLivro
join editora as e on lhe.editora_ideditora = e.ideditora
order by a.nomeAutor;

