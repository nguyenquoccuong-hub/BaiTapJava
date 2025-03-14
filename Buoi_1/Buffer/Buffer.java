package Buoi_1.Buffer;

import java.util.ArrayList;

public class Buffer {
    private ArrayList<Integer> buffer;
    private int size;

    public Buffer(int size){
        this.size = size;
        buffer = new ArrayList<>();
    }

    public synchronized void produce(int item){
        try{
            while (buffer.size() == size){
                System.out.println("Buffer đã bị đầy, đợi");
                wait();
            }
            buffer.add(item);
            System.out.println("produce đã sản xuất : item "+item);
            notify();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
