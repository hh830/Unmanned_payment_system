package org.techtown.home2;

import android.view.View;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FlipperVIewHolder extends RecyclerView.ViewHolder {
    //public RecyclerView recyclerView;
    public ViewFlipper viewFlipper;


    public FlipperVIewHolder(@NonNull View itemView) {
        super(itemView);

        //recyclerView = itemView.findViewById(R.id.recyclerView);

        viewFlipper = itemView.findViewById(R.id.viewFlipper);
        //viewFlipper.startFlipping();
        //viewFlipper.setFlipInterval(2000);
    }
}
