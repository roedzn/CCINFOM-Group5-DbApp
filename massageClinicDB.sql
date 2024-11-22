-- Booking Management System for Massage Clinics
-- Updated Database Schema SQL Script
-- Author: Group 5

-- Create the database
CREATE DATABASE IF NOT EXISTS massageClinicDbms;
USE massageClinicDbms;

-- Table: Client
CREATE TABLE Clients (
    ClientID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(45) NOT NULL,
    LastName VARCHAR(45) NOT NULL,
    Sex CHAR(1),
    Birthdate DATE,
    Phone VARCHAR(20),
    Email VARCHAR(100) UNIQUE,
    Address VARCHAR(255)
);

-- Table: Therapist
CREATE TABLE Therapists (
    TherapistID INT AUTO_INCREMENT PRIMARY KEY,
    LastName VARCHAR(45) NOT NULL,
    FirstName VARCHAR(45) NOT NULL,
    Sex CHAR(1),
    Birthdate DATE,
    SessionRate DECIMAL(4, 2)
);

-- Table: TherapistQualifications
CREATE TABLE Therapist_Qualifications (
    QualificationID INT AUTO_INCREMENT PRIMARY KEY,
    TherapistID INT NOT NULL,
    RelevantExp VARCHAR(45),
    YearsExp TINYINT,
    FOREIGN KEY (TherapistID) REFERENCES Therapists(TherapistID)
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);

-- Table: TherapistRevenue
CREATE TABLE Therapist_Revenue (
    RevenueID INT AUTO_INCREMENT PRIMARY KEY,
    TherapistID INT NOT NULL,
    TherapistRevenue DECIMAL(8, 2),
    FOREIGN KEY (TherapistID) REFERENCES Therapists(TherapistID)
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);

-- Table: ServiceType
CREATE TABLE Service_Types (
    ServiceTypeID INT AUTO_INCREMENT PRIMARY KEY,
    Type VARCHAR(45) NOT NULL,
    Description VARCHAR(85) NOT NULL,
    SessionCost DECIMAL(10, 2) NOT NULL
);

-- Table: Transaction
CREATE TABLE Transactions (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    PayingClientID INT NOT NULL,
    ReceivingClientID INT NOT NULL,
    TransactionDate DATE NOT NULL,
    AmountPaid DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (PayingClientID) REFERENCES Clients(ClientID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (ReceivingClientID) REFERENCES Clients(ClientID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Table: Service
CREATE TABLE Services (
    ServiceID INT AUTO_INCREMENT,
    ServiceTypeID INT NOT NULL,
    TransactionID INT NOT NULL,
    TherapistID INT NOT NULL,
    Duration TIME NOT NULL,
    PRIMARY KEY (ServiceID, TransactionID),
    FOREIGN KEY (ServiceTypeID) REFERENCES Service_Types(ServiceTypeID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (TransactionID) REFERENCES Transactions(TransactionID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (TherapistID) REFERENCES Therapists(TherapistID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Table: Timeslot
CREATE TABLE Timeslots (
    TimeslotID INT AUTO_INCREMENT PRIMARY KEY,
    TherapistID INT,
    Day VARCHAR(20),
    StartTime TIME,
    EndTime TIME,
    Status ENUM('Available', 'Booked') DEFAULT 'Available' NOT NULL,
    FOREIGN KEY (TherapistID) REFERENCES Therapists(TherapistID)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Table: Appointment
CREATE TABLE Appointments (
    AppointmentID INT AUTO_INCREMENT PRIMARY KEY,
    ClientID INT NOT NULL,
    ServiceID INT NOT NULL,
    TimeslotID INT NOT NULL,
    Status ENUM('Pending', 'Booked') DEFAULT 'Pending' NOT NULL,
    FOREIGN KEY (ClientID) REFERENCES Clients(ClientID)
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    FOREIGN KEY (ServiceID) REFERENCES Services(ServiceID)
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    FOREIGN KEY (TimeslotID) REFERENCES Timeslots(TimeslotID)
        ON DELETE CASCADE  
        ON UPDATE CASCADE
);

-- Table: ClientFeedback
CREATE TABLE Client_Feedbacks (
    ClientFeedbackID INT AUTO_INCREMENT PRIMARY KEY,
    ClientID INT NOT NULL,
    AppointmentID INT NOT NULL,
    ClientRating INT NOT NULL,
    AdditionalFeedback VARCHAR(45),
    FOREIGN KEY (ClientID) REFERENCES Clients(ClientID)
		ON DELETE CASCADE 
		ON UPDATE CASCADE,
    FOREIGN KEY (AppointmentID) REFERENCES Appointments(AppointmentID)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

-- Sample data for each table
INSERT INTO Clients (FirstName, LastName, Sex, Birthdate, Phone, Email, Address) VALUES
('John', 'Doe', 'M', '1990-05-20', '123-456-7890', 'john.doe@example.com', '123 Elm Street'),
('Jane', 'Smith', 'F', '1985-10-10', '987-654-3210', 'jane.smith@example.com', '456 Oak Avenue'),
('Emily', 'Brown', 'F', '1992-03-15', '555-123-4567', 'emily.brown@example.com', '789 Pine Lane'),
('Michael', 'Green', 'M', '1988-12-25', '444-567-8901', 'michael.green@example.com', '123 Birch Road'),
('Anna', 'White', 'F', '1995-06-30', '666-234-5678', 'anna.white@example.com', '456 Cedar Street'),
('Chris', 'Taylor', 'M', '1991-09-09', '888-765-4321', 'chris.taylor@example.com', '789 Maple Avenue'),
('Sophia', 'Lee', 'F', '1993-07-07', '111-222-3333', 'sophia.lee@example.com', '123 Cherry Drive'),
('David', 'Johnson', 'M', '1986-01-01', '222-333-4444', 'david.johnson@example.com', '456 Spruce Circle'),
('Olivia', 'Martinez', 'F', '1999-11-11', '333-444-5555', 'olivia.martinez@example.com', '789 Fir Path'),
('James', 'Wilson', 'M', '1994-04-14', '444-555-6666', 'james.wilson@example.com', '123 Redwood Lane');

INSERT INTO Therapists (LastName, FirstName, Sex, Birthdate, SessionRate) VALUES
('Brown', 'Emily', 'F', '1982-07-15', 50.00),
('Green', 'Michael', 'M', '1990-03-05', 60.00),
('Smith', 'Jessica', 'F', '1985-11-20', 55.00),
('Taylor', 'Robert', 'M', '1988-02-18', 70.00),
('White', 'Sophia', 'F', '1992-08-10', 65.00),
('Jones', 'Matthew', 'M', '1986-04-25', 75.00),
('Martinez', 'Angela', 'F', '1993-09-30', 80.00),
('Wilson', 'David', 'M', '1994-06-12', 85.00),
('Lopez', 'Maria', 'F', '1989-01-01', 90.00),
('Garcia', 'James', 'M', '1991-12-15', 95.00);

INSERT INTO Therapist_Qualifications (TherapistID, RelevantExp, YearsExp) VALUES
(1, 'Kinesiology Degree', 10),
(2, 'DPT Degree', 8),
(3, 'Licensure', 7),
(4, 'Biology Degree', 9),
(5, 'DPT Degree', 5),
(6, 'Licensure', 4),
(7, 'Kinesiology Degree', 6),
(8, 'Biology Degree', 7),
(9, 'DPT Degree', 3),
(10, 'Licensure', 8);

INSERT INTO Therapist_Revenue (TherapistID, TherapistRevenue) VALUES
(1, 1200.00),
(2, 1500.00),
(3, 1800.00),
(4, 2000.00),
(5, 1700.00),
(6, 1600.00),
(7, 1900.00),
(8, 1400.00),
(9, 1300.00),
(10, 1100.00);

INSERT INTO Service_Types (Type, Description, SessionCost) VALUES
('Relaxation', 'Soothing and relaxing massage', 0.00),  
('Therapeutic', 'Treatment for pain relief', 20.00),  
('Swedish', 'Light to medium pressure massage', 10.00), 
('Deep Tissue', 'Intense muscle work for tension', 30.00),
('Hot Stone', 'Relaxation using heated stones', 50.00), 
('Reflexology', 'Foot massage targeting pressure points', 15.00),
('Sports', 'Massage for athletes and recovery', 40.00),
('Shiatsu', 'Japanese pressure massage', 25.00),       
('Prenatal', 'Massage for pregnant clients', 10.00),    
('Thai', 'Stretching-based therapy', 0.00);

INSERT INTO Transactions (PayingClientID, ReceivingClientID, TransactionDate, AmountPaid) VALUES
(1, 1, '2024-01-15', 50.00 + 0.00),   
(2, 2, '2024-02-10', 60.00 + 20.00),  
(3, 3, '2024-03-05', 55.00 + 10.00),  
(4, 4, '2024-04-20', 70.00 + 30.00),  
(5, 5, '2024-05-25', 65.00 + 50.00),  
(6, 6, '2024-06-15', 75.00 + 15.00),  
(7, 7, '2024-07-10', 80.00 + 40.00),  
(8, 8, '2024-08-05', 85.00 + 25.00),  
(9, 9, '2024-09-15', 90.00 + 10.00),  
(10, 10, '2024-10-20', 95.00 + 0.00);

INSERT INTO Timeslots (Day, StartTime, EndTime, Status, TherapistID) VALUES
('Monday', '10:00:00', '11:00:00', 'Available', NULL),
('Tuesday', '11:00:00', '12:00:00', 'Available', NULL),
('Wednesday', '14:00:00', '15:00:00', 'Available', NULL),
('Thursday', '09:00:00', '10:00:00', 'Available', NULL),
('Friday', '15:00:00', '16:00:00', 'Available', NULL),
('Saturday', '12:00:00', '14:00:00', 'Booked', 1),
('Sunday', '13:00:00', '14:00:00', 'Available', NULL),
('Monday', '08:00:00', '09:00:00', 'Booked', 2),
('Tuesday', '16:00:00', '17:00:00', 'Available', NULL),
('Wednesday', '18:00:00', '19:00:00', 'Booked', 3);


INSERT INTO Services (ServiceTypeID, TransactionID, TherapistID, Duration) VALUES
(1, 1, 1, '01:00:00'),
(2, 2, 2, '01:30:00'),
(3, 3, 3, '01:00:00'),
(4, 4, 4, '01:15:00'),
(5, 5, 5, '01:30:00'),
(6, 6, 6, '00:45:00'),
(7, 7, 7, '01:00:00'),
(8, 8, 8, '01:30:00'),
(9, 9, 9, '01:00:00'),
(10, 10, 10, '01:30:00');


INSERT INTO Appointments (ClientID, ServiceID, TimeslotID, Status) VALUES
(1, 1, 1, 'Booked'), 
(2, 2, 2, 'Pending'),
(3, 3, 3, 'Booked'),
(4, 4, 4, 'Pending'),
(5, 5, 5, 'Booked'),
(6, 6, 6, 'Pending'),
(7, 7, 7, 'Booked'),
(8, 8, 8, 'Pending'),
(9, 9, 9, 'Booked'),
(10, 10, 10, 'Pending');

INSERT INTO Client_Feedbacks (ClientID, AppointmentID, ClientRating, AdditionalFeedback) VALUES
(1, 1, 5, 'Excellent service'),
(2, 2, 4, 'Good, but room was cold'),
(3, 3, 5, 'Very professional and attentive'),
(4, 4, 3, 'Average, expected better pressure'),
(5, 5, 5, 'Amazing hot stone massage, very relaxing'),
(6, 6, 4, 'Good technique, but session started late'),
(7, 7, 5, 'Fantastic sports massage, perfect'),
(8, 8, 5, 'Shiatsu session was incredible!'),
(9, 9, 4, 'Prenatal massage was great, but short'),
(10, 10, 5, 'Excellent Thai massage, very refreshing');
