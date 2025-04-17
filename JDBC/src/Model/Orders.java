package Model;

public class Orders {
    public String order_id;
    public String customers_id;
    public String shipAddress;
    public double amount;

    public Orders(String order_id, String customers_id, String shipAddress, double amount) {
        this.order_id = order_id;
        this.customers_id = customers_id;
        this.shipAddress = shipAddress;
        this.amount = amount;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCustomers_id() {
        return customers_id;
    }

    public void setCustomers_id(String customers_id) {
        this.customers_id = customers_id;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "order_id='" + order_id + '\'' +
                ", customers_id=" + customers_id +
                ", shipAddress='" + shipAddress + '\'' +
                ", amount=" + amount +
                '}';
    }
}
