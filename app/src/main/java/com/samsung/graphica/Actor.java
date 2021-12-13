package com.samsung.graphica;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Actor {
    float x, y;
    Bitmap bitmap;
    Paint paint;

    public Actor(float x, float y, Bitmap bitmap, Paint paint) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
        this.paint = paint;
    }

    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public boolean bound(float x, float y) {
        if (x > this.x && x < this.x + bitmap.getWidth()) {
            if (y > this.y && y < this.y + bitmap.getHeight()) {
                return true;
            }
        }
        return false;
    }
}
