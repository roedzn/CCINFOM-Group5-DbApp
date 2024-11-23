-- Variables to be set before running the query:
-- @ClientID: ID of the client booking the appointment.
-- @ServiceTypeID: ID of the desired service type.
-- @DesiredDay: Day of the appointment (e.g., 'Monday').
-- @DesiredStartTime: Start time of the appointment (e.g., '10:00:00').
-- @DesiredEndTime: End time of the appointment (e.g., '11:00:00').
-- @Duration: Duration of the service (e.g., '01:00:00').

-- Step 1: Find an available timeslot on the desired day and time
SELECT 
    TimeslotID, TherapistID INTO @AvailableTimeslotID, @AvailableTherapistID
FROM 
    Timeslots
WHERE 
    Day = ?
    AND StartTime = ?
    AND EndTime = ?
    AND Status = 'Available'
LIMIT 1;

-- Step 2: Begin a transaction to ensure atomicity
START TRANSACTION;

-- Step 3: Check if timeslot is available
IF @AvailableTimeslotID IS NOT NULL THEN

    -- Step 4: Update the timeslot status to 'Booked'
    UPDATE Timeslots
    SET Status = 'Booked'
    WHERE TimeslotID = ? AND TherapistID = ?;

    -- Step 5: Insert a new appointment record
    INSERT INTO Appointments (ClientID, TimeslotID, Status)
    VALUES (?, ?, 'Booked');

    SET @NewAppointmentID = LAST_INSERT_ID();

    -- Step 6: Insert a new transaction record
    INSERT INTO Transactions (PayingClientID, ReceivingClientID, TransactionDate, AmountPaid)
    VALUES (?, ?, CURDATE(), ?);  -- Assuming AmountPaid is calculated elsewhere

    SET @NewTransactionID = LAST_INSERT_ID();

    -- Step 7: Insert a new service record
    INSERT INTO Services (ServiceTypeID, TransactionID, AppointmentID, TherapistID, Duration)
    VALUES (?, ?, ?, ?, ?);

    COMMIT;
ELSE
    -- If not available, roll back the transaction
    ROLLBACK;
    SELECT 'No available timeslot for the selected day and time' AS ErrorMessage;
END IF;
