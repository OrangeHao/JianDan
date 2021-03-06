package com.orange.jiandan.ui.home;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseLazyFragment;

import butterknife.BindView;

/**
 */
public class HomeFragment extends BaseLazyFragment {


    @BindView(R.id.sliding_tabs)
    SlidingTabLayout mSlidingTab;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private HomePagerAdapter mHomeAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void fetchData() {

    }

    @Override
    protected void initView() {
        initViewpager();
    }

    private void initViewpager(){
        mHomeAdapter=new HomePagerAdapter(getChildFragmentManager(),getApplicationContext());
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(mHomeAdapter);
        mSlidingTab.setViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
    }

}
