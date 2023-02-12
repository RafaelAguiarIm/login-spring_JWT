#Editar o nome da migration quando necessario apra V1__create-table-usuarios.sql

create table usuarios(

    id bigint not null auto_increment,
    login varchar(100) not null,
    senha varchar(100) not null,

    primary key(id)

);