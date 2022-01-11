public class Admin {
    static void login() {
        System.out.println("----Welcome Admin----");
        System.out.print("Enter Username or 0 to exit : ");
        String uname = Main.sc.nextLine();
        if(uname.equals("0")){
            Main.homepage();
        }
        System.out.print("Enter Password : ");
        String pass = Main.sc.nextLine();
        if (uname.equals("a") && pass.equals("z")) {
            adminpage();
        } else {
            System.out.println("Invalid Credentials ! Try again");
            login();
        }
    }

    static void adminpage() {
        System.out.println("Enter 1 to view Train Details or 0 to exit");
        String opt = Main.sc.nextLine();
        if (opt.equals("1")) {
            for (int j = 0; j < 10; j++,System.out.println()) {
                for (int k = 0; k < 6; k++) {
                    System.out.print(Train.seats[j][k] + " ");
                }
            }
            System.out.println("Press any key to Exit");
            Main.sc.nextLine();
            login();
        }
        else if(opt.equals("0")){
            Main.homepage();
        }
    }
}
