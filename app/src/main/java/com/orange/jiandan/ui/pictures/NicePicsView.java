package com.orange.jiandan.ui.pictures;

import com.orange.jiandan.base.BaseView;
import com.orange.jiandan.model.NewsBean;
import com.orange.jiandan.model.PicsBean;

import java.util.List;

/**
 * created by czh on 2018/5/11
 */
public interface NicePicsView extends BaseView{

    void getRefreshDatas(List<String> data);
    void getLoadMoreDatas(List<String> data);
    void getHotDatas(List<String> data);
}
