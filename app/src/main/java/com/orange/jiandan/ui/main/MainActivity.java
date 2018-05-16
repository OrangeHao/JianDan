package com.orange.jiandan.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.orange.jiandan.R;
import com.orange.jiandan.base.BaseActivity;
import com.orange.jiandan.model.novel.BookMessage;
import com.orange.jiandan.ui.books.BookListActivity;
import com.orange.jiandan.ui.home.HomeFragment;
import com.orange.jiandan.ui.jsoup.books.BooksActivity;
import com.orange.jiandan.utils.L;
import com.orange.jiandan.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    private HomeFragment mHomeFragment;
    private long exitTime;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //初始化Fragment
        initFragments();
        //初始化侧滑菜单
        initNavigationView();


//        test();
    }




    private void test(){
        Single.create(new SingleOnSubscribe<List<BookMessage>>() {
            @Override
            public void subscribe(SingleEmitter<List<BookMessage>> emitter) {
                List<BookMessage> list=new ArrayList<>();
                L.debug("thread:"+android.os.Process.myTid());
                emitter.onSuccess(list);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<BookMessage>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<BookMessage> bookMessages) {
                        L.debug("thread:"+android.os.Process.myTid());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }









    private void initFragments(){
        mHomeFragment=HomeFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container,mHomeFragment)
                .show(mHomeFragment).commit();
    }

    private void initNavigationView(){
        mNavigationView.setNavigationItemSelectedListener(this);

        mNavigationView.setItemIconTintList(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.nav_camera:
                BookListActivity.start(mContext);
                break;
            case R.id.nav_books:
                BooksActivity.start(mContext);
                break;
        }
        return false;
    }


    /**
     * 双击退出App
     */
    private void exitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtil.ShortToast("再按一次退出");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }


    /**
     * 解决App重启后导致Fragment重叠的问题
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    /**
     * DrawerLayout侧滑菜单开关
     */
    public void toggleDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }
}
