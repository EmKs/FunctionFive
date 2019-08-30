package cn.qinguide.f5web.dagger.module.fragment;

import android.view.View;

import com.ljy.devring.di.scope.FragmentScope;

import java.util.ArrayList;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.common.view.iview.IFiveFragment;
import cn.qinguide.f5web.via.repertory.proxy.DownloadProxy;
import cn.qinguide.f5web.via.ui.adapter.DownloadRecyclerViewAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class DownloadFragmentModule {

    private IFiveFragment iView;

    public DownloadFragmentModule(IFiveFragment iView) {
        this.iView = iView;
    }

    @FragmentScope
    @Provides
    ArrayList<ScriptEntity> scripts() {
        return new ArrayList<>();
    }

    @FragmentScope
    @Provides
    DownloadRecyclerViewAdapter adapter() {
        DownloadRecyclerViewAdapter adapter = new DownloadRecyclerViewAdapter(scripts());
        adapter.setEmptyView(View.inflate(iView.getFragmentContext(), R.layout.view_empty, null));
        return adapter;
    }

    @FragmentScope
    @Provides
    DownloadProxy downloadProxy() {
        return new DownloadProxy();
    }

}
