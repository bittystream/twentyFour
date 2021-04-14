package cn.edu.cqu.twentyfour;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.ContentValues.TAG;

public class PokerAdapter extends RecyclerView.Adapter<PokerAdapter.ViewHolder> {

    private List<Poker> mPokerList;
    public int pokerCount = 0;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView pokerId;
        public ViewHolder(View view) {
            super(view);
            pokerId = view.findViewById(R.id.pokerId);
        }

    }

    public PokerAdapter(List<Poker> pokerList) {
        mPokerList = pokerList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poker_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final Poker poker = mPokerList.get(position);
        final char id = poker.getId();
        final int color = poker.getColor();
        holder.pokerId.setText(String.valueOf(id));
        Log.i(TAG, "onBindViewHolder: "+String.valueOf(color));
        if (color==1) holder.pokerId.setTextColor(Color.BLACK); // 黑色的牌
        else {
            holder.pokerId.setTextColor(Color.RED); // 红色的牌
        }

        // 因为是recycler view，每次回到可见区域都要进行对扑克牌所有有关信息的重新绘制。
        if (poker.isChosen){
            holder.pokerId.setTextColor(Color.GRAY);
            holder.pokerId.setEnabled(false);
        }
        else {
            holder.pokerId.setEnabled(true);
        }

        holder.pokerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pokerCount == 4) return;// 选完四张牌就不能再选了
                pokerCount++;
                TextView clickedPoker = view.findViewById(R.id.pokerId); // 点中的牌
                TextView chosenPoker = null; // 表示将要被加入到已选区域中去的牌

                poker.isChosen = true;
                clickedPoker.setEnabled(false);
                clickedPoker.setTextColor(Color.GRAY);
                // 找到加入的位置
                switch (pokerCount){
                    case 1:
                        chosenPoker = view.getRootView().findViewById(R.id.chosenPoker1);
                        break;
                    case 2:
                        chosenPoker = view.getRootView().findViewById(R.id.chosenPoker2);
                        break;
                    case 3:
                        chosenPoker = view.getRootView().findViewById(R.id.chosenPoker3);
                        break;
                    case 4:
                        chosenPoker = view.getRootView().findViewById(R.id.chosenPoker4);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + pokerCount);
                }
                chosenPoker.setText(String.valueOf(id));
                Log.i(TAG, "onClick: chose card"+String.valueOf(poker.getId()));
                if(color==1) chosenPoker.setTextColor(Color.BLACK);
                else chosenPoker.setTextColor(Color.RED);
                chosenPoker.setVisibility(View.VISIBLE); // 本来该位置的牌是INVISIBLE的

                Log.i(TAG, "onClick: 8787878position @ "+String.valueOf(position));

                // 选完了
                if (pokerCount == 4){
                    Button chooseButton = view.getRootView().findViewById(R.id.chooseButton);
                    chooseButton.setEnabled(true);
                    chooseButton.setText("选择完毕");
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mPokerList.size();
    }

    // 在PlayActivity中直接对与adapter绑定的对象列表进行修改比较复杂
    // 可以通过调用这个函数来间接修改，同时向通知holder，对position对应的牌进行重绘
    public void pokerBack(int position){
        mPokerList.get(position).isChosen = false;
        notifyItemChanged(position);
    }
}
