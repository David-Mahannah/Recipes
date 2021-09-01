package recipe.util;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Recipe
{
    private String name;
    private Double rating;
    private Integer servingSize;
    private Double preparationTime;
    private List<String> dietaryRestrictions;
    private String region;
    private List<String> flavors;
    private List<Ingredient> ingredients;
    private String directions;

    /*-------------------Initializers-----------------*/
    public Recipe(){
        name = null;
        rating = -1.0;
        servingSize = -1;
        preparationTime = -1.0;
        dietaryRestrictions = new ArrayList<>();
        region = null;
        flavors = new ArrayList<>();
        ingredients = new ArrayList<>();
        directions = null;
    }

    public Recipe(String name, List<Ingredient> ingredients){
        this.name = name;
        this.ingredients = ingredients;

        rating = -1.0;
        servingSize = -1;
        preparationTime = -1.0;
        dietaryRestrictions = new ArrayList<>();
        region = null;
        flavors = new ArrayList<>();
        directions = null;
    }

    public Recipe(String name, List<Ingredient> ingredients, String directions){
        this.name = name;
        this.ingredients = ingredients;
        this.directions = directions;

        rating = -1.0;
        servingSize = -1;
        preparationTime = -1.0;
        dietaryRestrictions = new ArrayList<>();
        region = null;
        flavors = new ArrayList<>();
    }

    public Recipe(String name, String region, double rating, int servingSize, double preparationTime, List<String> dietaryRestrictions, List<String> flavors, List<Ingredient> ingredients, String directions){
        this.name = name;
        this.rating = rating;
        this.servingSize = servingSize;
        this.preparationTime = preparationTime;
        this.dietaryRestrictions = dietaryRestrictions;
        this.region = region;
        this.flavors = flavors;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    /*-------------------Constructor Functions-----------------*/

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setServingSize(Integer servingSize) {
        this.servingSize = servingSize;
    }

    public void setPreparationTime(Double preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setDietaryRestrictions(List<String> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setFlavors(List<String> flavors) {
        this.flavors = flavors;
    }

    /*-------------------Functions-----------------*/

    // Convert RecipeTools.util.Recipe object to Document type to store in database
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

        // Create Document List of dietary restriction tags
        List<org.bson.Document> dietRestrict = new ArrayList<>();
        System.out.println(dietaryRestrictions);
        if (!dietaryRestrictions.isEmpty()){
            for( String diet : this.dietaryRestrictions){
                dietRestrict.add(new org.bson.Document("dietary_restriction", diet));
            }
        }

        // Return documentified recipe
        return new org.bson.Document("_id", new ObjectId()).append("recipe", this.name)
                .append("rating", this.rating)
                .append("serving_size", this.servingSize)
                .append("preparation_time", this.preparationTime)
                .append("dietary_restrictions",dietRestrict)
                .append("region", this.region)
                .append("flavor", flavorList)
                .append("ingredients", ingredientList)
                .append("cooking_directions", this.directions);
    }

    @Override
    public String toString() {
        return "RecipeTools.util.Recipe{" + "\n" +
                "\t" + "name= '" + name + '\'' + " ," + "\n" +
                "\t" + "rating= '" + rating + '\'' + " ," + "\n" +
                "\t" + "serving size= '" + servingSize + '\'' + " ," + "\n" +
                "\t" + "preparation time= '" + preparationTime + '\'' + " ," + "\n" +
                "\t" + "dietary restrictions= " + dietaryRestrictions + " ," + "\n" +
                "\t" + "region= '" + region + '\'' + " ," + "\n" +
                "\t" + "flavors= " + flavors + " ," + "\n" +
                "\t" + "ingredients= " + ingredients + " ," + "\n" +
                "\t" + "cooking directions= '" + directions + '\'' + "\n" +
                '}';
    }

    /*-------------------Debug Tools-----------------*/
    public static void main (String[] args)
    {
        // Test recipePrompt()
        /*RecipeTools.util.Recipe recipe = recipePrompt();
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
        System.out.print("RecipeTools.util.Recipe Name: ");
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
            System.out.print("RecipeTools.util.Ingredient name (or 'x' to quit): ");
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
        return new Recipe(recipeName, ingredients);//RecipeTools.util.Recipe(); // make a RecipeTools.util.Recipe constructor
    }
}
