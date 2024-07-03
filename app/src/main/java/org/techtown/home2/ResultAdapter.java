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

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    private ArrayList<ResultData> myData_db;
    private Context context;

    public ResultAdapter(Context context, ArrayList<ResultData> data)
    {
        this.context=context;
        this.myData_db=data;
    }

    @NonNull
    @Override
    public ResultAdapter.ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_result_item, parent, false);

        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultAdapter.ResultViewHolder holder, int position) {
        holder.onBind(myData_db.get(position));
    }

    @Override
    public int getItemCount() {
        return myData_db.size();
    }


    public class ResultViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_1;
        private TextView tv_2;
        private ImageView iv_1;
        public ResultViewHolder(View itemView) {
            super(itemView);
            tv_1=itemView.findViewById(R.id.tv_1);
            tv_2=itemView.findViewById(R.id.tv_2);
            iv_1=itemView.findViewById(R.id.IV1);

            iv_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Bundle bundle = new Bundle();

                        bundle.putString("name", myData_db.get(pos).getPrdNm());
                        bundle.putString("price", myData_db.get(pos).getBrandNm());
                        //bundle.putInt("price",myData_db.get(pos).getPrice());

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

        public void onBind(ResultData resultData) {
            tv_1.setText(resultData.getPrdNm());
            tv_2.setText(resultData.getBrandNm());

        }
    }
}