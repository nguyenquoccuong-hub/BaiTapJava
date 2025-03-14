package Buoi_1.ThoiGian;

import java.time.LocalTime;

public class inThoiGian {
    public void thoiGian(){
        while (true) {
            LocalTime now = LocalTime.now();
            String time = String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
            System.out.println("Giờ hiện tại: " + time);

            try {
                Thread.sleep(1000); // Chờ 1 giây
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
