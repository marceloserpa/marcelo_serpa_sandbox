package marceloserpa;

public class Sale {

    private int id;
    private int salesman_id;
    private int store_id;
    private int total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalesman_id() {
        return salesman_id;
    }

    public void setSalesman_id(int salesman_id) {
        this.salesman_id = salesman_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", salesman_id=" + salesman_id +
                ", store_id=" + store_id +
                ", total=" + total +
                '}';
    }
}
