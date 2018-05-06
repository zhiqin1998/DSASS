import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Simulator {
    static final int NO_OF_QUEUE = 2;
    static final int NO_OF_COUNTER = 4;
    static ArrayList<Customer> input = new ArrayList<>();
    static Queue[] queues = new Queue[NO_OF_QUEUE];
    static Counter[] counters = new Counter[NO_OF_COUNTER];
    static int time =-1;
    private JPanel jPanel;
    private JTextField timeField;
    private JButton simulateBtn;
    private JPanel counterName;
    private JPanel counterFrame;
    private JPanel queuesFrame;
    private JButton saveLog;
    private JButton saveReport;
    private String printText;
    private StringBuilder logText = new StringBuilder();
    private StringBuilder reportText = new StringBuilder();
    Formatter formatter = new Formatter(reportText);
    ImageIcon customerIcon = new ImageIcon("customer.png");
    ImageIcon nullIcon = new ImageIcon("null.png");

    public Simulator() {
        simulateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (simulateBtn.getText().equals("Simulate")) {
                    try {
                        simulateBtn.setVisible(false);
                        simulate();
                        System.out.println("Console: Simulation Ended\n");
                        System.out.println("Total Completion Time: " + time);
                        System.out.println("-------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("|%-10s|%-10s|%-20s|%-15s|%-20s|%-15s|%-8s|%-8s|\n", "Customer", "Arrival", "Start Processing", "End Processing", "Processing Time", "Waiting Time", "Queue", "Counter");
                        formatter.format("|%-10s|%-10s|%-20s|%-15s|%-20s|%-15s|%-8s|%-8s|\n", "Customer", "Arrival", "Start Processing", "End Processing", "Processing Time", "Waiting Time", "Queue", "Counter");
                        for (int i = 0; i < input.size(); i++) {
                            String type, cName;
                            if (input.get(i).getType() == 'V') {
                                type = "VIP";
                            } else {
                                type = "Normal";
                            }
                            cName = input.get(i).getCounter().getName();
                            formatter.format("|%-10d|%-10d|%-20d|%-15d|%-20d|%-15d|%-8s|%-8s|\n", (i + 1), input.get(i).getArrivalTime(), input.get(i).getStartProcessing(), input.get(i).getEndProcessing(), (input.get(i).getEndProcessing() - input.get(i).getStartProcessing()), input.get(i).getWaitingTime(), type, cName);
                            System.out.printf("|%-10d|%-10d|%-20d|%-15d|%-20d|%-15d|%-8s|%-8s|\n", (i + 1), input.get(i).getArrivalTime(), input.get(i).getStartProcessing(), input.get(i).getEndProcessing(), (input.get(i).getEndProcessing() - input.get(i).getStartProcessing()), input.get(i).getWaitingTime(), type, cName);
                        }
                        saveLog.setVisible(true);
                        saveReport.setVisible(true);
                        simulateBtn.setText("End Program");
                        simulateBtn.setVisible(true);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    System.exit(0);
                }
            }
        });
        saveLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PrintWriter pw = new PrintWriter(new FileOutputStream("log.txt"));
                    pw.print(logText.toString());
                    System.out.println("Console: Saved log files.");
                    pw.close();
                } catch (FileNotFoundException e1) {
                    System.out.println("IO error");
                }
            }
        });
        saveReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PrintWriter pw = new PrintWriter(new FileOutputStream("report.txt"));
                    pw.print(reportText.toString());
                    System.out.println("Console: Saved report files.");
                    pw.close();
                } catch (FileNotFoundException e1) {
                    System.out.println("IO error");
                }

            }
        });
    }

    public static void main(String[] args) {
        //initializing variables
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new Queue();
        }
        System.out.print("Use default counter settings? (y/n): ");
        if (sc.nextLine().equals("n")) {
            System.out.print("Enter numbers of counter: ");
            counters = new Counter[Integer.parseInt(sc.nextLine())];
            for (int i = 1; i <= counters.length; i++) {
                System.out.print("Enter name of counter " + i + ": ");
                String name = sc.nextLine();
                System.out.print("Enter time to sell for the counter " + i + ": ");
                int timeToSell = Integer.parseInt(sc.nextLine());
                counters[i - 1] = new Counter(name, timeToSell);
                System.out.println();
            }
        } else {

            counters[0] = new Counter("A", 10);
            counters[1] = new Counter("B", 15);
            counters[2] = new Counter("C", 30);
            counters[3] = new Counter("D", 15);
        }
        System.out.println("Enter customer inputs: ");
        //getting input
        int N  = Integer.parseInt(sc.nextLine());
        for (int i =0;i<N;i++) {
            String[] in = sc.nextLine().split(" ");
            input.add(new Customer(Integer.parseInt(in[0]),in[1].charAt(0),Integer.parseInt(in[2])));
        }
        //bubble-sorting input based on arrival time (optional)
        for (int i =1;i<input.size();i++){
            for (int j=1;j<input.size()-1;j++){
                if (input.get(j).compareTo(input.get(j+1))>0){
                    Customer temp = input.get(j+1);
                    input.set(j+1,input.get(j));
                    input.set(j,temp);
                }
            }
        }

        System.out.println("All set! Click \"Simulate\" to begin!");
        JFrame frame = new JFrame("Simulator");
        frame.setContentPane(new Simulator().jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    public void simulate() throws InterruptedException {
        //simulate
        while (checkEnd(queues,counters,input)){
            printText = "";
//            Uncomment to enable real time simulation
//            Thread.sleep(1000);
            time++;
            //Customer leaves counter if done
            for (int i =0;i<counters.length;i++){
                if (!counters[i].isEmpty()) {
                    if (counters[i].getEndTime() == time) {
                        counters[i].customerLeave();
                        printText+="Customer left Counter " + counters[i].getName()+" | ";
                    }
                }
            }
            //Customer enters queues based on arrival time
            for (int i =0;i<input.size();i++){
                if (input.get(i).getArrivalTime()==time && input.get(i).getType()=='N'){
                    findBestQueue(queues).enqueue(input.get(i));
                    printText+="Customer enter Normal Queue | ";
                }
                else if (input.get(i).getArrivalTime()==time && input.get(i).getType()=='V'){
                    queues[0].enqueue(input.get(i));
                    printText+="Customer enter VIP Queue | ";
                }
                else if (input.get(i).getArrivalTime()>time){
                    break;
                }
            }
            //Customer enters counter from queues if available
            enterCounterLoop:
            while (findBestCounter(counters)!=null&&checkQueue(queues)){
                for (int j=0;j<queues.length;j++) {
                    if (!queues[j].isEmpty()) {
                        for (int i = 0; i < queues[j].getSize(); i++) {
                            Counter temp = findBestCounter(counters);
                            temp.serveCustomer(queues[j].dequeue(), time);
                            printText+="Customer enters Counter "+ temp.getName()+" | ";
                            continue enterCounterLoop;
                        }
                    }
                }
            }
//            display();
            logText.append("(Log) - Time: ").append(time).append(" | ");
            System.out.print("(Log) - Time: "+ time+" | ");
            if (printText.equals("")){
                logText.append("Nothing happened!\n");
                System.out.println("Nothing happened!");
            }
            else {
                logText.append(printText).append("\n");
                System.out.println(printText);
            }
        }
    }

    public static Counter findBestCounter(Counter[] c){
        Counter best = null;
        int min = Integer.MAX_VALUE;
        for (int i = 0;i<c.length;i++){
            int temp = c[i].getTimeToSell();
            if (temp<min && c[i].isEmpty()){
                min= temp;
                best = c[i];
            }
        }
        return best;
    }

    public static Queue findBestQueue(Queue[] q){
        Queue best = null;
        int totalTickets = Integer.MAX_VALUE;
        for (int i =1;i<q.length;i++){
            int temp = q[i].totalTicket();
            if (temp<totalTickets){
                totalTickets=temp;
                best = q[i];
            }
        }
        return best;
    }
    public static boolean checkEnd(Queue[] q, Counter[] c, ArrayList<Customer> cust){
        for (int i =0;i<cust.size();i++){
            if (!cust.get(i).isServed()){
                return true;
            }
        }
        for (int i =0;i<q.length;i++){
            if (!q[i].isEmpty()){
                return true;
            }
        }
        for (int i =0;i<c.length;i++){
            if (!c[i].isEmpty()){
                return true;
            }
        }

        return false;
    }

    public static boolean checkQueue(Queue[] q){
        for(int i =0;i<q.length;i++){
            if (!q[i].isEmpty()){
                return true;
            }
        }
        return false;
    }
    public void display(){
        new Thread()
        {
            @Override
            public void run()
            {
                timeField.setText("Time: "+time);
                counterName = new JPanel(new FlowLayout());
                counterFrame= new JPanel(new FlowLayout());
                queuesFrame= new JPanel(new GridLayout(queues.length,1));
                for (int i =0;i<counters.length;i++){
                    JLabel temp = new JLabel();
                    temp.setText("Counter "+ counters[i].getName());
                    counterName.add(temp);
                    if (!counters[i].isEmpty()){
                        JLabel temp2 = new JLabel();
                        temp2.setIcon(customerIcon);
                        counterFrame.add(temp2);
                    }
                    else {
                        JLabel temp2 = new JLabel();
                        temp2.setIcon(nullIcon);
                        counterFrame.add(temp2);
                    }
                }
                for (int i =0;i<queues.length;i++){
                    JPanel temp = new JPanel(new FlowLayout());
                    for (int j =0;j<queues[i].getSize();j++){
                        JLabel temp2 = new JLabel();
                        temp2.setIcon(customerIcon);
                        temp.add(temp2);
                    }
                    queuesFrame.add(temp);
                }
                jPanel.repaint();
                jPanel.revalidate();

            }
        }.start();

    }
}
