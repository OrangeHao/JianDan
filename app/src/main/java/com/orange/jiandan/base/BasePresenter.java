/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.orange.jiandan.base;

import android.support.annotation.NonNull;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Presenter基类  可用可不用
 * @param <V>
 */
public abstract class BasePresenter<V> {


    public BaseActivity mContext;
    protected Reference<V> mViewRef;
    @NonNull
    protected CompositeDisposable mCompositeDisposable;


    public BasePresenter(BaseActivity context) {
        mContext = context;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }

    protected abstract void subscribe();

    protected  void unsubscribe(){
        mCompositeDisposable.clear();
    };

}