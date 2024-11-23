-- Removing therapist availability
-- a. REMOVE: Reading the appointment record to check for any appointments that
--    overlap with the timeslot in question
-- b. REMOVE: Deleting overlapping appointment records
-- c. UPDATE: Updating timeslot to remove therapist

-- Variables to be set before running the query
-- @TimeslotID: ID of the timeslot to be removed

DELIMITER //

CREATE PROCEDURE RemoveTherapistAvailability()
BEGIN

    IF EXISTS (
        SELECT 1
        FROM Timeslots
        WHERE TimeslotID = @TimeslotID AND TherapistID IS NOT NULL
    ) THEN
    
        DELETE FROM Appointment
        WHERE TimeslotID = @TimeslotID;
        
        DELETE FROM Timeslots
        WHERE TimeslotID = @TimeslotID;
    END IF;
END //

DELIMITER ;
