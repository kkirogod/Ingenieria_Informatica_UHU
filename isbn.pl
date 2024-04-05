isbn([], 0, Sum) :- 0 is mod(Sum, 11).

isbn([Head|Rest], Index, Sum) :- isbn(Rest, Index, Sum), Head is -.

isbn([Head|Rest], Index, Sum) :- 
    isbn(Rest, Index2, Sum2), 
    belongs(Head, [0,1,2,3,4,5,6,7,8,9]), 
    Mul is Head*Index, 
    Sum2 is Sum+Mul,
    Index2 is Index-1.

isbn([Head|Rest], Index, Sum) :- 
    isbn(Rest, Index2, Sum2), 
    Head is X,
    Mul is 10*Index, 
    Sum2 is Sum+Mul,
    Index2 is Index-1.



belongs(Elem, [Elem|_]).
belongs(Elem, [_|Rest]):- belongs(Elem, Rest).
