package daythree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src\\main\\resources\\DayThreeInput.txt");
        Stream<String> lines = Files.lines(path);
        List<String> linesInList = lines.collect(Collectors.toList());
        String stringOne = linesInList.get(0);
        String stringTwo = linesInList.get(1);
        String[] wireOnePath = stringOne.split(",");
        CoordinatesHandler wireOne = new CoordinatesHandler();
        for (int i=0; i<wireOnePath.length; i++){
            wireOne.move(wireOnePath[i]);
        }
        String[] wireTwoPath = stringTwo.split(",");
        CoordinatesHandler wireTwo = new CoordinatesHandler();
        for (int i=0; i<wireTwoPath.length; i++){
            wireTwo.move(wireTwoPath[i]);
        }
        CoordinatesComparer comparer = new CoordinatesComparer(wireOne, wireTwo);
        System.out.println(comparer.checkForLowestDistance());
        System.out.println(comparer.checkForLowestStepsTaken());
    }
}
