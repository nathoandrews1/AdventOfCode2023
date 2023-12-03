import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class AOCRunner {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\losma\\IdeaProjects\\AdventOfCode2023\\src\\main\\java\\somefile.txt";
        int[] sum = {0};

        HashMap<Integer,Boolean> possibleGamesMap = new HashMap<>();
        try{
        Files.lines(Paths.get(filePath)).forEach(line -> {
                if (!line.isEmpty()) {
                    //Process each line here,

                    //Day1.Day1Part1(line, sum);
                    //Day1.Day1Part2(line, sum);

                    //Day2.Day2Part1(line,sum);

                    ArrayList<String> games = new ArrayList<>();

                    HashMap<String,Integer> gameValues = new HashMap<>();
                    gameValues.put("red",0);
                    gameValues.put("blue",0);
                    gameValues.put("green",0);

                    String[] split = line.split("[:;, ]");

                    for (String s : split) {
                        if (!s.isEmpty()) {
                            games.add(s);
                        }
                    }

                    int currentGameId = 0;

                    for(int i = 0; i < games.size()-1; i++){
                        if(games.get(i).equalsIgnoreCase("game")){
                            currentGameId = Integer.parseInt(games.get(i+1));
                        }
                        if(games.get(i).equalsIgnoreCase("red")){
                            int currentRed = gameValues.get("red");
                            currentRed += Integer.parseInt(games.get(i+1));
                            gameValues.put("red",currentRed);
                        }
                        if(games.get(i).equalsIgnoreCase("blue")){
                            int currentBlue = gameValues.get("blue");
                            currentBlue += Integer.parseInt(games.get(i+1));
                            gameValues.put("blue",currentBlue);
                        }
                        if(games.get(i).equalsIgnoreCase("green")){
                            int currentGreen = gameValues.get("green");
                            currentGreen += Integer.parseInt(games.get(i+1));
                            gameValues.put("green",currentGreen);
                        }
                    }

                    System.out.println("Game id: " + currentGameId);
                    System.out.println("Red values " + gameValues.get("red"));
                    System.out.println("Green values " + gameValues.get("green"));
                    System.out.println("Blue values " + gameValues.get("blue"));


                    if(gameValues.get("red") <= 12 && gameValues.get("green") <= 13 && gameValues.get("blue") <= 14) {
                        possibleGamesMap.put(currentGameId, true);
                    }else{
                        possibleGamesMap.put(currentGameId, false);
                    }

                    System.out.println("Game is possible: " + possibleGamesMap.get(currentGameId));
                    System.out.println();

                    sum[0] = 0;
                    //Loop over the map and sum all the id values based on the boolean value
                    for(Map.Entry<Integer,Boolean> entry : possibleGamesMap.entrySet()){
                        if(entry.getValue() == true){
                            sum[0] += entry.getKey();
                        }
                    }
                }
            });

            System.out.println();
            System.out.println(possibleGamesMap);

            System.out.println("Sum of all values:");
            System.out.println(sum[0]);
        } catch (IOException e) {

        }
    }
}
