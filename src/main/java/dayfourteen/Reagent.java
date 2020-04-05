package dayfourteen;

public class Reagent {
    private int quantity;
    private String name;

    public Reagent(int quantity, String name) {
        this.quantity = quantity;
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reagent reagent = (Reagent) o;

        return name.equals(reagent.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Reagent{" + quantity + " "  + name + "}";
    }
}
