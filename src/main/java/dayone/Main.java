package dayone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        long sumOfFuelNeeded = 0;
        Path filePath = Paths.get("src\\main\\resources\\DayOneInput.txt");
        Stream<String> lines = Files.lines(filePath);
        sumOfFuelNeeded += lines.mapToInt(Integer::parseInt).map(Main::calculateFuelFromMass).sum();
        System.out.println(sumOfFuelNeeded);
    }

    private static int calculateFuelFromMass(int mass){
        int massDivided;
        massDivided = (int) Math.floor(mass/3);
        if ((massDivided -2) >= 0){
            return (massDivided - 2) + calculateFuelFromMass(massDivided - 2);
        } else {
            return 0;
        }
    }
}
