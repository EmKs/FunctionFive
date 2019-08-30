package cn.qinguide.f5web.via.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.NestedScrollView;
import android.view.View;

import com.kunminx.architecture.business.bus.Result;

import java.util.List;

import javax.inject.Inject;

import cn.qinguide.f5web.common.constant.KeyConstants;
import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.common.utils.ActiveUtil;
import cn.qinguide.f5web.dagger.component.fragment.DaggerHomeFragmentComponent;
import cn.qinguide.f5web.databinding.FragmentHomeBinding;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.view.BaseFragment;
import cn.qinguide.f5web.dagger.module.fragment.HomeFragmentModule;
import cn.qinguide.f5web.via.business.bus.MainBus;
import cn.qinguide.f5web.via.repertory.proxy.HomeProxy;
import cn.qinguide.f5web.via.ui.adapter.HomeRecyclerViewAdapter;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements NestedScrollView.OnScrollChangeListener {

    @Inject
    HomeProxy homeProxy;
    @Inject
    HomeRecyclerViewAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected boolean getHasOptionsMenu() {
        return false;
    }

    @Override
    protected void register() {
        DaggerHomeFragmentComponent.builder().homeFragmentModule(new HomeFragmentModule(this)).build().inject(this);
        MainBus.registerResponseObserver(this);
    }

    @Override
    protected void unRegister() {
        MainBus.unregisterResponseObserver(this);
    }


    @Override
    protected void initView() {
        dataBinding.switchHome.setChecked(ActiveUtil.isModuleActive() && ActiveUtil.isF5Active);
        dataBinding.recyclerViewHome.setAdapter(adapter);
        setEnabledTips();
    }

    private void setEnabledTips() {
        if (ActiveUtil.isModuleActive()) {
            if (ActiveUtil.isF5Active) {
                dataBinding.textViewTipsHome.setText(String.format(getString(R.string.functionFive_situation), getString(R.string.tips_success)));
                dataBinding.frameLayoutStatusHome.setBackgroundColor(getResources().getColor(R.color.green));
                dataBinding.textViewTipsHome.setTextColor(getResources().getColor(R.color.green));
                dataBinding.imageViewTips.setImageResource(R.drawable.ic_success);
            } else {
                dataBinding.textViewTipsHome.setText(String.format(getString(R.string.functionFive_situation), getString(R.string.tips_warning)));
                dataBinding.frameLayoutStatusHome.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                dataBinding.textViewTipsHome.setTextColor(getResources().getColor(R.color.colorAccent));
                dataBinding.imageViewTips.setImageResource(R.drawable.ic_warning);
            }
        } else {
            dataBinding.textViewTipsHome.setText(String.format(getString(R.string.functionFive_situation), getString(R.string.tips_error)));
            dataBinding.frameLayoutStatusHome.setBackgroundColor(getResources().getColor(R.color.red));
            dataBinding.textViewTipsHome.setTextColor(getResources().getColor(R.color.red));
            dataBinding.imageViewTips.setImageResource(R.drawable.ic_error);
        }
    }

    @Override
    protected void initData() {
        MainBus.home().getLocalScript();
    }

    @Override
    protected void initEvent() {
        dataBinding.setHomeProxy(homeProxy);
        dataBinding.nestedScrollViewHome.setOnScrollChangeListener(this);
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
            case KeyConstants.UPDATE_TIPS:
                setEnabledTips();
                break;
            case KeyConstants.UPDATE_SCRIPT_LIST:
                List<ScriptEntity> newData = (List<ScriptEntity>) result.getResultObject();
                if (newData != null && newData.size() > 0)
                    adapter.setNewData(newData);
                break;
            case KeyConstants.LOCAL_SCRIPT_LOAD:
                List<ScriptEntity> localData = (List<ScriptEntity>) result.getResultObject();
                if (localData != null && localData.size() > 0)
                    adapter.setNewData(localData);
                break;
        }
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
    public void onDestroy() {
        MainBus.unregisterResponseObserver(this);
        super.onDestroy();
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int x, int y, int oldX, int oldY) {
        if (y > oldY && dataBinding.floatMenuHome.getVisibility() == View.VISIBLE) {
            dataBinding.floatMenuHome.hideMenu(true);
        } else if (y < oldY && dataBinding.floatMenuHome.getVisibility() == View.GONE)
            dataBinding.floatMenuHome.showMenu(true);
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
