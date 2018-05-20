package com.orange.jiandan.model;

import com.orange.jiandan.app.MyApp;
import com.orange.jiandan.model.novel.BookMessage;
import com.orange.jiandan.model.novel.BookMessage_;

import java.util.List;

import io.objectbox.Box;

/**
 * created by czh on 2018/5/20
 */
public class JianDanDB {

    private static Box<CollectPicBean> getCollectPicBox(){
        return MyApp.getBoxStore().boxFor(CollectPicBean.class);
    }

    public static void PicAdd(CollectPicBean pic){
        getCollectPicBox().put(pic);
    }

    public static void PicAdd(List<CollectPicBean> pictures){
        getCollectPicBox().put(pictures);
    }

    public static void PicDelete(CollectPicBean pic){
        getCollectPicBox().remove(pic);
    }


    public static List<CollectPicBean> PicQuertAll(){
        return getCollectPicBox().query().build().find();
    }

    public static void PicUpdate(CollectPicBean pic){
        getCollectPicBox().put(pic);
    }


}
