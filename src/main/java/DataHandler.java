import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;

public class DataHandler
{
    MongoCollection<Document> collection;

    public DataHandler (MongoCollection<Document> c)
    {
        collection = c;
    }

    /* ------------ Functions ----------- */


    // interface web form to mongodb query
    // Pass null for parameters that arent being used in the search.
    // Assuming prep time is a range...
    public List<Document> theSuperMegaFormMethod(String name, String flavor, String region, int prepTimeMin, int prepTimeMax, String[] ingredients)
    {
        Bson name_filter = null,
                flavor_filter = null,
                region_filter = null,
                prepTimeMin_filter = null,
                prepTimeMax_filter = null,
                ingredients_filter = null;

        Bson[] filters = new Bson[]{name_filter, flavor_filter, region_filter, prepTimeMin_filter, prepTimeMax_filter};

        // Filters
        if (name != null) { // dunno if this is a redundancy...
            name_filter = eq("recipe", name);
        }

        if (flavor != null) {
            flavor_filter = eq();
        }

        if (region != null)
        {
            region_filter = eq();
        }

//        if (prepTimeMax != null)
//        {
//
//        }
//
//        if (prepTimeMin != null)
//        {
//
//        }

        // Smack them together
        Bson finalFilter = name_filter;
        for (Bson filter : filters)
        {
            if (filter != null)
            {
                finalFilter = and(finalFilter, filter);
            }
        }
//        Bson finalFilter = and(name, flavor, prepTime);
        List<Document> list = collection.find(finalFilter).into(new ArrayList<>());
        return list;
    }

    // Return a single recipe in Bson format that is randomly selected
    // from all recipes that fit the filter
    public Document randomGenByFilter(Bson filter)
    {
        Random rand = new Random();
        List<Document> list = collection.find(filter).into(new ArrayList<>());
        //System.out.println(list);
        return list.get(rand.nextInt(list.size()));
    }

    // Return a randomly selected recipe inn Bson format
    public Document randomGen()
    {
        Random rand = new Random();
        List<Document> list = collection.find().into(new ArrayList<>());
        return list.get(rand.nextInt(list.size()));
    }

    // Return all recipes that have a "recipe" label equal to name
    public List<Document> findRecipeByName(String name)
    {
        Bson filter = eq("recipe", name);
        List<Document> list = collection.find(filter).into(new ArrayList<>());
        return list;
    }

    // Return all recipes that
    public List<Document> containsIngredient(String ingredient)
    {
        Bson filter = all("ingredients", ingredient);
        List<Document> list = collection.find(filter).into(new ArrayList<>());
        return null;
    }

    public List<Document> doesNotContainIngredient(String search_string)
    {
        return null;
    }

    public List<Document> fromRegion(String region)
    {
        Bson filter = eq("region", region);
        List<Document> list = collection.find(filter).into(new ArrayList<>());
        return list;
    }

    // Returns List<Document> of all recipes that contain flavor
    public List<Document> isFlavor(String flavor)
    {
        Bson filter = all("flavor", flavor);
        List<Document> list = collection.find(filter).into(new ArrayList<>());
        return list;
    }

    // Returns List<Documents> of all recipes that contain all flavors
    public List<Document> hasFlavors(String... flavors)
    {
        Bson filter = all("flavor", flavors);
        List<Document> list = collection.find(filter).into(new ArrayList<>());
        return list;
    }

    public List<Document> ratingAbove(int rating)
    {
        Bson filter = // Do this
        return list;
    }

    public List<Document> ratingBelow(int rating)
    {
        Bson filter = // Do this
        return list;
    }

    public List<Document> prepTimeBelow(double minutes)
    {

    }

    public List<Document> prepTimeAbove(double minutes)
    {

    }
}
