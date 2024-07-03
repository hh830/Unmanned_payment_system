package org.techtown.home2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VerticalViewHolder extends RecyclerView.ViewHolder{
    public RecyclerView recyclerView;
    public TextView textView;

    public VerticalViewHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.recycler_textView);
        this.recyclerView = (RecyclerView)itemView.findViewById(R.id.recyclerView);
    }
}
