package Buoi_4.Bai9;

public class Test {
    public static void main(String[] args) {
        String kiTu1 = "src/Buoi_4/Bai9/KiTu1.txt";  // Đổi tên file theo nhu cầu
        String kiTu2 = "src/Buoi_4/Bai9/KiTu2.txt";
        CharCountThread thread = new CharCountThread(kiTu1, kiTu2);
        thread.start();
    }
}
