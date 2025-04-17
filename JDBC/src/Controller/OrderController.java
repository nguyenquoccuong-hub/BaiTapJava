package Controller;

import DAO.CustomerDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Customers;
import Model.OrderItems;
import Model.Orders;
import Model.Products;
import View.OrderManagementView;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    private OrderManagementView view;
    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    public OrderController(OrderManagementView view, Connection connection) {
        this.view = view;
        this.customerDAO = new CustomerDAO(connection);
        this.orderDAO = new OrderDAO(connection);
        this.productDAO = new ProductDAO(connection);

        initializeListeners();
    }

    private void initializeListeners() {
        view.addOrderButtonListener(() -> view.showAddOrderDialog(this::addNewOrder));
        view.viewHistoryButtonListener(() -> view.showViewHistoryDialog(this::viewOrderHistory));
        view.calculateTotalButtonListener(() -> view.showCalculateTotalDialog(this::calculateOrderTotal));
    }

    private void addNewOrder() {
        try {
            String customerId = view.getCustomerId();
            String orderId = view.getOrderId();
            String shipAddress = view.getShipAddress();
            String productId = view.getProductId();
            int quantity = Integer.parseInt(view.getQuantity());

            // Validate inputs
            if (customerId.isEmpty() || orderId.isEmpty() || shipAddress.isEmpty() || productId.isEmpty()) {
                view.showMessage("Please fill in all fields");
                return;
            }

            // Get product details
            Products product = productDAO.getProductById(productId);
            if (product == null) {
                view.showMessage("Invalid product ID");
                return;
            }

            if (product.getStock_quantity() < quantity) {
                view.showMessage("Insufficient stock");
                return;
            }

            // Calculate total
            double totalAmount = product.getPrice() * quantity;

            // Create order
            Orders order = new Orders(orderId, customerId, shipAddress, totalAmount);

            // Create order item
            List<OrderItems> items = new ArrayList<>();
            OrderItems item = new OrderItems(
                    orderDAO.generateOrderItemId(),
                    productId,
                    orderId,
                    product.getName(),
                    quantity,
                    product.getPrice(),
                    totalAmount
            );
            items.add(item);

            // Save to database
            boolean success = orderDAO.createOrder(order, items);

            if (success) {
                view.showMessage("Order created successfully");
                view.refreshTables();
            } else {
                view.showMessage("Failed to create order");
            }
        } catch (NumberFormatException e) {
            view.showMessage("Invalid quantity format");
        }
    }

    private void viewOrderHistory() {
        String customerId = view.getCustomerId();
        if (customerId.isEmpty()) {
            view.showMessage("Please enter a customer ID");
            return;
        }

        // Verify customer exists
        Customers customer = customerDAO.getCustomerById(customerId);
        if (customer == null) {
            view.showMessage("Customer ID " + customerId + " does not exist");
            return;
        }

        List<Orders> orders = orderDAO.getOrdersByCustomerId(customerId);
        if (orders.isEmpty()) {
            view.showMessage("No orders found for customer ID " + customerId);
            return;
        }

        List<OrderItems> items = new ArrayList<>();
        for (Orders order : orders) {
            items.addAll(orderDAO.getOrderItemsByOrderId(order.getOrder_id()));
        }

        view.setOrderHistory(orders, items);
        view.showMessage("Order history loaded for customer ID " + customerId);
    }

    private void calculateOrderTotal() {
        String orderId = view.getOrderId();
        if (orderId.isEmpty()) {
            view.showMessage("Please enter an order ID");
            return;
        }

        // Verify order exists
        Orders order = orderDAO.getOrderById(orderId);
        if (order == null) {
            view.showMessage("Order ID " + orderId + " does not exist");
            return;
        }

        List<OrderItems> items = orderDAO.getOrderItemsByOrderId(orderId);
        if (items.isEmpty()) {
            view.showMessage("No items found for order ID " + orderId);
            return;
        }

        double total = 0;
        for (OrderItems item : items) {
            total += item.getTotal_price();
        }

        view.showMessage("Total for order ID " + orderId + ": $" + String.format("%.2f", total));
    }
}