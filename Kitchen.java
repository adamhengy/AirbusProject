package Rooms;
import GameStructure.GameMethods;

public class Kitchen extends GameMethods implements RoomInterface{
    @Override
    public String getRoomName() {
        return "Kitchen";
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
        return 20;
    }
    public int getHauntedPercent(){
        return 10;
    }
}
