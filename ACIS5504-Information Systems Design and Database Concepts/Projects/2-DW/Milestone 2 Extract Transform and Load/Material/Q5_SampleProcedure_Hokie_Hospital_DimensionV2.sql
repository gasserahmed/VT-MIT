/* ACIS5504 Data Warehouse Assignment Sample procedure. 
/* Steven D. Sheetz, 10/25/2015, updated 4/19/2016
/*
/* This procedure was adapted from the following reference.
/* PL/SQL User's Guide and Reference
/*  Release 2 (9.2)
/*  Part Number A96624-01



/* Patientinfo is a dimension table.*/

CREATE table PatientInfo
	(c_patientInfo_pi		number (6) not null,
	 c_corp1PatientId_pi 	number (6), /* fK from db1 */
	 c_corp2PatientId_pi 	number (6), /* fK from db2 */
	 t_firstName_pi 		varchar(100)
	 t_middleName_pi 		varchar(100)
	 t_lastName_pi 			varchar(100)
	 t_address 				varchar(200)
	 t_phoneNumber 			varchar(13)
	constraint c_patientInfo_pi_pk Primary key (c_patientInfo_pi)
	);
	
CREATE OR REPLACE PROCEDURE Get_PatientInfo_Dimension
/* There are two hospital operational DBs to merge into the DW.

/ *------------------------------------------------------------*/
/* This procedure creates the Patient dimension table for the 
/* data warehouse by extracting data from both operational databases.
/*------------------------------------------------------------*/


/* Patient Dimension process. 

/* Get patients for the PatientInfo dimension table of the data warehouse from operational database one.

/* Declare PatientInfo Table Population variables and cursors for DB1 and DB2 */

declare @corp1PatientId int
declare @corp2PatientId int
declare @existingPatientId int
declare @FirstName nvarchar(200)
declare @MiddleName nvarchar(200)
declare @LastName nvarchar(200)
declare @Address nvarchar(200)
declare @PhoneNumber nvarchar(200)

declare @getPatientInfoDB1 CURSOR
declare @getPatientInfoDB2 CURSOR
     
/* Define the SQL statement to query the first operational DB */  
set @getPatientInfo = CURSOR for 
	Select C_PatientID_Pt, T_PatientFirstName_Pt, T_PatientMiddleName_Pt, T_PatientLastName_Pt, T_PatientAddress_Pt, T_PatientPhoneNumber_Pt from Patient 

/* Opening the cursor returns a result set based on the query */
open @getPatientInfoDB1;

/* get the first record*/
fetch next
from @getPatientInfoDB1 into @corp1PatientId, @FirstName, @MiddleName, @LastName, @Address, @PhoneNumber

while @@FETCH_STATUS = 0
	begin
	/* check to see if the patient is already in the table */
	@existingPatient = 0
	select patientid into @existingPatientid from patientinfo where c_patientInfo_pi = @corp1PatientID;
	If @existingPatient == 0
		/* patient does not exist in dimension table so insert new patient into PatientInfo table */
		begin try 
			insert into PatientInfo (c_patient_pi, c_corp1PatientId_pi, t_firstName_pi, 
									 t_middleName_pi, t_lastName_pi, t_address_pi, t_phoneNumber_pi)
			values (PatientDimSequence, @corp1PatientId, @FirstName, @MiddleName, @LastName, @Address, @PhoneNumber);
		end try 
		begin catch 
			print 'Error in insert for PatientInfo DB1';
		end catch 
	else
		print 'Patient patient from DB1 exists in dimension table PatientInfo, id = ' + @existingPatientId;
	end

	fetch next
		from @getPatientInfoDB1 into @corp1PatientId, @FirstName, @MiddleName, @LastName, @Address, @PhoneNumber
end 

close @getPatientInfoDB1
deallocate @getPatientInfoDB1

/* Get patients from the second operational DB where patients are called visitors*/

/* Define the SQL statement to query the first operational DB */  
set @getPatientInfoDB2 = CURSOR for 
	Select C_VisitorID_vi, T_VisitorFirstName_vi, T_VisitorMiddleName_vi, T_VisitorLastName_vi, T_VisitorAddress_vi, T_VisitorPhoneNumber_vi from Visitor 

/* Opening the cursor returns a result set based on the query */
open @getPatientInfoDB2;

/* get the first record*/
fetch next
from @getPatientInfoDB1 into @corp2PatientId, @FirstName, @MiddleName, @LastName, @Address, @PhoneNumber

while @@FETCH_STATUS = 0
	begin
	/* check to see if the patient is already in the table */
	@existingPatient = 0
	select patientid into @existingPatient from patientinfo where c_patientInfo_pi = @corp2PatientID;
	
	If @existingPatient == 0
		/* patient does not exist in dimension table so insert new patient into PatientInfo table */
		begin try 
			insert into PatientInfo (c_patient_pi, c_corp2PatientId_pi, t_firstName_pi, 
									 t_middleName_pi, t_lastName_pi, t_address_pi, t_phoneNumber_pi)
			values (PatientDimSequence, @existingPatientId, @FirstName, @MiddleName, @LastName, @Address, @PhoneNumber);
		end try 
		begin catch 
			print 'Error in insert for PatientInfo DB2';
		end catch 
	else
		print 'Patient patient from DB2 exists in dimension table PatientInfo, id = ' + @existingPatientId;
	end
	fetch next
		from @getPatientInfoDB1 into @corp2PatientId, @FirstName, @MiddleName, @LastName, @Address, @PhoneNumber
end 

close @getPatientInfoDB2
deallocate @getPatientInfoDB2

DBMS_OUTPUT.PUT_LINE ("PatientInfo Dimension Table Loaded at: " || sysdate
END;
/





