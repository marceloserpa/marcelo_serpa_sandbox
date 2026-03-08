package marceloserpa;

public class Salesman {

    private int id;
    private String name;
    private int store_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", store_id=" + store_id +
                '}';
    }
}
