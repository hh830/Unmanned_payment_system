package org.techtown.home2;

import android.graphics.Movie;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class Home1Fragment extends Fragment {

    private ArrayList<ArrayList<Horizontal_data>> list = new ArrayList<ArrayList<Horizontal_data>>();
    VerticalAdapter verticalAdapter;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home1, container, false);

        this.initializeData();

        verticalAdapter = new VerticalAdapter(getContext(),list);
        verticalAdapter.setHasStableIds(true);

        recyclerView = rootView.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(verticalAdapter);
        recyclerView.addItemDecoration(new ItemDecoration(getContext())); //아이템 항목 구분


        viewFlipper = rootView.findViewById(R.id.viewFlipper);
        viewFlipper.startFlipping();
        viewFlipper.setFlipInterval(2500);


       return rootView;
    }

    public void initializeData()
    {

        ArrayList<Horizontal_data> mydata1 = new ArrayList();
        mydata1.add(new Horizontal_data(R.drawable.product_1,"[일상미소]샤브샤브300g","15000원"));
        mydata1.add(new Horizontal_data(R.drawable.product_2,"[도제]촉촉한 생식빵","5000원"));
        mydata1.add(new Horizontal_data(R.drawable.product_3,"[존쿡 델리미트]이탈리안 소시지 320g","7000원"));

        ArrayList<Horizontal_data> mydata2 = new ArrayList();
        mydata2.add(new Horizontal_data(R.drawable.product_4,"비말차단 마스크 50매", "10000원"));
        mydata2.add(new Horizontal_data(R.drawable.product_5,"하이트진로 석수 2LX6개","2400원"));
        mydata2.add(new Horizontal_data(R.drawable.product_6,"백도 복숭아 1.8KG","17900원"));

        ArrayList<Horizontal_data> mydata3 = new ArrayList();
        mydata3.add(new Horizontal_data(R.drawable.product_3,"[존쿡 델리미트]이탈리안 소시지 320g","7000원"));
        mydata3.add(new Horizontal_data(R.drawable.product_6,"백도 복숭아 1.8KG","17900원"));
        mydata3.add(new Horizontal_data(R.drawable.product_2,"[도제]촉촉한 생식빵","5000원"));

        list.add(mydata3);
        list.add(mydata2);
        list.add(mydata1);


    }



}