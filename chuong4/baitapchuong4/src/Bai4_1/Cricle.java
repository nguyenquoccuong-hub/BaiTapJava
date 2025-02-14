package Bai4_1;

public class Cricle {
    private double radius = 1;
    private String color = "red";
    public Cricle(){};
    public Cricle(double radius){
        this.radius = radius;
    }
    public Cricle(double radius, String color){
        this.radius = radius;
        this.color = color;
    }
    public double getRadius(){
        return radius;
    }
    public void setRadius(double radius){
        this.radius =radius;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }
    public double getArea(){
        return radius*radius*3.14;
    }
    public String toString(){
        return "Circle[ radius = "+radius+",color = "+color+"]";
    }
}
