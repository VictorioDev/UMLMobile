package zansavio.victorio.com.umlmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class FirstActivity extends AppCompatActivity {
    private final String TAG = "App";
    private Button btnScanear;
    private TextView tvUrl;

    private int REQUEST_CODE = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btnScanear = (Button) findViewById(R.id.btnScanear);
        tvUrl = (TextView) findViewById(R.id.txtURL);

        final Context ctx = this;
        btnScanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(ctx, ScanActivity.class);
                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE){
                Bundle b = data.getExtras();
                if(b != null){
                    String url = data.getStringExtra("URL");
                    tvUrl.setText("URL: " + url);
                }

            }else {
                Log.v(TAG, "Outro código");
            }
        }else {
            Log.v(TAG, "Result Not ok");
        }
    }
}
