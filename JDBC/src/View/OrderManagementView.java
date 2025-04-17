package View;

import DAO.CustomerDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Customers;
import Model.OrderItems;
import Model.Orders;
import Model.Products;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class OrderManagementView extends JFrame {
    private JTable customerTable, orderTable, orderItemTable, productTable;
    private DefaultTableModel customerModel, orderModel, orderItemModel, productModel;
    private JButton addOrderButton, viewHistoryButton, calculateTotalButton;
    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    public OrderManagementView(Connection connection) {
        customerDAO = new CustomerDAO(connection);
        orderDAO = new OrderDAO(connection);
        productDAO = new ProductDAO(connection);

        setTitle("Order Management System");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize tables
        initializeTables();

        // Create tabbed pane for tables
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Customers", new JScrollPane(customerTable));
        tabbedPane.addTab("Orders", new JScrollPane(orderTable));
        tabbedPane.addTab("Order Items", new JScrollPane(orderItemTable));
        tabbedPane.addTab("Products", new JScrollPane(productTable));

        // Create button panel
        JPanel buttonPanel = createButtonPanel();

        // Add components to frame
        add(tabbedPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load initial data
        loadTableData();
    }

    private void initializeTables() {
        // Customer table
        customerModel = new DefaultTableModel(new String[]{"ID", "Name", "Address", "Gender"}, 0);
        customerTable = new JTable(customerModel);

        // Order table
        orderModel = new DefaultTableModel(new String[]{"Order ID", "Customer ID", "Ship Address", "Amount"}, 0);
        orderTable = new JTable(orderModel);

        // Order Item table
        orderItemModel = new DefaultTableModel(new String[]{"Item ID", "Product ID", "Order ID", "Product Name", "Quantity", "Unit Price", "Total Price"}, 0);
        orderItemTable = new JTable(orderItemModel);

        // Product table
        productModel = new DefaultTableModel(new String[]{"Product ID", "Name", "Price", "Stock Quantity"}, 0);
        productTable = new JTable(productModel);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        // Buttons
        addOrderButton = new JButton("Add New Order");
        viewHistoryButton = new JButton("View Order History");
        calculateTotalButton = new JButton("Calculate Order Total");

        // Add buttons to panel
        panel.add(addOrderButton);
        panel.add(viewHistoryButton);
        panel.add(calculateTotalButton);

        return panel;
    }

    private void loadTableData() {
        // Load customers
        customerModel.setRowCount(0);
        for (Customers c : customerDAO.getAllCustomers()) {
            customerModel.addRow(new Object[]{c.getCustomer_id(), c.getName(), c.getAddress(), c.getGender()});
        }

        // Load orders
        orderModel.setRowCount(0);
        for (Orders o : orderDAO.getAllOrders()) {
            orderModel.addRow(new Object[]{o.getOrder_id(), o.getCustomers_id(), o.getShipAddress(), o.getAmount()});
        }

        // Load order items
        orderItemModel.setRowCount(0);
        for (OrderItems item : orderDAO.getAllOrderItems()) {
            orderItemModel.addRow(new Object[]{
                    item.getOrder_item_id(),
                    item.getProducts_id(),
                    item.getOrder_id(),
                    item.getProduct_name(),
                    item.getQuantity(),
                    item.getUnit_price(),
                    item.getTotal_price()
            });
        }

        // Load products
        productModel.setRowCount(0);
        for (Products p : productDAO.getAllProducts()) {
            productModel.addRow(new Object[]{p.getProduct_id(), p.getName(), p.getPrice(), p.getStock_quantity()});
        }
    }

    // Dialog for adding new order
    public void showAddOrderDialog(Runnable onSubmit) {
        JDialog dialog = new JDialog(this, "Add New Order", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(6, 2, 10, 10));

        JTextField customerIdField = new JTextField(10);
        JTextField orderIdField = new JTextField(10);
        JTextField shipAddressField = new JTextField(20);
        JTextField productIdField = new JTextField(10);
        JTextField quantityField = new JTextField(5);

        dialog.add(new JLabel("Customer ID:"));
        dialog.add(customerIdField);
        dialog.add(new JLabel("Order ID:"));
        dialog.add(orderIdField);
        dialog.add(new JLabel("Ship Address:"));
        dialog.add(shipAddressField);
        dialog.add(new JLabel("Product ID:"));
        dialog.add(productIdField);
        dialog.add(new JLabel("Quantity:"));
        dialog.add(quantityField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            // Store input values in view for controller access
            setInputValues(customerIdField.getText(), orderIdField.getText(),
                    shipAddressField.getText(), productIdField.getText(),
                    quantityField.getText());
            onSubmit.run();
            dialog.dispose();
        });

        dialog.add(submitButton);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Dialog for viewing order history
    public void showViewHistoryDialog(Runnable onSubmit) {
        JDialog dialog = new JDialog(this, "View Order History", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel instructionLabel = new JLabel("Enter Customer ID to view history:");
        JTextField customerIdField = new JTextField(10);

        dialog.add(instructionLabel);
        dialog.add(customerIdField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            setInputValues(customerIdField.getText(), "", "", "", "");
            onSubmit.run();
            dialog.dispose();
        });

        dialog.add(new JLabel(""));
        dialog.add(submitButton);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Dialog for calculating order total
    public void showCalculateTotalDialog(Runnable onSubmit) {
        JDialog dialog = new JDialog(this, "Calculate Order Total", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel instructionLabel = new JLabel("Enter Order ID to calculate total:");
        JTextField orderIdField = new JTextField(10);

        dialog.add(instructionLabel);
        dialog.add(orderIdField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            setInputValues("", orderIdField.getText(), "", "", "");
            onSubmit.run();
            dialog.dispose();
        });

        dialog.add(new JLabel(""));
        dialog.add(submitButton);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Store input values
    private String customerId, orderId, shipAddress, productId, quantity;

    private void setInputValues(String customerId, String orderId, String shipAddress,
                                String productId, String quantity) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.shipAddress = shipAddress;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getter methods for controller
    public String getCustomerId() { return customerId; }
    public String getOrderId() { return orderId; }
    public String getShipAddress() { return shipAddress; }
    public String getProductId() { return productId; }
    public String getQuantity() { return quantity; }

    // Methods to be called by controller
    public void addOrderButtonListener(Runnable listener) {
        addOrderButton.addActionListener(e -> listener.run());
    }

    public void viewHistoryButtonListener(Runnable listener) {
        viewHistoryButton.addActionListener(e -> listener.run());
    }

    public void calculateTotalButtonListener(Runnable listener) {
        calculateTotalButton.addActionListener(e -> listener.run());
    }

    public void refreshTables() {
        loadTableData();
    }

    public void setOrderHistory(List<Orders> orders, List<OrderItems> items) {
        // Update Orders table
        orderModel.setRowCount(0);
        for (Orders order : orders) {
            orderModel.addRow(new Object[]{
                    order.getOrder_id(),
                    order.getCustomers_id(),
                    order.getShipAddress(),
                    order.getAmount()
            });
        }

        // Update Order Items table
        orderItemModel.setRowCount(0);
        for (OrderItems item : items) {
            orderItemModel.addRow(new Object[]{
                    item.getOrder_item_id(),
                    item.getProducts_id(),
                    item.getOrder_id(),
                    item.getProduct_name(),
                    item.getQuantity(),
                    item.getUnit_price(),
                    item.getTotal_price()
            });
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}