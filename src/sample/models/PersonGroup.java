package sample.models;

public class PersonGroup {
    private int id;
    private int startFloor;
    private int endFloor;
    private int groupSize;

    public PersonGroup(int id, int startFloor, int endFloor, int group) {
        this.id = id;
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.groupSize=group;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public void setStartFloor(int startFloor) {
        this.startFloor = startFloor;
    }

    public int getEndFloor() {
        return endFloor;
    }

    public void setEndFloor(int endFloor) {
        this.endFloor = endFloor;
    }
}
