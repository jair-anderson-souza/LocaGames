create table if not exists cliente(
    nome varchar(50) not null,
    email varchar(50) not null unique,
    cpf varchar(15) not null,
    primary key(cpf)
);

create table jogo(
    idDoJogo serial,
    nomeDoJogo varchar(50) not null,
    genero varchar(30) not null,
    estado varchar(40) not null,   
    primary key(idDoJogo)
);

create table if not exists locacao(
    idDaLocacao serial,
    idDoCliente varchar(50),
    idDoJogo int,
    dataDaLocacao varchar(15) not null, 
    dataDeDevolucao varchar(15),
    estrategia varchar(30) not null,
    foreign key(idDoJogo) references jogo(idDoJogo) on update cascade on delete restrict,
    foreign key(idDoCliente) references cliente(cpf) on update cascade on delete restrict,
    primary key(idDaLocacao)
);
create table if not exists observadores(
    idDoObservador serial,
    idDoJogo bigint,
    idDoCliente varchar(50),
    foreign key(idDoJogo) references jogo(idDoJogo) on update cascade on delete restrict,
    foreign key(idDoCliente) references cliente(cpf) on update cascade on delete restrict,
    primary key(idDoObservador)
);
insert into cliente values('Root', 'root@hotmail.com','123');
insert into cliente values('Jair', 'jair@hotmail.com','12345');
insert into jogo values(1, 'Mario', 'Aventura', 'ALUGADO');
insert into locacao values(1, '12345', 1, '2016-08-30', '2016-08-31','ESPECIAL');

