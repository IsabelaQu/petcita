CREATE DATABASE PETCITA;

USE PETCITA;

CREATE TABLE catalogo(
	id_catalogo int auto_increment NOT NULL,
	disponivel boolean NOT NULL,
	valor decimal NOT NULL,
	nome varchar(100) NOT NULL,
	descrição varchar(255) NOT NULL,
	categoria varchar(30) NOT NULL,
    PRIMARY KEY (id_catalogo)
);

CREATE TABLE cat_servico (
	id_cat_servico int auto_increment NOT NULL,
	min_duração int NOT NULL,
	serviço_interno boolean NOT NULL,
    id_catalogo int NOT NULL, 
	PRIMARY KEY (id_cat_servico),
    FOREIGN KEY (id_catalogo) REFERENCES catalogo(id_catalogo)
);

CREATE TABLE cat_produto (
	id_cat_produto int auto_increment NOT NULL,
	fornecedor varchar(255) NOT NULL,
	dt_validade date NOT NULL,
	dt_registro date NOT NULL,
	id_catalogo int NOT NULL, 
    PRIMARY KEY (id_cat_produto),
    FOREIGN KEY (id_catalogo) REFERENCES catalogo(id_catalogo)
);

CREATE TABLE usuario (
	id_usuario int auto_increment NOT NULL,
	nome varchar(255) NOT NULL,
	telefone varchar(11) NOT NULL,
	senha varchar(255) NOT NULL,
	login varchar(30) NOT NULL,
	email varchar(255) NOT NULL,
    PRIMARY KEY (id_usuario)
);

CREATE TABLE funcionario (
	id_funcionario int auto_increment NOT NULL,
	cargo varchar(30) NOT NULL,
    salario decimal NOT NULL,
    id_usuario int NOT NULL,
	PRIMARY KEY (id_funcionario),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE cliente (
	id_cliente int auto_increment NOT NULL,
	cep varchar(8) NOT NULL,
	numero_residencia int NOT NULL,
	id_usuario int NOT NULL,
	PRIMARY KEY (id_cliente),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE animal (
	id_animal int auto_increment NOT NULL,
	nome varchar(50) NOT NULL,
	especie varchar(50) NOT NULL,
	data_nascimento date NOT NULL,
	porte varchar(1) NOT NULL,
	id_cliente int NOT NULL,
    PRIMARY KEY (id_animal),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE pedido (
	id_pedido int auto_increment NOT NULL,
	id_funcionario int NOT NULL,
	id_cliente int NOT NULL,
	finalizado boolean NOT NULL,
    PRIMARY KEY (id_pedido),
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE pedido_itens (
	id_pedido_itens int auto_increment NOT NULL,
	id_cat_produto int NOT NULL,
	id_pedido int NOT NULL,
	quantidade int NOT NULL,
	data_pedido date NOT NULL,
	PRIMARY KEY (id_pedido_itens),
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_cat_produto) REFERENCES cat_produto(id_cat_produto)
);

CREATE TABLE pedido_agendamentos (
	id_pedido_agendamentos int auto_increment NOT NULL,
	id_pedido int NOT NULL,
    id_cat_servico int NOT NULL,
    id_animal int NOT NULL,
	data_agendamento date NOT NULL,
    PRIMARY KEY (id_pedido_agendamentos),
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_cat_servico) REFERENCES servico(id_cat_servico),
	FOREIGN KEY (id_animal) REFERENCES animal(id_animal)
);
