package com.orange.jiandan.ui.jsoup.chapter;

import android.text.TextUtils;
import android.util.Log;

import com.orange.jiandan.base.BaseActivity;
import com.orange.jiandan.base.BasePresenter;
import com.orange.jiandan.model.novel.BookMessage;
import com.orange.jiandan.model.novel.ChapterContent;
import com.orange.jiandan.model.novel.ChapterMessage;
import com.orange.jiandan.model.novel.NovelDB;
import com.orange.jiandan.ui.jsoup.bean.Chapter;
import com.orange.jiandan.ui.jsoup.books.Book;
import com.orange.jiandan.utils.L;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * created by czh on 2018/5/8
 */
public class ChapterContentPresenter extends BasePresenter<ChapterContentView> {

    private BookMessage mBook;

    public ChapterContentPresenter(BaseActivity context, long bookId) {
        super(context);
        mBook=NovelDB.BookQuertById(bookId);
    }


    @Override
    public void subscribe() {

    }

    public void getContent(ChapterMessage chapterMessage,int position){
        Disposable disposable= Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                String content=checkSaveData(chapterMessage.getId());

                if (TextUtils.isEmpty(content)){
                    Document doc = Jsoup.connect(chapterMessage.getUrl())
                            .get();
                    Element sections=doc.getElementById(mBook.getContentKey());
                    content=sections.toString();

                    ChapterContent chapterContent=new ChapterContent();
                    chapterContent.setBookId(chapterMessage.getBookId());
                    chapterContent.setChapterId(chapterMessage.getId());
                    chapterContent.setContent(content);
                    chapterContent.setUrl(chapterMessage.getUrl());
                    NovelDB.ChapterContentAdd(chapterContent);
                    L.debug("content network,save data");
                }else {
                    L.debug("content local");
                }

                e.onNext(content);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String content) throws Exception {
                        getView().receivedContent(content,position);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private String checkSaveData(long chapterId){
        ChapterContent chapterContent=NovelDB.chapterContentQuertByChapterId(chapterId);
        return chapterContent==null?"":chapterContent.getContent();
    }

    public void loadLocalChapters(long bookId){
        L.debug("loadLocalData");
        Single.create(new SingleOnSubscribe<List<ChapterMessage>>() {
            @Override
            public void subscribe(SingleEmitter<List<ChapterMessage>> emitter) {
                emitter.onSuccess(NovelDB.ChapterQuertAllById(bookId));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ChapterMessage>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(List<ChapterMessage> chapters) {
                        L.debug("local size:"+chapters.size());
                        getView().receivedChapters(chapters);
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.debug("Throwable:"+e);
                        getView().onFailed(e);
                    }

                });
    }

}
