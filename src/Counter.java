public class Counter {
    private int timeToSell;
    private String name;
    private Customer serving=null;
    private int totalTicketSold;
    private int vipServed;
    private int customerServed;

    public Counter(String name, int timeToSell) {
        this.name = name;
        totalTicketSold = 0;
        vipServed = 0;
        customerServed = 0;
        this.timeToSell = timeToSell;
    }

    public int getTotalTicketSold() {
        return totalTicketSold;
    }

    public int getVipServed() {
        return vipServed;
    }

    public int getTimeToSell() {
        return timeToSell;
    }

    public int getEndTime(){
        return this.serving.getEndProcessing();
    }

    public int getCustomerServed() {
        return customerServed;
    }

    public void serveCustomer(Customer serving, int time){
        this.serving = serving;
        this.serving.setStartProcessing(time);
        this.serving.setEndProcessing(timeToSell*this.serving.getNoOfTickets()+time);
        this.serving.setWaitingTime(time-this.serving.getArrivalTime());
        this.serving.setCounter(this);
        this.serving.setServed(true);
        customerServed++;
        totalTicketSold += this.serving.getNoOfTickets();
        if (this.serving.getType() == 'V') {
            vipServed++;
        }
    }

    public String getName() {
        return name;
    }

    public Customer customerLeave(){
        Customer temp = serving;
        serving = null;
        return temp;
    }
    public boolean isEmpty(){
        return serving==null;
    }
}
