package com.orange.jiandan.ui.news;

import android.content.Context;

import com.orange.jiandan.base.BasePresenter;
import com.orange.jiandan.data.DataResource;
import com.orange.jiandan.data.DataResourceSwitch;
import com.orange.jiandan.model.NewsDetail;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * created by czh on 2018/7/28
 */
public class NewsDetailPresenter extends BasePresenter<NewsDetailView>{

    protected DataResource mDataRepository;

    public NewsDetailPresenter(Context context) {
        super(context);
        mDataRepository= DataResourceSwitch.getDataResource();
    }

    @Override
    public void subscribe() {

    }

    public void getNewsData(int id){
        mDataRepository.getNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<NewsDetail.PostBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(NewsDetail.PostBean postBean) {
                        getView().getNewsDetail(postBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


}
