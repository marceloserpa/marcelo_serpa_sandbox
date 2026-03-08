package marceloserpa;

public class SaleEnriched {

    public int salesman_id;
    public String salesman_name;
    public String store_name;
    public double total;
    public String city;
    public String state;

    public int getSalesman_id() {
        return salesman_id;
    }

    public void setSalesman_id(int salesman_id) {
        this.salesman_id = salesman_id;
    }

    public String getSalesman_name() {
        return salesman_name;
    }

    public void setSalesman_name(String salesman_name) {
        this.salesman_name = salesman_name;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SaleEnriched{" +
                "salesman_id=" + salesman_id +
                ", salesman_name='" + salesman_name + '\'' +
                ", store_name='" + store_name + '\'' +
                ", total=" + total +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
