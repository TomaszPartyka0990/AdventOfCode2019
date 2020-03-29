package daytwelve;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src\\main\\resources\\DayTwelveInput.txt");
        List<String> lines =  Files.readAllLines(path);
        List<Moon> moons = new ArrayList<>();
        for (String line:lines) {
            String[] coordinates = line.substring(1, line.length() - 1).split(", ");
            int x = Integer.parseInt(coordinates[0].substring(2));
            int y = Integer.parseInt(coordinates[1].substring(2));
            int z = Integer.parseInt(coordinates[2].substring(2));
            moons.add(new Moon(x, y, z));
        }
        moons.get(0).setName("Io");
        moons.get(1).setName("Europa");
        moons.get(2).setName("Ganymede");
        moons.get(3).setName("Callisto");
        MoonsService moonsService = new MoonsService(moons);
        System.out.println("At start:");
        moonsService.printMoons();
        for (int i=0; i<1000; i++) {
            moonsService.applyGravity();
            moonsService.applyVelocity();
        }
        System.out.println("After 1000 step:");
        moonsService.printMoons();
        System.out.println("ENERGY: " + moonsService.calculateEnergy());
    }
}
