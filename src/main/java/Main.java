import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javax.swing.text.Document;

public class Main {
    public static void main (String[] args)
    {
        // Passwords stored in User environment variables
        String username = System.getenv("Mongodbuser");
        String password = System.getenv("Mongodbpass");

        // Login
        MongoClient mongoClient = MongoClients.create(
                "mongodb://" + username + ":" + password +"@cluster0-shard-00-00.zs3y6.mongodb.net:27017,cluster0-shard-00-01.zs3y6.mongodb.net:27017,cluster0-shard-00-02.zs3y6.mongodb.net:27017/myFirstDatabase?ssl=true&replicaSet=atlas-d8sbv8-shard-0&authSource=admin&retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("test");


        // Database work here...
        System.out.println("name" + mongoClient.listDatabaseNames().first());
        MongoDatabase db = mongoClient.getDatabase("Hugh-Mungus");
        //db.createCollection("Awesome Collection");
        MongoCollection mc = db.getCollection("Awesome Collection");
        //Document toy = new Document("name", "david");
        //mc.insertOne();
    }
}