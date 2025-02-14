package Bai4_5;

public class Square extends Rectangle {
    public Square(){
        super();
    }
    public Square(double side){
         super(side,side);
    }
    public Square(double side, String color, boolean filled){
        super(side,side,color,filled);
    }
    public double getSide(){
        return getSide();
    }
    public void setSide(double side){
        setWidth(side);
        setLenghth(side);
    }
    public void setWidth(double side){
        setWidth(side);

    }
    public void setLenghth(double side){
       setLength(side);
    }
    public String toString(){
        return "Square[Bai4_5.Rectangle[Bai4_5.Shape[Color="+this.getColor()+", filled="+this.isFilled()+"]"+", width="+this.getWidth()+", length="+ this.getLength()+"]]";
    }


}
