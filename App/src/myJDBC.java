import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class myJDBC {
    public static void main(String[] args) throws SQLException {
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://51.79.175.191:8888/massageClinicDBMS?user=group5&password=EkRqBrfqr2CxFRXET8988p98zMZZxUmzNwFL9bwxZPxzQnUsObiuiDVepOa5l6GZ",
                    "group5",
                    "EkRqBrfqr2CxFRXET8988p98zMZZxUmzNwFL9bwxZPxzQnUsObiuiDVepOa5l6GZ"
            );
            Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY
            );
            try (Scanner scanner = new Scanner(System.in)) {
                int uberChoice = 0;

                DatabaseMetaData metaData = connection.getMetaData();
                ResultSet tabResultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"});
                ArrayList<String> tableNames = new ArrayList<>();

                while (tabResultSet.next()) {
                    tableNames.add(tabResultSet.getString("TABLE_NAME"));
                }

                while(uberChoice != 7){
                    int choice;

                    System.out.println("\n\nChoose a mode:");
                    System.out.println("1. Create");
                    System.out.println("2. Read");
                    System.out.println("3. Update");
                    System.out.println("4. Delete");
                    System.out.println("5. Transactions");
                    System.out.println("6. Reports");
                    System.out.println("7. Exit\n");
                    System.out.println("Enter your choice (1-5): ");
                    uberChoice = scanner.nextInt();
                    
                    switch (uberChoice){
                        case 1 -> {
                            System.out.println("\nSelected: Create mode\n");

                            choice = 0;
                            while (choice != tableNames.size() + 1) { // Include the 'Exit' option
                                tabResultSet.beforeFirst();
                                listTables(tabResultSet);
                                String tableName;

                                System.out.print("Enter your choice (1-" + (tableNames.size() + 1) + "): ");
                                choice = scanner.nextInt();

                                if (choice >= 1 && choice <= tableNames.size()) { // Valid table selection
                                    tableName = tableNames.get(choice - 1); // Convert to 0-based index
                                    System.out.println("\nSelected Table: " + tableName + "\n");
                                    insertRecord(connection, metaData, tableName);
                                } else if (choice == tableNames.size() + 1) { // Exit option
                                    System.out.println("Exiting...");
                                } else {
                                    System.out.println("Invalid choice. Please try again.");
                                }
                                System.out.println();
                            }
                        }


                        case 2 -> {
                            System.out.println("\nSelected: Read mode\n");
                            choice = 0;
                            while(choice != tableNames.size()){
                                tabResultSet.beforeFirst();
                                listTables(tabResultSet);

                                System.out.print("Enter your choice (1-" + (tableNames.size() + 1) + "): ");
                                choice = scanner.nextInt();
                                choice--;
                                

                                
                                if (choice >= 0 && choice < tableNames.size()) {
                                    String tableName = tableNames.get(choice);
                                    System.out.println("Selected Table: " + tableName + "\n\n");

                                    ResultSet printSet = statement.executeQuery("SELECT * FROM " + tableName);
                                    ResultSetMetaData printMetaData = printSet.getMetaData();

                                    printTable(printSet, printMetaData);
                                    

                                } else if (choice == tableNames.size()) {
                                    System.out.println("Exiting...");
                                } else {
                                    System.out.println("Invalid choice. Please try again.");
                                }
                                System.out.println();

                            }
                        }

                        case 3 -> {
                            System.out.println("\nSelected: Update mode\n");
                            choice = 0;
                        
                            while (choice != tableNames.size()) {
                                tabResultSet.beforeFirst();
                                listTables(tabResultSet);
                        
                                System.out.print("Enter your choice (1-" + (tableNames.size() + 1) + "): ");
                                choice = scanner.nextInt();
                                choice--;
                        
                                if (choice >= 0 && choice < tableNames.size()) {
                                    String tableName = tableNames.get(choice);
                                    System.out.println("Selected Table: " + tableName + "\n\n");

                                    ResultSet printSet = statement.executeQuery("SELECT * FROM " + tableName);
                                    ResultSetMetaData printMetaData = printSet.getMetaData();

                                    printTable(printSet, printMetaData);
                                    scanner.nextLine();

                                    System.out.print("\nEnter column to update: ");
                                    String colName = scanner.nextLine();
                                    
                                    System.out.print("Enter the new value for column '" + colName + "': ");
                                    String newValue = scanner.nextLine();

                                    String exampleCondition = "";
                                    if (printSet.next()) { // Move cursor to the first row
                                        exampleCondition = printSet.getMetaData().getColumnName(1) + " = '" + printSet.getString(1) + "'";
                                    }

                                    System.out.print("Enter the WHERE condition to identify the record (ex. '" + exampleCondition + "'): ");
                                    String condition = scanner.nextLine();

                                    String updateQuery = "UPDATE " + tableName + " SET " + colName + " = ? WHERE " + condition;

                                    try (PreparedStatement prepst = connection.prepareStatement(updateQuery)) {
                                        prepst.setString(1, newValue);
                                        int rowsUpdated = prepst.executeUpdate();
                                        System.out.println(rowsUpdated + " row(s) updated.\n\n");
                                    } catch (SQLException e) {
                                        System.out.println("Error on update: " + e.getMessage() + "\n\n");
                                    }
                                }
                                else if (choice == tableNames.size()) {
                                    System.out.println("Exiting...\n\n");
                                } else {
                                    System.out.println("Invalid choice. Please try again.\n\n");
                                }
                            }
                        }

                        case 4 -> {
                            System.out.println("\nSelected: Delete mode\n");
                            choice = 0;

                            while (choice != tableNames.size()) {
                                tabResultSet.beforeFirst();
                                listTables(tabResultSet);

                                System.out.print("Enter your choice (1-" + (tableNames.size() + 1) + "): ");
                                choice = scanner.nextInt();
                                choice--;

                                if (choice >= 0 && choice < tableNames.size()) {
                                    String tableName = tableNames.get(choice);
                                    System.out.println("Selected Table: " + tableName + "\n\n");

                                    ResultSet printSet = statement.executeQuery("SELECT * FROM " + tableName);
                                    ResultSetMetaData printMetaData = printSet.getMetaData();

                                    printTable(printSet, printMetaData);
                                    scanner.nextLine();

                                    String exampleCondition = "";
                                    if (printSet.next()) { // Move cursor to the first row
                                        exampleCondition = printSet.getMetaData().getColumnName(1) + " = '" + printSet.getString(1) + "'";
                                    }

                                    System.out.print("Enter the WHERE condition to identify the record (ex. '" + exampleCondition + "'): ");
                                    String condition = scanner.nextLine();

                                    String deleteQuery = "DELETE FROM " + tableName + " WHERE " + condition;

                                    try (PreparedStatement prepst = connection.prepareStatement(deleteQuery)) {
                                        int rowsDeleted = prepst.executeUpdate();
                                        System.out.println(rowsDeleted + " row(s) deleted.\n\n");
                                    } catch (Exception e) {
                                        System.out.println("Error on delete: " + e.getMessage() + "\n\n");
                                    }
                                    
                                } 
                            }
                        }                            

                        case 5 -> {
                            System.out.println("\nSelected: Transactions\n");
                            System.out.println("\nChoose a transaction:");
                            System.out.println("1. Book appointment");
                            System.out.println("2. Pay for service");
                            System.out.println("3. Update therapist");
                            System.out.println("4. Record client feedback");
                            System.out.println("5. Exit\n");
                            System.out.println("Enter your choice (1-5): ");
                            uberChoice = scanner.nextInt();
                            
                            
                            switch (uberChoice) {
                                case 1 -> bookAppointment(connection);
                                case 2 -> payForService(connection, metaData);
                                case 3 -> updateTimeslot(connection);
                                case 4 -> recordClientFeedback(connection, metaData);
                                case 5 -> System.out.println("Exiting...");
                                default -> System.out.println("Invalid choice. Please try again.");
                            }
                        }

                        case 6 -> {
                            System.out.println("\nSelected: Reports\n");
                            System.out.println("\nChoose a transaction:");
                            System.out.println("1. Monthly Appointment Summary");
                            System.out.println("2. Service Popularity Report");
                            System.out.println("3. Service Repor");
                            System.out.println("4. Revenue Report");
                            System.out.println("5. Exit\n");
                            System.out.println("Enter your choice (1-5): ");
                            uberChoice = scanner.nextInt();
                            
                            
                            switch (uberChoice) {
                                case 1 -> generateMonthlyAppointmentSummary(connection);
                                case 2 -> generateServicePopularityReport(connection);
                                case 3 -> generateServiceReport(connection);
                                case 4 -> generateRevenueReport(connection);
                                case 5 -> System.out.println("Exiting...");
                                default -> System.out.println("Invalid choice. Please try again.");
                            }
                        }
                        
                        case 7 -> System.out.println("\n\nTerminating program..\n");
                    }

                }

            }
        } catch (SQLException e){
        }
    }

    private static void listTables(ResultSet tabResultSet) throws SQLException {
        int index = 1;

        System.out.println("List of tables:");

        while (tabResultSet.next()) {
            System.out.println(index++ + ". " + tabResultSet.getString("TABLE_NAME"));
        }
        System.out.println(index + ". Exit");
    }

    private static void printTable(ResultSet printSet, ResultSetMetaData printMetaData) throws SQLException {
        int columnCount = printMetaData.getColumnCount();
        int[] columnWidths = new int[columnCount];

        for (int i = 1; i <= columnCount; i++) {
            columnWidths[i - 1] = printMetaData.getColumnName(i).length();
        }

        printSet.beforeFirst();
        while (printSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String value = printSet.getString(i);
                if (value != null) {
                    columnWidths[i - 1] = Math.max(columnWidths[i - 1], value.length());
                } else {
                    columnWidths[i - 1] = Math.max(columnWidths[i - 1], 4);
                }
            }
        }

        for (int width : columnWidths) {
            System.out.print("+");
            System.out.print("-".repeat(width + 2));
        }
        System.out.println("+");
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("| %-"+ columnWidths[i - 1] +"s ", printMetaData.getColumnName(i));
        }
        System.out.println("|");

        for (int width : columnWidths) {
            System.out.print("+");
            System.out.print("-".repeat(width + 2));
        }
        System.out.println("+");

        printSet.beforeFirst();
        while (printSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String value = printSet.getString(i);
                System.out.printf("| %-"+ columnWidths[i - 1] +"s ", value != null ? value : "NULL");
            }
            System.out.println("|");
        }
        for (int width : columnWidths) {
            System.out.print("+");
            System.out.print("-".repeat(width + 2));
        }
        System.out.println("+\n\n");
    }

    private static void insertRecord(Connection connection, DatabaseMetaData metaData, String tableName) {
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                String colName;
                int i;

                ResultSet columns = metaData.getColumns(null, null, tableName, null);
                StringBuilder qb = new StringBuilder("INSERT INTO " + tableName + " (");
                StringBuilder vb = new StringBuilder(" VALUES (");
                ArrayList<String> columnNames = new ArrayList<>();

                /*
                * builds the insert query, will loop until the stringbuilders are
                * approriately full
                * 
                * ex.
                * qb is appended -> INSERT INTO (id, name, age, etc.)
                * vb is appended -> VALUES (?, ?, ?, ...)
                */
                while (columns.next()) {
                    colName = columns.getString("COLUMN_NAME");

                    String isAutoIncrement = columns.getString("IS_AUTOINCREMENT");
                    String defaultValue = columns.getString("COLUMN_DEF");

                    // skips auto-increment or columns with default values
                    if (!("YES".equalsIgnoreCase(isAutoIncrement) || defaultValue != null)) {
                        columnNames.add(colName);
                        qb.append(colName).append(", ");
                        vb.append("?, ");
                    }
                }

                if (columnNames.isEmpty()) {
                    System.out.println("No columns to insert for table " + tableName);
                    return;
                }

                qb.setLength(qb.length() - 2);
                vb.setLength(vb.length() - 2);

                // turns into INSERT INTO (colnames) VALUES ()
                qb.append(")").append(vb).append(")");

                PreparedStatement prepst = connection.prepareStatement(qb.toString());
                
                for (i = 0; i < columnNames.size(); i++) {
                    colName = columnNames.get(i);
                    String input;
                    System.out.print("Enter " + colName + ": ");
                    input = scanner.nextLine();
                    prepst.setString(i + 1, input);
                }

                int rowsInserted = prepst.executeUpdate();
                if (rowsInserted > 0) 
                    System.out.println("Created a new record into " + tableName + "!");
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

    }
    
    private static void bookAppointment(Connection connection) throws SQLException {
        Statement statement = connection.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );
        String clientID, therapistID, timeslotID;
        try (Scanner sc = new Scanner(System.in)) {
            ResultSet printSet = statement.executeQuery("""
                SELECT Timeslots.TimeslotID, 
                       Timeslots.TherapistID, 
                       CONCAT(Therapists.FirstName, ' ', Therapists.LastName) AS TherapistName, 
                       Timeslots.Day,
                       Timeslots.StartTime, 
                       Timeslots.EndTime,
                       Timeslots.Status
                FROM Timeslots
                INNER JOIN Therapists ON Timeslots.TherapistID = Therapists.TherapistID; 
                """);
            ResultSetMetaData printMetaData = printSet.getMetaData();

            System.out.println("\nBooking an appointment...");

            System.out.print("\n\nEnter your client ID: ");
            clientID = sc.nextLine();

            printTable(printSet, printMetaData);

            System.out.print("\nEnter your therapist's ID: ");
            therapistID = sc.nextLine();

            ResultSet printSet2 = statement.executeQuery("""
                SELECT Timeslots.TimeslotID, 
                       Timeslots.TherapistID, 
                       CONCAT(Therapists.FirstName, ' ', Therapists.LastName) AS TherapistName, 
                       Timeslots.Day,
                       Timeslots.StartTime, 
                       Timeslots.EndTime,
                       Timeslots.Status
                FROM Timeslots
                INNER JOIN Therapists 
                    ON Timeslots.TherapistID = Therapists.TherapistID
                WHERE Timeslots.TherapistID = """ + therapistID + ";");
            ResultSetMetaData printMetaData2 = printSet2.getMetaData();

            printTable(printSet2, printMetaData2);

            System.out.print("\nEnter preferred timeslot ID: ");
            timeslotID = sc.nextLine();
        }
        String updateQuery = "UPDATE Timeslots SET Status = ? WHERE TherapistID = ? AND Status != ?";

        try (PreparedStatement updatestm = connection.prepareStatement(updateQuery)) {
            updatestm.setString(1, "Booked");
            updatestm.setString(2, therapistID);
            updatestm.setString(3, "Booked");
            int rowsUpdated = updatestm.executeUpdate();
            System.out.println("row: " + rowsUpdated);
            if (rowsUpdated > 0) {
                System.out.println("\nAppointment awaiting booking! Therapist has been informed!");

                String insertQuery = "INSERT INTO Appointments (TimeslotID, ClientID, TherapistID) VALUES (?, ?, ?)";
                try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, timeslotID);
                    insertStmt.setString(2, clientID);
                    insertStmt.setString(3, therapistID);
    
                    int rowsInserted = insertStmt.executeUpdate();
    
                    if (rowsInserted > 0) {
                        System.out.println("\nAppointment successfully booked!");
                    } else {
                        System.out.println("\nFailed to book the appointment.");
                    }
                    
                } catch (SQLException e) {
                    System.out.println("Error on insert: " + e.getMessage() + "\n\n");
                }
            }
            else {
                System.out.println("That timeslot has already been booked, try some other timeslot!");
            }
        } catch (SQLException e) {
            System.out.println("Error on update: " + e.getMessage() + "\n\n");
        }

    }
    private static void payForService(Connection connection, DatabaseMetaData metaData) {
        System.out.println("\nPaying for a service...");
        insertRecord(connection, metaData, "Transactions");
    }
    private static void recordClientFeedback(Connection connection, DatabaseMetaData metaData) {
        System.out.println("\nRecording client feedback...");
        insertRecord(connection, metaData, "Client_Feedbacks");
    }
    private static void updateTimeslot(Connection connection) {
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                System.out.println("\nUpdate Timeslot Information");
                System.out.print("Enter TherapistID: ");
                int therapistId = scanner.nextInt();
                System.out.print("Enter TimeslotID: ");
                int timeslotId = scanner.nextInt();

                scanner.nextLine(); // Consume newline

                System.out.print("Enter the column to update (e.g., Day, StartTime, EndTime, Status): ");
                String columnName = scanner.nextLine();

                System.out.print("Enter the new value for " + columnName + ": ");
                String newValue = scanner.nextLine();

                // Prepare the update query
                String updateQuery = "UPDATE Timeslots SET " + columnName + " = ? WHERE TherapistID = ? AND TimeslotID = ?";

                try (PreparedStatement prepst = connection.prepareStatement(updateQuery)) {
                    prepst.setString(1, newValue);
                    prepst.setInt(2, therapistId);
                    prepst.setInt(3, timeslotId);

                    int rowsUpdated = prepst.executeUpdate();
                    System.out.println(rowsUpdated + " row(s) updated.\n");
                } catch (SQLException e) {
                    System.out.println("Error updating timeslot: " + e.getMessage() + "\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.\n");
            }
        }
    }

    
    private static void generateMonthlyAppointmentSummary(Connection connection) {
    System.out.println("\nGenerating Monthly Appointment Summary...");
    String query = """
        SELECT t.FirstName, t.LastName, COUNT(a.AppointmentID) AS AppointmentCount
        FROM Therapists t
        LEFT JOIN Appointments a ON t.TherapistID = a.TherapistID
        JOIN Timeslots ts ON a.TimeslotID = ts.TimeslotID
        WHERE a.Status = 'Booked' 
          AND MONTH(ts.StartTime) = MONTH(CURRENT_DATE()) 
          AND YEAR(ts.StartTime) = YEAR(CURRENT_DATE())
        GROUP BY t.TherapistID
        ORDER BY AppointmentCount DESC;
    """;
    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        System.out.printf("%-20s %-20s %-15s\n", "Therapist First Name", "Therapist Last Name", "Appointments");
        while (rs.next()) {
            System.out.printf("%-20s %-20s %-15d\n",
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getInt("AppointmentCount"));
        }
    } catch (SQLException e) {
        System.err.println("Error generating Monthly Appointment Summary: " + e.getMessage());
    }
}


private static void generateServicePopularityReport(Connection connection) {
    System.out.println("\nGenerating Service Popularity Report...");
    String query = """
        SELECT s.Type, COUNT(sv.ServiceID) AS TotalBookings
        FROM Service_Types s
        LEFT JOIN Services sv ON s.ServiceTypeID = sv.ServiceTypeID
        WHERE MONTH(CURRENT_DATE()) = MONTH(sv.Duration)
        GROUP BY s.ServiceTypeID
        ORDER BY TotalBookings DESC;
    """;
    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        System.out.printf("%-30s %-15s\n", "Service Type", "Bookings");
        while (rs.next()) {
            System.out.printf("%-30s %-15d\n",
                rs.getString("Type"),
                rs.getInt("TotalBookings"));
        }
    } catch (SQLException e) {
        System.err.println("Error generating Service Popularity Report: " + e.getMessage());
    }
}

private static void generateServiceReport(Connection connection) {
    System.out.println("\nGenerating Service Report...");

    String totalPaymentsQuery = """
        SELECT SUM(t.AmountPaid) AS Payments
        FROM Services s
        JOIN Transactions t ON t.TransactionID = s.TransactionID
        JOIN Appointments ap ON s.AppointmentID = ap.AppointmentID;
    """;

    String totalBookedPaymentsQuery = """
        SELECT SUM(t.AmountPaid) AS totalBookedPayments
        FROM Services s
        JOIN Transactions t ON t.TransactionID = s.TransactionID
        JOIN Appointments ap ON s.AppointmentID = ap.AppointmentID
        WHERE ap.Status = 'Booked';
    """;

    String totalTherapistRevenueQuery = """
        SELECT SUM(tr.TherapistRevenue) AS totalTherapistRevenue
        FROM Therapist_Revenue tr;
    """;

    try (Statement stmt = connection.createStatement()) {
        // Execute Total Payments Query
        ResultSet rsTotalPayments = stmt.executeQuery(totalPaymentsQuery);
        if (rsTotalPayments.next()) {
            System.out.printf("Total Payments: %.2f\n", rsTotalPayments.getDouble("Payments"));
        }

        // Execute Total Booked Payments Query
        ResultSet rsTotalBookedPayments = stmt.executeQuery(totalBookedPaymentsQuery);
        if (rsTotalBookedPayments.next()) {
            System.out.printf("Total Booked Payments: %.2f\n", rsTotalBookedPayments.getDouble("totalBookedPayments"));
        }

        // Execute Total Therapist Revenue Query
        ResultSet rsTotalTherapistRevenue = stmt.executeQuery(totalTherapistRevenueQuery);
        if (rsTotalTherapistRevenue.next()) {
            System.out.printf("Total Therapist Revenue: %.2f\n", rsTotalTherapistRevenue.getDouble("totalTherapistRevenue"));
        }
    } catch (SQLException e) {
        System.err.println("Error generating Service Report: " + e.getMessage());
    }
}


private static void generateRevenueReport(Connection connection) {
    System.out.println("\nGenerating Revenue Report...");
    String query = """
        SELECT s.Type, SUM(t.AmountPaid) AS TotalRevenue, MONTH(t.TransactionDate) AS Month
        FROM Service_Types s
        LEFT JOIN Services sv ON s.ServiceTypeID = sv.ServiceTypeID
        LEFT JOIN Transactions t ON sv.TransactionID = t.TransactionID
        WHERE YEAR(t.TransactionDate) = YEAR(CURRENT_DATE())
        GROUP BY s.ServiceTypeID, MONTH(t.TransactionDate)
        ORDER BY Month, TotalRevenue DESC;
    """;
    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        System.out.printf("%-30s %-15s %-10s\n", "Service Type", "Total Revenue", "Month");
        while (rs.next()) {
            System.out.printf("%-30s %-15.2f %-10d\n",
                rs.getString("Type"),
                rs.getDouble("TotalRevenue"),
                rs.getInt("Month"));
        }
    } catch (SQLException e) {
        System.err.println("Error generating Revenue Report: " + e.getMessage());
    }
}




}
