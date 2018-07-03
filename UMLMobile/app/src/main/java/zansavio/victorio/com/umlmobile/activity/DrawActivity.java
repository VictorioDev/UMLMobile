package zansavio.victorio.com.umlmobile.activity;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import zansavio.victorio.com.umlmobile.R;
import zansavio.victorio.com.umlmobile.pojo.BaseElement;
import zansavio.victorio.com.umlmobile.pojo.Ligacao;
import zansavio.victorio.com.umlmobile.pojo.Observacao;
import zansavio.victorio.com.umlmobile.pojo.Response;
import zansavio.victorio.com.umlmobile.utils.NetworkUtils;
import zansavio.victorio.com.umlmobile.view.LineView;

import static java.lang.Thread.sleep;

public class DrawActivity extends AppCompatActivity {
    private static final String TAG = "App";
    private Response resp;
    private ViewGroup root;
    private int myWidth;
    private int myHeight;

    private String url = "";
    private List<View> listaViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        myWidth = size.x;
        myHeight = size.y;

        url = getIntent().getExtras().getString("URL");

        //Toast.makeText(this, getIntent().getExtras().getString("URL"), Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            limpaTela();
                        }
                    });
                    resp = montaObjeto();
                    resp.setLargura(1164);
                    resp.setAltura(675);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adicionaViews(resp);
                            Log.v(TAG, "Executando...");
                        }
                    });
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
        }).start();
    }

    private Response montaObjeto() {
        //String json = NetworkUtils.getJson("http://192.168.0.102/tcc/");
        String json = NetworkUtils.getJson(url);
        Gson g = new Gson();
        Response r = g.fromJson(json, Response.class);
        return r;
    }

    private void limpaTela(){
        root = findViewById(R.id.layoutRoot);
        root.removeAllViews();
    }

    private void adicionaViews(Response obj){

        //Adiciona os use cases na tela
        for(BaseElement element: obj.getDiagrama().getCases()){
            View v = LayoutInflater.from(this).inflate(R.layout.use_case, null);
            TextView tv = (TextView) v.findViewById(R.id.txtName);
            tv.setText(element.getNome());
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            v.setLayoutParams(params);
            int[] coord = {element.getAtributos().getX(),element.getAtributos().getY()};
            int[] rightCoord = convertPixels(coord, myWidth, myHeight, resp.getLargura(), resp.getAltura());
            v.setX(rightCoord[0] + v.getMeasuredWidth());
            v.setY(rightCoord[1] + v.getMeasuredHeight());
            v.setId(element.getId());
            //root.addView(v);
            listaViews.add(v);
        }

        //Adiciona os atores na tela
        for(BaseElement element: obj.getDiagrama().getAtores()){
            View v = LayoutInflater.from(this).inflate(R.layout.ator, null);
            TextView tv = (TextView) v.findViewById(R.id.tvNomeAtor);
            tv.setText(element.getNome());
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            v.setLayoutParams(lp);
            int[] coord = {element.getAtributos().getX(),element.getAtributos().getY()};
            int[] rightCoord = convertPixels(coord, myWidth, myHeight, resp.getLargura(), resp.getAltura());
            v.setX(rightCoord[0] + v.getMeasuredWidth());
            v.setY(rightCoord[1] + v.getMeasuredHeight());
            v.setId(element.getId());
            //root.addView(v);
            listaViews.add(v);
        }

       for(Observacao element: obj.getDiagrama().getObservacoes()){
            TextView v =  new TextView(this);
            ViewGroup.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            v.setLayoutParams(lp);
            int[] coord = {element.getAtributos().getX(),element.getAtributos().getY()};
            int[] rightCoord = convertPixels(coord, myWidth, myHeight, resp.getLargura(), resp.getAltura());
            v.setX(rightCoord[0] + v.getMeasuredWidth());
            v.setY(rightCoord[1] + v.getMeasuredHeight());
            v.setId(element.getId());
            v.setText(element.getDescricao());
            listaViews.add(v);

    }

        for(Ligacao element: obj.getDiagrama().getLigacoes().getAssociacao()){
            LineView line = new LineView(this, findByIdInArray(element.getDe()), findByIdInArray(element.getPara()));
            ViewGroup.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            root.addView(line);
        }


        for(Ligacao element: obj.getDiagrama().getLigacoes().getExtend()){
            LineView line = new LineView(this, findByIdInArray(element.getDe()), findByIdInArray(element.getPara()), "<<Extend>>");
            ViewGroup.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            root.addView(line);
        }


        for(Ligacao element: obj.getDiagrama().getLigacoes().getInclude()){
            LineView line = new LineView(this, findByIdInArray(element.getDe()), findByIdInArray(element.getPara()), "<<Include>>");
            ViewGroup.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            root.addView(line);
        }

        for(View v: listaViews){
            root.addView(v);
        }

        listaViews.clear();

    }

    private int[] convertPixels(int[] cord, int myWidth, int myHeight, int theirWidth, int theirHeight){
        int x = cord[0] * myWidth;
        x = x / theirWidth;

        int y = cord[1] * myHeight;
        y = y / theirHeight;

        int[] rightCoord = {x, y};
        return rightCoord;
    }

    private View findByIdInArray(int id){
       for (View v : listaViews){
           if(v.getId() == id)
               return v;
       }

       return null;
    }
}
