package Bai4_6;

public class Test_Animal_Mammal_Cat_Dog {
    public static void main(String[] args) {
        Animal a1 = new Animal("cuong");
        System.out.println(a1);
        Mammal m1 = new Mammal("suc vat");
        System.out.println(m1);
        Cat c1 = new Cat("mimi");
        System.out.println(c1);
        c1.greets();
        Dog d1 = new Dog("papa");
        System.out.println(d1);
        d1.greets();
        d1.greets();

    }
}
