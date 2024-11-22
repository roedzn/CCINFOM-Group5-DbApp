SELECT
    DATE_FORMAT(t.TransactionDate, '%Y-%m') AS Month,
    COUNT(a.AppointmentID) AS TotalAppointments,
    SUM(t.AmountPaid) AS TotalRevenue
FROM
    Appointment a
    INNER JOIN Service s ON a.ServiceID = s.ServiceID
    INNER JOIN `Transaction` t ON s.TransactionID = t.TransactionID
GROUP BY
    Month
ORDER BY
    Month;
