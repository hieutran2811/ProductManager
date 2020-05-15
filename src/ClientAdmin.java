import java.util.Scanner;

public class ClientAdmin {
    private static LaptopCrawl laptopCrawl= new LaptopCrawl();
    private static ProductManager productManager= new ProductManager(laptopCrawl);
    public static void start (){
        System.out.println("------------------*** Menu ***------------------");
        System.out.println("1. Show all products");
        System.out.println("2. Add new product");
        System.out.println("3. Edit product");
        System.out.println("4. Delete product by id");
        System.out.println("5. Sort products list");
        System.out.println("6. Find product");
        System.out.println("7. Show history");
        System.out.println("0. Exit");
        System.out.println("-------------------------------------------------");
        System.out.print("Your choice: ");
        Scanner option = new Scanner(System.in);
        String choose = option.next();

        Scanner input = new Scanner(System.in).useDelimiter("\n");
        switch (choose) {
            case "1":
                productManager.showAllProducts();
                break;
            case "2":
                String id ;
                System.out.print("New product id: ");
                id = input.nextLine();
                while (productManager.checkExistId(id)) {
                    System.out.println("This id already exist !");
                    System.out.print("New product id: ");
                    id = input.next();
                }

                System.out.print("Categoty: ");
                String category = input.next();

                System.out.print("Name: ");
                String name = input.next();

                System.out.print("Price: ");
                String price = input.next();
                productManager.addProduct(category, id, name, price);
                break;
            case "3":
                // Edit product
                System.out.print("Edit product id: ");
                String editId = input.next();
                System.out.print("New product name: ");
                String editName = input.next();
                System.out.print("New product price: ");
                String editPrice = input.next();
                System.out.print("New product category: ");
                String editCategory = input.next();
                productManager.editProduct(editId, editName, editPrice, editCategory);
                break;
            case "4":
                // Delete
                System.out.print("Delete product id: ");
                String deleteId = option.next();
                productManager.deleteProduct(deleteId);
                break;
            case "5":
                System.out.println("Sort option:");
                System.out.println("1. Ascending");
                System.out.println("2. Descending ");
                System.out.print("Your choose: ");
                int sortOption = option.nextInt();
                switch (sortOption) {
                    case 1:
                        System.out.println("Ascending price list");
                        productManager.sort(true);
                        break;
                    case 2:
                        System.out.println("Descending price list");
                        productManager.sort(false);
                        break;
                }

                break;
            case "6":
                System.out.println("Find option:");
                System.out.println("1. Find by name");
                System.out.println("2. Find by id ");
                System.out.print("Your choose: ");
                int findOption = option.nextInt();
                switch (findOption) {
                    case 1:
                        System.out.print("Input product name: ");
                        String productName = input.next();
                        productManager.findProductByName(productName);
                        break;
                    case 2:
                        System.out.print("Input product id: ");
                        String productId = input.next();
                        productManager.findProductById(productId);
                        break;
                }
                break;
            case "7":
                productManager.showHistory();
                break;
            case "0":
                productManager.update();
                System.exit(0);
            default:
                System.out.println("Wrong option, please choose again !");
        }
        start();
    }
}
