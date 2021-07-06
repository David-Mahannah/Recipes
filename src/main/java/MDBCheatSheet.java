import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MDBCheatSheet
{
    public static void main (String[] args)
    {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        // Passwords stored in User environment variables
        String username = System.getenv("Mongodbuser");
        String password = System.getenv("Mongodbpass");

        // Login
        MongoClient mongoClient = MongoClients.create(
                "mongodb://" + username + ":" + password +"@cluster0-shard-00-00.zs3y6.mongodb.net:27017,cluster0-shard-00-01.zs3y6.mongodb.net:27017,cluster0-shard-00-02.zs3y6.mongodb.net:27017/myFirstDatabase?ssl=true&replicaSet=atlas-d8sbv8-shard-0&authSource=admin&retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("Hugh-Mungus");
        //db.createCollection("Awesome Collection");

        // MongoCollection instance configured with the Pojos type
        database = database.withCodecRegistry(pojoCodecRegistry); // Setting pojoCodecRegistry

        //MongoCollection mc = db.getCollection("Awesome Collection");

        MongoCollection<Recipe> collection = database.getCollection("recipes", Recipe.class);

        Recipe recipe_1 = new Recipe();
        collection.insertOne(recipe_1);

        List<Recipe> recipes = asList(
                new Recipe(),
                new Recipe(),
                new Recipe()
        );
        collection.insertMany(recipes);
    }
}