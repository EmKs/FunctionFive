package cn.qinguide.f5web.via.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kunminx.architecture.business.bus.Result;
import com.ljy.devring.other.toast.RingToast;

import java.util.List;

import javax.inject.Inject;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.constant.KeyConstants;
import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.common.view.BaseFragment;
import cn.qinguide.f5web.dagger.component.fragment.DaggerDownloadFragmentComponent;
import cn.qinguide.f5web.dagger.module.fragment.DownloadFragmentModule;
import cn.qinguide.f5web.databinding.FragmentDownloadBinding;
import cn.qinguide.f5web.via.business.bus.MainBus;
import cn.qinguide.f5web.via.repertory.proxy.DownloadProxy;
import cn.qinguide.f5web.via.ui.adapter.DownloadRecyclerViewAdapter;

public class DownloadFragment extends BaseFragment<FragmentDownloadBinding> implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {

    private int page = 1, size = 20;
    @Inject
    DownloadRecyclerViewAdapter adapter;
    @Inject
    DownloadProxy downloadProxy;
    private BottomSheetBehavior bottomSheetBehavior;

    public DownloadFragment() {

    }

    public static DownloadFragment newInstance() {
        return new DownloadFragment();
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
            case KeyConstants.SCRIPT_LIST_LOAD:
                page++;
                adapter.addData((List<ScriptEntity>) result.getResultObject());
                adapter.loadMoreComplete();
                break;
            case KeyConstants.SCRIPT_LIST_FINISHED:
                page++;
                adapter.addData((List<ScriptEntity>) result.getResultObject());
                adapter.loadMoreEnd();
                break;
            case KeyConstants.SCRIPT_LIST_REFRESH:
                page++;
                adapter.setNewData((List<ScriptEntity>) result.getResultObject());
                break;
            case KeyConstants.SCRIPT_LIST_ERROR:
                adapter.loadMoreFail();
                RingToast.show(result.getResultObject().toString());
                break;
            case KeyConstants.SAVE_SCRIPT:
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                RingToast.show(String.format(getString(R.string.save_script), (String) result.getResultObject()));
                MainBus.home().updateScript();
                break;
            case KeyConstants.SAVE_SCRIPT_ERROR:
                RingToast.show(R.string.download_error);
                break;
        }
        dataBinding.swipeRefreshDownload.setRefreshing(false);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_download;
    }

    @Override
    protected boolean getHasOptionsMenu() {
        return true;
    }

    @Override
    protected void register() {
        DaggerDownloadFragmentComponent.builder().downloadFragmentModule(new DownloadFragmentModule(this)).build().inject(this);
        MainBus.registerResponseObserver(this);
    }

    @Override
    protected void unRegister() {
        MainBus.unregisterResponseObserver(this);
    }

    @Override
    protected void initView() {
        bottomSheetBehavior = BottomSheetBehavior.from(dataBinding.layoutBottomSheetDownload);
        dataBinding.swipeRefreshDownload.setRefreshing(true);
        dataBinding.recyclerViewDownload.setAdapter(adapter);
        dataBinding.swipeRefreshDownload.setColorSchemeColors(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.red));

    }

    @Override
    protected void initData() {
        dataBinding.setFragment(this);
        dataBinding.setDownloadProxy(downloadProxy);
        MainBus.download().initScript(page, size, KeyConstants.SCRIPT_LIST_REFRESH, this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.download, menu);
    }

    @Override
    protected void initEvent() {
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        dataBinding.swipeRefreshDownload.setOnRefreshListener(this);
        adapter.setOnLoadMoreListener(this, dataBinding.recyclerViewDownload);
        dataBinding.recyclerViewDownload.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int topRowVerticalPosition = recyclerView.getChildCount() == 0 ? 0 : recyclerView.getChildAt(0).getTop();
                dataBinding.swipeRefreshDownload.setEnabled(topRowVerticalPosition >= 0);

            }
        });
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
    public void onLoadMoreRequested() {
        MainBus.download().initScript(page, size, KeyConstants.SCRIPT_LIST_LOAD, this);
    }

    @Override
    public void onRefresh() {
        page = 1;
        MainBus.download().initScript(page, size, KeyConstants.SCRIPT_LIST_REFRESH, this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            dataBinding.swipeRefreshDownload.setEnabled(false);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        } else {
            MainBus.download().saveScript(((ScriptEntity) adapter.getData().get(position)));
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            dataBinding.swipeRefreshDownload.setEnabled(true);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN || bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            dataBinding.swipeRefreshDownload.setEnabled(false);
            dataBinding.setScriptEntity((ScriptEntity) adapter.getData().get(position));
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }
}
