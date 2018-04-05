package Mongo1;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;

/**
 * Created by Kaszuba on 12.03.2018.
 */
public class BarChart {
    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    public BarChart(Object[] names, int[] counter){
        for(int i=0;i <names.length;i++){
            dataset.addValue(counter[i], (Comparable) names[i],"");
        }
    }

    public void createBarChart(String title, String yName, String xName){
        JFreeChart barChart = ChartFactory.createBarChart(
                title,
                xName, yName,
                dataset, PlotOrientation.VERTICAL,
                true, true, false);

        int width = 1920;    /* Width of the image */
        int height = 1080;   /* Height of the image */
        File BarChart = new File( "BarChart.jpeg");
        try {
            ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
