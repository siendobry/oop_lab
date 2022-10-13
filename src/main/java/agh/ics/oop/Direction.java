package agh.ics.oop;

public class Direction {

    public enum Directions {
        f("Zwierzak idzie do przodu"),
        b("Zwierzak idzie do tylu"),
        r("Zwierzak skreca w prawo"),
        l("Zwierzak skreca w lewo");

        private String information;

        Directions(String information) {
            this.information = information;
        }

        public void information() {
            System.out.println(information);
        }
    }

}
