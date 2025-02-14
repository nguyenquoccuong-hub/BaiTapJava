package Bai6_2;

public class Rectangle_1 implements Geometricobject {
    private double width;
    private double length;
    public Rectangle_1(double width,double length){
        this.width = width;
        this.length = length;
    }
    public String toString(){
        return "Rectangle[width="+this.width+", length="+this.length+"]";
    }
    public double getArea(){
        return width*length;
    }
    public double getPerimeter(){
        return (width+length)*2;
    }
}
