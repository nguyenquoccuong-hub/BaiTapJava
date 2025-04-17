package DAO;

import Model.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }
    public ProductDAO(){};

    public List<Products> getAllProducts() {
        List<Products> list = new ArrayList<>();
        String sql = "SELECT * FROM Products";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Products product = new Products(
                        rs.getString("product_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity")
                );
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean updateStock(String productId, int quantityChange) {
        String sql = "UPDATE Products SET stock_quantity = stock_quantity + ? WHERE product_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantityChange); // quantityChange có thể là âm hoặc dương
            stmt.setString(2, productId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Products getProductById(String productId) {
        Products product = null;
        String sql = "SELECT * FROM Products WHERE product_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, productId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int stockQuantity = rs.getInt("stock_quantity");

                product = new Products(productId, name, price, stockQuantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

}
