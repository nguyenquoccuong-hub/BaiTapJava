package Bai4_1;

public class TestCylinder {
    public static void main(String[] args) {
        Cylinder c1 = new Cylinder();
        System.out.println("radius="+c1.getRadius()
                                      +", height="+c1.getHeight()
                                      +", base area="+c1.getArea()
                                      +", volume="+c1.getVolume());
        Cylinder c2 = new Cylinder(10);
        System.out.println("radius="+c2.getRadius()
                +", height="+c2.getHeight()
                +", base area="+c2.getArea()
                +", volume="+c2.getVolume());
        Cylinder c3 = new Cylinder(2.0, 10.0,"red");
        System.out.println(" radius=" + c3.getRadius()
                + ", height=" + c3.getHeight()
                + ", base area=" + c3.getArea()
                + ", volume=" + c3.getVolume()
                + "color : "+c3.getColor());

    }
}
