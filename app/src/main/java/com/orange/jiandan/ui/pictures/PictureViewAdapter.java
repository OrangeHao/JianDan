package com.orange.jiandan.ui.pictures;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.orange.jiandan.R;
import com.orange.jiandan.ui.jsoup.bean.Chapter;
import com.orange.jiandan.widget.zoomable.DoubleTapGestureListener;
import com.orange.jiandan.widget.zoomable.ZoomableDraweeView;

import java.util.List;

/**
 * created by czh on 2018/5/20
 */
public class PictureViewAdapter extends PagerAdapter{

    private List<String> list;

    public PictureViewAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_picture_view,null);
        ZoomableDraweeView zoomableDraweeView = (ZoomableDraweeView) view.findViewById(R.id.draweeview);
        //允许缩放时切换
        zoomableDraweeView.setAllowTouchInterceptionWhileZoomed(true);

        //长按
        zoomableDraweeView.setIsLongpressEnabled(false);
        //双击击放大或缩小
        zoomableDraweeView.setTapListener(new DoubleTapGestureListener(zoomableDraweeView));

        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(list.get(position))
                .build();
        //加载图片
        zoomableDraweeView.setController(draweeController);
        container.addView(view);
        view.requestLayout();

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

}
