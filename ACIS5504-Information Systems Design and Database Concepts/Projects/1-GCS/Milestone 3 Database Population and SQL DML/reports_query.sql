--Report #1: P5.11C
SELECT T_TaskDescription_TK AS `Project Task`,
	   D_TaskStartDate_PS AS `Start Date`,
	   D_TaskEndDate_PS AS `End Date`,
	   T_SkillDescription_SK AS `Skill`,
	   Switch( 
	   		T_FirstName_EP IS NOT NULL, C_AssignmentID_AS & '-' & T_FirstName_EP & ' ' & LEFT(T_LastName_EP, 1) & '.',
	   		T_FirstName_EP IS NULL, ''
	   ) AS `Employee`,
	   D_AssignmentStartDate_AS AS `Actual Start Date`, 
	   D_AssignmentEndDate_AS AS `Actual End Date`
FROM 
((((Task t
LEFT JOIN ProjectSchedule ps ON ps.C_TaskID_PS = t.C_TaskID_TK)
LEFT JOIN Assignment a ON a.C_TaskID_AS = t.C_TaskID_TK)
LEFT JOIN Skill s ON s.C_SkillID_SK = a.C_SkillID_AS)
LEFT JOIN Employee e on e.C_EmployeeID_EP = a.C_EmployeeID_AS)
WHERE C_ProjectID_PS = 1
ORDER BY C_TaskID_TK, C_AssignmentID_AS;
----------------------------------------------------------------------

--Report #2: P5.11A
SELECT T_SkillDescription_SK AS `Skill`,
	   T_FirstName_EP & ' ' & T_LastName_EP AS `Employee`
FROM 
(Skill s
LEFT JOIN EmployeeSkill es on es.C_SkillID_ES = s.C_SkillID_SK)
LEFT JOIN Employee e on e.C_EmployeeID_EP = es.C_EmployeeID_ES
ORDER BY C_SkillID_SK;

----------------------------------------------------------------------

--Report #3: P5.11D
SELECT T_FirstName_EP & ' ' & LEFT(T_LastName_EP, 1) & '.' AS `Employee Name`,
	   D_WeekEndingDate_WL AS `Week Ending`,
	   C_AssignmentID_WL AS `Assignment Number`,
	   N_HoursWorked_WL AS `Hours Worked`,
	   C_BillID_WL AS `Bill Number`
FROM
((WorkLog wl
LEFT JOIN Assignment a on a.C_AssignmentID_AS = wl.C_AssignmentID_WL)
LEFT JOIN Employee e on e.C_EmployeeID_EP = a.C_EmployeeID_AS)
LEFT JOIN ProjectSchedule ps on ps.C_TaskID_PS = a.C_TaskID_AS
WHERE C_ProjectID_PS = 1;
----------------------------------------------------------------------
