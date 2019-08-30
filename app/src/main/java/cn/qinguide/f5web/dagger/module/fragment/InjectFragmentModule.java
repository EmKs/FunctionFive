package cn.qinguide.f5web.dagger.module.fragment;

import android.view.View;

import com.ljy.devring.di.scope.FragmentScope;

import java.util.ArrayList;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.entity.AppInfoEntity;
import cn.qinguide.f5web.common.view.iview.IFiveFragment;
import cn.qinguide.f5web.via.ui.adapter.InjectRecyclerViewAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class InjectFragmentModule {

    private IFiveFragment iView;

    public InjectFragmentModule(IFiveFragment iView) {
        this.iView = iView;
    }

    @FragmentScope
    @Provides
    ArrayList<AppInfoEntity> appInfos() {
        return new ArrayList<>();
    }

    @FragmentScope
    @Provides
    InjectRecyclerViewAdapter injectRecyclerViewAdapter() {
        InjectRecyclerViewAdapter adapter = new InjectRecyclerViewAdapter(appInfos());
        adapter.setEmptyView(View.inflate(iView.getFragmentContext(), R.layout.view_empty, null));
        adapter.openLoadAnimation();
        return adapter;
    }

}
