/* ACIS5504 Data Warehouse Assignment Sample procedure. 
/* Steven D. Sheetz, 10/25/2015, updated 4/19/2016
/*
/* This procedure was adapted from the following reference.
/* PL/SQL User's Guide and Reference
/*  Release 2 (9.2)
/*  Part Number A96624-01


* 
Assuming this time dimension table the next step is to properly assign the right time dimension, i.e., TimeID to 
each row inserted into the fact tables. 

To do this we use the date information from the operational data to query to the TimeDim table to get the matching 
timeID to insert into the foreign key for time in the fact table.

First assume the following DW fact table.
*/




/* There are two hospital operational DBs to merge into the DW.


/* Medications is a fact table.*/

CREATE table MedicationsFact
	(c_medicationsID_mf		number (9) not null,
	 c_patientInfo_mf		number (6) not null, /*fk to PatientInfo dimension table */
	 c_provider_mf		number (6) not null, /*fk to Provider which is hospital or doctor */
	 c_time_mf			 	number (6), /* fK from time dimension table */
	 c_drug_mf			 	number (6), /* fK from drug dimension table */
	 n_drugPrice_mf			number (6,2),
	 d_perscription_mf		date, /* date of the perscription */
	 n_amountTaken_mf 		number (6,2),
	constraint c_medicationsID_mf_pk Primary key (c_medicationsID_mf)
	constraint c_patientInfo_mf_fk Foreign key REFERENCES Patient(c_patientInfo_pi)
	constraint c_provider_mf_fk Foreign key REFERENCES Provider(c_provider_pr)
	constraint c_drug_mf_fk Foreign key REFERENCES Mediation(c_drug_me)
	constraint c_time_mf_fk Foreign key REFERENCES Time(c_time_ti)
	);

CREATE table TimeDim
	(c_time_td		number (9) not null,
	 n_year_td		number (4) not null, /*year of use */
	 n_month_td		number (2) not null, /*month of use */
	 n_day_td		number (2) not null, /*day of use */
	 n_quarter_td	number (1) not null, /*quarter of use */

	constraint c_time_td_pk Primary key (c_time_td)
	);


/* Populate Medications Fact table from the first operational DB */

CREATE OR REPLACE PROCEDURE Get_Medications_Facts

declare @TimeId int
declare @Month int
declare @Day int
declare @Year int 
declare @OriginPerscriptionId int
declare @PerscriptionDate
declare @PatientId int 
declare @ProviderId int

declare @MedDate 
declare @HospitalIDNumber int
declare @AmountTaken 
declare @TimeTaken
declare @DrugPrice

/* Define the SQL statement to query the first operational DB */  
set @getMedicationsFactDB1 = CURSOR for 
	Select PrescriptionID, PrescriptionDate, VistorID, DoctorID, DrugID, day(TimeTaken), sum(AmountTaken), Avg(DrugPrice), 1 FROM Perscriptions
      ORDER BY PrescriptionID group by PrescriptionID, PrescriptionDate, VistorID, DoctorID, DrugID, day(TimeTaken);

/* Opening the cursor returns a result set based on the query */
open @getMedicationsFactDB1;

fetch next
from @getMedicationsFactDB1 into @OriginPerscriptionId, @CursorPatientId, @ProviderId, @DrugId, @TimeTaken, @AmountTaken, @DrugPrice, @HospitalID 

while @@FETCH_STATUS = 0
	begin	
		/* Extract date fields from timestamp */
		@Month = Month(@TimeTaken)
		@Day = Day(@TimeTaken)
		@Year = Year(@TimeTaken)
		
		/* Find the time dimension row for this fact row (should have */
		Select TimeID into @TimeID from TimeInfo where time.day = @day and time.month = @month and time.year = @year;

		/* Find the PatientInfo dimension for this fact row */
		Select c_patientinfo_pi into @DimPatientID from PatientInfo where c_corp1PatientId_pi = @CursorPatientId; 
		
		/*
		
		Additional dimension tables needed for the medications fact include Provider and Drug, 
		but are not included in this example.
		
		*/
			
		/* Insert the new fact row into the MedicationsFact table */
		begin try 
			insert into MedicationsFact (c_medicationsID_mf, c_patientInfo_mf, c_provider_mf, c_time_mf, c_drug_mf, n_drugPrice_mf, n_amountTaken_mf, d_perscription_mf, d_timeTaken_mf)
			values (MedicationsFactSequence, @DimPatientId, @ProviderId, @TimeID, @DrugId, @DrugPrice, @AmountTaken, @TimeTaken, 1, @TimeId);
		end try 
		begin catch 
			print 'Error in insert for MedicationsFactDB1';
		end catch 
		
	fetch next 
	from @getMedicationsFactDB1 into @OriginPerscriptionId, @ProviderId, @PatientId, @DrugId, TimeTaken, @DrugPrice, @HospitalID 
end 

close @getMedicationsFactDB1
deallocate @getMedicationsFactDB1

/*  Populate Medications Fact table from the second operational DB*/

/* Define the SQL statement to query the second operational DB */  
set @getMedicationsFactDB2 = CURSOR for 
	Select MedPrescribedID, PrescriptionDate, PatientID, ProviderID, MedID, AmtPerscribed, TimeTaken, MedCost, 2 FROM Perscriptions
      ORDER BY MedPrescribedID;

/* Opening the cursor returns a result set based on the query */
open @getMedicationsFactDB2;

fetch next
from @getMedicationsFactDB2 into @OriginPerscriptionId, @ProviderId, @CursorPatientId, @DrugId, TimeTaken, @DrugPrice, @HospitalID 

while @@FETCH_STATUS = 0
	begin	
		@Month = Month(@StayDate)
		@Day = Day(@StayDate)
		@Year = Year(@StayDate)
		@TimeId = newId()

		/* Find the time dimension row for this fact row (should have */
		Select TimeID into @TimeID from TineInfo where time.day = @day and time.month = @month and time.year = @year;

		/* Find the PatientInfo dimension for this fact row */
		Select c_patient_pa into @DimPatientID from PatientInfo where c_corp2PatientId_pi = @CursorPatientId; 
		
		/*
		
		Additional DW dimension tables needed for the medications fact include Provider and Drug, 
		but are not included in this example.
		
		*/
			
		/* Insert the new fact row from DB 2 into the MedicationsFact table */
		begin try 
			insert into MedicationsFact (c_medicationsID_mf, c_patientInfo_mf, c_provider_mf, c_time_mf, c_drug_mf, n_drugPrice_mf, n_amountTaken_mf, d_perscription_mf, d_timeTaken_mf)
			values (MedicationsFactSequence, @DimPatientId, @ProviderId, @TimeID, @DrugId, @DrugPrice, @AmountTaken, @TimeTaken, 1, @TimeId);
		end try 
		begin catch 
			print 'Error in insert for MedicationsFactDB2';
		end catch 
		
	fetch next 
	from @getMedicationsFactDB2 into @OriginPerscriptionId, @ProviderId, @PatientId, @DrugId, TimeTaken, @DrugPrice, @HospitalID 
end 

close @getMedicationsFactDB2
deallocate @getMedicationsFactDB2


DBMS_OUTPUT.PUT_LINE ("Medications Fact Table Loaded at: " || sysdate
END;
/





