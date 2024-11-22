

import java.sql.*;

public class myJDBC {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bookingmanagementsystem",
                    "root",
                    "G^79wNh.JW^sBcm"
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select *  from client_feedback");

            while(resultSet.next()){
                int clientFeedbackID = resultSet.getInt("ClientFeedbackID");
                int clientID = resultSet.getInt("ClientID");
                int appointmentID = resultSet.getInt("AppointmentID");
                int clientRating = resultSet.getInt("ClientRating");
                String additionalFeedback = resultSet.getString("AdditionalFeedback");

                // Print each row of the result
                System.out.println("ClientFeedbackID: " + clientFeedbackID);
                System.out.println("ClientID: " + clientID);
                System.out.println("AppointmentID: " + appointmentID);
                System.out.println("ClientRating: " + clientRating);
                System.out.println("AdditionalFeedback: " + additionalFeedback);
                System.out.println();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }



    }
}
