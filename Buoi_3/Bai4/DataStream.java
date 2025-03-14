package Buoi_3.Bai4;

import java.io.*;

public class DataStream {
    public static void writeFile(String file, int[] numbers) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            for (int num : numbers) {
                dos.writeUTF(String.valueOf(num));
            }
            System.out.println("Ghi file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    public static void readFile(String file) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            System.out.println("Các số đọc từ file:");
            while (dis.available() > 0) {
                String num = dis.readUTF();
                System.out.print(num + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }


}
