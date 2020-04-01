package daythirteen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src\\main\\resources\\DayThirteenInput.txt");
        String line = Files.lines(path).collect(Collectors.joining());
        String[] dataSplitted = line.split(",");
        List<String> data = new ArrayList<>();
        Collections.addAll(data, dataSplitted);
        Intcode intcode = new Intcode(data);
        ArcadeCabinet arcadeCabinet = new ArcadeCabinet(intcode);
        arcadeCabinet.playGame();
        System.out.println(arcadeCabinet.coutBlockTiles());
    }
}
