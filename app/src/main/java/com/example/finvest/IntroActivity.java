package com.example.finvest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class IntroActivity extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private Button navigateNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        navigateNext = findViewById(R.id.navigate_next);

        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new SalaryFragment());
        fragments.add(new PercentageFragment());
        fragments.add(new TimeFragment());
        fragments.add(new RiskFragment());

        pager = findViewById(R.id.viewpager);
        pager.setAdapter(new IntroPagerAdapter(getSupportFragmentManager(), fragments));

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

        navigateNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pager.getCurrentItem() != fragments.size()-1) {
                    pager.setCurrentItem(pager.getCurrentItem()+1);
                }else {
                    Intent intent = new Intent(IntroActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(pager.getCurrentItem() == 0) {
            super.onBackPressed();
        }else {
            pager.setCurrentItem(pager.getCurrentItem()-1);
        }
    }

    private class IntroPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> fragments;
        public IntroPagerAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}