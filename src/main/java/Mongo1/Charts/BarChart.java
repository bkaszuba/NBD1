package Mongo1.Charts;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCursor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BarChart {
    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    public BarChart(DistinctIterable names, List<Long> counter){
        int i=0;
        MongoCursor<String> cursor = names.iterator();
        while(cursor.hasNext()) {
            dataset.addValue(counter.get(i),cursor.next(), "");
            i++;
        }
    }

    public void createBarChart(String title, String yName, String xName, String fileName){
        JFreeChart barChart = ChartFactory.createBarChart(
                title,
                xName, yName,
                dataset, PlotOrientation.VERTICAL,
                true, true, false);

        int width = 1920;    /* Width of the image */
        int height = 1080;   /* Height of the image */
        File BarChart = new File( fileName);
        try {
            ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
