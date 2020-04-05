package dayfourteen;

import java.util.List;

public class Reaction {
    private List<Reagent> reagents;
    private Reagent product;

    public Reaction(List<Reagent> reagents, Reagent product) {
        this.reagents = reagents;
        this.product = product;
    }

    public Reagent getProduct() {
        return product;
    }

    public List<Reagent> getReagents() {
        return reagents;
    }

    public void printReaction(){
        StringBuilder sb = new StringBuilder();
        for (Reagent reagent:reagents){
            sb.append(reagent);
            sb.append(",");
        }
        sb.append(" => ");
        sb.append(product);
        System.out.println(sb.toString());
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
