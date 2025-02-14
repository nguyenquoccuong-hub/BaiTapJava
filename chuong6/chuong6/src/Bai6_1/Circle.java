package Bai6_1;

public class Circle extends Shape {
    protected double radius=1.0;
    public Circle(){}
    public Circle(double radius){
        this.radius = radius;
    }
    public Circle(double radius, String color, boolean filled){
        super(color, filled);
    }
    public double getRadius(){
        return radius;
    }
    public void setRadius(double radius){
        this.radius = radius;
    }
    public double getArea(){
        return radius*radius*Math.PI;
    }
    public double getPerimeter(){
        return 2*Math.PI*radius;
    }
    public String toString(){
        return "Bai6_1.Circle[Bai6_1.Shape[color="+
                this.getColor()+
                ", filled="+
                this.isfilled()+
                "], radius="+
                this.getRadius()+
                "]";
    }

}
