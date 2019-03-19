package com.portillo.naomyportillo.viewpagerappfromscratch.viewpager;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.portillo.naomyportillo.viewpagerappfromscratch.R;
import com.squareup.picasso.Picasso;

public class ViewPagerFragment extends Fragment {

     private static final String ZODIAC_NAME = "zodiac_name";
        private static final String ZODIAC_IMAGE = "zodiac_image";
        private static final String ZODIAC_NUMBER = "zodiac_number";

        private String name;
        private String imageUrl;
        private String number;
        private TextView nametextView;
        private TextView numbertextView;
        private ImageView imageView;

        public static ViewPagerFragment newInstance(String name, String imageUrl, String number){
            ViewPagerFragment fragment = new ViewPagerFragment();
            Bundle args = new Bundle();
            args.putString(ZODIAC_NAME, name);
            args.putString(ZODIAC_IMAGE, imageUrl);
            args.putString(ZODIAC_NUMBER, number);
            fragment.setArguments(args);
            return fragment;
        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            name = getArguments().getString(ZODIAC_NAME);
            imageUrl = getArguments().getString(ZODIAC_IMAGE);
            number = getArguments().getString(ZODIAC_NUMBER);
        }
    }

    public ViewPagerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nametextView= view.findViewById(R.id.viewpager_nametextView);
        numbertextView = view.findViewById(R.id.viewpager_numbertextView);
        imageView = view.findViewById(R.id.viewpager_imageview);

        nametextView.setText(name);
        numbertextView.setText(number);
        Picasso.get().load(imageUrl).into(imageView);

    }
}
