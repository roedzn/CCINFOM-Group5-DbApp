select sum(t.AmountPaid) as Payments
from service s join  transaction t ON t.TransactionID = s.TransactionID
join appointment ap ON s.serviceID = ap.ServiceID;


select sum(t.AmountPaid) as totalBookedPayments
from service s join  transaction t ON t.TransactionID = s.TransactionID
join appointment ap ON s.serviceID = ap.ServiceID
where ap.status = "booked";

select sum(tr.TherapistRevenue) as totalTherapistRevenue
from therapist_revenue tr
