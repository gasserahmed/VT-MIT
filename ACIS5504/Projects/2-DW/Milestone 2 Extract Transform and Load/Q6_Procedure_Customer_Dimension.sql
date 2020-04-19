/* CustomerDim is a dimension table.*/
CREATE TABLE CustomerDim
(
C_CustomerID_CU INT NOT NULL PRIMARY KEY,
C_Corp1CustomerID_CU INT,
C_Corp2CustomerID_CU INT,
T_CustomerFirstName_CU VARCHAR(50) NOT NULL,
T_CustomerLastName_CU VARCHAR(50) NOT NULL,
T_CustomerMiddleName_CU VARCHAR(50),
T_CustomerAddress_CU VARCHAR(255),
I_CustomerPhoneNumber_CU VARCHAR(50) NOT NULL
);
	
CREATE OR REPLACE PROCEDURE Get_CustomerDim_Dimension
/* There are two resort operational DBs to merge into the DW.

/ *------------------------------------------------------------*/
/* This procedure creates the Customer dimension table for the 
/* data warehouse by extracting data from both operational databases.
/*------------------------------------------------------------*/


/* Customer Dimension process. 

/* Get customers for the CustomerDim dimension table of the data warehouse from operational database one.

/* Declare CustomerDim Table Population variables and cursors for DB1 and DB2 */

declare @Corp1CustomerID int
declare @Corp2CustomerID int
declare @existingCustomerID int
declare @FirstName nvarchar(50)
declare @MiddleName nvarchar(50)
declare @LastName nvarchar(50)
declare @Address nvarchar(255)
declare @PhoneNumber nvarchar(50)

declare @getCustomerDimDB1 CURSOR
declare @getCustomerDimDB2 CURSOR
     
/* Define the SQL statement to query the first operational DB */  
set @getCustomerDimDB1 = CURSOR for 
	Select C_PersonID_PR, T_PersonFName_PR, T_PersonMName_PR, T_PersonLName_PR, T_StreetAddress_PR, I_PhoneNumber_PR from Person 

/* Opening the cursor returns a result set based on the query */
open @getCustomerDimDB1;

/* get the first record*/
fetch next
from @getCustomerDimDB1 into @Corp1CustomerID, @FirstName, @MiddleName, @LastName, @Address, @PhoneNumber

while @@FETCH_STATUS = 0
	begin
	/* check to see if the customer is already in the table */
	@existingCustomerID = 0
	select C_CustomerID_CU into @existingCustomerID from CustomerDim where C_Corp1CustomerID_CU = @Corp1CustomerID;
	If @existingCustomerID == 0
		/* customer does not exist in dimension table so insert new customer into CustomerDim table */
		begin try 
			insert into CustomerDim (C_CustomerID_CU, C_Corp1CustomerID_CU, T_CustomerFirstName_CU, 
									 T_CustomerMiddleName_CU, T_CustomerLastName_CU, T_CustomerAddress_CU, I_CustomerPhoneNumber_CU)
			values (CustomerDimSequence, @Corp1CustomerID, @FirstName, @MiddleName, @LastName, @Address, @PhoneNumber);
		end try 
		begin catch 
			print 'Error in insert for CustomerDim DB1';
		end catch 
	else
		print 'Customer customer from DB1 exists in dimension table CustomerDim, id = ' + @existingCustomerID;
	end

	fetch next
		from @getCustomerDimDB1 into @Corp1CustomerID, @FirstName, @MiddleName, @LastName, @Address, @PhoneNumber
end 

close @getCustomerDimDB1
deallocate @getCustomerDimDB1

/* Get customers from the second operational DB*/

/* Define the SQL statement to query the second operational DB */  
set @getCustomerDimDB2 = CURSOR for 
	Select C_CustomerID_Cu, T_CustomerFirstName_Cu, T_CustomerMiddleName_Cu, T_CustomerLastName_Cu, T_CustomerAddress_Cu, I_CustomerPhoneNumber_Cu from Customer 

/* Opening the cursor returns a result set based on the query */
open @getCustomerDimDB2;

/* get the first record*/
fetch next
from @getCustomerDimDB2 into @Corp2CustomerID, @FirstName, @MiddleName, @LastName, @Address, @PhoneNumber

while @@FETCH_STATUS = 0
	begin
	/* check to see if the customer is already in the table */
	@existingCustomerID = 0
	select C_CustomerID_CU into @existingCustomerID from CustomerDim where C_Corp2CustomerID_CU = @Corp2CustomerID;
	
	If @existingCustomerID == 0
		/* customer does not exist in dimension table so insert new customer into CustomerDim table */
		begin try 
			insert into CustomerDim (C_CustomerID_CU, C_Corp2CustomerID_CU, T_CustomerFirstName_CU, 
									 T_CustomerMiddleName_CU, T_CustomerLastName_CU, T_CustomerAddress_CU, I_CustomerPhoneNumber_CU)
			values (CustomerDimSequence, @Corp2CustomerID, @FirstName, @MiddleName, @LastName, @Address, @PhoneNumber);
		end try 
		begin catch 
			print 'Error in insert for CustomerDim DB2';
		end catch 
	else
		print 'Customer customer from DB2 exists in dimension table CustomerDim, id = ' + @existingCustomerID;
	end
	fetch next
		from @getCustomerDimDB2 into @Corp2CustomerID, @FirstName, @MiddleName, @LastName, @Address, @PhoneNumber
end 

close @getCustomerDimDB2
deallocate @getCustomerDimDB2

DBMS_OUTPUT.PUT_LINE ("CustomerDim Dimension Table Loaded at: " || sysdate
END;
/





