import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {
    public static void main (String[] args)
    {
        // connection string stored in User environment variables
        String connectionString = System.getenv("mongodb.uri");

        // Login
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("Hugh-Mungus");
        MongoCollection<Document> recipesCol = database.getCollection("Awesome Collection");



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