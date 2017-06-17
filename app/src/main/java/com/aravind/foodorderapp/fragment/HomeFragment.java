package com.aravind.foodorderapp.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.aravind.foodorderapp.FoodOrderApplication;
import com.aravind.foodorderapp.R;
import com.aravind.foodorderapp.listener.QuantityChangeListener;
import com.aravind.foodorderapp.model.FoodItem;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements QuantityChangeListener {
    ArrayList<FoodItem> mainArray = new ArrayList<FoodItem>();
    ArrayList<String> keyArray = new ArrayList<>();
    ArrayList<FoodItem> checkoutArray = new ArrayList<FoodItem>();
    ViewPager viewPager;
    TabLayout tabLayout;
    LinearLayout footer;
    ViewPagerAdapter adapter;
    int totalprice = 0;
    int totalcount = 0;
    TextView price_txt, counttxt;
    ImageView mDummyImgView, mCheckoutImgView;

    int actionbarheight;


    @Override
    public void onResume() {
        super.onResume();
        Log.e("HomeFragVisib", "res==");
//        setupViewPager();


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Food Order App");
        viewPager.getAdapter().notifyDataSetChanged();
        totalprice = 0;
        checkoutArray = new ArrayList<FoodItem>();

        for (int t = 0; t < mainArray.size(); t++) {
            if (mainArray.get(t).getCount() > 0) {
                checkoutArray.add(mainArray.get(t));
                totalprice = totalprice + mainArray.get(t).getCount() * Integer.parseInt(mainArray.get(t).getPrice());
            }
            price_txt.setText(getResources().getString(R.string.rs) + totalprice);
        }
        totalcount = 0;
        for (int t = 0; t < mainArray.size(); t++) {

            totalcount = totalcount + mainArray.get(t).getCount();
        }
        counttxt.setText("" + totalcount);


    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.e("HomeFragVisib", "crvi==");
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mainArray = bundle.getParcelableArrayList("TotalArray");
            keyArray = bundle.getStringArrayList("KeyArray");
        }
        footer = (LinearLayout) v.findViewById(R.id.footer);
        price_txt = (TextView) v.findViewById(R.id.price);
        counttxt = (TextView) v.findViewById(R.id.counttxt);
        mDummyImgView = (ImageView) v.findViewById(R.id.img_cpy);
        mCheckoutImgView = (ImageView) v.findViewById(R.id.chk_icon);


        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) v.findViewById(R.id.tabs);

        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getContext().getResources().getColor(R.color.black), getContext().getResources().getColor(R.color.white));
        TypedValue tv = new TypedValue();
        if (getContext().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionbarheight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }

        return v;

    }

    private void setupViewPager() {

        adapter = new ViewPagerAdapter(this);
        for (int i = 0; i < keyArray.size(); i++) {
            Log.e("intt", "=0" + i);
            ArrayList<FoodItem> mi = new ArrayList<FoodItem>();
            for (int u = 0; u < mainArray.size(); u++) {
                if (mainArray.get(u).getCategory().equals(keyArray.get(i))) {
                    mi.add(mainArray.get(u));
                }
            }

            Bundle b = new Bundle();
            b.putParcelableArrayList("array", mi);
            MenuFragment fragobj = new MenuFragment();
            fragobj.setListener(this);
            fragobj.setArguments(b);
            adapter.addFragment(fragobj, keyArray.get(i).toString());

        }


        viewPager.setAdapter(adapter);
    }


    @Override
    public void onQuantityChange(String menuid, int count, int price, View cardv) {
        if (cardv != null) {
            Bitmap b = FoodOrderApplication.getInstance().loadBitmapFromView(cardv, cardv.getWidth(), cardv.getHeight());
            animateView(cardv, b);
        }
        for (int i = 0; i < mainArray.size(); i++) {
            if (mainArray.get(i).getMenuid().equals(menuid)) {
                totalprice = totalprice + count * Integer.parseInt(mainArray.get(i).getPrice());
                totalcount = totalcount + count;
                if (mainArray.get(i).getCount() > 0) {
                    if (checkoutArray.contains(mainArray.get(i))) {

                    } else
                        checkoutArray.add(mainArray.get(i));

                } else {
                    if (checkoutArray.contains(mainArray.get(i))) {

                        checkoutArray.remove(mainArray.get(i));
                    }

                }

            }
        }
        price_txt.setText(getResources().getString(R.string.rs) + totalprice);

        counttxt.setText("" + totalcount);
        Animation shake;
        shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        mCheckoutImgView.setAnimation(shake);

    }

    private void animateView(View foodCardView, Bitmap b) {
        mDummyImgView.setImageBitmap(b);
        mDummyImgView.setVisibility(View.VISIBLE);
        int u[] = new int[2];
        mCheckoutImgView.getLocationInWindow(u);
        mDummyImgView.setLeft(foodCardView.getLeft());
        mDummyImgView.setTop(foodCardView.getTop());
        AnimatorSet animSetXY = new AnimatorSet();
        ObjectAnimator y = ObjectAnimator.ofFloat(mDummyImgView, "translationY", mDummyImgView.getTop(), u[1] - (2 * actionbarheight));
        ObjectAnimator x = ObjectAnimator.ofFloat(mDummyImgView, "translationX", mDummyImgView.getLeft(), u[0] - (2 * actionbarheight));
        ObjectAnimator sy = ObjectAnimator.ofFloat(mDummyImgView, "scaleY", 0.8f, 0.1f);
        ObjectAnimator sx = ObjectAnimator.ofFloat(mDummyImgView, "scaleX", 0.8f, 0.1f);
        animSetXY.playTogether(x, y, sx, sy);
        animSetXY.setDuration(650);
        animSetXY.start();
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        public ViewPagerAdapter(android.support.v4.app.Fragment fragment) {
            super(fragment.getChildFragmentManager());

            // write your code here
        }

        @Override
        public int getItemPosition(Object object) {
            // POSITION_NONE makes it possible to reload the PagerAdapter
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
