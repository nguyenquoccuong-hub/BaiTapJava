package Model;

public class OrderItems {
    public String order_item_id;
    public String products_id;
    public String order_id;
    public String product_name;
    public int quantity;
    public double unit_price;
    public double total_price;

    public OrderItems(String order_item_id, String products_id, String order_id, String produuct_name, int quantity, double unit_price, double total_price) {
        this.order_item_id = order_item_id;
        this.products_id = products_id;
        this.order_id = order_id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.total_price = total_price;
    }

    public String getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(String order_item_id) {
        this.order_item_id = order_item_id;
    }

    public String getProducts_id() {
        return products_id;
    }

    public void setProducts_id(String products_id) {
        this.products_id = products_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getTotal_price() {
        total_price = unit_price*quantity;
        return total_price;
    }


}
