package cn.qinguide.f5web.via.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.kunminx.architecture.business.bus.Result;

import cn.qinguide.f5web.R;

import cn.qinguide.f5web.common.view.BaseFragment;
import cn.qinguide.f5web.dagger.component.fragment.DaggerSupportFragmentComponent;
import cn.qinguide.f5web.dagger.module.fragment.SupportFragmentModule;
import cn.qinguide.f5web.databinding.FragmentSupportBinding;
import cn.qinguide.f5web.via.business.bus.MainBus;

public class SupportFragment extends BaseFragment<FragmentSupportBinding> {

    public SupportFragment(){

    }

    public static SupportFragment newInstance() {
        return new SupportFragment();
    }


    @Override
    protected String[] initPermissions() {
        return null;
    }

    @Override
    protected void permissionDenied() {

    }

    @Override
    public void onResultHandle(Result result) {

    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_support;
    }

    @Override
    protected boolean getHasOptionsMenu() {
        return false;
    }

    @Override
    protected void register() {
        DaggerSupportFragmentComponent.builder().supportFragmentModule(new SupportFragmentModule(this)).build().inject(this);
        MainBus.registerResponseObserver(this);
    }

    @Override
    protected void unRegister() {
        MainBus.unregisterResponseObserver(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public Context getFragmentContext() {
        return getContext();
    }

    @Override
    public FragmentActivity getFragmentActivity() {
        return getActivity();
    }

    @Override
    public void onSaveState(Bundle bundleToSave) {

    }

    @Override
    public void onRestoreState(Bundle bundleToRestore) {

    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }
}
