package dayfourteen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChemicalFactory {
    private List<Reaction> reactionList;
    private Map<String, Long> chemicals;
    private Map<String, Long>  leftovers;
    private Reaction fuelReaction;
    private long oreForOneFuel;
    private long fuelProduced;

    ChemicalFactory(List<Reaction> reactionList, Map<String, Long> chemicals) {
        this.reactionList = reactionList;
        this.chemicals = chemicals;
        this.leftovers = new HashMap<>();
        this.leftovers.putAll(chemicals);
        this.fuelReaction = getReactionWithProvidedProductName("FUEL");
        this.oreForOneFuel = 0;
        this.fuelProduced = 0;
    }

    /*public void printChemicalsMap(){
        chemicals.forEach((k, v) -> System.out.println("{" + k + ", " + v + "}"));
    }

    public void printLeftoversMap(){
        leftovers.forEach((k, v) -> System.out.println("{" + k + ", " + v + "}"));
    }*/

    long getOreForOneFuel() {
        return oreForOneFuel;
    }

    long getFuelProduced() {
        return fuelProduced;
    }

    private Reaction getReactionWithProvidedProductName(String productName){
        for (Reaction reaction:reactionList){
            if (reaction.getProduct().getName().equals(productName)){
                return reaction;
            }
        }
        return null;
    }

    void produceFuelForTrilionOre(){
        //todo Its very slow, make algorithm to count how much fuel can i make with trilion ore
        do {
            produceOneFuel();
        } while (chemicals.get("ORE") < 1000000000000L);
    }

    void produceOneFuel(){
        for (Reagent r:fuelReaction.getReagents()){
            chemicals.replace(r.getName(), chemicals.get(r.getName()) + r.getQuantity());
            getReagentsForProduct(r, 1);
        }
        fuelProduced++;
        if (fuelProduced == 1){
            oreForOneFuel = chemicals.get("ORE");
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
                leftovers.replace(reagent.getName(), 0L);
            }
            int numberOfReactions = (int) Math.ceil((float)quantityReallyNeed
                    / (float)reaction.getProduct().getQuantity());
            if (numberOfReactions!=0) {
                numberOfLeftovers = (numberOfReactions * reaction.getProduct().getQuantity())
                        - quantityReallyNeed;
            } else {
                numberOfLeftovers = 0;
            }
            leftovers.replace(reagent.getName(), leftovers.get(reagent.getName()) + numberOfLeftovers);
            for (Reagent reag : reaction.getReagents()) {
                chemicals.replace(reag.getName(), chemicals.get(reag.getName()) + reag.getQuantity() * numberOfReactions);
                getReagentsForProduct(reag, numberOfReactions);
            }
        }
    }
}
