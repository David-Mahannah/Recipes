package recipe.classifiers;

import java.util.ArrayList;
import java.util.Arrays;

public class Flavors
{
    public static class FlavorReadException extends Exception
    {
        public FlavorReadException (String errorMessage)
        {
            super(errorMessage);
        }
    }

    static String SPICY = "spicy";
    static String SWEET = "sweet";
    static String SOUR = "sour";


    /**
     * Returns a string representation of a flavor for database storage
     *
     * Verify that name is a known flavor and return a standardized string representation.
     *
     * @param name The string name of a given recipe
     * @return      standardized string representation of name
     */
    public static String processFlavor (String name) throws FlavorReadException {
        switch (name.toLowerCase())
        {
            case "spicy": return SPICY;
            case "sweet": return SWEET;
            case "sour": return SOUR;
            default: throw new FlavorReadException("Unrecognized flavor: " + name);
        }
    }

    /**
     * Returns an ArrayList of standardized String flavors.
     *
     * Verify and standardize a list of flavors strings for database use
     *
     * @param list The string name of a given recipe
     * @return      standardized string representation of name
     */
    public static ArrayList<String> processList (String list) throws FlavorReadException
    {
        ArrayList<String> items = new ArrayList<String>(Arrays.asList(list.split("\\s*,\\s*")));
        ArrayList<String> out = new ArrayList<String>();
        for (String item : items)
        {
            out.add(processFlavor(item));
        }
        return out;
    }
}
