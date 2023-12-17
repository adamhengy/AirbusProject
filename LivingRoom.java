package Rooms;
import GameStructure.GameMethods;

public class LivingRoom extends GameMethods implements RoomInterface{
    @Override
    public String getRoomName() {
        return "Living Room";
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
        return 7;
    }
    public int getHauntedPercent(){
        return 15;
    }
}
