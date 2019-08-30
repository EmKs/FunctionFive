package cn.qinguide.f5web.dagger.module.fragment;

import cn.qinguide.f5web.common.view.iview.IFiveFragment;
import dagger.Module;

@Module
public class AboutFragmentModule {

    private IFiveFragment iView;

    public AboutFragmentModule(IFiveFragment iView) {
        this.iView = iView;
    }


}
