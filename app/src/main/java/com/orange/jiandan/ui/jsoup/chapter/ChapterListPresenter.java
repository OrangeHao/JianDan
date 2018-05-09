package com.orange.jiandan.ui.jsoup.chapter;

import android.text.TextUtils;
import android.util.Log;

import com.orange.jiandan.base.BaseActivity;
import com.orange.jiandan.base.BasePresenter;
import com.orange.jiandan.ui.jsoup.bean.Chapter;
import com.orange.jiandan.ui.jsoup.books.Book;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * created by czh on 2018/5/7
 */
public class ChapterListPresenter extends BasePresenter<ChapterListView> {

    private Book mBook;

    public ChapterListPresenter(BaseActivity context,Book book) {
        super(context);
        mBook=book;
    }


    @Override
    public void subscribe() {
        Disposable disposable=Observable.create(new ObservableOnSubscribe<List<Chapter>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Chapter>> e) throws Exception {
                Document doc = Jsoup.connect(mBook.getUrl())
                        .get();
                Elements chapters;
                chapters=doc.getElementsByClass(mBook.getChapter()).select("a[href]");
                if (TextUtils.isEmpty(chapters.toString())){
                    chapters=doc.getElementById(mBook.getChapter()).getElementsByTag("a");
                }
                List<Chapter> chapterList=new ArrayList<Chapter>();
                Log.d("czh","get:"+chapters.get(0).toString());
                if (mBook.isHasExtraUrl()){
                    for (Element element:chapters){
                        chapterList.add(new Chapter(mBook.getBaseUrl()+element.attr("href"),element.text()));
                    }
                }else {
                    for (Element element:chapters){
                        chapterList.add(new Chapter(mBook.getUrl()+element.attr("href"),element.text()));
                    }
                }
                Log.d("czh","get:"+chapterList.get(0).getUrl());
                e.onNext(chapterList);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Chapter>>() {
                    @Override
                    public void accept(List<Chapter> list) throws Exception {
                        getView().getChapters(list);
                    }
                });
        mCompositeDisposable.add(disposable);
    }


}
