import java.util.ArrayList;

public class User {

    String Name;
    String mobileNo;
    String password;
    Integer id;
    String booked;
    Integer walletAmount=3500;
    
    public User(String name, String mobileNo, String password, Integer id) {
        Name = name;
        this.mobileNo = mobileNo;
        this.password = password;
        this.id = id;
    }

    public static void userType(){
        System.out.println("----------Welcome User----------");
        System.out.println("    -> Enter 1 If New User");
        System.out.println("    -> Enter 2 If Existing User");
        System.out.println("    -> Enter 3 to Return Homepage");
        System.out.print("Enter Choice :  ");
        int n=Integer.parseInt(Main.sc.nextLine());

        if(n==1){
            userSignUp();
        }
        else if(n==2){
            userLogin();
        }
        else if(n==3){
            Main.homepage();
        }
        else{
            System.out.println("Invalid Option ! Enter the Correct Key....");
            userType();
        }
    }

    public static void userSignUp(){

        System.out.println("----Enter Following Details to create an account----");
        System.out.print("Enter Your Name : ");
        String name = Main.sc.nextLine();
        System.out.print("Enter Mobile Number : ");
        String mobileNo = Main.sc.nextLine();
        System.out.print("Enter Password : ");
        String password = Main.sc.nextLine();
        Main.users.add(new User(name, mobileNo, password,(Main.ids++)));
        System.out.println("Account Created Successfully");
        System.out.println("Your Login Credentials :- ");
        System.out.println("ID =>"+ (Main.ids-1));
        System.out.println("Password => "+password);
        System.out.println("Please login to use Application");
        userType();
    }

    public static void userLogin(){
        System.out.println("-----Please Enter your ID and Password to continue-----");
        System.out.println("Forgot User Credentials ? Press 1");
        System.out.println("Press 2 to go back");
        System.out.print("Enter User ID or any one of the choice :");
        Integer ID = Integer.parseInt(Main.sc.nextLine());
        if(ID==1){
            forgotPassword();
        }
        else if(ID==2){
            userType();
        }
        System.out.print("Enter Password : ");
        String pass = Main.sc.nextLine();
        authenticate(ID,pass);

    }

    public static void authenticate(Integer ID,String pass){
        int ind=-1;
        for(int i=0;i<Main.users.size();i++){
            if(Main.users.get(i).id==ID){
                ind=i;break;
            }
        }
        if(ind==-1){
            System.out.printf("%d => User ID not Found\n",ID);userLogin();
        }
        else{
            if(Main.users.get(ind).password.equals(pass)){
                userPage(ind);
            }
            else{
                System.out.println("Wrong password ! Enter again to Login");userLogin();
            }
        }
    }

    public static void forgotPassword(){
        System.out.print("Enter Mobile number : ");
        String mn = Main.sc.nextLine();
        int ind=-1;
        for(int i=0;i<Main.users.size();i++){
            if(Main.users.get(i).mobileNo.equals(mn)){
                ind=i;break;
            }
        }
        if(ind==-1){
            System.out.printf("%s => Mobile number not Found\n",mn);userLogin();
        }
        else{
            System.out.print("Enter OTP : ");
            String otp = Main.sc.nextLine();
            if(otp.equals("123456")){
                System.out.println("Your ID -> "+Main.users.get(ind).id);
                System.out.println("Your Password -> "+Main.users.get(ind).password);
                System.out.println("Press Enter to redirect to Login page");
                Main.sc.nextLine();
                userLogin();
            }
        }
    }

    public static void userPage(int ind){
        System.out.printf("-------Welcome , %s ------\n",Main.users.get(ind).Name);
        System.out.println("---Please select an Option---");
        System.out.println("    -> Enter 1 for Train Ticket Booking");
        System.out.println("    -> Enter 2 for view the List of trains available");
        System.out.println("    -> Enter 3 to view/cancel Booked Tickets");
        System.out.println("    -> Enter 4 to view Wallet Amount");
        System.out.println("    -> Enter 5 to Logout");
        int n = Integer.parseInt(Main.sc.nextLine());
        if(n==1){
            Train.book(ind);
        }
        else if(n==2){
            Train.showTrains(ind);
        }
        else if(n==3){
            showBookedDetails(ind);
        }
        else if(n==4){
            System.out.println("Amount left in your Wallet => "+Main.users.get(ind).walletAmount);
            userPage(ind);
        }
        else if(n==5){
            userLogin();
        }
        else{
            System.out.println("Invalid Option ! Enter the Correct Key....");
            userPage(ind);
        }

    }

    static void showBookedDetails(int ind) {
        ArrayList<Ticket> tp=new ArrayList<>();
        for(int i=0;i<Main.soldTickets.size();i++){
            if(Main.users.get(ind).id==Main.soldTickets.get(i).id){
                tp.add(Main.soldTickets.get(i));
            }
        }
        if(tp.isEmpty()){
            System.out.println("You Haven't Bought any Tickets Yet");
            System.out.println("Press any key to redirect to UserPage");
            Main.sc.nextLine();
            User.userPage(ind);
        }
        else{
            System.out.println("-----Booked Tickets-----");
            for(int i=0;i<tp.size();i++){
                System.out.println("Ticket No. : "+(i+1)+" "+Train.stops[tp.get(i).from-1] +"->"+Train.stops[tp.get(i).to-1]);
            }
        }

        System.out.println("Press 1 if you Want to cancel Any Tickets");
        System.out.println("Or any other key to redirect to Userpage");
        String opt = Main.sc.nextLine();
        if(opt.equals("1")){
           Train.cancellation(ind,tp);
        }
        else{
            userPage(ind);
        }
    }
}
