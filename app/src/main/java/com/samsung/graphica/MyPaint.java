package com.samsung.graphica;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MyPaint extends View {

    int wight, height;
    Paint paint;
    Bitmap bitmapTree, bitmapToy, bitmapBasket;
    Toy button, activeToy;
    Basket basket;
    List<Toy> toys = new ArrayList<>();

    public MyPaint(Context context) {
        super(context);
        wight = MainActivity.screenWidth;
        height = MainActivity.screenHeight;
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.violet));
        bitmapTree = BitmapFactory.decodeResource(getResources(), R.drawable.elka);
        bitmapTree = Bitmap.createScaledBitmap(bitmapTree, wight, height, true);
        bitmapToy = BitmapFactory.decodeResource(getResources(), R.drawable.igrushka);
        bitmapToy = Bitmap.createScaledBitmap(bitmapToy, 100, 100, true);
        bitmapBasket = BitmapFactory.decodeResource(getResources(), R.drawable.basket);
        bitmapBasket = Bitmap.createScaledBitmap(bitmapBasket, 100, 100, true);
        basket = new Basket(wight - 120, height - 100, bitmapBasket, paint);
        button = new Toy(100, height - 100, bitmapToy, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmapTree, 0, 0, paint);
        button.onDraw(canvas);
        basket.onDraw(canvas);
        for (int i = 0; i < toys.size(); i++){
            toys.get(i).onDraw(canvas);
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:{
                for (int i = 0; i < toys.size(); i++){
                    if (toys.get(i).bound(event.getX(), event.getY())){
                        activeToy = toys.get(i);
                        return true;
                    }
                }
                if (button.bound(event.getX(), event.getY())) {
                    activeToy = new Toy(event.getX() - bitmapToy.getWidth()/2,
                            event.getY() - bitmapToy.getHeight()/2, bitmapToy, paint);
                    toys.add(activeToy);
                    invalidate();
                }
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                if (activeToy != null){
                    activeToy.x = event.getX() - bitmapToy.getWidth()/2;
                    activeToy.y = event.getY() - bitmapToy.getHeight()/2;
                    invalidate();
                }
                break;
            }
            case MotionEvent.ACTION_UP:{
                if (basket.bound(event.getX(), event.getY()) && activeToy != null){
                    toys.remove(activeToy);
                }
                activeToy = null;
                invalidate();
            }
        }
        return true;
    }
}
