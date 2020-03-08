package daytwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("src\\main\\resources\\DayTwoInput.txt");
        List<String> line = Files.readAllLines(filePath);
        String dataInString = "";
        for(String oneLine:line){
            dataInString = oneLine;
        }
        String[] dataSplited = dataInString.split(",");
        int[] intData = new int[dataSplited.length];
        for(int i=0; i<dataSplited.length; i++){
            intData[i] = Integer.parseInt(dataSplited[i]);
        }
        int[] intDataBackup = Arrays.copyOf(intData, intData.length);
        System.out.println(intDataBackup[0]);
        boolean check = false;
        int noun = 0;
        int verb = 0;
        for (int i=0; i<100; i++){
            for (int j=0; j<100; j++){
                intData[1] = i;
                intData[2] = j;
                intcode(intData);
                if (intData[0] == 19690720){
                    noun = i;
                    verb = j;
                    check = true;
                    break;
                }
                intData = Arrays.copyOf(intDataBackup, intDataBackup.length);
            }
            if (check){
                break;
            }
        }
        System.out.println(noun);
        System.out.println(verb);
    }

    private static int[] intcode (int[] table){
        int actualPosition = 0;
        int firstNumberPosition = 0;
        int secondNumberPosition = 0;
        int resultPosition = 0;
        int result = 0;
        do {
            firstNumberPosition = table[actualPosition+1];
            secondNumberPosition = table[actualPosition+2];
            resultPosition = table[actualPosition+3];
            if (table[actualPosition] == 1){
                result = table[firstNumberPosition] + table[secondNumberPosition];
            }
            if (table[actualPosition] == 2){
                result = table[firstNumberPosition] * table[secondNumberPosition];
            }
            if (table[actualPosition]!=1 && table[actualPosition]!=2 && table[actualPosition]!=99){
                table[0] = 0;
                return table;
            }
            table[resultPosition] = result;
            actualPosition += 4;
            if (actualPosition > (table.length - 4)){
                table[0] = 0;
                return table;
            }
        } while (table[actualPosition]!=99);
        return table;
    }
}
