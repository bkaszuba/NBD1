package Mongo1;

import Mongo1.Charts.BarChart;
import Mongo1.Model.Database;

public class App {
    private static final String DATABASE_NAME = "test";
    private static final String DAtABASE_COLLECTION_NAME = "restaurants";
    private static final String FIELD_CUISINE_NAME = "cuisine";
    private static final String FIELD_BOROUGH_NAME = "borough";
    private static final String FIELD_GRADES_SCORE_NAME = "grades.score";
    private static final String FIELD_GRADES_GRADE_NAME = "grades.grade";

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
        barChart.createBarChart("Borough counter",
                "Restaurants number", "Borough name",
                "BoroughCounter.jpeg");

        barChart = new BarChart(database.getPossibleFieldNames(FIELD_GRADES_GRADE_NAME),
                database.getFieldsCount(FIELD_GRADES_GRADE_NAME,
                        database.getPossibleFieldNames(FIELD_GRADES_GRADE_NAME)));
        barChart.createBarChart("Grades counter",
                "Restaurants number", "Grade",
                "GradesCounter.jpeg");

        barChart = new BarChart(database.getPossibleFieldNames(FIELD_BOROUGH_NAME),
                database.getFieldsCountWhereFieldEquals(FIELD_GRADES_GRADE_NAME,
                        database.getPossibleFieldNames(FIELD_GRADES_GRADE_NAME), FIELD_GRADES_GRADE_NAME, "A"));
        barChart.createBarChart("Grades per borough",
                "Restaurants number", "Grade",
                "GradesPerBorough.jpeg");

        barChart = new BarChart(database.getPossibleFieldNames(FIELD_GRADES_GRADE_NAME),
                database.getFieldsCountWhereFieldEquals(FIELD_BOROUGH_NAME,
                        database.getPossibleFieldNames(FIELD_BOROUGH_NAME), FIELD_BOROUGH_NAME, "Queens"));
        barChart.createBarChart("Grades in Manhattan",
                "Restaurants ", "Grade",
                "GradesInManhattan.jpeg");

        barChart = new BarChart(database.getPossibleFieldNames(FIELD_BOROUGH_NAME),
                database.getFieldsCountWhereFieldEquals(FIELD_BOROUGH_NAME,
                        database.getPossibleFieldNames(FIELD_BOROUGH_NAME), FIELD_CUISINE_NAME, "German"));
        barChart.createBarChart("German cuisines per borough",
                "Restaurants ", "Borough",
                "GermanCuisinePerBorough.jpeg");

        barChart = new BarChart(database.getPossibleFieldNames(FIELD_CUISINE_NAME),
                database.getFieldsCountWhereFieldEquals(FIELD_CUISINE_NAME,
                        database.getPossibleFieldNames(FIELD_CUISINE_NAME), FIELD_BOROUGH_NAME, "Staten Island"));
        barChart.createBarChart("Restaurants in Staten Island",
                "Restaurants ", "Cuisine",
                "RestaurantsInStatenIsland.jpeg");

        database.getAllRestarantsNumber();
        database.getFieldCounterGreaterThanValue(FIELD_GRADES_SCORE_NAME, 60);
        database.getFieldCounterLessThanValue(FIELD_GRADES_SCORE_NAME, 30);
        database.getHighestValueFromNumericField(FIELD_GRADES_SCORE_NAME);
        database.getLowestValueFromNumericField(FIELD_GRADES_SCORE_NAME);
    }
}
