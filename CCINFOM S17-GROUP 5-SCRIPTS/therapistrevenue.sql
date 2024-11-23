-- Therapist Revenue Report
-- Sumarizes the total earnings of each therapist from appointments in a given month

-- Variables to be set before running the query
-- @Month: Target month
-- @Year: Target year

SELECT t.TherapistID, t.LastName, t.FirstName, SUM(TherapistRevenue) AS TotalEarnings
FROM Therapists t
JOIN Therapist_Revenue tr ON t.therapistID = tr.TherapistID
JOIN Services s ON tr.ServiceID = s.ServiceID
JOIN Transactions tx ON s.TransactionID = tx.TransactionID
WHERE MONTH(tx.TransactionDate) = @Month AND YEAR(tx.TransactionDate) = @Year
GROUP BY t.TherapistID, t.LastName, t.FirstName
ORDER BY t.TherapistID