package com.orange.jiandan.ui.news;

import android.content.Context;

import com.orange.jiandan.base.BaseActivity;
import com.orange.jiandan.base.BasePresenter;
import com.orange.jiandan.data.DataResource;
import com.orange.jiandan.data.DataResourceSwitch;
import com.orange.jiandan.model.NewsBean;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * created by czh on 2018/5/9
 */
public class NewsPresenter extends BasePresenter<NewsView>{

    protected DataResource mDataRepository;

    public NewsPresenter(Context context) {
        super(context);
        mDataRepository= DataResourceSwitch.getDataResource();
    }

    @Override
    public void subscribe() {

    }
    
//    public void loadData(int pageIndex,boolean isRefresh){
//        Disposable disposable=mDataRepository.getNews(pageIndex)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((postsBeans) -> {
//                    if (isRefresh){
//                        getView().getRefreshNews(postsBeans);
//                    }else {
//                        getView().getLoadMoreNews(postsBeans);
//                    }
//                });
//        mCompositeDisposable.add(disposable);
//    }
    public void loadDatas(int pageIndex,boolean isRefresh){
        mDataRepository.getNews(pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<NewsBean.PostsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(List<NewsBean.PostsBean> postsBeans) {
                        if (isRefresh){
                            getView().getRefreshNews(postsBeans);
                        }else {
                            getView().getLoadMoreNews(postsBeans);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().getNewsFailed();
                    }
                });
    }


}
