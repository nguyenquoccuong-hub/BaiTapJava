package Buoi_4.Bai5;

public class Test {
    public static void main(String[] args) {
        String file = "src/Buoi_4/Bai5/log";
        LogFile logFile = new LogFile(file);

        logFile.start();

        logFile.log("Cường đẹp trai");
        logFile.log("nhất thế giới");
        logFile.log("hahaha");
        logFile.log("hihi");
        logFile.log("huhu");
        logFile.log("End");
        logFile.log("hoho");

        logFile.stopLog();
    }
}
