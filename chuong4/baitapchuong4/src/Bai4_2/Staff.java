package Bai4_2;

public class Staff extends Person {
    private String school;
    private double pay;
    public Staff(String name, String address, String school, double pay){
        this.school = school;
        this.pay = pay;
        super(name, address);
    }
    public String getSchool(){
        return school;
    }
    public void setSchool(String school){
        this.school = school;
    }
    public double getPay(){
        return pay;
    }
    public void setPay(double pay){
        this.pay = pay;
    }
    public String toString(){
        return "Staff[Person[name = "+this.getName()+", address = "+this.getAddress()+"],school = "+this.getSchool()+", pay = "+this.getPay()+"]";
    }

}
