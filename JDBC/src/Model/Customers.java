package Model;

public class Customers {
    public String customer_id;
    public String name;
    public String address;
    public String gender;

    public Customers(String customer_id, String name, String address, String gender) {
        this.customer_id = customer_id;
        this.name = name;
        this.address = address;
        this.gender = gender;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customer_id='" + customer_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
