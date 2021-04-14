package cn.edu.cqu.twentyfour;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PlayActivity";
    List<Poker> pokerList = new ArrayList<>();
    List<Character> pokerNumberList = new ArrayList<>();
    PokerAdapter pokerAdapter = new PokerAdapter(pokerList);
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayout.HORIZONTAL, false);
    RecyclerView recyclerView;
    Button chooseButton;

    TextView poker4;
    TextView poker3;
    TextView poker2;
    TextView poker1;
    CharSequence text4;
    CharSequence text3;
    CharSequence text2;
    CharSequence text1;
    int color4;
    int color3;
    int color2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        chooseButton = findViewById(R.id.chooseButton);
        chooseButton.setEnabled(false);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fresh();
                ArrayList<Integer> num = new ArrayList<Integer>(4);
                // get the numbers on the four chosen poker
                String [] cs = {(String)text1, (String)text2, (String)text3, (String)text4};
                Log.i(TAG, "onClick: "+text1+text2+text3+text4);
                for (CharSequence it : cs){
                    char id = it.charAt(0);
                    Log.i(TAG, "onClick: "+String.valueOf(id));
                    if ((int)id < (int)'N') {
                        num.add((int)id-(int)'A'+1);
                        Log.i(TAG, "onClick: 方块"+String.valueOf((int)id));
                    }
                    else if ((int)id < (int)'a'){
                        num.add((int)id-(int)'N'+1);
                        Log.i(TAG, "onClick: 红桃"+String.valueOf((int)id));
                    }
                    else if ((int)id < (int)'n'){
                        num.add((int)id-(int)'a'+1);
                        Log.i(TAG, "onClick: 黑桃"+String.valueOf((int)id));
                    }
                    else{
                        num.add((int)id-(int)'n'+1);
                        Log.i(TAG, "onClick: 梅花"+String.valueOf((int)id));
                    }
                }
                int [] numArray = {0,0,0,0};
                int i = 0;
                for (int n: num){
                    numArray[i++] = n;
                }

                Log.i(TAG, "onClick: "+num.toString()+num.getClass());
                Intent intent = new Intent(PlayActivity.this,ResActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("num",numArray);
                startActivity(intent);
            }
        });

        TextView chosenPoker1 = findViewById(R.id.chosenPoker1);
        TextView chosenPoker2 = findViewById(R.id.chosenPoker2);
        TextView chosenPoker3 = findViewById(R.id.chosenPoker3);
        TextView chosenPoker4 = findViewById(R.id.chosenPoker4);

        chosenPoker1.setOnClickListener(this);
        chosenPoker2.setOnClickListener(this);
        chosenPoker3.setOnClickListener(this);
        chosenPoker4.setOnClickListener(this);

        char[] cardType = {'S', 'H', 'C', 'D'};

        for (char ct : cardType) {
            for (int i = 1; i <= 13; i++) {
                Poker poker = new Poker(ct, i);
                Log.i(TAG, "onCreate: poker " + String.valueOf(ct) + " " + String.valueOf(i));
                pokerList.add(poker);
            }
        }
        Collections.shuffle(pokerList);

        for (Poker p : pokerList){
            pokerNumberList.add(p.getId());
        }

        recyclerView = findViewById(R.id.recyclerView);
        ;
//        PokerAdapter pokerAdapter = new PokerAdapter(pokerList);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(pokerAdapter);

    }

    // 刷新取值
    private void fresh(){
        poker4 = findViewById(R.id.chosenPoker4);
        poker3 = findViewById(R.id.chosenPoker3);
        poker2 = findViewById(R.id.chosenPoker2);
        poker1 = findViewById(R.id.chosenPoker1);
        text4 = poker4.getText();
        text3 = poker3.getText();
        text2 = poker2.getText();
        text1 = poker1.getText();
        color4 = poker4.getCurrentTextColor();
        color3 = poker3.getCurrentTextColor();
        color2 = poker2.getCurrentTextColor();
    }

    @Override
    public void onClick(View view) {
        if (view.getVisibility()==View.INVISIBLE) return;
        fresh();
        char viewText = 'A';
        switch (view.getId()){
            case R.id.chosenPoker1:
                viewText = text1.charAt(0);
                switch (pokerAdapter.pokerCount){
                    case 1:
                        poker1.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        poker1.setText(text2);
                        poker1.setTextColor(color2);
                        poker2.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        poker1.setText(text2);
                        poker1.setTextColor(color2);
                        poker2.setText(text3);
                        poker2.setTextColor(color3);
                        poker3.setVisibility(View.INVISIBLE);
                        break;
                    case 4:
                        poker1.setText(text2);
                        poker1.setTextColor(color2);
                        poker2.setText(text3);
                        poker2.setTextColor(color3);
                        poker3.setText(text4);
                        poker3.setTextColor(color4);
                        poker4.setVisibility(View.INVISIBLE);

                        break;
                }
                break;

            case R.id.chosenPoker2:
                viewText = text2.charAt(0);
                switch (pokerAdapter.pokerCount){
                    case 2:
                        poker2.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        poker2.setText(text3);
                        poker2.setTextColor(color3);
                        poker3.setVisibility(View.INVISIBLE);
                        break;
                    case 4:
                        poker2.setText(text3);
                        poker2.setTextColor(color3);
                        poker3.setText(text4);
                        poker3.setTextColor(color4);
                        poker4.setVisibility(View.INVISIBLE);
                        break;
                }
                break;

            case R.id.chosenPoker3:
                viewText = text3.charAt(0);
                if(pokerAdapter.pokerCount==3) {
                    poker3.setVisibility(View.INVISIBLE);
                    break;
                }
                poker3.setText(text4);
                poker3.setTextColor(color4);
                poker4.setVisibility(View.INVISIBLE);
                break;
            case R.id.chosenPoker4:
                viewText = text4.charAt(0);
                poker4.setVisibility(View.INVISIBLE);
                break;
        }
        pokerAdapter.pokerCount--;
        if (pokerAdapter.pokerCount!=4){
            chooseButton.setText("请选择四张扑克牌");
            chooseButton.setEnabled(false);
        }

        int position = pokerNumberList.indexOf(viewText);
        Log.i(TAG, "onClick: position @ "+String.valueOf(position));
        pokerAdapter.pokerBack(position);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        onCreate(null);
        super.onActivityResult(requestCode, resultCode, data);
    }
}