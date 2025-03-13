create database DMA_CSD_S243_10644781
go

use DMA_CSD_S243_10644781
go

create table Employee
(
	id int not null,
	firstName varchar(20),
	lastName varchar(20),
	title varchar(30),
	phoneNumber varchar(15),
	emailAddress varchar(30),
	streetName varchar (30),
	houseNumber int,
	floorNumber int,
	doorNumber int,
	stateName varchar(30),
	postalCode int,

	primary key (id)
)

create table Customer
(
	emailAddress varchar(30) not null,
	id int not null,
	firstName varchar(20),
	lastName varchar(20),
	title varchar(30),
	phoneNumber varchar(15),
	streetName varchar (30),
	houseNumber int,
	floorNumber int,
	doorNumber int,
	stateName varchar(30),
	postalCode int,

	primary key (emailAddress),
	unique (id)
)
go

create table SaleOrder
(
	orderId int not null,
	customerId int,
	invoiceNumber int,
	emailAddress varchar(30),
	employeeId int,
	createdDate datetime,
	deliveryDate datetime,
	deliveryStatus bit not null,

	primary key (orderId),
	constraint fk_SaleOrder_Customer foreign key (customerId) references Customer(id),
	constraint fk_SaleOrder_Employee foreign key (employeeId) references Employee(id)
);

create table Product
(
	id int not null,
	name varchar(45),
	countryOfOrigin varchar(30),
	minStock int not null,
	salesPrice double precision not null,
	purchasePrice double precision,

	primary key (id)
);
go

create table SaleOrderLine
(
	id int not null,
	productId int not null,
	quantity int,
	orderId int not null,

	primary key (id),
	constraint fk_SaleOrderLine_SaleOrder foreign key (orderId) references SaleOrder(orderId),
	constraint fk_SaleOrderLine_Product foreign key (productId) references Product(id)
);