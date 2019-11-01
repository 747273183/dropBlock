package com.example.dropblock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewAnimationUtils;

import com.example.dropblock.game.RedBlock;

public class GameSurfaceView extends SurfaceView implements Runnable {

    private static final String TAG = "GameSurfaceView";
    private Thread mThread;
    private boolean isRunning;
    private RedBlock mRedBlock;
    private Bitmap mRedBlockBm;


    private Paint mPaint;


    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                isRunning = true;
                mThread = new Thread(GameSurfaceView.this);
                mThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    isRunning=false;
            }
        });

        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);


        initPaint();
        initBitMap();

    }

    private void initBitMap() {
        mRedBlockBm= BitmapFactory.decodeResource(getResources(),R.drawable.red_block);
    }

    private void initPaint() {
        mPaint=new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.DKGRAY);
    }




    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        createRedBlock(w);
    }

    //定义一个接口用来向外返回RedBlock
    public interface OnRedBlockCreated
    {
        void setRedBlock(RedBlock redBlock,float gameHeight);
    }

    private OnRedBlockCreated redBlockCreated;
    public void setRedBlockCreated(OnRedBlockCreated redBlockCreated) {
        this.redBlockCreated = redBlockCreated;
    }

    private void createRedBlock(int w) {
        mRedBlock=new RedBlock(getContext());
        mRedBlock.setmWidth( (w*1.0f/6));
        mRedBlock.setmHeight( (w*1.0f/6));
        mRedBlock.setmBitMap(mRedBlockBm);
        Log.d(TAG, "getWidth: "+getWidth()+",w="+w);
        mRedBlock.setmGameWidth(getWidth());
        mRedBlock.setmGameHeight(getHeight());

        redBlockCreated.setRedBlock(mRedBlock,getHeight());

    }

    @Override
    public void run() {
        while (isRunning) {
            Canvas canvas = null;
            try {
                canvas = getHolder().lockCanvas();
                if (canvas != null) {

                    drawOuterBorder(canvas);//画外边框
                    drawRedBlock(canvas);

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void drawOuterBorder(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
    }

    private void drawRedBlock(Canvas canvas) {
        mRedBlock.draw(canvas);
    }


}
