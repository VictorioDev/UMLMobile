package zansavio.victorio.com.umlmobile.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import zansavio.victorio.com.umlmobile.R;

/**
 * Created by Victorio Zansavio on 29/10/2017.
 */

public class LineView extends View {

    private View v1;
    private View v2;
    private String text;

    public LineView(Context context) {
        super(context);
    }

    public LineView(Context context, View v1, View v2) {
        super(context);
        this.v1 = v1;
        this.v2 = v2;
    }


    public LineView(Context context, View v1, View v2, String text) {
        super(context);
        this.v1 = v1;
        this.v2 = v2;
        this.text = text;
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p  = new Paint();
        p.setColor(Color.BLACK);
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (5 * scale + 0.5f);
        p.setStrokeWidth(pixels);
        float x1 = v1.getMeasuredWidth()/2 + v1.getX();
        float y1 = v1.getMeasuredHeight()/2 + v1.getY();
        float x2 = v2.getMeasuredWidth()/2 + v2.getX();
        float y2 =  v2.getMeasuredHeight()/2 + v2.getY();
        canvas.drawLine(x1, y1, x2, y2, p);

        if (this.text != null){
            p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            p.setTextSize(18f * scale);
            canvas.drawText(this.text,x1 + (x2 - x1)/2 + pixels,y1 + (y2 - y1)/2 + pixels, p);
        }

    }
}
