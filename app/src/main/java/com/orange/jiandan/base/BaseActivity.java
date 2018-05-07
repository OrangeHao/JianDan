package com.orange.jiandan.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 *
 */
public abstract class BaseActivity<V,T extends BasePresenter> extends AppCompatActivity {

    protected T mPresenter;

    protected  T createPresenter(){
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        mPresenter=createPresenter();
        if(mPresenter!=null){
            mPresenter.attachView((V)this);
        }

        initView();
        initRecyclerView();
        initRefreshLayout();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.unsubscribe();
        }
    }

    /**
     * 设置布局layout
     *
     * @return
     */
    public abstract int getLayoutId();


    protected void initView(){ }

    /**
     * 加载数据
     */
    protected void loadData() {
    }

    /**
     * 初始化recyclerView
     */
    protected void initRecyclerView() {
    }

    /**
     * 初始化refreshLayout
     */
    protected void initRefreshLayout() {
    }

}
