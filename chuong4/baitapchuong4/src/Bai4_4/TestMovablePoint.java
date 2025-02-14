package Bai4_4;

public class TestMovablePoint {
    public static void main(String[] args) {
        Point p1 = new Point(4,6);
        System.out.println(p1);
        p1.setX(3);
        p1.setY(4);
        System.out.println(p1);
        MovablePoint mp1 = new MovablePoint(7,8);
        mp1.setX(3);
        mp1.setY(4);
        System.out.println(mp1);
        System.out.println(mp1.move());
    }
}
