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
        MoonsService moonsService = new MoonsService(moons);
        System.out.println("At start:");
        moonsService.printMoons();
        System.out.println();
        for (int i=0; i<1000; i++) {
            moonsService.applyGravity();
            moonsService.applyVelocity();
        }
        System.out.println("After 1000 step:");
        moonsService.printMoons();
        System.out.println("ENERGY: " + moonsService.calculateEnergy());
        moonsService.bringBackInitialState();
        System.out.println();
        long xSteps = 0;
        do{
            moonsService.applyXGravity();
            moonsService.applyXVelocity();
            xSteps++;
        } while (!moonsService.checkIfXMatchesInitialState());
        moonsService.bringBackInitialState();
        long ySteps = 0;
        do{
            moonsService.applyYGravity();
            moonsService.applyYVelocity();
            ySteps++;
        } while (!moonsService.checkIfYMatchesInitialState());
        moonsService.bringBackInitialState();
        long zSteps = 0;
        do{
            moonsService.applyZGravity();
            moonsService.applyZVelocity();
            zSteps++;
        } while (!moonsService.checkIfZMatchesInitialState());
        System.out.println("xSteps: " + xSteps);
        System.out.println("ySteps: " + ySteps);
        System.out.println("zSteps: " + zSteps);
        long steps = nww(nww(xSteps, ySteps),zSteps);
        System.out.println("Steps to go back to initial state: " + steps);
    }

    public static long nwd(long x, long y) {
        while (x != y) {
            if (x > y)
                x -= y;
            else
                y -= x;
        }
        return x;
    }

    public static long nww(long x, long y){
        return (x*y)/nwd(x, y);
    }
}
