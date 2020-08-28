package com.zhangwy.sample.presenter;


import com.zhangwy.sample.view.BaseView;

@SuppressWarnings("unused")
public abstract class BasePresenter<T extends BaseView> {
    private T view;

    public BasePresenter(T view) {
        this.view = view;
    }

    protected boolean hasView() {
        return this.view != null;
    }

    protected T getView() {
        return this.view;
    }

    public final void destroy() {
        this.onDestroy();
        this.view = null;
    }

    abstract void onDestroy();
}
