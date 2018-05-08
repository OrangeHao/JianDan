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
 * created by czh on 2018/5/8
 */
public class ChapterContentPresenter<ChapterContentView> extends BasePresenter{

    private Book mBook;

    public ChapterContentPresenter(BaseActivity context, Book book) {
        super(context);
        mBook=book;
    }


    @Override
    public void subscribe() {

    }

    public void getContent(String url,int position){
        Disposable disposable= Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Document doc = Jsoup.connect(url)
                        .get();
                Element sections=doc.getElementById(mBook.getChaptertext());
//                String content=tagfilter(sections.toString());
                String content=sections.toString();
                Log.d("czh",content);
                e.onNext(content);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String content) throws Exception {
                        ( (com.orange.jiandan.ui.jsoup.chapter.ChapterContentView)getView()).receivedContent(content,position);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

}
