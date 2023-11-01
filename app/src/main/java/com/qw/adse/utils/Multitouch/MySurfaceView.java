package com.qw.adse.utils.Multitouch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.qw.adse.ui.duodianchukong.MultitouchActivity;

import java.util.ArrayList;
import java.util.Random;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private MultitouchActivity activity;
    private Paint paint;
    private Paint paint1;
    private Paint paint2;
    private ArrayList<BNPoint> pointArrayList  = new ArrayList<>();

    public  MySurfaceView(MultitouchActivity activity){
        super(activity);

        this.activity = activity;
        this.getHolder().addCallback(this);

        paint = new Paint();
        paint.setAntiAlias(true);

        paint1 = new Paint();
        paint1.setAntiAlias(true);

        paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint1.setTextSize(35);
    }

    public void onDraw(Canvas canvas,int index){
        canvas.drawColor(Color.WHITE);

        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        paint1.setColor(Color.BLACK);
        paint1.setStrokeWidth(100);
        paint1.setStyle(Paint.Style.STROKE);

        paint2.setStrokeWidth(20);

        for (BNPoint point:pointArrayList){
            point.drawSelf(paint,paint1,paint2,canvas,index);
        }
    }

    public int[] getColor(){
        int[] result = new int[4];

        result[0] = (int)(Math.random()*255);
        result[1] = (int)(Math.random()*255);
        result[2] = (int)(Math.random()*255);
        result[3] = (int)(Math.random()*255);

        return result;
    }
    public int  setRefresh(){
        Random random = new Random();


        if (pointArrayList.size()!=0){
            for (int i = 0; i <10;i++){
                int n = random.nextInt(pointArrayList.size());
                SystemClock.sleep(100);

                repaint(pointArrayList.get(n).getId());
            }
            int n = random.nextInt(pointArrayList.size());

            repaint(pointArrayList.get(n).getId());

            return pointArrayList.get(n).getId();
        }
        return 999;
    }


    public void repaint(int index){
        SurfaceHolder holder = this.getHolder();
        Canvas canvas = holder.lockCanvas();
        try {
            synchronized (holder){
                onDraw(canvas,index);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(canvas!=null){
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event){

        int actionMasked = event.getActionMasked();
        int id = (event.getAction()&MotionEvent.ACTION_POINTER_ID_MASK) >>> MotionEvent.ACTION_POINTER_ID_SHIFT;

        switch (actionMasked){
            case MotionEvent.ACTION_DOWN:
                pointArrayList.add(id,new BNPoint(event.getX(id), event.getY(id),getColor(),id));

                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                pointArrayList.add(id,new BNPoint(event.getX(id),event.getY(id),getColor(),id));

                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0;i<pointArrayList.size();i++){
                    try {

                        float x = event.getX(i);
                        float y = event.getY(i);
                        pointArrayList.get(i).setLocation(x,y);
                    }catch (Exception e){
                        Log.d("MySurfaceView", "onTouchEvent: point.id=" + pointArrayList.get(i).id);
                        e.printStackTrace();
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                pointArrayList.clear();
                break;
            case MotionEvent.ACTION_POINTER_UP:
                pointArrayList.remove(id);
                break;
        }
        repaint(999);
        return true;
    }

    public void surfaceCreated(SurfaceHolder holder){

    }

    public void surfaceChanged(SurfaceHolder holder,int arg1,int arg2,int arg3){
        this.repaint(999);
    }

    public void surfaceDestroyed(SurfaceHolder holder){

    }
}