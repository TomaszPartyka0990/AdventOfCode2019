package daysix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src\\main\\resources\\DaySixInput.txt");
        List<String> lines = Files.readAllLines(path);
        AllPlanetsHandler allPlanetsHandler = new AllPlanetsHandler();
        for(String line:lines){
            allPlanetsHandler.getOrbitInformationAndActualizeSet(line);
        }
        allPlanetsHandler.setNumberOfOrbitsForAllPlanets();
        System.out.println(allPlanetsHandler.getAllOrbitsNumber());
        System.out.println(allPlanetsHandler.getNumberOfJumpsToSanta());
    }
}
