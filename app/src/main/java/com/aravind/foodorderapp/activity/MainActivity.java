package com.aravind.foodorderapp.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.aravind.foodorderapp.R;
import com.aravind.foodorderapp.fragment.HomeFragment;
import com.aravind.foodorderapp.model.FoodItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    ArrayList<String> keyarr;
    public ArrayList<FoodItem> arrItem;
    ArrayList<FoodItem> mCheckOutArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
        }
        mCheckOutArrayList = new ArrayList<FoodItem>();
        keyarr = new ArrayList<>();
        arrItem = new ArrayList<>();
keyarr.add("Tiffin");
        keyarr.add("Veg Rice");
        keyarr.add("Starters");
        arrItem.add(new FoodItem("no description", "180", "200", "", "001", "Tiffin", "Masala Dosa", 0, 0));
        arrItem.add(new FoodItem("no description", "280", "300", "", "002", "Tiffin", "Idli", 0, 0));
        arrItem.add(new FoodItem("no description", "80", "100", "", "003", "Tiffin", "Chappatti", 0, 0));
        arrItem.add(new FoodItem("no description", "99", "150", "", "004", "Tiffin", "Poori", 0, 0));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.primary));
        toolbar.setTitleTextColor(getResources().getColor(R.color.icons));
        final TypedArray styledAttributes = this.getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        int mActionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        HomeFragment firstFragment = new HomeFragment();
        Bundle b = new Bundle();
        b.putParcelableArrayList("TotalArray", arrItem);
        b.putStringArrayList("KeyArray", keyarr);

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        firstFragment.setArguments(b);

        // Add the fragment to the 'fragment_container' FrameLayout
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        fragmentTransaction.replace(R.id.container_body, firstFragment, "h");
        fragmentTransaction.addToBackStack("h");

        fragmentTransaction.commit();

    }






    @Override
    public void onBackPressed() {


        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();

        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            this.finish();
        }

        fm.addOnBackStackChangedListener(getListener());

        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container_body);
        if (f != null)
            Log.e("tagggg", "" + f.getTag());


    }

    private FragmentManager.OnBackStackChangedListener getListener() {
        FragmentManager.OnBackStackChangedListener result = new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                FragmentManager manager = getSupportFragmentManager();

                if (manager != null) {
                    if (manager.findFragmentById(R.id.container_body).getTag().equals("h")) {
                        Fragment currFrag = (Fragment) manager.findFragmentById(R.id.container_body);

                        currFrag.onResume();

                    }
                }
            }
        };

        return result;
    }


}
