package dayfive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("src\\main\\resources\\DayFiveInput.txt");
        List<String> line = Files.readAllLines(filePath);
        String dataInString = "";
        for(String oneLine:line){
            dataInString = oneLine;
        }
        String[] dataSplited = dataInString.split(",");
        intcode(dataSplited);
    }

    private static void intcode (String[] table){
        int actualPosition = 0;
        int firstNumber = 0;
        int secondNumber = 0;
        int resultPosition = 0;
        String result;
        Scanner scanner = new Scanner(System.in);
        String input;
        String opcode = "";
        String parameterOneMode="";
        String parameterTwoMode="";
        do {
            if (table[actualPosition].length() > 1){
                switch (table[actualPosition].length()){
                    case 2:
                        if ("99".equals(table[actualPosition])){
                            opcode = "99";
                        } else {
                            opcode = table[actualPosition].charAt(1) + "";
                            parameterOneMode = "0";
                            parameterTwoMode = "0";
                        }
                        break;
                    case 3:
                        opcode = table[actualPosition].charAt(2)+"";
                        parameterOneMode = table[actualPosition].charAt(0)+"";
                        parameterTwoMode = "0";
                        break;
                    case 4:
                        opcode = table[actualPosition].charAt(3)+"";
                        parameterOneMode = table[actualPosition].charAt(1)+"";
                        parameterTwoMode = table[actualPosition].charAt(0)+"";
                        break;
                    case 5:
                        opcode = table[actualPosition].charAt(4)+"";
                        parameterOneMode = table[actualPosition].charAt(2)+"";
                        parameterTwoMode = table[actualPosition].charAt(1)+"";
                        break;
                }
            } else {
                opcode = table[actualPosition];
                parameterOneMode = "0";
                parameterTwoMode = "0";
            }
            if ("1".equals(opcode)||"2".equals(opcode)||"5".equals(opcode)||"6".equals(opcode)
                    ||"7".equals(opcode)||"8".equals(opcode)) {
                if ("0".equals(parameterOneMode)){
                    firstNumber = Integer.parseInt(table[Integer.parseInt(table[actualPosition + 1])]);
                } else {
                    firstNumber = Integer.parseInt(table[actualPosition + 1]);
                }
                if ("0".equals(parameterTwoMode)) {
                    secondNumber = Integer.parseInt(table[Integer.parseInt(table[actualPosition + 2])]);
                } else {
                    secondNumber = Integer.parseInt(table[actualPosition + 2]);
                }
                resultPosition = Integer.parseInt(table[actualPosition+3]);
            }
            if ("1".equals(opcode)){
                result = firstNumber + secondNumber + "";
                table[resultPosition] = result;
                actualPosition += 4;
            }
            if ("2".equals(opcode)){
                result = firstNumber * secondNumber + "";
                table[resultPosition] = result;
                actualPosition += 4;
            }
            if ("3".equals(opcode)){
                resultPosition = Integer.parseInt(table[actualPosition+1]);
                System.out.println("Insert the instruction");
                input = scanner.nextLine();
                result = input;
                table[resultPosition] = result;
                actualPosition += 2;
            }
            if ("4".equals(opcode)){
                if ("0".equals(parameterOneMode)) {
                    result = table[Integer.parseInt(table[actualPosition + 1])];
                } else {
                    result = table[actualPosition + 1];
                }
                System.out.println(result);
                actualPosition += 2;
            }
            if ("5".equals(opcode)){
                if (firstNumber!=0){
                    actualPosition = secondNumber;
                } else {
                    actualPosition += 3;
                }
            }
            if ("6".equals(opcode)){
                if (firstNumber==0){
                    actualPosition = secondNumber;
                } else {
                    actualPosition += 3;
                }
            }
            if ("7".equals(opcode)){
                if (firstNumber<secondNumber){
                    result = "1";
                } else {
                    result = "0";
                }
                table[resultPosition] = result;
                actualPosition += 4;
            }
            if ("8".equals(opcode)) {
                if (firstNumber == secondNumber) {
                    result = "1";
                } else {
                    result = "0";
                }
                table[resultPosition] = result;
                actualPosition += 4;
            }
        } while (!"99".equals(opcode));
    }
}
