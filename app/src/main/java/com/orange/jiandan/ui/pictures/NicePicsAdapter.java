package com.orange.jiandan.ui.pictures;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orange.jiandan.R;

import java.util.List;

/**
 * created by czh on 2018/5/11
 */
public class NicePicsAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public NicePicsAdapter(@Nullable List<String> data) {
        super(R.layout.item_nicepics, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ((SimpleDraweeView)helper.getView(R.id.pic_img)).setImageURI(item);
    }
}
