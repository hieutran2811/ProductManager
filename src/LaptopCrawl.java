import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LaptopCrawl {
    public ArrayList<Product> productsList;
    public LaptopCrawl(){
    }

    public void run() {
        productsList = new ArrayList<>();
        String path1 = "https://fptshop.com.vn/may-tinh-xach-tay/apple-macbook";
        String p1 = "'Home','\\s*(.*?)',(.*?),(.*?),0000,.*href=\"(.*?)\" title=";
        crawl(path1, p1);
    }

    public void crawl(String webPath, String pattern) {
        try {
            URL url = new URL(webPath);
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            scanner.useDelimiter("\\Z");
            String content = scanner.next();
            scanner.close();
            content = content.replaceAll("\\n+", "");
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(content);
            while (matcher.find()) {
                Product laptop = new Laptop();
                laptop.setName(matcher.group(1));
                laptop.setId(matcher.group(2));
                laptop.setPrice(Integer.parseInt(matcher.group(3)));
                laptop.setCategory("Laptop");
                this.productsList.add(laptop);
            }
            saveProduct();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveProduct() {
        String fileName = "C:\\Users\\tran\\IdeaProjects\\CaseStudy2.0\\src\\ProductList.txt";
        try {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(writer);
            for(Product pr:this.productsList) {
                String laptopsInfo = pr.getCategory() + ", " + pr.getId() + ", " + pr.getName() + ", " + pr.getPrice();
                bw.write(laptopsInfo);
                bw.newLine();
            }
            bw.close();

        } catch (IOException var6) {
            System.err.format("IOException: %s%n", var6);
        }
    }
}