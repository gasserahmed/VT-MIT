--Q1
SELECT N_TimeYear_TI AS YEAR, T_RoomType_TR, C_ResortID_TR, SUM(N_TotalNumOfStays_TR) AS TotalStays, SUM(N_TotalPrice_TR) AS TotalProfit
FROM RoomTypeReservationsFact TR
JOIN TimeDim TI ON TR.C_TimeID_TR = TI.C_TimeID_TI
WHERE T_RoomType_TR IN ('SINGLE', 'MULTI') AND N_TimeYear_TI = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))
GROUP BY T_RoomType_TR, C_ResortID_TR WITH ROLLUP;

--Q2
SELECT N_TimeYear_TI AS YEAR, C_ResortID_CR, COUNT(*) AS TotalReservations, SUM(N_TotalPrice_CR) AS TotalProfit
FROM CustomerReservationsFact CR
JOIN TimeDim TI ON CR.C_TimeID_CR = TI.C_TimeID_TI
WHERE N_TimeYear_TI >= YEAR(DATE_SUB(CURDATE(), INTERVAL 2 YEAR))
GROUP BY N_TimeYear_TI, C_ResortID_CR WITH ROLLUP;

--Q3
SELECT N_TimeYear_TI AS YEAR, C_EventID_ER, COUNT(*) AS TotalReservations, SUM(N_TotalPrice_ER) AS TotalProfit
FROM EventReservationsFact ER
JOIN TimeDim TI ON ER.C_TimeID_ER = TI.C_TimeID_TI
WHERE N_TimeYear_TI in ('2018', '2019')
GROUP BY N_TimeYear_TI, C_EventID_ER WITH ROLLUP;