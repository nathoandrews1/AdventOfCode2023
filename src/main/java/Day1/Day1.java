package Day1;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {
    public static String convertStringToInt(String str) {
        StringBuilder sb = new StringBuilder();

        for (char in : str.toCharArray()) {
            if (Character.isDigit(in)) {
                sb.append(in);
            }
        }

        return sb.toString();
    }

    public static int getFirstAndLast(String str) {
        StringBuilder sb = new StringBuilder(convertStringToInt(str));

        char first = sb.charAt(0);
        String joined;
        if (sb.length() > 1) {
            char last = sb.charAt(sb.length() - 1);
            joined = first + "" + last;
            return Integer.parseInt(joined);
        }

        joined = first + "" + first;
        return Integer.parseInt(joined);
    }

    public static HashMap<String, Integer> createNumberMap() {
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

    public static String changeWordToNumberString(String input) {
        HashMap<String, Integer> numberMap = createNumberMap();
        if (input.length() == 1) {
            return "" + Integer.parseInt(input);
        }

        if (numberMap.containsKey(input)) {
            return "" + numberMap.get(input);
        }

        return "";
    }

    public static void Day1Part1(String line, int[] sum) {
        //File path location
        sum[0] += Day1.getFirstAndLast(line);
    }

    public static void Day1Part2(String line, int[] sum) {
        //File path location
        String filePath = "C:\\Users\\losma\\IdeaProjects\\AdventOfCode2023\\src\\main\\java\\Day1\\input.txt";

        //creating a regex capture group
        String patternForFirst = "([0-9]|one|two|three|four|five|six|seven|eight|nine)";

        // Create a Pattern object for the above regex
        Pattern firstWordPattern = Pattern.compile(patternForFirst);

        // Now creating pattern matcher object.
        Matcher firstMatcher = firstWordPattern.matcher(line);

        String firstMatch = null;
        String lastMatch = null;

        //Loop the found matches, first start last end
        while (firstMatcher.find()) {
            if (firstMatch == null) {
                //Grabbing the first match
                firstMatch = firstMatcher.group(0);
            }
            //Grabbing the last match
            lastMatch = firstMatcher.group(0);
        }

        if (firstMatch.length() > 1) {
            firstMatch = Day1.changeWordToNumberString(firstMatch);
        }
        if (lastMatch.length() > 1) {
            lastMatch = Day1.changeWordToNumberString(lastMatch);
        }

        //Joining the first and last match
        String joined = firstMatch + lastMatch;
        int joinedInt = Integer.parseInt(joined);

        sum[0] += joinedInt;
    }
}
