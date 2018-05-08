package com.orange.jiandan.base;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import com.orange.jiandan.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 *
 */
public abstract class BaseActivity<V,T extends BasePresenter> extends AppCompatActivity {

    protected T mPresenter;
    protected Context mContext;

    private Toolbar mToolBar;
    private TextView mTitle;
    private TextView mRightText;

    protected  T createPresenter(){
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        mContext=this;
        mPresenter=createPresenter();
        if(mPresenter!=null){
            mPresenter.attachView((V)this);
        }

        setUpAppBar();

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


    /**---------------------- toolbar  --------------------**/

    private void setUpAppBar(){
        if (includeAppBar()){
            mToolBar=(Toolbar)findViewById(R.id.toolbar_layout);
            mTitle=(TextView)findViewById(R.id.appbar_title);
            mRightText=(TextView)findViewById(R.id.appbar_right_text);
            if (enableReturnBtn() && mToolBar!=null){
                mToolBar.setNavigationIcon(R.drawable.base_icon_return_white);
                mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        }
    }


    protected boolean includeAppBar(){
        return true;
    }

    protected boolean enableReturnBtn(){ return true; }

    protected void setBarTitle(String string){
        mTitle.setText(string);
    }

    protected void setToolBarScrollEnable(boolean scroll){
        if (mToolBar==null){
            return;
        }
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) mToolBar.getLayoutParams();
        if (scroll){
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        }else {
            params.setScrollFlags(0);
        }
    }
    protected void setBarTitleColor(int colorId){
        mTitle.setTextColor(getResources().getColor(colorId));
    }

    protected void setBarColor(int colorId){
        mToolBar.setBackgroundColor(getResources().getColor(colorId));
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
