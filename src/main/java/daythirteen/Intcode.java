package daythirteen;

import java.util.List;

public class Intcode {
    private String intcodeInput;
    private String intcodeOutput;
    private boolean intcodeIsFinished;
    private int actualPosition;
    private int relativeBase;
    private List<String> memoryToWorkOn;
    private boolean inputStarts;
    private int inputCounter;

    public Intcode(List<String> memoryToWorkOn) {
        this.intcodeInput = "";
        this.intcodeOutput = "";
        this.intcodeIsFinished = false;
        this.actualPosition = 0;
        this.relativeBase = 0;
        this.memoryToWorkOn =  memoryToWorkOn;
        this.inputCounter = 0;
        this.inputStarts = false;
        for (int i=0; i<10000; i++){
            this.memoryToWorkOn.add("0");
        }
    }

    public boolean isInputStarts() {
        return inputStarts;
    }

    public void setIntcodeInput(String intcodeInput) {
        this.intcodeInput = intcodeInput;
    }

    public int getIntcodeOutput() {
        return Integer.parseInt(intcodeOutput);
    }

    public boolean isIntcodeIsFinished() {
        return intcodeIsFinished;
    }

    public void runIntcode (){
        long firstNumber = 0;
        long secondNumber = 0;
        int resultPosition = 0;
        String result = "";
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
                inputCounter++;
                if (inputCounter<=3){
                    if (inputCounter == 3){
                        inputStarts = true;
                    }
                    return;
                }
                result = intcodeInput;
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
