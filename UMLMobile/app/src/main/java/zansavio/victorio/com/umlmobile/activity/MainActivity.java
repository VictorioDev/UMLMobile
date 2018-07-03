package zansavio.victorio.com.umlmobile.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zansavio.victorio.com.umlmobile.view.LineView;
import zansavio.victorio.com.umlmobile.R;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    //private View entity;
    private ViewGroup container;
    private Button btnAdd;
    private int id = 0;
    private List<View> listViews = new ArrayList<>();
    private static final String TAG = "TESTE";

    private ArrayList<String> methods = new ArrayList<>();
    private ArrayList<String> attributes = new ArrayList<>();
    private int _xDelta;
    private int _yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        container = (ViewGroup) findViewById(R.id.mainContainer);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.addView(createView());
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                //LinearLayout.LayoutParams lParams = (LinearLayout.LayoutParams) v.getLayoutParams();
                //_xDelta = X - lParams.leftMargin;
                //_yDelta = Y - lParams.topMargin;
                _xDelta = (int)(X - v.getX());
                _yDelta = (int) (Y - v.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                //LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) v.getLayoutParams();
                //layoutParams.leftMargin = X - _xDelta;
                //layoutParams.topMargin = Y - _yDelta;
                //v.setLayoutParams(layoutParams);
                if(v.getX() != (X - _xDelta) && v.getY() != (Y - _yDelta)){
                    v.setX(X - _xDelta);
                    v.setY(Y - _yDelta);
                    container.invalidate();
                    return true;
                }


                break;
        }

        //Log.i("Teste", "ID: " + id);
        return false;
    }

    public View createView(){
        //Log.i("Teste", "ID: " + id);
        View entity = LayoutInflater.from(this).inflate(R.layout.entity, null, false);
        entity.setId(id++);
        entity.setOnTouchListener(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 0;
        params.leftMargin = 0;
        entity.setLayoutParams(params);
        final ViewGroup attributeContainer;
        final ViewGroup methodContainer;
        ImageView addAttribute;
        ImageView addMethod;
        attributeContainer = (ViewGroup) entity.findViewById(R.id.attributesContainer);
        methodContainer = (ViewGroup) entity.findViewById(R.id.methodsContainer);
        addAttribute = (ImageView) entity.findViewById(R.id.addAttribute);
        addAttribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(MainActivity.this);
                ViewGroup.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(params);
                textView.setText("Attribute"+attributeContainer.getChildCount() + " : String");
                attributeContainer.addView(textView);
            }
        });

        addMethod = (ImageView) entity.findViewById(R.id.addMethod);
        addMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(MainActivity.this);
                ViewGroup.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(params);
                textView.setText("Method"+methodContainer.getChildCount()+ "() : void");
                methodContainer.addView(textView);
            }
        });

        entity.setOnLongClickListener (new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Log.i(TAG, "onLongClick: " + v.getId());
                if(listViews.size() < 1){
                    listViews.add(v);
                    return false;
                }else{
                    container.addView(new LineView(MainActivity.this, v, listViews.get(0)));
                    listViews.clear();
                }
                return true;
            }
        });
        return entity;
    }
}
