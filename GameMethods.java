package GameStructure;
import Rooms.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
public abstract class GameMethods {
    private int abnormallyCold = 0;
    private final Map<String,Integer> objectStore = new HashMap<>();
    private final Random randomObject = new Random();
    private boolean wasHaunted = false;
    private int roomsExplored = 0;
    private final List<String> roomNamesExplored = new ArrayList<>();
    private static final String CLOSE_COMMAND = "close";
    private final List<RoomInterface> roomList = new ArrayList<>();
    private final String[] objectNames = {"Music Box","Haunted Mirror","Ouija Board","Summoning Circle","Tarot Cards","Voodoo doll"};

    protected void populateList(){
        roomList.add(new Bathroom());
        roomList.add(new BedroomOne());
        roomList.add(new BedroomTwo());
        roomList.add(new Kitchen());
        roomList.add(new LivingRoom());
        roomList.add(new DiningRoom());
    }
    private void printList(){
        int counter = 1;
        System.out.println("-------------------------------------------------");
        for(RoomInterface i : roomList){
            System.out.println(i.getRoomName() + " (Press "+counter+") ");
            counter++;
        }
    }
    protected void startGame(){
        populateList();
        System.out.println("\nWelcome to the Transylvania Haunted House!\n");
        printList();
        System.out.println("These are the rooms that you are free to explore!");
        System.out.println("-------------------------------------------------");
        continueGame();
    }
    private void continueGame() {
        try (Scanner sc = new Scanner(System.in)) {
            String input;
            do {
                if (sc.hasNextLine()) {
                    input = sc.nextLine();

                    if (!input.equals(CLOSE_COMMAND)) {
                        switchCaseHelper(input);
                    }
                } else {
                    break;
                }
            } while (!input.equals(CLOSE_COMMAND));

        }
        System.out.println("            Here was your story:");
        generateStory();
    }

    private void switchCaseHelper(String line) {
        System.out.println("-------------------------------------------------");
        switch (line) {
            case "1" -> printRoomDetails("Bathroom");
            case "2" -> printRoomDetails("Bedroom One");
            case "3" -> printRoomDetails("Bedroom Two");
            case "4" -> printRoomDetails("Kitchen");
            case "5" -> printRoomDetails("Living Room");
            case "6" -> printRoomDetails("Dining Room");
            default -> System.out.println("Please enter a valid number (1-6)");
        }
    }

    private void printRoomDetails(String line){
        for(RoomInterface j : roomList) {
            if (j.getRoomName().equals(line)) {
                generateRandomObject();
                roomNamesExplored.add(j.getRoomName());
                roomsExplored++;
                if (j.isHaunted()) {
                    int oneInFour = randomObject.nextInt(4);
                    wasHaunted = true;
                    System.out.println("AHHHH\nThis room is haunted!\nQuick get out, you have a one in four chance to get out! Good luck!");
                    try (Scanner scanner = new Scanner(System.in)) {
                        String typeClose;
                        do {
                            System.out.println("Please enter 'close' in order to leave the room!");
                            typeClose = scanner.nextLine();
                        } while (!typeClose.equals(CLOSE_COMMAND));

                    } finally {
                        if (oneInFour == 1) {
                            System.out.println("Oh No! You weren't successful in escaping!");
                            System.out.println("                Here is your story");
                        } else {
                            System.out.println("Success you survived!");
                        }
                        generateStory();
                        System.exit(0);
                    }
                }




                System.out.println("\nWelcome to the " + j.getRoomName());

                int temperature = j.getRoomTemperature();
                if (temperature < 6) {
                    System.out.println("\nThe current temperature is " + temperature + "°c");
                    abnormallyCold++;
                    printCold();
                    printList();
                } else {
                    System.out.println("\nThe current temperature is " + temperature + "°c\n\n");
                    printList();
                }

            }
        }
    }
    private void generateRandomObject(){
        int percentChance = randomObject.nextInt(100);
        if(percentChance > 80) {
            int randomObjectInt = randomObject.nextInt(6 - 1);
            if (objectStore.containsKey(objectNames[randomObjectInt])) {
                int currentValue = objectStore.get(objectNames[randomObjectInt]);
                objectStore.put(objectNames[randomObjectInt], currentValue++);
            } else {
                objectStore.put(objectNames[randomObjectInt],1);
            }
            System.out.println("You have found a " + objectNames[randomObjectInt] + "!");
        }
    }

    protected boolean generateHaunted(int hauntedPercent) {
        int randomNumber = randomObject.nextInt(100);
        return !(randomNumber > hauntedPercent);
    }
    protected int generateTemperature(int percentChance){
        int randomNumber = randomObject.nextInt(100);
        boolean normalTemp = (100 - randomNumber) <= (100 - percentChance);
        if(normalTemp){
            return randomObject.nextInt(25 - randomObject.nextInt(5));
        }
        return randomObject.nextInt(5);
    }
    private void printCold(){
        System.out.println("This room is abnormally cold, spooky!\n\n");
    }
    private void generateStory(){
        System.out.println("\n            You discovered: " + roomsExplored + " Rooms!");
        System.out.println("\n            These are the rooms you explored in order!\n");
        System.out.print("            ");
        for(int i = 0; i < roomNamesExplored.size(); i++) {
            if (i == roomNamesExplored.size() - 1) {
                System.out.print(roomNamesExplored.get(i) + "\n");
            } else {
                System.out.print(roomNamesExplored.get(i) + " -> ");
            }
        }
        if(abnormallyCold > 0){
            System.out.println("\n            There were " + abnormallyCold + " instances where the temperature was abnormally cold");
        }
        if(wasHaunted){
            System.out.println("\n            You got jumpscared this time!\n");
        } else {
            System.out.println("\n            You weren't jumpscared this time!");
        }
        if(objectStore.size() > 0){
            System.out.println("\n            These were all of the haunted objects you came across, and their frequency!\n");
            hashMapPrinter();
        } else {
            System.out.println("\n            You didn't find any haunted objects this time!");
        }
    }
    private void hashMapPrinter(){
        for (Map.Entry<String, Integer> entry : objectStore.entrySet()) {
            System.out.println("            "+ entry.getKey() + " : " + entry.getValue());
        }
    }
}
