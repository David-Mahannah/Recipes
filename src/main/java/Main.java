import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
//import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException {
        // TODO
        // Flavors array in RecipeBuilder
        // Add Cooking directions to Recipes

        // Store connection string in user environment variables
        String connectionString = System.getenv("mongodb.uri");

        // Login
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("Hugh-Mungus");
        MongoCollection<Document> recipesCol = database.getCollection("Awesome Collection");


        // DataHandler Test Case
        Document myDoc = recipesCol.find().first();
        //System.out.println(myDoc.toJson());

        DataHandler datahandler = new DataHandler(recipesCol);

        List<Document> hasbeef = datahandler.containsIngredient("Beef");

        // Testing RecipeBuilder class
        RecipeBuilder r = new RecipeBuilder(datahandler);

       // List<Document> hasOnions = datahandler.containsIngredient("Onion");
        // System.out.println(datahandler.doesNotContainIngredient("Onion"));
       // System.out.println(datahandler.findRecipeByName("French Onion Soup"));
        // System.out.println(hasOnions);
//        datahandler.findRecipe();

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