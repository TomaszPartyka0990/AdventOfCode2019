package dayfourteen;

import java.util.List;

public class Reaction {
    private List<Reagent> reagents;
    private Reagent product;

    Reaction(List<Reagent> reagents, Reagent product) {
        this.reagents = reagents;
        this.product = product;
    }

    Reagent getProduct() {
        return product;
    }

    List<Reagent> getReagents() {
        return reagents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reaction reaction = (Reaction) o;

        return product.equals(reaction.product);
    }

    @Override
    public int hashCode() {
        return product.hashCode();
    }
}
