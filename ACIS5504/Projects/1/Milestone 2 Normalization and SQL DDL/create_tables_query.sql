--Region
CREATE TABLE Region
(
C_RegionID_RG INT PRIMARY KEY,
T_RegionName_RG VARCHAR(50) NOT NULL,
T_RegionCode_RG VARCHAR(2) NOT NULL
);

--Employee
CREATE TABLE Employee
(
C_EmployeeID_EP INT PRIMARY KEY,
T_LastName_EP VARCHAR(50),
T_MiddleInitial_EP VARCHAR(2),
T_FirstName_EP VARCHAR(50),
D_HireDate_EP DATETIME,
C_RegionID_EP INT NOT NULL CONSTRAINT FK_RegionID_RG REFERENCES Region(C_RegionID_RG)
);