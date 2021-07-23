import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;

public class DataHandler
{
    private MongoCollection<Document> collection;

    public DataHandler (MongoCollection<Document> c)
    {
        collection = c;
    }

    /* ------------ Functions ----------- */

    /**
     * <b> UNTESTED </b>
     * <br>
     * Returns a list of recipes from the collection that match all non-null parameters
     *
     * Used to interface web form inputs to mongodb query in a single method
     *
     * @param name The string name of a given recipe
     * @param flavor The string name of a given flavor
     * @param region The string name of a given region
     * @param ingredients An array of ingredient names
     * @return      A list of recipes as BSON documents
     */
    public List<Document> findRecipe(String name, String flavor, String region,  String[] ingredients)
    {
        Bson name_filter = null,
                flavor_filter = null,
                region_filter = null,
                ingredients_filter = null;

        Bson[] filters = new Bson[]{name_filter, flavor_filter, region_filter};

        // Filters
        if (name != null) { // dunno if this is a redundancy...
            name_filter = eq("recipe", name);
        }

        if (flavor != null) {
            flavor_filter = elemMatch("flavor", eq(flavor));
        }

        if (region != null)
        {
            region_filter = eq("region", region);
        }

        // The painful one
        if (ingredients != null)
        {
            Bson[] ingredients_filter_arr = new Bson[ingredients.length];
            for (int i = 0; i < ingredients.length; i++)
            {
                ingredients_filter_arr[i] = elemMatch("ingredients", eq("name", ingredients[i]));
            }
            ingredients_filter = and(ingredients_filter_arr); // Combine all ingredient filters into one
        }

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
        return collection.find(finalFilter).into(new ArrayList<>());
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

    /**
     * <b> UNTESTED </b>
     *<br>
     * Returns a list of recipes from the collection that match the name passed.
     *
     * @param name The string name of a given recipe
     * @return      A list of recipes as BSON documents
     */
    public List<Document> findRecipeByName(String name)
    {
        Bson filter = eq("recipe", name);
        return collection.find(filter).into(new ArrayList<>());

    }


    /**
     * <b> UNTESTED </b>
     *<br>
     * Returns a list of recipes from the collection that contain the passed ingredient.
     *
     * @param ingredient The string name of a given ingredient
     * @return      A list of recipes as BSON documents
     */
    public List<Document> containsIngredient(String ingredient)
    {
        Bson filter = elemMatch("ingredients", eq("name", ingredient));
        return collection.find(filter).into(new ArrayList<>());
    }

    /**
     * <b> UNTESTED </b>
     *<br>
     * Returns a list of recipes from the collection that do not contain the passed ingredient.
     *
     * @param ingredient The string name of a given ingredient
     * @return      A list of recipes as BSON documents
     */
    public List<Document> doesNotContainIngredient(String ingredient)
    {
        Bson filter = elemMatch("ingredients", ne("name", ingredient));
        return collection.find(filter).into(new ArrayList<>());
    }


    /**
     * <b> UNTESTED </b>
     *<br>
     * Returns a list of recipes from the collection that are from the specified region.
     *
     * @param region The string name of a given region
     * @return      A list of recipes in BSON document
     */
    public List<Document> fromRegion(String region)
    {
        Bson filter = eq("region", region);
        return collection.find(filter).into(new ArrayList<>());
    }

    /**
     * <b> UNTESTED </b>
     * <br>
     * Returns a list of recipes from the collection that do not contain the passed flavor.
     *
     * @param flavor The string name of a given flavor
     * @return      A list of recipes as BSON documents
     */
    public List<Document> isFlavor(String flavor)
    {
        Bson filter = elemMatch("flavor", eq(flavor));
        return collection.find(filter).into(new ArrayList<>());
    }

    /**
     * <b> UNTESTED </b>
     * <br>
     * Returns a list of recipes from the collection that contain all of the passed flavors.
     *
     * @param flavors The string names of a given flavor
     * @return      A list of recipes as BSON documents
     */
    public List<Document> hasFlavors(String... flavors)
    {
        Bson filter = all("flavor", flavors);
        return collection.find(filter).into(new ArrayList<>());
    }

    // TODO create search methods for prep time and rating based on their implementation in Recipe class
}
