package recipe.classifiers;

import java.util.ArrayList;
import java.util.Arrays;

public class DietRestriction
{
    public static class DietReadException extends Exception
    {
        public DietReadException(String errorMessage)
        {
            super(errorMessage);
        }
    }

    static String VEGAN = "vegan";
    static String PESCATARIAN = "pescatarian";
    static String DAIRY_FREE = "dairy_free";
    static String GLUTEN_FREE = "gluten_free";

    public static String processDietaryRestriction (String name) throws DietReadException {
        switch (name.toLowerCase())
        {
            case "vegan": return VEGAN;
            case "pescatarian": return PESCATARIAN;
            case "dairy_free": return DAIRY_FREE;
            case "gluten_free": return GLUTEN_FREE;
            default: throw new DietReadException("Unrecognized dietary restriction: " + name);
        }
    }

    public static ArrayList<String> processList (String list) throws DietReadException {
        ArrayList<String> items = new ArrayList<String>(Arrays.asList(list.split("\\s*,\\s*")));
        ArrayList<String> out = new ArrayList<String>();
        for (String item : items)
        {
            out.add(processDietaryRestriction(item));
        }
        return out;
    }
}
