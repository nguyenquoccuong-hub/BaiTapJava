package Bai6_1;

public class Square extends Rectangle {
    public Square(){}
    public Square(double side){
        super(side,side);
    }
    public Square(double side, String color, boolean filled){
        super(side, side,color,filled);
    }
    public double getSide(){
        return getWidth();
    }
    public void setSide(double side){
        setWidth(side);
        setLength(side);
    }
    public void setWidth(double side){
        super.setWidth(side);

    }
    public void setLenghth(double side){
        super.setLength(side);
    }
    public String toString(){
        return "Square[Bai6_1.Rectangle[Bai6_1.Shape[Color="+this.getColor()+", filled="+this.isfilled()+"]"+", width="+this.getWidth()+", length="+ this.getLength()+"]]";
    }


}
