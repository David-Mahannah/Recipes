public class Ingredient
{
    private String name;
    private double amount;
    private String unit_of_measurement;

    public Ingredient(String n, double a, String u)
    {
        name = n;
        amount = a;
        unit_of_measurement = u;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", unit_of_measurement='" + unit_of_measurement + '\'' +
                '}';
    }
}
