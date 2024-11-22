select sum(t.AmountPaid) as totalLifetimeRevenue
from service s join  transaction t ON t.TransactionID = s.TransactionID
join appointment ap ON s.serviceID = ap.ServiceID;


select sum(t.AmountPaid) as totalLifetimeRevenue
from service s join  transaction t ON t.TransactionID = s.TransactionID
join appointment ap ON s.serviceID = ap.ServiceID
where ap.status = "booked";



