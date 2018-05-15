package com.orange.jiandan.model.novel;

import com.orange.jiandan.app.MyApp;

import java.util.List;

import io.objectbox.Box;

/**
 * created by czh on 2018/5/13
 */
public class NovelDB {

    private static Box<BookMessage> getBookMessageBox(){
        return MyApp.getBoxStore().boxFor(BookMessage.class);
    }

    private static Box<chapterMessage> getChapterMessageBox(){
        return MyApp.getBoxStore().boxFor(chapterMessage.class);
    }

    /*********************** book **************************/

    public static void BookAdd(BookMessage book){
        getBookMessageBox().put(book);
    }

    public static void BookAdd(List<BookMessage> books){
        getBookMessageBox().put(books);
    }

    public static void BookDelete(){

    }
    public static BookMessage BookQuertById(long id){
        return getBookMessageBox().query().equal(BookMessage_.__ID_PROPERTY,id).build().findUnique();
    }

    public static List<BookMessage> BookQuertAll(){
        return getBookMessageBox().query().build().find();
    }

    public static void BookUpdate(BookMessage book){
        getBookMessageBox().put(book);
    }


    /*********************** chapter **************************/

    public static void ChapterAdd(chapterMessage chapter){
        getChapterMessageBox().put(chapter);
    }

    public static void ChapterAdd(List<chapterMessage> chapters){
        getChapterMessageBox().put(chapters);
    }

    public static void ChapterDelete(){

    }

    public static List<chapterMessage> ChapterQuertAllById(long id){
        return getChapterMessageBox().query().equal(chapterMessage_.bookId,id).build().find();
    }

    public static void ChapterUpdate(chapterMessage chapter){
        getChapterMessageBox().put(chapter);
    }

    public static void ChapterUpdate(List<chapterMessage> chapters){
        getChapterMessageBox().put(chapters);
    }



}
