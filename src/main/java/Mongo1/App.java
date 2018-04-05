package Mongo1;

import Mongo1.Charts.BarChart;
import Mongo1.Model.Database;

public class App {
    private static final String DATABASE_NAME = "test";
    private static final String DAtABASE_COLLECTION_NAME = "restaurants";
    private static final String FIELD_CUISINE_NAME = "cuisine";
    private static final String FIELD_BOROUGH_NAME = "borough";
    private static final String FIELD_GRADE_SCORE_NAME = "grades.score";

    public static void main(String[] args) {
        Database database = new Database(DATABASE_NAME, DAtABASE_COLLECTION_NAME);

        BarChart barChart = new BarChart(database.getPossibleFieldNames(FIELD_CUISINE_NAME),
                database.getFieldsCount(FIELD_CUISINE_NAME,
                        database.getPossibleFieldNames(FIELD_CUISINE_NAME)));
        barChart.createBarChart("Cuisine counter",
                "Restaurants number", "Cuisine name",
                "CuisinesCounter.jpeg");

        barChart = new BarChart(database.getPossibleFieldNames(FIELD_BOROUGH_NAME),
                database.getFieldsCount(FIELD_BOROUGH_NAME,
                        database.getPossibleFieldNames(FIELD_BOROUGH_NAME)));
        barChart.createBarChart("Borough ocunter",
                "Restaurants number", "Borough name",
                "BoroughCounter.jpeg");

        database.getAllRestarantsNumber();
        database.getFieldCounterGreaterThanValue(FIELD_GRADE_SCORE_NAME, 130);
        database.getFieldCounterLessThanValue(FIELD_GRADE_SCORE_NAME, 5);
        database.getHighestValueFromNumericField(FIELD_GRADE_SCORE_NAME);
        database.getLowestValueFromNumericField(FIELD_GRADE_SCORE_NAME);
    }
}
