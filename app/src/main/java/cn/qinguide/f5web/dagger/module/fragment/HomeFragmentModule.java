package cn.qinguide.f5web.dagger.module.fragment;

import android.view.View;

import com.ljy.devring.di.scope.FragmentScope;

import java.util.ArrayList;

import cn.qinguide.f5web.R;
import cn.qinguide.f5web.common.entity.ScriptEntity;
import cn.qinguide.f5web.common.view.iview.IFiveFragment;
import cn.qinguide.f5web.via.repertory.proxy.HomeProxy;
import cn.qinguide.f5web.via.ui.adapter.HomeRecyclerViewAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class HomeFragmentModule {

    private IFiveFragment iView;

    public HomeFragmentModule(IFiveFragment iView) {
        this.iView = iView;
    }

    @FragmentScope
    @Provides
    HomeProxy homeProxy() {
        return new HomeProxy();
    }

    @FragmentScope
    @Provides
    ArrayList<ScriptEntity> scripts() {
        return new ArrayList<>();
    }

    @FragmentScope
    @Provides
    HomeRecyclerViewAdapter adapter() {
        HomeRecyclerViewAdapter adapter = new HomeRecyclerViewAdapter(scripts(), homeProxy());
        View empty = View.inflate(iView.getFragmentContext(), R.layout.view_empty, null);
        empty.setPadding(0, 200, 0, 0);
        adapter.setEmptyView(empty);
        adapter.openLoadAnimation();
        return adapter;
    }

}
