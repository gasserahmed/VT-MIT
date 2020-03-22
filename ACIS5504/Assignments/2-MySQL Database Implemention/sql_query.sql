--2
CREATE DATABASE PineValleyFurniture;
USE PineValleyFurniture;
CREATE TABLE Customer_t (
Customer_ID INTEGER,
Customer_Name CHAR (30),
Customer_Address VARCHAR (30),
City CHAR (30),
State CHAR (30),
Postal_Code VARCHAR (5),
PRIMARY KEY (Customer_ID));

CREATE TABLE Product_t (
Product_ID INTEGER,
Product_Description CHAR (30),
Product_Finish CHAR (30),
Standard_Price INTEGER,
Product_Line_ID INTEGER,
PRIMARY KEY (Product_ID));

CREATE TABLE Order_t (
Order_ID INTEGER,
Order_Date DATE,
Customer_ID INTEGER,
PRIMARY KEY (Order_ID));

CREATE TABLE Order_Line_t (
Order_ID INTEGER,
Product_ID INTEGER,
Quantity_Ordered INTEGER); 

INSERT INTO Customer_t VALUES (1, “Contemporary Casuals”, “1355 S. Hines Blvd.”, “Gainesville”, “FL”, 32601);

INSERT INTO Customer_t VALUES (2, “Value Furniture”, “15145 S.W. 17th St.”, “Plano”, “TX”, 75094);

INSERT INTO Customer_t VALUES (3, “Home Furnishings”, “1900 Allard Ave.”, “Albany”, “NY”, 12209);

INSERT INTO Customer_t VALUES (4, “Eastern Furniture”, “1925 Beltline Rd.”, “Carteret”, “NJ”, 07008);

INSERT INTO Customer_t VALUES (5, “Impressions”, “5595 Westcott Ct.”, “Sacramento”, “CA”, 94206);

INSERT INTO Customer_t VALUES (6, “Furniture Gallery”, “325 Flatiron Dr.”, “Boulder”, “CO”, 80514);

INSERT INTO Customer_t VALUES (7, “Period Furniture”, “394 Rainbow Dr.”, “Seattle”, “WA”, 97954);

INSERT INTO Customer_t VALUES (8, “California Classics”, “816 Peach Rd.”, “Santa Clara”, “CA”, 69615);

INSERT INTO Customer_t VALUES (9, “M and H Casual Furniture”, “3709 First Street”, “Clearwater”, “FL”, 34620);

INSERT INTO Customer_t VALUES (10, “Seminole Interiors”, “2400 Rocky Point Dr.”, “Seminole”, “FL”, 34646);

INSERT INTO Customer_t VALUES (11, “American Euro Lifestyles”, “2424 Missouri Ave N.”, “Prospect Park”, “NJ”, 07508);

INSERT INTO Customer_t VALUES (12, “Battle Creek Furniture”, “345 Capitol Ave SW”, “Battle Creek”, “MI”, 49015);

INSERT INTO Customer_t VALUES (13, “Heritage Furnishings”, “66789 College Ave”, “Carlisle”, “PA”, 17013);

INSERT INTO Customer_t VALUES (14, “Kaneohe Homes”, “112 Kiowai St.”, “Kaneohe”, “HI”, 96744);

INSERT INTO Customer_t VALUES (15, “Mountain Scenes”, “4132 Main Street”, “Ogden”, “UT”, 84403);

-----

INSERT INTO Product_t VALUES (1, “End Table”, “Cherry”, 175, 1);
INSERT INTO Product_t VALUES (2, “Coffee Table”, “Natural Ash”, 200, 2);
INSERT INTO Product_t VALUES (3, “Computer Desk”, “Natural Ash”, 375, 2);
INSERT INTO Product_t VALUES (4, “Entertainment Center”, “Natural Maple”, 650, 3);
INSERT INTO Product_t VALUES (5, “Writers Desk”, “Cherry”, 325, 1);
INSERT INTO Product_t VALUES (6, “8 Drawer Desk”, “White Ash”, 750, 2);
INSERT INTO Product_t VALUES (7, “Dining Table”, “Natural Ash”, 800, 2);
INSERT INTO Product_t VALUES (8, “Computer Desk”, “Walnut”, 250, 3);

 

-----

INSERT INTO Order_Line_t VALUES (1001, 1, 2);
INSERT INTO Order_Line_t VALUES (1001, 2, 2);
INSERT INTO Order_Line_t VALUES (1001, 4, 1);
INSERT INTO Order_Line_t VALUES (1002, 3, 5);
INSERT INTO Order_Line_t VALUES (1003, 3, 3);
INSERT INTO Order_Line_t VALUES (1004, 6, 2);
INSERT INTO Order_Line_t VALUES (1004, 8, 2);
INSERT INTO Order_Line_t VALUES (1005, 4, 4);
INSERT INTO Order_Line_t VALUES (1006, 4, 1);
INSERT INTO Order_Line_t VALUES (1006, 5, 2);
INSERT INTO Order_Line_t VALUES (1006, 7, 2);
INSERT INTO Order_Line_t VALUES (1007, 1, 3);
INSERT INTO Order_Line_t VALUES (1007, 2, 2);
INSERT INTO Order_Line_t VALUES (1008, 3, 3);
INSERT INTO Order_Line_t VALUES (1008, 8, 3);
INSERT INTO Order_Line_t VALUES (1009, 4, 2);
INSERT INTO Order_Line_t VALUES (1009, 7, 3);
INSERT INTO Order_Line_t VALUES (1010, 8, 10);

 

-----

INSERT INTO Order_t VALUES (1001, “2004-10-21”, 1);
INSERT INTO Order_t VALUES (1002, “2004-10-21”, 8);
INSERT INTO Order_t VALUES (1003, “2004-10-22”, 15);
INSERT INTO Order_t VALUES (1004, “2004-10-22”, 5);
INSERT INTO Order_t VALUES (1005, “2004-10-24”, 3);
INSERT INTO Order_t VALUES (1006, “2004-10-24”, 2);
INSERT INTO Order_t VALUES (1007, “2004-10-27”, 11);
INSERT INTO Order_t VALUES (1008, “2004-10-30”, 12);
INSERT INTO Order_t VALUES (1009, “2004-11-05”, 4);
INSERT INTO Order_t VALUES (1010, “2004-11-05”, 1);

--3
SELECT PRODUCT_ID, PRODUCT_DESCRIPTION, STANDARD_PRICE
FROM PRODUCT_T;

--4
SELECT PRODUCT_DESCRIPTION, STANDARD_PRICE
FROM PRODUCT_T
WHERE STANDARD_PRICE < 275;

--5
SELECT AVG(STANDARD_PRICE)
FROM PRODUCT_T;

--6
SELECT COUNT(*)
FROM Order_Line_t
WHERE Order_ID = 1004;

--7
SELECT Order_ID, Order_Date
FROM Order_t
WHERE Order_Date > "2004-10-24";

--8
SELECT PRODUCT_DESCRIPTION, Product_Finish
FROM PRODUCT_t
WHERE Product_Finish != "Cherry";

--9
SELECT *
FROM Customer_t
WHERE State IN ("FL", "TX", "CA", "HI")
ORDER BY STATE, Customer_Name;

--10
SELECT STATE, COUNT(*) AS CUSTOMER_NUM
FROM Customer_t
GROUP BY STATE
HAVING COUNT(*) > 1;

--11
SELECT Product_Finish, AVG(STANDARD_PRICE) AS AVG_PRICE
FROM PRODUCT_t
GROUP BY Product_Finish
HAVING AVG(STANDARD_PRICE) < 750;

--12
SELECT PRODUCT_ID, PRODUCT_DESCRIPTION, Product_Finish, STANDARD_PRICE
FROM PRODUCT_t
WHERE STANDARD_PRICE < (SELECT AVG(STANDARD_PRICE)
						FROM PRODUCT_t);

--13
CREATE
VIEW Product_V
AS SELECT PRODUCT_DESCRIPTION, Product_Finish, STANDARD_PRICE FROM PRODUCT_T;

SELECT *
FROM Product_V
WHERE (PRODUCT_DESCRIPTION LIKE "%Desk%" OR PRODUCT_DESCRIPTION LIKE "%Table%") AND STANDARD_PRICE > 300;

--14
CREATE INDEX Furniture ON PRODUCT_t(Product_Finish);

SELECT *
FROM PRODUCT_t
USE INDEX (Furniture);