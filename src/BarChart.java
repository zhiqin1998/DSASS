import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class BarChart extends ApplicationFrame {
    JFreeChart barChart;

    public BarChart(String applicationTitle, String chartTitle, Counter[] counter) {
        super(applicationTitle);
        barChart = ChartFactory.createBarChart(
                chartTitle,
                "Counter",
                "Value",
                createDataset(counter),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1280, 720));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset(Counter[] counter) {
        final String totalTicket = "Total Ticket Sold";
        final String vipServed = "VIP Served";
        final String customerServed = "Customer Served;";
        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();
        for (int i = 0; i < counter.length; i++) {
            dataset.addValue(counter[i].getTotalTicketSold(), counter[i].getName(), totalTicket);
            dataset.addValue(counter[i].getCustomerServed(), counter[i].getName(), customerServed);
            dataset.addValue(counter[i].getVipServed(), counter[i].getName(), vipServed);
        }
        return dataset;
    }

}