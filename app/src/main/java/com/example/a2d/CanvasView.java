package com.example.a2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasView extends View {

    //Handler handler = new Handler();
    final int  SPRITE_HEIGHT = 64;
    final int  SPRITE_WIDTH = 64;
    Handler handler;
    private Bitmap spriteSheet;
    int row,col;

    private Runnable animationThread = new Runnable(){

        @Override
        public void run() {
            invalidate();
        }
    };

    public CanvasView(Context context) {
        super(context);
        init(null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {


        spriteSheet = BitmapFactory.decodeResource(getResources(),R.drawable.image1);
        spriteSheet = Bitmap.createScaledBitmap(spriteSheet,SPRITE_WIDTH*4,SPRITE_HEIGHT*4,false);
        handler = new Handler();
        col=0 ;
        row= 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //change the background
        canvas.drawColor(Color.GRAY);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);

        Rect rect1= new Rect(10,50,30,20);
        canvas.drawRect(rect1,paint);

        Paint paintCircle = new Paint();
        paint.setColor(Color.RED);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(350,250,70,paint);
        canvas.drawText("This is a test",500,500,paint);

        drawAnimationFrame(canvas);
        handler.postDelayed(animationThread,60);
        nextFrame();



    }

    private void nextFrame() {
        col++;
        if(col>=4){
            row++;
            col=0;
        }
        if (row>=4){
            row=0;
        }
    }

    private void drawAnimationFrame(Canvas canvas) {
//canvas.drawBitmap(spriteSheet,);
        int left = col*SPRITE_WIDTH; // SPRITE_WIDTH =64
        int top = row *SPRITE_HEIGHT;// SPRITE_Height=64
        Rect source = new Rect(left,top,left+SPRITE_WIDTH,top+SPRITE_HEIGHT);
        RectF dest = new RectF(200,1200,200+SPRITE_WIDTH*2,1200+SPRITE_WIDTH*2);

        Paint paintCircle = new Paint();
        paintCircle.setColor(Color.RED);

        canvas.drawRect(source,paintCircle);
        canvas.drawRect(dest,paintCircle);


        canvas.drawBitmap(spriteSheet,source,dest,null);

    }


}
