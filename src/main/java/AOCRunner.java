import Day1.Day1;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkPermission;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class AOCRunner {

    public static void main(String[] args) {
        Day1Part2();
    }

    private static void Day1Part1(){
        //File path location
        String filePath = "C:\\Users\\losma\\IdeaProjects\\AdventOfCode2023\\src\\main\\java\\somefile.txt";

        int[] sum = {0};

        //Using streams to read the file
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(line -> {
                // Process each line here
                if(!line.isEmpty()) {
                    sum[0] += Day1.getFirstAndLast(line);
                }
            });

            System.out.println(sum[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Day1Part2() {
        //File path location
        String filePath = "C:\\Users\\losma\\IdeaProjects\\AdventOfCode2023\\src\\main\\java\\somefile.txt";
        int[] sum = {0};

        //creating a regex capture group
        String patternForFirst = "([0-9]|one|two|three|four|five|six|seven|eight|nine)";

        // Create a Pattern object for the above regex
        Pattern firstWordPattern = Pattern.compile(patternForFirst);

        //Using streams to read the file
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(line -> {
                if (!line.isEmpty()) {
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

                    if(firstMatch.length() > 1){
                        firstMatch = Day1.changeWordToNumberString(firstMatch);
                    }
                    if(lastMatch.length() > 1){
                        lastMatch = Day1.changeWordToNumberString(lastMatch);
                    }

                    //Joining the first and last match
                    String joined = firstMatch + lastMatch;
                    int joinedInt = Integer.parseInt(joined);

                    sum[0] += joinedInt;
                }
            });

            //Printing the sum value
            System.out.println(sum[0]);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
