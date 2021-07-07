import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;

public class DataHandler
{
    MongoCollection<Document> collection;

    public DataHandler (MongoCollection<Document> c)
    {
        collection = c;
    }

    public Document randomGenByFilter(Bson filter)
    {
        Random rand = new Random();
        List<Document> list = collection.find(filter).into(new ArrayList<>());
        System.out.println(list);
        return list.get(rand.nextInt(list.size()));
    }

    public Document randomGen()
    {
        Random rand = new Random();
        List<Document> list = collection.find().into(new ArrayList<>());
        return list.get(rand.nextInt(list.size()));
    }

    public List<Document> findRecipeByName(String s)
    {
        Bson filter = eq("recipe", s);
        List<Document> list = collection.find().into(new ArrayList<>());
        return list;
    }

    public Document containsIngredient()
    {
        Bson filter = ne("")
    }

    public Document doesNotContainIngredient()
    {

    }
}
