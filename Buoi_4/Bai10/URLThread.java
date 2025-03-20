package Buoi_4.Bai10;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLThread extends Thread{
    private String urlContent;
    private String file;

    public URLThread(String url, String file){
        this.urlContent = url;
        this.file = file;
    }

    @Override
    public void run() {
        try{
            URL url = new URL(urlContent);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                String line;
                 while((line = reader.readLine()) != null){
                     writer.write(line);
                     writer.newLine();
                 }
                System.out.println("Download hoàn tất : "+file);
            }

        }catch (IOException e){
            System.out.println("Lỗi khi tải file : "+e.getMessage());
        }
    }
}
