import java.util.LinkedList;
public class Queue {
    private LinkedList<Customer> queue = new LinkedList<>();
    public int getSize(){
        return queue.size();
    }
    public void enqueue(Customer c){
        queue.addLast(c);
    }
    public Customer dequeue(){
        return queue.removeFirst();
    }
    public boolean isEmpty(){
        return getSize()==0;
    }
    public int totalTicket(){
        int sum =0;
        for (int i =0;i<queue.size();i++){
            sum+=queue.get(i).getNoOfTickets();
        }
        return sum;
    }
}
