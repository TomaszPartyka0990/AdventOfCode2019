package dayfour;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src\\main\\resources\\DayFourInput.txt");
        String line = Files.lines(path).collect(Collectors.joining());
        System.out.println(line);
        String[] values = line.split("-");
        int startValue = Integer.parseInt(values[0]);
        int endValue = Integer.parseInt(values[1]);
        System.out.println(startValue);
        System.out.println(endValue);
        int counter = 0;
        for (int i=startValue; i<=endValue; i++){
            int[] valueInTab = getIntTabFromString(i+"");
            if (checkIfNumberMatchesCriteria(valueInTab)){
                counter++;
            }
        }
        System.out.println(counter);
    }

    private static int[] getIntTabFromString(String string){
        int[] table = new int[string.length()];
        for (int i=0; i<string.length(); i++){
            table[i] = Integer.parseInt(string.substring(i, i+1));
        }
        return table;
    }

    private static boolean checkIfNumberMatchesCriteria (int[] tab){
        boolean doubleDigitCriterium = false;
        for (int i=0; i<tab.length-1; i++){
            if (tab[i+1]<tab[i]){
                return false;
            }
            switch (i){
                case 0:
                    if (tab[i]==tab[i+1] && tab[i+1]!=tab[i+2]){
                        doubleDigitCriterium = true;
                    }
                    break;
                case 4:
                    if (tab[i]==tab[i+1] && tab[i-1]!=tab[i]){
                        doubleDigitCriterium = true;
                    }
                    break;
                default:
                    if (tab[i]==tab[i+1] && tab[i-1]!=tab[i] && tab[i+1]!=tab[i+2]){
                        doubleDigitCriterium = true;
                    }
                    break;
            }
        }
        return doubleDigitCriterium;
    }
}
