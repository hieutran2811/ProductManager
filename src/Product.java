public class Product {
    protected String id;
    protected String name;
    protected int price;
    protected String category;

    public Product() {
    }

    public Product(String id, String name, int price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return  "-------------------------------------------------" + "\n" +
                "Id: " + id + "\n" +
                "Category: " + category + "\n" +
                "Name: " + name + "\n" +
                "Price : " + price;
    }
}
