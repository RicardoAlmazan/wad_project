drop database if exists forms;

create database forms;
use forms;

create table UserType (
	id int auto_increment primary key,
    descripcion nvarchar(64) not null
);

insert into UserType(descripcion) values
('Administrador'),
('Cliente');

create table Users (
	id int auto_increment primary key,
	nombres nvarchar (128) not null,
    apellidos nvarchar(128) not null,
    email nvarchar(128) not null,
    contrasenia nvarchar(32) not null,
	userType int not null,
    foreign key (userType) references UserType(id)
);

create table Estados(
	id int auto_increment primary key,
    abrev varchar(4) not null,
    nombre varchar(128) not null,
    activo tinyint not null default 1
);

create table Municipios(
	id int auto_increment primary key,
    abrev varchar(4) not null,
    nombre varchar(128) not null,
    estado int not null,
    activo tinyint not null default 1,
    foreign key (estado) references Estados(id)
);

create table TipoProblema(
	id int auto_increment primary key,
    descripcion nvarchar(128) not null
);

insert into TipoProblema(descripcion) values 
('SIN SERVICIO'),
('SERVICIO DEFICIENTE');

create table Solicitud(
	id int auto_increment primary key,
    numeroSolicitud nvarchar(12) not null,
    idSolicitante int not null,
    nombresOwner nvarchar(128) not null,
    apellidosOwner nvarchar(128) not null,
    fechaSolicitud datetime not null,
    tipoProblema int not null,
    estado int not null,
    municipio int not null,
    direccion nvarchar(1024) not null,
    comentarios nvarchar(1024),
    foreign key (idSolicitante) references Users(id),
    foreign key (estado) references Estados(id),
    foreign key (municipio) references Municipios(id)
);

insert into Users(nombres, apellidos, email, contrasenia, userType) values
('Ricardo', 'Almazán', 'ricardo.almazan.trejo@iikt.com.mx', md5('contrasenia'),1);

drop procedure if exists validateUsr;
delimiter //
create procedure validateUsr (in mail nvarchar(128), in pass nvarchar(32))
begin
	declare existe int;
    
    set existe = (select count(*) from Users where email = mail and contrasenia = md5(pass));
    
    if existe = 1 then
		select * from Users where email = mail and contrasenia = md5(pass);
	else
		select null;
	end if;
end;//
delimiter //

call validateUsr('ricardo.almazan.trejo@iikt.com.mx', 'contrasenia');

select * from Users;

delete from Users where id = 3;