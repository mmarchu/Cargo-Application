package th.ac.kmutnb.addlistview1;

public class Product {

    private String id,name,position,amount;

    public Product(String id, String name, String position, String amount) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
