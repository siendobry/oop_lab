package agh.ics.oop;

public enum Direction {
    f("Zwierzak idzie do przodu"),
    b("Zwierzak idzie do tylu"),
    r("Zwierzak skreca w prawo"),
    l("Zwierzak skreca w lewo");

    private String information;

    Direction(String information) {
        this.information = information;
    }

    public void information() {
        System.out.println(information);
    }
}