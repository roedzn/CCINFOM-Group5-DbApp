import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionUI {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Database Connection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1, 10, 10));

        // Create components
        JTextField urlField = new JTextField("jdbc:mysql://localhost:3306/your_database");
        JTextField userField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton connectButton = new JButton("Connect");
        JLabel statusLabel = new JLabel("Status: Not connected", SwingConstants.CENTER);

        // Add placeholders and labels
        frame.add(new JLabel("Database URL:", SwingConstants.CENTER));
        frame.add(urlField);
        frame.add(new JLabel("Username:", SwingConstants.CENTER));
        frame.add(userField);
        frame.add(new JLabel("Password:", SwingConstants.CENTER));
        frame.add(passwordField);
        frame.add(connectButton);
        frame.add(statusLabel);

        // Connect button action
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlField.getText();
                String user = userField.getText();
                String password = new String(passwordField.getPassword());

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    statusLabel.setText("Status: Connected!");
                } catch (SQLException ex) {
                    statusLabel.setText("Status: Failed to connect!");
                    JOptionPane.showMessageDialog(frame,
                            "Error: " + ex.getMessage(),
                            "Connection Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Show the frame
        frame.setVisible(true);
    }
}
