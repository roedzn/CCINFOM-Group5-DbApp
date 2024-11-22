SELECT SUM(t.AmountPaid) AS Payments
FROM Services s
JOIN Transactions t ON t.TransactionID = s.TransactionID
JOIN Appointments ap ON s.AppointmentID = ap.AppointmentID;

SELECT SUM(t.AmountPaid) AS totalBookedPayments
FROM Services s
JOIN Transactions t ON t.TransactionID = s.TransactionID
JOIN Appointments ap ON s.AppointmentID = ap.AppointmentID
WHERE ap.Status = 'Booked';

select sum(tr.TherapistRevenue) as totalTherapistRevenue
from therapist_revenue tr
