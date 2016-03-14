create table user(
    name varchar(50) not null,
    email varchar(50) not null,
    cpf varchar(15) not null,
    primary key(cpf)
);
create table game(
    idGame bigint AUTO_INCREMENT,
    nameGame varchar(50) not null,
    gender varchar(30) not null,
    state varchar(40) not null,   
    primary key(idGame)
);

create table location(
    idLocation bigint AUTO_INCREMENT,
    idUser varchar(50),
    idGame bigint,
    dateLocation varchar(15) not null, 
    dateDevolution varchar(15),
    strategy varchar(30) not null,
    foreign key(idGame) references game(idGame) on update cascade on delete restrict,
    foreign key(idUser) references user(cpf) on update cascade on delete restrict,
    primary key(idLocation)
);
create table observers(
    idObserver bigint AUTO_INCREMENT,
    idGame bigint,
    idUser varchar(50),
    foreign key(idGame) references game(idGame) on update cascade on delete restrict,
    foreign key(idUser) references user(cpf) on update cascade on delete restrict,
    primary key(idObserver)
);
insert into user values('Anderson Souza', 'jair_anderson_bs@hotmail.com','98765445678');
insert into user values('Diogo Moreira', 'diogomoreira@hotmail.com','765678987');
insert into user values('Dijalma', 'dijalma@hotmail.com','9658');
insert into game values(1, 'Donkey Kong 3','Aventura', 'AVAILABLE');
insert into game values(2, 'Resident Evil','Suspense', 'AVAILABLE');
insert into game values(3, 'Mario','Aventura', 'AVAILABLE');