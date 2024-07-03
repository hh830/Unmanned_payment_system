package org.techtown.home2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home2Fragment extends Fragment {
    private ArrayList<Home2Data> bestlist =new ArrayList<>();
    RecyclerView recyclerView;
    Home2Adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home2, container, false);
        //this.initializeDate();


        recyclerView = rootView.findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new Home2Adapter(getContext(), bestlist);
        recyclerView.setAdapter(adapter);


        return rootView;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }
    /*private void initializeDate() {

    }*/
    private void getData() {

        bestlist.add(new Home2Data(R.drawable.product_1, "[일상미소]샤브샤브 300g", "15000원"));
        bestlist.add(new Home2Data(R.drawable.product_2, "[도제]촉촉한 생식빵", "5000원"));
        bestlist.add(new Home2Data(R.drawable.product_3, "[존쿡 델리미트]이탈리안 소시지 320g","7000원"));
        bestlist.add(new Home2Data(R.drawable.product_4,"비말차단 마스크 50매", "10000원"));
        bestlist.add(new Home2Data(R.drawable.product_5,"하이트진로 석수 2LX6개","2400원"));
        bestlist.add(new Home2Data(R.drawable.product_6,"백도 복숭아 1.8KG","17900원"));

        bestlist.add(new Home2Data(R.drawable.product_1, "[일상미소]샤브샤브 300g", "15000원"));
        bestlist.add(new Home2Data(R.drawable.product_6,"백도 복숭아 1.8KG","17900원"));
        bestlist.add(new Home2Data(R.drawable.product_4,"비말차단 마스크 50매", "10000원"));
        bestlist.add(new Home2Data(R.drawable.product_2, "[도제]촉촉한 생식빵", "5000원"));
        bestlist.add(new Home2Data(R.drawable.product_5,"하이트진로 석수 2LX6개","2400원"));
        bestlist.add(new Home2Data(R.drawable.product_3, "[존쿡 델리미트]이탈리안 소시지 320g","7000원"));


   //     bestlist.add(mybestdata1);
    }

}