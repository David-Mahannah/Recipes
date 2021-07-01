import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Recipe
{
    private String name;
    private String region;
    private List<String> flavors;
    private List<Ingredient> ingredients;

    /*-------------------Constructors-----------------*/
    public Recipe(){
        name = null;
        region = null;
        flavors = new ArrayList<>();
        ingredients = new ArrayList<>();
    }

    public Recipe(String name, List<Ingredient> ingredients){
        this.name = name;
        this.ingredients = ingredients;

        region = null;
        flavors = new ArrayList<>();
    }

    public Recipe(String name, String region, List<String> flavors, List<Ingredient> ingredients){
        this.name = name;
        this.ingredients = ingredients;

        this.flavors = flavors;
        this.region = region;
    }

    /*-------------------Functions-----------------*/

    // Convert Recipe object to Document type to store in database
    public Document documentify()
    {
        // Create Document List of ingredients
        List<org.bson.Document> ingredientList = new ArrayList<>();
        if (!ingredients.isEmpty()) {
            for (Ingredient ingredient : this.ingredients) {
                ingredientList.add(new org.bson.Document("name", ingredient.getName())
                        .append("amount", ingredient.getAmount())
                        .append("unit", ingredient.getUnit_of_measurement()));
            }
        }

        // Create Document List of flavor tags
        List<org.bson.Document> flavorList = new ArrayList<>();
        if (!flavors.isEmpty()){
            for( String flavor : this.flavors){
                flavorList.add(new org.bson.Document("flavor",flavor));
            }
        }

        // Return documentified recipe
        return new org.bson.Document("_id", new ObjectId()).append("recipe", this.name)
                .append("flavor", flavorList)
                .append("region", this.region)
                .append("ingredients", ingredientList);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", Ingredients=" + ingredients +
                '}';
    }

    /*-------------------Debug Tools-----------------*/
    public static void main (String[] args)
    {
        // Test recipePrompt()
        /*Recipe recipe = recipePrompt();
        System.out.print(recipe);
        //System.out.print(recipe.documentify());*/

        // Test documentify()
        Recipe test = new Recipe("Burger", Arrays.asList(new Ingredient("Beef", 1.5, "lb"),
                new Ingredient("Buns", 1, "pack"),
                new Ingredient("Cheese", 1, "slice")));
        System.out.println(test);
        System.out.print(test.documentify());
    }

    // Debugging tool to create a recipe
    public static Recipe recipePrompt()
    {
        System.out.print("Recipe Name: ");
        Scanner keyboard = new Scanner(System.in);
        String recipeName = keyboard.nextLine();

        // TODO: Create prompt for region and flavor
        /*
        System.out.print("Flavor: ");
        keyboard.nextLine();
        System.out.print("Region: ");
        keyboard.nextLine();
        */

        String name;
        double amount;
        String unit;

        ArrayList<Ingredient> ingredients = new ArrayList<>();

        while (true)
        {
            System.out.print("Ingredient name (or 'x' to quit): ");
            name = keyboard.nextLine();
            if (name.equals("x"))
            {
                break;
            }
            System.out.print("amount of " + name + ": ");
            amount = Double.parseDouble(keyboard.nextLine());

            System.out.print("unit of measurement: ");
            unit = keyboard.nextLine();

            ingredients.add(new Ingredient(name, amount, unit));
        }
        return new Recipe(recipeName, ingredients);//Recipe(); // make a Recipe constructor
    }
}
