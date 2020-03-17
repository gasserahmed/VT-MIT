--Report #1: P5.11c
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

--Report #2:

----------------------------------------------------------------------

--Report #3:

----------------------------------------------------------------------
