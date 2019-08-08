package me.teenyda.flycotabtest;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SlidingTabLayout main_tab;

    private ViewPager main_vp;

    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_tab = findViewById(R.id.main_tab);
        main_vp = findViewById(R.id.main_vp);

        mFragments = new ArrayList<>();
        mFragments.add(new ListFragment());
        mFragments.add(new ListFragment());
        mFragments.add(new ListFragment());

        String[] title = new String[]{"列表1", "列表2", "列表3"};

        main_tab.setViewPager(main_vp, title, this, mFragments);

        main_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                // tab被选中时刷新数据
                ListFragment listFragment = (ListFragment) mFragments.get(i);
                listFragment.getList();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        ListFragment listFragment = (ListFragment) mFragments.get(0);
        listFragment.getList();
    }
}
