select sum(t.AmountPaid) as Payments
from services s join  transactions t ON t.TransactionID = s.TransactionID
join appointments ap ON s.serviceID = ap.ServiceID;


select sum(t.AmountPaid) as totalBookedPayments
from services s join  transactions t ON t.TransactionID = s.TransactionID
join appointments ap ON s.serviceID = ap.ServiceID
where ap.status = "booked";

select sum(tr.TherapistRevenue) as totalTherapistRevenue
from therapist_revenue tr
