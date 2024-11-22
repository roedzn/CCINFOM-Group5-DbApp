INSERT INTO transactions (payingClientID, receivingClientID, transactionDate, AmountPaid)
SELECT 10, 10, NOW(), 500.00
FROM serviceTypes si
JOIN services s ON si.ServiceTypeID = s.ServiceTypeID
JOIN therapists th ON s.TherapistID = th.TherapistID
WHERE s.sessionCost + th.SessionRate = 500.00;
