package Buoi_3.Bai4;

public class Test {
    public static void main(String[] args) {
        int[] num = {10, 20, 30};
        String file = "src/Buoi_3/Bai4/Number.txt";

        DataStream.writeFile(file, num);
        DataStream.readFile(file);
    }
}
