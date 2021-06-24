import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.Scanner;

public class Recipe
{
    private String name;
    private ArrayList<Ingredient> Ingredients;

    // Convert Recipe object to Document type to store in database
    public Document documentify()
    {
        return null;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", Ingredients=" + Ingredients +
                '}';
    }

    // Debugging
    public static void main (String[] args)
    {

    }

    // Debugging tool to create a recipe
    public static Recipe recipePrompt()
    {
        System.out.print("Title: ");
        Scanner keyboard = new Scanner(System.in);
        keyboard.nextLine();

        System.out.print("Title: ");
        keyboard.nextLine();

        String name;
        double amount;
        String unit;

        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

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
        return null;//Recipe(); // make a Recipe constructor
    }
}
