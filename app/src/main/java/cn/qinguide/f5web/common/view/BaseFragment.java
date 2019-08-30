package cn.qinguide.f5web.common.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kunminx.architecture.business.bus.IResponse;
import com.kunminx.architecture.business.bus.Result;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import cn.qinguide.f5web.common.view.iview.IFiveFragment;
import io.reactivex.functions.Consumer;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseFragment<V extends ViewDataBinding> extends SupportFragment implements IFiveFragment, IResponse {

    protected V dataBinding;

    private RxPermissions rxPermissions;

    private int permissionSize = 0;

    private List<Result> initResult = new ArrayList<>();

    protected boolean isFragmentAnimationEnd = false;

    protected abstract String[] initPermissions();

    protected abstract void permissionDenied();

    public abstract void onResultHandle(Result result);

    protected abstract int getContentLayout();

    protected abstract boolean getHasOptionsMenu();

    protected abstract void register();

    protected abstract void unRegister();

    protected abstract void initView();//做视图相关的初始化工作

    protected abstract void initData();//做数据相关的初始化工作

    protected abstract void initEvent();//做监听事件相关的初始化工作

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (dataBinding == null)
            dataBinding = DataBindingUtil.inflate(inflater, getContentLayout(), container, false);
        setHasOptionsMenu(getHasOptionsMenu());
        return dataBinding.getRoot();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        applyPermissions();
    }

    private void applyPermissions() {
        if (initPermissions() == null)
            init();
        else {
            if (rxPermissions == null)
                rxPermissions = new RxPermissions(getFragmentActivity());
            rxPermissions.request(initPermissions()).subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    permissionSize++;
                    if (!aBoolean) permissionDenied();
                    else if (permissionSize == initPermissions().length) init();
                }
            }).isDisposed();
        }
    }

    private void init() {
        register();
        initView();
        initData();
        initEvent();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (dataBinding != null) {
            dataBinding.unbind();
            dataBinding = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegister();
        isFragmentAnimationEnd = false;
        System.gc();
        System.runFinalization();
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        loadInitData();
        isFragmentAnimationEnd = true;
    }

    private void loadInitData() {
        if (initResult.size() > 0)
            for (Result result : initResult) {
                onResultHandle(result);
            }
        initResult.clear();
    }


    @Override
    public void onResult(Result testResult) {
        if (!isFragmentAnimationEnd && testResult != null) {
            initResult.add(testResult);
            return;
        }
        onResultHandle(testResult);
    }

}
