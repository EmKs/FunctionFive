package cn.qinguide.f5web.via.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.kunminx.architecture.business.bus.Result;

import cn.qinguide.f5web.R;

import cn.qinguide.f5web.common.view.BaseFragment;
import cn.qinguide.f5web.dagger.component.fragment.DaggerUploadFragmentComponent;
import cn.qinguide.f5web.dagger.module.fragment.UploadFragmentModule;
import cn.qinguide.f5web.databinding.FragmentUploadBinding;
import cn.qinguide.f5web.via.business.bus.MainBus;

public class UploadFragment extends BaseFragment<FragmentUploadBinding> {

    public UploadFragment() {

    }

    public static UploadFragment newInstance() {
        return new UploadFragment();
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
        return R.layout.fragment_upload;
    }

    @Override
    protected boolean getHasOptionsMenu() {
        return true;
    }

    @Override
    protected void register() {
        DaggerUploadFragmentComponent.builder().uploadFragmentModule(new UploadFragmentModule(this)).build().inject(this);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.upload, menu);
    }
}
