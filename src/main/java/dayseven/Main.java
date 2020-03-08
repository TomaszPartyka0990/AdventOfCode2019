package dayseven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {
    public static String output;
    public static boolean isFinished;
    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get("C:\\Users\\Tomasz Partyka\\IdeaProjects\\AdventOfCode\\src\\main\\resources\\DaySevenInput.txt");
        String line = Files.lines(path).collect(Collectors.joining());
        String[] dataSplited = line.split(",");
        String tmp = "0";
        String result;
        int maxOutput = 0;
        for (int i=5; i<10; i++){
            for (int j=5; j<10; j++){
                if (j!=i) {
                    for (int z = 5; z < 10; z++) {
                        if (z!=i && z!=j) {
                            for (int k = 5; k < 10; k++) {
                                if (k!=z && k!=j && k!=i) {
                                    for (int l = 5; l < 10; l++) {
                                        if (l!=k && l!=z && l!=j && l!=i){
                                            output = "0";
                                            isFinished = false;
                                            Amplifier amp1 = new Amplifier(dataSplited, i + "");
                                            Amplifier amp2 = new Amplifier(dataSplited, j + "");
                                            Amplifier amp3 = new Amplifier(dataSplited, z + "");
                                            Amplifier amp4 = new Amplifier(dataSplited, k + "");
                                            Amplifier amp5 = new Amplifier(dataSplited, l + "");
                                            do {
                                                amp1.setInput(output);
                                                amp1.intcode();
                                                amp2.setInput(output);
                                                amp2.intcode();
                                                amp3.setInput(output);
                                                amp3.intcode();
                                                amp4.setInput(output);
                                                amp4.intcode();
                                                amp5.setInput(output);
                                                amp5.intcode();
                                            } while (!isFinished);
                                            if (Integer.parseInt(output) > maxOutput){
                                                maxOutput = Integer.parseInt(output);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(maxOutput);
    }
}
