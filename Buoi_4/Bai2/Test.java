package Buoi_4.Bai2;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String file = "/Users/cuongnguyen/Documents/Java nâng cao/BaiTapTrenLop/src/Buoi_4/Bai2/DoubleThread";
        System.out.println("Nội dung Thread 1 : ");
        String thread1 = sc.nextLine();
        System.out.println("Nội dung Thread 2 : ");
        String thread2 = sc.nextLine();

        ThreadWriteFile writeFile1 = new ThreadWriteFile(file, thread1);
        ThreadWriteFile writeFile2 = new ThreadWriteFile(file, thread2);

        writeFile1.start();
        writeFile2.start();
    }
}
