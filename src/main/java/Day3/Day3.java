package Day3;

import javax.xml.stream.Location;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day3 {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\losma\\IdeaProjects\\AdventOfCode2023\\src\\main\\java\\Day3\\input.txt";
        ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();

        //Adding the file to the map
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(line -> {
                if (!line.isEmpty()) {
                    //Iterating each character on the line and adding to the map
                    ArrayList<String> row = new ArrayList<String>();
                    for (int i = 0; i < line.length(); i++) {
                        row.add(String.valueOf(line.charAt(i)));
                    }
                    map.add(row);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Part 1
        int counter[] = {0};
        int lastRow = map.size();
        ArrayList<String> specialCharacters = getSpecialCharacterArraylist();
        Map<String, Integer> foundSpecialLocation = new HashMap<String, Integer>();
        Map<String,Integer> foundDigits = new HashMap<String,Integer>();

        for (ArrayList<String> row : map) {
            //Search each row in the map for the special character, if found move to circular search.
            for (int i = 0; i < row.size(); i++) {
                //Found a special character, move to circular search
                if (specialCharacters.contains(row.get(i))) {
                    System.out.println("Found special character: " + row.get(i));
                    foundSpecialLocation.put("row", counter[0]);
                    foundSpecialLocation.put("index", i);

                    //Search the circular area around the special character
                    circularSearch(map,foundSpecialLocation,foundDigits);
                }
            }

            counter[0]++;
        }

        System.out.println(foundDigits);
    }

    private static void circularSearch(ArrayList<ArrayList<String>> map, Map<String, Integer> foundLocation,
    Map<String,Integer> foundDigits) {
        //Values of the found location for the special character
        int foundRow = foundLocation.get("row");
        int foundIndex = foundLocation.get("index");

        int[] searchRows = new int[0];
        int[] searchIndex = new int[0];

        //If first row
        if (foundRow == 0) {
            //We are at first row in the list, no top row available
            searchRows = new int[]{foundRow, foundRow + 1};
            searchIndex = new int[]{foundIndex, foundIndex + 1};
        }

        //If Last row
        if (foundRow == map.size() - 1) {
            //We are at last row in the list, no bottom row available
            searchRows = new int[]{foundRow - 1, foundRow};
            searchIndex = new int[]{foundIndex - 1, foundIndex};
        }

        //If any other row
        if (foundRow > 0 && foundRow < map.size() - 1) {
            //We are in the middle of the list, top and bottom rows available
            searchRows = new int[]{foundRow - 1, foundRow, foundRow + 1};
            searchIndex = new int[]{foundIndex - 1, foundIndex, foundIndex + 1};
        }

        //Search the rows and indexs for digits
        for (int row : searchRows) {
            Pattern pattern = Pattern.compile("[0-9]");
            for (int index : searchIndex) {
                if (map.get(row).get(index).matches("[0-9]")) {
                    //Found a digit, add to list
                    System.out.println(map.get(row).get(index));
                    foundDigits.put(row+"",index);
                }
            }
        }

        //Searching only the rows above and below the special character
        for (int i = searchRows[0]; i <= searchRows[searchRows.length-1]; i++){
            //Creating string to build digits
            String joinedDigits = "";

            //Searching only the indexs above and below the special character
            for (int j = 0; j < map.get(i).size(); j++){
                int counter = 0;
                //Making sure loop doesn't go passed the bounds of special character and get data not touching
                if(j >= foundIndex + 1 && !Character.isDigit(map.get(i).get(j).charAt(0)))
                {
                    if(!joinedDigits.isEmpty()){
                        if(foundDigits.containsKey(i)){
                            counter++;
                            foundDigits.put(i+"."+counter,foundDigits.get(i+"") + Integer.parseInt(joinedDigits));
                        }
                        else{
                            foundDigits.put(i+"",Integer.parseInt(joinedDigits));
                        }
                    }
                    break;
                }

                //If the character is not a digit, add to string to build
                if(!Character.isDigit(map.get(i).get(j).charAt(0))){
                    //Making sure we've found values to insert, otherwise skip
                    if(!joinedDigits.isEmpty()){
                        if(foundDigits.containsKey(i+"")){
                            counter++;
                            foundDigits.put(i+"."+counter,foundDigits.get(i+"") + Integer.parseInt(joinedDigits));
                        }
                        else{
                            foundDigits.put(i+"",Integer.parseInt(joinedDigits));
                        }
                    }
                }

                //Found digits add to String to build then add to list by parse
                if(Character.isDigit(map.get(i).get(j).charAt(0))){
                    joinedDigits += map.get(i).get(j);
                }
            }
        }

        System.out.println(foundDigits);
    }

    private static ArrayList<String> getSpecialCharacterArraylist() {
        ArrayList<String> specialCharacters = new ArrayList<String>();
        specialCharacters.add("#");
        specialCharacters.add("%");
        specialCharacters.add("+");
        specialCharacters.add("$");
        specialCharacters.add("£");
        specialCharacters.add("!");
        specialCharacters.add("*");
        return specialCharacters;
    }
}
