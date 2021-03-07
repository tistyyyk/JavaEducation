package main.lesson8.homework;

public class Wall implements IBarrier{
    private int height;

    public Wall (int height) {
        this.height = height;
    }

    @Override
    public boolean overcome(IParticipant participant) {
        if (participant.getJumpable() >= height) {
            participant.jump(height);
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "Wall "+this.height;
    }

}
