package dayeleven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static String intcodeInput;
    static String intcodeOutput;
    static boolean intcodeIsFinished;
    static int actualPosition;

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src\\main\\resources\\DayElevenInput.txt");
        String line = Files.lines(path).collect(Collectors.joining());
        String[] dataSplitted = line.split(",");
        List<String> data = new ArrayList<>();
        Collections.addAll(data, dataSplitted);
        PaintingRobot robot = new PaintingRobot();
        intcodeIsFinished = false;
        actualPosition = 0;
        while(!intcodeIsFinished){
            intcodeInput = robot.getColorOfPanelTheRobotIsOn()+"";
            intcode(data);
            robot.paintThePanel(Integer.parseInt(intcodeOutput));
            intcode(data);
            robot.moveTheRobot(Integer.parseInt(intcodeOutput));
        }
        //robot.printGrid();
        System.out.println(robot.countPaintedPanels());
    }

    static void intcode (List<String> memoryToWorkOn){
        for (int i=0; i<10000; i++){
            memoryToWorkOn.add("0");
        }
        long firstNumber = 0;
        long secondNumber = 0;
        int resultPosition = 0;
        int relativeBase = 0;
        String result = "";
        String input;
        String opcode = "";
        String parameterOneMode="";
        String parameterTwoMode="";
        String parameterThreeMode = "0";
        do {
            if (memoryToWorkOn.get(actualPosition).length() > 1){
                switch (memoryToWorkOn.get(actualPosition).length()){
                    case 2:
                        if ("99".equals(memoryToWorkOn.get(actualPosition))){
                            opcode = "99";
                        } else {
                            opcode = memoryToWorkOn.get(actualPosition).charAt(1) + "";
                            parameterOneMode = "0";
                            parameterTwoMode = "0";
                            parameterThreeMode = "0";
                        }
                        break;
                    case 3:
                        opcode = memoryToWorkOn.get(actualPosition).charAt(2)+"";
                        parameterOneMode = memoryToWorkOn.get(actualPosition).charAt(0)+"";
                        parameterTwoMode = "0";
                        parameterThreeMode = "0";
                        break;
                    case 4:
                        opcode = memoryToWorkOn.get(actualPosition).charAt(3)+"";
                        parameterOneMode = memoryToWorkOn.get(actualPosition).charAt(1)+"";
                        parameterTwoMode = memoryToWorkOn.get(actualPosition).charAt(0)+"";
                        parameterThreeMode = "0";
                        break;
                    case 5:
                        opcode = memoryToWorkOn.get(actualPosition).charAt(4)+"";
                        parameterOneMode = memoryToWorkOn.get(actualPosition).charAt(2)+"";
                        parameterTwoMode = memoryToWorkOn.get(actualPosition).charAt(1)+"";
                        parameterThreeMode = memoryToWorkOn.get(actualPosition).charAt(0)+"";
                        break;
                }
            } else {
                opcode = memoryToWorkOn.get(actualPosition);
                parameterOneMode = "0";
                parameterTwoMode = "0";
                parameterThreeMode = "0";
            }
            if ("1".equals(opcode)||"2".equals(opcode)||"5".equals(opcode)||"6".equals(opcode)
                    ||"7".equals(opcode)||"8".equals(opcode)) {
                if ("0".equals(parameterOneMode)){
                    firstNumber = Long.parseLong(memoryToWorkOn.get(Integer.parseInt(memoryToWorkOn.get(actualPosition + 1))));
                } else if ("2".equals(parameterOneMode)){
                    firstNumber = Long.parseLong(memoryToWorkOn.get(relativeBase + Integer.parseInt(memoryToWorkOn.get(actualPosition + 1))));
                } else {
                    firstNumber = Long.parseLong(memoryToWorkOn.get(actualPosition + 1));
                }
                if ("0".equals(parameterTwoMode)) {
                    secondNumber = Long.parseLong(memoryToWorkOn.get(Integer.parseInt(memoryToWorkOn.get(actualPosition + 2))));
                } else if ("2".equals(parameterTwoMode)) {
                    secondNumber = Long.parseLong(memoryToWorkOn.get(relativeBase + Integer.parseInt(memoryToWorkOn.get(actualPosition + 2))));
                } else {
                    secondNumber = Long.parseLong(memoryToWorkOn.get(actualPosition + 2));
                }
                if ("2".equals(parameterThreeMode)){
                    resultPosition = relativeBase + Integer.parseInt(memoryToWorkOn.get(actualPosition+3));
                } else {
                    resultPosition = Integer.parseInt(memoryToWorkOn.get(actualPosition+3));
                }
            }
            if ("1".equals(opcode)){
                result = firstNumber + secondNumber + "";
            }
            if ("2".equals(opcode)){
                result = firstNumber * secondNumber + "";
            }
            if ("3".equals(opcode)){
                if ("2".equals(parameterOneMode)){
                    resultPosition = relativeBase + Integer.parseInt(memoryToWorkOn.get(actualPosition+1));
                } else {
                    resultPosition = Integer.parseInt(memoryToWorkOn.get(actualPosition+1));
                }
                //System.out.println("Give input");
                input = intcodeInput;
                result = input;
            }
            if ("4".equals(opcode)){
                if ("0".equals(parameterOneMode)) {
                    result = memoryToWorkOn.get(Integer.parseInt(memoryToWorkOn.get(actualPosition + 1)));
                } else if ("2".equals(parameterOneMode)) {
                    result = memoryToWorkOn.get(relativeBase + Integer.parseInt(memoryToWorkOn.get(actualPosition + 1)));
                } else {
                    result = memoryToWorkOn.get(actualPosition + 1);
                }
                intcodeOutput = result;
                //System.out.println(result);
                actualPosition += 2;
                return;
            }
            if ("5".equals(opcode)){
                if (firstNumber!=0){
                    actualPosition = (int) secondNumber;
                } else {
                    actualPosition += 3;
                }
            }
            if ("6".equals(opcode)){
                if (firstNumber==0){
                    actualPosition = (int) secondNumber;
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
            }
            if ("8".equals(opcode)) {
                if (firstNumber == secondNumber) {
                    result = "1";
                } else {
                    result = "0";
                }
            }
            if ("9".equals(opcode)){
                if ("0".equals(parameterOneMode)) {
                    result = memoryToWorkOn.get(Integer.parseInt(memoryToWorkOn.get(actualPosition + 1)));
                } else if ("2".equals(parameterOneMode)) {
                    result = memoryToWorkOn.get(relativeBase + Integer.parseInt(memoryToWorkOn.get(actualPosition + 1)));
                } else {
                    result = memoryToWorkOn.get(actualPosition + 1);
                }
                relativeBase += Integer.parseInt(result);
                actualPosition += 2;
            }
            if ("1".equals(opcode)||"2".equals(opcode)||"3".equals(opcode)||"7".equals(opcode)||"8".equals(opcode)) {
                if (memoryToWorkOn.get(resultPosition) == null) {
                    memoryToWorkOn.add(resultPosition, result);
                } else {
                    memoryToWorkOn.set(resultPosition, result);
                }
                if ("3".equals(opcode)){
                    actualPosition += 2;
                } else {
                    actualPosition += 4;
                }
            }
        } while (!"99".equals(opcode));
        intcodeIsFinished = true;
    }
}
