package org.techtown.home2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InformationFragment extends Fragment {
    ImageView imageView;
    TextView textView1;
    TextView textView2;
    String name;
    String price;
    int resId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_information, container, false);

        imageView = rootView.findViewById(R.id.image_image);
        textView1 = rootView.findViewById(R.id.name_text);
        textView2 = rootView.findViewById(R.id.price_text);

        Bundle bundle = getArguments();
        name = bundle.getString("name");
        price = bundle.getString("price");
        resId = bundle.getInt("id");

        //imageView.setImageResource(resId);
        textView1.setText(name);
        textView2.setText(price);

        return rootView;

    }
}
