package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day2 {

    public static void main(String[] args) {
    }

    public static void Day2Part1() {
        String filePath = "C:\\Users\\losma\\IdeaProjects\\AdventOfCode2023\\src\\main\\java\\somefile.txt" ;
        int[] sum = {0};

        boolean[] isSubset = {false};
        HashMap<Integer, Boolean> possibleGamesMap = new HashMap<>();
        try {
            Files.lines(Paths.get(filePath)).forEach(line -> {
                if (!line.isEmpty()) {
                    //Process each line here,
                    ArrayList<String> games = new ArrayList<>();

                    HashMap<String, Integer> gameValues = new HashMap<>();
                    gameValues.put("red", 0);
                    gameValues.put("blue", 0);
                    gameValues.put("green", 0);

                    HashMap<String, Integer> subsetValues = new HashMap<>();
                    gameValues.put("red", 0);
                    gameValues.put("blue", 0);
                    gameValues.put("green", 0);

                    String[] split = line.split("[:, ]");

                    for (String s : split) {
                        if (!s.isEmpty()) {
                            games.add(s.toLowerCase());
                        }
                    }

                    int currentGameId = 0;

                    for (int i = 0; i < games.size(); i++) {


                        if (games.get(i).contains("game")) {
                            currentGameId = Integer.parseInt(games.get(i + 1));
                        }

                        //Check red
                        int currentRed = gameValues.get("red");
                        if (games.get(i).contains("red")) {
                            if (games.get(i).contains(";")) {

                                //Adding current values of the current color as it only indicates the end of the subset.
                                currentRed = Integer.parseInt(games.get(i - 1));

                                if (currentRed > gameValues.get("red")) {
                                    gameValues.put("red", currentRed);
                                }

                                //Wait until we add the current values of the found end of subset for correct value comparison
                                if (isSubset[0] == true) {
                                    int highestCurrent = gameValues.get("red");
                                    int newSubsetCount;

                                    if (subsetValues.get("red") != null) {
                                        newSubsetCount = subsetValues.get("red");
                                    }

                                    newSubsetCount = 0;
                                    if (newSubsetCount > highestCurrent) {
                                        gameValues.put("red", newSubsetCount);
                                    }
                                }

                                isSubset[0] = true;
                            }

                            if (isSubset[0] == true) {
                                currentRed = Integer.parseInt(games.get(i - 1));
                                subsetValues.put("red", currentRed);

                                if (subsetValues.get("red") > gameValues.get("red")) {
                                    gameValues.put("red", subsetValues.get("red"));
                                }
                            }

                            if (isSubset[0] == false) {
                                currentRed += Integer.parseInt(games.get(i - 1));
                                gameValues.put("red", currentRed);
                            }
                        }


                        //Check blue
                        int currentBlue = gameValues.get("blue");
                        if (games.get(i).contains("blue")) {
                            if (games.get(i).contains(";")) {

                                //Adding current values of the current color as it only indicates the end of the subset.
                                currentBlue = Integer.parseInt(games.get(i - 1));

                                if (currentBlue > gameValues.get("blue")) {
                                    gameValues.put("blue", currentBlue);
                                }

                                //Wait until we add the current values of the found end of subset for correct value comparison
                                if (isSubset[0] == true) {
                                    int highestCurrent = gameValues.get("blue");
                                    int newSubsetCount;

                                    if (subsetValues.get("blue") != null) {
                                        newSubsetCount = subsetValues.get("blue");
                                    }

                                    newSubsetCount = 0;
                                    if (newSubsetCount > highestCurrent) {
                                        gameValues.put("blue", newSubsetCount);
                                    }
                                }

                                isSubset[0] = true;
                            }

                            if (isSubset[0] == true) {
                                currentBlue = Integer.parseInt(games.get(i - 1));
                                subsetValues.put("blue", currentBlue);

                                if (subsetValues.get("blue") > gameValues.get("blue")) {
                                    gameValues.put("blue", subsetValues.get("blue"));
                                }
                            }

                            if (isSubset[0] == false) {
                                currentBlue += Integer.parseInt(games.get(i - 1));
                                gameValues.put("blue", currentBlue);
                            }
                        }

                        //Check green
                        int currentGreen = gameValues.get("green");

                        if (games.get(i).contains("green")) {
                            if (games.get(i).contains(";")) {

                                //Adding current values of the current color as it only indicates the end of the subset.
                                currentGreen = Integer.parseInt(games.get(i - 1));

                                if (currentGreen > gameValues.get("green")) {
                                    gameValues.put("green", currentGreen);
                                }

                                //Wait until we add the current values of the found end of subset for correct value comparison
                                if (isSubset[0] == true) {
                                    int highestCurrent = gameValues.get("green");
                                    int newSubsetCount;

                                    if (subsetValues.get("green") != null) {
                                        newSubsetCount = subsetValues.get("green");
                                    }

                                    newSubsetCount = 0;
                                    if (newSubsetCount > highestCurrent) {
                                        gameValues.put("green", newSubsetCount);
                                    }
                                }

                                isSubset[0] = true;
                            }

                            if (isSubset[0] == true) {
                                currentGreen = Integer.parseInt(games.get(i - 1));
                                subsetValues.put("green", currentGreen);

                                if (subsetValues.get("green") > gameValues.get("green")) {
                                    gameValues.put("green", subsetValues.get("green"));
                                }
                            }

                            if (isSubset[0] == false) {
                                currentGreen += Integer.parseInt(games.get(i - 1));
                                gameValues.put("green", currentGreen);
                            }
                        }
                    }

                    System.out.println("Game id: " + currentGameId);
                    System.out.println("Red values " + gameValues.get("red"));
                    System.out.println("Green values " + gameValues.get("green"));
                    System.out.println("Blue values " + gameValues.get("blue"));


                    if (gameValues.get("red") <= 12 && gameValues.get("green") <= 13 && gameValues.get("blue") <= 14) {
                        possibleGamesMap.put(currentGameId, true);
                    } else {
                        possibleGamesMap.put(currentGameId, false);
                    }

                    System.out.println("Game is possible: " + possibleGamesMap.get(currentGameId));
                    System.out.println();

                    sum[0] = 0;
                    //Loop over the map and sum all the id values based on the boolean value
                    for (Map.Entry<Integer, Boolean> entry : possibleGamesMap.entrySet()) {
                        if (entry.getValue() == true) {
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
