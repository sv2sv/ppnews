package com.android.ppnews.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by wangyao on 3/3/17.
 */

public class PPCircleImageView extends ImageView {

    private Paint mPaint;
    private int min;

    public PPCircleImageView(Context context) {
        super(context);
        initPaint();
    }

    public PPCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public PPCircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public PPCircleImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
      //  super.onDraw(canvas);
         min = getWidth() > getHeight()? getHeight()/2:getWidth()/2;

        Drawable drawable = getDrawable();
        if(drawable instanceof ColorDrawable) {
            mPaint.setColor(((ColorDrawable) drawable).getColor());
            canvas.drawCircle(min,min,min,mPaint);
        }else if(drawable instanceof BitmapDrawable){
            Bitmap src = ((BitmapDrawable) drawable).getBitmap();
            Bitmap dst = createRoundBitmap(src);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
          //  mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawBitmap(dst,0,0,mPaint);


        }



    }


    public void initPaint(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

    }

    public Bitmap createRoundBitmap(Bitmap bitmap){

        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(bitmap1);
        canvas.drawColor(Color.BLACK);
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(min,min,min,mPaint);
        return bitmap1;
    }

}
