--Variables to be set before running the query:
--@ClientID: ID of the client booking the appointment.
--@ServiceTypeID: ID of the desired service type.
--@DesiredDay: Day of the appointment (e.g., 'Monday').
--@DesiredTime: Time of the appointment (e.g., '10:00:00').
--@Duration: Duration of the service (e.g., '01:00:00').

-- Step 1: Find an available timeslot on the desired day and time
SELECT TimeslotID INTO @AvailableTimeslotID
FROM Timeslot
WHERE Day = @DesiredDay AND Time = @DesiredTime AND Status = 'Available'
LIMIT 1;

-- Step 2: Find a therapist available at that timeslot
SELECT TherapistID INTO @AvailableTherapistID
FROM Therapist_Availability
WHERE Day = @DesiredDay AND Time = @DesiredTime
LIMIT 1;

-- Step 3: Begin a transaction to ensure atomicity
START TRANSACTION;

-- Step 4: Check if both timeslot and therapist are available
IF @AvailableTimeslotID IS NOT NULL AND @AvailableTherapistID IS NOT NULL THEN

    -- Step 5: Update the timeslot status to 'Booked'
    UPDATE Timeslot
    SET Status = 'Booked'
    WHERE TimeslotID = @AvailableTimeslotID;

    -- Step 6: Insert a new transaction record
    INSERT INTO `Transaction` (PayingClientID, ReceivingClientID, TransactionDate, AmountPaid)
    VALUES (@ClientID, @ClientID, CURDATE(), 0.00);  -- Assuming AmountPaid is calculated elsewhere

    SET @NewTransactionID = LAST_INSERT_ID();

    -- Step 7: Insert a new service record
    INSERT INTO Service (ServiceTypeID, TransactionID, TherapistID, Duration)
    VALUES (@ServiceTypeID, @NewTransactionID, @AvailableTherapistID, @Duration);

    SET @NewServiceID = LAST_INSERT_ID();

    -- Step 8: Insert a new appointment record
    INSERT INTO Appointment (ClientID, ServiceID, TimeslotID, Status)
    VALUES (@ClientID, @NewServiceID, @AvailableTimeslotID, 'Booked');

    COMMIT;
ELSE
    -- If not available, roll back the transaction
    ROLLBACK;
    SELECT 'No available timeslot or therapist for the selected day and time' AS ErrorMessage;
END IF;

