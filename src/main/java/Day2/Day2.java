package Day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day2 {

    public static void main(String argsp[]){
        String line = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green" ;

        ArrayList<String> games = new ArrayList<>();
        HashMap<Integer,Boolean> possibleGamesMap = new HashMap<>();

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
                System.out.println("Found game id: " + currentGameId);
            }
            if(games.get(i).equalsIgnoreCase("red")){
                gameValues.put("red",Integer.parseInt(games.get(i+1)));
            }
            if(games.get(i).equalsIgnoreCase("blue")){
                gameValues.put("blue",Integer.parseInt(games.get(i+1)));
            }
            if(games.get(i).equalsIgnoreCase("green")){
                gameValues.put("green",Integer.parseInt(games.get(i+1)));
            }
        }

        if(gameValues.get("red") <= 12 && gameValues.get("green") <= 13 && gameValues.get("blue") <= 14) {
            possibleGamesMap.put(currentGameId, true);
        }else{
            possibleGamesMap.put(currentGameId, false);
        }

        System.out.println(games);
        System.out.println(possibleGamesMap);
    }

    public static void Day2Part1(String line, int[] sum){

        String[] games = line.split(" ");

    }
}
