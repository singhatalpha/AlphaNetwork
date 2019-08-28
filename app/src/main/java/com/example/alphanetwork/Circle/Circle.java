package com.example.alphanetwork.Circle;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

//import com.example.alphanetwork.Circle.CircleChatFragment;
import com.example.alphanetwork.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


import Utils.BottomNavigationViewHelper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

public class Circle extends AppCompatActivity{
    private static final String TAG = "Circle";
    private static final int ACTIVITY_NUM = 2;

    private Context mContext = Circle.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: started.");

        setupBottomNavigationView();
        setupViewPager();
    }

    private void setupViewPager(){
        CirclePagerAdapter adapter = new CirclePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CircleEventsFragment()); //index 0
//        adapter.addFragment(new CircleChatFragment()); //index 1
        adapter.addFragment(new CircleWatchFragment()); //index 2
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_events);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_chat);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_watch);
    }

    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}