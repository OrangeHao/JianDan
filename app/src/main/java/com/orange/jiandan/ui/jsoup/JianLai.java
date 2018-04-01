package com.orange.jiandan.ui.jsoup;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * created by czh on 2018/3/29
 */

public class JianLai {

    private final String Url="http://www.jianlaixiaoshuo.com/";
    private final String Name_Class_chapter="chapterlist";
    private final String Name_Class_chaptertext="BookText";

    public void getChapters(DataListner listner){
        Observable.create(new ObservableOnSubscribe<List<Chapter>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Chapter>> e) throws Exception {
                Document doc = Jsoup.connect(Url)
                        .get();
                Elements chapters=doc.getElementsByClass(Name_Class_chapter).select("a[href]");

                List<Chapter> chapterList=new ArrayList<Chapter>();
                for (Element element:chapters){
                    chapterList.add(new Chapter(Url+element.attr("href"),element.text()));
                }
                Log.d("czh","get:"+chapterList.get(0).getUrl());
                e.onNext(chapterList);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Chapter>>() {
                    @Override
                    public void accept(List<Chapter> list) throws Exception {
                        if (listner!=null){
                            listner.getChapters(list);
                        }
                    }
                });
    }


    public void getContent(String url, DataListner listner){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Document doc = Jsoup.connect(url)
                        .get();
                Element sections=doc.getElementById(Name_Class_chaptertext);
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
                        if (listner!=null){
                            listner.getBookText(content);
                        }
                    }
                });
    }


    interface DataListner{
        void getChapters(List<Chapter> list);
        void getBookText(String content);
    }

    public static String tagfilter(String str){
        final String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签
        Pattern pattern = Pattern.compile(regxpForHtml);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
