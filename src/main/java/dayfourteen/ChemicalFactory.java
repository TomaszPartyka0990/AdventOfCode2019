package dayfourteen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChemicalFactory {
    private List<Reaction> reactionList;
    private Map<String, Long> chemicals;
    private Map<String, Long>  leftovers;
    private Reaction fuelReaction;

    public ChemicalFactory(List<Reaction> reactionList, Map<String, Long> chemicals) {
        this.reactionList = reactionList;
        this.chemicals = chemicals;
        this.leftovers = new HashMap<>();
        this.leftovers.putAll(chemicals);
        this.fuelReaction = getReactionWithProvidedProductName("FUEL");
    }

    public void printChemicalsMap(){
        chemicals.forEach((k, v) -> System.out.println("{" + k + ", " + v + "}"));
    }

    public void printLeftoversMap(){
        leftovers.forEach((k, v) -> System.out.println("{" + k + ", " + v + "}"));
    }

    public void printOreNeededForOneFuel(){
        System.out.println("ORE needed fo 1 FUEL: " + chemicals.get("ORE"));
    }

    private Reaction getReactionWithProvidedProductName(String productName){
        for (Reaction reaction:reactionList){
            if (reaction.getProduct().getName().equals(productName)){
                return reaction;
            }
        }
        return null;
    }

    public void getChemicalsNeededForOneFuel(){
        for (Reagent r:fuelReaction.getReagents()){
            chemicals.replace(r.getName(), chemicals.get(r.getName()) + r.getQuantity());
            getReagentsForProduct(r, 1);
        }
    }

    private void getReagentsForProduct (Reagent reagent, int quantity){
        Reaction reaction = getReactionWithProvidedProductName(reagent.getName());
        if (reaction != null) {
            long quantityAlreadyHave = leftovers.get(reagent.getName());
            long quantityPotentialyNeed = reagent.getQuantity()*quantity;
            long quantityReallyNeed;
            long numberOfLeftovers;
            if (quantityAlreadyHave >= quantityPotentialyNeed){
                quantityReallyNeed = 0;
                leftovers.replace(reagent.getName(), quantityAlreadyHave - quantityPotentialyNeed);
            } else {
                quantityReallyNeed = quantityPotentialyNeed - quantityAlreadyHave;
                leftovers.replace(reagent.getName(), 0l);
            }
            int numberOfReactions = (int) Math.ceil((float)quantityReallyNeed
                    / (float)reaction.getProduct().getQuantity());
            if (numberOfReactions!=0) {
                numberOfLeftovers = (numberOfReactions * reaction.getProduct().getQuantity())
                        - quantityReallyNeed;
            } else {
                numberOfLeftovers = 0;
            }
            //System.out.println("Current product: " + reagent.getName() + ", i need to produce: " + quantityPotentialyNeed
            //        + ", but i already have: " + quantityAlreadyHave + ", so actually i need to produce: " + quantityReallyNeed);
            //System.out.println("One reaction makes: " + reaction.getProduct().getQuantity() + " so i need to make reactions: "
            //        + numberOfReactions + "and i will have left of it: " + numberOfLeftovers);
            leftovers.replace(reagent.getName(), leftovers.get(reagent.getName()) + numberOfLeftovers);
            for (Reagent reag : reaction.getReagents()) {
                chemicals.replace(reag.getName(), chemicals.get(reag.getName()) + reag.getQuantity() * numberOfReactions);
                getReagentsForProduct(reag, numberOfReactions);
            }
        }
    }
}
