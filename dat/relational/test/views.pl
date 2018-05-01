'org.logicware.domain.model.view.AnEmployeeView'(Salary,Department,Id,FirstName,MiddleName,LastName,Address,Phones,Emails,NickNames,BirthDate,JoinDate,LastLoginDate,LoginCount) :-
	findall((Id / X / Y),'org.logicware.domain.model.Employee'(Id,X,Y),L).

