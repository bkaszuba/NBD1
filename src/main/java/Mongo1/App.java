package Mongo1;

import com.mongodb.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MongoClient mongo = new MongoClient();
        DB db = mongo.getDB("test");
        DBCollection dbCollection = db.getCollection("restaurants");

        //Cuisines names
        Object[] cuisines = dbCollection.distinct("cuisine").toArray();
        //Cuisines counter
        int[] counter = new int [cuisines.length];

        for(int i=0; i<cuisines.length; i++){
            DBObject query = new BasicDBObject("cuisine", cuisines[i]);
            counter[i] = dbCollection.find(query).count();
        }

        BarChart barChart = new BarChart(cuisines,counter);
        barChart.createBarChart("Cuisine counter",
                "Restaurants number", "Cuisine name");


























        /*DBObject restau1 = new BasicDBObject("_id", "1234")
                .append("borough", "Warszawa")
                .append("cuisine", "Polska")
                .append("name", "Testowa")
                .append("restaurant_id", "66666666");

        //dbCollection.insert(restau1);
        System.out.println(dbCollection.getCount());

        DBObject query = new BasicDBObject("_id", "1234");
        DBCursor dbCursor = dbCollection.find(query);
        DBObject test = dbCursor.one();

        System.out.println(test);
        System.out.println(test.get("name"));*/
    }
}
