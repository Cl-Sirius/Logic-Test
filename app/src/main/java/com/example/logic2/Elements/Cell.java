package com.example.logic2.Elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Cell {
    public int type = 0;
    public int changeable=0;
    public float sx;
    public float sy;
    public float fx;
    public float fy;
    public Paint paint;
    public double dp;
    public Paint p2 = new Paint();
    public Paint fpaint = new Paint();

    public Cell(float sx, float sy, float fx, float fy, Canvas canvas, Paint paint,double dp1) {
        this.sx = sx;
        dp=dp1;
        this.sy = sy;
        this.fx = fx;
        this.fy = fy;
        this.paint = paint;
        {
            p2.setStyle(Paint.Style.FILL);
        }
        canvas.drawRect(sx, sy, fx, fy, paint);
    }

    void color(int id) {
        switch (id) {
            case 0:
            case 1:
                this.paint.setColor(Color.rgb(215, 0, 0));
                break;
            case 6:
            case 7:
            case 8:
                this.paint.setColor(Color.rgb(115, 0, 115));
                break;
            case 2:
            case 3:
                this.paint.setColor(Color.rgb(0, 215, 0));
                break;
            case 4:
            case 5:
                this.paint.setColor(Color.rgb(0, 215, 215));
                break;
            default:
                this.paint.setColor(Color.WHITE);break;
        }
    }

    public void refresh(Canvas canvas, int id) {
        switch (this.type) {
            case 0:
                this.paint.setColor(Color.rgb(140, 140, 140));
                this.p2.setColor(Color.rgb(140, 140, 140));
                break;
            case 1:
                    color(id);
                this.p2.setColor(Color.rgb(140, 140, 140));
                break;
            case 2:
                color(id);
                this.p2.setColor(this.paint.getColor());
                break;
            case 8:
                color(id);
                this.p2.setColor(this.fpaint.getColor());
                break;
            case 3:
                this.p2.setColor(Color.argb(186, 0, 0, 0));
                color(id);
                break;
            case 9:
                color(id);
                this.p2.setColor(Color.argb(186, 0, 0, 0));
                break;
            case 4:
                this.paint.setColor(Color.rgb(140, 140, 140));
                this.p2.setColor(Color.rgb(215, 0, 0));
                break;
            case 5:
                this.paint.setColor(Color.rgb(140, 140, 140));
                this.p2.setColor(Color.rgb(0, 215, 0));
                break;
            case 6:
                this.paint.setColor(Color.rgb(140, 140, 140));
                this.p2.setColor(Color.rgb(0, 215, 215));
                break;
            case 7:
                this.paint.setColor(Color.rgb(140, 140, 140));
                this.p2.setColor(Color.rgb(115, 0, 115));
                break;
        }
        if(this.changeable==0){
        canvas.drawRect(sx, sy, fx, fy, this.paint);}else{
            this.fpaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(sx, sy, fx, fy, this.fpaint);}
        canvas.drawCircle(this.sx + canvas.getWidth() / 22, this.sy + canvas.getWidth() / 22, (float) (8*dp), this.p2);
    }
}