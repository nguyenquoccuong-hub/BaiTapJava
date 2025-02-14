package Bai4_3;

public class Test_Point2D_3D {
    public static void main(String[] args) {
        Point2D p1 = new Point2D(1,1);
        System.out.println(p1);
        p1.setX(2);
        p1.setY(3);
        System.out.println(p1);
        System.out.println(p1.getX());
        System.out.println(p1.getY());
        p1.setXY(2,3);
        System.out.println(p1.getXY()[0]);
        System.out.println(p1.getXY()[1]);
        Point3D p2 = new Point3D(1,1,1);
        System.out.println(p2);
        p2.setZ(3);
        System.out.println(p2);

    }
}
