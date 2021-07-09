import java.util.Objects;

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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getUnit_of_measurement() {
        return unit_of_measurement;
    }

    public void setUnit_of_measurement(String unit_of_measurement) {
        this.unit_of_measurement = unit_of_measurement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Double.compare(that.amount, amount) == 0 && name.equals(that.name) && Objects.equals(unit_of_measurement, that.unit_of_measurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, unit_of_measurement);
    }

    @Override
    public String toString() {
        return "Ingredient{ " +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", unit_of_measurement='" + unit_of_measurement + '\'' + " " +
                '}';
    }
}
