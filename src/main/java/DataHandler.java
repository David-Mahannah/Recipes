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

    public List<Document> findRecipeByName(String search_string)
    {
        Bson filter = eq("recipe", search_string);
        List<Document> list = collection.find().into(new ArrayList<>());
        return list;
    }

    public List<Document> containsIngredient(String search_string)
    {
        Bson filter = ne("", "");
        return null;
    }

    public List<Document> doesNotContainIngredient(String search_string)
    {
        return null;
    }

    public List<Document> fromRegion(String region)
    {
        Bson filter = eq("region", region);
        List<Document> list = collection.find().into(new ArrayList<>());
        return list;
    }

    // Returns List<Document> of all recipes that contain flavor
    public List<Document> isFlavor(String flavor)
    {
        Bson filter = eq("flavor", flavor);
        List<Document> list = collection.find().into(new ArrayList<>());
        return list;
    }

    // Returns List<Documents> of all recipes that contain all flavors
    public List<Document> hasFlavors(String... flavors)
    {
        Bson filter = eq("flavor", flavors);
        List<Document> list = collection.find().into(new ArrayList<>());
        return list;
    }


}
