package com.orange.jiandan.ui.pictures;

import android.content.Context;

import com.orange.jiandan.base.BasePresenter;
import com.orange.jiandan.data.DataResource;
import com.orange.jiandan.data.DataResourceSwitch;
import com.orange.jiandan.model.NewsBean;
import com.orange.jiandan.model.PicsBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * created by czh on 2018/5/11
 */
public class NicePicsPresenter extends BasePresenter<NicePicsView>{

    protected DataResource mDataRepository;


    public NicePicsPresenter(Context context) {
        super(context);
        mDataRepository= DataResourceSwitch.getDataResource();
    }

    @Override
    public void subscribe() {

    }

    public void loadDatas(int pageIndex,boolean isRefresh){
        mDataRepository.getNicePics(pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<PicsBean.CommentsBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(List<PicsBean.CommentsBean> commentsBeans) {
                        if (isRefresh){
                            getView().getRefreshDatas(getPicUrls(commentsBeans));
                        }else {
                            getView().getLoadMoreDatas(getPicUrls(commentsBeans));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onFailed(e);
                    }
                });
    }

    private List<String> getPicUrls(List<PicsBean.CommentsBean> data){
        List<String> temp=new ArrayList<>();
        for (PicsBean.CommentsBean bean:data){
            if (bean.getPics()!=null){
                temp.addAll(bean.getPics());
            }
        }
        return temp;
    }
}
