import recipe.tools.DataHandler;
import recipe.tools.RecipeBuilder;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main
{
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

    public static void main (String[] args) throws IOException
    {
        RecipeBuilder r = new RecipeBuilder(new DataHandler(true));
    }
}