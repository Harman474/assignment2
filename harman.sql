use gc200350932;

drop table if exists consumers;

create table consumers(
consumerId int(11) auto_increment primary key,
consumerName varchar(200), 
email varchar(200), 
consumerPassword varchar(200), 
salt blob
);

select * from consumers;


create table products
(
productID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
make VARCHAR(50),
productName VARCHAR(50),
productType VARCHAR(50),
price INT
);

INSERT INTO products (make, productName, productType, price)
 VALUES('Fritolays','Classic Salted','Potato Chips',3),
 ('Ruffles','Loaded Bacon & Cheddar','Potato Chips',4),
 ('Pringles','Original Chips','Potato Chips',1),
 ('Del Monte','Ketchup','Sauce',5),
 ('Tombstone Original','Pizza','Frozen Food',7),
 ('Trader Joe','Chipotle Ranch Fries','Frozen Food',2),
 ('Huy Fong','Sriracha Chili Sauce','Sauce',2);
 