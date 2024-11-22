

import java.sql.*;
import java.util.Scanner;

public class myJDBC {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/massageclinicdbms",
                    "root",
                    "G^79wNh.JW^sBcm"
            );
            Statement statement = connection.createStatement();
            try (Scanner scanner = new Scanner(System.in)) {
                int uberChoice = 0;
                while(uberChoice != 5){
                    System.out.println("Choose a mode:");
                    System.out.println("1. Create");
                    System.out.println("2. read");
                    System.out.println("3. update");
                    System.out.println("4. delete");
                    System.out.println("5. exit");
                    System.out.println("Enter your choice (1-5): ");
                    uberChoice = scanner.nextInt();

                    switch (uberChoice){
                        case 1:
                            System.out.println("selected: Create mode");



                            break;

                        case 2:
                            System.out.println("Selected: Read mode");
                            int choice = 0;
                            while(choice != 11){
                                System.out.println("Choose a table to view data:");
                                System.out.println("1. Client Table");
                                System.out.println("2. Therapist Table");
                                System.out.println("3. Therapist_Qualifications Table");
                                System.out.println("4. Therapist_Revenue Table");
                                System.out.println("5. Service_Type Table");
                                System.out.println("6. Transaction Table");
                                System.out.println("7. Service Table");
                                System.out.println("8. Timeslot Table");
                                System.out.println("9. Appointment Table");
                                System.out.println("10. Client Feedback Table");
                                System.out.println("11. Exit");
                                System.out.print("Enter your choice (1-11): ");
                                choice = scanner.nextInt();

                                switch (choice) {
                                    case 1:
                                        System.out.println("Selected: Client Table");
                                        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM Clients");
                                        while (resultSet1.next()) {
                                            int clientID = resultSet1.getInt("ClientID");
                                            String firstName = resultSet1.getString("FirstName");
                                            String lastName = resultSet1.getString("LastName");
                                            String sex = resultSet1.getString("Sex");
                                            Date birthdate = resultSet1.getDate("Birthdate");
                                            String phone = resultSet1.getString("Phone");
                                            String email = resultSet1.getString("Email");
                                            String address = resultSet1.getString("Address");

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
                                        ResultSet resultSet2 = statement.executeQuery("SELECT * FROM Therapists");
                                        while (resultSet2.next()) {
                                            int therapistID = resultSet2.getInt("TherapistID");
                                            String firstName = resultSet2.getString("FirstName");
                                            String lastName = resultSet2.getString("LastName");
                                            String sex = resultSet2.getString("Sex");
                                            Date birthdate = resultSet2.getDate("Birthdate");
                                            double sessionRate = resultSet2.getDouble("SessionRate");

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
                                        ResultSet resultSet3 = statement.executeQuery("SELECT * FROM Therapist_Qualifications");
                                        while (resultSet3.next()) {
                                            int qualificationID = resultSet3.getInt("QualificationID");
                                            int therapistID = resultSet3.getInt("TherapistID");
                                            String relevantExp = resultSet3.getString("RelevantExp");
                                            int yearsExp = resultSet3.getInt("YearsExp");

                                            System.out.println("QualificationID: " + qualificationID);
                                            System.out.println("TherapistID: " + therapistID);
                                            System.out.println("RelevantExp: " + relevantExp);
                                            System.out.println("YearsExp: " + yearsExp);
                                            System.out.println();
                                        }
                                        break;

                                    case 4:
                                        System.out.println("Selected: Therapist_Revenue Table");
                                        ResultSet resultSet4 = statement.executeQuery("SELECT * FROM Therapist_Revenue");
                                        while (resultSet4.next()) {
                                            int revenueID = resultSet4.getInt("RevenueID");
                                            int therapistID = resultSet4.getInt("TherapistID");
                                            double therapistRevenue = resultSet4.getDouble("TherapistRevenue");

                                            System.out.println("RevenueID: " + revenueID);
                                            System.out.println("TherapistID: " + therapistID);
                                            System.out.println("TherapistRevenue: " + therapistRevenue);
                                            System.out.println();
                                        }
                                        break;

                                    case 5:
                                        System.out.println("Selected: Service_Type Table");
                                        ResultSet resultSet5 = statement.executeQuery("SELECT * FROM Service_Types");
                                        while (resultSet5.next()) {
                                            int serviceTypeID = resultSet5.getInt("ServiceTypeID");
                                            String type = resultSet5.getString("Type");
                                            String description = resultSet5.getString("Description");
                                            double sessionCost = resultSet5.getDouble("SessionCost");

                                            System.out.println("ServiceTypeID: " + serviceTypeID);
                                            System.out.println("Type: " + type);
                                            System.out.println("Description: " + description);
                                            System.out.println("SessionCost: " + sessionCost);
                                            System.out.println();
                                        }
                                        break;

                                    case 6:
                                        System.out.println("Selected: Transaction Table");
                                        ResultSet resultSet6 = statement.executeQuery("SELECT * FROM Transactions");
                                        while (resultSet6.next()) {
                                            int transactionID = resultSet6.getInt("TransactionID");
                                            int payingClientID = resultSet6.getInt("PayingClientID");
                                            int receivingClientID = resultSet6.getInt("ReceivingClientID");
                                            Date transactionDate = resultSet6.getDate("TransactionDate");
                                            double amountPaid = resultSet6.getDouble("AmountPaid");

                                            System.out.println("TransactionID: " + transactionID);
                                            System.out.println("PayingClientID: " + payingClientID);
                                            System.out.println("ReceivingClientID: " + receivingClientID);
                                            System.out.println("TransactionDate: " + transactionDate);
                                            System.out.println("AmountPaid: " + amountPaid);
                                            System.out.println();
                                        }
                                        break;

                                    case 7:
                                        System.out.println("Selected: Service Table");
                                        ResultSet resultSet7 = statement.executeQuery("SELECT * FROM Services");
                                        while (resultSet7.next()) {
                                            int serviceID = resultSet7.getInt("ServiceID");
                                            int serviceTypeID = resultSet7.getInt("ServiceTypeID");
                                            int transactionID = resultSet7.getInt("TransactionID");
                                            int therapistID = resultSet7.getInt("TherapistID");
                                            Time duration = resultSet7.getTime("Duration");

                                            System.out.println("ServiceID: " + serviceID);
                                            System.out.println("ServiceTypeID: " + serviceTypeID);
                                            System.out.println("TransactionID: " + transactionID);
                                            System.out.println("TherapistID: " + therapistID);
                                            System.out.println("Duration: " + duration);
                                            System.out.println();
                                        }
                                        break;

                                    case 8:
                                        System.out.println("Selected: Timeslot Table");
                                        ResultSet resultSet8 = statement.executeQuery("SELECT * FROM Timeslots");
                                        while (resultSet8.next()) {
                                            int timeslotID = resultSet8.getInt("TimeslotID");
                                            String day = resultSet8.getString("Day");
                                            Time time = resultSet8.getTime("Time");
                                            String status = resultSet8.getString("Status");

                                            System.out.println("TimeslotID: " + timeslotID);
                                            System.out.println("Day: " + day);
                                            System.out.println("Time: " + time);
                                            System.out.println("Status: " + status);
                                            System.out.println();
                                        }
                                        break;

                                    case 9:
                                        System.out.println("Selected: Appointment Table");
                                        ResultSet resultSet9 = statement.executeQuery("SELECT * FROM Appointments");
                                        while (resultSet9.next()) {
                                            int appointmentID = resultSet9.getInt("AppointmentID");
                                            int clientID = resultSet9.getInt("ClientID");
                                            int timeslotID = resultSet9.getInt("TimeslotID");
                                            String status = resultSet9.getString("Status");

                                            System.out.println("AppointmentID: " + appointmentID);
                                            System.out.println("ClientID: " + clientID);
                                            System.out.println("TimeslotID: " + timeslotID);
                                            System.out.println("Status: " + status);
                                            System.out.println();
                                        }
                                        break;

                                    case 10:
                                        System.out.println("Selected: Client Feedback Table");
                                        ResultSet resultSet10 = statement.executeQuery("SELECT * FROM Client_Feedbacks");
                                        while (resultSet10.next()) {
                                            int feedbackID = resultSet10.getInt("FeedbackID");
                                            int clientID = resultSet10.getInt("ClientID");
                                            int appointmentID = resultSet10.getInt("AppointmentID");
                                            int clientRating = resultSet10.getInt("ClientRating");
                                            String additionalFeedback = resultSet10.getString("AdditionalFeedback");

                                            System.out.println("FeedbackID: " + feedbackID);
                                            System.out.println("ClientID: " + clientID);
                                            System.out.println("AppointmentID: " + appointmentID);
                                            System.out.println("ClientRating: " + clientRating);
                                            System.out.println("AdditionalFeedback: " + additionalFeedback);
                                            System.out.println();
                                        }
                                        break;

                                    case 11:
                                        System.out.println("Exiting...");
                                        break;

                                    default:
                                        System.out.println("Invalid choice, please try again.");
                                        break;
                                }

                            }
                            break;

                        case 3:
                            System.out.println("Selected: Update mode");

                            break;

                        case 4:
                            System.out.println("Selected: Delete mode");

                            break;

                        case 5:
                            System.out.println("quiting program");
                            break;
                    }

                }

            }





        }catch (SQLException e){
            e.printStackTrace();
        }



    }
}
