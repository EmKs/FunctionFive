package cn.qinguide.f5web.common.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.kunminx.architecture.business.bus.IResponse;
import com.kunminx.architecture.business.bus.Result;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import cn.qinguide.f5web.common.view.iview.IFiveActivity;
import io.reactivex.functions.Consumer;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseActivity<V extends ViewDataBinding> extends SupportActivity implements IFiveActivity, IResponse {

    protected V dataBinding;

    private RxPermissions rxPermissions;

    private int permissionSize = 0;

    private List<Result> initResult = new ArrayList<>();

    private boolean isResumed = false;

    protected Bundle savedInstanceState;

    protected abstract String[] initPermissions();

    protected abstract Toolbar setToolbar();

    protected abstract boolean showToolbar();

    protected abstract boolean showToolbarBack();

    protected abstract void permissionDenied();

    protected abstract int setContentLayout();

    protected abstract void onResultHandle(Result testResult);

    protected abstract void register();

    protected abstract void unRegister();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();


    private void init() {
        if (dataBinding != null) return;
        dataBinding = DataBindingUtil.setContentView(this, setContentLayout());
        register();
        initView();
        initToolbar();
        initData();
        initEvent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        applyPermissions();
    }

    private void initToolbar() {
        if (showToolbar()) {
            setSupportActionBar(setToolbar());
            if (getSupportActionBar() != null)
                getSupportActionBar().setDisplayHomeAsUpEnabled(showToolbarBack());
        }
    }

    private void applyPermissions() {
        if (initPermissions() == null)
            init();
        else {
            if (rxPermissions == null)
                rxPermissions = new RxPermissions(this);
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

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
        applyPermissions();
        loadInitData();
    }

    private void loadInitData() {
        if (initResult.size() > 0)
            for (Result result : initResult) {
                onResultHandle(result);
            }
        initResult.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegister();
        if (dataBinding != null) {
            dataBinding.unbind();
            dataBinding = null;
        }
        savedInstanceState = null;
        isResumed = false;
        System.gc();
        System.runFinalization();
    }

    @Override
    public void onResult(Result testResult) {
        if (!isResumed && testResult != null) {
            initResult.add(testResult);
            return;
        }
        onResultHandle(testResult);
    }

}
