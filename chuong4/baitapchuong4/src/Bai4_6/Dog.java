package Bai4_6;

public class Dog extends Mammal {
    public Dog(String name){
        super(name);
    }
    public void greets(){
        System.out.println("woof");
    }
    public void greets(Dog another){
        System.out.println("woooof");
    }
    public String toString(){
        return "Dog["+super.toString()+"]";
    }
}
