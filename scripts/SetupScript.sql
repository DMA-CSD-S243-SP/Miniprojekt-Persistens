
create table Country
(
	country varchar(30),
	stateName varChar(30) not null,

	primary key (stateName)
);

create table PostalCode
(
	postalCode int not null,
	city varchar(30),

	primary key (postalCode)
);
go

create table Supplier
(
	id int not null,
	companyName varchar(30),
	phoneNumber varchar(15),
	emailAddress varchar(30),
	postalCode int not null,
	stateName varchar(30),
	streetName varchar(30),
	houseNumber int,
	floorNumver int,
	doorNumber int,

	primary key (id),
	constraint FK_Supplier_Country foreign key(stateName) references Country(stateName),
	constraint FK_Supplier_PostalCode foreign key(postalCode) references PostalCode(postalCode)
);

create table Store
(
	storeNumber int not null,
	phoneNumber varchar(15),
	emailAddress varchar(30),
	stateName varchar(30) not null,
	postalCode int not null,
	streetName varchar(30),
	houseNumber int,
	floorNumber int,

	primary key (storeNumber),
	constraint FK_Store_Country foreign key (stateName) references Country(stateName),
	constraint FK_Store_PostalCode foreign key (postalCode) references PostalCode(postalCode)
);
go

create table Warehouse
(
	id int not null,
	currentLocation varchar(30),

	primary key (id)
);

create table Invoice
(
	invoiceNumber int not null,
	invoiceStatus bit not null,
	paymentDate datetime,
	totalPrice double precision,

	primary key (invoiceNumber)
);

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
);

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
);
go

create table StockKeepingUnit
(
	id int not null,
	warehouseNumber int not null,
	shelfLocation varchar(30),
	storeNumber int not null,

	primary key (id),
	constraint FK_StockKeepingUnit_Warehouse foreign key (warehouseNumber) references Warehouse(id)
);

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
	constraint fk_SaleOrder_Employee foreign key (employeeId) references Employee(id),
	constraint fk_SaleOrder_Invoice foreign key (invoiceNumber) references Invoice(invoiceNumber)
);

create table Product
(
	id int not null,
	name varchar(45),
	countryOfOrigin varchar(30),
	minStock int not null,
	salesPrice double precision not null,
	purchasePrice double precision,
	supplierId int not null,
	stockKeepingUnitId int not null,

	primary key (id),
	constraint FK_Product_Supplier foreign key (supplierId) references Supplier(id),
	constraint FK_Product_StockKeepingUnit foreign key (stockKeepingUnitId) references StockKeepingUnit(id)
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
go

create table GunReplica
(
	productId int not null,
	productName varchar(30),
	calibre varchar(30),
	material varchar(30),

	primary key (productId),
	constraint FK_GunReplica_Product foreign key (productId) references Product(Id)
);

create table Clothing
(
	productId int not null,
	productName varchar(30),
	size varchar(10),
	colour varchar (20),

	primary key (productId),
	constraint FK_Clothing_Product foreign key (productId) references Product(Id)
);

create table Equipment
(
	productId int not null,
	productName varchar(30),
	equipmentType varchar(30),
	description varchar (60),

	primary key (productId),
	constraint FK_Equipment_Product foreign key (productId) references Product(Id)
);
go

create table Cd
(
	productId int not null,
	albumTitle varchar(30),
	artist varchar(30),
	releaseYear int,

	primary key (productId),
	constraint FK_Cd_Equipment foreign key (productId) references Equipment(productId),
	constraint FK_Cd_Product foreign key (productId) references Product(Id)
);

create table Licenseplate
(
	productId int not null,
	plateNumber varchar(15),

	primary key (productId),
	constraint FK_Cd_Licenseplate foreign key (productId) references Equipment(productId),
	constraint FK_Licenseplate_Product foreign key (productId) references Product(Id)
);

create table [Works At]
(
	employeeId int not null,
	storeNumber int not null,

	primary key (employeeId, storeNumber),
	constraint FK_WorksAt_Employee foreign key (employeeId) references Employee(id),
	constraint FK_WorksAt_Store foreign key (storeNumber) references Store(storeNumber)
);