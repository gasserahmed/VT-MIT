/* DW-DBINIT.SQL                                    	*/
/* DATA WAREHOUSE SQL 					*/
/* Script file for ORACLE 9iR2 DBMS			*/
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
DROP TABLE RoomTypeDim;
DROP TABLE RoomReservationDim;
DROP TABLE FloorDim;
DROP TABLE WingDim;
DROP TABLE BuildingDim;
DROP TABLE ResortDim;
DROP TABLE CityDim;
DROP TABLE RegionDim;
DROP TABLE CountryDim;
DROP TABLE EventDim;
DROP TABLE CustomerDim;
DROP TABLE CustomerAffiliationDim;

--TimeDim
CREATE TABLE TimeDim
(
C_TimeID_TI INT PRIMARY KEY,
N_TimeYear_TI INT NOT NULL,
N_TimeMonth_TI INT NOT NULL,
T_TimeDay_TI VARCHAR(50) NOT NULL,
);

--RoomTypeDim
CREATE TABLE RoomTypeDim
(
C_RoomType_RT VARCHAR(50) PRIMARY KEY,
N_RoomPrice_RT DECIMAL NOT NULL
);

--CountryDim
CREATE TABLE CountryDim
(
C_CountryCode_CO VARCHAR(5) PRIMARY KEY,
T_CountryName_CO VARCHAR(50) NOT NULL
);

--RegionDim
CREATE TABLE RegionDim
(
C_RegionID_RG INT PRIMARY KEY,
T_RegionName_RG VARCHAR(50) NOT NULL,
C_CountryCode_RG VARCHAR(5) NOT NULL,
FOREIGN KEY (C_CountryCode_RG) REFERENCES CountryDim(C_CountryCode_CO)
);

--CityDim
CREATE TABLE CityDim
(
C_CityName_CI VARCHAR(50),
C_ZipCode_CI VARCHAR(50),
T_State_CI VARCHAR(50) NOT NULL,
C_RegionID_CI INT NOT NULL,
FOREIGN KEY (C_RegionID_CI) REFERENCES RegionDim(C_RegionID_RG),
PRIMARY KEY(C_CityName_CI, C_ZipCode_CI)
);

--ResortDim
CREATE TABLE ResortDim
(
C_ResortID_RS INT PRIMARY KEY,
T_ResortName_RS VARCHAR(50) NOT NULL,
T_StreetAddress_RS VARCHAR(255) NOT NULL,
C_CityName_RS VARCHAR(50) NOT NULL,
FOREIGN KEY (C_CityName_RS) REFERENCES CityDim(C_CityName_CI)
);

--BuildingDim
CREATE TABLE BuildingDim
(
C_BuildingID_BU INT PRIMARY KEY,
T_BuildingName_BU VARCHAR(50) NOT NULL,
T_BuildingDescription_BU VARCHAR(255) NOT NULL,
C_ResortID_BU INT NOT NULL,
FOREIGN KEY (C_ResortID_BU) REFERENCES ResortDim(C_ResortID_RS)
);

--WingDim
CREATE TABLE WingDim
(
C_WingCode_WG VARCHAR(10) NOT NULL,
C_BuildingID_WG INT NOT NULL,
T_WingName_WG VARCHAR(50) NOT NULL,
PRIMARY KEY(C_WingCode_WG, C_BuildingID_WG)
);

--FloorDim
CREATE TABLE FloorDim
(
C_FloorNumber_FL INT NOT NULL,
C_BuildingID_FL INT NOT NULL,
C_WingCode_FL VARCHAR(10) NOT NULL,
PRIMARY KEY(C_FloorNumber_FL, C_BuildingID_FL, C_WingCode_FL)
);

--RoomDim
CREATE TABLE RoomDim
(
C_RoomNum_RM VARCHAR(10) NOT NULL,
C_FloorNumber_RM INT NOT NULL,
C_WingCode_RM VARCHAR(10) NOT NULL,
C_RoomType_RM VARCHAR(50) NOT NULL,
PRIMARY KEY(C_RoomNum_RM, C_FloorNumber_RM, C_WingCode_RM),
FOREIGN KEY (C_RoomType_RM) REFERENCES RoomTypeDim(C_RoomType_RT)
);

--EventDim
CREATE TABLE EventDim
(
C_EventID_EV INT NOT NULL PRIMARY KEY,
T_EventDescription_EV VARCHAR(255) NOT NULL,
D_EventStartDate_EV DATETIME NOT NULL,
D_EventEndDate_EV DATETIME NOT NULL
);

--CustomerDim
CREATE TABLE CustomerDim
(
C_CustomerID_CU INT NOT NULL PRIMARY KEY,
T_CustomerFirstName_CU VARCHAR(50) NOT NULL,
T_CustomerLastName_CU VARCHAR(50) NOT NULL,
T_CustomerMiddleName_CU VARCHAR(50),
T_CustomerAddress_CU VARCHAR(255),
I_CustomerPhoneNumber_CU VARCHAR(50) NOT NULL
);

--CustomerAffiliationDim
CREATE TABLE CustomerAffiliationDim
(
C_CustomerID_CA INT NOT NULL,
C_EventID_CA INT NOT NULL,
FOREIGN KEY (C_CustomerID_CA) REFERENCES CustomerDim(C_CustomerID_CU),
FOREIGN KEY (C_EventID_CA) REFERENCES EventDim(C_EventID_EV),
PRIMARY KEY(C_CustomerID_CA, C_EventID_CA)
);

--RoomTypeReservationsFact
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

--CustomerReservationsFact
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

--EventReservationsFact
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

/* Loading data rows					*/
/* Turn Escape character on                             */
/* Default escape character "\" 			*/
/* Used to enter special characters (&)			*/
SET ESCAPE ON;

/* TimeDim rows						*/
INSERT INTO TimeDim VALUES(1, 2010, 09, 29);
INSERT INTO TimeDim VALUES(2, 2010, 09, 30);
INSERT INTO TimeDim VALUES(3, 2010, 09, 31);
INSERT INTO TimeDim VALUES(4, 2010, 10, 03);
INSERT INTO TimeDim VALUES(5, 2010, 10, 04);
INSERT INTO TimeDim VALUES(6, 2010, 10, 05);
INSERT INTO TimeDim VALUES(7, 2010, 10, 06);
INSERT INTO TimeDim VALUES(8, 2010, 10, 07);
INSERT INTO TimeDim VALUES(9, 2010, 10, 08);
INSERT INTO TimeDim VALUES(10, 2010, 10, 09);

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
INSERT INTO CityDim VALUES('Blacksburg','24060','Virginia','US');
INSERT INTO CityDim VALUES('Arlington','76010','Texas','US');
INSERT INTO CityDim VALUES('Houston','77092','Texas','US');

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
INSERT INTO WingDim VALUES('E', 1 'East');
INSERT INTO WingDim VALUES('W', 1 'West');
INSERT INTO WingDim VALUES('N', 1 'North');
INSERT INTO WingDim VALUES('S', 1 'South');
INSERT INTO WingDim VALUES('E', 2 'East');
INSERT INTO WingDim VALUES('W', 2 'West');
INSERT INTO WingDim VALUES('N', 2 'North');
INSERT INTO WingDim VALUES('S', 2 'South');
INSERT INTO WingDim VALUES('E', 3 'East');
INSERT INTO WingDim VALUES('W', 3 'West');
INSERT INTO WingDim VALUES('N', 3 'North');
INSERT INTO WingDim VALUES('S', 3 'South');
INSERT INTO WingDim VALUES('E', 4 'East');
INSERT INTO WingDim VALUES('W', 4 'West');
INSERT INTO WingDim VALUES('N', 4 'North');
INSERT INTO WingDim VALUES('S', 4 'South');

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
INSERT INTO RoomTypeReservationsFact VALUES(3, 'MULTI', 1, 1, 105.0);

/* CustomerReservationsFact rows						*/
INSERT INTO CustomerReservationsFact VALUES(4, 1, 1, 2, 105.0);
INSERT INTO CustomerReservationsFact VALUES(5, 2, 1, 1, 80.0);
INSERT INTO CustomerReservationsFact VALUES(6, 3, 1, 2, 180.0);

/* EventReservationsFact rows						*/
INSERT INTO EventReservationsFact VALUES(7, 1, 1, 1, 90.0);
INSERT INTO EventReservationsFact VALUES(8, 2, 1, 1, 80.0);
INSERT INTO EventReservationsFact VALUES(9, 2, 1, 1, 105.0);
INSERT INTO EventReservationsFact VALUES(10, 1, 1, 2, 160.0);

COMMIT;

SET ESCAPE OFF;