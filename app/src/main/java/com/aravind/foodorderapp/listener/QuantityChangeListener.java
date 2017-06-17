package com.aravind.foodorderapp.listener;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by Aravindraj on 7/24/2016.
 */
public interface QuantityChangeListener {
    public void onQuantityChange(String menuid, int count, int price, View cardv);
}
