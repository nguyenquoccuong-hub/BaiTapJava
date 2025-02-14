package Bai6_2;

public class Circle_1 implements Geometricobject {
    protected double radius;
    public Circle_1(double radius){
        this.radius =radius;
    }
    public String toString(){
        return "Circle[radius="+this.radius+"]";
    }
    public double getArea(){
        return radius*radius*Math.PI;
    }
    public double getPerimeter(){
        return 2*radius*Math.PI;
    }
}
