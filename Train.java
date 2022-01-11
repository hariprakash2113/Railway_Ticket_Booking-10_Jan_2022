import java.util.ArrayList;

public class Train {

    static String trainName = "CBE - CHENNAI CENTAL EXPRESS";
    static String pnrNo = "12345678";
    static int seatsInTrain = 10;
    static String depatureTime = "14:00";
    static String arrivalTime = "22:00";

    static int[][] seats = new int[10][6];
    static String stops[] = { "Coimbatore", "Tiruppur", "Erode", "Salem", "Katpaadi", "Chennai Central" };

    public static void book(int ind) {
        System.out.println("------------Booking Portal--------------");
        for (int i = 0; i < seats.length; i++) {
            System.out.println((i+1)+" => " + stops[i]);
        }
        System.out.print("Select Onboarding station : ");
        int from = Integer.parseInt(Main.sc.nextLine());
        if (from > 6 || from < 1) {
            System.out.println("Invalid city ! press 1 to enter again or 0 to exit");
            int n = Integer.parseInt(Main.sc.nextLine());
            if (n == 1) {
                book(ind);
            } else {
                User.userPage(ind);
            }
        }
        for (int i = 0; i < seats.length; i++) {
            System.out.println((i+1)+" => " + stops[i]);
        }
        System.out.print("Select Destination station : ");
        int to = Integer.parseInt(Main.sc.nextLine());
        if (from == to) {
            System.out.println("From and to stations are same ! Please enter correct Destination");
            System.out.println("press 1 to enter again or 0 to exit");
            int n = Integer.parseInt(Main.sc.nextLine());
            if (n == 1) {
                book(ind);
            } else {
                User.userPage(ind);
            }
        } else {
            System.out.printf("You're booking Ticket from %s to %s\n", stops[from - 1], stops[to - 1]);
            int Amt = ((to - from) * 50);
            System.out.println("Total Fare => " + Amt);
            System.out.println("Press Enter 1 to confirm and continue or Any other number to Exit");
            int opt = Integer.parseInt(Main.sc.nextLine());
            if (opt == 1) {
                status(ind, from, to, Amt);
            } else {
                User.userPage(ind);
            }
        }
    }

    public static void status(int ind, int from, int to, int Amt) {
        int count = 0;
        y: for (int i = 0; i < seats.length; i++) {
            x: for (int j = from - 1; j < to; j++) {
                if (seats[i][j] == 0) {
                    count++;
                } else {
                    count = 0;
                    break x;
                }
            }
            if (count == (to - from) + 1) {
                doBook(ind, from, to, i, Amt);
                break y;
            }
            count = 0;
        }
        if (count > (to - from) + 1 || count < (to - from) + 1) {
            System.out.println("You've been waitlised since seats are unavailable ! ");
            Main.waiters.add(new Ticket(Main.users.get(ind).id, from, to, Amt));
            System.out.println("Press any key to continue");
            Main.sc.nextLine();
            User.userPage(ind);
        }
    }

    public static void doBook(int ind, int from, int to, int i, int Amt) {
        if (Main.users.get(ind).walletAmount < Amt) {
            System.out.println("Insufficient Wallet Amount");
            User.userPage(ind);
        }
        Main.users.get(ind).walletAmount -= Amt;
        for (int j = from - 1; j < to; j++) {
            seats[i][j] = Main.users.get(ind).id;
        }
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 6; k++) {
                System.out.print(seats[j][k] + " ");
            }
            System.out.println();
        }
        Main.soldTickets.add(new Ticket(Main.users.get(ind).id, i, from, to, Amt));
        System.out.printf("Ticket has been booked from %s to %s%n", stops[from - 1], stops[to - 1]);
        System.out.println("Your seat No. => " + (i + 1));
        System.out.println("Press any key to redirect to login page");
        Main.sc.nextLine();
        User.userPage(ind);
    }



    public static void showTrains(int ind) {
        System.out.println("-----Available Trains-----");
        System.out.println("Train Name => " + trainName);
        System.out.println("PNR NUMBER => " + pnrNo);
        System.out.println("Total number of Seats => " + seatsInTrain);
        System.out.println("Departure Time => " + depatureTime);
        System.out.println("Arrival Time => " + arrivalTime);
        System.out.println("Total no. of Stops => " + stops.length);
        for (int i = 0; i < stops.length; i++) {
            System.out.println((i + 1) + "    -> " + stops[i]);
        }
        User.userPage(ind);
    }

    public static void cancellation(int ind, ArrayList<Ticket> tp) {

        System.out.print("Enter Ticket Number to cancel Ticket or press 0 to exit : ");
        int n = Integer.parseInt(Main.sc.nextLine());
        if (n == 0) {
            User.showBookedDetails(ind);
        } else {
            int cap = Main.soldTickets.indexOf(tp.get(n - 1));
            doCancel(ind, cap, n);
        }
    }

    static void doCancel(int ind, int cap, int n) {
        for (int j = Main.soldTickets.get(cap).from - 1; j < Main.soldTickets.get(cap).to; j++) {
            seats[Main.soldTickets.get(cap).seat][j] = 0;
        }
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 6; k++) {
                System.out.print(seats[j][k] + " ");
            }
            System.out.println();
        }
        allocate(cap);
        System.out.printf("Your Ticket from %s to %s has been cancelled\n", stops[Main.soldTickets.get(cap).from - 1],
                stops[Main.soldTickets.get(cap).to - 1]);
        Main.users.get(ind).walletAmount += (Main.soldTickets.get(cap).cost - 15);
        System.out.printf("Amount %d has been Refunded and Added to your Wallet\n",
                Main.soldTickets.get(cap).cost - 15);
        System.out.println("**Note :- Cancellation charge Rs.15/-");
        Main.soldTickets.remove(cap);
        System.out.println("Press any key to continue");
        Main.sc.nextLine();
        User.userPage(ind);
    }

    static int findID(int id) {
        for (int i = 0; i < Main.users.size(); i++) {
            if (id == Main.users.get(i).id) {
                return i;
            }
        }
        return 0;
    }

    static void allocate(int cap) {
        Ticket canceller = Main.soldTickets.get(cap);
        for (int i = 0; i < Main.waiters.size(); i++) {
            if (Main.waiters.get(i).from >= canceller.from && Main.waiters.get(i).to <= canceller.to) {
                Main.soldTickets.get(cap).id = Main.waiters.get(i).id;
                changeSeatDet(Main.waiters.get(i).from, Main.waiters.get(i).to, canceller.seat, Main.waiters.get(i).id);
                int n = findID(Main.waiters.get(i).id);
                Main.users.get(n).walletAmount -= (Main.waiters.get(i).to-Main.waiters.get(i).from)*50;
                return;
            }
        }
        return;
    }

    static void changeSeatDet(int from, int to, int seat, int id) {
        for (int j = from - 1; j < to; j++) {
            seats[seat][j] = id;
        }
        return;
    }
}
