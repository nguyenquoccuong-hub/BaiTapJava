package Buoi_4.Bai1;

public class Test {
    public static void main(String[] args) {
        String file = "/Users/cuongnguyen/Documents/Java nâng cao/BaiTapTrenLop/src/Buoi_4/Bai1/Test.txt";
        FileReadThread thread = new FileReadThread(file);
        thread.start();
    }
}
