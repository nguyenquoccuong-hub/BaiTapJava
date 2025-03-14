package Buoi_3.Bai3;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String file = "src/Buoi_3/bai3/soDong";
        System.out.println("Ghi nội dung file : ");
        StringBuilder content = new StringBuilder();
        while (true){
            String line = sc.nextLine();
            if("exit".equalsIgnoreCase(line)){
                break;
            }
            content.append(line).append("\n");
        }
        sc.close();
        InputFile.writeFile(file, content.toString());
        int count = InputFile.readFile(file);
        System.out.println("số dòng trong file : "+count);


    }
}
