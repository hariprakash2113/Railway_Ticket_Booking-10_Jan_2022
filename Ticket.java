public class Ticket {
    int id;
    int seat;
    int from;
    int to;
    int cost;

    public Ticket(int id, int seat, int from, int to, int cost) {
        this.id = id;
        this.seat = seat;
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
    public Ticket(int id,  int from, int to, int cost) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    // for(int j=0;j<10;j++){
    //     for (int k = 0; k < 6; k++) {
    //         System.out.print(seats[j][k]+" ");
    //     }
    //     System.out.println();
    // }
    
}
