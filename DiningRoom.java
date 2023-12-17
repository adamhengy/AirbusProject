package Rooms;
import GameStructure.GameMethods;

public class DiningRoom extends GameMethods implements RoomInterface {
    @Override
    public String getRoomName() {
        return "Dining Room";
    }

    @Override
    public int getRoomTemperature() {
        return super.generateTemperature(getColdPercent());
    }

    @Override
    public boolean isHaunted() {
        return super.generateHaunted(getHauntedPercent());
    }
    @Override
    public int getColdPercent(){
        return 10;
    }
    public int getHauntedPercent(){
        return 10;
    }
}
