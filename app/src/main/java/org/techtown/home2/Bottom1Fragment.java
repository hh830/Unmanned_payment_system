package org.techtown.home2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

public class Bottom1Fragment extends Fragment {
    LinearLayout linearLayout;
    Home1Fragment home1_f;
    Home2Fragment home2_f;
    Home3Fragment home3_f;
    TabLayout tabs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_bottom1, container, false);


        home1_f = new Home1Fragment();
        home2_f = new Home2Fragment();
        home3_f = new Home3Fragment();

        linearLayout = rootView.findViewById(R.id.container2);

        FragmentManager child = getChildFragmentManager();
        child.beginTransaction().add(R.id.container2,home1_f).commit();

         tabs = rootView.findViewById(R.id.tabs);
         tabs.addTab(tabs.newTab().setText("추천"));
         tabs.addTab(tabs.newTab().setText("베스트"));
         tabs.addTab(tabs.newTab().setText("이벤트"));

         tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
             @Override
             public void onTabSelected(TabLayout.Tab tab) {
                 int position = tab.getPosition();
                 if(position ==0){
                     getChildFragmentManager().beginTransaction().replace(R.id.container2,home1_f).commit();
                 }else if(position == 1){
                     getChildFragmentManager().beginTransaction().replace(R.id.container2,home2_f).commit();
                 }else if(position == 2){
                     getChildFragmentManager().beginTransaction().replace(R.id.container2,home3_f).commit();
                 }
             }

             @Override
             public void onTabUnselected(TabLayout.Tab tab) {

             }

             @Override
             public void onTabReselected(TabLayout.Tab tab) {

             }
         });


        return rootView;

    }

}