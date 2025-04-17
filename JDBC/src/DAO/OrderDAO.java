package DAO;

import Model.OrderItems;
import Model.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private static Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean createOrder(Orders order, List<OrderItems> items) {
        String insertOrderSQL = "INSERT INTO Orders (order_id, customers_id, shipAddress, amount) VALUES (?, ?, ?, ?)";
        String insertItemSQL = "INSERT INTO OrderItems (order_item_id, products_id, order_id, product_name, quantity, unit_price) VALUES (?, ?, ?, ?, ?, ?)";
        String updateStockSQL = "UPDATE Products SET stock_quantity = stock_quantity - ? WHERE product_id = ?";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement orderStmt = connection.prepareStatement(insertOrderSQL)) {
                orderStmt.setString(1, order.getOrder_id());
                orderStmt.setString(2, order.getCustomers_id());
                orderStmt.setString(3, order.getShipAddress());
                orderStmt.setDouble(4, order.getAmount());

                orderStmt.executeUpdate();
            }

            for (OrderItems item : items) {
                try (PreparedStatement itemStmt = connection.prepareStatement(insertItemSQL)) {
                    itemStmt.setString(1, item.getOrder_item_id());
                    itemStmt.setString(2, item.getProducts_id());
                    itemStmt.setString(3, item.getOrder_id());
                    itemStmt.setString(4, item.getProduct_name());
                    itemStmt.setInt(5, item.getQuantity());
                    itemStmt.setDouble(6, item.getUnit_price());

                    itemStmt.executeUpdate();
                }

                try (PreparedStatement updateStockStmt = connection.prepareStatement(updateStockSQL)) {
                    updateStockStmt.setInt(1, item.getQuantity());
                    updateStockStmt.setString(2, item.getProducts_id());
                    updateStockStmt.executeUpdate();
                }
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Orders> getAllOrders() {
        List<Orders> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String orderId = rs.getString("order_id");
                String customerId = rs.getString("customers_id");
                String shipAddress = rs.getString("shipAddress");
                double amount = rs.getDouble("amount");

                Orders orders1 = new Orders(orderId, customerId, shipAddress, amount);
                orders.add(orders1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public boolean addOrder(Orders order) {
        String sql = "INSERT INTO Orders (order_id, customers_id, shipAddress, amount) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, order.getOrder_id());
            pstmt.setString(2, order.getCustomers_id());
            pstmt.setString(3, order.getShipAddress());
            pstmt.setDouble(4, order.getAmount());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addOrderItem(OrderItems item) {
        String sql = "INSERT INTO OrderItems (order_item_id, products_id, order_id, product_name, quantity, unit_price) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, item.getOrder_item_id());
            pstmt.setString(2, item.getProducts_id());
            pstmt.setString(3, item.getOrder_id());
            pstmt.setString(4, item.getProduct_name());
            pstmt.setInt(5, item.getQuantity());
            pstmt.setDouble(6, item.getUnit_price());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<OrderItems> getOrderItemsByOrderId(String orderId) {
        List<OrderItems> items = new ArrayList<>();
        String sql = "SELECT * FROM OrderItems WHERE order_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderItems item = new OrderItems(
                        rs.getString("order_item_id"),
                        rs.getString("products_id"),
                        rs.getString("order_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price"),
                        rs.getDouble("total_price")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<OrderItems> getAllOrderItems() {
        List<OrderItems> items = new ArrayList<>();
        String sql = "SELECT * FROM OrderItems";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                OrderItems item = new OrderItems(
                        rs.getString("order_item_id"),
                        rs.getString("products_id"),
                        rs.getString("order_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price"),
                        rs.getDouble("total_price")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public String generateOrderItemId() {
        String sql = "SELECT order_item_id FROM OrderItems WHERE order_item_id REGEXP '^OI[0-9]+$' ORDER BY CAST(SUBSTRING(order_item_id, 3) AS UNSIGNED) DESC LIMIT 1";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                String lastId = rs.getString("order_item_id");
                int number = Integer.parseInt(lastId.substring(2)) + 1;
                return String.format("OI%03d", number);
            }
            return "OI001";
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            return "OI001";
        }
    }

    public List<Orders> getOrdersByCustomerId(String customerId) {
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

    public Orders getOrderById(String orderId) {
        String sql = "SELECT * FROM Orders WHERE order_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Orders(
                        rs.getString("order_id"),
                        rs.getString("customers_id"),
                        rs.getString("shipAddress"),
                        rs.getDouble("amount")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}