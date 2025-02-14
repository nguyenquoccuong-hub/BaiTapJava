package Bai4_6;

public class Cat extends Mammal {
    public Cat(String name){
        super(name);
    }
    public void greets(){
        System.out.println("meow");
    }
    public String toString(){
        return "cat["+super.toString()+"]";
    }

}
