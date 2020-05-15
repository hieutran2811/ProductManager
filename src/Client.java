import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static ArrayList<Account> accounts = new ArrayList<>();
    private static String acc;
    public static void registrationAccount (){
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("tài khoản: ");
        String account = input.next();
        System.out.print("mật khẩu: ");
        String password = input.next();
        System.out.print("nhập lại mật khẩu: ");
        String password1 = input.next();
        System.out.println("bạn có đồng ý với điều khoản của chúng tôi?(y/n)");
        System.out.println("bạn phải gia nhập câu lạc bộ long đẹp trai");
        System.out.println("bạn phải thông qua kiểm tra của kiều anh xinh");
        String pass = input.next();
        if (pass.equals("y")){
            if (password.equals(password1)){
                Account account1 = new Account(account,password);
                accounts.add(account1);
            }else registrationAccount();
        }else registrationAccount();
        Client.writeAccount();
        ClientUser.start();
    }
    public static void login (){
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        System.out.print("tài khoản: ");
        String account = input.next();
        System.out.print("mật khẩu: ");
        String password = input.next();
        for (Account value : accounts) {
            if (account.equals(value.getName()) && password.equals(value.getPasswords())) {
                if (acc.equals("user")) {
                    ClientUser.start();
                }else ClientAdmin.start();
            }
        }
        System.out.println("hãy đăng nhập lại: ");
        Client.login();
    }
    public static void writeAccount(){

        String fileName = "C:\\Users\\tran\\IdeaProjects\\CaseStudy2.0\\src\\Account.txt";
        try {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(writer);
            for(Account his:accounts) {
                String accountInfo = his.toString();
                bw.write(accountInfo);
                bw.newLine();
            }
            bw.close();

        } catch (IOException var6) {
            System.err.format("IOException: %s%n", var6);
        }
    }

    public static void readAccount() {
        String fileName = "C:\\Users\\tran\\IdeaProjects\\CaseStudy2.0\\src\\Account.txt";
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                String[] results=line.split(",");
                Account account = new Account(results[0],results[1]);
                accounts.add(account);
            }
            br.close();
        } catch (IOException var6) {
            System.err.format("IOException: %s%n", var6);
        }

    }

    public static void start(){
        Client.readAccount();
        System.out.println("Who are you?");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.println("-------------------------------------------------");
        System.out.print("Your choice: ");
        Scanner option = new Scanner(System.in);
        String choice = option.next();
        System.out.println();
        switch (choice){
            case "1":
                acc="admin";
                System.out.println("Mời bạn đăng nhập: ");
                Client.login();
                break;
            case "2":
                acc="user";
                System.out.println("1. đăng nhập");
                System.out.println("2. đăng ký");
                String choice1 = option.next();
                switch (choice1){
                    case "1":
                        Client.login();
                        break;
                    case "2":
                        Client.registrationAccount();
                        break;
                }
                break;
            default:
                System.out.println("Wrong option, please choose again !");
                System.out.println();
                start();
        }
    }
}
