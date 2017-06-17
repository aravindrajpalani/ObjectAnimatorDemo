package com.aravind.foodorderapp.adapter;

/**
 * Created by Aravindraj on 6/19/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aravind.foodorderapp.FoodOrderApplication;
import com.aravind.foodorderapp.R;
import com.aravind.foodorderapp.listener.QuantityChangeListener;
import com.aravind.foodorderapp.model.FoodItem;

import java.util.ArrayList;


public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.SolventViewHolders> {

    private ArrayList<FoodItem> itemList;
    private Context context;
    QuantityChangeListener mQcl;
    public FoodItemAdapter(Activity a, Context context, ArrayList<FoodItem> itemList, QuantityChangeListener qcl) {
        this.itemList = itemList;
        this.context = context;
        this.mQcl = qcl;
    }
    @Override
    public SolventViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.solvent_list, parent, false);

        return new SolventViewHolders(itemView);

    }
    @Override
    public void onBindViewHolder(final SolventViewHolders holder, final int position) {
        holder.countryName.setText(itemList.get(position).getName());
        holder.price.setText(context.getResources().getString(R.string.rs) + " " + itemList.get(position).getPrice());
        holder.oldprice.setText(context.getResources().getString(R.string.rs) + " " + itemList.get(position).getOldprice());
        holder.oldprice.setPaintFlags(holder.oldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.count.setText("" + itemList.get(position).getCount());
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = itemList.get(position).getCount();
                itemList.get(position).setCount(count + 1);
                Log.e("adaptercalled", "=cl" + itemList.get(position).getCount());
                mQcl.onQuantityChange(itemList.get(position).getMenuid(), 1, Integer.parseInt(itemList.get(position).getPrice()), holder.mcard);
                int e[] = new int[2];
                holder.mcard.getLocationInWindow(e);
                Log.e("tuxe", "=" + e[0]);
                Log.e("tuye", "=" + e[1]);
                notifyDataSetChanged();
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = itemList.get(position).getCount();
                if (count > 0) {
                    itemList.get(position).setCount(count - 1);
                    mQcl.onQuantityChange(itemList.get(position).getMenuid(), -1, -(Integer.parseInt(itemList.get(position).getPrice())), null);
                    notifyDataSetChanged();
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


    public class SolventViewHolders extends RecyclerView.ViewHolder  {

        public TextView countryName, count, price, oldprice;
        public ImageView plus, minus;

        CardView mcard;

        public SolventViewHolders(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.country_name);
            mcard = (CardView) itemView.findViewById(R.id.card_view);
            price = (TextView) itemView.findViewById(R.id.price);
            oldprice = (TextView) itemView.findViewById(R.id.oldprice);
            plus = (ImageView) itemView.findViewById(R.id.add);
            minus = (ImageView) itemView.findViewById(R.id.minus);
            count = (TextView) itemView.findViewById(R.id.count);

        }


    }
}