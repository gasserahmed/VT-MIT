/* DW-DBINIT.SQL                                    	*/
/* DATA WAREHOUSE SQL 					*/
/* Script file for MySQL DBMS			*/
/* This script file creates the following tables:	*/
/* TimeDim, RoomDim, RoomTypeDim, RoomReservationDim, FloorDim,	*/
/* WingDim, BuildingDim, ResortDim, CityDim, RegionDim, CountryDim	*/
/* EventDim, CustomerDim, CustomerAffiliationDim*/
/* RoomTypeReservationsFact, CustomerReservationsFact, EventReservationsFact	*/
/* and loads the default data rows			*/

DROP TABLE RoomTypeReservationsFact;
DROP TABLE CustomerReservationsFact;
DROP TABLE EventReservationsFact;
DROP TABLE TimeDim;
DROP TABLE RoomDim;
DROP TABLE RoomReservationDim;
DROP TABLE RoomTypeDim;
DROP TABLE FloorDim;
DROP TABLE WingDim;
DROP TABLE BuildingDim;
DROP TABLE ResortDim;
DROP TABLE CityDim;
DROP TABLE RegionDim;
DROP TABLE CountryDim;
DROP TABLE CustomerAffiliationDim;
DROP TABLE EventDim;
DROP TABLE CustomerDim;

/* TimeDim */
CREATE TABLE TimeDim
(
C_TimeID_TI INT PRIMARY KEY,
N_TimeYear_TI INT NOT NULL,
N_TimeMonth_TI INT NOT NULL,
T_TimeDay_TI VARCHAR(50) NOT NULL
);

/* RoomTypeDim */
CREATE TABLE RoomTypeDim
(
C_RoomType_RT VARCHAR(50) PRIMARY KEY,
N_RoomPrice_RT DECIMAL NOT NULL
);

/* CountryDim */
CREATE TABLE CountryDim
(
C_CountryCode_CO VARCHAR(5) PRIMARY KEY,
T_CountryName_CO VARCHAR(50) NOT NULL
);

/* RegionDim */
CREATE TABLE RegionDim
(
C_RegionID_RG INT PRIMARY KEY,
T_RegionName_RG VARCHAR(50) NOT NULL,
C_CountryCode_RG VARCHAR(5) NOT NULL,
FOREIGN KEY (C_CountryCode_RG) REFERENCES CountryDim(C_CountryCode_CO)
);

/* CityDim */
CREATE TABLE CityDim
(
C_CityName_CI VARCHAR(50),
C_ZipCode_CI VARCHAR(50),
T_State_CI VARCHAR(50) NOT NULL,
C_RegionID_CI INT NOT NULL,
FOREIGN KEY (C_RegionID_CI) REFERENCES RegionDim(C_RegionID_RG),
PRIMARY KEY(C_CityName_CI, C_ZipCode_CI)
);

/* ResortDim */
CREATE TABLE ResortDim
(
C_ResortID_RS INT PRIMARY KEY,
T_ResortName_RS VARCHAR(50) NOT NULL,
T_StreetAddress_RS VARCHAR(255) NOT NULL,
C_CityName_RS VARCHAR(50) NOT NULL,
FOREIGN KEY (C_CityName_RS) REFERENCES CityDim(C_CityName_CI)
);

/* BuildingDim */
CREATE TABLE BuildingDim
(
C_BuildingID_BU INT PRIMARY KEY,
T_BuildingName_BU VARCHAR(50) NOT NULL,
T_BuildingDescription_BU VARCHAR(255) NOT NULL,
C_ResortID_BU INT NOT NULL,
FOREIGN KEY (C_ResortID_BU) REFERENCES ResortDim(C_ResortID_RS)
);

/* WingDim */
CREATE TABLE WingDim
(
C_WingCode_WG VARCHAR(10) NOT NULL,
C_BuildingID_WG INT NOT NULL,
T_WingName_WG VARCHAR(50) NOT NULL,
PRIMARY KEY(C_WingCode_WG, C_BuildingID_WG)
);

/* FloorDim */
CREATE TABLE FloorDim
(
C_FloorNumber_FL INT NOT NULL,
C_BuildingID_FL INT NOT NULL,
C_WingCode_FL VARCHAR(10) NOT NULL,
PRIMARY KEY(C_FloorNumber_FL, C_BuildingID_FL, C_WingCode_FL)
);

/* RoomDim */
CREATE TABLE RoomDim
(
C_RoomNum_RM VARCHAR(10) NOT NULL,
C_FloorNumber_RM INT NOT NULL,
C_WingCode_RM VARCHAR(10) NOT NULL,
C_RoomType_RM VARCHAR(50) NOT NULL,
PRIMARY KEY(C_RoomNum_RM, C_FloorNumber_RM, C_WingCode_RM),
FOREIGN KEY (C_RoomType_RM) REFERENCES RoomTypeDim(C_RoomType_RT)
);

/* EventDim */
CREATE TABLE EventDim
(
C_EventID_EV INT NOT NULL PRIMARY KEY,
T_EventDescription_EV VARCHAR(255) NOT NULL,
D_EventStartDate_EV DATETIME NOT NULL,
D_EventEndDate_EV DATETIME NOT NULL
);

/* CustomerDim */
CREATE TABLE CustomerDim
(
C_CustomerID_CU INT NOT NULL PRIMARY KEY,
T_CustomerFirstName_CU VARCHAR(50) NOT NULL,
T_CustomerLastName_CU VARCHAR(50) NOT NULL,
T_CustomerMiddleName_CU VARCHAR(50),
T_CustomerAddress_CU VARCHAR(255),
I_CustomerPhoneNumber_CU VARCHAR(50) NOT NULL
);

/* RoomReservationDim */
CREATE TABLE RoomReservationDim
(
C_RoomReservationNum_RR VARCHAR(50),
C_CustomerID_RR INT,
C_RoomType_RR VARCHAR(50),
D_ArrivalDate_RR DATETIME NOT NULL,
D_DepartureDate_RR DATETIME NOT NULL,
FOREIGN KEY (C_CustomerID_RR) REFERENCES CustomerDim(C_CustomerID_CU),
FOREIGN KEY (C_RoomType_RR) REFERENCES RoomTypeDim(C_RoomType_RT),
PRIMARY KEY(C_RoomReservationNum_RR, C_CustomerID_RR, C_RoomType_RR)
);

/* CustomerAffiliationDim */
CREATE TABLE CustomerAffiliationDim
(
C_CustomerID_CA INT NOT NULL,
C_EventID_CA INT NOT NULL,
FOREIGN KEY (C_CustomerID_CA) REFERENCES CustomerDim(C_CustomerID_CU),
FOREIGN KEY (C_EventID_CA) REFERENCES EventDim(C_EventID_EV),
PRIMARY KEY(C_CustomerID_CA, C_EventID_CA)
);

/* RoomTypeReservationsFact */
CREATE TABLE RoomTypeReservationsFact
(
C_TimeID_TR INT NOT NULL,
T_RoomType_TR VARCHAR(50) NOT NULL,
C_ResortID_TR INT NOT NULL,
N_TotalNumOfStays_TR DECIMAL,
N_TotalPrice_TR DECIMAL,
FOREIGN KEY (C_TimeID_TR) REFERENCES TimeDim(C_TimeID_TI),
FOREIGN KEY (T_RoomType_TR) REFERENCES RoomTypeDim(C_RoomType_RT),
FOREIGN KEY (C_ResortID_TR) REFERENCES ResortDim(C_ResortID_RS),
PRIMARY KEY(C_TimeID_TR, T_RoomType_TR, C_ResortID_TR)
);

/* CustomerReservationsFact */
CREATE TABLE CustomerReservationsFact
(
C_TimeID_CR INT NOT NULL,
C_CustomerID_CR INT NOT NULL,
C_ResortID_CR INT NOT NULL,
N_TotalNumOfStays_CR DECIMAL,
N_TotalPrice_CR DECIMAL,
FOREIGN KEY (C_TimeID_CR) REFERENCES TimeDim(C_TimeID_TI),
FOREIGN KEY (C_CustomerID_CR) REFERENCES CustomerDim(C_CustomerID_CU),
FOREIGN KEY (C_ResortID_CR) REFERENCES ResortDim(C_ResortID_RS),
PRIMARY KEY(C_TimeID_CR, C_CustomerID_CR, C_ResortID_CR)
);

/* EventReservationsFact */
CREATE TABLE EventReservationsFact
(
C_TimeID_ER INT NOT NULL,
C_EventID_ER INT NOT NULL,
C_ResortID_ER INT NOT NULL,
N_TotalNumOfStays_ER DECIMAL,
N_TotalPrice_ER DECIMAL,
FOREIGN KEY (C_TimeID_ER) REFERENCES TimeDim(C_TimeID_TI),
FOREIGN KEY (C_EventID_ER) REFERENCES EventDim(C_EventID_EV),
FOREIGN KEY (C_ResortID_ER) REFERENCES ResortDim(C_ResortID_RS),
PRIMARY KEY(C_TimeID_ER, C_EventID_ER, C_ResortID_ER)
);

/* TimeDim rows						*/
INSERT INTO TimeDim VALUES(1, 2018, 09, 29);
INSERT INTO TimeDim VALUES(2, 2018, 09, 30);
INSERT INTO TimeDim VALUES(3, 2018, 09, 31);
INSERT INTO TimeDim VALUES(4, 2018, 10, 03);
INSERT INTO TimeDim VALUES(5, 2018, 10, 04);
INSERT INTO TimeDim VALUES(6, 2018, 10, 05);
INSERT INTO TimeDim VALUES(7, 2018, 10, 06);
INSERT INTO TimeDim VALUES(8, 2018, 10, 07);
INSERT INTO TimeDim VALUES(9, 2018, 10, 08);
INSERT INTO TimeDim VALUES(10, 2018, 10, 09);
INSERT INTO TimeDim VALUES(11, 2018, 10, 10);
INSERT INTO TimeDim VALUES(12, 2018, 11, 10);
INSERT INTO TimeDim VALUES(13, 2019, 12, 10);
INSERT INTO TimeDim VALUES(14, 2019, 12, 11);
INSERT INTO TimeDim VALUES(15, 2019, 11, 12);
INSERT INTO TimeDim VALUES(16, 2019, 10, 13);
INSERT INTO TimeDim VALUES(17, 2019, 10, 14);
INSERT INTO TimeDim VALUES(18, 2019, 10, 14);
INSERT INTO TimeDim VALUES(19, 2019, 10, 16);
INSERT INTO TimeDim VALUES(20, 2019, 10, 17);
INSERT INTO TimeDim VALUES(21, 2019, 10, 17);
INSERT INTO TimeDim VALUES(22, 2019, 10, 17);
INSERT INTO TimeDim VALUES(23, 2019, 10, 18);
INSERT INTO TimeDim VALUES(24, 2019, 10, 19);
INSERT INTO TimeDim VALUES(25, 2019, 10, 20);
INSERT INTO TimeDim VALUES(26, 2019, 10, 21);
INSERT INTO TimeDim VALUES(27, 2019, 10, 2);
INSERT INTO TimeDim VALUES(28, 2019, 10, 3);
INSERT INTO TimeDim VALUES(29, 2019, 10, 4);
INSERT INTO TimeDim VALUES(30, 2019, 10, 5);
INSERT INTO TimeDim VALUES(31, 2019, 10, 6);
INSERT INTO TimeDim VALUES(32, 2019, 10, 7);
INSERT INTO TimeDim VALUES(33, 2019, 10, 8);
INSERT INTO TimeDim VALUES(34, 2019, 10, 9);
INSERT INTO TimeDim VALUES(35, 2019, 10, 10);
INSERT INTO TimeDim VALUES(36, 2019, 10, 11);
INSERT INTO TimeDim VALUES(37, 2019, 10, 12);

/* RoomTypeDim rows						*/
INSERT INTO RoomTypeDim VALUES('SINGLE', 80.0);
INSERT INTO RoomTypeDim VALUES('DOUBLE', 90.0);
INSERT INTO RoomTypeDim VALUES('TRIPLE', 100.0);
INSERT INTO RoomTypeDim VALUES('MULTI', 105.0);

/* CountryDim rows						*/
INSERT INTO CountryDim VALUES('US','United States');
INSERT INTO CountryDim VALUES('EG','Egypt');
INSERT INTO CountryDim VALUES('JP','Japan');

/* RegionDim rows						*/
INSERT INTO RegionDim VALUES(1,'North-West', 'US');
INSERT INTO RegionDim VALUES(2,'Middle-East', 'EG');
INSERT INTO RegionDim VALUES(3,'EAST', 'JP');

/* CityDim rows						*/
INSERT INTO CityDim VALUES('Blacksburg','24060','Virginia', 1);
INSERT INTO CityDim VALUES('Arlington','76010','Texas', 1);
INSERT INTO CityDim VALUES('Houston','77092','Texas', 1);

/* ResortDim rows						*/
INSERT INTO ResortDim VALUES(1,'Hokie East', '131 Patrick Dr.','Blacksburg');
INSERT INTO ResortDim VALUES(2,'Hokie Central', '79 Watonga Blvd.','Houston');
INSERT INTO ResortDim VALUES(3,'Hokie DFW', '209 Elm St.','Arlington');

/* BuildingDim rows						*/
INSERT INTO BuildingDim VALUES(1,'Main','Central Building', 1);
INSERT INTO BuildingDim VALUES(2,'Hokies','Hokies Building', 1);
INSERT INTO BuildingDim VALUES(3,'Innovation','Innovation Building', 1);
INSERT INTO BuildingDim VALUES(4,'Main','Central Building', 2);
INSERT INTO BuildingDim VALUES(5,'Hokies','Hokies Building', 2);
INSERT INTO BuildingDim VALUES(6,'Innovation','Innovation Building', 2);
INSERT INTO BuildingDim VALUES(7,'Main','Central Building', 3);
INSERT INTO BuildingDim VALUES(8,'Hokies','Hokies Building', 3);
INSERT INTO BuildingDim VALUES(9,'Innovation','Innovation Building', 3);

/* WingDim rows						*/
INSERT INTO WingDim VALUES('E', 1, 'East');
INSERT INTO WingDim VALUES('W', 1, 'West');
INSERT INTO WingDim VALUES('N', 1, 'North');
INSERT INTO WingDim VALUES('S', 1, 'South');
INSERT INTO WingDim VALUES('E', 2, 'East');
INSERT INTO WingDim VALUES('W', 2, 'West');
INSERT INTO WingDim VALUES('N', 2, 'North');
INSERT INTO WingDim VALUES('S', 2, 'South');
INSERT INTO WingDim VALUES('E', 3, 'East');
INSERT INTO WingDim VALUES('W', 3, 'West');
INSERT INTO WingDim VALUES('N', 3, 'North');
INSERT INTO WingDim VALUES('S', 3, 'South');
INSERT INTO WingDim VALUES('E', 4, 'East');
INSERT INTO WingDim VALUES('W', 4, 'West');
INSERT INTO WingDim VALUES('N', 4, 'North');
INSERT INTO WingDim VALUES('S', 4, 'South');

/* FloorDim rows						*/
INSERT INTO FloorDim VALUES(1, 1, 'W');
INSERT INTO FloorDim VALUES(2, 1, 'W');
INSERT INTO FloorDim VALUES(3, 1, 'W');
INSERT INTO FloorDim VALUES(4, 1, 'W');
INSERT INTO FloorDim VALUES(1, 1, 'E');
INSERT INTO FloorDim VALUES(2, 1, 'E');
INSERT INTO FloorDim VALUES(3, 1, 'E');
INSERT INTO FloorDim VALUES(4, 1, 'E');
INSERT INTO FloorDim VALUES(1, 1, 'N');
INSERT INTO FloorDim VALUES(2, 1, 'N');
INSERT INTO FloorDim VALUES(3, 1, 'N');
INSERT INTO FloorDim VALUES(4, 1, 'N');
INSERT INTO FloorDim VALUES(1, 1, 'S');
INSERT INTO FloorDim VALUES(2, 1, 'S');
INSERT INTO FloorDim VALUES(3, 1, 'S');
INSERT INTO FloorDim VALUES(4, 1, 'S');

/* RoomDim rows						*/
INSERT INTO RoomDim VALUES('W1-100', 1, 'W', 'SINGLE');
INSERT INTO RoomDim VALUES('N2-203', 2, 'N', 'MULTI');
INSERT INTO RoomDim VALUES('S4-404', 4, 'S', 'SINGLE');
INSERT INTO RoomDim VALUES('E3-310', 3, 'E', 'DOUBLE');
INSERT INTO RoomDim VALUES('W3-305', 3, 'W', 'TRIPLE');
INSERT INTO RoomDim VALUES('E2-201', 2, 'E', 'MULTI');
INSERT INTO RoomDim VALUES('S1-107', 1, 'S', 'DOUBLE');
INSERT INTO RoomDim VALUES('N4-409', 4, 'N', 'SINGLE');

/* EventDim rows						*/
INSERT INTO EventDim VALUES(1,'ColdPlay Concert', '2010-04-25 07:00:00','2010-04-30 10:15:00');
INSERT INTO EventDim VALUES(2,'Hokies Reunion', '2011-07-15 12:00:00','2011-07-15 18:00:00');

/* CustomerDim rows						*/
INSERT INTO CustomerDim VALUES(1, 'John', 'Smith', '', '513 Main St.', '205-814-2233');
INSERT INTO CustomerDim VALUES(2, 'Sarah', 'Kent', 'T.', '20 South St.', '205-600-1024');
INSERT INTO CustomerDim VALUES(3, 'Mariam', 'Hafez', '', '19 Roanoke St.', '817-245-4000');
INSERT INTO CustomerDim VALUES(4, 'Jordan', 'Saba', '', '37 N St.', '205-112-1134');
INSERT INTO CustomerDim VALUES(5, 'Zack', 'Nolan', '', '1700 Pratt St.', '205-406-7780');

/* RoomReservationDim rows						*/
INSERT INTO RoomReservationDim VALUES(1, 1, 'MULTI', '2010-04-25 15:00:00', '2010-04-26 11:01:00');
INSERT INTO RoomReservationDim VALUES(2, 2, 'SINGLE', '2010-04-25 15:30:00', '2010-04-26 10:30:00');
INSERT INTO RoomReservationDim VALUES(3, 3, 'DOUBLE', '2010-04-25 18:00:00', '2010-04-26 11:05:00');
INSERT INTO RoomReservationDim VALUES(4, 4, 'SINGLE', '2010-04-25 16:09:00', '2010-04-26 09:03:00');

/* CustomerAffiliationDim rows						*/
INSERT INTO CustomerAffiliationDim VALUES(1, 1);
INSERT INTO CustomerAffiliationDim VALUES(2, 1);
INSERT INTO CustomerAffiliationDim VALUES(3, 1);
INSERT INTO CustomerAffiliationDim VALUES(4, 1);
INSERT INTO CustomerAffiliationDim VALUES(5, 1);
INSERT INTO CustomerAffiliationDim VALUES(3, 2);
INSERT INTO CustomerAffiliationDim VALUES(4, 2);

/* RoomTypeReservationsFact rows						*/
INSERT INTO RoomTypeReservationsFact VALUES(1, 'SINGLE', 1, 2, 160.0);
INSERT INTO RoomTypeReservationsFact VALUES(2, 'DOUBLE', 1, 1, 90.0);
INSERT INTO RoomTypeReservationsFact VALUES(24, 'MULTI', 1, 1, 105.0);
INSERT INTO RoomTypeReservationsFact VALUES(3, 'MULTI', 1, 1, 105.0);
INSERT INTO RoomTypeReservationsFact VALUES(4, 'SINGLE', 1, 1, 80.0);
INSERT INTO RoomTypeReservationsFact VALUES(12, 'SINGLE', 2, 1, 80.0);
INSERT INTO RoomTypeReservationsFact VALUES(13, 'SINGLE', 1, 1, 80.0);
INSERT INTO RoomTypeReservationsFact VALUES(14, 'MULTI', 1, 1, 105.0);
INSERT INTO RoomTypeReservationsFact VALUES(15, 'SINGLE', 1, 1, 80.0);
INSERT INTO RoomTypeReservationsFact VALUES(16, 'MULTI', 2, 1, 105.0);
INSERT INTO RoomTypeReservationsFact VALUES(17, 'MULTI', 1, 1, 105.0);
INSERT INTO RoomTypeReservationsFact VALUES(18, 'DOUBLE', 2, 1, 80.0);
INSERT INTO RoomTypeReservationsFact VALUES(25, 'MULTI', 2, 1, 105.0);
INSERT INTO RoomTypeReservationsFact VALUES(19, 'DOUBLE', 2, 1, 80.0);
INSERT INTO RoomTypeReservationsFact VALUES(20, 'MULTI', 2, 1, 105.0);
INSERT INTO RoomTypeReservationsFact VALUES(21, 'DOUBLE', 2, 1, 90.0);
INSERT INTO RoomTypeReservationsFact VALUES(22, 'SINGLE', 2, 1, 80.0);
INSERT INTO RoomTypeReservationsFact VALUES(23, 'DOUBLE', 1, 1, 90.0);
INSERT INTO RoomTypeReservationsFact VALUES(26, 'MULTI', 1, 1, 105.0);
INSERT INTO RoomTypeReservationsFact VALUES(37, 'SINGLE', 1, 1, 80.0);


/* CustomerReservationsFact rows						*/
INSERT INTO CustomerReservationsFact VALUES(1, 1, 1, 2, 160.0);
INSERT INTO CustomerReservationsFact VALUES(2, 2, 1, 1, 90.0);
INSERT INTO CustomerReservationsFact VALUES(24, 3, 1, 1, 105.0);
INSERT INTO CustomerReservationsFact VALUES(3, 4, 1, 1, 105.0);
INSERT INTO CustomerReservationsFact VALUES(4, 1, 1, 1, 80.0);
INSERT INTO CustomerReservationsFact VALUES(12, 2, 2, 1, 80.0);
INSERT INTO CustomerReservationsFact VALUES(13, 3, 1, 1, 80.0);
INSERT INTO CustomerReservationsFact VALUES(14, 4, 1, 1, 105.0);
INSERT INTO CustomerReservationsFact VALUES(15, 1, 1, 1, 80.0);
INSERT INTO CustomerReservationsFact VALUES(16, 2, 2, 1, 105.0);
INSERT INTO CustomerReservationsFact VALUES(17, 3, 1, 1, 105.0);
INSERT INTO CustomerReservationsFact VALUES(18, 4, 1, 1, 80.0);
INSERT INTO CustomerReservationsFact VALUES(25, 1, 2, 1, 105.0);
INSERT INTO CustomerReservationsFact VALUES(19, 2, 2, 1, 80.0);
INSERT INTO CustomerReservationsFact VALUES(20, 3, 2, 1, 105.0);
INSERT INTO CustomerReservationsFact VALUES(21, 4, 2, 1, 90.0);
INSERT INTO CustomerReservationsFact VALUES(22, 1, 2, 1, 80.0);
INSERT INTO CustomerReservationsFact VALUES(23, 2, 1, 1, 90.0);
INSERT INTO CustomerReservationsFact VALUES(26, 3, 1, 1, 105.0);

/* EventReservationsFact rows						*/
INSERT INTO EventReservationsFact VALUES(1, 1, 1, 1, 90.0);
INSERT INTO EventReservationsFact VALUES(2, 2, 1, 1, 80.0);
INSERT INTO EventReservationsFact VALUES(3, 2, 1, 1, 105.0);
INSERT INTO EventReservationsFact VALUES(4, 1, 1, 2, 160.0);
INSERT INTO EventReservationsFact VALUES(5, 1, 1, 1, 90.0);
INSERT INTO EventReservationsFact VALUES(6, 2, 1, 1, 80.0);
INSERT INTO EventReservationsFact VALUES(7, 2, 1, 1, 105.0);
INSERT INTO EventReservationsFact VALUES(8, 1, 1, 2, 160.0);
INSERT INTO EventReservationsFact VALUES(9, 1, 1, 2, 160.0);
INSERT INTO EventReservationsFact VALUES(10, 1, 1, 1, 90.0);
INSERT INTO EventReservationsFact VALUES(11, 2, 1, 1, 80.0);
INSERT INTO EventReservationsFact VALUES(12, 2, 1, 1, 105.0);
INSERT INTO EventReservationsFact VALUES(13, 1, 1, 2, 160.0);
INSERT INTO EventReservationsFact VALUES(14, 1, 1, 1, 90.0);
INSERT INTO EventReservationsFact VALUES(15, 2, 1, 1, 80.0);
INSERT INTO EventReservationsFact VALUES(16, 2, 1, 1, 105.0);
INSERT INTO EventReservationsFact VALUES(17, 1, 1, 2, 160.0);
INSERT INTO EventReservationsFact VALUES(18, 1, 1, 2, 160.0);
INSERT INTO EventReservationsFact VALUES(19, 1, 1, 1, 90.0);
INSERT INTO EventReservationsFact VALUES(20, 2, 1, 1, 80.0);
INSERT INTO EventReservationsFact VALUES(21, 2, 1, 1, 105.0);
INSERT INTO EventReservationsFact VALUES(22, 1, 1, 2, 160.0);
INSERT INTO EventReservationsFact VALUES(23, 1, 1, 1, 90.0);
INSERT INTO EventReservationsFact VALUES(24, 2, 1, 1, 80.0);
INSERT INTO EventReservationsFact VALUES(25, 2, 1, 1, 105.0);
INSERT INTO EventReservationsFact VALUES(26, 1, 1, 2, 160.0);
INSERT INTO EventReservationsFact VALUES(27, 1, 1, 2, 160.0);
INSERT INTO EventReservationsFact VALUES(28, 1, 1, 1, 90.0);
INSERT INTO EventReservationsFact VALUES(29, 2, 1, 1, 80.0);
INSERT INTO EventReservationsFact VALUES(30, 2, 1, 1, 105.0);
INSERT INTO EventReservationsFact VALUES(31, 1, 1, 2, 160.0);
INSERT INTO EventReservationsFact VALUES(32, 1, 1, 1, 90.0);
INSERT INTO EventReservationsFact VALUES(33, 2, 1, 1, 80.0);
INSERT INTO EventReservationsFact VALUES(34, 2, 1, 1, 105.0);
INSERT INTO EventReservationsFact VALUES(35, 1, 1, 2, 160.0);
INSERT INTO EventReservationsFact VALUES(36, 1, 1, 2, 160.0);

COMMIT;
