import java.util.ArrayList;
import java.util.Scanner;

public class Main{

    static Integer ids=101;


    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        homepage();
    }

    static ArrayList<User> users = new ArrayList<>();

    static ArrayList<Ticket> soldTickets = new ArrayList<>();

    static ArrayList<Ticket> waiters = new ArrayList<>();

    static ArrayList<Train> trainList = new ArrayList<>();

    public static void homepage(){
        System.out.println("------Railway Ticket Booking Application----------");
        System.out.println("    -> Enter 1 for Admin Login");
        System.out.println("    -> Enter 2 for User Login");
        System.out.println("    -> Enter 3 to Quit Application");
        System.out.print("Enter Choice :  ");
        int n = Integer.parseInt(sc.nextLine());
        if(n==1){
            Admin.login();
        }
        else if(n==2){
            User.userType();
        }
        else if(n==3){
            System.exit(0);
        }
        else{
            System.out.println("\n\nInvalid option ! \nEnter correct choice");
            homepage();
        }
    }
}