package dayten;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src\\main\\resources\\DayTenInput.txt");
        List<String> lines = Files.newBufferedReader(path).lines().collect(Collectors.toList());
        lines.forEach(System.out::println);
        System.out.println();
        SpaceMap spaceMap = new SpaceMap(lines);
        spaceMap.getAsteroidLocations();
        spaceMap.fillCoordinatesListsForEachAsteroid();
        spaceMap.fillAsteroidCoordinatesListsForEachAsteroid();
        Asteroid bestAsteroidForStation = spaceMap.getAsteroidWithMaxOthersDetected();
        System.out.println("Best location: " + bestAsteroidForStation);
        System.out.println("Highest number detected: " + bestAsteroidForStation.getAngles().size());
        Coordinate asteroidCoordinatesVaporizedAs200Th =
                                        bestAsteroidForStation
                                        .vaporizeOtherAsteroidsAndReturnCoordinatesOf200thVaporized();
        System.out.println("As 200th was vaporized asteroid at: " + asteroidCoordinatesVaporizedAs200Th);
        int result = (asteroidCoordinatesVaporizedAs200Th.getX()*100)
                        + asteroidCoordinatesVaporizedAs200Th.getY();
        System.out.println("Result: " + result);
    }
}
