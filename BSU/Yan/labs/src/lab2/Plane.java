package lab2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Plane {
    private String name;
    private Wings wings;
    private Engine engine;
    private LandingGear gear;
    private List<String> path;

    public void fly(){
        System.out.println("Plane <"+name+"> getting of:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
        for (int i = 0; i < path.size(); i++) {
            System.out.println(LocalDateTime.now().plusHours(i).format(formatter)+": got to "+path.get(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Plane <"+name+"> finished:");
    }

    public Plane(String name, Wings wings, Engine engine, LandingGear gear, List<String> path) {
        this.name = name;
        this.wings = wings;
        this.engine = engine;
        this.gear = gear;
        this.path = path;
    }

    @Override
    public String toString() {
        String text = "Plane with name <"+name+">, path: ";
        for (String dest: path){
            text+=dest+" - ";
        }

        if (path.size()!=0){text = text.substring(0, text.length()-3);}
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return Objects.equals(name, plane.name) &&
                Objects.equals(wings, plane.wings) &&
                Objects.equals(engine, plane.engine) &&
                Objects.equals(gear, plane.gear) &&
                Objects.equals(path, plane.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, wings, engine, gear, path);
    }

    public Wings getWings() {
        return wings;
    }

    public void setWings(Wings wings) {
        this.wings = wings;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public LandingGear getGear() {
        return gear;
    }

    public void setGear(LandingGear gear) {
        this.gear = gear;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
