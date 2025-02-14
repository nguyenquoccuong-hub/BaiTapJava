package Bai6_4;

import Bai6_3.Movable;

public class Test_Movavle_MovablePoint_MovableCircle {
    public static void main(String[] args) {
        Movable m1 = new MovableCircle(1,2,3,4,5);
        m1.moveUp();
        m1.moveRight();
        System.out.println(m1);
    }
}
