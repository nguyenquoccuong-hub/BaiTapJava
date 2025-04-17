

import DAO.DatabaseConnection;
import Controller.OrderController;
import View.OrderManagementView;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize database connection
            Connection connection = DatabaseConnection.getConnection();

            // Create view
            OrderManagementView view = new OrderManagementView(connection);

            // Create controller
            OrderController controller = new OrderController(view, connection);

            // Show the view
            view.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to connect to the database: " + e.getMessage());
            System.exit(1); // Exit with error code
        }
    }
}