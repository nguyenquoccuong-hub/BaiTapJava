package Bai4_4;

public class Point {
    private float x = 0.0f;
    private float y = 0.0f;
    public Point( float x, float y){
        this.x = x;
        this.y = y;
    }
    public Point(){}
    public float getX(){
        return x;
    }
    void setX(float x){
        this.x = x;
    }
    public float getY(){
        return y;
    }
    void setY(float y){
        this.y = y;
    }
    void setXY(float x, float y){
        this.x = x;
        this.y = y;
    }
    public float[] getXY(){
        return new float[]{x,y};
    }
    public String toString(){
        return "("+x+","+y+")";
    }

}
