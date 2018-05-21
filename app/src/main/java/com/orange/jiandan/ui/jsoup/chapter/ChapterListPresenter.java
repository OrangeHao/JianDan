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
import java.util.Collections;
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
    private List<ChapterMessage> mDataList=new ArrayList<>();

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
                        //去重
                        if (!haveSameData(chapterMessage,chapterList)){
                            chapterList.add(chapterMessage);
                        }
                    }
                }else {
                    for (Element element:chapters){
                        ChapterMessage chapterMessage=new ChapterMessage();
                        chapterMessage.setBookId(mBook.getId());
                        chapterMessage.setUrl(mBook.getUrl()+element.attr("href"));
                        chapterMessage.setTitle(element.text());
                        //去重
                        if (!haveSameData(chapterMessage,chapterList)){
                            chapterList.add(chapterMessage);
                        }
                    }
                }
                Log.d("czh","get:"+chapterList.get(0).getUrl());
                L.debug("new chapter size:"+chapterList.size());
                updateData(chapterList,mDataList);
                e.onNext(mDataList);
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
                            mDataList.addAll(chapters);
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

    private void updateData(List<ChapterMessage> newList,List<ChapterMessage> oldList){
        int oldLen=oldList.size();
        int newLen=newList.size();

        //升序
        if (!isAsc(oldList)){
            Collections.reverse(oldList);
        }
        if (!isAsc(newList)){
            Collections.reverse(newList);
        }


        if (newLen==oldLen){
            return;
        }

        if (newLen<oldLen){
            return;
        }

        for (int i=0;i<newLen;i++){
            ChapterMessage chapter=newList.get(i);
            boolean contains=false;
            for (int j=0;j<oldLen;j++){
                if (oldList.get(j).getUrl().equals(chapter.getUrl())){
                    contains=true;
                    break;
                }
            }
            if (!contains){
                oldList.add(chapter);
            }
        }

        NovelDB.ChapterAdd(oldList);
    }


    /**
     * 判断是否是升序
     * @param dataList
     * @return
     */
    private boolean isAsc(List<ChapterMessage> dataList){
        if (dataList==null){
            return true;
        }
        int len=dataList.size();
        if (len==0 || len==1){
            return true;
        }
        int firstChapterPosition=0;
        for (int i=0;i<len;i++){
            ChapterMessage chapter=dataList.get(i);
            if (chapter.getTitle().contains("第一章")){
                firstChapterPosition=i;
                break;
            }

            ChapterMessage chapter2=dataList.get(len-i-1);
            if (chapter2.getTitle().contains("第一章")){
                firstChapterPosition=len-i-1;
                break;
            }
        }
        if (firstChapterPosition==0){
            return true;
        }
        if (firstChapterPosition==len){
            return false;
        }

        if (dataList.get(firstChapterPosition+1).getTitle().contains("第二章")){
            return true;
        }

        if (dataList.get(firstChapterPosition-1).getTitle().contains("第二章")){
            return false;
        }

        return true;
    }

    /**
     * 去重
     * @param message
     * @param chapters
     * @return
     */
    private boolean haveSameData(ChapterMessage message,List<ChapterMessage> chapters){
        for (ChapterMessage bean:chapters){
            if (bean.getUrl().equals(message.getUrl())){
                return true;
            }
        }
        return false;
    }




}
