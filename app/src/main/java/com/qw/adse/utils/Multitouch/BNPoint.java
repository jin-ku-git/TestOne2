package com.qw.adse.utils.Multitouch;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BNPoint {
    float x;
    float y;
    int id;
    int[] color;
    static final float RADIS = 200;


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    Bitmap mBitmap;

    public BNPoint(float x,float y,int[] color,int id){
        this.x = x;
        this.y = y;
        this.color = color;
        this.id = id;
    }

    public void setLocation(float x,float y){
        this.x = x;
        this.y = y;
    }

    public void drawSelf(Paint paint, Paint paint1,Paint paint2, Canvas canvas,int index){
        paint2.setARGB(200,color[1],color[2],color[3]);
        if (index ==id){

            canvas.drawCircle(x,y,RADIS+10,paint2);
        }

        paint.setARGB(180,color[1],color[2],color[3]);
        canvas.drawCircle(x,y,RADIS-10,paint);

        paint.setARGB(150,color[1],color[2],color[3]);
        canvas.drawCircle(x,y,RADIS-50,paint);


        paint1.setARGB(200,color[1],color[2],color[3]);
        canvas.drawCircle(x,y,RADIS-150,paint1);

        paint2.setTextSize(80);
        canvas.drawText(id+"",x-20,y-220,paint2);

    }
}