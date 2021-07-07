import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;

public class Main {
    public static Document randomGenByFilter(MongoCollection<Document> collection, Bson filter){
        Random rand = new Random();
        List<Document> list = collection.find(filter).into(new ArrayList<>());
        System.out.println(list);
        return list.get(rand.nextInt(list.size()));
    }

    public static Document randomGen(MongoCollection<Document> collection){
        Random rand = new Random();
        List<Document> list = collection.find().into(new ArrayList<>());
        return list.get(rand.nextInt(list.size()));
    }

    public static void main (String[] args)
    {
        // Store connection string in user environment variables
        String connectionString = System.getenv("mongodb.uri");

        // Login
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("Hugh-Mungus");
        MongoCollection<Document> recipesCol = database.getCollection("Awesome Collection");

        // Recipe Test Case
        Recipe recipe = new Recipe("Pasta", Arrays.asList(new Ingredient("Veggie Patty", 1.5, "lb"),
                new Ingredient("Buns", 1, "pack"),
                new Ingredient("Cheese", 1, "slice")));
        //System.out.println(recipe);

        //Recipe recipe = Recipe.recipePrompt();
        // Insert Recipe
        //recipesCol.insertOne(recipe.documentify());

        // Test random generators
        //System.out.println(randomGenByFilter(recipesCol,eq("recipe","Pasta")));
        //System.out.println(randomGen(recipesCol));
        //System.out.println(randomGen(recipesCol));
        //System.out.println(randomGen(recipesCol));

        // Delete Recipe

       /* Bson filter = eq("recipe", "Cheeseburger");
        DeleteResult result = recipesCol.deleteOne(filter);
        System.out.println(result);*/



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