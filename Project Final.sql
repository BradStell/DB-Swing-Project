#############Participants###################
#### Jake "The Snake" Cooney
#### Morgan "The Destroyer" Stewart
#### James "The Hat" Stell
#### Michael "Austin" Powers
############################################

##############################
#SUPER DUPER IMPORTANT#
#PROJECT MUST BE RUN IN SCHEMA NAMED  "Project"
#Tables are slightly different than the ER diagram
#as things needed to be modified as we went along
##############################

####################################################
#This section is where all of the tables are created
#Tables are self-explanatory
####################################################
####################################################
#create database project;
CREATE TABLE Books 
(ISBN DECIMAL(20,0) PRIMARY KEY,
Author CHAR(50),
Title CHAR(80),
Edition CHAR(5),
PubYear Decimal(4,0),
Category CHAR(20),
Publisher CHAR(30),
Quantity Decimal(4,0),
Price Decimal (5,2));

CREATE TABLE BooksInCart 
(CartID DECIMAL(8,0),
ISBN DECIMAL(20,0),
Quantity Decimal(4,0));


CREATE TABLE Cart
(CartID DECIMAL(8,0) PRIMARY KEY,
CustomerID DECIMAL (20,0), 
CartName CHAR (50), 
dateCreated CHAR (30), 
dateUpdated CHAR (30), 
totalPrice DECIMAL (10,2), 
quantity DECIMAL (10,0));

##############################################################
#Later we realized we forgot to add a quantity table
#So we used the alter table command to add it
##############################################################

CREATE TABLE CartList 
(CartID DECIMAL(8,0) PRIMARY KEY,
CustomerID DECIMAL(8,0));

CREATE TABLE Orders
(OrderNum DECIMAL(10,0) PRIMARY KEY,
CustomerID DECIMAL(8,0),
EmployeeID DECIMAL(10,0),
OrderDate CHAR(10),
OrderStatus CHAR(20),
ShipAddress CHAR(80),
BillAddress CHAR(80),
quantity DECIMAL(10,0),
TotalPrice DECIMAL(10,2),
CCNum DECIMAL(16,0));

CREATE TABLE BooksOrdered 
(OrderNum DECIMAL(10,0),
ISBN Decimal (20,0),
Price DECIMAL (5,2),
Quantity DECIMAL (4,0));

CREATE TABLE CreditCard
(CustomerID DECIMAL(10,0),
CreditCardNum DECIMAL(16,0), PRIMARY KEY (CustomerID, CreditCardNum)); ###Two primary keys

CREATE TABLE Customer
(CustomerID DECIMAL(20,0) PRIMARY KEY,
CusName CHAR(50),
Phone CHAR(15),
Email CHAR (40),
CusPassword CHAR (30));

CREATE TABLE Employee
(employeeID DECIMAL(20,0) PRIMARY KEY,
EmpName CHAR(50),
Address CHAR(50),
Salary Decimal (10,2),
ManagerID Decimal(20,0));


###This table holds the value of the next primary key for the cart table.
#This allows us to easily increment and merge new carts

CREATE TABLE counterTable
(id DECIMAL (1,0) PRIMARY KEY, 
counter DECIMAL (10,0));

insert into counterTable values (1, 1000); #Initialized them to 1000

################################################################
#All of our Triggers
################################################################
################################################################

###This trigger adds the books added to cart to the total price column in the cart table, as well as
###updating the quantity in cart and updating the date updated for the cart

DELIMITER $$
CREATE TRIGGER IncreaseCartQuantity
AFTER INSERT ON BooksInCart
FOR EACH row 
BEGIN
UPDATE CART
SET totalPrice = totalPrice + (select price from books where new.ISBN = books.ISBN) * new.quantity, quantity = new.quantity + cart.quantity, dateUpdated = curDate()
#Increment the total price by the price of the books added to the cart; do the same with quantity
where new.cartID = cart.cartID;
end
$$
DELIMITER ; 


###Same as above, except decrements quantity and total price when books are deleted

DELIMITER $$
CREATE TRIGGER DecreaseCartQuantity
AFTER DELETE ON BooksInCart
FOR EACH row 
BEGIN
UPDATE CART
SET quantity = cart.quantity - old.quantity, totalPrice = totalPrice - ((select price from books where old.ISBN = books.ISBN) * old.quantity), dateUpdated = curDate()
#Remove total price and quantity from total in cart
where old.cartID = cart.cartID;
end
$$
DELIMITER ;


###This trigger adds the total quantity ordered by however many books are added to the booksordered table
#drop trigger if exists increaseordersquantity;
DELIMITER $$
CREATE TRIGGER IncreaseOrdersQuantity
AFTER INSERT ON booksOrdered
FOR EACH row 
BEGIN
UPDATE ORDERS
SET quantity = orders.quantity + new.quantity
WHERE orderNum=new.orderNum;
end
$$
DELIMITER ;


###Same as above, but decrements it by the proper amount
#drop trigger if exists decreaseordersquantity;
DELIMITER $$
CREATE TRIGGER DecreaseOrdersQuantity
AFTER DELETE ON booksOrdered
FOR EACH row 
BEGIN
UPDATE ORDERS
SET quantity = orders.quantity - old.quantity
WHERE orderNum=old.orderNum;
end
$$
DELIMITER ;

###Increases the quantity of books in stock whenever an order is deleted/cancelled
#drop trigger if exists increasebooksquantity;
DELIMITER $$
CREATE TRIGGER IncreaseBooksQuantity
BEFORE DELETE ON BooksOrdered
FOR EACH row 
BEGIN
UPDATE BOOKS
SET quantity = quantity + old.quantity 
WHERE ISBN=old.ISBN;
end
$$
DELIMITER ;

###reduces books in stock whenever a book is added to an order
#drop trigger if exists decreasebooksquantity;
DELIMITER $$
CREATE TRIGGER DecreaseBooksQuantity
BEFORE INSERT ON BooksOrdered
FOR EACH row 
BEGIN
set @temp = (select quantity from books where ISBN = new.ISBN);
UPDATE BOOKS
SET quantity = @temp - new.quantity
WHERE ISBN=new.ISBN;
end
$$
DELIMITER ;

##########################################################
#All procedures used in script
##########################################################
##########################################################

###Updates the user's password; requires username and old password for security, plus new password

DELIMITER $$
CREATE PROCEDURE UpdatePassword
(CusID DECIMAL(20,0),
CusPass CHAR (30),
newPass CHAR (30))
begin
UPDATE Customer
SET cuspassword = newPass #Set the new password
where customerID=cusID and cuspassword = cuspass; #Only if the IDs and passwords match
END
$$
DELIMITER ;

# EX: call UpdatePassword ('234', 'password', 'pass');


###Creates a new user account

DELIMITER $$
CREATE PROCEDURE AccountCreation
(CustomerID DECIMAL(20,0),
CusName CHAR(50),
Phone CHAR(15),
Email CHAR (40),
CusPassword CHAR (30))
begin
INSERT INTO Customer
VALUES (CustomerID, cusname, phone, email, cuspassword);
END
$$
DELIMITER ;


# Ex: CALL accountCreation (2356, 'Barnibus', '5556669876', 'barnibus@erbottlewat.com', 'stuff');

###Searches for a book inside the Book table and returns its information, if it exists

DELIMITER $$
create procedure bookSearch
(Auth CHAR(50),
Ti CHAR(80),
PYear Decimal(4,0),
Cat CHAR(20))
begin
SELECT Author, Title, PubYear, Category 
FROM books
where author = auth or title = ti or PYear = pubyear or category = cat;
END
$$
DELIMITER ;

# Ex. call bookSearch ('235', '234', '3454', '24234');




###This procedure increases the counter value in the counter table by 1

#drop procedure if exists updateCounter;
DELIMITER $$
create Procedure updateCounter()
begin
update counterTable set counter = counter+1;
end
$$
delimiter ;

### Procedure returns the value of the counter in the counterTable

#drop procedure if exists project.returnCounter;
DELIMITER $$
create procedure returnCounter()
begin
select counter from counterTable;
END
$$
DELIMITER ;

###Procedure to create a new, empty cart

#drop procedure if exists project.createCart;
DELIMITER $$
create procedure createCart
(CusID DECIMAL (8,0),
CName CHAR(50))
begin
call updateCounter();
INSERT INTO cart
values ((select counter from counterTable where id = 1), CusID, Cname, curdate(), curdate(), 0, 0); #Set its creation/update date to now
END
$$
DELIMITER ;

#ex. call createCart ('1', 'bonks');


###Adds a book to a cart. IT IS ASSUMED THAT THE BOOK IS UNIQUE. Adding duplicates will be handled in the interface

DELIMITER $$
create procedure addToCart
(CartID DECIMAL (8,0), ISBN DECIMAL(20,0),
quantity DECIMAL(4,0))
begin
INSERT INTO booksInCart
values (cartID, ISBN, Quantity);
END
$$
DELIMITER ;

# Ex: call addtocart (22222, 33333, 4);

###Deletes a book from the cart. Deletes ALL copies; reducing quantity will be dealt with inside the interface.

delimiter $$
create procedure deletefromcart
(CID DECIMAL (8,0), ISB DECIMAL(20,0))
begin
delete FROM booksincart
where CID = CartID and ISB = ISBN;
END
$$
DELIMITER ;

#ex. call deletefromCart (1135, 5556);a

###Procedure that merges two carts into a new cart. It is ASSUMED THAT NEITHER CART CONTAINS THE SAME BOOK.

#drop procedure if exists mergecarts;
DELIMITER $$
create procedure mergeCarts
(CartID1 DECIMAL (8,0), CartID2 DECIMAL (8,0), cID DECIMAL(20,0))
begin
#Sets various variables and initiates them with subqueries
set @userName = (select Cusname from customer where customerid=cid);
call updateCounter(); #Increment the counter 'variable' before creating a new cart
set @varquantity = (select quantity from project.cart where cartid = cartid1)+(select quantity from project.cart where cartid =cartid2);
set @varprice = (select totalprice from project.cart where cartid = cartid1)+(select totalprice from project.cart where cartid=cartid2);

#Take all the variables you just created and use them to create a new cart
INSERT INTO cart values ((select counter from counterTable where id = 1), CID, @userName, curdate(), curdate(), @varprice, @varquantity);

#Take the old booksincart rows that have the same cartID as the carts being merged and change their
#cartIDs to the new cart (counterTable holds the current, new cartID)
UPDATE booksincart SET cartid=(select counter from counterTable where id = 1) where booksincart.cartid = cartid1 or booksincart.cartid = cartid2;
#Delete the old carts

delete from cart where cartid = cartid1 or cartid = cartid2;
END
$$
DELIMITER ;

DELIMITER $$

# Ex: call mergecarts (1112,1113,1114);

###Moves the books from the cart into the order. Used by Interface ONLY. IT IS ASSUMED ISBNS ARE UNIQUE.

#drop procedure if exists mergebooks;
DELIMITER $$
Create Procedure MergeBooks
(CID decimal(20,0), ISB Decimal (20,0), quantity decimal (10,0))
BEGIN
set @temp = (select price from books where ISBN = ISB);
Insert INTO BooksOrdered values (CID,  ISB, @temp, quantity);
delete from booksInCart where ISBN = ISB and CartID = CID;
END
$$
Delimiter ;

#call mergebooks(1122, 11111, 4);

###Employee function that pulls up order information for employees

#drop procedure if exists ordertrace;
DELIMITER $$
CREATE procedure  ordertrace
(Ordn Decimal (20,0))
begin
select orderstatus
from orders
where ordernum = ordn;
end
$$
DELIMITER ;

#Ex. call ordertrace (1);

###Places an order by moving the cart to the Order table

#drop procedure if exists placeorder;
delimiter $$
create procedure placeOrder
(carid decimal (20,0), cID decimal (20,0), empid decimal (20,0), shipadd char(50), billAdd char(50), cc decimal (20,0))
begin
#set @bordered = (select quantity from cart where cartid = cid);
#set @bcusname = (select cartname from cart where cartid = cid);
#set @bcustid = (select customerid from cart where cartid = cid);
#set @bdc = (select datecreated from cart where cartid = cid);
set @btp = (select totalprice from cart where cartid=carid);

insert into orders values (carid, cid, empid, curdate(), 'processing', shipadd, billadd, 0, @btp, cc);
delete from cart where cartid = carid;
end
$$
delimiter ;

#Ex. call placeOrder (1140, 12345, 54321, 'Blerp', 'Bweop', 245098425);

###Looks up a book by its ISBN

delimiter $$
create procedure bookInfo
(ISB decimal (20,0))
begin
select * from books where ISBN = ISB;
end
$$
delimiter ;

#Ex. call bookinfo(5555);

###Displays all the orders by the employee that represents them.

delimiter $$ 
create procedure orderRep
(rep decimal (20,0))
begin
select * from orders where employeeID = rep;
end
$$
delimiter ;

#Ex. call orderRep(54321);

###Procedure that updates the order status of an order; takes a string that replaces the current order status

delimiter $$
create procedure updateStatus
(ordn decimal (20,0), stat char (50))
begin
update orders set orderStatus = stat where ordernum = ordn;
end
$$
delimiter ;

#Ex. call updatestatus(1, 'shipped');

###Adds a book to the Books table. It automatically determines whether or not the quantity should be increased
###or a new book should be added

delimiter $$
create procedure addBook
(ISB DECIMAL(20,0),
Author CHAR(50),
Title CHAR(80),
Edition CHAR(5),
PubYear Decimal(4,0),
Category CHAR(20),
Publisher CHAR(30),
Q Decimal(4,0),
Price Decimal (5,2))
begin

if (select ISBN from books where ISBN = ISB) = ISB #If the book already exists, simply increase the quantity
then
update books set quantity = quantity + Q where ISBN = ISB;

else #Otherwise, enter into the table another row for a new book
insert into books values (ISB, Author, Title, edition, pubyear, category, publisher, Q, price);
END IF;

end
$$
delimiter ;

# Ex: call addbook (5566, 'Tom Clancy', 'THE SUM OF ALL FEARS', '1st', 1992, 'Military', 'Penguin', 13, 9.99);

###Return a user's information

DELIMITER $$
Create Procedure loginInfo
(cid decimal (20,0), pass char(50))

begin
select * from customer where customerid = cid and cusPassword = pass;
end
$$
delimiter ;


# Ex: call logininfo (2356, 'bananas');



#####Example Values#####
####################################################################################################################

insert into Books values (11111, 'John_Grisham', 'A_Time_to_Kill', '1st', '1990', 'Suspense', 'Penguin', 10, 25.00);
insert into Books values (22222, 'Plato', 'Utopia', '1st',  '645', 'Philosophy', 'Some_guy', 1, 500);
insert into Books values (33333, 'JK_Rowling', 'Harry_Potter', '2nd',  '1999', 'Fantasy', 'Scholastic', 20, 15);
insert into Books values (44444, 'James_Patternson', '7th Heaven', '1st',  '2004', 'Thriller', '?', 4, 10);
insert into Books values (55555, 'JRR_Tolkien', 'LOTR', '3rd',  '1964', 'Fantasy', 'Dudemcgee', 40, 14.99);
insert into Books values (66666, 'Stephen_J_Fry', 'The_Scary_Door', 'last',  '3003', 'Horror', 'Lurr', 5, 4.99);

insert into customer values (1, 'Bob', 5555555555, 'lol@yolo.com', 'password');
insert into customer values (2, 'Joe', 6666666666, 'rofl@copter.com', 'wordpass');
insert into customer values (3, 'Larry', 7777777777, 'loller@skates.com', 'erbottlewat');


Insert into Employee values (10, 'John', '22_Jump_Street', 9001, 20);
Insert into Employee values (20, 'Jacob', '123_SOMEPLACE', 1, 20);
Insert into Employee values (30, 'Jingleheimerschmidt', '21_Jump_Street', 18002, 10);