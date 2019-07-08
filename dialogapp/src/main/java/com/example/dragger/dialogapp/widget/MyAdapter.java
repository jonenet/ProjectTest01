package com.example.dragger.dialogapp.widget;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dragger.dialogapp.R;

/**
 * Desc: TODO
 * <p>
 * Author: zhoulai
 * PackageName: com.example.dragger.dialogapp.widget
 * ProjectName: ProjectTest01
 * Date: 2019/6/18 15:06
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private int[] colors = new int[]{Color.rgb(0, 0, 255), Color.rgb(0, 255, 255), Color.rgb(255, 0, 255), Color.rgb(255, 0, 0)};




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(parent.getContext().getResources().getDisplayMetrics().widthPixels/3-30,300));
//        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300));
        Log.i("SmartLayoutManager", "onCreateViewHolder");
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i("SmartLayoutManager", "onBindViewHolder");
        TextView tv_label = holder.itemView.findViewById(R.id.tv_label);
        tv_label.setBackgroundColor(colors[position % colors.length]);
        tv_label.setText("item:" + position);
    }

    @Override
    public int getItemCount() {
        return 33;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}