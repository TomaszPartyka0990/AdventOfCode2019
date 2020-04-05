package dayfourteen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src\\main\\resources\\DayFourteenInput.txt");
        List<String> lines = Files.readAllLines(path);
        List<Reaction> reactions = new ArrayList<>();
        Map<String, Long> chemicals = new HashMap<>();
        for (String line:lines){
            String input = line.substring(0, line.indexOf("=>")-1);
            String[] inputReagents = input.split(", ");
            List<Reagent> reagents = new ArrayList<>();
            for (String inputReagent:inputReagents){
                String[] inputReagentDetail = inputReagent.split(" ");
                reagents.add(new Reagent(Integer.parseInt(inputReagentDetail[0]), inputReagentDetail[1]));
                chemicals.put(inputReagentDetail[1], 0L);
            }
            String result = line.substring(line.indexOf("=>")+3);
            String[] resultDetail = result.split(" ");
            Reagent product = new Reagent(Integer.parseInt(resultDetail[0]), resultDetail[1]);
            if (!resultDetail[1].equals("FUEL")){
                chemicals.put(resultDetail[1], 0L);
            }
            Reaction reaction = new Reaction(reagents, product);
            reactions.add(reaction);
        }
        ChemicalFactory chemicalFactory = new ChemicalFactory(reactions, chemicals);
        chemicalFactory.produceOneFuel();
        System.out.println("ORE for 1 FUEL: " + chemicalFactory.getOreForOneFuel());
        chemicalFactory.produceFuelForTrilionOre();
        System.out.println("Fuel  produced: " + (chemicalFactory.getFuelProduced()-1));
    }
}
