package com.orange.jiandan.ui.books;

import android.content.Context;

import com.orange.jiandan.base.BasePresenter;
import com.orange.jiandan.model.novel.BookMessage;
import com.orange.jiandan.model.novel.NovelDB;
import com.orange.jiandan.utils.L;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.android.AndroidScheduler;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * created by czh on 2018/5/14
 */
public class BookListPresenter extends BasePresenter<BookListView>{

    public BookListPresenter(Context context) {
        super(context);
    }

    @Override
    public void subscribe() {

    }

    public void getBooks(){
        Single.just(getBooksFromDB())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<BookMessage>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onSuccess(List<BookMessage> bookMessages) {
                        if (bookMessages.size()==0){
                            bookMessages=getInitBooks();
                        }
                        getView().getBooks(bookMessages);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onFailed(e);
                    }
                });
    }

    private List<BookMessage> getBooksFromDB(){
        L.debug("getbooks from database!");
        return NovelDB.BookQuertAll();
    }


    private List<BookMessage> getInitBooks(){
        L.debug("getInitBooks");

        BookMessage jl=new BookMessage();
        jl.setUrl("http://www.jianlaixiaoshuo.com/");
        jl.setChapterKey("chapterlist");
        jl.setContentKey("BookText");

        BookMessage Gm=new BookMessage();
        Gm.setUrl("http://www.bequge.com/0_124/");
        Gm.setBaseUrl("http://www.bequge.com");
        Gm.setChapterKey("list");
        Gm.setContentKey("content");

        BookMessage YLCQ=new BookMessage();
        YLCQ.setUrl("https://www.booktxt.net/7_7067/");
        YLCQ.setBaseUrl("https://www.booktxt.net");
        YLCQ.setChapterKey("list");
        YLCQ.setContentKey("content");

        List<BookMessage> temp=new ArrayList<>();
        temp.add(jl);
        temp.add(Gm);
        temp.add(YLCQ);

        NovelDB.BookAdd(temp);

        return temp;
    }




}
