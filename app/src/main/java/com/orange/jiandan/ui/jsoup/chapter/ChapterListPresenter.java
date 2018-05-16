package com.orange.jiandan.ui.jsoup.chapter;

import android.text.TextUtils;
import android.util.Log;

import com.orange.jiandan.base.BaseActivity;
import com.orange.jiandan.base.BasePresenter;
import com.orange.jiandan.model.novel.BookMessage;
import com.orange.jiandan.model.novel.NovelDB;
import com.orange.jiandan.model.novel.ChapterMessage;
import com.orange.jiandan.ui.jsoup.bean.Chapter;
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
 * created by czh on 2018/5/7
 */
public class ChapterListPresenter extends BasePresenter<ChapterListView> {

    private BookMessage mBook;

    public ChapterListPresenter(BaseActivity context,long bookId) {
        super(context);
        mBook= NovelDB.BookQuertById(bookId);
    }


    @Override
    public void subscribe() {

    }

    public void loadNewData(){
        L.debug("loadNewData");
        Disposable disposable=Observable.create(new ObservableOnSubscribe<List<ChapterMessage>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ChapterMessage>> e) throws Exception {
                Document doc = Jsoup.connect(mBook.getUrl())
                        .get();
                Elements chapters;
                chapters=doc.getElementsByClass(mBook.getChapterKey()).select("a[href]");
                if (TextUtils.isEmpty(chapters.toString())){
                    Element temp=doc.getElementById(mBook.getChapterKey());
                    if (temp==null){
                        return;
                    }
                    chapters=temp.getElementsByTag("a");
                }
                List<ChapterMessage> chapterList=new ArrayList<>();
                Log.d("czh","get:"+chapters.get(0).toString());
                if (!TextUtils.isEmpty(mBook.getBaseUrl())){
                    for (Element element:chapters){
                        ChapterMessage chapterMessage=new ChapterMessage();
                        chapterMessage.setBookId(mBook.getId());
                        chapterMessage.setUrl(mBook.getBaseUrl()+element.attr("href"));
                        chapterMessage.setTitle(element.text());
                        chapterList.add(chapterMessage);
                    }
                }else {
                    for (Element element:chapters){
                        ChapterMessage chapterMessage=new ChapterMessage();
                        chapterMessage.setBookId(mBook.getId());
                        chapterMessage.setUrl(mBook.getUrl()+element.attr("href"));
                        chapterMessage.setTitle(element.text());
                        chapterList.add(chapterMessage);
                    }
                }
                Log.d("czh","get:"+chapterList.get(0).getUrl());

                L.debug("local size:"+chapterList.size());
                //save to db
                NovelDB.ChapterAdd(chapterList);
                e.onNext(chapterList);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ChapterMessage>>() {
                    @Override
                    public void accept(List<ChapterMessage> list) throws Exception {
                        getView().getChapters(list);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void loadLocalData(){
        L.debug("loadLocalData");
        Single.create(new SingleOnSubscribe<List<ChapterMessage>>() {
            @Override
            public void subscribe(SingleEmitter<List<ChapterMessage>> emitter) {
                emitter.onSuccess(loadChapters());
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
                        if (chapters.size()==0){
                            loadNewData();
                        }else {
                            getView().getChapters(chapters);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.debug("Throwable:"+e);
                        getView().onFailed(e);
                    }

                });
    }

    private List<ChapterMessage> loadChapters(){
        return NovelDB.ChapterQuertAllById(mBook.getId());
    }


}
