package Bai4_2;

public class testPerson_Student_Staff {
    public static void main(String[] args) {
        Person p1 = new Person("nguyen quoc cuong","ha tinh" );
        System.out.println(p1);
        p1.setAddress("huong khe-ha tinh");
        System.out.println(p1);
        Student s1 = new Student("nguyen quoc cuong", "ha tinh", "cntt", 2006, 10000000 );
        System.out.println(s1);
        s1.setProgram("IT");
        s1.setYear(2000);
        s1.setFee(100);
        s1.setAddress("huong khe");
        System.out.println(s1);
        System.out.println("program is : "+s1.getProgram());
        System.out.println("year is : "+s1.getYear());
        System.out.println("Fee is : "+s1.getFee());
        Staff s2 = new Staff("nguyen quoc cuong", "phu phong", "ke toan",1000);
        System.out.println(s2);
        s2.setPay(5000);
        s2.setSchool("chu van an");
        System.out.println(s2);

    }
}
