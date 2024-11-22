INSERT INTO transaction (payingClientID, receivingClientID, transactionDate, AmountPaid)
SELECT 10, 10, NOW(), 500.00
FROM serviceType si
JOIN service s ON si.ServiceTypeID = s.ServiceTypeID
JOIN therapist th ON s.TherapistID = th.TherapistID
WHERE s.sessionCost + th.SessionRate = 500.00;