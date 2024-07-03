package org.techtown.home2;

import android.content.Context;
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
import java.util.HashMap;

public class Home2Adapter extends RecyclerView.Adapter<Home2Adapter.Home2ViewHolder> {
    private ArrayList<Home2Data> myData_best;
    //private ArrayList<HashMap<String, String>>mArrayList;
    private Context context;


    public Home2Adapter(Context context, ArrayList<Home2Data> data) {
        this.context=context;
        this.myData_best=data;
    }




    @NonNull
    @Override
    public Home2Adapter.Home2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home2_item, parent, false);
        return new Home2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Home2Adapter.Home2ViewHolder holder, int position) {
        //Home2Data dataModel = myData_best.get(position);
        holder.onBind(myData_best.get(position));
    }

    @Override
    public int getItemCount() {
        return myData_best.size();
    }

    void addItem(Home2Data data){
        myData_best.add(data);
    }


    public class Home2ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView3;
        private TextView textView4;
        private ImageView imageView;

        public Home2ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            imageView = itemView.findViewById(R.id.imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Bundle bundle = new Bundle();

                        bundle.putString("name", myData_best.get(pos).getName());
                        bundle.putString("price", myData_best.get(pos).getPrice());
                        bundle.putInt("id",myData_best.get(pos).getResId());

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

        void onBind(Home2Data data) {
            textView3.setText(data.getName());
            textView4.setText(data.getPrice());
            imageView.setImageResource(data.getResId());
        }
    }


}
