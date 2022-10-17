package com.example.logic2.Elements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

public class Part {
    public int id;
    public static double dp;
    public int placex, placey;
    public boolean used;
    public boolean taken = false;
    public Paint paint = new Paint();
    public Paint cpaint = new Paint();
    {
        cpaint.setStyle(Paint.Style.FILL);
        cpaint.setColor(Color.BLACK);
    }
    public int tcount = 0;
    public int reverse = 0;
    public float k;
    public int idk = 0;
    public float sx, sy, fx, fy;
    public List<List<List<Integer>>> details;

    public Part(int id, int placex, int placey, Canvas canvas, List<List<List<Integer>>> details, double dp1) {
        this.id = id;
        dp = dp1;
        this.placex = placex;
        this.placey = placey;
        this.used=false;
        this.idk = 0;
        this.details = details;
        k = canvas.getWidth() / 8;
        this.sx = (float) (this.placex * 2 * k + 1.25 * k);
        this.sy = (float) (this.placey * 2 * k + 6 * k + 100 * dp);
        this.fx = (float) (this.placex * 2 * k + 2.75 * k);
        this.paint.setStrokeWidth((float) (5*dp));
        cpaint.setStrokeWidth((float) (5*dp));
        this.fy = (float) (7.5 * k + 100 * dp + this.placey * 2 * k);
        color();
        update(canvas);
    }

    void color() {
        switch (id) {
            case 0:
            case 1:
                this.idk = 4;
                this.paint.setColor(Color.rgb(215, 0, 0));
                break;
            case 6:
            case 7:
            case 8:
                this.idk = 7;
                this.paint.setColor(Color.rgb(115, 0, 115));
                break;
            case 2:
            case 3:
                this.idk = 5;
                this.paint.setColor(Color.rgb(0, 215, 0));
                break;
            case 4:
            case 5:
                this.idk = 6;
                this.paint.setColor(Color.rgb(0, 215, 215));
                break;

        }
    }

    void spec(List<Integer> i) {
        if (i.get(2) == 1) {
            cpaint.setStyle(Paint.Style.STROKE);
            this.paint.setStyle(Paint.Style.STROKE);
        } else {
            cpaint.setStyle(Paint.Style.FILL_AND_STROKE);
            this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        }
    }

    void update(Canvas canvas) {
        if (!this.taken) {
            idk(this.paint,canvas);
        }else{
            idk(cpaint,canvas);
        }
    }
    void idk(Paint paint1,Canvas canvas) {
            if (!used) {
                switch (this.id) {
                    case 0: {
                        for (List<Integer> i :
                                this.details.get(reverse)) {
                            spec(i);
                            canvas.drawRect((float) (1.75 * k + 0.5 * k * i.get(0)), (float) (6.5 * k + 100 * dp + -0.5 * k * i.get(1)), (float) (2.25 * k + 0.5 * k * i.get(0)), (float) (7 * k + 100 * dp + -0.5 * k * i.get(1)), paint1);
                        }
                        break;
                    }
                    case 1: {
                        for (List<Integer> i :
                                this.details.get(reverse)) {
                            spec(i);
                            canvas.drawRect((float) (3.75 * k + 0.5 * k * i.get(0)), (float) (6.5 * k + 100 * dp + -0.5 * k * i.get(1)), (float) (4.25 * k + 0.5 * k * i.get(0)), (float) (7 * k + 100 * dp + -0.5 * k * i.get(1)), paint1);
                        }
                        break;
                    }
                    case 2: {
                        for (List<Integer> i :
                                this.details.get(reverse)) {
                            spec(i);
                            canvas.drawRect((float) (5.75 * k + 0.5 * k * i.get(0)), (float) (6.5 * k + 100 * dp + -0.5 * k * i.get(1)), (float) (6.25 * k + 0.5 * k * i.get(0)), (float) (7 * k + 100 * dp + -0.5 * k * i.get(1)), paint1);
                        }
                        break;
                    }
                    case 3: {
                        for (List<Integer> i :
                                this.details.get(reverse)) {
                            spec(i);
                            canvas.drawRect((float) (1.75 * k + 0.5 * k * i.get(0)), (float) (8.5 * k + 100 * dp + -0.5 * k * i.get(1)), (float) (2.25 * k + 0.5 * k * i.get(0)), (float) (9 * k + 100 * dp + -0.5 * k * i.get(1)), paint1);
                        }
                        break;
                    }
                    case 4: {
                        for (List<Integer> i :
                                this.details.get(reverse)) {
                            spec(i);
                            canvas.drawRect((float) (3.75 * k + 0.5 * k * i.get(0)), (float) (8.5 * k + 100 * dp + -0.5 * k * i.get(1)), (float) (4.25 * k + 0.5 * k * i.get(0)), (float) (9 * k + 100 * dp + -0.5 * k * i.get(1)), paint1);
                        }
                        break;
                    }
                    case 5: {
                        for (List<Integer> i :
                                this.details.get(reverse)) {
                            spec(i);
                            canvas.drawRect((float) (5.75 * k + 0.5 * k * i.get(0)), (float) (8.5 * k + 100 * dp + -0.5 * k * i.get(1)), (float) (6.25 * k + 0.5 * k * i.get(0)), (float) (9 * k + 100 * dp + -0.5 * k * i.get(1)), paint1);
                        }
                        break;
                    }
                    case 6: {
                        for (List<Integer> i :
                                this.details.get(reverse)) {
                            spec(i);
                            canvas.drawRect((float) (1.75 * k + 0.5 * k * i.get(0)), (float) (10.5 * k + 100 * dp + -0.5 * k * i.get(1)), (float) (2.25 * k + 0.5 * k * i.get(0)), (float) (11 * k + 100 * dp + -0.5 * k * i.get(1)), paint1);
                        }
                        break;
                    }
                    case 7: {
                        for (List<Integer> i :
                                this.details.get(reverse)) {
                            spec(i);
                            canvas.drawRect((float) (3.75 * k + 0.5 * k * i.get(0)), (float) (10.5 * k + 100 * dp + -0.5 * k * i.get(1)), (float) (4.25 * k + 0.5 * k * i.get(0)), (float) (11 * k + 100 * dp + -0.5 * k * i.get(1)), paint1);
                        }
                        break;
                    }
                    case 8: {
                        for (List<Integer> i :
                                this.details.get(reverse)) {
                            spec(i);
                            canvas.drawRect((float) (5.75 * k + 0.5 * k * i.get(0)), (float) (10.5 * k + 100 * dp + -0.5 * k * i.get(1)), (float) (6.25 * k + 0.5 * k * i.get(0)), (float) (11 * k + 100 * dp + -0.5 * k * i.get(1)), paint1);
                        }
                        break;
                    }
                }
            }
        }
    public void refresh(Canvas canvas, boolean needReverse) {
        if (!this.taken) {
            this.tcount = 0;
            color();
        } else {
            if (this.tcount == 0) {
                this.tcount = 1;
            } else {
                if (needReverse) {
                    if (this.reverse != 3) {
                        this.reverse++;
                    } else {
                        this.reverse = 0;
                    }
                }
            }
        }
        update(canvas);
    }
}