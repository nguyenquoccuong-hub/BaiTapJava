package Model;

public class Products {
    public String product_id;
    public String name;
    public double price;
    public int stock_quantity;

    public Products(String product_id, String name, double price, int stock_quantity) {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.stock_quantity = stock_quantity;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public int getStockQuantity() {
        return stock_quantity;
    }




    @Override
    public String toString() {
        return "Products{" +
                "product_id='" + product_id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock_quantity=" + stock_quantity +
                '}';
    }
}
