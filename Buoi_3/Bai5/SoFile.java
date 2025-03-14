package Buoi_3.Bai5;

import java.io.File;

public class SoFile {
    public static void listFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null && files.length > 0) {
                System.out.println("Danh sách các tệp trong thư mục " + directoryPath + ":");
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    }
                }
            } else {
                System.out.println("Thư mục trống hoặc không có tệp nào.");
            }
        } else {
            System.out.println("Đường dẫn không hợp lệ hoặc không phải là thư mục.");
        }
    }

}

