package Bai6_2;

public class Test_Rectangle_Circle {
    public static void main(String[] args) {
        Geometricobject g1 = new Rectangle_1(2,3);
        System.out.println(g1);
        System.out.println(g1.getArea());
        System.out.println(g1.getPerimeter());
        Geometricobject g2 = new Circle_1(4);
        System.out.println(g2);
        System.out.println(g2.getArea());
        System.out.println(g2.getPerimeter());
    }
}
