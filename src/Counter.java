public class Counter {
    private int timeToSell;
    private char name;
    private Customer serving=null;

    public int getTimeToSell() {
        return timeToSell;
    }

    public int getEndTime(){
        return this.serving.getEndProcessing();
    }

    public Counter(char name, int timeToSell){
        this.name = name;

        this.timeToSell = timeToSell;
    }
    public void serveCustomer(Customer serving, int time){
        this.serving = serving;
        this.serving.setStartProcessing(time);
        this.serving.setEndProcessing(timeToSell*this.serving.getNoOfTickets()+time);
        this.serving.setWaitingTime(time-this.serving.getArrivalTime());
        this.serving.setCounter(this);
        this.serving.setServed(true);
    }

    public char getName() {
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
