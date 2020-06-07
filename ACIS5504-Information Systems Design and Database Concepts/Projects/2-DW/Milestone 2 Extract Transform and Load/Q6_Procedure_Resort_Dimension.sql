/* ResortDim is a dimension table.*/
CREATE TABLE ResortDim
(
C_ResortID_RS INT PRIMARY KEY,
C_Corp1ResortID_CU INT,
C_Corp2ResortID_CU INT,
T_ResortName_RS VARCHAR(50) NOT NULL,
T_StreetAddress_RS VARCHAR(255) NOT NULL,
C_CityName_RS VARCHAR(50) NOT NULL,
FOREIGN KEY (C_CityName_RS) REFERENCES CityDim(C_CityName_CI)
);

	
CREATE OR REPLACE PROCEDURE Get_ResortDim_Dimension
/* There are two resort operational DBs to merge into the DW.

/ *------------------------------------------------------------*/
/* This procedure creates the Resort dimension table for the 
/* data warehouse by extracting data from both operational databases.
/*------------------------------------------------------------*/


/* Resort Dimension process. 

/* Get resorts for the ResortDim dimension table of the data warehouse from operational database one.

/* Declare ResortDim Table Population variables and cursors for DB1 and DB2 */

declare @Corp1ResortID int
declare @Corp2ResortID int
declare @existingResortID int
declare @ResortName nvarchar(50)
declare @StreetAddress nvarchar(255)
declare @CityName nvarchar(50)

declare @getResortDimDB1 CURSOR
declare @getResortDimDB2 CURSOR
     
/* Define the SQL statement to query the first operational DB */  
set @getResortDimDB1 = CURSOR for 
	Select C_ResortID_RS, T_ResortName_RS, T_StreetAddress_CR, T_City_CR from Resort 

/* Opening the cursor returns a result set based on the query */
open @getResortDimDB1;

/* get the first record*/
fetch next
from @getResortDimDB1 into @Corp1ResortID, @ResortName, @StreetAddress, @CityName

while @@FETCH_STATUS = 0
	begin
	/* check to see if the resort is already in the table */
	@existingResortID = 0
	select C_ResortID_CU into @existingResortID from ResortDim where C_Corp1ResortID_CU = @Corp1ResortID;
	If @existingResortID == 0
		/* resort does not exist in dimension table so insert new resort into ResortDim table */
		begin try 
			insert into ResortDim (C_ResortID_CU, C_Corp1ResortID_CU, T_ResortName_RS, T_StreetAddress_RS, C_CityName_RS)
			values (ResortDimSequence, @Corp1ResortID, @ResortName, @StreetAddress, @CityName);
		end try 
		begin catch 
			print 'Error in insert for ResortDim DB1';
		end catch 
	else
		print 'Resort resort from DB1 exists in dimension table ResortDim, id = ' + @existingResortID;
	end

	fetch next
		from @getResortDimDB1 into @Corp1ResortID, @ResortName, @StreetAddress, @CityName
end 

close @getResortDimDB1
deallocate @getResortDimDB1

/* Get resorts from the second operational DB*/

/* Define the SQL statement to query the second operational DB */  
set @getResortDimDB2 = CURSOR for 
	Select C_ResortCode_Rs, T_ResortName_Rs, T_StreetAddress_Rs, C_CityName_Rs from Resort 

/* Opening the cursor returns a result set based on the query */
open @getResortDimDB2;

/* get the first record*/
fetch next
from @getResortDimDB2 into @Corp2ResortID, @ResortName, @StreetAddress, @CityName

while @@FETCH_STATUS = 0
	begin
	/* check to see if the resort is already in the table */
	@existingResortID = 0
	select C_ResortID_CU into @existingResortID from ResortDim where C_Corp2ResortID_CU = @Corp2ResortID;

	If @existingResortID == 0
		/* resort does not exist in dimension table so insert new resort into ResortDim table */
		begin try 
			insert into ResortDim (C_ResortID_CU, C_Corp2ResortID_CU, T_ResortName_RS, T_StreetAddress_RS, C_CityName_RS)
			values (ResortDimSequence, @Corp2ResortID, @ResortName, @StreetAddress, @CityName);
		end try 
		begin catch 
			print 'Error in insert for ResortDim DB2';
		end catch 
	else
		print 'Resort resort from DB2 exists in dimension table ResortDim, id = ' + @existingResortID;
	end

	fetch next
		from @getResortDimDB2 into @Corp2ResortID, @ResortName, @StreetAddress, @CityName
end 

close @getResortDimDB2
deallocate @getResortDimDB2

DBMS_OUTPUT.PUT_LINE ("ResortDim Dimension Table Loaded at: " || sysdate
END;
/





