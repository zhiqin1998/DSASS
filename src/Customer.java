public class Customer implements Comparable<Customer> {
    private int arrivalTime;
    private char type;
    private int noOfTickets;
    private int startProcessing;
    private int endProcessing;
    private Counter counter;
    private int waitingTime;
    private boolean served = false;
    public Customer(int arrivalTime,char type,int noOfTickets){
        this.arrivalTime = arrivalTime;
        this.type = type;
        this.noOfTickets = noOfTickets;
    }

    public boolean isServed() {
        return served;
    }

    public void setServed(boolean served) {
        this.served = served;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getStartProcessing() {
        return startProcessing;
    }

    public void setStartProcessing(int startProcessing) {
        this.startProcessing = startProcessing;
    }

    public int getEndProcessing() {
        return endProcessing;
    }

    public void setEndProcessing(int endProcessing) {
        this.endProcessing = endProcessing;
    }

    @Override
    public int compareTo(Customer o) {
        return this.arrivalTime-o.arrivalTime;
    }
}
