
import java.sql.*;
import java.util.*;


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

                while(uberChoice != 5){
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
                        case 1:
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
                            break;


                        case 2:
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
                            break;

                        case 3:
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

                            break;

                        case 4:
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
                            

                            break;

                        case 5:
                            System.out.println("\nSelected: Transactions\n");
                            System.out.println("\nChoose a transaction:");
                            System.out.println("1. Book appointment");
                            System.out.println("2. Pay for service");
                            System.out.println("3. Record client feedback");
                            System.out.println("4. Exit\n");
                            System.out.println("Enter your choice (1-4): ");
                            uberChoice = scanner.nextInt();
                            
                            
                            switch (uberChoice) {
                                case 1:
                                    bookAppointment(connection, metaData);
                                    break;
                                case 2:
                                    payForService(connection, metaData);
                                    break;
                                case 3:
                                    recordClientFeedback(connection, metaData);
                                    break;
                                case 4:
                                    System.out.println("Exiting...");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }

                            break;
                        case 6:
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
                                case 1:
                                    generateMonthlyAppointmentSummary(connection);
                                    break;
                                case 2:
                                    generateServicePopularityReport(connection);
                                    break;
                                case 3:
                                    System.out.println("Enter year: ");
                                    int year = scanner.nextInt();
                                    System.out.println("Enter month: ");
                                    int month = scanner.nextInt();
                                    generateServiceReport(connection, year, month);
                                    break;
                                case 4:
                                    generateRevenueReport(connection);
                                    break;
                                case 5:
                                    System.out.println("Exiting...");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                            break;
                        
                        case 7:
                            System.out.println("\n\nTerminating program..\n");
                            break;
                    }

                }

            }
        } catch (SQLException e){
            e.printStackTrace();
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

        for (int width : columnWidths) {
            System.out.print("+");
            System.out.print("-".repeat(width + 2));
        }
        System.out.println("+");

        printSet.beforeFirst();
        while (printSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String value = printSet.getString(i);
                if (value != null) {
                    columnWidths[i - 1] = Math.max(columnWidths[i - 1], value.length());
                }
            }
        }
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
        Scanner scanner = new Scanner(System.in);
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
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
    
    private static void bookAppointment(Connection connection, DatabaseMetaData metaData) {
        System.out.println("\nBooking an appointment...");
        insertRecord(connection, metaData, "Appointments");
    }
    private static void payForService(Connection connection, DatabaseMetaData metaData) {
        System.out.println("\nPaying for a service...");
        insertRecord(connection, metaData, "Transactions");
    }
    private static void recordClientFeedback(Connection connection, DatabaseMetaData metaData) {
        System.out.println("\nRecording client feedback...");
        insertRecord(connection, metaData, "Client_Feedbacks");
    }
    
    private static void generateMonthlyAppointmentSummary(Connection connection) {
    System.out.println("\nGenerating Monthly Appointment Summary...");
    String query = """
        SELECT
            DATE_FORMAT(t.TransactionDate, '%Y-%m') AS Month,
            COUNT(a.AppointmentID) AS TotalAppointments,
            SUM(t.AmountPaid) AS TotalRevenue
        FROM
            Appointments a
            INNER JOIN Services s ON s.AppointmentID = a.AppointmentID
            INNER JOIN Transactions t ON s.TransactionID = t.TransactionID
        GROUP BY
            Month
        ORDER BY
            Month;
    """;

    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        System.out.printf("%-15s %-20s %-20s\n", "Month", "Total Appointments", "Total Revenue");
        while (rs.next()) {
            System.out.printf("%-15s %-20d %-20.2f\n",
                rs.getString("Month"),
                rs.getInt("TotalAppointments"),
                rs.getDouble("TotalRevenue"));
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

private static void generateServiceReport(Connection connection, int year, int month) {
    System.out.println("\nGenerating Service Report...");
    String query = """
        SELECT s.Type, COUNT(sv.ServiceID) AS TotalServices, AVG(TIME_TO_SEC(sv.Duration) / 60) AS AvgDuration
        FROM Service_Types s
        LEFT JOIN Services sv ON s.ServiceTypeID = sv.ServiceTypeID
        WHERE YEAR(sv.TransactionDate) = ? AND MONTH(sv.TransactionDate) = ?
        GROUP BY s.ServiceTypeID;
    """;
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, year);
        stmt.setInt(2, month);
        try (ResultSet rs = stmt.executeQuery()) {
            System.out.printf("%-30s %-15s %-15s\n", "Service Type", "Total Services", "Avg Duration (min)");
            while (rs.next()) {
                System.out.printf("%-30s %-15d %-15.2f\n",
                    rs.getString("Type"),
                    rs.getInt("TotalServices"),
                    rs.getDouble("AvgDuration"));
            }
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
