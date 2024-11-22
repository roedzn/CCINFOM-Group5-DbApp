SELECT
    DATE_FORMAT(t.TransactionDate, '%Y-%m') AS Month,
    COUNT(a.AppointmentID) AS TotalAppointments,
    SUM(t.AmountPaid) AS TotalRevenue
FROM
    Appointments a
    INNER JOIN Services s ON a.ServiceID = s.ServiceID
    INNER JOIN `Transactions` t ON s.TransactionID = t.TransactionID
GROUP BY
    Month
ORDER BY
    Month;
