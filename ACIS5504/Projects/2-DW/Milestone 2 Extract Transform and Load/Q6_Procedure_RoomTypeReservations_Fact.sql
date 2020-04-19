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

CREATE TABLE TimeDim
(
C_TimeID_TI INT PRIMARY KEY,
N_TimeYear_TI INT NOT NULL,
N_TimeMonth_TI INT NOT NULL,
T_TimeDay_TI VARCHAR(50) NOT NULL,
);

/* Populate RoomTypeReservationsFact Fact table from the first operational DB */

CREATE OR REPLACE PROCEDURE Get_RoomTypeReservationsFact_Fact

declare @TimeId int
declare @Month int
declare @Day int
declare @Year int
declare @OriginReservationId
declare @ResortId
declare @RoomType 
declare @ReservationDate
declare @TotalPrice
declare @TotalStays

/* Define the SQL statement to query the first operational DB */  
set @getRoomTypeReservationsFactDB1 = CURSOR for 
	Select C_ReservationID_RN, C_ResortID_RN, C_RoomRateID_RN, T_ReservationDate_RN FROM Reservation
      ORDER BY C_ReservationID_RN group by C_ReservationID_RN, C_ResortID_RN, C_RoomRateID_RN, T_ReservationDate_RN;

/* Opening the cursor returns a result set based on the query */
open @getRoomTypeReservationsFactDB1;

fetch next
from @getRoomTypeReservationsFactDB1 into @OriginReservationId, @ResortId, @RoomType, @ReservationDate 

while @@FETCH_STATUS = 0
	begin	
		/* Extract date fields from timestamp */
		@Month = Month(@ReservationDate)
		@Day = Day(@ReservationDate)
		@Year = Year(@ReservationDate)
		
		/* Find the time dimension row for this fact row (should have */
		Select TimeID into @TimeID from TimeInfo where time.day = @day and time.month = @month and time.year = @year;

		/* Find the RoomReservationDim dimension joined with RoomTypeDim for this fact row to calculate TotalStays and TotalPrice*/
		Select DATEDIFF(day, D_ArrivalDate_RR, D_DepartureDate_RR) into @TotalStays,
			   N_RoomPrice_RT * DATEDIFF(day, D_ArrivalDate_RR, D_DepartureDate_RR) into @TotalPrice
		from RoomReservationDim rr JOIN RoomTypeDim rt on rr.C_RoomType_RR = rt.C_RoomType_RT
		where rr.C_RoomReservationNum_RR = @OriginReservationId;
			
		/* Insert the new fact row into the RoomTypeReservationsFact table */
		begin try 
			insert into RoomTypeReservationsFact (C_TimeID_TR, T_RoomType_TR, C_ResortID_TR, N_TotalNumOfStays_TR, N_TotalPrice_TR)
			values (@TimeId, @RoomType, @ResortId, @TotalStays, @TotalPrice);
		end try 
		begin catch 
			print 'Error in insert for RoomTypeReservationsFactDB1';
		end catch 
		
	fetch next 
	from @getRoomTypeReservationsFactDB1 into @OriginReservationId, @ResortId, @RoomType, @ReservationDate
end 

close @getRoomTypeReservationsFactDB1
deallocate @getRoomTypeReservationsFactDB1

/* Define the SQL statement to query the second operational DB */

/* Assumption: ResortID column/attribute has been added to Room_Reservation table in Corp2 */
set @getRoomTypeReservationsFactDB2 = CURSOR for 
	Select C_RoomReservationNumber_Rr, C_ResortID_Rr, C_RoomType_Rr, D_ArrivalDate_Rr FROM Room_Reservation
      ORDER BY C_RoomReservationNumber_Rr group by C_RoomReservationNumber_Rr, C_ResortID_Rr, C_RoomType_Rr, D_ArrivalDate_Rr;

/* Opening the cursor returns a result set based on the query */
open @getRoomTypeReservationsFactDB2;

fetch next
from @getRoomTypeReservationsFactDB2 into @OriginReservationId, @ResortId, @RoomType, @ReservationDate 

while @@FETCH_STATUS = 0
	begin	
		/* Extract date fields from timestamp */
		@Month = Month(@ReservationDate)
		@Day = Day(@ReservationDate)
		@Year = Year(@ReservationDate)
		
		/* Find the time dimension row for this fact row (should have */
		Select TimeID into @TimeID from TimeInfo where time.day = @day and time.month = @month and time.year = @year;

		/* Find the RoomReservationDim dimension joined with RoomTypeDim for this fact row to calculate TotalStays and TotalPrice*/
		Select DATEDIFF(day, D_ArrivalDate_RR, D_DepartureDate_RR) into @TotalStays,
			   N_RoomPrice_RT * DATEDIFF(day, D_ArrivalDate_RR, D_DepartureDate_RR) into @TotalPrice,

		from RoomReservationDim rr JOIN RoomTypeDim rt on rr.C_RoomType_RR = rt.C_RoomType_RT
		where rr.C_RoomReservationNum_RR = @OriginReservationId;
			
		/* Insert the new fact row into the RoomTypeReservationsFact table */
		begin try 
			insert into RoomTypeReservationsFact (C_TimeID_TR, T_RoomType_TR, C_ResortID_TR, N_TotalNumOfStays_TR, N_TotalPrice_TR)
			values (@TimeId, @RoomType, @ResortId, @TotalStays, @TotalPrice);
		end try 
		begin catch 
			print 'Error in insert for RoomTypeReservationsFactDB2';
		end catch 
		
	fetch next 
	from @getRoomTypeReservationsFactDB2 into @OriginReservationId, @ResortId, @RoomType, @ReservationDate
end 

close @getRoomTypeReservationsFactDB2
deallocate @getRoomTypeReservationsFactDB2


DBMS_OUTPUT.PUT_LINE ("RoomTypeReservations Fact Table Loaded at: " || sysdate
END;
/





