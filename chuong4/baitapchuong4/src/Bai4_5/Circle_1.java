package Bai4_5;

public class Circle_1 extends Shape {
    private double radius = 1.0;
    public Circle_1(){}
    public Circle_1(double radius){
        this.radius =radius;
    }
    public Circle_1(double radius, String color, boolean filled){
        super(color, filled);
        this.radius=radius;

    }
    public double getRadius(){
        return radius;
    }
    public void setRadius(double radius){
        this.radius = radius;
    }
    public double getArea(){
        return radius*radius*3.14;
    }
    public double getPerimater(){
        return 2*radius*3.14;
    }
    public String toString(){
        return "Circle[Shape[color = "+this.getColor()+", filled ="+this.isFilled()+",radius = "+this.getRadius()+"]";
    }

}
