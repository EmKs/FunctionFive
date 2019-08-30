package cn.qinguide.f5web.via.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.kunminx.architecture.business.bus.Result;
import com.ljy.devring.other.toast.RingToast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.constant.KeyConstants;
import cn.qinguide.f5web.common.entity.AppInfoEntity;
import cn.qinguide.f5web.common.view.BaseFragment;
import cn.qinguide.f5web.dagger.component.fragment.DaggerInjectFragmentComponent;
import cn.qinguide.f5web.dagger.module.fragment.InjectFragmentModule;
import cn.qinguide.f5web.databinding.FragmentInjectBinding;
import cn.qinguide.f5web.via.business.bus.MainBus;
import cn.qinguide.f5web.via.ui.adapter.InjectRecyclerViewAdapter;

public class InjectFragment extends BaseFragment<FragmentInjectBinding> implements SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    ArrayList<AppInfoEntity> appInfos;
    @Inject
    InjectRecyclerViewAdapter adapter;

    public InjectFragment() {

    }

    public static InjectFragment newInstance() {
        return new InjectFragment();
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
        String resultCode = (String) result.getResultCode();
        switch (resultCode) {
            case KeyConstants.APP_LIST:
                appInfos.clear();
                appInfos.addAll((List<AppInfoEntity>) result.getResultObject());
                adapter.setNewData(appInfos);
                break;
            case KeyConstants.APP_LIST_ERROR:
                RingToast.show(R.string.get_app_install_error);
                break;
            case KeyConstants.APP_LIST_SEARCH:
                adapter.setNewData((List<AppInfoEntity>) result.getResultObject());
                break;
        }
        dataBinding.swipeRefreshInject.setRefreshing(false);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_inject;
    }

    @Override
    protected boolean getHasOptionsMenu() {
        return true;
    }

    @Override
    protected void register() {
        DaggerInjectFragmentComponent.builder().injectFragmentModule(new InjectFragmentModule(this)).build().inject(this);
        MainBus.registerResponseObserver(this);
    }

    @Override
    protected void unRegister() {
        MainBus.unregisterResponseObserver(this);
    }

    @Override
    protected void initView() {
        dataBinding.swipeRefreshInject.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.red));
        dataBinding.recyclerViewInject.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        dataBinding.swipeRefreshInject.setRefreshing(true);
        MainBus.inject().getAppInfos(getContext());
    }

    @Override
    protected void initEvent() {
        dataBinding.swipeRefreshInject.setOnRefreshListener(this);
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
        inflater.inflate(R.menu.inject, menu);
        setMenuView(menu);
    }

    private void setMenuView(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.menu_search_inject);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (s.length() == 0 || TextUtils.isEmpty(s))
            adapter.setNewData(appInfos);
        else MainBus.inject().searchAppInfos(s);
        return false;
    }

    @Override
    public void onRefresh() {
        MainBus.inject().getAppInfos(getFragmentContext());
    }
}
