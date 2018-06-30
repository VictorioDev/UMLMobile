package zansavio.victorio.com.umlmobile;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Victorio Zansavio on 29/10/2017.
 */

public class LineView extends View {

    private View v1;
    private View v2;

    public LineView(Context context) {
        super(context);
    }

    public LineView(Context context, View v1, View v2) {
        super(context);
        this.v1 = v1;
        this.v2 = v2;
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
        canvas.drawLine(v1.getMeasuredWidth()/2 + v1.getX(), v1.getMeasuredHeight()/2 + v1.getY(), v2.getMeasuredWidth()/2 + v2.getX(), v2.getMeasuredHeight()/2 + v2.getY(), p);
    }
}
