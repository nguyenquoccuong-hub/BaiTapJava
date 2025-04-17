package DAO;

import Model.Customers;
import Model.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Customers> getAllCustomers() {
        List<Customers> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customers";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customers customer = new Customers(
                        rs.getString("customer_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("gender")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public Customers getCustomerById(String customerId) {
        String sql = "SELECT * FROM Customers WHERE customer_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customers(
                        rs.getString("customer_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("gender")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Orders> getOrderHistoryByCustomer(String customerId) {
        // This method is replaced by OrderDAO.getOrdersByCustomerId
        // Kept for backward compatibility if needed
        List<Orders> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE customers_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Orders order = new Orders(
                        rs.getString("order_id"),
                        rs.getString("customers_id"),
                        rs.getString("shipAddress"),
                        rs.getDouble("amount")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}