package com.orange.jiandan.data;

import com.orange.jiandan.data.api.ApiRepository;

/**
 * created by czh on 2018/5/9
 */
public class DataResourceSwitch {

    public static DataResource getDataResource(){
        return ApiRepository.getInstance();
    }

}
