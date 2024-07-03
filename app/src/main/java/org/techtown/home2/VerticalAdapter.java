package org.techtown.home2;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VerticalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<ArrayList<Horizontal_data>> myData_v;
    private Context context;
    public static final int Flipper_VIEW = 1;
    public static final int Product_VIEW = 2;
    public String[] vertical_text = {"신상품", "놓치면 후회할 가격", "당신을 위한 추천"};
    int i = 0;


    public VerticalAdapter(Context context, ArrayList<ArrayList<Horizontal_data>> data)
    {
        this.context = context;
        this.myData_v = data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        //view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home1_vertical, null);

        if(viewType == Flipper_VIEW){
            view =LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home1_vertical, parent, false);
            return new FlipperVIewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home1_vertical, parent, false);
            return new VerticalViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        HorizontalAdapter adapter = new HorizontalAdapter(myData_v.get(position),context);

        if(holder instanceof FlipperVIewHolder){
            ((FlipperVIewHolder)holder).viewFlipper.startFlipping();
            ((FlipperVIewHolder)holder).viewFlipper.setFlipInterval(2000);

        }
        else{
            ((VerticalViewHolder) holder).recyclerView.setAdapter(adapter);
            ((VerticalViewHolder) holder).recyclerView.setLayoutManager(new LinearLayoutManager(context
                    , LinearLayoutManager.HORIZONTAL
                    ,false));
            ((VerticalViewHolder) holder).recyclerView.setHasFixedSize(true);
            ((VerticalViewHolder) holder).textView.setText(vertical_text[i++]);

        }

/*
        HorizontalAdapter adapter = new HorizontalAdapter(myData_v.get(position));

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context
                , LinearLayoutManager.HORIZONTAL
                ,false));
        holder.recyclerView.setAdapter(adapter);
*/
    }

    @Override
    public int getItemCount() {
        return myData_v.size();
    }
/*
    @Override
    public int getItemViewType(int position) {
        return 1;
    }
*/


}
