package Bai6_5;

public class ResizableCircle extends Circle_2 implements Resizable {
    public ResizableCircle(double radius){
        super(radius);
    }

    public String toString(){
        return "ResizableCircle[Bai6_1.Circle[radius = "+radius+"]]";
    }


    @Override
    public void resize(int percent) {
        radius = radius * percent/100;
    }
}
