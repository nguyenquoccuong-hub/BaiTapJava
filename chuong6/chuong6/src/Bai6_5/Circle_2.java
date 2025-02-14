package Bai6_5;

public class Circle_2 implements GeometricObject_1{
    protected double radius;

    public Circle_2(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return radius*radius*Math.PI;
    }

    @Override
    public double getPerimater() {
        return 2*radius*Math.PI;
    }

    @Override
    public String toString() {
        return "Circle_2{" +
                "radius=" + radius +
                '}';
    }
}
