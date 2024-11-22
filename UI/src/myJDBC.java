

import java.sql.*;
import java.util.Scanner;

public class myJDBC {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bookingmanagementsystem",
                    "root",
                    "G^79wNh.JW^sBcm"
            );
            Statement statement = connection.createStatement();
                 try (Scanner scanner = new Scanner(System.in)) {
                    System.out.println("Choose a table to view data:");
                     System.out.println("1. Client Table");
                     System.out.println("2. Therapist Table");
                     System.out.println("3. Therapist_Qualifications Table");
                     System.out.println("4. Therapist_Availability Table");
                     System.out.println("5. Therapist_Revenue Table");
                     System.out.println("6. Service_Type Table");
                     System.out.println("7. Transaction Table");
                     System.out.println("8. Service Table");
                     System.out.println("9. Timeslot Table");
                     System.out.println("10. Appointment Table");
                     System.out.print("Enter your choice (1-10): ");
                     int choice = scanner.nextInt();

         switch (choice) {
                    case 1:
                        System.out.println("Selected: Client Table");
                        // Add functionality to fetch data from the Client table
                        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM Client");
                        while (resultSet1.next()) {
                            int clientID = resultSet1.getInt("ClientID");
                            String firstName = resultSet1.getString("FirstName");
                            String lastName = resultSet1.getString("LastName");
                            String sex = resultSet1.getString("Sex");
                            Date birthdate = resultSet1.getDate("Birthdate");
                            String phone = resultSet1.getString("Phone");
                            String email = resultSet1.getString("Email");
                            String address = resultSet1.getString("Address");
         
                            // Print each row of the result
                            System.out.println("ClientID: " + clientID);
                            System.out.println("FirstName: " + firstName);
                            System.out.println("LastName: " + lastName);
                            System.out.println("Sex: " + sex);
                            System.out.println("Birthdate: " + birthdate);
                            System.out.println("Phone: " + phone);
                            System.out.println("Email: " + email);
                            System.out.println("Address: " + address);
                            System.out.println();
                        }
                        break;
         
                    case 2:
                        System.out.println("Selected: Therapist Table");
                        // Add functionality to fetch data from the Therapist table
                        ResultSet resultSet2 = statement.executeQuery("SELECT * FROM Therapist");
                        while (resultSet2.next()) {
                            int therapistID = resultSet2.getInt("TherapistID");
                            String firstName = resultSet2.getString("FirstName");
                            String lastName = resultSet2.getString("LastName");
                            String sex = resultSet2.getString("Sex");
                            Date birthdate = resultSet2.getDate("Birthdate");
                            double sessionRate = resultSet2.getDouble("SessionRate");
         
                            // Print each row of the result
                            System.out.println("TherapistID: " + therapistID);
                            System.out.println("FirstName: " + firstName);
                            System.out.println("LastName: " + lastName);
                            System.out.println("Sex: " + sex);
                            System.out.println("Birthdate: " + birthdate);
                            System.out.println("SessionRate: " + sessionRate);
                            System.out.println();
                        }
                        break;
         
                    case 3:
                        System.out.println("Selected: Therapist_Qualifications Table");
                        // Add functionality to fetch data from the Therapist_Qualifications table
                        ResultSet resultSet3 = statement.executeQuery("SELECT * FROM Therapist_Qualifications");
                        while (resultSet3.next()) {
                            int qualificationID = resultSet3.getInt("QualificationID");
                            int therapistID = resultSet3.getInt("TherapistID");
                            String relevantExp = resultSet3.getString("RelevantExp");
                            int yearsExp = resultSet3.getInt("YearsExp");
         
                            // Print each row of the result
                            System.out.println("QualificationID: " + qualificationID);
                            System.out.println("TherapistID: " + therapistID);
                            System.out.println("RelevantExp: " + relevantExp);
                            System.out.println("YearsExp: " + yearsExp);
                            System.out.println();
                        }
                        break;
         
                    case 4:
                        System.out.println("Selected: Therapist_Availability Table");
                        // Add functionality to fetch data from the Therapist_Availability table
                        ResultSet resultSet4 = statement.executeQuery("SELECT * FROM Therapist_Availability");
                        while (resultSet4.next()) {
                            int timeslotID = resultSet4.getInt("TimeslotID");
                            int therapistID = resultSet4.getInt("TherapistID");
                            String day = resultSet4.getString("Day");
                            Time time = resultSet4.getTime("Time");
         
                            // Print each row of the result
                            System.out.println("TimeslotID: " + timeslotID);
                            System.out.println("TherapistID: " + therapistID);
                            System.out.println("Day: " + day);
                            System.out.println("Time: " + time);
                            System.out.println();
                        }
                        break;
         
                    case 5:
                        System.out.println("Selected: Therapist_Revenue Table");
                        // Add functionality to fetch data from the Therapist_Revenue table
                        ResultSet resultSet5 = statement.executeQuery("SELECT * FROM Therapist_Revenue");
                        while (resultSet5.next()) {
                            int revenueID = resultSet5.getInt("RevenueID");
                            int therapistID = resultSet5.getInt("TherapistID");
                            double therapistRevenue = resultSet5.getDouble("TherapistRevenue");
         
                            // Print each row of the result
                            System.out.println("RevenueID: " + revenueID);
                            System.out.println("TherapistID: " + therapistID);
                            System.out.println("TherapistRevenue: " + therapistRevenue);
                            System.out.println();
                        }
                        break;
         
                    case 6:
                        System.out.println("Selected: Service_Type Table");
                        // Add functionality to fetch data from the Service_Type table
                        ResultSet resultSet6 = statement.executeQuery("SELECT * FROM Service_Type");
                        while (resultSet6.next()) {
                            int serviceTypeID = resultSet6.getInt("ServiceTypeID");
                            String type = resultSet6.getString("Type");
                            String description = resultSet6.getString("Description");
                            double sessionCost = resultSet6.getDouble("SessionCost");
         
                            // Print each row of the result
                            System.out.println("ServiceTypeID: " + serviceTypeID);
                            System.out.println("Type: " + type);
                            System.out.println("Description: " + description);
                            System.out.println("SessionCost: " + sessionCost);
                            System.out.println();
                        }
                        break;
         
                    case 7:
                        System.out.println("Selected: Transaction Table");
                        // Add functionality to fetch data from the Transaction table
                        ResultSet resultSet7 = statement.executeQuery("SELECT * FROM Transaction");
                        while (resultSet7.next()) {
                            int transactionID = resultSet7.getInt("TransactionID");
                            int payingClientID = resultSet7.getInt("PayingClientID");
                            int receivingClientID = resultSet7.getInt("ReceivingClientID");
                            Date transactionDate = resultSet7.getDate("TransactionDate");
                            double amountPaid = resultSet7.getDouble("AmountPaid");
         
                            // Print each row of the result
                            System.out.println("TransactionID: " + transactionID);
                            System.out.println("PayingClientID: " + payingClientID);
                            System.out.println("ReceivingClientID: " + receivingClientID);
                            System.out.println("TransactionDate: " + transactionDate);
                            System.out.println("AmountPaid: " + amountPaid);
                            System.out.println();
                        }
                        break;
         
                    case 8:
                        System.out.println("Selected: Service Table");
                        // Add functionality to fetch data from the Service table
                        ResultSet resultSet8 = statement.executeQuery("SELECT * FROM Service");
                        while (resultSet8.next()) {
                            int serviceID = resultSet8.getInt("ServiceID");
                            int serviceTypeID = resultSet8.getInt("ServiceTypeID");
                            int transactionID = resultSet8.getInt("TransactionID");
                            int therapistID = resultSet8.getInt("TherapistID");
                            Time duration = resultSet8.getTime("Duration");
         
                            // Print each row of the result
                            System.out.println("ServiceID: " + serviceID);
                            System.out.println("ServiceTypeID: " + serviceTypeID);
                            System.out.println("TransactionID: " + transactionID);
                            System.out.println("TherapistID: " + therapistID);
                            System.out.println("Duration: " + duration);
                            System.out.println();
                        }
                        break;
         
                    case 9:
                        System.out.println("Selected: Timeslot Table");
                        // Add functionality to fetch data from the Timeslot table
                        ResultSet resultSet9 = statement.executeQuery("SELECT * FROM Timeslot");
                        while (resultSet9.next()) {
                            int timeslotID = resultSet9.getInt("TimeslotID");
                            String day = resultSet9.getString("Day");
                            Time time = resultSet9.getTime("Time");
                            String status = resultSet9.getString("Status");
         
                            // Print each row of the result
                            System.out.println("TimeslotID: " + timeslotID);
                            System.out.println("Day: " + day);
                            System.out.println("Time: " + time);
                            System.out.println("Status: " + status);
                            System.out.println();
                        }
                        break;
         
                    case 10:
                        System.out.println("Selected: Appointment Table");
                        // Add functionality to fetch data from the Appointment table
                        ResultSet resultSet10 = statement.executeQuery("SELECT * FROM Appointment");
                        while (resultSet10.next()) {
                            int appointmentID = resultSet10.getInt("AppointmentID");
                            int clientID = resultSet10.getInt("ClientID");
                            int serviceID = resultSet10.getInt("ServiceID");
                            int timeslotID = resultSet10.getInt("TimeslotID");
                            String status = resultSet10.getString("Status");
         
                            // Print each row of the result
                            System.out.println("AppointmentID: " + appointmentID);
                            System.out.println("ClientID: " + clientID);
                            System.out.println("ServiceID: " + serviceID);
                            System.out.println("TimeslotID: " + timeslotID);
                            System.out.println("Status: " + status);
                            System.out.println();
                        }
                        break;

                    case 11:
                    System.out.println("Selected: Client Feedback");
                    ResultSet resultSet11 = statement.executeQuery("SELECT * FROM client_feedback");
                    while (resultSet11.next()) {
                        int clientFeedbackID = resultSet11.getInt("ClientFeedbackID");
                        int clientID = resultSet11.getInt("ClientID");
                        int appointmentID = resultSet11.getInt("AppointmentID");
                        int clientRating = resultSet11.getInt("ClientRating");
                        String additionalFeedback = resultSet11.getString("AdditionalFeedback");
   
                        // Print each row of the result
                        System.out.println("ClientFeedbackID: " + clientFeedbackID);
                        System.out.println("ClientID: " + clientID);
                        System.out.println("AppointmentID: " + appointmentID);
                        System.out.println("ClientRating: " + clientRating);
                        System.out.println("AdditionalFeedback: " + additionalFeedback);
                        System.out.println();
                    }
                    break;
                    
                    default:
                        System.out.println("Invalid choice.");
                        break;
         }
                }
            

            
           

        }catch (SQLException e){
            e.printStackTrace();
        }



    }
}
