import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class ProductManager {
    public ArrayList<Product> plist;
    public ArrayList<History> histories = new ArrayList<>();
    public ProductManager(LaptopCrawl productsList) {
        productsList.run();
        this.plist = productsList.productsList;
        update();
    }
    public boolean checkExistId(String id) {
        boolean result = false;
        for (Product product : this.plist) {
            if (id.equals(product.getId())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void showAllProducts() {
        History history = new History( "showAllProducts " , new Date());
        histories.add(history);
        for (Product product : this.plist) {
            System.out.println(product.toString());
        }
        updateHistory();
    }

    public void addProduct(String category, String id, String name, String price) {
        History history = new History( "addProduct "+ id+" "+name+" "+price+" "+category , new Date());
        histories.add(history);
        Product product = new Product();
        product.setCategory(category);
        product.setId(id);
        product.setName(name);
        product.setPrice(Integer.parseInt(price));

        this.plist.add(product);
        update();
        updateHistory();
    }

    public void editProduct(String id, String name, String price, String category) {
        History history = new History( "editProduct "+ id+" "+name+" "+price+" "+category, new Date());
        histories.add(history);
        for (Product product : this.plist) {
            if (id.equals(product.getId())) {
                product.setName(name);
                product.setCategory(category);
                product.setPrice(Integer.parseInt(price));
            }
        }
        updateHistory();
        update();
    }

    public void deleteProduct(String id) {
        History history = new History( "deleteProduct id: "+ id , new Date());
        histories.add(history);
        for (int i = 0; i < this.plist.size(); i++) {
            if (this.plist.get(i).getId().equals(id)) {
                this.plist.remove(i);
                break;
            }
        }
        updateHistory();
        update();
    }

    public void sort(boolean type) {
        History history = new History( "sort" , new Date());
        histories.add(history);
        ArrayList<Product> sortList = this.plist;
        if (type) {
            for (int i = 0; i < sortList.size() - 1; i++) {
                for (int j = 0; j < sortList.size() - 1; j++) {
                    if (sortList.get(j).getPrice() > sortList.get(j + 1).getPrice()) {
                        Product temp;
                        temp = sortList.get(j);
                        sortList.set(j, sortList.get(j + 1));
                        sortList.set(j + 1, temp);
                    }
                }
            }
        } else {
            for (int i = 0; i < sortList.size() - 1; i++) {
                for (int j = 0; j < sortList.size() - 1; j++) {
                    if (sortList.get(j).getPrice() < sortList.get(j + 1).getPrice()) {
                        Product temp;
                        temp = sortList.get(j);
                        sortList.set(j, sortList.get(j + 1));
                        sortList.set(j + 1, temp);
                    }
                }
            }
        }
        for (Product product : sortList) {
            System.out.println(product.toString());
        }
        updateHistory();
    }

    public void findProductByName(String productName) {
        History history = new History( "findProductByName"+productName , new Date());
        histories.add(history);
        for (Product product : this.plist) {
            if (product.getName().equals(productName)) {
                System.out.println(product.toString());
            }
        }
        updateHistory();
    }

    public void findProductById(String productId) {
        History history = new History( "findProductById id: "+productId , new Date());
        histories.add(history);
        for (Product product : this.plist) {
            if (product.getId().equals(productId)) {
                System.out.println(product.toString());
            }
        }
        updateHistory();
    }
    public void showHistory(){
        String fileName = "C:\\Users\\tran\\IdeaProjects\\CaseStudy2.0\\src\\History.txt";
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader);
            String line="";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException var6) {
            System.err.format("IOException: %s%n", var6);
        }
    }
    public void updateHistory(){
        String fileName = "C:\\Users\\tran\\IdeaProjects\\CaseStudy2.0\\src\\History.txt";
        try {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(writer);
            for(History his:histories) {
                String laptopsInfo = his.toString();
                bw.write(laptopsInfo);
                bw.newLine();
            }
            bw.close();

        } catch (IOException var6) {
            System.err.format("IOException: %s%n", var6);
        }
    }
    public void update() {
        History history = new History( "update" , new Date());
        histories.add(history);
        try {
            FileWriter writer = new FileWriter("C:\\Users\\tran\\IdeaProjects\\CaseStudy2.0\\src\\ProductList.txt");
            BufferedWriter bw = new BufferedWriter(writer);
            for (Product updateProduct : this.plist) {
                String content = updateProduct.getCategory() + ", " + updateProduct.getId() + ", " + updateProduct.getName() + ", " + updateProduct.getPrice();
                bw.write(content);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        updateHistory();
    }

}
