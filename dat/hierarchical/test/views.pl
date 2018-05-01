'org.logicware.domain.geometry.view.SamePoint'(Idp,X,Y) :-
	findall((Id / X / Y),'org.logicware.domain.geometry.Point'(Id,X,Y),L).

