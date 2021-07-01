import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;

public class Main {
    public static void main (String[] args)
    {
        // Store connection string in user environment variables
        String connectionString = System.getenv("mongodb.uri");

        // Login
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("Hugh-Mungus");
        MongoCollection<Document> recipesCol = database.getCollection("Awesome Collection");

        // Recipe Test Case
        Recipe recipe = new Recipe("Burger", Arrays.asList(new Ingredient("Beef", 1.5, "lb"),
                new Ingredient("Buns", 1, "pack"),
                new Ingredient("Cheese", 1, "slice")));
        System.out.println(recipe);

        // Insert Recipe
        //recipesCol.insertOne(recipe.documentify());

        // Delete Recipe
        /*
        Bson filter = eq("recipe", "Burger");
        DeleteResult result = recipesCol.deleteOne(filter);
        System.out.println(result);
        */


        /*
         David's Database work here...
        System.out.println("name" + mongoClient.listDatabaseNames().first());
        MongoDatabase db = mongoClient.getDatabase("Hugh-Mungus");
        db.createCollection("Awesome Collection");
        MongoCollection mc = db.getCollection("Awesome Collection");
        Document toy = new Document("name", "david");
        mc.insertOne();
        */
    }
}