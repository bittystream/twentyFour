package cn.edu.cqu.twentyfour;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Set;

public class ResActivity extends AppCompatActivity {

    private static final String TAG = "ResActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        //Toast.makeText(this,"im glad it works",Toast.LENGTH_LONG).show();

        Bundle bundle = this.getIntent().getExtras();
        int [] arr = bundle.getIntArray("num");
        Game24 game = new Game24();
        Set<String> res = game.play(arr);
        CharSequence result = "";
        CharSequence resultNote = "";

        if (res.size()<1) resultNote = "无法得到二十四点！";
        else {
            int cnt = 0;
            resultNote = "一共有"+String.valueOf(res.size())+"种方法可以得到24点:";
            for (String it : res){
//            Log.i(TAG, "onCreate: "+it);
                if (cnt == 0) result = it;
                else result = result + "\n" + it;
                cnt++;
            }
        }

        TextView resultNotetv = findViewById(R.id.resultNote);
        TextView tv = findViewById(R.id.result);
        resultNotetv.setText(resultNote);
        tv.setText(result);

        Button button = findViewById(R.id.oneMoreButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResActivity.this,PlayActivity.class);
                setResult(0,intent);
                finish();
            }
        });
    }
}