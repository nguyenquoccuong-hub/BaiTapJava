package Bai6_5;

import Bai6_2.Circle_1;
import Bai6_2.Geometricobject;

public class Test_GeometricObiect_Circle_Resizable_ResizableCircle {
    public static void main(String[] args) {
        Geometricobject c1 = new Circle_1(3);
        System.out.println(c1);
        System.out.println(c1.getPerimeter());
        System.out.println(c1.getArea());

        Resizable resizableCircle = new ResizableCircle(5);
        System.out.println(resizableCircle);
        resizableCircle.resize(50);
        System.out.println(resizableCircle);

    }

}
