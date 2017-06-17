package com.aravind.foodorderapp.fragment;

/**
 * Created by Aravindraj on 6/19/2016.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.aravind.foodorderapp.R;
import com.aravind.foodorderapp.adapter.FoodItemAdapter;
import com.aravind.foodorderapp.listener.QuantityChangeListener;
import com.aravind.foodorderapp.model.FoodItem;

import java.util.ArrayList;


public class MenuFragment extends Fragment  {

    private GridLayoutManager mGridLayoutManager;
    RecyclerView recyclerView;
    FoodItemAdapter foodItemAdapter;
    public ArrayList<FoodItem> foodArrayList;
    QuantityChangeListener mQuantityChangeListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        Bundle args = getArguments();
        foodArrayList = args.getParcelableArrayList("array");
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mGridLayoutManager);
        foodItemAdapter = new FoodItemAdapter(getActivity(), getActivity(), foodArrayList, mQuantityChangeListener);
        recyclerView.setAdapter(foodItemAdapter);
        return v;

    }

    public void setListener(QuantityChangeListener listener) {
        this.mQuantityChangeListener=listener;
    }


}