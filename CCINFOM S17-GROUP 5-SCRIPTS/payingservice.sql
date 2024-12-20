--Variables to be set before running the query:
--@payingClientID: ID of the client payinf the transaction.
--@receivingClientID: ID of client recieving the service.
--@transactionDate: date of transaction.
--@Amount_Paid: the amount paid by the paying client whcih should be equal to the service cost and therapist rate.

INSERT INTO Transactions (PayingClientID, ReceivingClientID, TransactionDate, AmountPaid)
SELECT @payingClientID, @receivingClientID, @transactionDate, @Amount_Paid
FROM Service_Types si
JOIN Services s ON si.ServiceTypeID = s.ServiceTypeID
JOIN Therapists th ON s.TherapistID = th.TherapistID
WHERE si.SessionCost + th.SessionRate = @Amount_Paid;

