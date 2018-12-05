create database Agenda
use Agenda

create table Contactos(
id int primary key identity(1,1),
nombre varchar(30),
apellido varchar(30),
alias varchar (20),
email varchar(60),
telefono varchar(20)

)
