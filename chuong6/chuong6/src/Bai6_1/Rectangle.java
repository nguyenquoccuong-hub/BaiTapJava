package Bai6_1;

public class Rectangle extends Shape {
    protected double width = 1.0;
    protected double length = 1.0;
    public Rectangle(){}
    public Rectangle(double width, double length){
        this.width = width;
        this.length = length;
    }
    public Rectangle(double width, double length, String color, boolean filled){
        super(color, filled);
    }
    public double getWidth(){
        return width;
    }
    public void setWidth(double width){
        this.width = width;
    }
    public double getLength(){
        return length;
    }
    public void setLength(double length){
        this.length = length;
    }
    public double getArea(){
        return width*length;
    }
    public double getPerimeter(){
        return (width+length)*2;
    }
    public String toString(){
        return "Bai6_1.Rectangle[Bai6_1.Shape[color="+
                this.getColor()+
                ", filled="+
                this.isfilled()+
                "], width="+
                this.getWidth()+
                ", length="+
                this.getLength()+
                "]]";
    }
}
