package dayeight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src\\main\\resources\\DayEightInput.txt");
        String line = Files.lines(path).collect(Collectors.joining());
        List<String> layers = new ArrayList<>();
        String tmp = line;
        do {
            layers.add(tmp.substring(0,150));
            tmp = tmp.substring(150);
        } while (tmp.length() > 150);
        layers.add(tmp);
        int index = 0;
        int zeroCounter;
        int minZeroCounter = 151;
        for(String layer:layers){
            zeroCounter = 0;
            for (int i = 0;  i < layer.length(); i++) {
                if (layer.charAt(i) == '0') {
                    zeroCounter++;
                }
            }
            if (zeroCounter < minZeroCounter) {
                minZeroCounter = zeroCounter;
                index = layers.indexOf(layer);
            }
        }
        int oneCounter = 0;
        int twoCounter = 0;
        String chosenLayer = layers.get(index);
        for (int i = 0; i<chosenLayer.length(); i++){
            if (chosenLayer.charAt(i) == '1') {
                oneCounter++;
            }
            if (chosenLayer.charAt(i) == '2') {
                twoCounter++;
            }
        }
        System.out.println(oneCounter*twoCounter);
        int rowcounter = 0;
        StringBuilder picture = new StringBuilder();
        List<String> pictureRows = new ArrayList<>();
        for (int i=0; i<150; i++){
            for(String layer:layers){
                if (layer.charAt(i) != '2'){
                    if (layer.charAt(i) == '1') {
                        picture.append(layer.charAt(i));
                    }
                    if (layer.charAt(i) == '0') {
                        picture.append(" ");
                    }
                    rowcounter++;
                    if (rowcounter == 25){
                        rowcounter = 0;
                        pictureRows.add(picture.toString());
                        picture.delete(0,25);
                    }
                    break;
                }
            }
        }
        System.out.println();
        System.out.println();
        for(String pictureRow:pictureRows){
            System.out.println(pictureRow);
        }
    }
}
