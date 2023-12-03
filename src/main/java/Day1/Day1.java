package Day1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Day1 {
    public static String convertStringToInt(String str) {
       StringBuilder sb = new StringBuilder();

       for(char in : str.toCharArray()){
           if(Character.isDigit(in)){
                sb.append(in);
           }
       }

       return sb.toString();
    }

    public static int getFirstAndLast(String str){
        StringBuilder sb = new StringBuilder(convertStringToInt(str));

        char first = sb.charAt(0);
        String joined;
        if(sb.length() > 1) {
            char last = sb.charAt(sb.length() - 1);
            joined = first + "" + last;
            return Integer.parseInt(joined);
        }

        joined = first + "" + first;
        return Integer.parseInt(joined);
    }

    public static HashMap<String, Integer> createNumberMap(){
        HashMap<String, Integer> numberMap = new HashMap<>();
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);
        return numberMap;
    }

    public static String changeWordToNumberString(String input){
        HashMap<String, Integer> numberMap = createNumberMap();
        if(input.length() == 1){
            return "" + Integer.parseInt(input);
        }

        if(numberMap.containsKey(input)){
            return "" + numberMap.get(input);
        }

        return "";
    }
}
