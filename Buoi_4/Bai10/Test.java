package Buoi_4.Bai10;

import java.util.SplittableRandom;

public class Test {
    public static void main(String[] args) {
        String url = "https://en.wikipedia.org/wiki/URL";
        String file = "src/Buoi_4/Bai10/ContentFileDownload.txt";

        URLThread thread = new URLThread(url, file);
        thread.start();
    }
}
