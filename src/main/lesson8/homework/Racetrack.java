package main.lesson8.homework;

public class Racetrack implements IBarrier{
    private int length;

    public Racetrack (int length) {
        this.length = length;
    }

    @Override
    public boolean overcome(IParticipant participant) {
        if (participant.getRunnable() >= length) {
            participant.run(length);
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "Track "+this.length;
    }
}
