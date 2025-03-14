package Buoi_3.Bai5;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        File directory = new File("MyFolder");

        if(!directory.exists()){
            directory.mkdir();
        }

        File file = new File(directory, "Output.txt");
        if(!file.exists()){
            file.createNewFile();

        }


    }
}
