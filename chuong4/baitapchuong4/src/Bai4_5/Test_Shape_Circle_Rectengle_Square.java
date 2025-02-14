package Bai4_5;

public class Test_Shape_Circle_Rectengle_Square {
    public static void main(String[] args) {
        Shape s1 = new Shape();
        System.out.println(s1);
        s1.setColor("white");
        System.out.println(s1);
        Circle_1 c1 = new Circle_1(3,"blue",true);
        System.out.println(c1);
        Rectangle r1 = new Rectangle(1,1,"red", true);
        System.out.println(r1);
        r1.setLength(4);
        r1.setWidth(4);
        System.out.println(r1);
        System.out.println("area is : "+r1.getArea());
        Square s2 = new Square();
        System.out.println(s2);

    }
}
