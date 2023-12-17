package Rooms;
import GameStructure.GameMethods;

public class BedroomTwo extends GameMethods implements RoomInterface{
    @Override
    public String getRoomName() {
        return "Bedroom Two";
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
        return 5;
    }
    public int getHauntedPercent(){
        return 15;
    }
}
