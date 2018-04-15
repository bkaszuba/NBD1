package Mongo1.Model;

import com.mongodb.*;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class Database {
    private static final String DATABASE_URL = "localhost";
    private static final int DATABASE_PORT = 27017;
    private MongoCollection<Document> collection;

    public Database(String databaseName, String collectionName) {
        MongoClient mongo = new MongoClient(DATABASE_URL, DATABASE_PORT);
        this.collection = mongo.getDatabase(databaseName).getCollection(collectionName);
    }

    public DistinctIterable getPossibleFieldNames(String field) {
        return collection.distinct(field, String.class);
    }

    public List<Long> getFieldsCount(String fieldName, DistinctIterable searchingFieldNames) {
        List<Long> fieldsCounter = new ArrayList<>();
        MongoCursor<String> cursor = searchingFieldNames.iterator();
        while(cursor.hasNext()) {
            fieldsCounter.add(collection.count(and(eq(fieldName, cursor.next()))));
        }
        return fieldsCounter;
    }

    public List<Long> getFieldsCountWhereFieldEquals(String fieldName, DistinctIterable searchingFieldNames, String fieldToEqual, String valueToEqual) {
        List<Long> fieldsCounter = new ArrayList<>();
        MongoCursor<String> cursor = searchingFieldNames.iterator();
        while(cursor.hasNext()) {
            fieldsCounter.add(collection.count(and(eq(fieldName, cursor.next()),eq(fieldToEqual, valueToEqual))));
        }
        return fieldsCounter;
    }

    public void getFieldCounterGreaterThanValue(String field, int value) {
        long result = collection.count(and(gte(field, value)));
        System.out.println("Restaurants with " + field
                + " higher than: " + value + " = " + result + "\n");
    }

    public void getFieldCounterLessThanValue(String field, int value) {
        long result = collection.count(and(gte(field, value)));
        System.out.println("Restaurants with " + field
                + " lower than: " + value + " = " + result + "\n");
    }

    public void getHighestValueFromNumericField(String fieldName) {
        List<Document> result = collection.find().sort(ascending("number")).into(new ArrayList<Document>());
        System.out.println("Highest: " + fieldName +"\n" + "\tName: "+ result.get(0).get("name") +
                " Cuisine: " + result.get(0).get("cuisine") +
                " Borough: " + result.get(0).get("borough") +
                " Score:");
        System.out.println("\t" + result.get(0).get("grades") + "\n");
    }

    public void getLowestValueFromNumericField(String fieldName) {
        List<Document> result = collection.find().sort(descending(fieldName)).into(new ArrayList<Document>());
        System.out.println("Lowest: " + fieldName +"\n" + "\tName: "+ result.get(0).get("name") +
                " Cuisine: " + result.get(0).get("cuisine") +
                " Borough: " + result.get(0).get("borough") +
                " Score:");
        System.out.println("\t" + result.get(0).get("grades") + "\n");
    }

    public void getAllRestarantsNumber() {
        long result = collection.count();
        System.out.println("All restaurants number: " +result + "\n");
    }
}
