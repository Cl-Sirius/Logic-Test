package com.example.logic2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.logic2.Elements.Cell;
import com.example.logic2.Elements.Part;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameActivity extends Activity {
    private Path path;
    public float dp;
    private float k;
    private int turn = 0;
    public boolean temp = false;
    public float TouchX, TouchY;
    public final Paint bgPaint = new Paint();
    public final Paint linePaint = new Paint();
    public ArrayList<Cell> field = new ArrayList<>();
    public ArrayList<Part> parts = new ArrayList<>();
    public boolean needReverse = true;
    public final Paint textPaint = new Paint();
    public boolean isFilled = true;
    public int activeID, activeLevel = 0;
    public boolean wasLastPlaced = false;
    public boolean changed = false;
    List<Object> saveList;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }


    class DrawView extends View {
        public List<List<List<Integer>>> levels = Arrays.asList(
                Arrays.asList(Arrays.asList(6, 4), Arrays.asList(20, 5), Arrays.asList(18, 6), Arrays.asList(1, 6), Arrays.asList(15, 7), Arrays.asList(25, 7)),
                Arrays.asList(Arrays.asList(23, 4), Arrays.asList(13, 5), Arrays.asList(16, 5), Arrays.asList(11, 6), Arrays.asList(6, 7), Arrays.asList(14, 7)),
                Arrays.asList(Arrays.asList(10, 4), Arrays.asList(8, 5), Arrays.asList(16, 6), Arrays.asList(19, 7)),
                Arrays.asList(Arrays.asList(21, 4), Arrays.asList(5, 5), Arrays.asList(11, 6), Arrays.asList(18, 6), Arrays.asList(24, 7), Arrays.asList(25, 7)),
                Arrays.asList(Arrays.asList(13, 5), Arrays.asList(29, 5), Arrays.asList(24, 6)),
                Arrays.asList(Arrays.asList(18, 6), Arrays.asList(19, 6), Arrays.asList(23, 4), Arrays.asList(25, 7)),
                Arrays.asList(Arrays.asList(27, 7), Arrays.asList(31, 4), Arrays.asList(7, 6)),
                Arrays.asList(Arrays.asList(0, 4), Arrays.asList(13, 5), Arrays.asList(18, 5), Arrays.asList(7, 6), Arrays.asList(24, 6), Arrays.asList(31, 7), Arrays.asList(21, 7)),
                Arrays.asList(Arrays.asList(4, 5), Arrays.asList(17, 5), Arrays.asList(9, 6), Arrays.asList(16, 6), Arrays.asList(24, 7), Arrays.asList(25, 7)),
                Arrays.asList(Arrays.asList(9, 4), Arrays.asList(14, 4), Arrays.asList(3, 5), Arrays.asList(17, 5), Arrays.asList(21, 6), Arrays.asList(28, 6), Arrays.asList(18, 7), Arrays.asList(22, 7)));
        private final List<Integer> blackListLeft = Arrays.asList(0, 1, 2, 3);
        private final List<Integer> blackListBottom = Arrays.asList(3, 7, 11, 15, 19, 23, 27, 31);
        private final List<Integer> blackListTop = Arrays.asList(0, 4, 8, 12, 16, 20, 24, 28);
        private final List<Integer> blackListRight = Arrays.asList(28, 29, 30, 31);
        private final List<List<List<List<Integer>>>> typeList = Arrays.asList(
                Arrays.asList(
                        Arrays.asList(Arrays.asList(-1, 1), Arrays.asList(1, 1), Arrays.asList(-5, 0), Arrays.asList(0, 0)),
                        Arrays.asList(Arrays.asList(-4, 1), Arrays.asList(4, 1), Arrays.asList(3, 0), Arrays.asList(0, 0)),
                        Arrays.asList(Arrays.asList(1, 1), Arrays.asList(-1, 1), Arrays.asList(0, 0), Arrays.asList(5, 0)),
                        Arrays.asList(Arrays.asList(-4, 1), Arrays.asList(4, 1), Arrays.asList(0, 0), Arrays.asList(-3, 0))),
                Arrays.asList(
                        Arrays.asList(Arrays.asList(0, 1), Arrays.asList(-4, 0), Arrays.asList(5, 0), Arrays.asList(1, 0)),
                        Arrays.asList(Arrays.asList(0, 1), Arrays.asList(-4, 0), Arrays.asList(-1, 0), Arrays.asList(-3, 0)),
                        Arrays.asList(Arrays.asList(0, 0), Arrays.asList(-1, 1), Arrays.asList(3, 0), Arrays.asList(-4, 0)),
                        Arrays.asList(Arrays.asList(0, 0), Arrays.asList(-1, 0), Arrays.asList(4, 1), Arrays.asList(5, 0))),
                Arrays.asList(
                        Arrays.asList(Arrays.asList(4, 1), Arrays.asList(1, 1), Arrays.asList(0, 0), Arrays.asList(-4, 0)),
                        Arrays.asList(Arrays.asList(1, 1), Arrays.asList(-4, 1), Arrays.asList(-1, 0), Arrays.asList(0, 0)),
                        Arrays.asList(Arrays.asList(-1, 1), Arrays.asList(-4, 1), Arrays.asList(0, 0), Arrays.asList(4, 0)),
                        Arrays.asList(Arrays.asList(4, 1), Arrays.asList(1, 0), Arrays.asList(0, 0), Arrays.asList(-1, 1))),
                Arrays.asList(
                        Arrays.asList(Arrays.asList(0, 1), Arrays.asList(-1, 1), Arrays.asList(-4, 0)),
                        Arrays.asList(Arrays.asList(0, 1), Arrays.asList(4, 1), Arrays.asList(-1, 0)),
                        Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 1), Arrays.asList(4, 0)),
                        Arrays.asList(Arrays.asList(0, 1), Arrays.asList(-4, 1), Arrays.asList(1, 0))),
                Arrays.asList(
                        Arrays.asList(Arrays.asList(-4, 1), Arrays.asList(0, 0), Arrays.asList(4, 0)),
                        Arrays.asList(Arrays.asList(-1, 1), Arrays.asList(0, 0), Arrays.asList(1, 0)),
                        Arrays.asList(Arrays.asList(4, 1), Arrays.asList(0, 0), Arrays.asList(-4, 0)),
                        Arrays.asList(Arrays.asList(1, 1), Arrays.asList(0, 0), Arrays.asList(-1, 0))),
                Arrays.asList(
                        Arrays.asList(Arrays.asList(-4, 1), Arrays.asList(-5, 1), Arrays.asList(1, 1), Arrays.asList(0, 0), Arrays.asList(4, 0)),
                        Arrays.asList(Arrays.asList(-1, 1), Arrays.asList(-4, 1), Arrays.asList(3, 1), Arrays.asList(0, 0), Arrays.asList(1, 0)),
                        Arrays.asList(Arrays.asList(5, 1), Arrays.asList(4, 1), Arrays.asList(-1, 1), Arrays.asList(0, 0), Arrays.asList(-4, 0)),
                        Arrays.asList(Arrays.asList(-3, 1), Arrays.asList(4, 1), Arrays.asList(1, 1), Arrays.asList(0, 0), Arrays.asList(-1, 0))),
                Arrays.asList(
                        Arrays.asList(Arrays.asList(1, 1), Arrays.asList(-3, 1), Arrays.asList(0, 0), Arrays.asList(4, 0), Arrays.asList(-4, 0)),
                        Arrays.asList(Arrays.asList(-4, 1), Arrays.asList(-5, 1), Arrays.asList(0, 0), Arrays.asList(-1, 0), Arrays.asList(1, 0)),
                        Arrays.asList(Arrays.asList(-1, 1), Arrays.asList(3, 1), Arrays.asList(0, 0), Arrays.asList(4, 0), Arrays.asList(-4, 0)),
                        Arrays.asList(Arrays.asList(4, 1), Arrays.asList(5, 1), Arrays.asList(0, 0), Arrays.asList(-1, 0), Arrays.asList(1, 0))),
                Arrays.asList(
                        Arrays.asList(Arrays.asList(0, 1), Arrays.asList(4, 0)),
                        Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 0)),
                        Arrays.asList(Arrays.asList(0, 1), Arrays.asList(-4, 0)),
                        Arrays.asList(Arrays.asList(0, 1), Arrays.asList(-1, 0))),
                Arrays.asList(
                        Arrays.asList(Arrays.asList(0, 0), Arrays.asList(4, 0)),
                        Arrays.asList(Arrays.asList(0, 0), Arrays.asList(1, 0)),
                        Arrays.asList(Arrays.asList(0, 0), Arrays.asList(-4, 0)),
                        Arrays.asList(Arrays.asList(0, 0), Arrays.asList(-1, 0))));

        public DrawView(Context context) {
            super(context);
            dp = context.getResources().getDisplayMetrics().density;
            bgPaint.setColor(Color.rgb(215, 215, 215));
            bgPaint.setStyle(Paint.Style.FILL);
            linePaint.setColor(Color.rgb(140, 140, 140));
            linePaint.setStyle(Paint.Style.FILL);
            textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC));
            textPaint.setTextAlign(Paint.Align.CENTER);
            path = new Path();
        }


        @SuppressLint("DrawAllocation")
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawRect(0, 0, getWidth(), getHeight(), bgPaint);
            k = getWidth() / 10;
            if (!temp) {
                for (float i = k; i < getWidth() * 9 / 10; i += k) {
                    for (float j = 100 * dp + 3 * k; j < getWidth() / 2 + 100 * dp + 2 * k; j += k) {
                        field.add(new Cell(i + 2 * dp, j, i + k - 2 * dp, j + k - 2 * dp, canvas, linePaint, dp));
                    }
                }
//                canvas.drawText();
                levelCreate(levels.get(activeLevel));
                parts.add(new Part(0, 0, 0, canvas, Arrays.asList(Arrays.asList(
                        Arrays.asList(0, 0, 0), Arrays.asList(0, 1, 1), Arrays.asList(-1, 1, 0), Arrays.asList(0, -1, 1)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(-1, 0, 1), Arrays.asList(1, 1, 0), Arrays.asList(1, 0, 1)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(0, 1, 1), Arrays.asList(0, -1, 1), Arrays.asList(1, -1, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(-1, 0, 1), Arrays.asList(-1, -1, 0), Arrays.asList(1, 0, 1))), dp));
                parts.add(new Part(1, 1, 0, canvas, Arrays.asList(
                        Arrays.asList(Arrays.asList(0, 0, 1), Arrays.asList(-1, 0, 0), Arrays.asList(0, -1, 0), Arrays.asList(1, -1, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 1), Arrays.asList(-1, 0, 0), Arrays.asList(-1, -1, 0), Arrays.asList(0, 1, 0)),
                        Arrays.asList(Arrays.asList(-1, 0, 0), Arrays.asList(0, 1, 1), Arrays.asList(0, 0, 0), Arrays.asList(1, 1, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(0, 1, 0), Arrays.asList(1, 0, 1), Arrays.asList(1, -1, 0))), dp));
                parts.add(new Part(2, 2, 0, canvas, Arrays.asList(
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(-1, 0, 0), Arrays.asList(0, -1, 1), Arrays.asList(1, 0, 1)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(0, 1, 0), Arrays.asList(0, -1, 1), Arrays.asList(-1, 0, 1)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(-1, 0, 1), Arrays.asList(0, 1, 1), Arrays.asList(1, 0, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(1, 0, 1), Arrays.asList(0, 1, 1), Arrays.asList(0, -1, 0))), dp));
                parts.add(new Part(3, 0, 1, canvas, Arrays.asList(
                        Arrays.asList(Arrays.asList(0, 0, 1), Arrays.asList(-1, 0, 0), Arrays.asList(0, 1, 1)),
                        Arrays.asList(Arrays.asList(0, 0, 1), Arrays.asList(1, 0, 1), Arrays.asList(0, 1, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 1), Arrays.asList(1, 0, 0), Arrays.asList(0, -1, 1)),
                        Arrays.asList(Arrays.asList(0, 0, 1), Arrays.asList(-1, 0, 1), Arrays.asList(0, -1, 0))), dp));
                parts.add(new Part(4, 1, 1, canvas, Arrays.asList(
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(-1, 0, 1), Arrays.asList(1, 0, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(0, 1, 1), Arrays.asList(0, -1, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(-1, 0, 0), Arrays.asList(1, 0, 1)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(0, 1, 0), Arrays.asList(0, -1, 1))), dp));
                parts.add(new Part(5, 2, 1, canvas, Arrays.asList(
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(-1, 0, 1), Arrays.asList(-1, 1, 1), Arrays.asList(1, 0, 0), Arrays.asList(0, -1, 1)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(-1, 0, 1), Arrays.asList(1, 1, 1), Arrays.asList(0, 1, 1), Arrays.asList(0, -1, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(-1, 0, 0), Arrays.asList(1, -1, 1), Arrays.asList(1, 0, 1), Arrays.asList(0, 1, 1)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(1, 0, 1), Arrays.asList(-1, -1, 1), Arrays.asList(0, -1, 1), Arrays.asList(0, 1, 0))), dp));
                parts.add(new Part(6, 0, 2, canvas, Arrays.asList(
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(-1, 0, 0), Arrays.asList(-1, -1, 1), Arrays.asList(1, 0, 0), Arrays.asList(0, -1, 1)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(-1, 0, 1), Arrays.asList(0, -1, 0), Arrays.asList(-1, 1, 1), Arrays.asList(0, 1, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(-1, 0, 0), Arrays.asList(1, 1, 1), Arrays.asList(1, 0, 0), Arrays.asList(0, 1, 1)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(0, 1, 0), Arrays.asList(1, -1, 1), Arrays.asList(1, 0, 1), Arrays.asList(0, -1, 0))), dp));
                parts.add(new Part(7, 1, 2, canvas, Arrays.asList(
                        Arrays.asList(Arrays.asList(0, 0, 1), Arrays.asList(1, 0, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 1), Arrays.asList(0, -1, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 1), Arrays.asList(-1, 0, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 1), Arrays.asList(0, 1, 0))), dp));
                parts.add(new Part(8, 2, 2, canvas, Arrays.asList(
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(1, 0, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(0, -1, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(-1, 0, 0)),
                        Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(0, 1, 0))), dp));
            } else {
                for (int i = 0; i < 32; i++) {
                    field.get(i).refresh(canvas, activeID);
                }

                for (Part part :
                        parts) {
                    part.refresh(canvas, needReverse);
                }
            }
            for (Part part1 :
                    parts) {
                if (!part1.used) {
                    isFilled = false;
                }
            }
            if (isFilled) {
                textPaint.setTextSize(35 * dp);
                canvas.drawText("Level "+(activeLevel+1), getWidth() / 2, 85 * dp, textPaint);
                canvas.drawText("Completed", getWidth() / 2, 130 * dp, textPaint);
            } else {
                textPaint.setTextSize(45 * dp);
                canvas.drawText("Level " + (activeLevel + 1), getWidth() / 2, 120 * dp, textPaint);
            }
            path.moveTo((float) (k * 2.25), 85 * dp);
            path.lineTo((float) (k * 1.5), 105 * dp);
            path.lineTo((float) (k * 2.25), 125 * dp);
            path.moveTo((float) (k * 7.75), 85 * dp);
            path.lineTo((float) (k * 8.5), 105 * dp);
            path.lineTo((float) (k * 7.75), 125 * dp);
            path.close();
            canvas.drawPath(path, textPaint);
            levelCreate(levels.get(activeLevel));
            temp = true;
        }

        public void levelCreate(List<List<Integer>> level) {
            for (List<Integer> i :
                    level) {
                if (field.get(i.get(0)).type != 9) {
                    field.get(i.get(0)).type = i.get(1);
                }
            }
            if (!temp) {
                invalidate();
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            ArrayList<Integer> timed = new ArrayList<>();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    TouchX = (float) event.getX();
                    TouchY = (float) event.getY();
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    int a = -1;
                    TouchX = (float) event.getX();
                    TouchY = (float) event.getY();
                    for (Part part :
                            parts) {
                        if (part.taken) {
                            activeID = part.id;
                            for (Cell cell :
                                    field) {
                                a++;
                                if (cell.sx <= TouchX && cell.sy <= TouchY && cell.fx > TouchX
                                        && cell.fy > TouchY) {
                                    timed.clear();
                                    if (map(a, part.id, part.reverse) && check(a, part)) {
                                        for (List<Integer> list : typeList.get(part.id).get(part.reverse)) {
                                            switch (list.get(1)) {
                                                case 0:
                                                    field.get(a + list.get(0)).type = 2;
                                                    break;
                                                case 1:
                                                    field.get(a + list.get(0)).type = 3;
                                                    break;
                                            }
                                            timed.add(a + list.get(0));
                                        }
                                    }
                                }
                                for (List<Integer> i :
                                        levels.get(activeLevel)) {
                                    timed.add(i.get(0));
                                }
                                for (int i = 0; i < 32; i++) {
                                    if (!timed.contains(i) && field.get(i).type != 8 && field.get(i).type != 9) {
                                        field.get(i).type = 0;
                                    }
                                }
                            }
                        }
                    }
                    needReverse = false;
                    invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    boolean canRestart = true;
                    int a;
                    for (Part part : parts) {
                        a = -1;
                        if (part.taken) {
                            canRestart = false;
                            for (Cell cell :
                                    field) {
                                a++;
                                if (cell.sx <= TouchX && cell.sy <= TouchY && cell.fx > TouchX
                                        && cell.fy > TouchY) {
                                    if (map(a, part.id, part.reverse) && check(a, part)) {
                                        place(a, part);
                                        isFilled = true;
                                        wasLastPlaced = true;
                                        changed = true;
                                    } else {
                                        wasLastPlaced = false;
                                    }
                                }
                            }
                        }
                    }
                    for (Part part : parts) {
                        timed.clear();
                        if (TouchX > part.sx && TouchX < part.fx && TouchY > part.sy && TouchY < part.fy) {
                            if (!part.used) {
                                part.taken = true;
                            }
                        } else {
                            part.taken = false;
                        }
                    }
                    if (TouchY < 150 * dp && canRestart) {
                        if (TouchX <= 2.5 * k && activeLevel >= 1) {
                            activeLevel--;
                            changed = true;
                        } else if (TouchX >= 7.5 * k && activeLevel < levels.size() - 1) {
                            activeLevel++;
                            changed = true;
                        }
                        if (changed) {
                            clean();
                            changed = false;
                        }
                    }
                    needReverse = true;
                    invalidate();
                    break;
                }
            }
            return true;
        }

        void clean() {
            field.clear();
            parts.clear();
            temp = false;
        }

        boolean check(int a, Part part) {
            boolean checked = true;
            boolean costil = true;
            for (List<Integer> list : typeList.get(part.id).get(part.reverse)) {
                switch (list.get(1)) {
                    case 0:
                        if (field.get(a + list.get(0)).type != 0 && field.get(a + list.get(0)).type != 2) {
                            checked = false;
                        }
                        break;
                    case 1:
                        if (field.get(a + list.get(0)).type != 0 && field.get(a + list.get(0)).type != 3) {
                            checked = false;
                            if (field.get(a + list.get(0)).type == part.idk) {
                                checked=true;
                            }else{
                                costil=false;
                            }
                        }
                        break;
                }
            }
            if (!costil) {
                checked = false;
            }
            return checked;
        }

        void place(int a, Part part) {
            for (List<Integer> list : typeList.get(part.id).get(part.reverse)) {
                switch (list.get(1)) {
                    case 0:
                        field.get(a + list.get(0)).type = 8;
                        if (field.get(a + list.get(0)).changeable == 0) {
                            field.get(a + list.get(0)).fpaint = part.paint;
                        }
                        field.get(a + list.get(0)).changeable++;
                        break;
                    case 1:
                        field.get(a + list.get(0)).type = 9;
                        if (field.get(a + list.get(0)).changeable == 0) {
                            field.get(a + list.get(0)).fpaint = part.paint;
                        }
                        field.get(a + list.get(0)).changeable++;
                        break;
                }
            }
            part.used = true;
            invalidate();
        }

        boolean map(int a, int id, int reverse) {
            boolean can = true;
            switch (id) {
                case 0:
                    switch (reverse) {
                        case 0:
                            if (blackListTop.contains(a) || blackListLeft.contains(a) || blackListBottom.contains(a)) {
                                can = false;
                            }
                            break;
                        case 1:
                            if (blackListTop.contains(a) || blackListLeft.contains(a) || blackListRight.contains(a)) {
                                can = false;
                            }
                            break;
                        case 2:
                            if (blackListTop.contains(a) || blackListBottom.contains(a) || blackListRight.contains(a)) {
                                can = false;
                            }
                            break;
                        case 3:
                            if (blackListRight.contains(a) || blackListLeft.contains(a) || blackListBottom.contains(a)) {
                                can = false;
                            }
                            break;
                    }
                    break;
                case 1:
                case 2:
                case 6:
                    switch (reverse) {
                        case 0:
                            if (blackListRight.contains(a) || blackListLeft.contains(a) || blackListBottom.contains(a)) {
                                can = false;
                            }
                            break;
                        case 1:
                            if (blackListTop.contains(a) || blackListBottom.contains(a) || blackListLeft.contains(a)) {
                                can = false;
                            }
                            break;
                        case 2:
                            if (blackListLeft.contains(a) || blackListTop.contains(a) || blackListRight.contains(a)) {
                                can = false;
                            }
                            break;
                        case 3:
                            if (blackListTop.contains(a) || blackListRight.contains(a) || blackListBottom.contains(a)) {
                                can = false;
                            }
                            break;
                    }
                    break;
                case 3:
                    switch (reverse) {
                        case 0:
                            if (blackListTop.contains(a) || blackListLeft.contains(a)) {
                                can = false;
                            }
                            break;
                        case 1:
                            if (blackListTop.contains(a) || blackListRight.contains(a)) {
                                can = false;
                            }
                            break;
                        case 2:
                            if (blackListRight.contains(a) || blackListBottom.contains(a)) {
                                can = false;
                            }
                            break;
                        case 3:
                            if (blackListLeft.contains(a) || blackListBottom.contains(a)) {
                                can = false;
                            }
                            break;
                    }
                    break;
                case 4:
                    switch (reverse) {
                        case 0:
                        case 2:
                            if (blackListRight.contains(a) || blackListLeft.contains(a)) {
                                can = false;
                            }
                            break;
                        case 1:
                        case 3:
                            if (blackListTop.contains(a) || blackListBottom.contains(a)) {
                                can = false;
                            }
                            break;
                    }
                    break;
                case 5:
                    if (blackListBottom.contains(a) || blackListTop.contains(a) || blackListLeft.contains(a) || blackListRight.contains(a)) {
                        can = false;
                    }
                    break;
                case 8:
                case 7:
                    switch (reverse) {
                        case 0:
                            if (blackListRight.contains(a)) {
                                can = false;
                            }
                            break;
                        case 1:
                            if (blackListBottom.contains(a)) {
                                can = false;
                            }
                            break;
                        case 2:
                            if (blackListLeft.contains(a)) {
                                can = false;
                            }
                            break;
                        case 3:
                            if (blackListTop.contains(a)) {
                                can = false;
                            }
                            break;
                    }
            }
            return can;
        }
    }

}



