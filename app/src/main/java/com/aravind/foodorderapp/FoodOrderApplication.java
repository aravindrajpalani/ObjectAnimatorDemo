package com.aravind.foodorderapp;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aravind on .
 */
public class FoodOrderApplication extends Application {

    private static FoodOrderApplication taqwaApplication;
    private SharedPreferences mSharedPreferences;
    private final String JSONPREFIX = "JSON";
    private ProgressDialog mProgress;

    //public MixpanelAPI mixpanel;

    //Constructor to initialize
    @Override
    public void onCreate() {
        super.onCreate();
        taqwaApplication = this;

    }

    /***/
    public static synchronized FoodOrderApplication getInstance() {
        if (taqwaApplication == null)
            taqwaApplication = new FoodOrderApplication();
        return taqwaApplication;
    }

    /**
     * Used to initialize SharedPreferences
     */
    public void initSharedPreferences() {
        if (mSharedPreferences == null)
            mSharedPreferences = getSharedPreferences("taqwa", MODE_PRIVATE);
    }

    /**
     * Used to get the SharedPref instance
     */
    private SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    /**
     * Used to get boolean
     */
    public boolean getBoolean(String key) {
        if (getSharedPreferences() != null)
            return getSharedPreferences().getBoolean(key, false);
        else
            return false;
    }

    /**
     * Used to save boolean
     */
    public void saveBoolean(String key, boolean value) {
        if (getSharedPreferences() != null)
            getSharedPreferences().edit().putBoolean(key, value).commit();
    }

    /*Check Network*/


    /**
     * Used to get String
     */
    public String getString(String key) {
        if (getSharedPreferences() != null)
            return getSharedPreferences().getString(key, "");
        else
            return "";
    }

    /**
     * Used to save String
     */
    public void saveString(String key, String value) {
        if (getSharedPreferences() != null)
            getSharedPreferences().edit().putString(key, value).commit();
    }


    /**
     * Used to get Int
     */
    public int getInt(String key) {
        if (getSharedPreferences() != null)
            return getSharedPreferences().getInt(key, 0);
        else
            return 0;
    }

    /**
     * Used to save Int
     */
    public void saveInt(String key, int value) {
        if (getSharedPreferences() != null)
            getSharedPreferences().edit().putInt(key, value).commit();
    }


    /**
     * Used to store JSON Object as string
     */
    public void saveJSONObject(String key, JSONObject object) {
        if (getSharedPreferences() != null)
            getSharedPreferences().edit().putString(JSONPREFIX.concat(key), object.toString()).commit();
    }


    /**
     * Used to load JSON Object from string
     */
    public JSONObject loadJSONObject(String key) throws JSONException {
        if (getSharedPreferences() != null)
            return new JSONObject(getSharedPreferences().getString(JSONPREFIX.concat(key), "{}"));
        else
            return null;
    }





    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void showLoadingProgress(Context context) {
        mProgress = new ProgressDialog(context);
        mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgress.setCancelable(true);
        mProgress.show();

    }

    public void cancelProgress() {
        if (mProgress != null) {
            if (mProgress.isShowing())
                mProgress.hide();
        }
    }


    public Bitmap loadBitmapFromView(View view, int width, int height) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);

        Log.e("width", "=" + width);
        Log.e("height","="+height);
        return returnedBitmap;
    }
}
