CREATE DATABASE DW;
USE DW;

--TimeDim
CREATE TABLE TimeDim
(
C_TimeID_TI INT PRIMARY KEY,
N_TimeYear_TI INT NOT NULL,
N_TimeQuarter_TI INT NOT NULL,
N_TimeMonth_TI INT NOT NULL,
T_TimeDay_TI VARCHAR(50) NOT NULL,
D_ClockTime_TI DATETIME NOT NULL
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