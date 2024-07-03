package org.techtown.home2;

import android.content.Context;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder>{

    private ArrayList<Horizontal_data> myData = new ArrayList<>();
    Context context;

    public HorizontalAdapter(ArrayList<Horizontal_data> data, Context context)
    {
        this.myData = data;
        this.context = context;
    }

    @NonNull
    @Override
    public HorizontalAdapter.HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home1_horizon,parent,false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalAdapter.HorizontalViewHolder holder, int position) {
        holder.onBind(myData.get(position));
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder{

        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;

        HorizontalViewHolder(View view){
            super(view);

            textView1 = view.findViewById(R.id.textView1);
            textView2 = view.findViewById(R.id.textView2);
            imageView = view.findViewById(R.id.imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Bundle bundle = new Bundle();
                        
                        bundle.putString("name", myData.get(pos).getName());
                        bundle.putString("price", myData.get(pos).getPrice());
                        bundle.putInt("id",myData.get(pos).getResId());

                        InformationFragment infor_f = new InformationFragment();
                        infor_f.setArguments(bundle);

                        FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container2,infor_f);
                        transaction.addToBackStack(null);//back 클릭시 이전 프레그먼트로 이동
                        transaction.commit();

                    }
                }
            });

        }

        void onBind(Horizontal_data data) {
            textView1.setText(data.getName());
            textView2.setText(data.getPrice());
            imageView.setImageResource(data.getResId());
        }
    }

}
