-- Adding therapist availability
-- a. ADD: Checking for overlapping timeslot for same therapist
-- b. ADD: Creating new timeslot record

-- Variables to be set before running the query
-- @TherapistID: ID of the therapist adding a new timeslot
-- @Day: Day of the week of the timeslot
-- @StartTime: Start time of the timeslot
-- @EndTime: End time of the timeslot

DELIMITER //

CREATE PROCEDURE AddTherapistAvailability()
BEGIN

    IF NOT EXISTS (
        SELECT 1
        FROM Timeslots
        WHERE TherapistID = @TherapistID
        AND Day = @Day
        AND @StartTime < EndTime
        AND @EndTime > StartTime
    ) THEN

        INSERT INTO Timeslots (TherapistID, Day, StartTime, EndTime, Status)
        VALUES (@TherapistID, @Day, @StartTime, @EndTime, 'Available');
        
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Overlapping timeslot exists for the therapist on the same day.';
    END IF;
END //

DELIMITER ;