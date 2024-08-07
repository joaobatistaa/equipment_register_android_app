package com.android.inventariocmrm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Equipamentos extends AppCompatActivity {

    private long exitTime = 0;
    MyApplication myApplication;
    View view;
    private BottomNavigationView navigation;
    public static ViewPager viewPager;
    private Toolbar toolbar;
    MenuItem prevMenuItem;
    int pager_number = 5;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lsv_item_equipamentos);
        view = findViewById(android.R.id.content);
        myApplication = MyApplication.getInstance();
        viewPager = findViewById(R.id.viewpager);

    }

}
