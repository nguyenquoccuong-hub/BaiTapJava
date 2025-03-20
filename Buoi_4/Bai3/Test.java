package Buoi_4.Bai3;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String file = "src/Buoi_4/Bai3/File1";
        String fileCopy = "src/Buoi_4/Bai3/File2";
        System.out.println("Ghi nội dung vào file 1 : ");
        String content = sc.nextLine();

        ThreadCopyFile.writeFile(file, content);


        ThreadCopyFile threadCopyFile = new ThreadCopyFile(file, fileCopy, content);
        threadCopyFile.start();
    }
}
