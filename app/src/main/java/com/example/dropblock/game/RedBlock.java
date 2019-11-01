package com.example.dropblock.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

public class RedBlock extends View  {
    private static final String TAG = "RedBlock";

    private float mX;
    private float mY;

    private float mWidth;
    private float mHeight;
    private float mGameWidth;
    private float mGameHeight;
    private Bitmap mBitMap;

    private RectF dstRectF;

    public RedBlock(Context context) {
        super(context);
        dstRectF=new RectF();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
        canvas.translate(mX,mY);
        dstRectF.set(0,0,mWidth,mHeight);
        canvas.drawBitmap(mBitMap,null,dstRectF,null);
        canvas.restore();


    }

    public float getmX() {
        return mX;
    }

    public void setmX(float mX) {
        if (mX<0)
        {
            mX=0;
        }
        if (mX>mGameWidth-mWidth)
        {

            mX=mGameWidth-mWidth;
        }

        Log.d(TAG, "mGameWidth-mWidth: "+(mGameWidth-mWidth)+",mX="+ mX);
        this.mX = mX;
    }

    public float getmY() {
        return mY;
    }

    public void setmY(float mY) {
        if (mY<0)
        {
            mY=0;
        }
        if (mY>mGameHeight-mHeight)
        {
            mY=mGameHeight-mHeight;
        }

        Log.d(TAG, "mGameHeight-mHeight: "+(mGameHeight-mHeight)+",mY="+ mY);
        this.mY = mY;
    }

    public float getmWidth() {
        return mWidth;
    }

    public void setmWidth(float mWidth) {
        this.mWidth = mWidth;
    }

    public float getmHeight() {
        return mHeight;
    }

    public void setmHeight(float mHeight) {
        this.mHeight = mHeight;
    }

    public void setmBitMap(Bitmap mBitMap) {
        this.mBitMap = mBitMap;
    }

    public float getmGameWidth() {
        return mGameWidth;
    }

    public void setmGameWidth(float mGameWidth) {
        this.mGameWidth = mGameWidth;
    }

    public float getmGameHeight() {
        return mGameHeight;
    }

    public void setmGameHeight(float mGameHeight) {
        this.mGameHeight = mGameHeight;
    }





}
